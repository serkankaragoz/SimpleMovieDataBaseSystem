public class Actor extends Performer{
    private int height;

    public Actor(){}

    public Actor(String[] peopleStringLine){
        this.setHeight(Integer.parseInt(peopleStringLine[5]));
    }

    //getters and setters of Actor class
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
