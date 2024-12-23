public class IceCream implements ExpiryDate{
    private final int year = 2024;                                  // Since ice creams usually take 2 - 4 months to expire
    private int month = 8 + (int)(Math.random() * (12 - 8 + 1));    // We start on August
    private int day;
    private int x;
    private int y; 
    private String name;

    public void printPosition(){
        System.out.println("Ice cream " + name + " is at (" + x + "," + y + ")." );
    }

    public int getDay(int month){
        int day, max;
        if (month == 9) {                                           // Only September has 30 days
            max = 30; 
        } else {
            max = 31;
        }

        day = 1 + (int)(Math.random() * max);  
        return day;
    }
        

    public void setDate(int month, int day){
        this.month = month;
        this.day = day;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
}