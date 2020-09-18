import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        if (oldfirst == null)
            last = first;
        else
            oldfirst.previous = first;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldlast;
        if (oldlast == null)
            first = last;
        else
            oldlast.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0)
            throw new NoSuchElementException();
        Item returnItem = first.item;
        first = first.next;
        size--;
        if (size > 0)
            first.previous = null;
        else
            last = null;
        return returnItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0)
            throw new NoSuchElementException();
        Node oldLast = last;
        last = last.previous;
        size--;
        if (size > 0)
            last.next = null;
        else
            first = null;
        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new dequeIterator();
    }

    private class dequeIterator implements Iterator<Item> {
        private Node Current = first;

        @Override
        public boolean hasNext() {
            return Current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item val = Current.item;
            Current = Current.next;
            return val;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> a = new Deque<Integer>();
        a.addFirst(1);
        a.removeLast();
        
    }
}