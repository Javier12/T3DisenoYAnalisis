package main;

public interface Tree {

	/**
	 * Inserta el elemento i en el arbol
	 * @param i el elemento a insertar
	 */
	public void insert(int i);

	/**
	 * Busca el elemento i en el arbol
	 * @param i el elemento a buscar
	 * @return true si lo encuentra, false si no
	 */
	public boolean find(int i);
	
	/**
	 * Borra el elemento i de el arbol
	 * @param i el elemento a borrar
	 */
	public void delete(int i);
	
	/**
	 * Obtiene la clave mas grande contenida en el arbol
	 * @return La clave o null si esta vacio
	 */
	public Integer getMax();
	/**
	 * Obtiene la clave mas pequenna contenida en el arbol
	 * @return La clave o null si esta vacio
	 */
	public Integer getMin();
	
	/**
	 * Obtiene la menor clave dentro de las mayores a la entregada como parametro
	 * @param i La clave de la cual se busca el sucesor
	 * @return La clave sucesora o null si no la hay
	 */
	public Integer getNext(int i);
	
	/**
	 * Obtiene la mayor clave dentro de las menores a la entregada como parametro
	 * @param i La clave de la cual se busca el antecesor
	 * @return La clave antesesora o null si no la hay
	 */
	public Integer getPrevious(int i);
	

}
