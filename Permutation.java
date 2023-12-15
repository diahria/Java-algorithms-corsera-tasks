import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> x = new RandomizedQueue<>();
        x.enqueue(StdIn.readString());
        x.enqueue(StdIn.readString());
        x.enqueue(StdIn.readString());
        x.enqueue(StdIn.readString());
        x.enqueue(StdIn.readString());
        x.enqueue(StdIn.readString());
        x.enqueue(StdIn.readString());
        x.enqueue(StdIn.readString());
        for (String s : x) {
            StdOut.println(s);
        }
    }
}
