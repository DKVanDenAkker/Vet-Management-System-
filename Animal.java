public abstract class Animal {
    protected String name;
    protected int age;
    protected String type;      // e.g., Dog, Cat, Goldfish
    protected String condition;
    protected String owner;
    protected boolean needsAppointment;
    protected String appointmentDate;

    // These are the animal’s attributes: name, age, species type (e.g., Dog, Cat), condition, owner, and appointment status.
    public Animal(String name, int age, String type, String condition, String owner, boolean needsAppointment, String appointmentDate) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.condition = condition;
        this.owner = owner;
        this.needsAppointment = needsAppointment;
        this.appointmentDate = appointmentDate;
    }

    // the second one sets a default value when no appointment is scheduled.
    public Animal(String name, int age, String type, String condition, String owner) {
        this(name, age, type, condition, owner, false, "");
    }

    // Abstract method that each subclass (e.g., Amphibian) must implement to display the animal’s info.
    public abstract void displayInfo();

    // This method converts the animal’s details to a CSV-like string format for saving/exporting to a file.
    public String toFileString() {
        return getSpecies() + "," + name + "," + age + "," + type + "," + condition + "," + owner + "," + needsAppointment + "," + appointmentDate;
    }

    // Helper
    public abstract String getSpecies();

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getType() { return type; }
    public String getCondition() { return condition; }
    public String getOwner() { return owner; }

    // New appointment-related methods
    public boolean hasAppointment() { return needsAppointment; }
    public String getAppointmentDate() { return appointmentDate; }
}
