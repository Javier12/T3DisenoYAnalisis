package trees;

public class VanEmdeBoasNode {

	private Integer max;
	private Integer min;
	private VanEmdeBoasNode[] clusters;
	private VanEmdeBoasNode summary;
	private int universo;
	private int raizUniversoTop;
	private int raizUniversoBottom;
	
	public static double log2(int n){
	    if(n <= 0)
	    	throw new IllegalArgumentException();
	    return 31 - Integer.numberOfLeadingZeros(n);
	}
	
	VanEmdeBoasNode(int size) {
		max = null;
		min = null;
		universo = size;
		raizUniversoTop = (int) Math.pow(2, Math.ceil(log2(universo)/2));
		if (universo == 8) {
		//System.out.println("Universo: " + size);
		//System.out.println("log 2 universo: " + log2(universo));
		//System.out.println("Math ceil log2/2: " + Math.ceil(log2(universo/2)));
		//System.out.println("Math pow: " +  Math.pow(2, Math.ceil(log2(universo)/2)));
		//System.out.println("Raiz universo top: " + raizUniversoTop);
		}
		raizUniversoBottom = (int) Math.pow(2, Math.ceil(log2(universo)/2));
		initializeChildren(size, raizUniversoTop);
	}
	/**
	 * Inicializa todos los hijos recursivamente del arbol.
	 * Los unicos que no tienen hijos son aquellos con universo tamano dos
	 * @param universeSize
	 */
	private void initializeChildren(int universe, int raizUniversoTop) {
		if(universe == 2) {
			summary = null;
			clusters = null;
		} else {

			summary = new VanEmdeBoasNode(raizUniversoTop);
			clusters = new VanEmdeBoasNode[raizUniversoTop];
			
			for(int i = 0; i < raizUniversoTop; i++) {
				clusters[i] = new VanEmdeBoasNode(raizUniversoTop);
			}
		} 
	}
	
	private int high(int x) {
		return (int) Math.floor(x/raizUniversoBottom);
	}
	
	private int low(int x) {
		return x % raizUniversoBottom;
	}
	
	private int index(int x, int y) {
		return x*raizUniversoBottom + y;
	}
	
	private void emptyInsertM(Integer toInsert) {
		System.out.println("Toinsert: " + toInsert);
		min = toInsert;
		max = toInsert;
	}
	
	public void insertM(Integer toInsert) {
		if (min == null) {
			System.out.println("What?");
			emptyInsertM(toInsert);
		} else {
			System.out.println("Algo");
			if (toInsert < min) {
				//min = toInsert;
				int maux = toInsert;
				toInsert = min;
				min = maux;
			}
			if (universo > 2) {
				System.out.println("Clusters size: " + clusters.length);
				System.out.println("To Insert: " + toInsert);
				System.out.println("High to insert: " + high(toInsert));
				if (clusters[high(toInsert)].min == null) {
					summary.insertM(high(toInsert));
					clusters[high(toInsert)].emptyInsertM(low(toInsert));
				} else {
					clusters[high(toInsert)].insertM(low(toInsert));
				}
			}
			if (toInsert > max) {
				max = toInsert;
			}
		}
	}
	
	public boolean findM(Integer toFind) {
		if (toFind.equals(min) || toFind.equals(max)) {
			return true;
		} else if (universo == 2) {
			return false;
		} else {
			return clusters[high(toFind)].findM(low(toFind));
		}
	}
	
	public void deleteM(Integer toDelete) {
		//System.out.println("To delete: " + toDelete);
		if ((min != null && min.equals(max)) || min == max) {
			//System.out.println("O1");
			min = max = null;
		} else if (universo == 2) {
			//System.out.println("O2");
			// El universo tiene dos elementos. Borro el que me piden
			// Me quedo con el otro y actualizo los parametros.
			if (toDelete == 0)
				min = 1;
			else
				min = 0;
			max = min;
		} else {
		
			if (toDelete.equals(min)) {
				//System.out.println("O3");
				int firstCluster = summary.min;
				toDelete = index(firstCluster, clusters[firstCluster].min);
				min = toDelete;
			}
			
			clusters[high(toDelete)].deleteM(low(toDelete));
		
			if (clusters[high(toDelete)].min == null) {
				summary.deleteM(high(toDelete));
			}
			if (toDelete.equals(max)) {
				if (summary.max == null) {
					max = min;
				} else {
					max = index(summary.max, clusters[summary.max].max);
				}
			} else if (toDelete.equals(max)) {
				max = index(high(toDelete), clusters[high(toDelete)].max);
			}
		}
			
	}
	
	public Integer getMax() {
		return max;
	}
	
	public Integer getMin() {
		return min;
	}

	public Integer getNext(int i) {
		if (universo == 2) {
			if (i == 0 && max != null && max == 1) {
				System.out.println("Uno");
				return 1;
			} else {
				return null;
			}
		} else if (min != null && i < min ) {
			return min;
		} else {
			Integer maxLow = clusters[high(i)].max;
			if (maxLow != null && low(i) < maxLow) {
				int offset = clusters[high(i)].getNext(low(i));
				System.out.println("Tres");
				System.out.println("Value tres: " + index(high(i), offset));
				if (index(high(i), offset) != i) {
					System.out.println("index i offset: " + index(high(i), offset));
					System.out.println("I: " + i);
					return index(high(i), offset);
				} else {
					return null;
				}
			} else {
				Integer succesorCluster = summary.getNext(high(i));
				if (succesorCluster == null) {
					return null;
				} else {
					int offset = clusters[succesorCluster].min;
					System.out.println("Cuatro");
					return index(succesorCluster, offset);
				}
			}
		}
	}

	public Integer getPrevious(int i) {
		if (universo == 2) {
			if (i == 1 && min == 0)
				return 0;
			else
				return null;
		} else if (max != null && i > max ) {
			return max;
		} else {
			Integer maxLow = clusters[high(i)].max;
			if (maxLow != null && low(i) < maxLow) {
				int offset = clusters[high(i)].getNext(low(i));
				return index(high(i), offset);
			} else {
				Integer succesorCluster = summary.getNext(high(i));
				if (succesorCluster == null) {
					return null;
				} else {
					int offset = clusters[succesorCluster].min;
					return index(succesorCluster, offset);
				}
			}
		}
	}
	
	
	
	
	
}
