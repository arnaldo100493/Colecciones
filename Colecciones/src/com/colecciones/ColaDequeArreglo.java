/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Implementación de matriz de tamaño variable de la interfaz {@link Deque}.
 * Formación los deques no tienen restricciones de capacidad; crecen según sea
 * necesario para apoyar uso. No son seguros para subprocesos; en ausencia de
 * factores externos sincronización, no admiten el acceso simultáneo por varios
 * hilos. Los elementos nulos están prohibidos. Es probable que esta clase sea
 * más rápida que {@link Pila} cuando se usa como una pila, y más rápido que
 * {@link ListaEnlazada} cuando se usa como una cola.
 * <p>
 * La mayoría de las operaciones {@code ArrayDeque} se ejecutan en tiempo
 * constante amortizado. Las excepciones incluyen {@link # remover (Objeto) remover}, {@link
 * #removerPrimeraOcurrencia removerPrimeraOcurrencia}, {@link #removeLastOccurrence
 * removerUltimaOcurrencia}, {@link #contiene contiene}, {@link #iterator
 * iterator.remover ()}, y las operaciones masivas, todas las cuales se ejecutan
 * en forma lineal hora.
 * <p>
 * Los iteradores devueltos por el método {
 *
 * @iterator} de esta clase son <i>a prueba de fallas</i>: si el deque se
 * modifica en cualquier momento después del iterador se crea, de cualquier
 * forma, excepto a través del propio {@code remove} del iterador método, el
 * iterador generalmente lanzará un {@link
 * ConcurrentModificationException}. Por lo tanto, frente a la concurrencia
 * modificación, el iterador falla rápida y limpiamente, en lugar de arriesgarse
 * comportamiento arbitrario, no determinista en un tiempo indeterminado en el
 * futuro.
 * <p>
 * Tenga en cuenta que no se puede garantizar el comportamiento a prueba de
 * errores de un iterador como lo es, en términos generales, es imposible hacer
 * ninguna garantía dura en el presencia de modificación concurrente no
 * sincronizada. Iteradores a prueba de fallas throw
 * {@code ConcurrentModificationException} sobre una base de mejor esfuerzo. Por
 * lo tanto, sería incorrecto escribir un programa que dependiera de esto
 * excepción por su corrección: <i>el comportamiento a prueba de fallas de los
 * iteradores debe usarse solo para detectar errores.</i>
 *
 * <p>
 * Esta clase y su iterador implementan todas las<em> métodos</em>
 * opcionales de {@link Coleccion} y {@link
 * Iterator} interfaces.  
 * <p>
 * Esta clase es miembro de la
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework </a>.
 * @since 1.6
 * @author FABAME
 * @param <E> el tipo de elementos contenidos en esta colección  
 */
public class ColaDequeArreglo<E> extends ColeccionAbstracta<E>
        implements ColaDeque<E>, Cloneable, Serializable {

    public ColaDequeArreglo() {

    }

    @Override
    public void agregarPrimero(E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregarUltimo(E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E elemento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void empujar(E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E encuestar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E encuestarPrimero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E encuestarUltimo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E obtenerPrimero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E obtenerUltimo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ofrecer(E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ofrecerPrimero(E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ofrecerUltimo(E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E ojear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E ojearPrimero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E ojearUltimo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E quitar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remover() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removerPrimeraOcurrencia(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removerUltimaOcurrencia(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E removerPrimero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E removerUltimo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int tamanio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
