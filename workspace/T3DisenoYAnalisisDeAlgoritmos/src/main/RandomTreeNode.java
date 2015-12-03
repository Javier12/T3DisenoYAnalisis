package main;

import java.util.Random;

public class RandomTreeNode extends BinaryNode {
	
	private RandomTree otree;

	private boolean leaf;
	private RandomTreeNode right;
	private RandomTreeNode left;
	private RandomTreeNode parent;
	private int key;
	private int size;
	
	private static Random randomSource;
	
	
	public RandomTreeNode(RandomTree otree) {
		leaf = true;
		right = null;
		left = null;
		size = 1;
		this.otree = otree;
		randomSource = new Random();
	}
	
	private RandomTreeNode(RandomTreeNode parent) {
		leaf = true;
		right = null;
		left = null;
		size = 0;
		this.parent = parent;
		this.otree = parent.otree;
	}
	
	public int getSize() {
		return size;
	}
	
	public void insertM(int i) {
		regularInsertM(i, false, null);
	}
	
	private void regularInsertM(int i, boolean willBeNewRoot, RandomTreeNode exRoot) {
		if (leaf) {
			leaf = false;
			right = new RandomTreeNode(this);
			left = new RandomTreeNode(this);
			key = i;
			size = 1;
			if (willBeNewRoot)
				reRoot(i, exRoot);
		} else {
			if (i > key) {
				if (willBeNewRoot == false) {
					int randomness = randomSource.nextInt(size);
					if (randomness == 0) {
						right.regularInsertM(i, true, this);
					} else {
						size++;
						right.regularInsertM(i, willBeNewRoot, exRoot);
					}
				} else {
					right.regularInsertM(i, willBeNewRoot, null);
				}
			} else {
				if (willBeNewRoot == false) {
					int randomness = randomSource.nextInt(size);
					if (randomness == 0) {
						left.regularInsertM(i, true, this);
					} else {
						size++;
						left.regularInsertM(i, willBeNewRoot, exRoot);
					}
				} else {
					left.regularInsertM(i, willBeNewRoot, null);
				}
			}
		}
	}
	
	private void reRoot(int i, RandomTreeNode exRoot) {
		if (exRoot != null)
			System.out.println("Exroot: " + exRoot.key);
		else
			System.out.println("Exroot: " + "null");
		System.out.println("?");
		while (parent != exRoot) {
			RandomTreeNode exParent = parent;
			if (exParent.right == this) {
				rotateLeft();
			} else {
				rotateRight();
			}
		}
		if (parent != null) {
			RandomTreeNode exParent = parent;
			if (exParent.right == this) {
				rotateLeft();
			} else {
				rotateRight();
			}
		}
			
	}
	
	private void rotateLeft() {
		// Si soy la raiz no puedo rotar
		if (this.parent != null) {
			// Ponemos al padre como hijo izquierdo
			RandomTreeNode padreOriginal = this.parent;
			RandomTreeNode abueloOriginal = this.parent.parent;
			padreOriginal.right = this.left;
			this.left.parent = padreOriginal;
			padreOriginal.parent = this;
			this.left = padreOriginal;
			// Abuelo mantiene su tamanno. Padre e hijo deben ajustarse
			padreOriginal.size = padreOriginal.right.size + padreOriginal.left.size + 1;
			this.size = this.left.size + this.right.size + 1;
			if (abueloOriginal != null) {
				// Me pongo a mi como hijo del abuelo
				if (abueloOriginal.right == padreOriginal) {
					abueloOriginal.right = this;
				} else {
					abueloOriginal.left = this;
				}
				this.parent = abueloOriginal;
			} else {
				System.out.println("Cambio root rleft");
				otree.setRoot(this);
				this.parent = null;
			}
		}
	}
	
	private void rotateRight() {
		// Si soy la raiz no puedo rotar
		if (this.parent != null) {
			// Ponemos al padre como hijo izquierdo
			RandomTreeNode padreOriginal = this.parent;
			RandomTreeNode abueloOriginal = this.parent.parent;
			padreOriginal.left = this.right;
			this.right.parent = padreOriginal;
			padreOriginal.parent = this;
			this.right = padreOriginal;
			padreOriginal.size = padreOriginal.left.size + padreOriginal.right.size + 1;
			this.size = this.left.size + this.right.size + 1;
			if (abueloOriginal != null) {
				// Me pongo a mi como hijo del abuelo
				if (abueloOriginal.right == padreOriginal) {
					abueloOriginal.right = this;
				} else {
					abueloOriginal.left = this;
				}
				this.parent = abueloOriginal;
			} else {
				System.out.println("Cambio root rright");
				otree.setRoot(this);
				this.parent = null;
			}
		}
	}

	public boolean findM(int i) {
		if (leaf)
			return false;
		if (i == key) {
			return true;
		} else if (i > key) {
			return right.findM(i);
		} else {
			return left.findM(i);
		}
	}

	public void deleteM(int i) {
		if (leaf)
			return;
		if (key == i) {
			if (right.leaf == true && left.leaf == true) {
				leaf = true;
				size = 0;
				return;
			}
			if (right.leaf == true || left.leaf == true) {
				if (left.leaf == false) {
					key = left.key;
					RandomTreeNode auxLeft = left;
					left = auxLeft.left;
					right = auxLeft.right;
					size = left.size + right.size + 1;
				} else {
					key = right.key;
					RandomTreeNode auxRight = right;
					left = auxRight.left;
					right = auxRight.right;	
					size = left.size + right.size + 1;
				}
			} else {
				RandomTreeNode next = findNextWithDecreaseSize(true);
				key = next.key;
				next.deleteM(key);
			}
		} else if (i > key) {
			size--;
			right.deleteM(i);
		} else {
			size--;
			left.deleteM(i);
		}
		
	}
	
	private RandomTreeNode findNextWithDecreaseSize(boolean start) {
		if (start) {
			right.size--;
			return right.findNextWithDecreaseSize(false);
		}
		if (left.leaf)
			return this;
		left.size--;
		return left.findNextWithDecreaseSize(false);
	}

	@Override
	public boolean isLeaf() {
		return leaf;
	}
	
	@Override
	public RandomTreeNode getLeft() {
		return left;
	}
	
	@Override
	public RandomTreeNode getRight() {
		return right;
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public BinaryNode getParent() {
		return parent;
	}

}