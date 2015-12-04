package trees;

public class TreeTimeMeasurer {
	
	private Tree tree;
	
	public TreeTimeMeasurer(Tree tree) {
		this.tree = tree;
	}
	
	public long measureInsert(int i) {
		long start = System.nanoTime();
		tree.insert(i);
		long end = System.nanoTime();
		return (end - start);
	}
	
	public long measureFind(int i) {
		long start = System.nanoTime();
		tree.find(i);
		long end = System.nanoTime();
		return (end - start);
	}
	
	public long measureDelete(int i) {
		long start = System.nanoTime();
		tree.delete(i);
		long end = System.nanoTime();
		return (end - start);
	}
	
	public long measureMax() {
		long start = System.nanoTime();
		tree.getMax();
		long end = System.nanoTime();
		return (end - start);
	}
	
	public long measureMin() {
		long start = System.nanoTime();
		tree.getMin();
		long end = System.nanoTime();
		return (end - start);
	}
	
	public long measureNext(int i) {
		long start = System.nanoTime();
		tree.getNext(i);
		long end = System.nanoTime();
		return (end - start);
	}
	
	public long measurePrevious(int i) {
		long start = System.nanoTime();
		tree.getPrevious(i);
		long end = System.nanoTime();
		return (end - start);
	}

}
