public class Amphibian extends Animal {
//The Amphibian class inherits from the Animal class. This means it has all the properties and behaviors of Animal, but can also add or override specific behaviors.
    
    public Amphibian(String name, int age, String type, String condition, String owner, boolean needsAppointment, String appointmentDate) {
        // super(name, age, type, condition, owner, needsAppointment, appointmentDate);//This constructor takes all properties and passes them directly to the Animal constructor using super.
    }

    public Amphibian(String name, int age, String type, String condition, String owner) {
        super(name, age, type, condition, owner); // default: no appointment
    }

    @Override
    public void displayInfo() {
        System.out.println("Species: Amphibian"); //method is overridden to display details specific to an amphibian, including its name, age, type, condition, etc.
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Type: " + type);
        System.out.println("Condition: " + condition);
        System.out.println("Owner: " + owner);
        if (needsAppointment) {
            System.out.println("Next Appointment: " + appointmentDate);
        } else {
            System.out.println("No upcoming appointment.");
        }
    }

    @Override
    public String getSpecies() {
        return "Amphibian";
    }
}
