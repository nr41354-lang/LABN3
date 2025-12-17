package hotel;

public class Person {
    public String name;
    private boolean pet;
    public String family;
    public Person(String name){
        this.name=name;
        this.pet=false;
        this.family=null;
    }
    public Person(String name, boolean pet){
        this.name=name;
        this.pet=pet;
        this.family=null;
    }
}
