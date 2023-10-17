import java.util.ArrayList;

public class TVSeries extends Film{
    private String startDate;
    private String endDate;
    private int numberOfSeasons;
    private int numberOfEpisodes;
    private ArrayList<String> genres;
    private ArrayList<Integer> writerIDs;


    public TVSeries(){}

    public TVSeries(String[] commandLine){
        super(commandLine);

        this.setGenres(Commands.toStringArrayList(commandLine[8].split(",")));
        this.setWriterIDs(Commands.toIntegerArrayList(commandLine[9].split(",")));
        this.setStartDate(commandLine[10]);
        this.setEndDate(commandLine[11]);
        this.setNumberOfSeasons(Integer.parseInt(commandLine[12]));
        this.setNumberOfEpisodes(Integer.parseInt(commandLine[13]));
    }

    @Override
    public String toString() { return "TVSeries"; }

    @Override
    public String getDate() {
        return "(" + this.getStartDate().substring(6) + "-" + this.getEndDate().substring(6) + ")";
    }


    //getters and setters
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genre) {
        this.genres = genre;
    }

    public ArrayList<Integer> getWriterIDs() {
        return writerIDs;
    }

    public void setWriterIDs(ArrayList<Integer> writers) {
        this.writerIDs = writers;
    }
}
