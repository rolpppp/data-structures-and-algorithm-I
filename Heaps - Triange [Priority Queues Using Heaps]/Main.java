import java.util.Scanner;


public class Main {
    private static void selectAction(){
        System.out.println("================================");
        System.out.println("Please select an action:");
        System.out.println("================================");
        System.out.println("1. Add a patient");
        System.out.println("2. Check next in line patient");
        System.out.println("3. Call the next in line patient");
        System.out.println("4. Change patient's triage score");
        System.out.println("5. Display triage queue");
        System.out.println("6. Exit");
    }
    private static void menu(){
        System.out.println("================================");
        System.out.println("Welcome to Patient Manager!");
        System.out.println("================================");
    }

     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TriagePriorityQueue tpq = new TriagePriorityQueue();
        menu();
        while (true) {
            selectAction();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter patient's first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter patient's last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter patient's triage score: ");
                    int score = scanner.nextInt();
                    tpq.insert(new TriageScoreData(firstName, lastName, score));
                    break;
                case 2:
                    TriageScoreData nextPatient = tpq.checkNextInLine();
                    if (nextPatient != null) {
                        System.out.println("Next patient: " + nextPatient.getPatientFirstName() + " " +
                                           nextPatient.getPatientLastName());
                    } else {
                        System.out.println("No patients in the queue.");
                    }
                    break;
                case 3:
                    TriageScoreData calledPatient = tpq.callNextInLine();
                    if (calledPatient != null) {
                        System.out.println("Called patient: " + calledPatient.getPatientFirstName() + 
                                           " " + calledPatient.getPatientLastName());
                    } else {
                        System.out.println("No patients in the queue.");
                    }
                    break;
                case 4:
                    System.out.print("Enter patient's last name: ");
                    String lastNameToChange = scanner.nextLine();
                    System.out.print("Enter new triage score: ");
                    int newScore = scanner.nextInt();
                    boolean changed = tpq.changeTriageScore(lastNameToChange, newScore);
                    if (changed) {
                        System.out.println("Triage score updated.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 5:
                    tpq.displayTriage();
                    break;
                case 6:
                    System.out.println("================================");
                    System.out.println("Exiting program.");
                    System.out.println("================================");
                    System.out.println("Thank you for using the program!");
                    System.out.println("================================");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
