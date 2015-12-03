package test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.SplayTree;

public class SplayAdvancedTreeTest {

	SplayTree mtree;
	
	@Before
	public void setup() {
		mtree = new SplayTree();
	}
	
	@Test
	public void insertionTest() {
		mtree.insert(5);
		assertTrue(mtree.getRoot().getKey() == 5);
		mtree.insert(15);
		assertTrue(mtree.getRoot().getKey() == 15);
		assertTrue(mtree.getRoot().getLeft().getKey() == 5);
		assertTrue(mtree.getRoot().getRight().getParent().getKey() == 15);
		mtree.insert(10);
		assertTrue(mtree.getRoot().getKey() == 10);
		assertTrue(mtree.getRoot().getRight().getKey() == 15);
		assertTrue(mtree.getRoot().getLeft().getKey() == 5);
	}
	
	@Test
	public void testEmpty() {
		mtree.insert(5);
		mtree.delete(5);
		assertFalse(mtree.find(5));
		mtree.insert(10);
		assertTrue(mtree.find(10));
	}
	
	@Test
	public void zig() {
		mtree.insert(5);
		mtree.insert(15);
		mtree.insert(25);
		mtree.insert(35);
		mtree.insert(10);
		mtree.insert(30);
		mtree.insert(20);
		
		/* 		
		System.out.println(mtree.getRoot().getKey());
		System.out.println(mtree.getRoot().getRight().getKey());
		System.out.println(mtree.getRoot().getLeft().getKey());
		System.out.println(mtree.getRoot().getRight().getRight().getKey());
		System.out.println(mtree.getRoot().getRight().getLeft().getKey());
		System.out.println(mtree.getRoot().getLeft().getRight().getKey());
		System.out.println(mtree.getRoot().getLeft().getLeft().getKey());
		 */
		
		assertTrue(mtree.find(10));
		assertTrue(mtree.getRoot().getKey() == 10);
		assertTrue(mtree.getRoot().getRight().getKey() == 20);
		assertTrue(mtree.getRoot().getRight().getLeft().getKey() == 15);
		
	}
	
	@Test
	public void zag() {
		mtree.insert(5);
		mtree.insert(15);
		mtree.insert(25);
		mtree.insert(35);
		mtree.insert(10);
		mtree.insert(30);
		mtree.insert(20);
		
		assertTrue(mtree.find(30));
		assertTrue(mtree.getRoot().getKey() == 30);
		assertTrue(mtree.getRoot().getLeft().getKey() == 20);
		assertTrue(mtree.getRoot().getLeft().getRight().getKey() == 25);
	}
	
	@Test
	public void zigzig() {
		mtree.insert(5);
		mtree.insert(15);
		mtree.insert(25);
		mtree.insert(35);
		mtree.insert(10);
		mtree.insert(30);
		mtree.insert(20);
		
		assertTrue(mtree.find(5));
		assertTrue(mtree.getRoot().getKey() == 5);
		assertTrue(mtree.getRoot().getRight().getKey() == 10);
		assertTrue(mtree.getRoot().getRight().getRight().getKey() == 20);
		assertTrue(mtree.getRoot().getRight().getRight().getLeft().getKey() == 15);
	}
	
	@Test
	public void zigzag() {
		mtree.insert(5);
		mtree.insert(15);
		mtree.insert(25);
		mtree.insert(35);
		mtree.insert(10);
		mtree.insert(30);
		mtree.insert(20);
		
		assertTrue(mtree.find(15));
		assertTrue(mtree.getRoot().getKey() == 15);
		assertTrue(mtree.getRoot().getRight().getKey() == 20);
		assertTrue(mtree.getRoot().getLeft().getKey() == 10);
	}
	
	@Test
	public void zagzag() {
		mtree.insert(5);
		mtree.insert(15);
		mtree.insert(25);
		mtree.insert(35);
		mtree.insert(10);
		mtree.insert(30);
		mtree.insert(20);
		
		assertTrue(mtree.find(35));
		assertTrue(mtree.getRoot().getKey() == 35);
		assertTrue(mtree.getRoot().getLeft().getKey() == 30);
		assertTrue(mtree.getRoot().getLeft().getLeft().getKey() == 20);
		assertTrue(mtree.getRoot().getLeft().getLeft().getRight().getKey() == 25);
	}
	
	@Test
	public void zagzig() {
		mtree.insert(5);
		mtree.insert(15);
		mtree.insert(25);
		mtree.insert(35);
		mtree.insert(10);
		mtree.insert(30);
		mtree.insert(20);
		
		assertTrue(mtree.find(25));
		assertTrue(mtree.getRoot().getKey() == 25);
		assertTrue(mtree.getRoot().getRight().getKey() == 30);
		assertTrue(mtree.getRoot().getLeft().getKey() == 20);
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
			System.out.println(mint);
			assertTrue(mtree.getRoot().getKey() == mint);
		}
		@SuppressWarnings("unchecked")
		LinkedList<Integer> intsCopy = (LinkedList<Integer>) ints.clone();
		Collections.shuffle(intsCopy);
		for (Integer mint : ints) {
			assertTrue(mtree.find(mint));
			assertTrue(mtree.getRoot().getKey() == mint);
		}
		LinkedList<Integer> removedInts = new LinkedList<>();
		for (Integer mint : intsCopy) {
			mtree.delete(mint);
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

}
