//ผู้ปกครอง
// Base class for all animals in the vet management system

public class Animal {
    protected String name;
    protected int age;
    protected String species;   // Mammal, Fish, Reptile
    protected String type;      // Dog, Cat, Goldfish
    protected String condition; // "Fever", "Needs surgery"
    protected String owner;

    // Constructor
    public Animal(String name, int age, String species, String type, String condition, String owner) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.type = type;
        this.condition = condition;
        this.owner = owner;
    }

    // Display animal details
    public void displayInfo() {
        System.out.println("Name: " + name +
                           "\nAge: " + age +
                           "\nSpecies: " + species +
                           "\nType: " + type +
                           "\nCondition: " + condition +
                           "\nOwner: " + owner);
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getSpecies() { return species; }
    public String getType() { return type; }
    public String getCondition() { return condition; }
    public String getOwner() { return owner; }

    // Convert to a string format for file saving
    public String toFileString() {
        return name + "," + age + "," + species + "," + type + "," + condition + "," + owner;
    }
}
