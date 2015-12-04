package test;

import static org.junit.Assert.*;

import org.junit.Test;

import trees.VanEmdeBoasTree;

public class EmdeBoasAdvancedTreeTest {

	VanEmdeBoasTree mtree;
	
	
	@Test
	public void testOperacionInsercionBorradoResincercion() throws Exception {
		mtree = new VanEmdeBoasTree(8);
		mtree.insert(0);
		mtree.insert(1);
		mtree.insert(2);
		mtree.insert(3);
		mtree.insert(4);
		mtree.insert(5);
		mtree.insert(6);
		mtree.insert(7);
		assertTrue(mtree.find(0));
		assertTrue(mtree.find(1));
		assertTrue(mtree.find(2));
		assertTrue(mtree.find(3));
		assertTrue(mtree.find(4));
		assertTrue(mtree.find(5));
		assertTrue(mtree.find(6));
		assertTrue(mtree.find(7));
		mtree.delete(1);
		assertFalse(mtree.find(1));
		assertTrue(mtree.find(4));
		assertTrue(mtree.find(0));
		assertTrue(mtree.find(2));
		assertTrue(mtree.find(3));
		assertTrue(mtree.find(5));
		assertTrue(mtree.find(6));
		assertTrue(mtree.find(7));
	}
	
	@Test
	public void testOperacionInsercionBorradoResincercion2() throws Exception {
		mtree = new VanEmdeBoasTree(1048576);
		mtree.insert(0);
		mtree.insert(1);
		mtree.insert(2);
		mtree.insert(3);
		mtree.insert(4);
		mtree.insert(5);
		mtree.insert(6);
		mtree.insert(7);
		assertTrue(mtree.find(0));
		assertTrue(mtree.find(1));
		assertTrue(mtree.find(2));
		assertTrue(mtree.find(3));
		assertTrue(mtree.find(4));
		assertTrue(mtree.find(5));
		assertTrue(mtree.find(6));
		assertTrue(mtree.find(7));
		mtree.delete(1);
		assertFalse(mtree.find(1));
		assertTrue(mtree.find(4));
		assertTrue(mtree.find(0));
		assertTrue(mtree.find(2));
		assertTrue(mtree.find(3));
		assertTrue(mtree.find(5));
		assertTrue(mtree.find(6));
		assertTrue(mtree.find(7));
	}
	
	@Test
	public void testOperacionInsercionBorradoResincercion3() throws Exception {
		mtree = new VanEmdeBoasTree(1048576);
		int toAdd = 1000000;
		assertFalse(mtree.find(toAdd));
		mtree.insert(toAdd);
		assertTrue(mtree.find(toAdd));
	}
	
	@Test
	public void testBorradoInexistente() throws Exception {
		mtree = new VanEmdeBoasTree(1048576);
		mtree.insert(100);
		assertTrue(mtree.find(100));
		mtree.delete(101);
		assertTrue(mtree.find(100));
		assertFalse(mtree.find(99));
	}
	
}
