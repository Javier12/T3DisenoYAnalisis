package main;

public class VanEmdeBoasTree implements Tree {

	private VanEmdeBoasNode root;	
	
	public VanEmdeBoasTree(int maxInt) throws Exception {
		if (!isPowerOf2(maxInt))
			throw new Exception("Debe inicializarse con una potencia de dos");
		root = new VanEmdeBoasNode(maxInt);
	}
	
	@Override
	public void insert(int i) {
		root.insertM(i);
	}

	@Override
	public boolean find(int i) {
		return root.findM(i);
	}

	@Override
	public void delete(int i) {
		root.deleteM(i);
	}
	
	private static boolean isPowerOf2(int x) {
		if(x <= 0)
			return false;
		
		while(x % 2 == 0)
			x = x / 2;
		
		if(x > 1)
			return false;
		return true;
	}

	@Override
	public Integer getMax() {
		return root.getMax();
	}

	@Override
	public Integer getMin() {
		return root.getMin();
	}

	@Override
	public Integer getNext(int i) {
		return root.getNext(i);
	}

	@Override
	public Integer getPrevious(int i) {
		return root.getPrevious(i);
	}

}
