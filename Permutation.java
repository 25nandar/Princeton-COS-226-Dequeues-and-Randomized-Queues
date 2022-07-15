import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] input = StdIn.readAllStrings();
        RandomizedQueue permutation_queue = new RandomizedQueue();
        for (int i = 0; i < input.length; i++) {
            permutation_queue.enqueue(input[i]);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(permutation_queue.dequeue());
        }
    }
}
