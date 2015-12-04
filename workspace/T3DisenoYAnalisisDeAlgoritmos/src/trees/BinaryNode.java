package trees;

public abstract class BinaryNode {

	public abstract boolean isLeaf();
	public abstract int getKey();
	public abstract BinaryNode getRight();
	public abstract BinaryNode getLeft();
	public abstract BinaryNode getParent();
	
	public Integer getMax() {
		BinaryNode current = this;
		Integer toReturn = null;
		while (!current.isLeaf()) {
			toReturn = current.getKey();
			current = current.getRight();
		}
		return toReturn;
	}

	
	public Integer getMin() {
		BinaryNode current = this;
		Integer toReturn = null;
		while (!current.isLeaf()) {
			toReturn = current.getKey();
			current = current.getLeft();
		}
		return toReturn;
	}
	
	public BinaryNode findTree(int i) {
		if (isLeaf()) {
			System.out.println("HI: " + getKey());
			return this;
		}
		if (i == getKey()) {
			System.out.println("MI: " + getKey());
			return this;
		} else if (i > getKey()) {
			System.out.println("RI: " + getKey());
			return getRight().findTree(i);
		} else {
			System.out.println("LI: " + getKey());
			return getLeft().findTree(i);
		}
	}

	
	public Integer getNext(int i) {
		BinaryNode iTree = findTree(i);
		if (iTree == null)
			return null;
		if (iTree.isLeaf() || iTree.getRight().isLeaf()) {
			BinaryNode current = iTree;
			while (current.getParent() != null) {
				BinaryNode currentParent = current.getParent();
				if (currentParent.getLeft() == current) {
					System.out.println("Current parent key: " + currentParent.getKey());
					return currentParent.getKey();
				}
				current = currentParent;
			}
			return null;
		} else {
			BinaryNode current = iTree.getRight();
			while (!current.getLeft().isLeaf()) {
				current = current.getLeft();
			}
			return current.getKey();
		}
	}

	
	public Integer getPrevious(int i) {
		BinaryNode iTree = findTree(i);
		if (iTree == null)
			return null;
		if (iTree.isLeaf() || iTree.getLeft().isLeaf()) {
			BinaryNode current = iTree;
			while (current.getParent() != null) {
				BinaryNode currentParent = current.getParent();
				if (currentParent.getRight() == current) {
					return currentParent.getKey();
				}
				current = currentParent;
			}
			return null;
		} else {
			BinaryNode current = iTree.getLeft();
			while (!current.getRight().isLeaf()) {
				current = current.getRight();
			}
			return current.getKey();
		}
	}
	
}
