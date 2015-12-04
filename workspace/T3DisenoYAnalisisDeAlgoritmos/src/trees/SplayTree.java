package trees;

public class SplayTree implements Tree {
	
	private SplayTreeNode root;
	
	public SplayTree() {
		root = new SplayTreeNode(this);
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
	public boolean delete(int i) {
		return root.deleteM(i);
	}
	
	public SplayTreeNode getRoot() {
		return root;
	}
	
	public void setRoot(SplayTreeNode root) {
		this.root = root;
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
