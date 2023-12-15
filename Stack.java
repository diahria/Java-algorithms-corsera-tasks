import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
    private Item [] a;
    private int size;
    private int top;
    public Stack(){
        this.a = (Item[]) new Object[4];
        this.size = 0;
        this.top = 0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void resize(int n){
        int m = Math.min(size(),n);
        Item [] y = (Item[]) new Object[n];
        for (int i=0 ; i<m ;i++) {
            y[i] = a[i];
        }
        a = y;
    }
    public void push(Item v){
        if (v == null) {
            throw new IllegalArgumentException("please insert a value to push");
        }
        else {
            if (size() == 4) {
                resize(2 * a.length);
            }
            a[top++] = v;
            size++;
        }
    }
    public Item pop(){
        Item l;
        if(isEmpty()){
            throw new NoSuchElementException("there is no element to pop");
        }
        else{
            if(size() == a.length/4){
                resize(a.length/2);
            }
             l = a[top--];
             size--;
             return l;
        }
    }

    public Iterator<Item> iterator() {
        return  new StackIterator();
    }
    private class StackIterator implements Iterator<Item>{
        private int current;
        private int b;
        public StackIterator(){
            this.b = 0;
            this.current = top;
        }
        public boolean hasNext(){
                return b < size();
        }
        public Item next(){
            if (!hasNext()) {
                throw new NoSuchElementException("there no such elemnt to reach");
            }
            else {
                a[current] = a[--top];
                b++;
                return a[current];
            }
        }
        public void remove(){
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }
    public static void main(String[] args) {
        Stack<Character> x = new Stack<>();
        x.push('a');
        x.push('b');
        x.push('c');
        x.push('d');
        x.push('e');
        x.pop();
        for(Character s : x){
            StdOut.println(s);
        }
    }
}
