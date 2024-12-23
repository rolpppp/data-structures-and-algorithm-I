import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static void mainMenu(){
        System.out.println("==================================================");
        System.out.println("Welcome to String Generator!\n");
            System.out.println("To continue, please select an option below:");
    }

    private static int selectOption(Scanner scn){
        System.out.println("\t1.)Generate String via Randomizer\n\t2.)Exit");
        System.out.print("Enter Choice: ");
        return scn.nextInt();
    }

    private static void generateString(StringBuilder sb, Random random, String characters, int n){
        for (int i = 0; i < n; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        String X = sb.toString();
        System.out.println("==================================================");
        System.out.println("Generated string X: " + X);

        BST bst = new BST();                                                                            // Creates a BST and inserts the characters
        for (char c : X.toCharArray()) {        
            bst.insert(c);
        }

        System.out.println();
        System.out.print("In-order traversal of BST: ");                                                                           
        bst.inorder();                                                                                 // Performs in-order traversal   
        System.out.println();
        System.out.println("==================================================");
    }

    private static void exit(){
        System.out.println("==================================================");
        System.out.println("        THANK YOU FOR USING THE PROGRAM!");
        System.out.println("==================================================");
    }
    public static void main(String[] args) {
        // 1. Create a random string X
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ+-*/0123456789";
        Random random = new Random();
        Scanner scn = new Scanner(System.in);
        int choice;
        int n = random.nextInt(10) + 5;                                                     // Length will be between 5 and 14
        StringBuilder sb = new StringBuilder(n);

        mainMenu();
        try{
            choice = selectOption(scn);
            switch(choice){
                case 1:
                    generateString(sb, random, characters, n);
                    break;
                case 2:
                    scn.close();
                    exit();
                    System.exit(0);
                default:
                    System.out.println("Press enter valid inputs.");
                    break;
            }
        }catch(InputMismatchException e){
            System.out.println("==================================================");
            System.out.println("Enter valid input.");
        }finally{
            scn.nextLine();
        }
    }
}
