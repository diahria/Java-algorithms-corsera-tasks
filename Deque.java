import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
    private Node front, rear;
    private int size;
    public Deque(){
        rear = null;
        front = null;
        size = 0;
    }
    private class Node{
        Node previous = null;
        Item value = null;
        Node next = null;
        public Node() {
        }
        public Node(Node that){
            this.previous = that.previous;
            this.value = that.value;
            this.next = that.next;
        }
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void addFirst(Item item){
        if (item == null) {
            throw new IllegalArgumentException("Invalid !! you must put a value to assign");
        }
        else {
            Node oldback = new Node();
            if (isEmpty()){
                rear = oldback;
                rear.value = item;
                rear.previous = null;
                front = rear;
            }
            else{
                oldback.next = rear;
                rear = oldback;
                oldback.value = item;
            }
            size++;
        }
    }
    public void addLast(Item item){
        if (item == null){
            throw new IllegalArgumentException("Invalid !! you must put a value to assign");
        }
        else {
            if (isEmpty()){
                Node newfront = new Node();
                rear = newfront;
                rear.value = item;
                front = rear;
            }
            else{
                Node newfront = new Node();
                newfront.previous = front;
                front.next = newfront;
                front = newfront;
                newfront.next = null;
                newfront.value = item;
            }
            size++;
        }
    }
    public Item removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException("Invalid !! there is no element to delete");
    }
        else{
            Item item = rear.value;
            if (size == 1){
                rear = rear.next;
                size--;
            }
            else{
                rear = rear.next;
                rear.previous = null;
                size--;
            }
            return item;
        }
    }
    public Item removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException("Invalid !! there is no element to delete");
        }
        else{
            if (size == 1){
                Item item = front.value;
                front = front.previous;
                size--;
                return item;
            }
            else{
                Item item = front.value;
                front = front.previous;
                front.next = null;
                size--;
                return item;
            }
        }
    }
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
        private Node current = rear;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            if (!hasNext()){
                throw new NoSuchElementException("Invalid !! there is no more items");
            }
            else{
                Item item = current.value;
                current = current.next;
                return item;
            }
        }
        public void remove(){
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }
    public static void main(String[] args) {
        Deque<Integer> x =  new Deque<>();
        x.addFirst(3);
        x.addFirst(2);
        x.addLast(4);
        x.removeLast();
        x.removeFirst();
        for (Integer m : x)
            StdOut.println(m);
        StdOut.println(x.size());
    }
}
