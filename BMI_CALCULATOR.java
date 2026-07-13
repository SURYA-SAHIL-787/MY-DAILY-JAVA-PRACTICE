import java.util.Scanner;
public class BMI_CALCULATOR {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER WEIGHT IN KG : ");
        double W = sc.nextDouble();
        System.out.println("ENTER HEIGHT IN METRE : ");
        double H = sc.nextDouble();
        double BMI = W / Math.pow(H, 2);
        System.out.println("YOUR BMI INDEX IS : " + BMI );
        sc.close();
    }
}
