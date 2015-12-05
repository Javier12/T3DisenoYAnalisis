package main;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import trees.ABBTree;
import trees.AVLTree;
import trees.SplayTree;
import trees.Tree;
import trees.TreeTimeMeasurer;
import trees.TreeTimeMeasurer.TuplaTimeOperationResult;
import trees.VanEmdeBoasTree;

public class Main {
	
	private static final int ABB = 0;
	private static final int AVL = 1;
	private static final int SPL = 2;
	private static final int VEB = 3;

	public static void main(String[] args) throws Exception {
		//----------------
		//----Prueba 1----
		//----------------
		/*
		System.out.println("Probando ABB universo 2^21");
		testStack(0, (int) Math.pow(2, 21), ABB);
		System.out.println("Probando AVL universo 2^21");
		testStack(0, (int) Math.pow(2, 21), AVL);
		System.out.println("Probando Splay universo 2^21");
		testStack(0, (int) Math.pow(2, 21), SPL);
		System.out.println("Probando VanEmdeBoas universo 2^21");
		testStack(0, (int) Math.pow(2, 21), VEB);
		
		System.out.println("Probando ABB universo 2^25");
		testStack(0, (int) Math.pow(2, 25), ABB);
		System.out.println("Probando AVL universo 2^25");
		testStack(0, (int) Math.pow(2, 25), AVL);
		System.out.println("Probando Splay universo 2^25");
		testStack(0, (int) Math.pow(2, 25), SPL);
		System.out.println("Probando VanEmdeBoas universo 2^25");
		testStack(0, (int) Math.pow(2, 25), VEB);
		
		//----------------
		//----Prueba 2----
		//----------------
		System.out.println("Probando Segunda ABB universo 2^21");
		testSegunda(0, (int) Math.pow(2, 21), ABB);
		System.out.println("Probando Segunda AVL universo 2^21");
		testSegunda(0, (int) Math.pow(2, 21), AVL);
		System.out.println("Probando Segunda Splay universo 2^21");
		testSegunda(0, (int) Math.pow(2, 21), SPL);
		System.out.println("Probando Segunda VanEmdeBoas universo 2^21");
		testSegunda(0, (int) Math.pow(2, 21), VEB);
		
		System.out.println("Probando Segunda ABB universo 2^25");
		testSegunda(0, (int) Math.pow(2, 25), ABB);
		System.out.println("Probando Segunda AVL universo 2^25");
		testSegunda(0, (int) Math.pow(2, 25), AVL);
		System.out.println("Probando Segunda Splay universo 2^25");
		testSegunda(0, (int) Math.pow(2, 25), SPL);
		System.out.println("Probando Segunda VanEmdeBoas universo 2^25");
		testSegunda(0, (int) Math.pow(2, 25), VEB);
		*/
		//----------------
		//----Prueba 3----
		//----------------
		System.out.println("Probando Tercera ABB universo 2^21");
		testTercera(0, (int) Math.pow(2, 21), ABB);
		System.out.println("Probando Tercera AVL universo 2^21");
		testTercera(0, (int) Math.pow(2, 21), AVL);
		System.out.println("Probando Tercera Splay universo 2^21");
		testTercera(0, (int) Math.pow(2, 21), SPL);
		System.out.println("Probando Tercera VanEmdeBoas universo 2^21");
		testTercera(0, (int) Math.pow(2, 21), VEB);
		System.out.println("Probando Tercera ABB universo 2^25");
		testTercera(0, (int) Math.pow(2, 25), ABB);
		System.out.println("Probando Tercera AVL universo 2^25");
		testTercera(0, (int) Math.pow(2, 25), AVL);
		System.out.println("Probando Tercera Splay universo 2^25");
		testTercera(0, (int) Math.pow(2, 25), SPL);
		System.out.println("Probando Tercera VanEmdeBoas universo 2^25");
		testTercera(0, (int) Math.pow(2, 25), VEB);
		
		
		while (true) ;
	}
	
	/**
	 * Prueba el los arboles para la secuencia tipo semi stack: inseciones semi secuenciales, max min next y previous, buscar random, borrar semi desecuancial.
	 * @param universoStart
	 * @param universoEnd
	 * @param tree El tree a probar. 0 ABB, 1 AVL, 2 Splay, 3 Van Emde Boas
	 * @throws Exception  Si el universoEnd no es una potencia de dos
	 */
	public static void testPrimera(int universoStart, int universoEnd, int tree) throws Exception {
		Random r = new Random();
		long s = System.nanoTime();
		TreeTimeMeasurer treeMeasurer = new TreeTimeMeasurer(getTreeForInt(tree, universoEnd));
		long e = System.nanoTime();
		long insertResults = 0;
		long sucesorResults = 0;
		long antesesorResults = 0;
		long maxResult = 0;
		long minResult = 0;
		long searchResults = 0;
		long deleteResults = 0;
		
		
		// Se insertaran bloques con un tamano de el 10% del universo
		
		
		// Insercion
		int index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			insertResults = insertResults + treeMeasurer.measureInsert(i);
			index++;
		}
		System.out.println("Insercion terminado");
		// Sucesor
		index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			sucesorResults = sucesorResults + treeMeasurer.measureNext(i);
			index++;
		}
		System.out.println("Sucesor terminado");
		// Antesesor
		index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			antesesorResults = antesesorResults + treeMeasurer.measureInsert(i);
			index++;
		}
		
		maxResult = treeMeasurer.measureMax();
		System.out.println("Maximo terminado");
		minResult = treeMeasurer.measureMin();
		System.out.println("Minimo terminado");
		
		
		System.out.println("Antesesor terminado");
		// Busqueda
		for (int i = 0; i <= (universoEnd - universoStart)*5; i++) {
			int buscar = r.nextInt(universoEnd) + universoStart;
			searchResults = searchResults + treeMeasurer.measureFind(buscar).getTime();
		}
		System.out.println("Busqueda terminada");
		// Borrado
		index = 0;
		for (int i = universoEnd; i >= universoStart; i--) {
			deleteResults = deleteResults + treeMeasurer.measureDelete(i).getTime();
			index++;
		}
		System.out.println("Borrado terminado");
		PrintWriter writer = new PrintWriter("results_test_1_universo_" + (universoEnd - universoStart + 1) + "_" + getStringTreeForInt(tree) + ".txt", "UTF-8");
		writer.println("Tiempo inicializado: " + (e - s));
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") insercion: " + insertResults);
		writer.println("Tiempo buscar maximo: " + maxResult);
		writer.println("Tiempo buscar minimo: " + minResult);
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar sucesor: " + sucesorResults);
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar antesesor: " + antesesorResults);
		writer.println("Tiempos (sum " + ((universoEnd - universoStart)*5) + " ) busqueda: " + searchResults);
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") borrado:" + deleteResults);
		writer.close();
		
	}
	
	/**
	 * Pureba aleatoria
	 * @param universoStart
	 * @param universoEnd
	 * @param tree
	 * @throws Exception
	 */
	public static void testSegunda(int universoStart, int universoEnd, int tree) throws Exception {
		Random r = new Random();
		Tree copyTree = getTreeForInt(tree, universoEnd);
		long s = System.nanoTime();
		TreeTimeMeasurer treeMeasurer = new TreeTimeMeasurer(getTreeForInt(tree, universoEnd));
		long e = System.nanoTime();
		long insertResults = 0;
		long sucesorResults = 0;
		long antesesorResults = 0;
		long maxResult = 0;
		long minResult = 0;
		long succesfullSearchResults = 0;
		long unsuccesfullSearchResults = 0;
		long succesfullDeleteResults = 0;
		long unsuccesfullDeleteResults = 0;
		int succesfullSearches = 0;
		int unsuccesfullSearches = 0;
		int succesfullDeletes = 0;
		int unsuccesfullDeletes = 0;
		
		
		// Insercion
		int insertionIndex = 0;
		while (insertionIndex < (int) ((universoEnd - universoStart + 1) * 0.75)) {
			int insertar = r.nextInt(universoEnd) + universoStart;
			if (copyTree.find(insertar))
				continue;
			insertResults = insertResults + treeMeasurer.measureInsert(insertar);
			copyTree.insert(insertar);
			insertionIndex++;
		}
		System.out.println("Insercion terminado");
		// Sucesor
		int index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			sucesorResults = sucesorResults + treeMeasurer.measureNext(i);
			index++;
		}
		System.out.println("Sucesor terminado");
		// Antesesor
		index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			antesesorResults = antesesorResults + treeMeasurer.measureInsert(i);
			index++;
		}	
		System.out.println("Antesesor terminado");
		maxResult = treeMeasurer.measureMax();
		System.out.println("Maximo terminado");
		minResult = treeMeasurer.measureMin();
		System.out.println("Minimo terminado");
		
		// Busqueda y borrado
		for (int i = 0; i <= (universoEnd - universoStart)*10; i++) {
			int buscarOBorrar = r.nextInt(universoEnd) + universoStart;
			int decidor = r.nextInt(2);
			if (decidor == 0) {
				TuplaTimeOperationResult ttor = treeMeasurer.measureFind(buscarOBorrar);
				if (ttor.getOperationResult()) {
					succesfullSearchResults = succesfullSearchResults + ttor.getTime(); 
					succesfullSearches++;
				} else {
					unsuccesfullSearchResults = unsuccesfullSearchResults + ttor.getTime();
					unsuccesfullSearches++;
				}
			} else {
				TuplaTimeOperationResult ttor = treeMeasurer.measureDelete(buscarOBorrar);
				if (ttor.getOperationResult()) {
					succesfullDeleteResults = succesfullDeleteResults + ttor.getTime(); 
					succesfullDeletes++;
				} else {
					unsuccesfullDeleteResults = unsuccesfullDeleteResults + ttor.getTime();
					unsuccesfullDeletes++;
				}
			}
		}
		System.out.println("Busqueda y borrado terminada");

		PrintWriter writer = new PrintWriter("results_test_2_universo_" + (universoEnd - universoStart + 1) + "_" + getStringTreeForInt(tree) + ".txt", "UTF-8");
		writer.println("Tiempo inicializado: " + (e - s));
		writer.println("Tiempos (sum " + insertionIndex + ") insercion: " + insertResults);
		writer.println("Tiempo buscar maximo: " + maxResult);
		writer.println("Tiempo buscar minimo: " + minResult);
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar sucesor: " + sucesorResults);
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar antesesor: " + antesesorResults);
		writer.println("Tiempos (sum " + succesfullSearches + " ) busqueda succesfull: " + succesfullSearchResults);
		writer.println("Tiempos (sum " + unsuccesfullSearches + " ) busqueda unsuccesfull: " + unsuccesfullSearchResults);
		writer.println("Tiempos (sum " + succesfullDeletes + ") borrado succesfull:" + succesfullDeleteResults);
		writer.println("Tiempos (sum " + unsuccesfullDeletes + ") borrado:" + unsuccesfullDeleteResults);
		writer.close();
	}
	
	/**
	 * Prueba cuando hay un sesgo de elementos hot, normal y low
	 * @param universoStart
	 * @param universoEnd
	 * @param tree
	 * @throws Exception
	 */
	public static void testTercera(int universoStart, int universoEnd, int tree) throws Exception {
		Random r = new Random();
		long s = System.nanoTime();
		TreeTimeMeasurer treeMeasurer = new TreeTimeMeasurer(getTreeForInt(tree, universoEnd));
		long e = System.nanoTime();
		long insertResults = 0;
		long sucesorResults = 0;
		long antesesorResults = 0;
		long maxResult = 0;
		long minResult = 0;
		long hotSearchResults = 0;
		long normalSearchResults = 0;
		long lowSearchResults = 0;
		long succesfullHotDeleteResults = 0;
		long unsuccesfullHotDeleteResults = 0;
		long succesfullNormalDeleteResults = 0;
		long unsuccesfullNormalDeleteResults = 0;
		long succesfullLowDeleteResults = 0;
		long unsuccesfullLowDeleteResults = 0;
		
		LinkedList<Integer> shuffleInsert = new LinkedList<>();
		for (int i = universoStart; i <= universoEnd; i++) {
			shuffleInsert.add(i);
		}
		Collections.shuffle(shuffleInsert);
		
		int index = 0;
		for (Integer mint : shuffleInsert) {
			insertResults = insertResults + treeMeasurer.measureInsert(mint);
			index++;
		}
		System.out.println("Terminado insert");
		
		// Definimos bloques mas activos para busqueda y borrado
		int shuffleSize = shuffleInsert.size();
		int[] hot = new int[shuffleSize];
		int[] normal = new int[shuffleSize];
		int[] low = new int[shuffleSize];
		int hotInsertCounter = 0;
		int normalInsertCounter = 0;
		int lowInsertCounter = 0;
		int counter = 0;
		for (Integer mint : shuffleInsert) {
			if (counter > shuffleSize*0.99) {
				hot[hotInsertCounter] = mint;
				hotInsertCounter++;
			} else if (counter > shuffleSize*0.95) {
				normal[normalInsertCounter] = mint;
				normalInsertCounter++;
			} else {
				low[lowInsertCounter] = mint;
				lowInsertCounter++;
			}
			counter++;
		}
		
		int hotSearchCounter = 0;
		int normalSearchCounter = 0;
		int lowSearchCounter = 0;
		// Busqueda
		for (int i = 0; i <= (universoEnd - universoStart)*10; i++) {
			int hotNormalLow = r.nextInt(1000);
			if (hotNormalLow <= 985) {
				int toSearch = hot[r.nextInt(hotInsertCounter)];
				hotSearchResults = hotSearchResults + treeMeasurer.measureFind(toSearch).getTime();
				hotSearchCounter++;
			} else if (hotNormalLow <= 990) {
				int toSearch = normal[r.nextInt(normalInsertCounter)];
				normalSearchResults = normalSearchResults + treeMeasurer.measureFind(toSearch).getTime();
				normalSearchCounter++;
			} else {
				int toSearch = low[r.nextInt(lowInsertCounter)];
				lowSearchResults = lowSearchResults + treeMeasurer.measureFind(toSearch).getTime();
				lowSearchCounter++;
			}
		}
		System.out.println("Busqueda terminada");
		// Borrado;
		int hotSDeleteCounter = 0;
		int hotFDeleteCounter = 0;
		int normalSDeleteCounter = 0;
		int normalFDeleteCounter = 0;
		int lowSDeleteCounter = 0;
		int lowFDeleteCounter = 0;
		for (int i = 0; i <= (universoEnd - universoStart)*10; i++) {
			int hotNormalLow = r.nextInt(1000);
			if (hotNormalLow <= 985) {
				int toDelete = hot[r.nextInt(hotInsertCounter)];
				TuplaTimeOperationResult tupla = treeMeasurer.measureDelete(toDelete);
				if (tupla.getOperationResult()) {
					succesfullHotDeleteResults = succesfullHotDeleteResults + tupla.getTime();
					hotSDeleteCounter++;
				} else {
					unsuccesfullHotDeleteResults = unsuccesfullHotDeleteResults + tupla.getTime();
					hotFDeleteCounter++;
				}
			} else if (hotNormalLow <= 990) {
				int toDelete = normal[r.nextInt(normalInsertCounter)];
				TuplaTimeOperationResult tupla = treeMeasurer.measureDelete(toDelete);
				if (tupla.getOperationResult()) {
					succesfullNormalDeleteResults = succesfullNormalDeleteResults + tupla.getTime();
					normalSDeleteCounter++;
				} else {
					unsuccesfullNormalDeleteResults = unsuccesfullNormalDeleteResults + tupla.getTime();
					normalFDeleteCounter++;
				}
			} else {
				int toDelete = low[r.nextInt(lowInsertCounter)];
				TuplaTimeOperationResult tupla = treeMeasurer.measureDelete(toDelete);
				if (tupla.getOperationResult()) {
					succesfullLowDeleteResults = succesfullLowDeleteResults + tupla.getTime();
					lowSDeleteCounter++;
				} else {
					unsuccesfullLowDeleteResults = unsuccesfullLowDeleteResults + tupla.getTime();
					lowFDeleteCounter++;
				}
			}
		}
		System.out.println("Borrado terminado");
		// Sucesor
		index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			sucesorResults = sucesorResults + treeMeasurer.measureNext(i);
			index++;
		}
		System.out.println("Sucesor terminado");
		// Antesesor
		index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			antesesorResults = antesesorResults + treeMeasurer.measureInsert(i);
			index++;
		}	
		System.out.println("Antesesor terminado");
		maxResult = treeMeasurer.measureMax();
		System.out.println("Maximo terminado");
		minResult = treeMeasurer.measureMin();
		System.out.println("Minimo terminado");
		
		
		
		PrintWriter writer = new PrintWriter("results_test_3_universo_" + (universoEnd - universoStart + 1) + "_" + getStringTreeForInt(tree) + ".txt", "UTF-8");
		writer.println("Tiempo inicializado: " + (e - s));
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") insercion: " + insertResults);
		writer.println("Tiempo buscar maximo: " + maxResult);
		writer.println("Tiempo buscar minimo: " + minResult);
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar sucesor: " + sucesorResults);
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar antesesor: " + antesesorResults);
		
		writer.println("Tiempos (sum " + hotSearchCounter + " ) busqueda hot: " + hotSearchResults);
		writer.println("Tiempos (sum " + normalSearchCounter + " ) busqueda normal: " + normalSearchResults);
		writer.println("Tiempos (sum " + lowSearchCounter + " ) busqueda low: " + lowSearchResults);
		
		
		writer.println("Tiempos (sum " + hotSDeleteCounter + ") borrado succesfull hot:" + succesfullHotDeleteResults);
		writer.println("Tiempos (sum " + hotFDeleteCounter + ") borrado unsuccefull hot:" + unsuccesfullHotDeleteResults);
		
		writer.println("Tiempos (sum " + normalSDeleteCounter + ") borrado succesfull normal:" + succesfullNormalDeleteResults);
		writer.println("Tiempos (sum " + normalFDeleteCounter + ") borrado unsuccefull normal:" + unsuccesfullNormalDeleteResults);
		
		writer.println("Tiempos (sum " + lowSDeleteCounter + ") borrado succesfull low:" + succesfullLowDeleteResults);
		writer.println("Tiempos (sum " + lowFDeleteCounter + ") borrado unsuccefull low:" + unsuccesfullLowDeleteResults);
		
		writer.close();
	}
	
	
	public static Tree getTreeForInt(int tree, int maxInt) throws Exception {
		switch(tree) {
		case ABB:
			return new ABBTree();
		case AVL:
			return new AVLTree();
		case SPL:
			return new SplayTree();
		case VEB:
			return new VanEmdeBoasTree(maxInt);
		}
		return null;
	}
	
	public static String getStringTreeForInt(int tree) {
		switch(tree) {
		case ABB:
			return "ABB Tree";
		case AVL:
			return "AVL Tree";
		case SPL:
			return "Splay Tree";
		case VEB:
			return "VanEmdeBoas Tree";
		}
		return null;
	}
	
	

}
