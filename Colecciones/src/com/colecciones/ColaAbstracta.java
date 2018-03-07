/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.util.NoSuchElementException;

/**
 * Esta clase proporciona implementaciones esqueléticas de algunos
 * {@link Cola}operaciones. Las implementaciones en esta clase son apropiadas
 * cuando la implementación base <em>no</em> permite <tt>null
 * </tt>
 * elementos. Métodos {@link #agregar agregar}, {@link #remover removre} y
 * {@link #elemento de elemento} se basan en {@link #ofrecer ofrecer}, {@link
 *  #encuestar encuestar} y {@link #ojear ojear}, respectivamente, pero arroja
 * excepciones en lugar de indicar falla a través de <tt>false</tt> o
 * <tt> null </tt> regresa.  
 * <p>
 * Una implementación <tt>Cola</tt> que extiende esta clase debe definir
 * mínimamente un método {@link Cola #ofrecer} que no permita inserción de
 * elementos <tt> null </ tt>, junto con los métodos {@link
 * Cola #ojear}, {@link Cola #encuestar}, {@link Coleccion #tamanio}, y
 * {@link Coleccion #iterator}. Por lo general, los métodos adicionales serán
 * anulado también. Si estos requisitos no pueden cumplirse, considere en lugar
 * de subclases {@link ColeccionAbstracta}.  
 * <p>
 * Esta clase es miembro de la  
 *
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework </a>.
 *
 *
 * @since 1.5
 * @author FABAME
 * @param <E> el tipo de elementos contenidos en esta colección
 */
public abstract class ColaAbstracta<E> extends ColeccionAbstracta<E>
        implements Cola<E> {

    //Constructor de la clase ColaAbstracta.
    /**
     * Constructor de la clase ColaAbstracta para uso por subclases.
     *
     */
    protected ColaAbstracta() {

    }

    //Métodos de la clase ColaAbstracta.
    @Override
    public boolean agregar(E elemento) {
        if (this.ofrecer(elemento)) {
            return true;
        } else {
            throw new IllegalStateException("Cola llena.");
        }
    }

    /**
     * Agrega todos los elementos en la colección especificada a este cola Los
     * intentos de agregar toda una cola a sí mismo dan como resultado
     * <tt>IllegalArgumentException</tt>. Además, el comportamiento de esta
     * operación no está definida si la colección especificada es modificado
     * mientras la operación está en progreso.
     *
     *      
     * <p>
     * Esta implementación itera sobre la colección especificada, y agrega cada
     * elemento devuelto por el iterador a este cola, a su vez. Una excepción de
     * tiempo de ejecución encontrada mientras tratando de agregar un elemento
     * (incluyendo, en particular, un elemento <tt>null</tt>) puede dar como
     * resultado solo algunos de los elementos  haber sido agregado con éxito
     * cuando la excepción asociada es arrojado.
     *
     * @param coleccion colección que contiene elementos que se agregarán a esta
     * cola
     * @return <tt>true</tt> si esta cola ha cambiado como resultado de la
     * llamada throws ClassCastException si la clase de un elemento de la
     * especificada la colección evita que se agregue a esta cola
     * @throws NullPointerException si la colección especificada contiene una
     * elemento nulo y esta cola no permite elementos nulos, o si la colección
     * especificada es nula
     * @throws IllegalArgumentException si alguna propiedad de un elemento del
     * la colección especificada impide que se agregue a este cola, o si la
     * colección especificada es esta cola
     * @throws IllegalStateException si no todos los elementos se pueden agregar
     * a esta vez debido a restricciones de inserción
     * @see #agregar(Object)
     */
    @Override
    public boolean agregarTodo(Coleccion<? extends E> coleccion) {
        if (coleccion == null) {
            throw new NullPointerException();
        }
        if (coleccion == this) {
            throw new IllegalArgumentException();
        }
        boolean modificado = false;
        for (E elemento : coleccion) {
            if (this.agregar(elemento)) {
                modificado = true;
            }
        }
        return modificado;
    }

    /**
     * Recupera, pero no elimina, el encabezado de esta cola. Este método
     * difiere de {@link #peek peek} solo en que arroja una excepción si esta
     * cola está vacía.    
     * <p>
     * Esta implementación devuelve el resultado de <tt>ojear</tt>
     * a menos que la cola esté vacía.
     *
     * @return el jefe de esta cola
     * @throws NoSuchElementException si esta cola está vacía      
     */
    @Override
    public E elemento() {
        E elemento = this.ojear();
        if (elemento != null) {
            return elemento;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void limpiar() {
        while (this.encuestar() != null);
    }

    @Override
    public E remover() {
        E elemento = this.encuestar();
        if (elemento != null) {
            return elemento;
        } else {
            throw new NoSuchElementException();
        }
    }
}
