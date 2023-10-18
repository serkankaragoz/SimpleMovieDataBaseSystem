public class Director extends Artist{
    private String agent;

    public Director(){}

    public Director(String[] peopleStringLine){
        super(peopleStringLine);

        this.setAgent(peopleStringLine[5]);
    }

    // getter and setter
    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
