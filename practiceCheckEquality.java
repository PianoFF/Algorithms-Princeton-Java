import edu.princeton.cs.algs4.StdOut;

public class practiceCheckEquality 
{
  public static void main(String[] args)
  {
    int numOne;
    int numTwo;
    int numThree;
    if(args.length == 3){
      numOne = Integer.parseInt(args[0]);
      numTwo = Integer.parseInt(args[1]);
      numThree = Integer.parseInt(args[2]);
    } else {
      StdOut.println("Please try again with three arguments");
      return;
    }
    if(numOne == numTwo && numOne == numThree){
      StdOut.println("equal");
    } else {
      StdOut.println("not equal");
    }
  }
}