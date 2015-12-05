package trees;

public class TreeTimeMeasurer {
	
	private Tree tree;
	
	public TreeTimeMeasurer(Tree tree) {
		this.tree = tree;
	}
	
	public int measureInsert(int i) {
		long start = System.nanoTime();
		tree.insert(i);
		long end = System.nanoTime();
		return (int) (end - start);
	}
	
	public TuplaTimeOperationResult measureFind(int i) {
		long start = System.nanoTime();
		boolean operationResult = tree.find(i);
		long end = System.nanoTime();
		return new TuplaTimeOperationResult((int) (end - start), operationResult);
	}
	
	public TuplaTimeOperationResult measureDelete(int i) {
		long start = System.nanoTime();
		boolean operationResult = tree.delete(i);
		long end = System.nanoTime();
		return new TuplaTimeOperationResult((int) (end - start), operationResult);
	}
	
	public int measureMax() {
		long start = System.nanoTime();
		tree.getMax();
		long end = System.nanoTime();
		return (int) (end - start);
	}
	
	public int measureMin() {
		long start = System.nanoTime();
		tree.getMin();
		long end = System.nanoTime();
		return (int) (end - start);
	}
	
	public int measureNext(int i) {
		long start = System.nanoTime();
		tree.getNext(i);
		long end = System.nanoTime();
		return (int) (end - start);
	}
	
	public int measurePrevious(int i) {
		long start = System.nanoTime();
		tree.getPrevious(i);
		long end = System.nanoTime();
		return (int) (end - start);
	}
	
	public class TuplaTimeOperationResult {
		
		private int time;
		private boolean operationResult;
		
		public TuplaTimeOperationResult(int time, boolean operationResult) {
			this.time = time;
			this.operationResult = operationResult;
		}
		
		public int getTime() {
			return time;
		}
		
		public boolean getOperationResult() {
			return operationResult;
		}
		
		
	}

}
