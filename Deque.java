import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // Creating private variables
    private int n;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        n = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (first == null && last == null);
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }


    // add the item to the front
    public void addFirst(Item item) {
        Node oldfirst;
        oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst != null) {
            oldfirst.prev = first;
        }
        n = n + 1;
        if (size() == 1) {
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (oldLast != null) {
            oldLast.next = last;
        }
        last.prev = oldLast;
        n = n + 1;
        if (size() == 1) {
            first = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        n = n - 1;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = last.item;
        last = last.prev;
        n = n - 1;
        return item;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        private Node changing;

        public ArrayIterator() {
            changing = first;
        }

        public boolean hasNext() {
            return changing != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }
            Node temp = changing;
            changing = changing.next;
            return temp.item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque test = new Deque();
        StdOut.println(test.isEmpty());
        StdOut.println(test.size());

        test.addFirst("first");
        test.addLast("second");
        StdOut.println(test.size());
        StdOut.println(test.removeFirst());
        StdOut.println(test.removeLast());

        test.addFirst("fourth");
        test.addFirst("third");
        test.addLast("fifth");
        test.addLast("sixth");

        Iterator iter = test.iterator();

        Object s = null;
        while (iter.hasNext()) {
            s = iter.next();
            StdOut.println(s);
        }
    }
}
