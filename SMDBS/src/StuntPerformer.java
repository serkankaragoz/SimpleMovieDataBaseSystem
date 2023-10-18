import java.util.ArrayList;

public class StuntPerformer extends Performer{
    private int height;
    private ArrayList<Integer> actorIDs;

    public StuntPerformer(){}

    public StuntPerformer(String[] peopleStringLine){
        super(peopleStringLine);

        this.setHeight(Integer.parseInt(peopleStringLine[5]));

        String[] IDs = peopleStringLine[6].split(",");
        this.setActorIDs(new ArrayList<>());

        for (String id : IDs) {
            this.addActorIDs(Integer.parseInt(id));
        }
    }


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
