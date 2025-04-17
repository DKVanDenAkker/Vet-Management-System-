public class Mammal extends Animal {

    public Mammal(String name, int age, String type, String condition, String owner, boolean needsAppointment, String appointmentDate) {
        super(name, age, type, condition, owner, needsAppointment, appointmentDate);
    }

    public Mammal(String name, int age, String type, String condition, String owner) {
        super(name, age, type, condition, owner); // default: no appointment
    }

    @Override
    public void displayInfo() {
        System.out.println("Species: Mammal");
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
        return "Mammal";
    }
}
