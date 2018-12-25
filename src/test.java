
import java.io.PrintStream;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws Exception {
        StringEvaluater e = new StringEvaluater();
        try {
        	//uncomment below line to test
        	//System.out.println(e.evaluate("√(-4.1^2/y*cosec(30π/3!*x))", true, 2, new char[]{'x','y'}, new double[]{30,1}));
        	Scanner scan= new Scanner(System.in);
        	System.out.println("Enter equation: ");
        	String eqn=scan.nextLine();
        	System.out.println(e.evaluate(eqn, true));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}


