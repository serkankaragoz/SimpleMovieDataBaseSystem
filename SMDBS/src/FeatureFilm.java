import java.util.ArrayList;

public class FeatureFilm extends Film{
    private String releaseDate;
    private long budget;
    private ArrayList<Integer> writerIDs;
    private ArrayList<String> genres;

    // returns the name of class
    @Override
    public String toString() { return "FeatureFilm"; }


    // returns the release date as a string which has parentheses
    @Override
    public String getDate() {
        return "(" + this.getReleaseDate().substring(6) + ")";
    }



    //getters ans setters
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
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

    public void setGenres(ArrayList<String> genre) {
        this.genres = genre;
    }


}
