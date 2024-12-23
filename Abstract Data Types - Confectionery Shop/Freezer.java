public class Freezer {
    private boolean open = false;
    private final int x = 7;                
    private int y;               
    

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void store(IceCream[] iceCream, int maxInt){
        
        if (maxInt <= 7){
            y = 1;                                      // Suppose that the ice cream tubs are displayed
        }else if (maxInt <= 14){                        // inside a 7 x 4 display freezer.
            y = 2;
        }else if (maxInt <= 21){
            y = 3;
        }else{
            y = 4;
        }

        for (int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){
                int index = j + i * x;
                if (index < maxInt) {  
                    iceCream[index].setPosition(i, j);
                    iceCream[index].setName((index + 1) + " ");
                }    
            }
        }
    }

    public void rearrange(IceCream[] iceCream, int maxInt) {
        IceCream temp;
        boolean swapped;
        
        for (int i = 0; i < maxInt - 1; i++) {
            swapped = false;  
            for (int j = 0; j < maxInt - i - 1; j++) {
                if (iceCream[j].getMonth() > iceCream[j + 1].getMonth()) {
                    temp = iceCream[j];
                    iceCream[j] = iceCream[j + 1];
                    iceCream[j + 1] = temp;
                    swapped = true;  
                } 
                
                else if (iceCream[j].getMonth() == iceCream[j + 1].getMonth() &&
                         iceCream[j].getDay() > iceCream[j + 1].getDay()) {
                    temp = iceCream[j];
                    iceCream[j] = iceCream[j + 1];
                    iceCream[j + 1] = temp;
                    swapped = true; 
                }
            }
            
            if (!swapped) break;
        }
    }
}
