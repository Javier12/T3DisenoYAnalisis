package trees;

public class AVLTree implements Tree {
	
	private AVLTreeNode root;
	
	public AVLTree() {
		root = new AVLTreeNode(this);
	}
	
	public void setRoot(AVLTreeNode root) {
		this.root = root;
	}
	
	public AVLTreeNode getRoot() {
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
	public boolean delete(int i) {
		return root.deleteM(i);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof AVLTree))
			return false;
		AVLTree compared = (AVLTree) o;
		return root.equals(compared.root);
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
