import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class Commands {

    // takes a String array and converts it to Integer ArrayList
    public static ArrayList<Integer> toIntegerArrayList(String[] strings){
        ArrayList<Integer> integers = new ArrayList<>();

        for(String string: strings){
            integers.add(Integer.valueOf(string));
        }
        return integers;

    }


    // takes an Integer array and converts it to String ArrayList
    public static ArrayList<String> toStringArrayList(String[] strings){
        return new ArrayList<>(Arrays.asList(strings));
    }


    public String[][] convertFileToStringArray(String filePath) throws IOException {
        // this functions returns a 2d string arraylist
        // with using the datas obtained from the path file taken from the user

        return new BufferedReader(new FileReader(filePath))
                .lines()
                .map(stringLine -> stringLine.split("\t"))
                .toArray(String[][]::new);

    }


    // takes the 2d String ArrayList and converts it to Person array
    public ArrayList<Person> StringToPersonArray(String[][] peopleStringArray){

        ArrayList<Person> persons = new ArrayList<>();

        Arrays.stream(peopleStringArray)
                .map(peopleStringLine -> { persons.add(toPerson(peopleStringLine)); return true; });

//        for(String[] peopleStringLine: peopleStringArray){
//            persons.add(toPerson(peopleStringLine));
//        }

        return persons;
    }


    public Person toPerson(String[] peopleStringLine){

        String userType = peopleStringLine[0].substring(0, peopleStringLine[0].length()-1);

        switch (userType){
            case "Director":{ return new Person(peopleStringLine);}
            case "Writer":{ return new Writer(peopleStringLine);}
            case "Actor":{ return new Actor(peopleStringLine);}
            case "ChildActor":{ return new ChildActor(peopleStringLine);}
            case "StuntPerformer":{ return new StuntPerformer(peopleStringLine);}
            case "User":{ return new User(peopleStringLine);}
            default: {return null;}
        }
    }

    // takes a String ArrayList and converts it to Film object
    public Film toFilm(String[] filmStringLine){

        String filmType = filmStringLine[0].substring(0, filmStringLine[0].length()-1);

        if(filmType.equals("ShortFilm") && Integer.parseInt(filmStringLine[5]) > 40){
            System.out.println("Error! A short film can't be longer than 40 minutes");
            return null;
        }

        /*
        try {
            Class c = Class.forName(filmType);
            c.cast()
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        */

        switch (filmType){
            case "FeatureFilm":{ return new FeatureFilm(filmStringLine); }
            case "ShortFilm":{ return new ShortFilm(filmStringLine); }
            case "Documentary":{ return new Documentary(filmStringLine); }
            case "TVSeries":{ return new TVSeries(filmStringLine); }
        }

        return new Film(filmStringLine);
    }

    // takes a 2d String ArrayList and converts it to Film ArrayList
    public ArrayList<Film> StringToFilmArray(String[][] filmStringArray) {

        ArrayList<Film> films = new ArrayList<>();

        String filmType;

        for (String[] filmStringLine : filmStringArray) {
            films.add(toFilm(filmStringLine));
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

            featureFilmDatas.append(
                    "Film title: " + film.getFilmTitle() + " " + film.getDate() + "\n"
                    + film.getRunTime() + " min\n"
                    + "Language: " + film.getLanguage() + "\n"
            );
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

