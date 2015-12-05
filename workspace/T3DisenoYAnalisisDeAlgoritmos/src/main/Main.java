package main;

import java.io.File;
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
	 * Prueba el los arboles para la secuencia tipo stack: inseciones secuenciales, max min next y previous, buscar random, borrar desecuancial.
	 * @param universoStart
	 * @param universoEnd
	 * @param tree El tree a probar. 0 ABB, 1 AVL, 2 Splay, 3 Van Emde Boas
	 * @throws Exception  Si el universoEnd no es una potencia de dos
	 */
	public static void testStack(int universoStart, int universoEnd, int tree) throws Exception {
		Random r = new Random();
		long s = System.nanoTime();
		TreeTimeMeasurer treeMeasurer = new TreeTimeMeasurer(getTreeForInt(tree, universoEnd));
		long e = System.nanoTime();
		long[] insertResults = new long[universoEnd - universoStart + 1];
		long[] sucesorResults = new long[universoEnd - universoStart + 1];
		long[] antesesorResults = new long[universoEnd - universoStart + 1];
		long maxResult;
		long minResult;
		long[] searchResults = new long[(universoEnd - universoStart)*5 + 1];
		long[] deleteResults = new long[universoEnd - universoStart + 1];
		
		// Insercion
		int index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			insertResults[index] = treeMeasurer.measureInsert(i);
			index++;
		}
		System.out.println("Insercion terminado");
		// Sucesor
		index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			sucesorResults[index] = treeMeasurer.measureNext(i);
			index++;
		}
		System.out.println("Sucesor terminado");
		// Antesesor
		index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			antesesorResults[index] = treeMeasurer.measureInsert(i);
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
			searchResults[i] = treeMeasurer.measureFind(buscar).getTime();
		}
		System.out.println("Busqueda terminada");
		// Borrado
		index = 0;
		for (int i = universoEnd; i >= universoStart; i--) {
			deleteResults[index] = treeMeasurer.measureDelete(i).getTime();
			index++;
		}
		System.out.println("Borrado terminado");
		PrintWriter writer = new PrintWriter("results_test_1_universo_" + (universoEnd - universoStart + 1) + "_" + getStringTreeForInt(tree) + ".txt", "UTF-8");
		writer.println("Tiempo inicializado: " + (e - s));
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") insercion: " + getArraySum(insertResults));
		writer.println("Tiempo buscar maximo: " + maxResult);
		writer.println("Tiempo buscar minimo: " + minResult);
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar sucesor: " + getArraySum(sucesorResults));
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar antesesor: " + getArraySum(antesesorResults));
		writer.println("Tiempos (sum " + ((universoEnd - universoStart)*5) + " ) busqueda: " + getArraySum(searchResults));
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") borrado:" + getArraySum(deleteResults));
		writer.close();
		
	}
	
	public static void testSegunda(int universoStart, int universoEnd, int tree) throws Exception {
		Random r = new Random();
		Tree copyTree = getTreeForInt(tree, universoEnd);
		long s = System.nanoTime();
		TreeTimeMeasurer treeMeasurer = new TreeTimeMeasurer(getTreeForInt(tree, universoEnd));
		long e = System.nanoTime();
		long[] insertResults = new long[(int) ((universoEnd - universoStart + 1) * 0.75)];
		long[] sucesorResults = new long[(int) ((universoEnd - universoStart + 1))];
		long[] antesesorResults = new long[(int) ((universoEnd - universoStart + 1))];
		long maxResult;
		long minResult;
		long[] succesfullSearchResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] unsuccesfullSearchResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] succesfullDeleteResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] unsuccesfullDeleteResults = new long[(universoEnd - universoStart)*10 + 1];
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
			insertResults[insertionIndex] = treeMeasurer.measureInsert(insertar);
			copyTree.insert(insertar);
			insertionIndex++;
		}
		System.out.println("Insercion terminado");
		// Sucesor
		int index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			sucesorResults[index] = treeMeasurer.measureNext(i);
			index++;
		}
		System.out.println("Sucesor terminado");
		// Antesesor
		index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			antesesorResults[index] = treeMeasurer.measureInsert(i);
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
					succesfullSearchResults[succesfullSearches] = ttor.getTime(); 
					succesfullSearches++;
				} else {
					unsuccesfullSearchResults[unsuccesfullSearches] = ttor.getTime();
					unsuccesfullSearches++;
				}
			} else {
				TuplaTimeOperationResult ttor = treeMeasurer.measureDelete(buscarOBorrar);
				if (ttor.getOperationResult()) {
					succesfullDeleteResults[succesfullDeletes] = ttor.getTime(); 
					succesfullDeletes++;
				} else {
					unsuccesfullDeleteResults[unsuccesfullDeletes] = ttor.getTime();
					unsuccesfullDeletes++;
				}
			}
		}
		System.out.println("Busqueda y borrado terminada");

		PrintWriter writer = new PrintWriter("results_test_2_universo_" + (universoEnd - universoStart + 1) + "_" + getStringTreeForInt(tree) + ".txt", "UTF-8");
		writer.println("Tiempo inicializado: " + (e - s));
		writer.println("Tiempos (sum " + insertionIndex + ") insercion: " + getArraySum(insertResults));
		writer.println("Tiempo buscar maximo: " + maxResult);
		writer.println("Tiempo buscar minimo: " + minResult);
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar sucesor: " + getArraySum(sucesorResults));
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar antesesor: " + getArraySum(antesesorResults));
		writer.println("Tiempos (sum " + succesfullSearches + " ) busqueda succesfull: " + getArraySum(succesfullSearchResults));
		writer.println("Tiempos (sum " + unsuccesfullSearches + " ) busqueda unsuccesfull: " + getArraySum(unsuccesfullSearchResults));
		writer.println("Tiempos (sum " + succesfullDeletes + ") borrado succesfull:" + getArraySum(succesfullDeleteResults));
		writer.println("Tiempos (sum " + unsuccesfullDeletes + ") borrado:" + getArraySum(unsuccesfullDeleteResults));
		writer.close();
	}
	
	/**
	 * Esto no esta corriendo mal por los arboles. Esta corriendo mal por los linked list
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
		long[] insertResults = new long[(universoEnd - universoStart + 1)];
		long[] sucesorResults = new long[(universoEnd - universoStart + 1)];
		long[] antesesorResults = new long[(universoEnd - universoStart + 1)];
		long maxResult;
		long minResult;
		long[] hotSearchResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] normalSearchResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] lowSearchResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] succesfullHotDeleteResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] unsuccesfullHotDeleteResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] succesfullNormalDeleteResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] unsuccesfullNormalDeleteResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] succesfullLowDeleteResults = new long[(universoEnd - universoStart)*10 + 1];
		long[] unsuccesfullLowDeleteResults = new long[(universoEnd - universoStart)*10 + 1];
		
		LinkedList<Integer> shuffleInsert = new LinkedList<>();
		for (int i = universoStart; i <= universoEnd; i++) {
			shuffleInsert.add(i);
		}
		Collections.shuffle(shuffleInsert);
		
		int index = 0;
		for (Integer mint : shuffleInsert) {
			insertResults[index] = treeMeasurer.measureInsert(mint);
			index++;
		}
		System.out.println("Terminado insert");
		// Sucesor
		index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			sucesorResults[index] = treeMeasurer.measureNext(i);
			index++;
		}
		System.out.println("Sucesor terminado");
		// Antesesor
		index = 0;
		for (int i = universoStart; i <= universoEnd; i++) {
			antesesorResults[index] = treeMeasurer.measureInsert(i);
			index++;
		}	
		System.out.println("Antesesor terminado");
		maxResult = treeMeasurer.measureMax();
		System.out.println("Maximo terminado");
		minResult = treeMeasurer.measureMin();
		System.out.println("Minimo terminado");
		
		// Definimos bloques mas activos para busqueda y borrado
		LinkedList<Integer> hot = new LinkedList<>();
		LinkedList<Integer> normal = new LinkedList<>();
		LinkedList<Integer> low = new LinkedList<>();
		int shuffleSize = shuffleInsert.size();
		int counter = 0;
		for (Integer mint : shuffleInsert) {
			if (counter > shuffleSize*0.9) {
				hot.add(mint);
			} else if (counter > shuffleSize*0.5) {
				normal.add(mint);
			} else {
				low.add(mint);
			}
			counter++;
		}
		int hotSize = hot.size();
		int normalSize = normal.size();
		int lowSize = low.size();
		
		int hotSearchCounter = 0;
		int normalSearchCounter = 0;
		int lowSearchCounter = 0;
		// Busqueda
		for (int i = 0; i <= (universoEnd - universoStart)*10; i++) {
			int hotNormalLow = r.nextInt(10);
			if (hotNormalLow <= 5) {
				int toSearch = hot.get(r.nextInt(hotSize));
				hotSearchResults[hotSearchCounter] = treeMeasurer.measureFind(toSearch).getTime();
				hotSearchCounter++;
			} else if (hotNormalLow <= 8) {
				int toSearch = normal.get(r.nextInt(normalSize));
				normalSearchResults[normalSearchCounter] = treeMeasurer.measureFind(toSearch).getTime();
				normalSearchCounter++;
			} else {
				int toSearch = low.get(r.nextInt(lowSize));
				lowSearchResults[lowSearchCounter] = treeMeasurer.measureFind(toSearch).getTime();
				lowSearchCounter++;
			}
			if (i % 100000 == 0)
				System.out.println("Pasa algo!");
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
			int hotNormalLow = r.nextInt(10);
			if (hotNormalLow <= 5) {
				int toDelete = hot.get(r.nextInt(hotSize));
				TuplaTimeOperationResult tupla = treeMeasurer.measureDelete(toDelete);
				if (tupla.getOperationResult()) {
					succesfullHotDeleteResults[hotSDeleteCounter] = tupla.getTime();
					hotSDeleteCounter++;
				} else {
					unsuccesfullHotDeleteResults[hotFDeleteCounter] = tupla.getTime();
					hotFDeleteCounter++;
				}
			} else if (hotNormalLow <= 8) {
				int toDelete = normal.get(r.nextInt(normalSize));
				TuplaTimeOperationResult tupla = treeMeasurer.measureDelete(toDelete);
				if (tupla.getOperationResult()) {
					succesfullNormalDeleteResults[normalSDeleteCounter] = tupla.getTime();
					normalSDeleteCounter++;
				} else {
					unsuccesfullNormalDeleteResults[normalFDeleteCounter] = tupla.getTime();
					normalFDeleteCounter++;
				}
			} else {
				int toDelete = low.get(r.nextInt(lowSize));
				TuplaTimeOperationResult tupla = treeMeasurer.measureDelete(toDelete);
				if (tupla.getOperationResult()) {
					succesfullLowDeleteResults[lowSDeleteCounter] = tupla.getTime();
					lowSDeleteCounter++;
				} else {
					unsuccesfullLowDeleteResults[lowFDeleteCounter] = tupla.getTime();
					lowFDeleteCounter++;
				}
			}
		}
		System.out.println("Borrado terminado");
		
		
		
		
		PrintWriter writer = new PrintWriter("results_test_3_universo_" + (universoEnd - universoStart + 1) + "_" + getStringTreeForInt(tree) + ".txt", "UTF-8");
		writer.println("Tiempo inicializado: " + (e - s));
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") insercion: " + getArraySum(insertResults));
		writer.println("Tiempo buscar maximo: " + maxResult);
		writer.println("Tiempo buscar minimo: " + minResult);
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar sucesor: " + getArraySum(sucesorResults));
		writer.println("Tiempos (sum " + (universoEnd - universoStart + 1) + ") buscar antesesor: " + getArraySum(antesesorResults));
		
		writer.println("Tiempos (sum " + hotSearchCounter + " ) busqueda hot: " + getArraySum(hotSearchResults));
		writer.println("Tiempos (sum " + normalSearchCounter + " ) busqueda normal: " + getArraySum(normalSearchResults));
		writer.println("Tiempos (sum " + lowSearchCounter + " ) busqueda low: " + getArraySum(lowSearchResults));
		
		
		writer.println("Tiempos (sum " + hotSDeleteCounter + ") borrado succesfull hot:" + getArraySum(succesfullHotDeleteResults));
		writer.println("Tiempos (sum " + hotFDeleteCounter + ") borrado unsuccefull hot:" + getArraySum(unsuccesfullHotDeleteResults));
		
		writer.println("Tiempos (sum " + normalSDeleteCounter + ") borrado succesfull normal:" + getArraySum(succesfullNormalDeleteResults));
		writer.println("Tiempos (sum " + normalFDeleteCounter + ") borrado unsuccefull normal:" + getArraySum(unsuccesfullNormalDeleteResults));
		
		writer.println("Tiempos (sum " + lowSDeleteCounter + ") borrado succesfull low:" + getArraySum(succesfullLowDeleteResults));
		writer.println("Tiempos (sum " + lowFDeleteCounter + ") borrado unsuccefull low:" + getArraySum(unsuccesfullLowDeleteResults));
		
		writer.close();
	}
	
	public static long getArraySum(long[] array) {
		long toReturn = 0;
		for (long along : array) {
			toReturn = toReturn + along;
		}
		return toReturn;
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
