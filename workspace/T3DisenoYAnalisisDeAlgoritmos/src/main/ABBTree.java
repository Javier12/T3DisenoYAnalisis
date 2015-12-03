package main;

public class ABBTree extends BinaryNode implements Tree {

	private boolean leaf;
	private ABBTree right;
	private ABBTree left;
	private int key;
	private ABBTree parent;
	
	
	public ABBTree() {
		leaf = true;
		right = null;
		left = null;
	}
	
	public ABBTree(ABBTree parent) {
		leaf = true;
		right = null;
		left = null;
		this.parent = parent;
	}
	
	@Override
	public void insert(int i) {
		if (leaf) {
			leaf = false;
			right = new ABBTree(this);
			left = new ABBTree(this);
			key = i;
		} else {
			if (i > key) {
				right.insert(i);
			} else {
				left.insert(i);
			}
		}
	}

	@Override
	public boolean find(int i) {
		if (leaf)
			return false;
		if (i == key) {
			return true;
		} else if (i > key) {
			return right.find(i);
		} else {
			return left.find(i);
		}
	}

	@Override
	public void delete(int i) {
		if (leaf)
			return;
		if (key == i) {
			if (right.leaf == true && left.leaf == true) {
				leaf = true;
				return;
			}
			if (right.leaf == true || left.leaf == true) {
				if (left.leaf == false) {
					key = left.key;
					ABBTree auxLeft = left;
					left = auxLeft.left;
					right = auxLeft.right;
					auxLeft.left.parent = this;
					auxLeft.right.parent = this;
					auxLeft = null;
				} else {
					key = right.key;
					ABBTree auxRight = right;
					left = auxRight.left;
					right = auxRight.right;	
					auxRight.left.parent = this;
					auxRight.right.parent = this;
					auxRight = null;
				}
			} else {
				ABBTree next = findNext(true);
				key = next.key;
				next.delete(key);
			}
		} else if (i > key) {
			right.delete(i);
		} else {
			left.delete(i);
		}
		
	}
	
	private ABBTree findNext(boolean start) {
		if (start) {
			return right.findNext(false);
		}
		if (left.leaf)
			return this;
		return left.findNext(false);
	}

	@Override
	public boolean isLeaf() {
		return leaf;
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public BinaryNode getRight() {
		return right;
	}

	@Override
	public BinaryNode getLeft() {
		return left;
	}

	@Override
	public BinaryNode getParent() {
		return parent;
	}
	
	

}
