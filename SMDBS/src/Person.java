import java.util.ArrayList;

// Person class is the super class of every person object
public class Person {
    private int ID;
    private String name;
    private String surname;
    private String country;




    // getters ans setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    // checks if this person inside of given Person ArrayList
    public boolean isPersonExist(ArrayList<Person> persons){
        for(Person person: persons){
            if(person.getID() == this.getID()) {
                return true;
            }
        }
        return false;
    }

}
