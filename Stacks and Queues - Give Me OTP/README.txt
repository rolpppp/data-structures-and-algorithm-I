This is an OTP generator using the queue data structure. Initially, the program prompts the user n-times with string input of an infix expression. 
For each input, the program processes it to become a postfix expression using the stack data structure, and then stores it in a queue. 
For simplicity, parenthesis is used only as the grouping symbol. For the operators the following are used: multiplication ( * ), division ( / ), addition ( + ), and subtraction ( - ).

Algorithm:

1.) Prompt the user to generate an OTP by dequeuing to your queue storage
2.) Evaluating that postfix expression using stack implementation. 
    2.1) If the resulting evaluation has 4 digits, return that as the OTP. 
    2.2) Else if it has less than 4 digits, append zeros in front to become 4 digits. 
    2.3) Else if greater than 4 digits, return only the last 4 digits. 
3.) Also, allow the user to have an option to add another input to your queue. 
4.) If there is nothing left to dequeue, prompt the user to input infix expressions again.

Generating OTP:

1.) Display first the postfix notation and then the evaluated postfix expression as the OTP.

