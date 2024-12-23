import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int i = 4, intI;
        double d = 4.0, intD;
        String s = "HackerRank ";
        String s1;
        
        intI = scn.nextInt();
        intD = scn.nextDouble();
        scn.nextLine();
        s1 = scn.nextLine();
        
        System.out.println(i + intI);
        System.out.println(d + intD);
        System.out.println(s + s1);
    }
}
