import java.util.HashMap;

public class User extends Person{

    private HashMap<Integer, Integer> ratedFilms = new HashMap<>();



    public User(){}

    public User(String[] peopleStringLine){
        super(peopleStringLine);
    }

    public HashMap<Integer, Integer> getRatedFilms() {
        return ratedFilms;
    }

    public void setRatedFilms(HashMap<Integer, Integer> ratedFilms) {
        this.ratedFilms = ratedFilms;
    }

    // rates the given film
    public void rateFilm(Integer filmID, Integer ratingPoint){
        if(!this.ratedFilms.containsKey(filmID)){
            ratedFilms.put(filmID, ratingPoint);
        }
    }


    // edits a rating. If the given film hasn't rated from this user yet, does nothing
    public void editRating(Integer filmID, Integer ratingPoint){
        if(this.ratedFilms.containsKey(filmID)){
            ratedFilms.remove(filmID);
            ratedFilms.put(filmID, ratingPoint);
        }
    }

    // removes a rating. If the given film hasn't rated from this user yet, does nothing
    public void removeRating(Integer filmID){
        if(this.ratedFilms.containsKey(filmID)){
            ratedFilms.remove(filmID);
        }
    }

}
