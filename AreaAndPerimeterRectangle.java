import java.util.Scanner;
public class AreaAndPerimeterRectangle{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER THE LENGTH OF THE RECTANGLE : ");
        int length = sc.nextInt();
        System.out.println("ENTER THE BREADTH OF THE RECTANGLE : ");
        int breadth = sc.nextInt();
        System.out.println("Area Of Rectanlge:" + (length*breadth));
        System.out.println("Perimeter Of Rectanlge:" + (2)*(length+breadth));
    }
}