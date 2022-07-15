import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // Creating queue
    private Item[] queue;
    private int n;


    // construct an empty deque
    public RandomizedQueue() {
        queue = (Item[]) new Object[8];
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (n == queue.length) {
            resize(2 * queue.length);    // double size of array if necessary
        }
        queue[n] = item;
        n = n + 1;
    }

    // remove and return a random item
    public Item dequeue() {
        if (n == 0) {
            return (Item) new NoSuchElementException();
        }
        int random_index = StdRandom.uniform(n);
        Item random_item = (Item) queue[random_index];
        queue[random_index] = queue[n - 1];
        queue[n - 1] = null;
        n = n - 1;
        if (n <= queue.length / 4)
            resize(queue.length / 2);
        return random_item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (queue.length == 0) {
            return (Item) new NoSuchElementException();
        }
        Random rand = new Random();
        int int_random = rand.nextInt(n);
        Item sampled;
        sampled = queue[int_random];
        return sampled;
    }

    // return an iterator over items in randomized order
    public Iterator<Item> iterator() {
        return new RandomizedQueue.ArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        // creating private variables
        private int i;
        private int[] rand_iter_array;
        private Item[] copy_queue;


        public ArrayIterator() {
            i = 0;
            int count = 0;
            for (int a = 0; a < queue.length; a++) {
                if (queue[a] != null) {
                    count = count + 1;
                }
            }
            copy_queue = (Item[]) new Object[count];
            for (int b = 0; b < count; b++) {
                copy_queue[b] = queue[b];
            }
            StdRandom.shuffle(copy_queue);
        }

        public boolean hasNext() {
            return i != copy_queue.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }
            Item answer = copy_queue[i];
            i = i + 1;
            return answer;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue test = new RandomizedQueue();
        StdOut.println(test.isEmpty());

        test.enqueue("first");
        test.enqueue("second");
        test.enqueue("third");
        test.enqueue("fourth");
        StdOut.println(test.size());

        Iterator iter = test.iterator();

        Object s = null;
        while (iter.hasNext()) {
            s = iter.next();
            StdOut.println(s);
        }

        StdOut.println("b");

        StdOut.println(test.sample());

        StdOut.println(test.dequeue());
        StdOut.println(test.size());
        StdOut.println(test.dequeue());
        StdOut.println(test.size());
        StdOut.println(test.dequeue());
        StdOut.println(test.size());
        StdOut.println(test.dequeue());
        StdOut.println(test.size());

        StdOut.println("c");

        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }
}
