package main;

public class AVLTreeNode extends BinaryNode {
	
	private AVLTree otree;
	
	private boolean leaf;
	private AVLTreeNode parent;
	private AVLTreeNode right;
	private AVLTreeNode left;
	private int key;
	private int height;
	
	
	/**
	 * Usado para construir el nodo raiz
	 */
	public AVLTreeNode(AVLTree otree) {
		this.otree = otree;
		leaf = true;
		parent = null;
		right = null;
		left = null;
		height = 0;
	}
	
	/**
	 * Usado para construir los nodos internos
	 * @param parent El nodo padre
	 */
	private AVLTreeNode(AVLTreeNode parent) {
		this.otree = parent.otree;
		leaf = true;
		this.parent = parent;
		right = null;
		left = null;
		height = 0;
	}
	
	public AVLTreeNode getRoot() {
		return otree.getRoot();
	}
	
	public void insertM(int i) {
		System.out.println("Inseting: " + i);
		if (leaf) {
			leaf = false;
			right = new AVLTreeNode(this);
			left = new AVLTreeNode(this);
			key = i;
			height++;
			// Retracing
			retrace();
		} else {
			if (i > key) {
				right.insertM(i);
			} else {
				left.insertM(i);
			}
		}
	}
	
	public AVLTreeNode getParent() {
		return parent;
	}
	
	private void retrace() {
		AVLTreeNode padre = parent;
		
		while (padre != null) {
			System.out.println("Izq: " + Math.abs(padre.left.height));
			System.out.println("Rig: " + Math.abs(padre.right.height));
			System.out.println("YO: " + padre.key);
			int originalHeight = padre.height;
			padre.height = Math.max(padre.left.height, padre.right.height) + 1;
			// PUede que mantenga su altura. Lo relevante es que su factor de balance no varie
			// Ahi puedo hacer break
			if (originalHeight == padre.height && Math.abs(padre.left.height - padre.right.height) < 2) {
				break;
			}
			if (Math.abs(padre.left.height - padre.right.height) == 2) {

				if (padre.right.height > padre.left.height) {
					// Estoy desbalanceado hacia la derecha
					if (padre.right.right.height >= padre.right.left.height) {
						// Desbalanceado doble derecha
						padre.right.rotateLeft();
					} else {
						// Desbalanceado derecha izquierda
						padre.right.left.rotateRight();
						padre.right.rotateLeft();
					}
				} else {
					// Estoy desbalanceado hacia la izquierda
					if (padre.left.left.height >= padre.left.right.height) {
						// Desbalanceado doble izquierda
						padre.left.rotateRight();
					} else {
						// Desbalanceado izquierda derecha
						padre.left.right.rotateLeft();
						padre.left.rotateRight();
					}
				}
				padre = padre.parent.parent;
			} else {
				padre = padre.parent;
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
	
	private void retraceFromBrother() {
		if (this.parent != null) {
			if (this.parent.left == this) {
				this.parent.right.mretrace();
			} else {
				this.parent.left.mretrace();
			}
		}
	}
	
	private void mretrace() {
		AVLTreeNode padre = parent;
		
		while (padre != null) {
			System.out.println("Izq: " + Math.abs(padre.left.height));
			System.out.println("Rig: " + Math.abs(padre.right.height));
			System.out.println("YO: " + padre.key);
			int originalHeight = padre.height;
			padre.height = Math.max(padre.left.height, padre.right.height) + 1;
			// PUede que mantenga su altura. Lo relevante es que su factor de balance no varie
			// Ahi puedo hacer break
			if (originalHeight == padre.height && Math.abs(padre.left.height - padre.right.height) < 2) {
				//break;
			}
			if (Math.abs(padre.left.height - padre.right.height) == 2) {
				if (padre.right.height > padre.left.height) {
					// Estoy desbalanceado hacia la derecha
					if (padre.right.right.height >= padre.right.left.height) {
						// Desbalanceado doble derecha
						System.out.println("Rotate right right");
						padre.right.rotateLeft();
					} else {
						// Desbalanceado derecha izquierda
						System.out.println("Rotate right left");
						padre.right.left.rotateRight();
						padre.right.rotateLeft();
					}
				} else {
					// Estoy desbalanceado hacia la izquierda
					if (padre.left.left.height >= padre.left.right.height) {
						// Desbalanceado doble izquierda
						System.out.println("Rotate left left");
						padre.left.rotateRight();
					} else {
						// Desbalanceado izquierda derecha
						System.out.println("Rotate left right");
						padre.left.right.rotateLeft();
						padre.left.rotateRight();
					}
				}
				padre = padre.parent.parent;
			} else {
				padre = padre.parent;
			}
			
		}
		
	}

	public void deleteM(int i) {
		System.out.println("try delete: " + i);
		if (leaf)
			return;
		System.out.println("Deleting: " + i);
		if (key == i) {
			if (right.leaf == true && left.leaf == true) {
				leaf = true;
				height = 0;
				key = 0;
				right = null;
				left = null;
				// Debo empezar a hacer retrace desde mi hermano
				retraceFromBrother();
				return;
			}
			if (right.leaf == true || left.leaf == true) {
				if (left.leaf == false) {
					AVLTreeNode auxLeft = left;
					right = null;
					key = auxLeft.key;
					left = auxLeft.left;
					right = auxLeft.right;
					auxLeft.left.parent = this;
					auxLeft.right.parent = this;
					auxLeft = null;
					height = 1;
					retraceFromBrother();
				} else {
					AVLTreeNode auxRight = right;
					left = null;
					key = auxRight.key;
					left = auxRight.left;
					right = auxRight.right;	
					auxRight.left.parent = this;
					auxRight.right.parent = this;
					auxRight = null;
					height = 1;
					retraceFromBrother();
				}
			} else {
				AVLTreeNode next = findNextM(true);
				key = next.key;
				next.deleteM(key);
			}
		} else if (i > key) {
			right.deleteM(i);
		} else {
			left.deleteM(i);
		}
	}
	
	private AVLTreeNode findNextM(boolean start) {
		if (start) {
			return right.findNextM(false);
		}
		if (left.leaf)
			return this;
		return left.findNextM(false);
	}

	
	public int getHeight() {
		return height;
	}
	
	private void rotateLeft() {
		// Si soy la raiz no puedo rotar
		if (this.parent != null) {
			// Ponemos al padre como hijo izquierdo
			AVLTreeNode padreOriginal = this.parent;
			AVLTreeNode abueloOriginal = this.parent.parent;
			padreOriginal.right = this.left;
			this.left.parent = padreOriginal;
			padreOriginal.parent = this;
			this.left = padreOriginal;
			// Actualizamos alturas
			padreOriginal.height = Math.max(padreOriginal.left.height, padreOriginal.right.height) + 1;
			this.height = Math.max(left.height, right.height) + 1;
			if (abueloOriginal != null) {
				// Me pongo a mi como hijo del abuelo
				if (abueloOriginal.right == padreOriginal) {
					abueloOriginal.right = this;
				} else {
					abueloOriginal.left = this;
				}
				this.parent = abueloOriginal;
				abueloOriginal.height = Math.max(abueloOriginal.left.height, abueloOriginal.right.height) + 1;
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
			AVLTreeNode padreOriginal = this.parent;
			AVLTreeNode abueloOriginal = this.parent.parent;
			padreOriginal.left = this.right;
			this.right.parent = padreOriginal;
			padreOriginal.parent = this;
			this.right = padreOriginal;
			// Actualizamos alturas
			padreOriginal.height = Math.max(padreOriginal.left.height, padreOriginal.right.height) + 1;
			this.height = Math.max(left.height, right.height) + 1;
			if (abueloOriginal != null) {
				// Me pongo a mi como hijo del abuelo
				if (abueloOriginal.right == padreOriginal) {
					abueloOriginal.right = this;
				} else {
					abueloOriginal.left = this;
				}
				this.parent = abueloOriginal;
				abueloOriginal.height = Math.max(abueloOriginal.left.height, abueloOriginal.right.height) + 1;
			} else {
				System.out.println("Cambio root rright");
				otree.setRoot(this);
				this.parent = null;
			}
		}
	}
	
	@Override
	public boolean isLeaf() {
		return leaf;
	}
	
	@Override
	public AVLTreeNode getRight() {
		return right;
	}
	
	@Override
	public AVLTreeNode getLeft() {
		return left;
	}
	
	@Override
	public int getKey() {
		return key;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof AVLTreeNode))
			return false;
		// Cada uno es igual
		AVLTreeNode compared = (AVLTreeNode) o;
		System.out.println("Compared: " + this.key);
		if (this.height == compared.height && this.key == compared.key && this.leaf == compared.leaf) {
			if (this.right == null && this.left == null)
				return compared.right == null && compared.left == null;
			if (this.right == null)
				return compared.right == null && this.left.equals(compared.left);
			if (this.left == null)
				return compared.left == null && this.right.equals(compared.right);
			return this.left.equals(compared.left) && this.right.equals(compared.right);
		} else {
			System.out.println("Diferencia en: ");
			System.out.println("this: " + this.key);
			System.out.println("compared: " + compared.key);
			return false;
		}
	}


}
