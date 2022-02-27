import java.util.*;

public class Film{
    private int filmID;
    private String filmTitle;
    private String language;
    private int runTime;
    private String country;
    private ArrayList<Integer> directorIDs;
    private ArrayList<Integer> performerIDs;
    private HashMap<Integer,Integer> ratings = new HashMap<>();
    private float sumOfRatingPoints;
    private Float ratingPoint = 0f;





    public String getDate(){
        return "";
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<Integer> getDirectorIDs() {
        return directorIDs;
    }

    public void setDirectorIDs(ArrayList<Integer> directors) {
        this.directorIDs = directors;
    }

    public ArrayList<Integer> getPerformerIDs() {
        return performerIDs;
    }

    public void setPerformerIDs(ArrayList<Integer> performers) {
        this.performerIDs = performers;
    }



    // checks if this film object inside of the given array
    public boolean isFilmExist(ArrayList<Film> films){

        for(Film film: films){
            if(film.getFilmID() == this.getFilmID()) {
                return true;
            }
        }

        return false;
    }


    public HashMap<Integer, Integer> getRatings() {
        return ratings;
    }



    public void addRating(Integer userID, Integer ratingPoint) {
        this.ratings.put(userID, ratingPoint);
        this.sumOfRatingPoints += ratingPoint;
    }

    public void editRating(Integer userID, Integer ratingPoint){
        if(this.ratings.containsKey(userID)){
            removeRating(userID);
            this.ratings.put(userID, ratingPoint);
            this.sumOfRatingPoints += ratingPoint;
        }
    }

    public void removeRating(Integer userID){
        if(this.ratings.containsKey(userID)){

            this.sumOfRatingPoints -= ratings.get(userID);
            this.ratings.remove(userID);

        }
    }

    public float getRatingPoint() {
        if(this.getRatings().size() != 0){
            ratingPoint = sumOfRatingPoints / this.ratings.size();
            return ratingPoint;
        }
        else{
            return 0;
        }
    }

    // since the rating point has printed with dot instead of comma, we needed to make another method to
    // convert the float value t oa string which has comma instead of dot
    public String printRating(){
        String point = String.format("%.2g", this.getRatingPoint());

        if(point.endsWith("0")){
            return String.format("%.1g", this.getRatingPoint());
        }
        else{
            return point;
        }

    }


}
