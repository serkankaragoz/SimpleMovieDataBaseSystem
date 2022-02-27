import java.util.ArrayList;

public class StuntPerformer extends Performer{
    private int height;
    private ArrayList<Integer> actorIDs;


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Integer> getActorIDs() {
        return actorIDs;
    }

    public void setActorIDs(ArrayList<Integer> actorIDs) {
        this.actorIDs = actorIDs;
    }

    public void addActorIDs(int id){
        this.actorIDs.add(id);
    }
}
