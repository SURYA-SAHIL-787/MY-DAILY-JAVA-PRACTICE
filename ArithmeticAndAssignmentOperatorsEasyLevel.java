import java.util.Scanner;
public class ArithmeticAndAssignmentOperatorsEasyLevel{
    public static void main (String[]args){
    Scanner sc = new Scanner(System.in);
    // ADDITION
    System.out.println("ENTER NUMBER 1 :");
    int num1 = sc.nextInt();
    System.out.println("ENTER NUMBER 2 :");
    int num2 = sc.nextInt();
    System.out.println("SUM OF TWO NUMBERs : " + (num1+num2));
    // SUBTRACTION ( DIFFERENCE)
    System.out.println("ENTER NUMBER A :");
    int A = sc.nextInt();
    System.out.println("ENTER NUMBER B :");
    int B = sc.nextInt();
    System.out.println("DIFFERENCE OF TWO NUMBERS : " + (A-B));
    // PRODUCT
    System.out.println("ENTER NUMBER C :");
    int C = sc.nextInt();
    System.out.println("ENTER NUMBER D :");
    int D = sc.nextInt();
    System.out.println("PRODUCT OF TWO NUMBERS : " + (C*D));
    // DIVISION AND REMAINDER
    System.out.println("ENTER NUMBER E :");
    int E = sc.nextInt();
    System.out.println("ENTER NUMBER F :");
    int F = sc.nextInt();
    if (F==0){
        System.out.println("Division By Zero Gives Infinite Not Allowed ,... Enter Number Greater Than Zero");
    }else{
    System.out.println("DIVISION OF TWO NUMBERS : " + (E/F));
    System.out.println("REMAINDER OF TWO NUMBERS : " + (E%F));
    }
}
}