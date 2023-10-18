public class Writer extends Artist{
    private String writingStyle;

    public Writer(){}

    public Writer(String[] peopleStringLine){
        super(peopleStringLine);

        this.setWritingStyle(peopleStringLine[5]);
    }


    public String getWritingStyle() {
        return writingStyle;
    }

    public void setWritingStyle(String writingStyle) {
        this.writingStyle = writingStyle;
    }
}
