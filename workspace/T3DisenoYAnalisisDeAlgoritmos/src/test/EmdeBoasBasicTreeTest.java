package test;

import org.junit.Before;
import org.junit.Test;

import trees.VanEmdeBoasTree;


public class EmdeBoasBasicTreeTest extends BasicTreeTest {

	@Before
	public void setUp() {
		try {
			tree = new VanEmdeBoasTree(1048576);
		} catch (Exception e) {
			System.out.println("Error generando emde boas: " + e.toString());
			e.printStackTrace();
		}
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
	
	@Test
	public void testOperacionedDeLaMuerteVHard() {
		super.testOperacionesDeLaMuerteVHard();
	}

}
