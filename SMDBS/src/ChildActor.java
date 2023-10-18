public class ChildActor extends Performer{
    private int age;

    public ChildActor(){}

    public ChildActor(String[] peopleStringLine){
        this.setAge(Integer.parseInt(peopleStringLine[5]));
    }

    // getter and setter
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
