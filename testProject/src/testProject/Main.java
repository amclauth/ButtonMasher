package testProject;

public class Main {
	
	public static int y;
    public static void foo(int x) 
    {
        System.out.print("foo ");
        y = x;
    }
    public static int bar(int z) 
    {
        System.out.print("bar ");
        return y = z;
    }
    public static void main(String [] args ) 
    {
    	double value = -9.0;
        System.out.println( Math.sqrt(value));
    }

}
