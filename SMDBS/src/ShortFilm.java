import java.util.ArrayList;

public class ShortFilm extends Film{

    private String releaseDate;
    private ArrayList<Integer> writerIDs;
    private ArrayList<String> genres;

    // returns the name of class
    @Override
    public String toString() { return "ShortFilm"; }

    // returns the release date as a string which has parentheses
    @Override
    public String getDate() {
        return "(" + this.getReleaseDate().substring(6) + ")";
    }



    // getter and setters
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<Integer> getWriterIDs() {
        return writerIDs;
    }

    public void setWriterIDs(ArrayList<Integer> writers) {
        this.writerIDs = writers;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> filmGenre) {
        this.genres = filmGenre;
    }

}
