package main;

public class RandomTree implements Tree {

	private RandomTreeNode root;
	
	public RandomTree() {
		root = new RandomTreeNode(this);
	}
	
	public void setRoot(RandomTreeNode root) {
		this.root = root;
	}
	
	public RandomTreeNode getRoot() {
		return root;
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
