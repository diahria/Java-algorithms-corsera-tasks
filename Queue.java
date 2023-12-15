import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class Queue<Item> implements Iterable<Item> {
    private Node rear,front;
    private int size;
    public Queue(){
        this.rear = null;
        this.front = null;
        this.size = 0;
    }
    private class Node{
        Node next = null;
        Item value = null;
        public Node(){
        }
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void enqueue(Item item){
        if(item == null){
            throw new IllegalArgumentException("Invalid out a real value to insert in the queue");
        }
        else{
            Node newrear = new Node();
            if (isEmpty()) {
                rear = newrear;
                newrear.value = item;
                front = rear;
            }
            else if(size() == 1){
                newrear.next = rear;
                rear = newrear;
                front.next = rear;
                newrear.value = item;
            }
            else{
                newrear.next = rear;
                rear = newrear;
                newrear.value = item;
            }
            size++;
        }
    }
    public Item dequeue(){
        if (isEmpty()) {
            throw new NoSuchElementException("Invalid there is no element to delete from the queue");
        }
        else{
            if (size() == 1) {
                Item value;
                value = front.value;
                front = null;
                rear = null;
                size--;
                return value;
            }
            else{
                Item value;
                value = front.value;
                front = front.next;
                size--;
                return value;
            }
        }
    }
    public Iterator<Item> iterator(){
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<Item>{
        private Node current = rear;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException("Invalid there is no next element");
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
        Queue<Integer> x = new Queue<>();
        x.enqueue(1);
        x.enqueue(2);
        x.enqueue(3);
        x.enqueue(4);
        x.dequeue();
        for (Integer y : x) {
            StdOut.println(y);
        }
    }
}
