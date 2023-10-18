import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {



        Commands commands = new Commands();

        // names of files which will be used

        //String peopleFilePath = args[0];
        String peopleFilePath = "people.txt";

        //String filmFilePath = args[1];
        String filmFilePath = "films.txt";

        //String commandsFilePath = args[2];
        String commandsFilePath = "commands.txt";

        //String outputFilePath = args[3];
        String outputFilePath = "output.txt";


        String[][] peopleStringArray = commands.convertFileToStringArray(peopleFilePath);
        ArrayList<Person> personArray = commands.StringToPersonArray(peopleStringArray);

        String[][] filmStringArray = commands.convertFileToStringArray(filmFilePath);
        ArrayList<Film> filmArray = commands.StringToFilmArray(filmStringArray);

        String[][] commandsArray = commands.convertFileToStringArray(commandsFilePath);


        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath, false));
        bw.close();



        // applies the commands line by line
        for(String[] commandLine: commandsArray){

            // We constructed this BufferedWriter on every beginning of the loop instead of constructing only once. Because somehow if
            // an error occurs, any of the outputs can't be written to output file

            bw = new BufferedWriter(new FileWriter(outputFilePath, true));

            for(String word: commandLine){
                bw.write(word +"\t");
            }
            bw.write("\n\n");


            if(commandLine[0].equals("RATE")){
                Integer personID = Integer.valueOf(commandLine[1]);
                Integer filmID = Integer.valueOf(commandLine[2]);
                Integer ratingPoint = Integer.valueOf(commandLine[3]);

                if(ratingPoint > 10 || ratingPoint < 0){
                    System.out.println("Error! Rating score must be between 1 and 10 integers");
                }
                else{
                    User person = new User();
                    if(commands.getPersonFromId(personID,personArray).getClass() == User.class){
                        person = (User)commands.getPersonFromId(personID,personArray);
                    }

                    Film film = commands.getFilmFromId(filmID,filmArray);


                    if(person != null && film != null && !person.getRatedFilms().containsKey(filmID) && person.getName() != null){
                        ((User) person).rateFilm(film.getFilmID(),ratingPoint);
                        film.addRating(person.getID(), ratingPoint);
                        //commands.addRating(person,film,ratingPoint);
                        bw.write("Film rated successfully\n");
                        bw.write("Film type: " + film.toString());
                        bw.write("\nFilm title: " + film.getFilmTitle() + "\n");
                    }
                    else if(person.getRatedFilms().containsKey(filmID)){
                        bw.write("This film was earlier rated\n");
                    }

                    else{
                        bw.write("Command Failed\n");
                        bw.write("User ID: " + personID);
                        bw.write("\nFilm ID: " + filmID + "\n");
                    }
                }



            }

            else if(commandLine[0].equals("ADD") && commandLine[1].equals("FEATUREFILM")){


                ArrayList<String> temp = (ArrayList<String>) Arrays.asList(commandLine);
                temp.remove(0);
                temp.set(0,"FeatureFilm:");
                String[] newLine = temp.toArray(new String[0]); // TODO improve performance since theres a redundant string array initialization extra



                Film film = commands.toFilm(newLine);

                Director directorr = new Director();
                Performer performerr = new Performer();
                Writer writerr = new Writer();

                if(!(filmArray.contains(film)) && commands.isEveryoneExist(directorr,newLine[4],personArray)
                        && commands.isEveryoneExist(performerr,newLine[7],personArray)
                        && commands.isEveryoneExist(writerr,newLine[10],personArray)){

                    filmArray.add(film);
                    bw.write("FeatureFilm added successfully");

                }
                else{
                    bw.write("Command Failed");
                }
                bw.write("\nFilm ID: " + film.getFilmID() + "\n");
                bw.write("Film title: " + film.getFilmTitle() + "\n");


            }

            else if(commandLine[0].equals("VIEWFILM")){
                if(commands.isFilmExist(Integer.valueOf(commandLine[1]),filmArray)){
                    Film film = commands.getFilmFromId(Integer.valueOf(commandLine[1]),filmArray);


                    if(film.getClass() == FeatureFilm.class){
                        bw.write(film.getFilmTitle() + " " + film.getDate() +"\n");
                        commands.printArrayListWithComma(((FeatureFilm) film).getGenres(),bw);
                        bw.write( "\nWriters: ");
                        /*
                        for(Integer writerID: ((FeatureFilm) film).getWriterIDs()){
                            Person person = commands.getPersonFromId(writerID, personArray);
                            bw.write(person.getName() + " " + person.getSurname() + ", ");
                        }
                        */
                        commands.printPersonNames(((FeatureFilm) film).getWriterIDs(),personArray,bw);
                    }
                    else if(film.getClass() == ShortFilm.class){
                        bw.write(film.getFilmTitle() + " " + film.getDate() + "\n");
                        commands.printArrayListWithComma(((ShortFilm) film).getGenres(),bw);
                        bw.write( "\nWriters: ");
                        for(Integer writerID: ((ShortFilm) film).getWriterIDs()){
                            Person person = commands.getPersonFromId(writerID, personArray);
                            bw.write(person.getName() + " " + person.getSurname() + " ");
                        }
                        bw.write("\n");
                    }
                    else if(film.getClass() == Documentary.class){
                        bw.write(film.getFilmTitle() + " " + film.getDate() + "\n\n");
                    }
                    else if(film.getClass() == TVSeries.class){
                        bw.write(film.getFilmTitle() + " " + film.getDate() + "\n");
                        bw.write(((TVSeries) film).getNumberOfSeasons() + " seasons, " + ((TVSeries) film).getNumberOfEpisodes() + " episodes\n");
                        commands.printArrayListWithComma(((TVSeries) film).getGenres(),bw);
                        bw.write( "\nWriters: ");
                        commands.printPersonNames(((TVSeries) film).getWriterIDs(),personArray,bw);
                    }

                    bw.write("Directors: ");
                    commands.printPersonNames(film.getDirectorIDs(),personArray,bw);

                    bw.write("Stars: ");
                    commands.printPersonNames(film.getPerformerIDs(),personArray,bw);

                    if(!film.getRatings().isEmpty()){
                        bw.write( "Ratings: " + film.printRating() + "/10 from " + film.getRatings().size() +" users");

                    }
                    else{
                        bw.write("Awaiting for votes");
                    }

                    bw.write("\n");
                }



            }
            else if(commandLine[0].equals("LIST") && commandLine[1].equals("USER") && commandLine[3].equals("RATES")){
                int userID = Integer.parseInt(commandLine[2]);

                if(commands.getPersonFromId(userID,personArray).getClass() == User.class){

                    User user = (User) commands.getPersonFromId(userID,personArray);
                    if(!user.getRatedFilms().isEmpty()){
                        for(Integer ratedFilmID: user.getRatedFilms().keySet()){
                            Film film = commands.getFilmFromId(ratedFilmID, filmArray);
                            bw.write(film.getFilmTitle() + ": " + film.getRatings().get(userID) +"\n");
                        }
                    }
                    else{
                        bw.write("There is not any ratings so far");
                    }
                }
                else{
                    bw.write("Command Failed:\nUser ID: " + userID + "\n");
                }



            }

            else if(commandLine[0].equals("EDIT") && commandLine[1].equals("RATE")){
                int userID = Integer.parseInt(commandLine[2]);
                int filmID = Integer.parseInt(commandLine[3]);
                int newRatingPoint = Integer.parseInt(commandLine[4]);

                User user = (User) commands.getPersonFromId(userID,personArray);
                Film film = commands.getFilmFromId(filmID,filmArray);

                if(user != null && film != null && user.getRatedFilms().get(filmID) != null){
                    user.editRating(filmID,newRatingPoint);
                    film.editRating(userID,newRatingPoint);
                    bw.write("New ratings done successfully\n");
                    bw.write("Film title: " + film.getFilmTitle() + "\n");
                    bw.write("Your rating: " + newRatingPoint + "\n");
                }
                else{
                    bw.write("Command Failed\n");
                    bw.write("User ID: " + userID);
                    bw.write("\nFilm ID: " + filmID + "\n");
                }
            }

            else if(commandLine[0].equals("REMOVE") && commandLine[1].equals("RATE")){
                int userID = Integer.parseInt(commandLine[2]);
                int filmID = Integer.parseInt(commandLine[3]);

                User user = (User) commands.getPersonFromId(userID,personArray);
                Film film = commands.getFilmFromId(filmID,filmArray);

                if(user != null && film != null && user.getRatedFilms().get(filmID) != null){
                    user.removeRating(filmID);
                    film.removeRating(userID);
                    bw.write("Your film rating was removed successfully\nFilm title: " + film.getFilmTitle() + "\n");
                }
                else{
                    bw.write("Command Failed\n");
                    bw.write("User ID: " + user.getID() + "\n");
                    bw.write("Film ID: " + film.getFilmID() + "\n");
                }

            }
            else if(commandLine[0].equals("LIST") && commandLine[1].equals("FILM") && commandLine[2].equals("SERIES")){

                int count = 0;

                ArrayList<Film> films = (ArrayList<Film>) filmArray.stream().filter(film -> film.getClass() == TVSeries.class).collect(Collectors.toList());

                for(Film film: films){
                        bw.write(film.getFilmTitle() + " " + film.getDate() + "\n");
                        bw.write(((TVSeries) film).getNumberOfSeasons() + " seasons and " + ((TVSeries) film).getNumberOfEpisodes() + " episodes\n");
                        count++;

                    if(count < films.size()){
                        bw.write("\n");
                    }

                }
                if(count == 0){
                    bw.write("No result");
                }
            }

            else if(commandLine[0].equals("LIST") && commandLine[1].equals("FILMS") && commandLine[2].equals("BY") && commandLine[3].equals("COUNTRY")){
                String countryName = String.valueOf(commandLine[4]);


                ArrayList<Film> films = new ArrayList<>();
                for(Film film: filmArray){
                    if(film.getCountry().equals(countryName)){
                        films.add(film);
                    }
                }
                int count = 0;

                for(Film film: films){
                    bw.write("Film title: " + film.getFilmTitle() + "\n");
                    bw.write(film.getRunTime() + " min\n");
                    bw.write("Language: " + film.getLanguage() + "\n");
                    count++;

                    if(count < films.size()){ bw.write("\n"); }

                }
                if(count == 0){
                    bw.write("No result\n");
                }
            }

            else if(commandLine[0].equals("LIST") && commandLine[1].equals("FEATUREFILMS")){

                int year = Integer.parseInt(commandLine[3]);
                Predicate<Film> filterPredicate = film -> false;

                switch (commandLine[2]) {
                    case "BEFORE": {
                        filterPredicate = film -> film.getClass() == FeatureFilm.class && Integer.parseInt(((FeatureFilm) film).getReleaseDate().substring(6)) <= year;
                        break;
                    }
                        case "AFTER": {
                        filterPredicate = film -> film.getClass() == FeatureFilm.class && Integer.parseInt(((FeatureFilm) film).getReleaseDate().substring(6)) >= year;
                        break;
                    }
                }

                ArrayList<Film> films = (ArrayList<Film>) filmArray.stream()
                        .filter(filterPredicate)
                        .collect(Collectors.toList());

                bw.write(commands.getFeatureFilmData(films));

            }

            else if(commandLine[0].equals("LIST") && commandLine[1].equals("FILMS") && commandLine[2].equals("BY") && commandLine[3].equals("RATE") && commandLine[4].equals("DEGREE")){
                ArrayList<Film> sampleFilms = new ArrayList<>();

                int i = 0;

                sampleFilms.add(new FeatureFilm());
                sampleFilms.add(new ShortFilm());
                sampleFilms.add(new Documentary());
                sampleFilms.add(new TVSeries());


                for(Film film: sampleFilms){

                    ArrayList<Film> films = (ArrayList<Film>) filmArray.stream().filter(it -> it.getClass().equals(film.getClass())).collect(Collectors.toList());

                    films.sort(Commands.rateDegreeComparator);

                    //bw.write("\n");

                    System.out.println(film.getClass().getName() + ":\n");

                    for(Film film1: films){
                        bw.write( film1.getFilmTitle() + " " + film1.getDate());
                        bw.write(" Ratings: " + film1.printRating() + "/10 from " + film1.getRatings().size() +" users\n");
                    }
                    i++;
                    if(i < sampleFilms.size()){
                        bw.write("\n");
                    }
                }



            }

            else if(commandLine[0].equals("LIST") && commandLine[1].equals("ARTISTS") && commandLine[2].equals("FROM")){
                String country = commandLine[3];

                ArrayList<ArrayList<Artist>> artists = new ArrayList<>();
                for(int i = 0;i < 5;i++){
                    artists.add(new ArrayList<>());
                }



                for(Person person: personArray){
                    if(person.getCountry().equals(country)){
                        if(person.getClass() == Director.class){ artists.get(0).add((Director) person); }
                        else if(person.getClass() == Writer.class){ artists.get(1).add((Writer) person); }
                        else if(person.getClass() == Actor.class){ artists.get(2).add((Actor) person); }
                        else if(person.getClass() == ChildActor.class){ artists.get(3).add((ChildActor) person); }
                        else if(person.getClass() == StuntPerformer.class){ artists.get(4).add((StuntPerformer) person); }
                    }
                }

                for(int i = 0;i < artists.size();i++){
                    if(i == 0){ bw.write("Directors:\n"); }
                    else if(i == 1){ bw.write("\nWriters:\n"); }
                    else if(i == 2){ bw.write("\nActors:\n"); }
                    else if(i == 3){ bw.write("\nChildActors:\n"); }
                    else if(i == 4){ bw.write("\nStuntPerformers:\n"); }

                    if(artists.get(i).isEmpty()){
                        bw.write("No result\n");
                    }
                    else{
                        for(Artist artist: artists.get(i)){

                            bw.write(artist.getName() + " " + artist.getSurname() + " ");
                            if(i == 0){ bw.write(((Director)artist).getAgent()); }
                            else if(i == 1){ bw.write(((Writer)artist).getWritingStyle()); }
                            else if(i == 2){ bw.write(((Actor)artist).getHeight() + " cm"); }
                            else if(i == 3){ bw.write(String.valueOf(((ChildActor)artist).getAge()));
                            }
                            else if(i == 4){ bw.write(((StuntPerformer)artist).getHeight() + " cm"); }
                            bw.write("\n");
                        }

                    }


                }


            }

            bw.write("\n-----------------------------------------------------------------------------------------------------\n");

            bw.close();


        }


    }
}
