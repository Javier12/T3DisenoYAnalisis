package test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.RandomTree;
import main.RandomTreeNode;

public class RandomAdvancedTreeTest extends BasicTreeTest {
	
	RandomTree mtree;
	
	@Before
	public void setup() {
		tree = new RandomTree();
		mtree = (RandomTree) tree;
	}
	
	
	@Test
	public void testOperacionesNivelUno() {
		super.testOperacionesNivelUno();
		checkSizes();
	}
	
	@Test
	public void testOperacionesNivelDos() {
		super.testOperacionesNivelDos();
		checkSizes();
		
	}
	
	@Test
	public void size5() {
		mtree.insert(1);
		checkSizes();
		assertTrue(mtree.getRoot().getSize() == 1);
		mtree.insert(2);
		checkSizes();
		System.out.println("Size 2: " + mtree.getRoot().getSize());
		assertTrue(mtree.getRoot().getSize() == 2);
		mtree.insert(3);
		checkSizes();
		System.out.println("Size 3: " + mtree.getRoot().getSize());
		assertTrue(mtree.getRoot().getSize() == 3);
		mtree.insert(4);
		checkSizes();
		System.out.println("Size 4: " + mtree.getRoot().getSize());
		assertTrue(mtree.getRoot().getSize() == 4);
		mtree.insert(5);
		checkSizes();
		assertTrue(mtree.getRoot().getSize() == 5);
	}
	
	@Test
	public void testOperacionesInsercion() {
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
			checkSizes();
		}
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
			checkSizes();
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
			checkSizes();
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
				checkSizes();
			} else {
				int toAdd = intPool.pop();
				intsOnTree.add(toAdd);
				assertFalse(mtree.find(toAdd));
				mtree.insert(toAdd);
				assertTrue(mtree.find(toAdd));	
				checkSizes();
			}
		}
		checkSizes();
		
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
			checkSizes();
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
			checkSizes();
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
	
	private void checkSizes() {
		// Recorre todo el arbol y verifica el invariante, recorremos iterativamente
		LinkedList<RandomTreeNode> randomTrees = new LinkedList<>();
		RandomTreeNode current = (RandomTreeNode) mtree.getRoot();
		while (!randomTrees.isEmpty() || !current.isLeaf()) {
			if (!current.isLeaf()) {
				randomTrees.push(current);
				current = current.getLeft();
			} else {
				current = randomTrees.pop();
				int sizeRight = current.getRight().getSize();
				int sizeLeft = current.getLeft().getSize();
				if (!(Math.abs(sizeRight - sizeLeft) <= 1)) {
					//System.out.println("Height right: " + heightRight);
					//System.out.println("Height left: " + heightLeft);
					//System.out.println("Mi abuelo: " + current.getParent().getParent().getKey());
					//System.out.println("Mi padre: " + current.getParent().getKey());
					//System.out.println("Yo: " + current.getKey());
					//System.out.println("Right: " + current.getRight().getKey());
					//System.out.println("Left: " + current.getLeft().getKey());
					//System.out.println("?");
				}
				System.out.println("(key,size) : " + "(" + current.getKey() + "," + current.getSize() + ")");
				System.out.println("Left size: " + sizeLeft);
				System.out.println("Right size: " + sizeRight);
				assertTrue(sizeLeft + sizeRight + 1== current.getSize());
				current = current.getRight();
			}
		}

	}

}
