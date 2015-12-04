package test;

import org.junit.Before;
import org.junit.Test;

import trees.AVLTree;

public class AVLBasicTreeTest extends BasicTreeTest {

	@Before
	public void setUp() {
		tree = new AVLTree();
	}
	
	@Test
	public void testOperacionesNivelUno() {
		super.testOperacionesNivelUno();
	}
	
	@Test
	public void testOperacionesNivelDos() {
		super.testOperacionesNivelDos();
	}
	
	@Test
	public void testOperacionesDeLaMuerte() {
		super.testOperacionesDeLaMuerte();
	}
	
	@Test
	public void testOperacionesDeLaMuerteVHard() {
		super.testOperacionesDeLaMuerteVHard();
	}
	
	@Test
	public void testMax1() {
		super.testMax1();
	}
	
	@Test
	public void testMax2() {
		super.testMax2();
	}
	
	@Test
	public void testMin1() {
		super.testMin1();
	}
	
	@Test
	public void testMin2() {
		super.testMin2();
	}
	
	@Test
	public void testSucesor1() {
		super.testSucesor1();
	}
	
	@Test
	public void testSucesor2() {
		super.testSucesor2();
	}
	
	@Test
	public void testAntecesor1() {
		super.testAntesesor1();
	}
	
	@Test
	public void testAntesesor2() {
		super.testAntesesor2();
	}

}
