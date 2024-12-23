public class Fibonacci {

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        
        int a = 0, b = 1;
        int fib = 1;
        
        for (int i = 2; i <= n; i++) {
            fib = a + b;  
            a = b;        
            b = fib;      
        }
        
        return fib;
    }

    public static void main(String[] args) {
        int n = 12;
        System.out.println("Fibonacci of " + n + " is: " + fibonacci(n)); 
    }
}
