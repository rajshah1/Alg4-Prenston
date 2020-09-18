import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item [] rsq;
    private int N=0;
    // construct an empty randomized queue
	public RandomizedQueue(){
        rsq=(Item[]) new Object[1];
    }
    // is the randomized queue empty?
    public boolean isEmpty(){
        return N==0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return N;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null)
        throw new IllegalArgumentException();
        if(N==rsq.length)
            resize(2*rsq.length);    
        rsq[N++]=item;
    }
    // remove and return a random item
    public Item dequeue(){
    	if(N==0) {
    		throw new NoSuchElementException();
    	}
    	int randIndex=StdRandom.uniform(N);
    	Item val=rsq[randIndex];
    	if(randIndex!=N-1)
    		rsq[randIndex]=rsq[N-1];
    	N--;
    	//System.out.println("N Value "+N+" LenTh "+rsq.length);
    	if(N>0 && N==rsq.length/4) {
    		resize(rsq.length/2);
    	}
    	rsq[N]=null;
    	return val;
    }
    
	private void resize(int i) {
		//System.out.println("IN RESIZE "+i);
        Item[] copyrsq=(Item[])new Object[i];
        for(int j=0;j<N;j++) {
        	//System.out.println("Copying :"+rsq[j]);
        	copyrsq[j]=rsq[j];
        }
            
        rsq=copyrsq;
        copyrsq=null;
        }

    // return a random item (but do not remove it)
    public Item sample(){
    	if(N==0) {
    		throw new NoSuchElementException();
    	}
    	Item val=rsq[StdRandom.uniform(N)];
    	return val;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new randIterator();
    }
    private class randIterator implements Iterator<Item>{
		private final Item[] copyItems=(Item[]) new Object[rsq.length];
    	private int copyN=N;
    	private randIterator() {
    		for(int j=0;j<rsq.length;j++)
    			copyItems[j]=rsq[j];
    	}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return copyN>0;
		}

		@Override
		public Item next() {
			if(!hasNext()) {
	    		throw new NoSuchElementException();
	    	}
			int randIndex=StdRandom.uniform(copyN);
	    	Item val=rsq[randIndex];
	    	if(randIndex!=copyN-1)
	    		rsq[randIndex]=rsq[copyN-1];
	    	rsq[copyN-1]=null;
	    	copyN--;
	    	return val;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
    	
    }
    // unit testing (required)
	public static void main(String[] args){
    	RandomizedQueue<String> rmqueue=new RandomizedQueue<String>();
    	rmqueue.enqueue("a");
    	//System.out.println(rmqueue.size());
    	rmqueue.enqueue("b");
    	//System.out.println(rmqueue.size());
    	rmqueue.enqueue("c");
    	//System.out.println(rmqueue.size());
    	rmqueue.dequeue();
    	//System.out.println(rmqueue.size());
    	rmqueue.dequeue();
    	//System.out.println(rmqueue.size());
    	
    }

}