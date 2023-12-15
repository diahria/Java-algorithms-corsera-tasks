import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int size;
    public RandomizedQueue(){
        this.a = (Item[]) new Object[4];
        this.size = 0;
    }

    private void resize(int s){
        Item[] y = (Item[]) new Object[s];
        int v = Math.min(a.length, s);
        for(int i = 0; i < v; i++){
            y[i] = a[i];
        }
        a = y;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void enqueue(Item item){
        if (item == null){
            throw new NullPointerException("Cannot add null Item");
        }
        if (size == a.length){
            resize(a.length * 2);
            a[size] = item;
            size++;
        }
        else{
            a[size] = item;
            size++;
        }
    }

    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException("Invalid !! there is no element to delete");
        }
        else{
            int randomIndex = StdRandom.uniformInt(size);
            Item returnItem = a[randomIndex];
            if (size - 1 == randomIndex){
                a[randomIndex] = null;
            }
            else{
                a[randomIndex] = a[size - 1];
                a[size - 1] = null;
            }
            if (size == a.length / 4){
                resize(a.length / 2);
            }
            size--;
            return returnItem;
        }
    }

    public Item sample(){
        if (isEmpty()){
            throw new NoSuchElementException("Invalid the queue is Empty");
        }
        else{
            int n = (int) (((Math.random() * 10) % a.length));
            return a[n];
        }
    }

    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>{
        int b = 0;
        private int current;
        boolean[] sum;

        public RandomizedQueueIterator(){
            sum = new boolean[size()];
        }

        public boolean hasNext(){
            return b < size;
        }

        public Item next(){
            if (!hasNext()){
                throw new java.util.NoSuchElementException("No more items in iteration.");
            }
            else{
                current = StdRandom.uniformInt(size());
                if (!sum[current]) {
                    sum[current] = true;
                }
                else{
                    do{
                        current = StdRandom.uniformInt(size());
                    }
                    while (sum[current]);
                    sum[current] = true;
                }
                b++;
                return a[current];
            }
        }

        public void remove(){
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }

    public static void main(String[] args){

    }
}
