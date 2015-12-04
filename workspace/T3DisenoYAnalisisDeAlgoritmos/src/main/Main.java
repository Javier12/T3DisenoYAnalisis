package main;

import trees.ABBTree;
import trees.AVLTree;

public class Main {

	public static void main(String[] args) {
		ABBTree tree = new ABBTree();
		for (int i = 0; i < 100000; i++) {
			tree.insert(i);
		}
		while (true){
		}
	}

}
