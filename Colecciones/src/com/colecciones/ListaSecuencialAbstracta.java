/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Esta clase proporciona una implementación esquemática de la <tt> Lista
 * </tt>
 * interfaz para minimizar el esfuerzo requerido para implementar esta interfaz
 * respaldado por un almacén de datos de "acceso secuencial" (como una lista
 * vinculada). por se deben usar datos de acceso aleatorio (como un arreglo),
 * <tt>ListaAbstracta</tt>
 * en preferencia a esta clase.
 * <p>
 *
 *  Esta clase es lo opuesto a la clase <tt> ListaAbstracta</tt> en el sentido
 * que implementa los métodos de "acceso aleatorio" (<tt> obtener(int index)
 * </tt>, <tt> set (índice int, elemento E) </tt>, <tt> agregar (índice int,
 * elemento E) </tt> y<tt> remover (índice int) </tt>) en la parte superior del
 * iterador de la lista, en lugar de al revés.
 * <p>
 *  
 * Para implementar una lista, el programador solo necesita extender esta clase
 * y proporcionar implementaciones para <tt> listIterator </tt> y <tt>
 * size </tt>
 * métodos. Para una lista no modificable, el programador solo necesita
 * implementar el list iterator's <tt> hasNext </tt>, <tt> next </tt>, <tt>
 * hasPrevious </tt>, <tt> métodos </tt> anteriores y <tt> </tt>
 * <p>
 * <p>
 *
 * Para una lista modificable, el programador también debe implementar la lista
 * Método <tt> establecer </tt> del iterador. Para una lista de tamaño variable,
 * el programador debería implementar adicionalmente el <tt>
 * remove</tt> del iterador de lista y <tt>agregar</tt> métodos.
 * <p>
 *
 * El programador generalmente debe proporcionar un vacío (sin argumento) y
 * recopilación constructor, según la recomendación en la interfaz <tt>
 * Coleccion</tt>
 * especificación.
 * <p>
 *
 *  Esta clase es un miembro de la  
 *
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework </a>.
 *
 * @since 1.2  
 * @author FABAME
 * @see Coleccion  
 * @see Lista
 * @see ListaAbstracta
 * @see ColeccionAbstracta
 * @param <E> el tipo de elementos contenidos en esta colección
 */
public abstract class ListaSecuencialAbstracta<E> extends ListaAbstracta<E> {

    //Constructor de la clase ListaSecuencialAbstracta.
    /**
     * Único constructor. (Para invocación por constructores de subclase,
     * típicamente implícito.)      
     */
    protected ListaSecuencialAbstracta() {

    }
//Métodos de la clase ListaSecuencialAbstracta.

    /**
     * Inserta el elemento especificado en la posición especificada en esta
     * lista (operación opcional). Cambia el elemento actualmente en esa
     * posición (si corresponde) y cualquier elemento posterior a la derecha
     * (agrega uno a sus índices).    
     * <p>
     * Esta implementación primero obtiene un iterador de lista que apunta al
     *  elemento indexado (con <tt>listIterator(indice)</tt>). Entonces eso
     * inserta el elemento especificado con <tt>
     * ListIterator.add </tt>.    
     * <p>
     * Tenga en cuenta que esta implementación arrojará un <tt>
     * UnsupportedOperationException </tt> si el iterador de la lista no lo hace
     * implementar la operación <tt> agregar </tt>.    
     *
     * @param indice índice el que se debe insertar el elemento especificado
     * @param elemento elemento a insertar
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public void agregar(int indice, E elemento) {
        try {
            this.listIterator(indice).add(elemento);
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Índice: " + indice);
        }
    }

    /**
     * Inserta todos los elementos en la colección especificada en este lista en
     * la posición especificada (operación opcional). Cambia el elemento
     * actualmente en esa posición (si corresponde) y cualquier elemento
     * posterior  elementos a la derecha (aumenta sus índices). Los nuevos
     * elementos aparecerá en esta lista en el orden en que el iterador de la
     * colección especificada. El comportamiento de esta operación es indefinido
     * si la colección especificada se modifica mientras la operación está en
     * progreso. (Tenga en cuenta que esto ocurrirá si el especificado colección
     * es esta lista, y no está vacía.)      
     * <p>
     * Esta implementación obtiene un iterador sobre la colección especificada y
     * un iterador de lista sobre esta lista que apunta al elemento indexado
     * (con <tt>listIterator(indice)</tt>). Luego, itera sobre el especificado
     * colección, insertando los elementos obtenidos del iterador en este lista,
     * uno a la vez, usando <tt>
     * ListIterator.add</tt> seguido de <tt>ListIterator.next</tt>
     * (omitir el elemento agregado).      
     * <p>
     * Tenga en cuenta que esta implementación arrojará un <tt>
     * UnsupportedOperationException </tt> si el iterador de la lista devuelto
     * por el método <tt> listIterator</tt> no implementa la
     * operación<tt>agregar
     * </tt>.
     *
     * @param indice indice el en cual se inserta el primer elemento de la
     * colección especificada
     * @param coleccion colección que contiene elementos para agregar a esta
     * lista
     * @return <tt>true</tt> si esta lista cambia como resultado de la llamada
     * @throws UnsupportedOperationException{@inheritDoc}
     * @throws ClassCastException {@inheritDoc}      
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public boolean agregarTodo(int indice, Coleccion<? extends E> coleccion) {
        try {
            boolean modificado = false;
            ListIterator<E> e1 = listIterator(indice);
            Iterator<? extends E> e2 = coleccion.iterator();
            while (e2.hasNext()) {
                e1.add(e2.next());
                modificado = true;
            }
            return modificado;
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Índice: " + indice);
        }
    }

    /**
     * Reemplaza el elemento en la posición especificada en esta lista con el
     * elemento especificado (operación opcional).    
     * <p>
     * Esta implementación primero obtiene un iterador de lista que apunta al
     * elemento indexado (con <tt>listIterator (índice)</tt>). Entonces, se pone
     * el elemento actual usando <tt>ListIterator.next</tt> y lo reemplaza con
     * <tt>ListIterator.set</tt>.
     * <p>
     * Tenga en cuenta que esta implementación arrojará un <tt>
     * UnsupportedOperationException </tt> si el iterador de la lista no lo hace
     * implementar la operación <tt>establecer</tt>.
     *
     * @param indice índice del elemento a reemplazar
     * @param elemento elemento para ser almacenado en la lista especificada
     * @return el elemento previamente en la posición especificada
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E establecer(int indice, E elemento) {
        try {
            ListIterator<E> e = listIterator(indice);
            E valorAntiguo = e.next();
            e.set(elemento);
            return valorAntiguo;
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: " + indice);
        }
    }

    /**
     * {@inheritDoc}      
     */
    @Override
    public abstract String imprimir();

    /**
     * Devuelve un iterador sobre los elementos en esta lista (en      
     * secuencia).
     * <p>
     * Esta implementación simplemente devuelve un iterador de lista sobre la
     * lista.
     *
     * @return un iterador sobre los elementos en esta lista (en la secuencia
     * apropiada)      
     */
    @Override
    public Iterator<E> iterator() {
        return this.listIterator();
    }

    /**
     * Devuelve un iterador de lista sobre los elementos en esta lista (en
     * secuencia).
     *
     * @param indice índice del primer elemento que se devolverá de la lista
     * iterador (mediante una llamada al método <code> next</code>)
     * @return un iterador de lista sobre los elementos en esta lista (en
     * secuencia)
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public abstract ListIterator<E> listIterator(int indice);

    /**
     * Devuelve el elemento en la posición especificada en esta lista.    
     * <p>
     * Esta implementación primero obtiene un iterador de lista que apunta al
     * elemento indexado (con <tt>listIterator(índice) </tt>). Entonces, se pone
     * el elemento que utiliza <tt> ListIterator.next </tt> y lo devuelve.
     *
     * @param indice índice del elemento a devolver.
     * @return el elemento en la posición especificada en esta lista
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E obtener(int indice) {
        try {
            return this.listIterator(indice).next();
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Índice: " + indice);
        }
    }

    /**
     * Elimina el elemento en la posición especificada en esta lista (operación
     * opcional). Cambia cualquier elemento posterior a la izquierda (resta uno
     * de sus índices). Devuelve el elemento que se eliminó de la lista.     
     * <p>
     * Esta implementación primero obtiene un iterador de lista que apunta al
     * elemento indexado (con <tt>listIterator(indice)</tt>). Entonces, elimina
     * el elemento con<tt>ListIterator.remove</tt>.      
     * <p>
     * Tenga en cuenta que esta implementación arrojará un<tt>
     * UnsupportedOperationException </tt> si el iterador de la lista no lo hace
     * implementar la operación <tt>remover</tt>.
     *
     * @param indice índice del elemento a eliminar
     * @return el elemento previamente en la posición especificada
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E remover(int indice) {
        try {
            ListIterator<E> e = listIterator(indice);
            E elemento = e.next();
            e.remove();
            return elemento;
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Índice: " + indice);
        }
    }
}
