/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.util.EmptyStackException;

/**
 * Clase Pila para guardar y manipular elementos en una Pila.
 */
/**
 * La clase <code>Pila</code> representa un último en entrar-primero en salir
 * (LIFO) pila de objetos. Extiende la clase <tt>ListaVector</tt> con cinco
 * operaciones que permiten que un vector sea tratado como una pila. Lo normal
 * <tt>empuja</tt> y se proporcionan <tt>quitar</tt> operaciones, así como  el
 * método para<tt>buscar</tt> en el elemento superior de la pila, un método para
 * probar para si la pila está <tt>vacía</tt>, y un método para <tt>buscar</tt>
 * la pila para un artículo y descubre qué tan lejos está de la parte superior.
 *  
 * <p>
 * Cuando se crea una pila por primera vez, no contiene elementos. 
 * <p>
 * Un conjunto más completo y consistente de operaciones de pila LIFO es
 * proporcionado por la interfaz {@link ColaDeque} y sus implementaciones, que
 * debe usarse con preferencia a esta clase. Por ejemplo:  
 * <pre> {@code
 * ColaDeque <Integer> = pila = new ColaDequeArreglo<Integer>();} </ pre>
 *
 * @since JDK 1.0
 * @author FABAME
 * @param <E> el tipo de elementos contenidos en esta colección
 */
public class Pila<E> extends ListaVector<E> {

    //Atributo de la clase Pila.
    /**
     * Use serialVersionUID de JDK 1.0.2 para interoperabilidad
     */
    private static final long serialVersionUID = 1224463164541339165L;

    //Constructor de la clase Pila.
    /**
     * Crea una pila vacía.
     */
    public Pila() {

    }

    //Métodos de la clase Pila.
    /**
     * Devuelve la posición 1 en la que un objeto está en esta pila. Si el
     * objeto <tt>o</tt> aparece como un elemento en esta pila, este el método
     * devuelve la distancia desde la parte superior de la pila del ocurrencia
     * más cercana a la parte superior de la pila; el elemento superior en el la
     * pila se considera a una distancia <tt>1</tt>. <tt>es igual a</tt>
     * el método se usa para comparar <tt>o</tt> con el elementos en esta pila.
     *
     * @param objeto el objeto deseado.      
     * @return la posición 1 desde la parte superior de la pila donde el objeto
     * está ubicado; el valor de retorno <code>-1</code> indica que el objeto no
     * está en la pila.      
     */
    public synchronized int buscar(Object objeto) {
        int i = this.ultimoIndiceDe(objeto);

        if (i >= 0) {
            return this.tamanio() - i;
        }
        return -1;
    }

    /**
     * Empuja un elemento en la parte superior de esta pila. Esto tiene
     * exactamente el mismo efecto que: <blockquote>
     * <pre>
     * agregarElemento(elemento)</pre></blockquote>
     *
     * @param inserta el elemento que se va a insertar en esta pila.
     * @return el argumento <code>elemento</code>.
     * @see ListaVector #agregarElemento      
     */
    public E empujar(E elemento) {
        this.agregarElemento(elemento);

        return elemento;
    }

    /**
     * Prueba si esta pila está vacía.
     *
     * @return<code>true</code> si y solo si esta pila contiene no hay
     * artículos; <code>false</code> de lo contrario.      
     */
    @Override
    public boolean estaVacia() {
        return this.tamanio() == 0;
    }

    /**
     * Mira el objeto en la parte superior de esta pila sin quitarlo de la pila.
     *
     * @return el objeto en la partesuperior de esta pila (el último elemento
     * del objeto <tt>ListaVector
     * </tt>).
     * @throws EmptyStackException si esta pila está vacía.     
     */
    public synchronized E ojear() {
        int tamanio = this.tamanio();

        if (tamanio == 0) {
            throw new EmptyStackException();
        }
        return this.elementoEn(tamanio - 1);
    }

    /**
     * Elimina el objeto en la parte superior de esta pila y lo devuelve objeto
     * como el valor de esta función.
     *
     * @return El objeto en la parte superior de esta pila (el último elemento
     * del objeto <tt>ListaVector</tt>).
     * @throws EmptyStackException si esta pila está vacía.      
     */
    public synchronized E quitar() {
        E objeto;
        int tamanio = this.tamanio();

        objeto = this.ojear();
        this.removerElementoEn(tamanio - 1);

        return objeto;
    }

}
