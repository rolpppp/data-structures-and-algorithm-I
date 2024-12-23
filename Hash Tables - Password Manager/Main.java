import java.util.Scanner;

public class Main {
    private static void welcome(){
        System.out.println("============================================");
        System.out.println("\tWELCOME TO PASSWORD MANAGER!");
        System.out.println("============================================");
    }

    private static void menu(){
        System.out.println("============================================");
        System.out.println("\nChoose an operation:");
        System.out.println("1. Insert password");
        System.out.println("2. Delete password");
        System.out.println("3. Search for password");
        System.out.println("4. Display all passwords");
        System.out.println("5. Resize password manager");
        System.out.println("6. Exit");
        System.out.println("============================================");
        System.out.print("Enter choice: ");
    }

    private static boolean isEmpty(int empCtr){
        return empCtr == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int empCtr = 0;                                                                 // Counter used to know if hash table is empty or not.
        welcome();
        System.out.print("Enter the size of the password manager: ");
        int size = scanner.nextInt();
        PasswordManager manager = new PasswordManagerBasic(size);
        boolean exit = false;
        
        while (!exit) {
            menu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            switch (choice) {
                case 1: // Insert password
                    System.out.print("Enter password: ");
                    String passwordValue = scanner.nextLine();
                    System.out.print("Enter key (integer): ");
                    int key = scanner.nextInt();
                    manager.insertPassword(new Password(passwordValue, key));
                    System.out.println("============================================");
                    System.out.println("Password inserted.");
                    empCtr++;
                    break;
                    
                case 2: // Delete password
                    if (!isEmpty(empCtr)){
                        System.out.print("Enter key to delete: ");
                        int deleteKey = scanner.nextInt();
                        System.out.println("============================================");
                        Password deletedPassword = manager.deletePassword(deleteKey);
                        if (deletedPassword != null) {
                            System.out.println("Deleted password: " + deletedPassword.getValue());
                            empCtr--;
                        } else {
                            System.out.println("============================================");
                            System.out.println("Password not found.");
                        }
                    }else{System.out.println("No passwords available yet.");}
                    
                    break;
                    
                case 3: // Search for password
                    if (!isEmpty(empCtr)){
                        System.out.print("Enter key to search: ");
                        int searchKey = scanner.nextInt();
                        System.out.println("============================================");
                        Password foundPassword = manager.searchPassword(searchKey);
                        if (foundPassword != null) {
                            System.out.println("Found password: " + foundPassword.getValue());
                        } else {
                            System.out.println("============================================");
                            System.out.println("Password not found.");
                        }
                    }else{System.out.println("No passwords available yet.");}
                    
                    break;
                    
                case 4: // Display all passwords
                    System.out.println("============================================");
                    if (!isEmpty(empCtr)){ 
                        manager.displayPasswordManager();
                    }else{System.out.println("No passwords available yet.");}
                    break;
                    
                case 5: // Resize password manager
                    System.out.print("Enter new size for the password manager: ");
                    int newSize = scanner.nextInt();
                    manager.setPasswordManagerSize(newSize);
                    System.out.println("Password manager resized to " + newSize + ".");
                    break;
                    
                case 6: // Exit
                    exit = true;
                    System.out.println("============================================");
                    System.out.println("Exiting...");
                    System.out.println("============================================");
                    break;
                    
                default:
                    System.out.println("============================================");
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
}
