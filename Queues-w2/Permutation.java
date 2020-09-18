import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		RandomizedQueue<String> rmque=new RandomizedQueue<String>();
		int uniqueComb=Integer.valueOf(args[0]);
		while(!StdIn.isEmpty()) {
			String currentString=StdIn.readString();
			rmque.enqueue(currentString);
		}
		while(uniqueComb>0) {
			StdOut.println(rmque.dequeue());
			uniqueComb--;
		}
		
		
	}

}
