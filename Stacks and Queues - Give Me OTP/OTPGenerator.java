import java.util.*;
import java.io.IOException;

public class OTPGenerator {

private static void inputExpressions(Queue<String> postFixQueue, Scanner scn, int n) {
        String input;
        System.out.println("==================================================");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter an infix expression: ");
            input = scn.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println("Error: Empty input is not allowed. Please try again.");
                i--;
                continue;
            }
            
            try {
                String postFix = infixToPostfix(input);
                postFixQueue.add(postFix);
                System.out.println("==================================================");
                System.out.println(input + " added successfully.");
                System.out.println("==================================================");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
                i--;
            }
        }
        System.out.println("==================================================");
        System.out.println("   Expressions successfully added to the queue!");
        System.out.println("==================================================");

        pressEnterToContinue();
    }

private static void generateOTP(Queue<String> postFixQueue, Queue<String> otpQueue) {
        String postFix = postFixQueue.poll();
        int result = evaluatePostfix(postFix);
        String otp = formatOTP(result);

        System.out.println("Postfix: " + postFix);
        System.out.println("Your one-time passcode (OTP): " + otp);
        System.out.println("==================================================");

        pressEnterToContinue();
    }

private static void pressEnterToContinue() {
    System.out.print("Press 'Enter' to continue");
    try {
        System.in.read();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private static String infixToPostfix(String infix) {
    Stack<Character> stack = new Stack<>();
    List<String> postFix = new ArrayList<>();
    StringBuilder number = new StringBuilder();
    boolean lastWasOperator = true;

    for (int i = 0; i < infix.length(); i++) {
        char c = infix.charAt(i);

        if (Character.isDigit(c) || (c == '-' && lastWasOperator && (number.length() == 0 || !Character.isDigit(number.charAt(number.length() - 1))))) {
            number.append(c);
            lastWasOperator = false;
        } else if (c == ' ') {
            if (number.length() > 0) {
                postFix.add(number.toString());
                number = new StringBuilder();
            }
        } else {
            if (number.length() > 0) {
                postFix.add(number.toString());
                number = new StringBuilder();
            }

            if (c == '(') {
                stack.push(c);
                lastWasOperator = true;
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postFix.add(String.valueOf(stack.pop()));
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                lastWasOperator = false;
            } 
            else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    postFix.add(String.valueOf(stack.pop()));
                }
                stack.push(c);
                lastWasOperator = true;
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + c);
            }
        }
    }

    if (number.length() > 0) {
        postFix.add(number.toString());
    }

    while (!stack.isEmpty()) {
        if (stack.peek() == '(') {
            throw new IllegalArgumentException("Mismatched parentheses");
        }
        postFix.add(String.valueOf(stack.pop()));
    }

    return String.join(" ", postFix);
}

private static boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/';
}

private static int precedence(char op) {
    switch (op) {
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        default:
            return -1;
    }
}

private static int evaluatePostfix(String postFix) {
    Stack<Integer> stack = new Stack<>();
    String[] tokens = postFix.split("\\s+");

    for (String token : tokens) {
        if (isOperator(token.charAt(0)) && token.length() == 1) {
            if (stack.size() < 2) {
                throw new IllegalArgumentException("Invalid postfix expression: not enough operands. Run the program again.");
            }
            int operand2 = stack.pop();
            int operand1 = stack.pop();
            int result = applyOperator(operand1, operand2, token.charAt(0));
            stack.push(result);
        } else {
            try {
                stack.push(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid token in postfix expression: " + token);
            }
        }
    }

    if (stack.size() != 1) {
        throw new IllegalArgumentException("Invalid postfix expression: too many operands");
    }

    return stack.pop();
}

private static String formatOTP(int result) {
    result = Math.abs(result);                                          // Ensures non-negative
    String otp = String.valueOf(result);
    if (otp.length() < 4) {
        return String.format("%04d", result);
    } else if (otp.length() > 4) {
        return otp.substring(otp.length() - 4);
    }
    return otp;
}

private static int applyOperator(int operand1, int operand2, char operator) {
    switch (operator) {
        case '+': return operand1 + operand2;
        case '-': return operand1 - operand2;
        case '*': return operand1 * operand2;
        case '/': 
            if (operand2 == 0) throw new IllegalArgumentException("Division by zero");
            return operand1 / operand2;
        default: throw new IllegalArgumentException("Invalid operator: " + operator);
    }
}

private static void availOTPs(Queue<String> otpQueue, Queue<String> postFixQueue){
    int i = 0;
    Iterator<String> itPostFix = postFixQueue.iterator();
    while(itPostFix.hasNext()){
        System.out.println("Postfix notation " + (i + 1) + ": " + itPostFix.next());
        i++;
    }
    System.out.println("==================================================");
    pressEnterToContinue();
}

private static void showOTPQueue(Queue<String> otpQueue, Queue<String> postFixQueue){
    if (postFixQueue.isEmpty()){
        System.out.println("\nError: Empty post fix queue. Please input expressions first!");
    }else{
        availOTPs(otpQueue, postFixQueue);
    }
}

private static void mainMenu(){
    System.out.println("==================================================");
    System.out.println("Welcome to OTP Generator!\n");
        System.out.println("To continue, please select an option below:");
}

private static void menu(){
    System.out.println("==================================================");
    System.out.println("Please select an option below:");
}

private static int askInput(Scanner scn){
    System.out.println("\t1. Generate OTP\n\t2. Add expressions to OTP Queue\n\t3. Display OTP Queue\n\t4. Exit");
    System.out.print("==================================================");
    System.out.print("\nEnter input: ");
    int n = scn.nextInt();
    System.out.println("==================================================");
    return n;
}

private static void exit(){
    System.out.println("==================================================");
    System.out.println("        THANK YOU FOR USING OTP GENERATOR!");
    System.out.println("==================================================");
}

private static int askNumExp(Scanner scn){
    int n;
    scn.nextLine();
        while (true) {
            System.out.print("Enter the number of wanted expressions (1-10): ");
            try {
                n = Integer.parseInt(scn.nextLine());
                if (n > 0 && n <= 10) {
                    return n;
                } else {
                    System.out.println("Please enter a number between 1 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
} 
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Queue<String> postFixQueue = new LinkedList<>();
        Queue<String> otpQueue = new LinkedList<>();
        int n, choice;
        boolean isFirst = true;

        while(true){
            if (isFirst){
                mainMenu();
                isFirst = false;
            }else{
                menu();
            }
            try{
                choice = askInput(scn);
                switch(choice){
                    case 1:
                        if (postFixQueue.isEmpty()){
                            if (postFixQueue.isEmpty()) {
                                System.out.println("==================================================");
                                System.out.println("Queue is empty, please input more infix expressions.");
                                System.out.println("==================================================\n");
                                n = askNumExp(scn);
                                inputExpressions(postFixQueue, scn, n);
                            }
                        }else{
                            generateOTP(postFixQueue, otpQueue);
                        }
                        break;
                    case 2:
                        n = askNumExp(scn);
                        inputExpressions(postFixQueue, scn, n);
                        break;
                    case 3:
                        showOTPQueue(otpQueue, postFixQueue);
                        break;
                    case 4:
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
}