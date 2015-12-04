package trees;

public class SplayTreeNode extends BinaryNode {
	
	private SplayTree otree;
	
	private boolean leaf;
	private SplayTreeNode parent;
	private SplayTreeNode right;
	private SplayTreeNode left;
	private int key;
	
	/**
	 * Crea un nodo raiz
	 */
	SplayTreeNode(SplayTree otree) {
		this.otree = otree;
		leaf = true;
		parent = null;
		right = null;
		left = null;
		key = 0;
	}
	
	/**
	 * Usado para construir los nodos internos
	 * @param parent El nodo padre
	 */
	private SplayTreeNode(SplayTreeNode parent) {
		this.otree = parent.otree;
		leaf = true;
		this.parent = parent;
		right = null;
		left = null;
	}
	
	public SplayTreeNode getParent() {
		return parent;
	}
	
	public void insertM(int i) {
		if (leaf) {
			leaf = false;
			right = new SplayTreeNode(this);
			left = new SplayTreeNode(this);
			key = i;
			moveToTop();
		} else {
			if (i > key) {
				right.insertM(i);
			} else {
				left.insertM(i);
			}
		}
	}
	
	public boolean findM(int i) {
		if (leaf)
			return false;
		if (i == key) {
			moveToTop();
			return true;
		} else if (i > key) {
			return right.findM(i);
		} else {
			return left.findM(i);
		}
	}
	
	public boolean deleteM(int i) {
		if (leaf)
			return false;
		if (key == i) {
			if (right.leaf == true && left.leaf == true) {
				leaf = true;
				key = 0;
				right = null;
				left = null;
				if (parent != null)
					parent.moveToTop();
				return true;
			}
			if (right.leaf == true || left.leaf == true) {
				if (left.leaf == false) {
					SplayTreeNode auxLeft = left;
					right = null;
					key = auxLeft.key;
					left = auxLeft.left;
					right = auxLeft.right;
					auxLeft.left.parent = this;
					auxLeft.right.parent = this;
					auxLeft = null;
					if (parent != null)
						parent.moveToTop();
					return true;
				} else {
					SplayTreeNode auxRight = right;
					left = null;
					key = auxRight.key;
					left = auxRight.left;
					right = auxRight.right;	
					auxRight.left.parent = this;
					auxRight.right.parent = this;
					auxRight = null;
					if (parent != null)
						parent.moveToTop();
					return true;
				}
			} else {
				SplayTreeNode next = findNextM(true);
				key = next.key;
				return next.deleteM(key);
			}
		} else if (i > key) {
			return right.deleteM(i);
		} else {
			return left.deleteM(i);
		}
	}
	
	private void moveToTop() {
		SplayTreeNode me = this;
		while (otree.getRoot() != me) {
			//System.out.println("Moving to top me: " + me.getKey());
			//System.out.println("Moving to top root: " + otree.getRoot().getKey());
			if (me.parent == otree.getRoot()) {
				// Operaciones de zig o zag
				if (me.parent.left == me) {
					//System.out.println("Zig");
					me.zig();
				} else {
					//System.out.println("Zag");
					me.zag();
				}
			} else {
				// Operaciones de zigzig, zigzag, zagzig, zagzag
				SplayTreeNode abuelo = me.parent.parent;
				if (abuelo.left == me.parent) {
					//zigzig, zigzag
					if (parent.left == me) {
						//System.out.println("ZigZig");
						me.zigzig();
					} else {
						//System.out.println("ZigZag");
						me.zigzag();
					}
				} else {
					if (parent.right == me) {
						//System.out.println("zagzag");
						me.zagzag();
					} else {
						//System.out.println("ZagZig");
						me.zagzig();
					}
				}
			}
		}
	}
	
	private void zig() {
		SplayTreeNode exRoot = otree.getRoot();
		exRoot.left = this.right;
		this.right.parent = exRoot;
		exRoot.parent = this;
		this.right = exRoot;
		this.parent = null;
		otree.setRoot(this);
	}
	
	private void zag() {
		SplayTreeNode exRoot = otree.getRoot();
		exRoot.right = this.left;
		this.left.parent = exRoot;
		exRoot.parent = this;
		this.left = exRoot;
		this.parent = null;
		otree.setRoot(this);
	}
	
	private void zigzig() {
		SplayTreeNode egreatGrandParent = this.parent.parent.parent;
		SplayTreeNode egrandParent = this.parent.parent;
		SplayTreeNode eparent = this.parent;
		
		egrandParent.left = eparent.right;
		eparent.right.parent = egrandParent;
		egrandParent.parent = eparent;
		
		eparent.left = this.right;
		this.right.parent = eparent;
		eparent.right = egrandParent;
		eparent.parent = this;
		
		this.right = eparent;
		
		checkGrandParent(egreatGrandParent, egrandParent);
		
	}
	
	private void zigzag() {
		SplayTreeNode egreatGrandParent = this.parent.parent.parent;
		SplayTreeNode egrandParent = this.parent.parent;
		SplayTreeNode eparent = this.parent;
		
		eparent.right = this.left;
		this.left.parent = eparent;
		eparent.parent = this;
		
		egrandParent.left = this.right;
		this.right.parent = egrandParent;
		egrandParent.parent = this;
		
		this.left = eparent;
		this.right = egrandParent;
		
		checkGrandParent(egreatGrandParent, egrandParent);
	}
	
	private void zagzag() {
		SplayTreeNode egreatGrandParent = this.parent.parent.parent;
		SplayTreeNode egrandParent = this.parent.parent;
		SplayTreeNode eparent = this.parent;
		
		egrandParent.right = eparent.left;
		eparent.left.parent = egrandParent;
		egrandParent.parent = eparent;
		
		eparent.right = this.left;
		this.left.parent = eparent;
		eparent.left = egrandParent;
		eparent.parent = this;
		
		this.left = eparent;
		
		checkGrandParent(egreatGrandParent, egrandParent);
	}
	
	private void zagzig() {
		SplayTreeNode egreatGrandParent = this.parent.parent.parent;
		SplayTreeNode egrandParent = this.parent.parent;
		SplayTreeNode eparent = this.parent;
		
		eparent.left = this.right;
		this.right.parent = eparent;
		eparent.parent = this;
		
		egrandParent.right = this.left;
		this.left.parent = egrandParent;
		egrandParent.parent = this;
		
		this.left = egrandParent;
		this.right = eparent;
		
		checkGrandParent(egreatGrandParent, egrandParent);
	}
	
	private void checkGrandParent(SplayTreeNode egreatGrandParent, SplayTreeNode egrandParent) {
		if (egreatGrandParent != null) {
			if (egreatGrandParent.right == egrandParent) {
				egreatGrandParent.right = this;
			} else if (egreatGrandParent.left == egrandParent) {
				egreatGrandParent.left = this;
			}
			this.parent = egreatGrandParent;
		} else {
			otree.setRoot(this);
			this.parent = null;
		}
	}
	
	private SplayTreeNode findNextM(boolean start) {
		if (start) {
			return right.findNextM(false);
		}
		if (left.leaf)
			return this;
		return left.findNextM(false);
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public SplayTreeNode getRight() {
		return right;
	}
	
	@Override
	public SplayTreeNode getLeft() {
		return left;
	}

	@Override
	public boolean isLeaf() {
		return leaf;
	}
	

}
