package test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import main.Tree;

public abstract class BasicTreeTest {
	
	Tree tree;
	
	public void setTree(Tree tree) {
		this.tree = tree;
	}
	
	public void testOperacionesNivelUno() {
		tree.insert(2);
		assertTrue(tree.find(2));
		tree.delete(2);
		assertFalse(tree.find(2));
	}
	
	public void testOperacionesNivelDos() {
		tree.insert(2);
		tree.insert(1);
		tree.insert(4);
		tree.insert(3);
		tree.insert(5);
		assertTrue(tree.find(2));
		assertTrue(tree.find(1));
		assertTrue(tree.find(4));
		assertTrue(tree.find(3));
		assertTrue(tree.find(5));
		tree.delete(4);
		assertTrue(tree.find(2));
		assertTrue(tree.find(1));
		assertTrue(tree.find(3));
		assertTrue(tree.find(5));
		assertFalse(tree.find(4));
		tree.delete(3);
		assertTrue(tree.find(2));
		assertTrue(tree.find(1));
		assertTrue(tree.find(5));
		assertFalse(tree.find(4));
		assertFalse(tree.find(3));
		tree.delete(1);
		assertTrue(tree.find(2));
		assertTrue(tree.find(5));
		assertFalse(tree.find(1));
		assertFalse(tree.find(4));
		assertFalse(tree.find(3));
		tree.delete(5);
		assertTrue(tree.find(2));
		assertFalse(tree.find(5));
		assertFalse(tree.find(1));
		assertFalse(tree.find(4));
		assertFalse(tree.find(3));
		tree.delete(2);
		assertFalse(tree.find(2));
		assertFalse(tree.find(5));
		assertFalse(tree.find(1));
		assertFalse(tree.find(4));
		assertFalse(tree.find(3));
		
	}
	
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
			tree.insert(mint);
		}
		@SuppressWarnings("unchecked")
		LinkedList<Integer> intsCopy = (LinkedList<Integer>) ints.clone();
		Collections.shuffle(intsCopy);
		for (Integer mint : ints) {
			assertTrue(tree.find(mint));
		}
		LinkedList<Integer> removedInts = new LinkedList<>();
		for (Integer mint : intsCopy) {
			tree.delete(mint);
			removedInts.add(mint);
			ints.remove(mint);
			for (Integer removedInt : removedInts) {
				assertFalse(tree.find(removedInt));
			}
			for (Integer stillInt : ints) {
				if (tree.find(stillInt) == false) {
					System.out.println("Still int: " + stillInt);
				}
				assertTrue(tree.find(stillInt));
			}
		}
	}

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
					assertTrue(tree.find(toRemove));
					tree.delete(toRemove);
					assertFalse(tree.find(toRemove));
				} else {
					int toAdd = intPool.pop();
					intsOnTree.add(toAdd);
					assertFalse(tree.find(toAdd));
					tree.insert(toAdd);
					assertTrue(tree.find(toAdd));
				}
			}
			
			for (int mint : intsOnTree) {
				assertTrue(tree.find(mint));
			}
			
			for (int mint : intsOutOfTree) {
				assertFalse(tree.find(mint));
			}
	}
	
	private Integer getMaxLinkedList(LinkedList<Integer> ints) {
		Integer toReturn = null;
		for (Integer mint : ints) {
			if (toReturn == null) {
				toReturn = mint;
			} else {
				if (toReturn < mint)
					toReturn = mint;
			}
		}
		return toReturn;
	}
	
	private Integer getMinLinkedList(LinkedList<Integer> ints) {
		Integer toReturn = null;
		for (Integer mint : ints) {
			if (toReturn == null) {
				toReturn = mint;
			} else {
				if (toReturn > mint)
					toReturn = mint;
			}
		}
		return toReturn;
	}
	
	private Integer getNextLinkedList(LinkedList<Integer> ints, int startInt) {
		Integer toReturn = null;
		for (Integer mint : ints) {
			if (toReturn == null) {
				if (mint.compareTo(startInt) > 0)
					toReturn = mint;
			} else {
				if (mint.compareTo(startInt) > 0 && mint.compareTo(toReturn) < 0)
					toReturn = mint;
			}
		}
		return toReturn;
	}
	
	private Integer getPreviousLinkedList(LinkedList<Integer> ints, int startInt) {
		Integer toReturn = null;
		for (Integer mint : ints) {
			if (toReturn == null) {
				if (mint.compareTo(startInt) < 0)
					toReturn = mint;
			} else {
				if (mint.compareTo(startInt) < 0 && mint.compareTo(toReturn) > 0)
					toReturn = mint;
			}
		}
		return toReturn;
	}
	
	
	public void testMax1() {
		tree.insert(1);
		tree.insert(10);
		tree.insert(200);
		tree.insert(100);
		tree.insert(1000);
		tree.insert(5);
		assertTrue(tree.getMax().equals(1000));
	}
	
	public void testMax2() {
		LinkedList<Integer> ints = new LinkedList<>();
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			int randomInt = random.nextInt(10000);
			if (ints.contains(randomInt))
				continue;
			ints.add(randomInt);
		}
		for (Integer mint : ints) {
			tree.insert(mint);
		}
		assertTrue(tree.getMax().equals(getMaxLinkedList(ints)));
		@SuppressWarnings("unchecked")
		LinkedList<Integer> intsCopy = (LinkedList<Integer>) ints.clone();
		Collections.shuffle(intsCopy);
		LinkedList<Integer> removedInts = new LinkedList<>();
		for (Integer mint : intsCopy) {
			tree.delete(mint);
			removedInts.add(mint);
			ints.remove(mint);
			if (tree.getMax() != null) 
				assertTrue(tree.getMax().equals(getMaxLinkedList(ints)));
			else 
				assertTrue(tree.getMax() == getMaxLinkedList(ints));
		}
	}
	
	public void testMin1() {
		tree.insert(10);
		tree.insert(1);
		tree.insert(200);
		tree.insert(100);
		tree.insert(1000);
		tree.insert(5);
		
		assertTrue(tree.getMin().equals(1));
	}
	
	public void testMin2() {
		LinkedList<Integer> ints = new LinkedList<>();
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			int randomInt = random.nextInt(10000);
			if (ints.contains(randomInt))
				continue;
			ints.add(randomInt);
		}
		for (Integer mint : ints) {
			tree.insert(mint);
		}
		assertTrue(tree.getMin().equals(getMinLinkedList(ints)));
		@SuppressWarnings("unchecked")
		LinkedList<Integer> intsCopy = (LinkedList<Integer>) ints.clone();
		Collections.shuffle(intsCopy);
		LinkedList<Integer> removedInts = new LinkedList<>();
		for (Integer mint : intsCopy) {
			tree.delete(mint);
			removedInts.add(mint);
			ints.remove(mint);
			if (tree.getMax() != null) 
				assertTrue(tree.getMin().equals(getMinLinkedList(ints)));
			else 
				assertTrue(tree.getMin() == getMinLinkedList(ints));
		}
	}
	
	public void testSucesor1() {
		tree.insert(1);
		tree.insert(10);
		tree.insert(200);
		tree.insert(100);
		tree.insert(1000);
		tree.insert(5);
		assertTrue(tree.getNext(5).equals(10));
	}
	
	public void testSucesor2() {
		LinkedList<Integer> ints = new LinkedList<>();
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			int randomInt = random.nextInt(10000);
			if (ints.contains(randomInt))
				continue;
			ints.add(randomInt);
		}
		for (Integer mint : ints) {
			tree.insert(mint);
		}
		for (Integer mint : ints) {
			if (tree.getNext(mint) != null) 
				assertTrue(tree.getNext(mint).equals(getNextLinkedList(ints, mint)));
			else 
				assertTrue(tree.getNext(mint) == getNextLinkedList(ints, mint));
		}
		@SuppressWarnings("unchecked")
		LinkedList<Integer> intsCopy = (LinkedList<Integer>) ints.clone();
		Collections.shuffle(intsCopy);
		LinkedList<Integer> removedInts = new LinkedList<>();
		for (Integer mint : intsCopy) {
			tree.delete(mint);
			removedInts.add(mint);
			ints.remove(mint);
			if (tree.getNext(mint) != null) {
				Integer nextTree = tree.getNext(mint);
				Integer nextLinked = getNextLinkedList(ints, mint);
				System.out.println("Mint: " + mint);
				System.out.println("Next tree: " + nextTree);
				System.out.println("Next Linked: " + nextLinked);
				System.out.println("Contains start tree: " + tree.find(mint));
				System.out.println("Contains start linked? " + ints.contains(mint));
				System.out.println("Linked contins next tree? : " + ints.contains(nextTree));
				assertTrue(tree.getNext(mint).equals(getNextLinkedList(ints, mint)));
			} else {
				Integer nextTree = tree.getNext(mint);
				Integer nextLinked = getNextLinkedList(ints, mint);
				System.out.println("N Mint: " + mint);
				System.out.println("N Next tree: " + nextTree);
				System.out.println("N Next Linked: " + nextLinked);
				System.out.println("N Contains start tree: " + tree.find(mint));
				System.out.println("N Contains start linked? " + ints.contains(mint));
				assertTrue(tree.getNext(mint) == getNextLinkedList(ints, mint));
			}
		}
	}
	
	public void testAntesesor1() {
		tree.insert(1);
		tree.insert(10);
		tree.insert(200);
		tree.insert(100);
		tree.insert(1000);
		tree.insert(5);
		
		assertTrue(tree.getPrevious(100).equals(10));
	}
	
	public void testAntesesor2() {
		LinkedList<Integer> ints = new LinkedList<>();
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			int randomInt = random.nextInt(10000);
			if (ints.contains(randomInt))
				continue;
			ints.add(randomInt);
		}
		for (Integer mint : ints) {
			tree.insert(mint);
		}
		for (Integer mint : ints) {
			if (tree.getPrevious(mint) != null) 
				assertTrue(tree.getPrevious(mint).equals(getPreviousLinkedList(ints, mint)));
			else 
				assertTrue(tree.getPrevious(mint) == getPreviousLinkedList(ints, mint));
		}
		@SuppressWarnings("unchecked")
		LinkedList<Integer> intsCopy = (LinkedList<Integer>) ints.clone();
		Collections.shuffle(intsCopy);
		LinkedList<Integer> removedInts = new LinkedList<>();
		for (Integer mint : intsCopy) {
			tree.delete(mint);
			removedInts.add(mint);
			ints.remove(mint);
			if (tree.getPrevious(mint) != null) 
				assertTrue(tree.getPrevious(mint).equals(getPreviousLinkedList(ints, mint)));
			else 
				assertTrue(tree.getPrevious(mint) == getPreviousLinkedList(ints, mint));
		}
	}

}
