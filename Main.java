import java.util.*;
import java.io.*;
import java.text.*;

public class Main {
    static ArrayList<Animal> animalList = new ArrayList<>();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
    // Show menu system
        while (running) {
            System.out.println("\n--- Vet Management System ---");
            System.out.println("1. Add Animal");
            System.out.println("2. Show Animals");
            System.out.println("3. Show Upcoming Appointments");
            System.out.println("4. Save to File");
            System.out.println("5. Load from File");
            System.out.println("6. Exit");
            System.out.print("Select: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // clear buffer

                switch (choice) {
                    case 1 -> addAnimal(scanner);
                    case 2 -> showAnimals();
                    case 3 -> showAppointments();
                    case 4 -> saveToFile();
                    case 5 -> loadFromFile();
                    case 6 -> {
                        System.out.println("Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid option.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // clear buffer
            }
        }

        scanner.close();
    }

    public static void addAnimal(Scanner scanner) {
        Animal newAnimal = null;

        while (newAnimal == null) {
            System.out.println("\nSelect species:");
            System.out.println("1. Mammal");
            System.out.println("2. Fish");
            System.out.println("3. Reptile");
            System.out.println("4. Amphibian");

            int speciesChoice = 0;

            // Keep asking until a valid species choice is entered
            while (speciesChoice < 1 || speciesChoice > 4) {
                System.out.print("Enter species choice (1-4): ");
                try {
                    speciesChoice = scanner.nextInt();
                    scanner.nextLine(); // clear the buffer

                    if (speciesChoice < 1 || speciesChoice > 4) {
                        System.out.println("Invalid choice! Please select a valid species (1-4).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number (1-4).");
                    scanner.nextLine(); // clear the buffer
                }
            }

            //scan the detail
            String type = getValidStringInput(scanner, "Enter type (e.g. Dog, Cat): ");
            String name = getValidStringInput(scanner, "Enter name: ");
            int age = getValidIntInput(scanner, "Enter age: ");
            String condition = getValidStringInput(scanner, "Enter condition: ");
            String owner = getValidStringInput(scanner, "Enter owner's name: ");

            boolean needsAppointment = getValidBoolInput(scanner, "Does this animal need an appointment? (true/false): ");
            String appointmentDate = "";
            if (needsAppointment) {
                appointmentDate = getValidAppointmentDate(scanner); // Use the new method for date validation
            }

            // Create animal object
            switch (speciesChoice) {
                case 1 -> newAnimal = new Mammal(name, age, type, condition, owner, needsAppointment, appointmentDate);
                case 2 -> newAnimal = new Fish(name, age, type, condition, owner, needsAppointment, appointmentDate);
                case 3 -> newAnimal = new Reptile(name, age, type, condition, owner, needsAppointment, appointmentDate);
                case 4 -> newAnimal = new Amphibian(name, age, type, condition, owner, needsAppointment, appointmentDate);
            }

            if (newAnimal != null) {
                animalList.add(newAnimal);
                System.out.println("Animal added!");
            } else {
                System.out.println("Something went wrong. Please try again.");
            }
        }
    }

    // get a valid appointment date
    private static String getValidAppointmentDate(Scanner scanner) {
        String appointmentDate = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter the appointment date (YYYY-MM-DD): ");
            appointmentDate = scanner.nextLine().trim();

            if (appointmentDate.isEmpty()) {
                System.out.println("Appointment date cannot be empty. Please try again.");
            } else {
                try {
                    // Check the date format
                    dateFormat.setLenient(false); 
                    dateFormat.parse(appointmentDate); 

                    //validate the date using Calendar
                    String[] dateParts = appointmentDate.split("-");
                    int year = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    int day = Integer.parseInt(dateParts[2]);

                    // Using Calendar to validate day/month/year
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month - 1, day); // inout date
                    if (calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) == (month - 1) && calendar.get(Calendar.DAY_OF_MONTH) == day) {
                        valid = true; // The date is valid
                    } else {
                        System.out.println("Invalid date! The day is out of range for the given month. Please try again.");    
                    }

                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid date.");
                }
            }
        }
        return appointmentDate;
    }

    // ensure a valid string input
    private static String getValidStringInput(Scanner scanner, String prompt) {
        String input = "";
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            } else {
                valid = true;
            }
        }
        return input;
    }

    //ensure a valid integer input
    private static int getValidIntInput(Scanner scanner, String prompt) {
        int input = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                scanner.nextLine(); // clear the buffer
                if (input < 0) {
                    System.out.println("Age must be a positive number. Please try again.");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // clear the buffer
            }
        }
        return input;
    }

    // ensure a valid boolean input
    private static boolean getValidBoolInput(Scanner scanner, String prompt) {
        boolean input = false;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                input = scanner.nextBoolean();
                scanner.nextLine(); // clear the buffer
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter 'true' or 'false'.");
                scanner.nextLine(); // clear the buffer
            }
        }
        return input;
    }

    public static void showAnimals() {
        if (animalList.isEmpty()) {
            System.out.println("No animals added yet.");
        } else {
            for (Animal a : animalList) {
                System.out.println("\n------------------");
                a.displayInfo(); // polymorphism in action
            }
        }
    }

    public static void showAppointments() {
        boolean hasAppointments = false;
        for (Animal a : animalList) {
            if (a.hasAppointment()) {
                hasAppointments = true;
                System.out.println("\n------------------");
                System.out.println(a.getName() + " has an appointment on " + a.getAppointmentDate());
            }
        }
        if (!hasAppointments) {
            System.out.println("No animals with upcoming appointments.");
        }
    }

    public static void saveToFile() {
        try (PrintWriter writer = new PrintWriter("animals.txt")) {
            for (Animal a : animalList) {
                writer.println(a.toFileString());
            }
            System.out.println("Saved to animals.txt.");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public static void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("animals.txt"))) {
            animalList.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) {  // Adjusted for appointment data
                    String species = data[0];
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String type = data[3];
                    String condition = data[4];
                    String owner = data[5];
                    boolean needsAppointment = Boolean.parseBoolean(data[6]);
                    String appointmentDate = data[7];

                    Animal a = switch (species) {
                        case "Mammal" -> new Mammal(name, age, type, condition, owner, needsAppointment, appointmentDate);
                        case "Fish" -> new Fish(name, age, type, condition, owner, needsAppointment, appointmentDate);
                        case "Reptile" -> new Reptile(name, age, type, condition, owner, needsAppointment, appointmentDate);
                        case "Amphibian" -> new Amphibian(name, age, type, condition, owner, needsAppointment, appointmentDate);
                        default -> null;
                    };

                    if (a != null) {
                        animalList.add(a);
                    }
                }
            }
            System.out.println("Loaded animals from file.");
        } catch (IOException e) {
            System.out.println("Load error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("File format error.");
        }
    }
}
