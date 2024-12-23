import java.io.IOException;
import java.util.Scanner;

public class ConfectioneryShop {
    private static IceCream[] iceCream ;
    
    private static void menu(){
        System.out.print("Choose Action:\n1.) Open Freezer\n2.) Exit\nEnter Action: ");
    }

    private static void iceCream(IceCream iceCream[], int maxInt){
        for (int i = 0; i < maxInt; i++){
            iceCream[i] = new IceCream();
            iceCream[i].setDate(iceCream[i].getMonth(), iceCream[i].getDay(iceCream[i].getMonth()));
        }
    }
    private static int storeIceCream(Scanner scn, Freezer freezer){
        int maxInt;

        do{
            System.out.print("\nEnter how many ice cream tubs to store: ");
            maxInt = scn.nextInt();
        } while(maxInt < 0 || maxInt > 28);
        
        iceCream = new IceCream[maxInt];
        iceCream(iceCream, maxInt);
        freezer.store(iceCream, maxInt);
        System.out.println("Ice cream tubs stored!");
        System.out.println("============================================\n");
        return maxInt;
    }

    private static void printIceCreamPos(IceCream[] iceCream, int maxInt, boolean newPos){
        if (newPos == false)
            System.out.println("--------------------- Ice Cream Tubs (Unsorted)---------------------");
        else    
            System.out.println("--------------------- Ice Cream Tubs (Sorted) ---------------------");

        for (int i = 0; i < maxInt; i++){
            System.out.printf( "%-3s" +  "| Expiry Date: " + "%d" +   "/" + "%d" +  "/" + "%d" + "\n", iceCream[i].getName(), iceCream[i].getMonth(), iceCream[i].getDay(), iceCream[i].getYear());   
        }
        System.out.println("============================================\n");
    }

    private static void rearrange (Scanner scn, Freezer freezer, int maxInt, boolean newPos){
        System.out.println("Press 'Enter' to rearrange ice cream tubs");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        freezer.rearrange(iceCream, maxInt);
                        newPos = true;
                        printIceCreamPos(iceCream, maxInt, newPos);
                        System.out.print("Show Display Freezer View?\n1.) Yes\n2.) No\nEnter choice: ");
                        int ans2 = scn.nextInt();
                        if (ans2 == 1){
                            displayFreezerView(iceCream, maxInt);
                            System.out.println("\n=================== YOU HAVE SUCCESSFULLY SORTED THE ICE CREAM TUBS! ===================\n");
                        }
    }

    private static void displayFreezerView(IceCream[] iceCream, int maxInt) {
        int rows = 4;
        int columns = 7;
        
        System.out.println("--------------------- Freezer Display (7 x 4) ---------------------");
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int index = i * columns + j;

                if (index < maxInt) {
                    System.out.printf("%-10s", iceCream[index].getName()); 
                } else {
                    System.out.printf("%-10s", "Empty"); 
                }
            }
            System.out.println();
        }
    
        System.out.println("-------------------------------------------------------------------");
    }
    
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        Freezer freezer = new Freezer();
        int action, maxInt;
        boolean newPos = false;
        
        System.out.println("\n----------MY CONFECTIONERY SHOP---------\n");
        System.out.println("The freezer used is a display freezer with a grid of 7 x 4.\n");

        while(true){
            menu();
            action = scn.nextInt();
            switch(action){
                case 1: 
                    freezer.setOpen(true);
                    maxInt = storeIceCream(scn, freezer);
                    printIceCreamPos(iceCream, maxInt, newPos);
                    System.out.print("Show Display Freezer View?\n1.) Yes\n2.) No\nEnter choice: ");
                        int ans = scn.nextInt();
                        if (ans == 1){
                            displayFreezerView(iceCream, maxInt);
                        }
                    rearrange(scn, freezer, maxInt, newPos);
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }
}
