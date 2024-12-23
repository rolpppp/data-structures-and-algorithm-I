import java.util.Random;
import java.util.Scanner;
        
public class Main {

        private static int addInt(Scanner scn){
            int n;                                                                 

            while (true){
                System.out.print("\nEnter number of inputs: ");
                n = scn.nextInt();

                if (n < 0){
                    System.out.println("\nEnter nonnegative values");
                }else if (n == 0){
                    System.out.println("\nInput must not be empty");
                }else if (n >20){
                    System.out.println("\nInput must not be greater than 20");     // I set the maximum input to 20
                } else {
                    return n;
                }
            }
        }

    
    public static void main(String[] args) {
        
        DoublyLinkedList list = new DoublyLinkedList();
        Random random = new Random();
        Scanner scn = new Scanner(System.in);

        int n = addInt(scn);                                                      // Feel free to change desired number of inputs

        for (int i = 0; i < n; i++) {
            list.append(random.nextInt(100));                             // Adding nodes. Creating random elements assures us that we will most probably get unique elements.
        }                                                                       // Upperbound is set to 100.

        if (n == 1){
            System.out.println("\nList has only one element '" + list.head.value + "' . Sorting cannot happen\n");
            
        } else{
            list.sort();

            System.out.println("\n==========================================================\nSorted list:");                     
            list.print();
            System.out.println("==========================================================");

            list.unsort();                                                         // Refer to DoublyLinkedList class for the unsort algo

            System.out.println("==========================================================\nUnsorted list:");  
            list.print();
            System.out.println("==========================================================\n");
        }
    }
}