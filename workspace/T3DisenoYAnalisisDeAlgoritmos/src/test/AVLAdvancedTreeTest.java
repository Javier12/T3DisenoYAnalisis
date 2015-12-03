package test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.AVLTree;
import main.AVLTreeNode;

public class AVLAdvancedTreeTest extends BasicTreeTest {
	
	AVLTree mtree;
	
	@Before
	public void setup() {
		tree = new AVLTree();
		mtree = (AVLTree) tree;
	}
	
	
	@Test
	public void testOperacionesNivelUno() {
		super.testOperacionesNivelUno();
		checkHeights();
	}
	
	@Test
	public void testOperacionesNivelDos() {
		super.testOperacionesNivelDos();
		checkHeights();
		
	}
	
	@Test
	public void esteCasoMeBotaElAVL() {
		mtree.insert(7616);
		mtree.insert(4517);
		mtree.insert(1066);
		mtree.insert(6791);
		mtree.insert(2878);
		mtree.insert(2245);
		mtree.insert(4390);
		mtree.insert(918);
		
		assertTrue(mtree.getRoot().getKey() == 4517);
		
		mtree.delete(7616);
			
		checkHeights();
	}
	
	@Test
	public void casoQueBotaAVLSinRotacionesEnInsercion() {
		mtree.insert(4517);
		mtree.insert(2245);
		mtree.insert(7616);
		mtree.insert(1066);
		mtree.insert(2878);
		mtree.insert(6791);
		mtree.insert(918);
		mtree.insert(4390);
		
		mtree.delete(7616);
		System.out.println(mtree.getRoot().getKey());
		assertTrue(mtree.getRoot().getKey() == 2245);
		
		checkHeights();
	}
	
	@Test
	public void casoQueBotaAVLSinRotacionesEnInsercionV2() {
		mtree.insert(4517);
		mtree.insert(2245);
		mtree.insert(7616);
		mtree.insert(1066);
		mtree.insert(2878);
		mtree.insert(6791);
		mtree.insert(918);
		mtree.insert(4390);
		
		mtree.delete(6791);
		System.out.println(mtree.getRoot().getKey());
		assertTrue(mtree.getRoot().getKey() == 2245);
		
		checkHeights();
	}
	
	@Test
	public void casoQueBotaAVLSinRotacionesEnInsercionAnalogoDerecho() {
		mtree.insert(50);
		mtree.insert(25);
		mtree.insert(100);
		mtree.insert(26);
		mtree.insert(110);
		mtree.insert(90);
		mtree.insert(111);
		mtree.insert(89);
		
		mtree.delete(26);
		System.out.println(mtree.getRoot().getKey());
		assertTrue(mtree.getRoot().getKey() == 100);
		
		checkHeights();
	}
	
	
	
	@Test
	public void testOperacionesDeLaMuerte() {
		LinkedList<Integer> ints = new LinkedList<>();
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			int randomInt = random.nextInt(10000);
			if (ints.contains(randomInt))
				continue;
			ints.add(randomInt);
		}
		for (Integer mint : ints) {
			mtree.insert(mint);
			checkHeights();
		}
		@SuppressWarnings("unchecked")
		LinkedList<Integer> intsCopy = (LinkedList<Integer>) ints.clone();
		Collections.shuffle(intsCopy);
		for (Integer mint : ints) {
			assertTrue(mtree.find(mint));
		}
		LinkedList<Integer> removedInts = new LinkedList<>();
		for (Integer mint : intsCopy) {
			mtree.delete(mint);
			checkHeights();
			removedInts.add(mint);
			ints.remove(mint);
			for (Integer removedInt : removedInts) {
				assertFalse(mtree.find(removedInt));
			}
			for (Integer stillInt : ints) {
				assertTrue(mtree.find(stillInt));
			}
		}
		
	}
	
	@Test
	public void testOperacionesDeLaMuerteVHard() {
		LinkedList<Integer> intPool = new LinkedList<>();
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			int randomInt = random.nextInt(1000000);
			if (intPool.contains(randomInt))
				continue;
			intPool.add(randomInt);
		}
		
		LinkedList<Integer> intsOnTree = new LinkedList<>();
		LinkedList<Integer> intsOutOfTree = new LinkedList<>();
		
		for (int i = 0; i < 10000; i++) {
			int addOrRemove = random.nextInt(2);
			if (addOrRemove == 0) {
				if (intsOnTree.isEmpty())
					continue;
				int toRemove = intsOnTree.remove(random.nextInt(intsOnTree.size()));
				intsOutOfTree.add(toRemove);
				assertTrue(mtree.find(toRemove));
				mtree.delete(toRemove);
				assertFalse(mtree.find(toRemove));
				checkHeights();
			} else {
				int toAdd = intPool.pop();
				intsOnTree.add(toAdd);
				assertFalse(mtree.find(toAdd));
				mtree.insert(toAdd);
				assertTrue(mtree.find(toAdd));	
				checkHeights();
			}
		}
		checkHeights();
		
		for (int mint : intsOnTree) {
			assertTrue(mtree.find(mint));
		}
		
		for (int mint : intsOutOfTree) {
			assertFalse(mtree.find(mint));
		}
		
	}
	
	@Test
	public void testOperacionesDeLaSemiMuerte() {
		LinkedList<Integer> ints = new LinkedList<>();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			int randomInt = random.nextInt(10000);
			if (ints.contains(randomInt))
				continue;
			ints.add(randomInt);
		}
		for (Integer mint : ints) {
			mtree.insert(mint);
			checkHeights();
		}
		@SuppressWarnings("unchecked")
		LinkedList<Integer> intsCopy = (LinkedList<Integer>) ints.clone();
		Collections.shuffle(intsCopy);
		for (Integer mint : ints) {
			assertTrue(mtree.find(mint));
		}
		LinkedList<Integer> removedInts = new LinkedList<>();
		for (Integer mint : intsCopy) {
			mtree.delete(mint);
			checkHeights();
			removedInts.add(mint);
			ints.remove(mint);
			for (Integer removedInt : removedInts) {
				assertFalse(mtree.find(removedInt));
			}
			for (Integer stillInt : ints) {
				assertTrue(mtree.find(stillInt));
			}
		}
		
	}
	
	/**
	 * En algunos casos de borrado, es necesario realizar mas de una rotacion
	 */
	@Test
	public void dobleRotateDelete() {
		mtree.insert(50);
		mtree.insert(25);
		mtree.insert(75);
		mtree.insert(10);
		mtree.insert(30);
		mtree.insert(60);
		mtree.insert(80);
		mtree.insert(5);
		mtree.insert(15);
		mtree.insert(27);
		mtree.insert(55);
		mtree.insert(1);
		
		mtree.delete(80);
		
		assertTrue(mtree.getRoot().getKey() == 25);
		assertTrue(mtree.getRoot().getLeft().getKey() == 10);
		assertTrue(mtree.getRoot().getRight().getKey() == 50);
		assertTrue(mtree.getRoot().getHeight() == 4);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 3);
		assertTrue(mtree.getRoot().getRight().getHeight() == 3);
		
		assertTrue(mtree.getRoot().getRight().getRight().getRight().getKey() == 75);
		assertTrue(mtree.getRoot().getRight().getRight().getRight().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getRight().getRight().getRight().isLeaf());
		assertTrue(mtree.getRoot().getRight().getRight().getRight().getLeft().isLeaf());
		
		checkHeights();
	}
	
	@Test
	public void hardDelete() {
		mtree.insert(44);
		mtree.insert(17);
		mtree.insert(78);
		mtree.insert(15);
		mtree.insert(50);
		mtree.insert(88);
		mtree.insert(48);
		mtree.insert(62);
		
		mtree.delete(15);
		assertTrue(mtree.getRoot().getKey() == 50);
		assertTrue(mtree.getRoot().getLeft().getKey() == 44);
		assertTrue(mtree.getRoot().getRight().getKey() == 78);
		assertTrue(mtree.getRoot().getLeft().getLeft().getKey() == 17);
		assertTrue(mtree.getRoot().getLeft().getRight().getKey() == 48);
		assertTrue(mtree.getRoot().getRight().getLeft().getKey() == 62);
		assertTrue(mtree.getRoot().getRight().getRight().getKey() == 88);
		
		assertTrue(mtree.getRoot().getHeight() == 3);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 2);
		assertTrue(mtree.getRoot().getRight().getHeight() == 2);
		assertTrue(mtree.getRoot().getLeft().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getLeft().getRight().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getRight().getHeight() == 1);
		checkHeights();
	}
	
	@Test
	public void testDeleteRoot() {
		mtree.insert(50);
		mtree.insert(44);
		mtree.insert(78);
		mtree.delete(50);
		assertTrue(mtree.getRoot().getKey() == 78);
		assertTrue(mtree.getRoot().getLeft().getKey() == 44);
		assertTrue(mtree.getRoot().getRight().getKey() == 0);
		assertTrue(mtree.getRoot().getRight().isLeaf());
		assertTrue(mtree.getRoot().getHeight() == 2);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getHeight() == 0);
		
		assertTrue(mtree.getRoot().getLeft().getParent() == mtree.getRoot());
		assertTrue(mtree.getRoot().getRight().getParent() == mtree.getRoot());
		assertTrue(mtree.getRoot().getParent() == null);
		
		assertTrue(mtree.getRoot().getRight().getLeft() == null);
		assertTrue(mtree.getRoot().getRight().getRight() == null);
	}
	
	@Test
	public void testRightLeftDelete() {
		mtree.insert(5);
		mtree.insert(1);
		mtree.insert(10);
		mtree.insert(7);
		mtree.delete(1);
		assertFalse(mtree.find(1));
		assertTrue(mtree.getRoot().getKey() == 7);
		assertTrue(mtree.getRoot().getLeft().getKey() == 5);
		assertTrue(mtree.getRoot().getRight().getKey() == 10);
		assertTrue(mtree.getRoot().getHeight() == 2);
		assertTrue(mtree.getRoot().getRight().getHeight() == 1);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 1);
		checkHeights();
	}
	
	@Test
	public void testRightLeft() {
		mtree.insert(3);
		mtree.insert(5);
		mtree.insert(4);
		assertTrue(mtree.getRoot().getKey() == 4);
		assertTrue(mtree.getRoot().getLeft().getKey() == 3);
		assertTrue(mtree.getRoot().getRight().getKey() == 5);
		assertTrue(mtree.getRoot().getRight().getRight().isLeaf());
		assertTrue(mtree.getRoot().getRight().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getLeft().getRight().isLeaf());
		assertTrue(mtree.getRoot().getLeft().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getHeight() == 2);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getHeight() == 1);
	}
	
	@Test
	public void testRightRight() {
		mtree.insert(3);
		mtree.insert(4);
		mtree.insert(5);
		System.out.println("Root: " + mtree.getRoot().getKey());
		System.out.println("root key: " + mtree.getRoot().getKey());
		System.out.println("Left key: " + mtree.getRoot().getLeft().getKey());
		System.out.println("Right key: " + mtree.getRoot().getRight().getKey());
		assertTrue(mtree.getRoot().getKey() == 4);
		assertTrue(mtree.getRoot().getLeft().getKey() == 3);
		assertTrue(mtree.getRoot().getRight().getKey() == 5);
		assertTrue(mtree.getRoot().getRight().getRight().isLeaf());
		assertTrue(mtree.getRoot().getRight().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getLeft().getRight().isLeaf());
		assertTrue(mtree.getRoot().getLeft().getLeft().isLeaf());
		System.out.println("Root height: " + mtree.getRoot().getHeight());
		System.out.println("Left height: " + mtree.getRoot().getLeft().getHeight());
		System.out.println("Right height: " + mtree.getRoot().getRight().getHeight());
		assertTrue(mtree.getRoot().getHeight() == 2);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getHeight() == 1);
		checkHeights();
		
	}
	
	@Test
	public void testLeftRight() {
		mtree.insert(5);
		mtree.insert(3);
		mtree.insert(4);
		assertTrue(mtree.getRoot().getKey() == 4);
		System.out.println("LeftRight key left: " + mtree.getRoot().getLeft().getKey());
		assertTrue(mtree.getRoot().getLeft().getKey() == 3);
		assertTrue(mtree.getRoot().getRight().getKey() == 5);
		assertTrue(mtree.getRoot().getRight().getRight().isLeaf());
		assertTrue(mtree.getRoot().getRight().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getLeft().getRight().isLeaf());
		assertTrue(mtree.getRoot().getLeft().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getHeight() == 2);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getHeight() == 1);
	}
	
	@Test
	public void testLeftLeft() {
		mtree.insert(5);
		mtree.insert(4);
		mtree.insert(3);
		assertTrue(mtree.getRoot().getKey() == 4);
		assertTrue(mtree.getRoot().getLeft().getKey() == 3);
		assertTrue(mtree.getRoot().getRight().getKey() == 5);
		assertTrue(mtree.getRoot().getRight().getRight().isLeaf());
		assertTrue(mtree.getRoot().getRight().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getLeft().getRight().isLeaf());
		assertTrue(mtree.getRoot().getLeft().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getHeight() == 2);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getHeight() == 1);
		
	}
	
	@Test
	public void testLeftLeftRightRight() {
		mtree.insert(10);
		mtree.insert(8);
		mtree.insert(6);
		mtree.insert(11);
		mtree.insert(12);
		assertTrue(mtree.getRoot().getKey() == 8);
		assertTrue(mtree.getRoot().getLeft().getKey() == 6);
		assertTrue(mtree.getRoot().getRight().getKey() == 11);
		assertTrue(mtree.getRoot().getRight().getLeft().getKey() == 10);
		assertTrue(mtree.getRoot().getRight().getRight().getKey() == 12);
		assertTrue(mtree.getRoot().getLeft().getRight().isLeaf());
		assertTrue(mtree.getRoot().getLeft().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getRight().getRight().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getRight().getRight().getRight().isLeaf());
		assertTrue(mtree.getRoot().getRight().getLeft().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getRight().getLeft().getRight().isLeaf());
		
		assertTrue(mtree.getRoot().getHeight() == 3);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getHeight() == 2);
		assertTrue(mtree.getRoot().getRight().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getLeft().getHeight() == 1);
	}
	
	@Test
	public void testTresRotaciones() {
		mtree.insert(10);
		mtree.insert(20);
		mtree.insert(15);
		mtree.insert(40);
		mtree.insert(50);
		assertTrue(mtree.getRoot().getKey() == 15);
		assertTrue(mtree.getRoot().getLeft().getKey() == 10);
		assertTrue(mtree.getRoot().getRight().getKey() == 40);
		assertTrue(mtree.getRoot().getRight().getLeft().getKey() == 20);
		assertTrue(mtree.getRoot().getRight().getRight().getKey() == 50);
		
		assertTrue(mtree.getRoot().getRight().getLeft().getParent().getKey() == 40);
		
		mtree.insert(17);
	}
	
	@Test
	public void testLeftRightRightLeft() {
		mtree.insert(30);
		mtree.insert(20);
		mtree.insert(25);
		mtree.insert(40);
		mtree.insert(35);
		
		System.out.println("Right key: " + mtree.getRoot().getRight().getKey());
		
		assertTrue(mtree.getRoot().getKey() == 25);
		assertTrue(mtree.getRoot().getLeft().getKey() == 20);
		assertTrue(mtree.getRoot().getRight().getKey() == 35);
		assertTrue(mtree.getRoot().getRight().getLeft().getKey() == 30);
		assertTrue(mtree.getRoot().getRight().getRight().getKey() == 40);
		assertTrue(mtree.getRoot().getLeft().getRight().isLeaf());
		assertTrue(mtree.getRoot().getLeft().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getRight().getRight().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getRight().getRight().getRight().isLeaf());
		assertTrue(mtree.getRoot().getRight().getLeft().getLeft().isLeaf());
		assertTrue(mtree.getRoot().getRight().getLeft().getRight().isLeaf());
		
		assertTrue(mtree.getRoot().getHeight() == 3);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getHeight() == 2);
		assertTrue(mtree.getRoot().getRight().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getLeft().getHeight() == 1);
		
	}
	
	@Test
	public void deleteTest1() {
		mtree.insert(10);
		mtree.insert(5);
		mtree.insert(15);
		mtree.delete(10);
		assertFalse(mtree.find(10));
		assertTrue(mtree.getRoot().getKey() == 15);
		assertTrue(mtree.getRoot().getLeft().getKey() == 5);
		assertTrue(mtree.getRoot().getHeight() == 2);
		assertTrue(mtree.getRoot().getLeft().getHeight() == 1);
		assertTrue(mtree.getRoot().getRight().getHeight() == 0);
	}
	
	private void checkHeights() {
		// Recorre todo el arbol y verifica el invariante, recorremos iterativamente
		LinkedList<AVLTreeNode> avlTrees = new LinkedList<>();
		AVLTreeNode current = (AVLTreeNode) mtree.getRoot();
		while (!avlTrees.isEmpty() || !current.isLeaf()) {
			if (!current.isLeaf()) {
				avlTrees.push(current);
				current = current.getLeft();
			} else {
				current = avlTrees.pop();
				int heightRight = current.getRight().getHeight();
				int heightLeft = current.getLeft().getHeight();
				if (!(Math.abs(heightRight - heightLeft) <= 1)) {
					//System.out.println("Height right: " + heightRight);
					//System.out.println("Height left: " + heightLeft);
					//System.out.println("Mi abuelo: " + current.getParent().getParent().getKey());
					//System.out.println("Mi padre: " + current.getParent().getKey());
					//System.out.println("Yo: " + current.getKey());
					//System.out.println("Right: " + current.getRight().getKey());
					//System.out.println("Left: " + current.getLeft().getKey());
					//System.out.println("?");
				}
				assertTrue(Math.abs(heightRight - heightLeft) <= 1);
				current = current.getRight();
			}
		}

	}

}
