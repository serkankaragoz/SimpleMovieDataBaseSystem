import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Commands {

    // takes a String array and converts it to Integer ArrayList
    public ArrayList<Integer> toIntegerArrayList(String[] strings){
        ArrayList<Integer> integers = new ArrayList<>();

        for(String string: strings){
            integers.add(Integer.valueOf(string));
        }
        return integers;

    }


    // takes a Integer array and converts it to String ArrayList
    public ArrayList<String> toStringArrayList(String[] strings){
        ArrayList<String> stringArray = new ArrayList<>();

        for(String string: strings){
            stringArray.add(string);
        }
        return stringArray;

    }


    public ArrayList<ArrayList<String>> convertFileToStringArray(String filePath) throws IOException {
        // this functions returns a 2d string arraylist
        // with using the datas obtained from the path file taken from the user

        ArrayList<ArrayList<String>> fileContents = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        String stringLine;

        for(int i = 0;(stringLine = bufferedReader.readLine()) != null;i++){

            ArrayList<String> arrayListLine = new ArrayList<>();


            String[] line = stringLine.split("\t");

            for(int j = 0;j < line.length;j++){
                arrayListLine.add(line[j]);
            }

            fileContents.add(arrayListLine);

        }
        return fileContents;
    }


    // takes the 2d String ArrayList and converts it to Person array
    public ArrayList<Person> StringToPersonArray(ArrayList<ArrayList<String>> peopleStringArray){

        ArrayList<Person> persons = new ArrayList<>();
        Person person;

        int index = 0;
        for(ArrayList<String> peopleStringLine: peopleStringArray){



            if(peopleStringLine.get(0).equals("Director:")){

                persons.add(new Director());
                ((Director) persons.get(index)).setAgent(peopleStringLine.get(5));
            }

            if(peopleStringLine.get(0).equals("Writer:")){

                persons.add(new Writer());
                ((Writer) persons.get(index)).setWritingStyle(peopleStringLine.get(5));
            }

            if(peopleStringLine.get(0).equals("Actor:")){

                persons.add(new Actor());
                ((Actor) persons.get(index)).setHeight(Integer.valueOf(peopleStringLine.get(5)));
            }

            if(peopleStringLine.get(0).equals("ChildActor:")){

                persons.add(new ChildActor());
                ((ChildActor) persons.get(index)).setAge(Integer.valueOf(peopleStringLine.get(5)));
            }

            if(peopleStringLine.get(0).equals("StuntPerformer:")){

                persons.add(new StuntPerformer());
                ((StuntPerformer) persons.get(index)).setHeight(Integer.valueOf(peopleStringLine.get(5)));

                String[] IDs = peopleStringLine.get(6).split(",");
                ((StuntPerformer) persons.get(index)).setActorIDs(new ArrayList<>());

                for(int i = 0;i < IDs.length;i++){
                    ((StuntPerformer) persons.get(index)).addActorIDs(Integer.valueOf(IDs[i]));
                }

            }
            if(peopleStringLine.get(0).equals("User:")){
                persons.add(new User());
            }

            persons.get(index).setID(Integer.valueOf(peopleStringLine.get(1)));
            persons.get(index).setName(peopleStringLine.get(2));
            persons.get(index).setSurname(peopleStringLine.get(3));
            persons.get(index).setCountry(peopleStringLine.get(4));



            index++;
        }

        return persons;
    }


    // takes a String ArrayList and converts it to Film object
    public Film toFilm(String filmType, ArrayList<String> filmStringLine){

        Film film = new Film();
        //int index = films.size();
        int index = 0;

        if(filmType.equals("FeatureFilm:")){

            film = new FeatureFilm();

            ((FeatureFilm) film).setGenres(toStringArrayList(filmStringLine.get(8).split(",")));
            ((FeatureFilm) film).setReleaseDate(filmStringLine.get(9));
            ((FeatureFilm) film).setWriterIDs(toIntegerArrayList(filmStringLine.get(10).split(",")));
            ((FeatureFilm) film).setBudget(Long.valueOf(filmStringLine.get(11)));

        }

        else if(filmType.equals("ShortFilm:")){

            film = new ShortFilm();

            if(Integer.valueOf(filmStringLine.get(5)) > 40){
                System.out.println("Error! A short film can't be longer than 40 minutes");
                return null;
            }

            ((ShortFilm) film).setGenres(toStringArrayList(filmStringLine.get(8).split(",")));
            ((ShortFilm) film).setReleaseDate(filmStringLine.get(9));
            ((ShortFilm) film).setWriterIDs(toIntegerArrayList(filmStringLine.get(10).split(",")));

        }

        else if(filmType.equals("Documentary:")){

            film =  new Documentary();

            ((Documentary) film).setReleaseDate(filmStringLine.get(8));

        }

        else if(filmType.equals("TVSeries:")){

            film = new TvSeries();

            ((TvSeries) film).setGenres(toStringArrayList(filmStringLine.get(8).split(",")));
            ((TvSeries) film).setWriterIDs(toIntegerArrayList(filmStringLine.get(9).split(",")));
            ((TvSeries) film).setStartDate(filmStringLine.get(10));
            ((TvSeries) film).setEndDate(filmStringLine.get(11));
            ((TvSeries) film).setNumberOfSeasons(Integer.valueOf(filmStringLine.get(12)));
            ((TvSeries) film).setNumberOfEpisodes(Integer.valueOf(filmStringLine.get(13)));

        }


        film.setFilmID(Integer.valueOf(filmStringLine.get(1)));
        film.setFilmTitle(filmStringLine.get(2));
        film.setLanguage(filmStringLine.get(3));
        film.setDirectorIDs(toIntegerArrayList(filmStringLine.get(4).split(",")));
        film.setRunTime(Integer.valueOf(filmStringLine.get(5)));
        film.setCountry(filmStringLine.get(6));
        film.setPerformerIDs(toIntegerArrayList(filmStringLine.get(7).split(",")));




        return film;
    }

    // takes a 2d String ArrayList and converts it to Film ArrayList
    public ArrayList<Film> StringToFilmArray(ArrayList<ArrayList<String>> filmStringArray) {


        ArrayList<Film> films = new ArrayList<>();
        Film film;
        int index = 0;

        for (ArrayList<String> filmStringLine : filmStringArray) {
               films.add(toFilm(filmStringLine.get(0),filmStringLine));
        }

        return films;
    }

    //Takes an Integer from user, searchs the person which belongs to this ID on given ArrayList, and returns the Person object
    public Person getPersonFromId(Integer ID, ArrayList<Person> persons){

        for(Person person: persons){
            if(person.getID() == ID){
                return person;
            }
        }

        return null;
    }

    //Takes an Integer from user, searchs the film which belongs to this ID on given ArrayList, and returns the Film object
    public Film getFilmFromId(Integer ID, ArrayList<Film> films){

        for(Film film: films){
            if(film.getFilmID() == ID){
                return film;
            }
        }

        return null;
    }


    // checks if the film exist with given ID inside an ArrayList
    public boolean isFilmExist(Integer ID, ArrayList<Film> films){

        for(Film film: films){
            if(film.getFilmID() == ID) {
                return true;
            }
        }

        return false;
    }

    // Takes a String consists of ID ans commas, converts it to Integer ArrayList, checks every ID's validation
    // and checks if the person with given ID's class is the same with the given example Person object
    public boolean isEveryoneExist(Person person, String IDs, ArrayList<Person> persons){

        ArrayList<Integer> personIDs = toIntegerArrayList(IDs.split(","));

        for(Integer id: personIDs){
            if(getPersonFromId(id, persons) == null || !(person.getClass().isInstance(getPersonFromId(id, persons)))){
                return false;
            }
        }

        return true;
    }


    public static Comparator<Film> rateDegreeComparator = new Comparator<Film>() {
        @Override
        public int compare(Film o1, Film o2) {
            return - Float.compare(o1.getRatingPoint(), o2.getRatingPoint());
        }
    };


    // Takes a Film ArrayList and returns another Film ArrayList which only contains the films that on specified class
    public ArrayList<Film> extractFilm(Film sampleFilm,ArrayList<Film> filmArray){
        ArrayList<Film> newFilms = new ArrayList<>();

        for(Film film: filmArray){
            if(film.getClass().equals(sampleFilm.getClass())){
                newFilms.add(film);
            }
        }
        return newFilms;
    }

    // takes an Integer ArrayList, Person ArrayList, creates a Person ArrayList with givenID, writes the person names and surnames to specified file
    public void printPersonNames(ArrayList<Integer> personIDs, ArrayList<Person> personArray, BufferedWriter bw) throws IOException {
        int count = 0;
        for(Integer ID: personIDs){
            Person person = getPersonFromId(ID, personArray);
            bw.write(person.getName() + " " + person.getSurname());
            count++;
            if(count < personIDs.size()){
                bw.write(", ");
            }
            else{
                bw.write("\n");
            }

        }
    }

    // Prints the String ArrayList with putting comma between every index to given file
    public void printArrayListWithComma(ArrayList<String> strings, BufferedWriter bw) throws IOException {
        int count = 0;
        for(String string: strings){
            bw.write(string);
            count++;
            if(count < strings.size()){
                bw.write(", ");
            }
        }
    }


    public String getFeatureFilmData(ArrayList<Film> films){
        int count = 0;
        StringBuilder featureFilmDatas = new StringBuilder();

        for(Film film: films){

            featureFilmDatas.append("Film title: " + film.getFilmTitle() + " " + film.getDate() + "\n");
            featureFilmDatas.append(film.getRunTime() + " min\n");
            featureFilmDatas.append("Language: " + film.getLanguage() + "\n");
            count++;
            if(count < films.size()){
                featureFilmDatas.append("\n");
            }
        }
        if(count == 0){
            featureFilmDatas.append("No result\n");
        }

        return featureFilmDatas.toString();
    }


}

