package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.AVLTree;

public class AVLTreeNodeEquality {

	@Test
	public void testBasicEquality() {
		AVLTree tree1 = new AVLTree();
		AVLTree tree2 = new AVLTree();
		
		tree1.insert(5);
		tree1.insert(1);
		tree1.insert(10);
		
		tree2.insert(5);
		tree2.insert(10);
		tree2.insert(1);
		
		assertTrue(tree1.equals(tree2));
	}
	
	@Test
	public void testEqualityWithRotation() {
		AVLTree treeNoRotation = new AVLTree();
		AVLTree treeRotation = new AVLTree();
		
		treeRotation.insert(5);
		treeRotation.insert(10);
		treeRotation.insert(7);
		
		treeNoRotation.insert(7);
		treeNoRotation.insert(5);
		treeNoRotation.insert(10);
		
		assertTrue(treeRotation.equals(treeNoRotation));
	}
	
	@Test
	public void testEqualityWeird() {
		AVLTree mtree = new AVLTree();
		mtree.insert(4517);
		mtree.insert(2245);
		mtree.insert(7616);
		mtree.insert(1066);
		mtree.insert(2878);
		mtree.insert(6791);
		mtree.insert(918);
		mtree.insert(4390);
		
		AVLTree mtree2 = new AVLTree();
		mtree2.insert(7616);
		mtree2.insert(4517);
		mtree2.insert(1066);
		mtree2.insert(6791);
		mtree2.insert(2878);
		mtree2.insert(2245);
		mtree2.insert(4390);
		mtree2.insert(918);
		System.out.println("Mtre 1 root: " + mtree.getRoot().getKey());
		System.out.println("Mtree2 root: " + mtree2.getRoot().getKey());
		assertTrue(mtree.equals(mtree2));
	}

}
