public class Documentary extends Film{
    private String releaseDate;

    public Documentary(){}

    public Documentary(String[] commandLine){
        super(commandLine);

        this.setReleaseDate(commandLine[8]);
    }


    // returns the name of class
    @Override
    public String toString() { return "Documentary"; }

    // returns the release date as a string which has parentheses
    @Override
    public String getDate() {
        return "(" + this.getReleaseDate().substring(6) + ")";
    }

    // getter ans setter
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
