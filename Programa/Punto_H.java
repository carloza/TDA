package Programa;

import java.util.Comparator;
import TDAPila.*;
import TDACola.*;
import TDALista.*;
import TDAArbol.*;
import TDAColaCP.*;
import TDAMapeo.*;
import TDADiccionario.*;

/**
 * Clase Punto_H. Implementa los ejercicios del Proyecto de Estructura de Datos 2013
 * 
 * @author LOZA, Carlos (94399)
 * @author AGRA, Federico (94186)
 *
 */
public class Punto_H {

	//Atributos
	private Tree<Integer> arbol;
	private Map<Integer, Integer> mapa;
	private Dictionary<Integer,Integer> dicc;
	
	/**
	 * Constructor de la clase "Punto_H". Inicializa un �rbol, un mapa 
	 * y un diccionario, los tres vacios.
	 * 
	 */
	public Punto_H(){
		arbol = new LinkedTree<Integer>();
		mapa = new OpenHMap<Integer, Integer>();
		dicc = new ClosedHDictionary<Integer, Integer>();	
	}
	
	
	/**
	 * Ejercicio 1: Carga un �rbol.
	 * 
	 * @param i: r�tulo de la r�iz.
	 */
	public void punto1(Integer i) throws ErrorExcecutionException{
		try 
		{
			arbol.createRoot(i);
		} 
		catch (InvalidOperationException e)
		{
			throw new ErrorExcecutionException(e.getMessage());
		}
	}
	
	
	/**
	 * Ejercicio 2: Crea un nodo nuevo.
	 * 
	 * @param i: r�tulo del nuevo nodo.
	 * @param p: r�tulo del padre del nuevo nodo.
	 */
	public void punto2(Integer i, Integer p) throws ErrorExcecutionException {
		Position<Integer> P = buscarNodo(p);
		try 
		{
			arbol.addLastChild(P, i);
		} 
		catch (InvalidPositionException e)
		{
			throw new ErrorExcecutionException(e.getMessage());
		}
	}

	
	/**
	 * Ejercicio 3: Muestra los r�tulos de los elementos del �rbol en Preorden.
	 * 
	 * @return una cadena de caracteres con los r�tulos de los elementos del �rbol en Preorden.
	 */
	public String punto3(){
		String salida = "";
		for(Integer i : arbol)
		{
			salida = salida + i + " - ";
		}
		return salida;
	}
	
	
	/**
	 * Ejercicio 4: Muestra los r�tulos de los elementos del �rbol en Posorden.
	 * 
	 * @return una cadena de caracteres con los r�tulos de los elementos del �rbol en Posorden.
	 */
	public String punto4(){
		String salida = "";
		try 
		{
			salida = stringDelPosOrden(arbol.root());
		} 
		catch (EmptyTreeException e) 
		{
			//no hago nada, porque el arbol esta vacio y retorno la cadena vacia
		}
		return salida;
	}
	
	
	/**
	 * Eercicio 5: Muestra los r�tulos de los elementos del �rbol ordenados por niveles.
	 * 
	 * @return una cadena de caracteres con los r�tulos de los elementos del �rbol ordenados por niveles.
	 */
	public String punto5(){
		String salida = "";
		Position<Integer> Marcador = null;
		Queue<Position<Integer>> cola = new ArrayQueue<Position<Integer>>();
		
		//acomodo inicialmente la cola
		if(!arbol.isEmpty()){
			try {
				cola.enqueue(arbol.root());
				cola.enqueue(Marcador);
			} catch (EmptyTreeException e) {
				// esto nunca va a pasar porque estoy preguntando si esta vacia
			}
		}
		
		//aca voy a iterar por los niveles
		Position<Integer> pos = null;
		while(!cola.isEmpty()){
			try {
				pos = cola.dequeue(); salida = salida+ " " + pos.element();
				for(Position<Integer> p : arbol.children(pos)){
					cola.enqueue(p);
				}
				if(cola.front()==Marcador){
					Position<Integer> marca = cola.dequeue();
					if (!cola.isEmpty()){
						cola.enqueue(marca);
						salida = salida+ " / ";
					}
				}
			} catch (EmptyQueueException e) {
				// esto nunca va a pasar porque pregunte si estaba vacia
			} catch (InvalidPositionException e) {
				// esto nunca va a pasar porque estoy pasando posiciones del arbol
			}
		}//fin de la iteracion
		
		return salida;
	}
	
	
	/**
	 * Ejercicio 6: Muestra los r�tulos de los elementos del �rbol en orden descendente. 
	 * 
	 * @return una cadena de caracteres con los r�tulos de los elementos del �rbol en orden descendente.
	 */
	public String punto6(){
		String salida = "";
		Comparator<Integer> c = new ComparadorInverso();
		PriorityQueue<Integer, Integer> ccp = new HeapPQueue<Integer, Integer>(c);
		for(Position<Integer> pos : arbol.positions()){
			try {
				ccp.insert(calcularHijos(pos), pos.element());
			} catch (InvalidKeyException e) {
				//esto nuca suceder� porque estoy insertando Keys del arbol
			}
		}
		
		while(!ccp.isEmpty()){
			try {
				Entry<Integer,Integer> ent = ccp.removeMin();
				if (ent.getKey()==1)
				{
					salida = salida +"El r�tulo "+ent.getValue()+" tiene "+ent.getKey()+" hijo.\n";
				}
				else
				{
					if (ent.getKey()==0)
					{
						salida = salida +"El r�tulo "+ent.getValue()+" no tiene hijos.\n";
					}
					else
					{
						salida = salida +"El r�tulo "+ent.getValue()+" tiene "+ent.getKey()+" hijos.\n";
					}
					
				}
				
			} catch (EmptyPriorityQueueException e) {
				//esto nunca suceder� porque estoy verificando que la cola no este vacia
			}
		}
		return salida;
	}


	/**
	 * Ejercicio 7: Elimina los nodos de una altura dada.
	 * 
	 * @param n: es la altura a eliminar.
	 * @return una pila que contiene lor r�tulos de los elementos eliminados.
	 */
	public Stack<Integer> punto7(int n){
		Stack<Integer> pila = new LinkedStack<Integer>();
		try
		{
			EliminarAltura(arbol.root(), n, pila);
		}
		catch(EmptyTreeException e)
		{
			/*
			 * Se supone que nunca entra aca, ya que este punto solo se puede hacer si
			 * el �rbol esta cargado.
			 */
		}
	
		//fin de la iteracion
		return pila;
	}
	

	/**
	 * Ejercicio 8: Muestra el camino de un nodo a otro.
	 * 
	 * @param r1: es el extremo inicial.
	 * @param r2: es el extremo final.
	 * @return el camino entre ambos extremos.
	 * @throws ErrorExcecutionException lanza esta excepcion si algun nodo no existe con los r�tulos ingresados. 
	 */
	public Stack<Integer> punto8(Integer r1, Integer r2) throws ErrorExcecutionException{
		Position<Integer> p1 = buscarNodo(r1);
		Position<Integer> p2 = buscarNodo(r2);
		if( p1==null || p2==null )
		{
			throw new ErrorExcecutionException("no existen esos rotulos en el arbol");
		}
		Stack<Integer> c1 = new LinkedStack<Integer>();
		Stack<Integer> c2 = new LinkedStack<Integer>();
		int d1 = depth(p1);
		int d2 = depth(p2);
		boolean mismaDepth = (d1==d2);
		
		//aca los pongo a la misma altura
		while(!mismaDepth)
		{
			if(d1<d2)
			{
				p2 = subir(p2,c2);
				d2--;
			}
			else
			{
				p1 = subir(p1,c1);
				d1--;
			}
			mismaDepth = (d1==d2);
		}
		
		//aca voy subiendo y apilando
		while(p1!=p2)
		{
			p1 = subir(p1,c1);
			p2 = subir(p2,c2);
		}
		c1.push(p1.element());
		return trasvasarPilas(c1,c2);
	}
	
	
	/**
	 * Ejericio 9: Crea un "�rbol espejo"
	 * 
	 * @return el �rbol espejo
	 */
	public Tree<Integer> Punto9(){
		Tree<Integer> salida = new LinkedTree<Integer>();
		try
		{
			salida.createRoot(arbol.root().element());
			InvertirArbol(arbol.root(), salida.root(), salida);
		}
		catch(EmptyTreeException e)
		{
			/*
			 * Se supone que nunca entra aca, ya que este punto solo se puede hacer si
			 * el �rbol esta cargado.
			 */
		} 
		catch (InvalidOperationException e) 
		{
			 //nunca pasa esto porque acabamos de crear el �rbol arriba
		}
		return salida;
	}

	
	/**
	 * Ejercicio 10 y 11: Crea un Mapeo y un Diccionario con los elementos del �rbol.
	 * 
	 * El Mapeo contiene entradas <r,p>, donde "r" es el rotulo de un nodo y "p" su profundidad.
	 * El Diccionario contiene entradas <p,r>, donde "p" es la profundidad del nodo cuyo r�tulo es "r".
	 */
	public void punto10y11(){
		mapa = new OpenHMap<Integer, Integer>();
		dicc = new ClosedHDictionary<Integer, Integer>();
		Position<Integer> Marcador = null;
		Queue<Position<Integer>> cola = new ArrayQueue<Position<Integer>>();
		
		//acomodo inicialmente la cola
		if(!arbol.isEmpty())
		{
			try 
			{
				cola.enqueue(arbol.root());
				cola.enqueue(Marcador);
			} 
			catch (EmptyTreeException e) 
			{
				// esto nunca va a pasar porque estoy preguntando si esta vacia
			}
		}
		
		//aca voy a iterar por los niveles
		Position<Integer> pos = null;
		Integer I = 0;
		while(!cola.isEmpty())
		{
			try
			{
				pos = cola.dequeue(); 
				mapa.put(pos.element(), I);
				dicc.insert(I, pos.element());
				for(Position<Integer> p : arbol.children(pos))
				{
					cola.enqueue(p);
				}
				if(cola.front()==Marcador)
				{
					Position<Integer> marca = cola.dequeue();
					if (!cola.isEmpty())
					{
						cola.enqueue(marca);
						I++;
					}
				}
			}
			catch (EmptyQueueException e)
			{
				// esto nunca va a pasar porque pregunte si estaba vacia
			} 
			catch (InvalidPositionException e)
			{
				// esto nunca va a pasar porque estoy pasando posiciones del �rbol
			} 
			catch (InvalidKeyException e)
			{
				// esto nunca va a pasar porque estoy usando claves sacadas del �rbol
			}
		}//fin de la iteracion
	}
	
	
	/**
	 * Ejercicio 12: Muestra la profundidad del nodo cuyo r�tulo es "r" y todos los nodos de misma profundidad.
	 * 
	 * @param r: es el r�tulo del nodo a calcular su profundidad.
	 * @param l: es una lista que almacena los nodos cuya profundidad es igual a la del nodo de r�tulo "r".
	 * @return la profundidad del nodo cuyo r�tulo es "r".
	 * @throws ErrorExcecutionException lanza esta excepci�n si el r�tulo ingresado no se encuentra en el �rbol
	 */
	public int punto12(Integer r, PositionList<Integer> l) throws ErrorExcecutionException{

		Position<Integer> p = buscarNodo(r);
		if(p==null)
		{
			throw new ErrorExcecutionException();
		}
		Integer profundidad = null;
		try 
		{
			profundidad = mapa.get(p.element());
			for(Entry<Integer,Integer> e : dicc.findAll(profundidad))
			{
				l.addLast(e.getValue());
			}
		} 
		catch (InvalidKeyException e) 
		{
			// esto nunca va a pasar, porque son r�tulos del �rbol
		}
		return profundidad;
	}
	
	
	/**
	 * M�todo auxiliar. Verifica si el �rbol interno esta vacio.
	 * 
	 * @return true si esta vacio, false caso contrario.
	 */
	public boolean isEmpty(){
		return arbol.isEmpty();
	}
	

	/**
	 * M�todo auxiliar. Calcula la altura del �rbol.
	 * 
	 * @return la altura del �rbol.
	 */
	public int AlturaArbol(){
		int salida = 0;
		
		try {
			salida = Altura(arbol.root());
		} catch (EmptyTreeException e) {
			//si el �rbol esta vacio la altura es 0 como fue inicializada
		}
		return salida;
	}
	
	/**
	 * M�todo auxiliar. Concatena los elementos de un �rbol pasado por par�metro en Posorden 
	 * y los devuelve en una cadena de caracteres
	 * 
	 * @param t �rbol a pasar a String
	 * @param p posicion del �rbol pasado por par�metro, inicialmente es la ra�z de dicho �rbol
	 * @return los elementos del �rbol pasado por par�metro en una cadena de caracteres
	 */
	public String StringPosOrden(Tree<Integer> t, Position<Integer> p) {
		String salida = "";
		try 
		{
			for(Position<Integer> pos : t.children(p))
			{
				salida = salida + stringDelPosOrden(pos);
			}
			salida = salida + p.element()+ " - ";
		} 
		catch (InvalidPositionException e) 
		{
			//Esto nunca sucede, porque estoy utilizando todas posiciones del �rbol
		}
		return salida;
	}
	
	/**
	 * M�todo auxiliar. Concatena los elementos de un �rbol pasado por par�metro en Preorden 
	 * y los devuelve en una cadena de caracteres
	 * 
	 * @param t �rbol a pasar a String
	 * @return los elementos del �rbol pasado por par�metro en una cadena de caracteres
	 */
	public String StringPreOrden(Tree<Integer> t){
		String salida = "";
		for(Integer i : t)
		{
			salida = salida + i + " - ";
		}
		return salida;
	}
	
	/**
	 * M�todo auxiliar. Concatena los elementos de un �rbol pasado por par�metro por Niveles 
	 * y los devuelve en una cadena de caracteres
	 * 
	 * @param t �rbol a pasar a String
	 * @return los elementos del �rbol pasado por par�metro en una cadena de caracteres
	 */
	public String StringPorNiveles(Tree<Integer> t){
		String salida = "";
		Position<Integer> Marcador = null;
		Queue<Position<Integer>> cola = new ArrayQueue<Position<Integer>>();
		
		//acomodo inicialmente la cola
		if(!t.isEmpty())
		{
			try 
			{
				cola.enqueue(t.root());
				cola.enqueue(Marcador);
			} 
			catch (EmptyTreeException e)
			{
				// esto nunca va a pasar porque estoy preguntando si esta vacia
			}
		}
		
		//aca voy a iterar por los niveles
		Position<Integer> pos = null;
		while(!cola.isEmpty())
		{
			try 
			{
				pos = cola.dequeue(); salida = salida+ " " + pos.element();
				for(Position<Integer> p : t.children(pos))
				{
					cola.enqueue(p);
				}
				if(cola.front()==Marcador)
				{
					Position<Integer> marca = cola.dequeue();
					if (!cola.isEmpty())
					{
						cola.enqueue(marca);
						salida = salida+ " / ";
					}
				}
			} 
			catch (EmptyQueueException e)
			{
				// esto nunca va a pasar porque pregunte si estaba vacia
			} 
			catch (InvalidPositionException e) 
			{
				// esto nunca va a pasar porque estoy pasando posiciones del arbol
			}
		}//fin de la iteracion
		
		return salida;
	}
	
	
	/*
	 * M�todo privado. Determina la altura de una posici�n dada.
	 */
	private int Altura(Position<Integer> pos) {
		
		int ac = 0;
		try 
		{
			for(Position<Integer> p : arbol.children(pos))
			{
				ac = Math.max(Altura(p), ac);
			}
		} 
		catch (InvalidPositionException e) 
		{
			//esto nuca pasa porque siempre llamo con posiciones buenas
		}
		return ac+1;
	}
	
	
	/*
	 * M�todo privado. Elimina los nodos con una alura dada.
	 */
	private int EliminarAltura(Position<Integer> r, int n, Stack<Integer> p) {
		int altura = 1;
		try 
		{
			for(Position<Integer> h : arbol.children(r))
			{
				altura = Math.max(EliminarAltura(h, n, p), altura);
			}
			if (altura==n)
			{
					if ((altura==1)&&(r==arbol.root()))
					{
						p.push(r.element());
						arbol.removeNode(r);
					}
					if (r!=arbol.root())
					{
						p.push(r.element());
						arbol.removeNode(r);
					}
			}		
		} 
		catch (InvalidPositionException e) 
		{ 
			//SE SUPONE UE NUNCA VA A PASAR
		} 
		catch (EmptyTreeException e) 
		{
			//EL �RBOL SIEMPRE ESTA CARGADO PARA ESTE PUNTO
		}
		return altura+1;
	}

	
	/*
	 * M�todo privado. Une el contenido de dos pilas en una
	 */
	private Stack<Integer> trasvasarPilas(Stack<Integer> c1, Stack<Integer> c2) {
		while(!c2.isEmpty())
		{
			try 
			{
				c1.push(c2.pop());
			} 
			catch (EmptyStackException e) 
			{
				// nunca va a pasar porque pregunto que c2 no sea vacia
			}
		}
		return c1;
	}

	
	/*
	 * M�todo privado. Apila un nodo y retorna el padre
	 */
	private Position<Integer> subir(Position<Integer> p, Stack<Integer> c){
		c.push(p.element());
		Position<Integer> salida = null;
		try 
		{
			salida = arbol.parent(p);
		} 
		catch (InvalidPositionException e) 
		{
			// esto nunca va a pasar, porque llamo con posiciones buenas
		} 
		catch (BoundaryViolationException e)
		{
			/* esto nunca va a pasar, nunca entra p == raiz, ya que llamo a subir
			 * cuando p1 tiene mas profundida que p2 o viceversa(en punto8)
			 */
		}
		return salida;
	}
	
	
	/*
	 * M�todo privado. Calcula la profundidad de un nodo.
	 */
	private int depth(Position <Integer> p){
		int salida = 0;
		try {
			if (p != arbol.root()){
				salida = depth(arbol.parent(p))+1;
			}
		} catch (EmptyTreeException e) {
			// nunca voy a llamar con un arbol vacio
		} catch (InvalidPositionException e) {
			// nunca va a pasar porque busco en posiciones del arbol
		} catch (BoundaryViolationException e) {
			// nunca va a pasar porque pregunto que sea distinto del padre
		}
		return salida;
	}
	
	
	/*
	 * M�todo privado. Concatena los elementos del �rbol en Posorden
	 * y los devuelve en una cadena de caracteres.
	 */
	private String stringDelPosOrden(Position<Integer> p) {
		String salida = "";
		try 
		{
			for(Position<Integer> pos : arbol.children(p))
			{
				salida = salida + stringDelPosOrden(pos);
			}
			salida = salida + p.element()+ " - ";
		} 
		catch (InvalidPositionException e) 
		{
			//Esto nunca sucede, porque estoy utilizando todas posiciones del �rbol
		}
		return salida;
	}

	
	/*
	 * M�todo privado. Busca un nodo.
	 */
	private Position<Integer> buscarNodo(Integer p) {
		Position<Integer> P = null;
		for(Position<Integer> pos : arbol.positions())
		{
			if(pos.element().equals(p))
			{
				P = pos;
				break;
			}
		}//retorna null si no lo encuentra
		return P;
	}
	
	
	/*
	 * M�todo privado. Invierte el orden de los hjos de ca da nodo del �rbol
	 */
	private void InvertirArbol(Position<Integer> r, Position<Integer> e, Tree<Integer> salida) {
		
		try 
		{
			for(Position<Integer> p : arbol.children(r))
			{
				Position<Integer> aux = salida.addFirstChild(e, p.element());
				InvertirArbol(p, aux, salida);
			}	
		} 
		catch(InvalidPositionException exc)
		{
			//Se supone que esto nunca pasa ya que usamos posiciones del mismo �rbol
		}
	}
	
	
	/*
	 * M�todo privado. Cuenta la cantidad de hijos de un nodo
	 */
	@SuppressWarnings("unused")//Salteo la comprobaci�n del compilador que dentro del FOREACH no uso "h"
	private Integer calcularHijos(Position<Integer> p) {
		int salida = 0;
		try
		{
			for( Position<Integer> h : arbol.children(p))
			{
				salida++;
			}
		}
		catch(InvalidPositionException e)
		{
			//Nunca entra porque uso posiciones del �rbol
		}
		return salida;
	}
}
