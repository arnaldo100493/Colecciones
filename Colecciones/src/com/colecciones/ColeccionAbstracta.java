/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * Esta clase proporciona una implementación esquemática de la <tt>
 * Coleccion</tt>
 * interfaz, para minimizar el esfuerzo requerido para implementar esta
 * interfaz.<p>
 *
 * Para implementar una colección no modificable, el programador solo necesita
 * extender esta clase y proporcionar implementaciones para el
 * <tt>iterator</tt> y <tt>tamanio</tt> métodos.(El iterador devuelto por el
 * <tt>iterator</tt>
 * el método debe implementar <tt>hasNext</tt> y <tt>next</tt>.)
 * <p>
 *
 * Para implementar una colección modificable, el programador debe
 * adicionalmente anula el método <tt>agregar</tt> de esta clase (que de lo
 * contrario arroja un <tt>UnsupportedOperationException</tt>), y el iterador
 * devuelto por el método <tt>iterator</tt> también debe implementar su
 * método<tt> remover</tt> .
 * <p>
 *
 * El programador generalmente debe proporcionar un vacío (sin argumento) y
 * <tt> Coleccion</tt> constructor, según la recomendación en el Especificación
 * de la interfaz <tt>Coleccion</tt>.
 * <p>
 *
 * La documentación para cada método no abstracto en esta clase describe su
 * implementación en detalle. Cada uno de estos métodos puede ser anulado si la
 * colección que se está implementando admite una implementación más eficiente.
 * <p>
 *
 * Esta clase es un miembro de la  
 *
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework </a>. 
 *
 * @param <E> el tipo de elementos contenidos en esta colección
 * @author FABAME
 * @see Coleccion
 * @since 1.2  
 */
public abstract class ColeccionAbstracta<E> implements Coleccion<E> {

    //Atributos de la clase ColeccionAbstracta
    /**
     * El tamaño máximo de la matriz para asignar. Algunas máquinas virtuales
     * reservan algunas palabras de encabezado en una matriz. Los intentos de
     * asignar arreglos más grandes pueden resultar en OutOfMemoryError: el
     * tamaño de la matriz solicitada excede el límite de VM      
     */
    private static final int TAMANIO_MAXIMO_ARREGLO = Integer.MAX_VALUE - 8;

    //Constructor de la clase ColeccionAbstracta.
    /**
     * Único constructor. (Para invocación por constructores de subclase,
     * típicamente implícito.)      
     */
    protected ColeccionAbstracta() {

    }

    //Métodos de la clase ColeccionAbstracta.
    /**
     * {@inheritDoc}
     *
     * <p>
     * This implementation always throws an
     * <tt>UnsupportedOperationException</tt>.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public boolean agregar(E elemento) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc} 
     * <p>
     * Esta implementación itera sobre la colección especificada y agrega      
     * cada objeto devuelto por el iterador a esta colección, a su vez.      
     *      
     * <p>
     * Tenga en cuenta que esta implementación arrojará un
     * <tt>UnsupportedOperationException</tt> a menos que <tt>agregar</tt> sea
     * anulado (suponiendo que la colección especificada no esté vacía).
     *
     * @throws UnsupportedOperationException {@inheritDoc}      
     * @throws ClassCastException {@inheritDoc}
     * @throwsNullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     * @see #agregar(Object)
     */
    @Override
    public boolean agregarTodo(Coleccion<? extends E> coleccion) {
        boolean modificado = false;
        for (E elemento : coleccion) {
            if (this.agregar(elemento)) {
                modificado = true;
            }
        }
        return modificado;
    }

    /**
     * {@inheritDoc}   
     * <p>
     * Esta implementación itera sobre esta colección, verificando cada elemento
     * devuelto por el iterador para ver si está contenido en la colección
     * especificada. Si no está tan contenido, se elimina de esta colección con
     * el método <tt>remover</tt> del iterador.      
     * <p>
     * Tenga en cuenta que esta implementación arrojará un <tt>
     * UnsupportedOperationException</tt> si el iterador devuelto por el El
     * método <tt>iterator</tt> no implementa el método <tt>remover</tt>
     * y esta colección contiene uno o más elementos no presentes en colección
     * especificada
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException{@inheritDoc}
     * @see #remover(Object)
     * @see #contiene(Object)      
     */
    @Override
    public boolean conservarTodo(Coleccion<?> coleccion) {
        Objects.requireNonNull(coleccion);
        boolean modificado = false;
        Iterator<E> iterador = this.iterator();
        while (iterador.hasNext()) {
            if (!coleccion.contiene(iterador.next())) {
                iterador.remove();
                modificado = true;
            }
        }
        return modificado;
    }

    /**
     * {@inheritDoc}  
     * <p>
     * Esta implementación itera sobre los elementos en la colección,  
     * verificando cada elemento a su vez para ver si es equitativo con el
     * elemento especificado.
     *
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}      
     */
    @Override
    public boolean contiene(Object objeto) {
        Iterator<E> iterador = this.iterator();
        if (objeto == null) {
            while (iterador.hasNext()) {
                if (iterador.next() == null) {
                    return true;
                }
            }
        } else {
            while (iterador.hasNext()) {
                if (objeto.equals(iterador.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Esta implementación itera sobre la colección especificada, revisando cada
     * elemento devuelto por el iterador para ver si está contenido en esta
     * colección. Si todos los elementos son tan se devuelve <tt>true</tt>, de
     * lo contrario <tt>false</tt>.
     *
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @see #contiene(Object)      
     */
    @Override
    public boolean contieneTodo(Coleccion<?> coleccion) {
        for (Object elemento : coleccion) {
            if (!this.contiene(elemento)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc} 
     * <p>
     * Esta implementación devuelve <tt> tamanio () == 0 </tt>.      
     */
    @Override
    public boolean estaVacia() {
        return this.tamanio() == 0;
    }

    private static int granCapacidad(int capacidadMinima) {
        if (capacidadMinima < 0) { // desbordamiento
            throw new OutOfMemoryError("Tamaño de arreglo requerido "
                    + "demasiado "
                    + "grande");
        }
        return (capacidadMinima > TAMANIO_MAXIMO_ARREGLO)
                ? Integer.MAX_VALUE : TAMANIO_MAXIMO_ARREGLO;

    }

    /**
     * Devuelve un iterador sobre los elementos contenidos en esta colección.
     *
     * @return un iterador sobre los elementos contenidos en esta colección
     *      
     */
    @Override
    public abstract Iterator<E> iterator();

    /**
     * {@inheritDoc}
     * <p>
     * Esta implementación itera sobre esta colección, eliminando cada elemento
     * que usa la operación <tt>Iterator.remove</tt>. Más las implementaciones
     * probablemente optarán por anular este método para eficiencia.
     * <p>
     * Tenga en cuenta que esta implementación arrojará un<tt>
     * UnsupportedOperationException</tt> si el iterador devuelto por este El
     * método <tt> iterator</tt> de la colección no implementa el
     * <tt>remover</tt> método y esta colección no está vacía.
     *
     * @throws UnsupportedOperationException {@inheritDoc}      
     */
    @Override
    public void limpiar() {
        Iterator<E> iterador = this.iterator();
        while (iterador.hasNext()) {
            iterador.next();
            iterador.remove();
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Esta implementación devuelve un arreglo que contiene todos los elementos
     * devuelto por el iterador de esta colección, en el mismo orden, almacenado
     * en elementos consecutivos del arreglo, comenzando con el índice
     * {@code 0}. La longitud del arreglo devuelta es igual a la cantidad de
     * elementos devuelto por el iterador, incluso si cambia el tamaño de esta
     * colección durante la iteración, como podría suceder si la colección lo
     * permite modificación concurrente durante la iteración. El método
     * {@code size} es llamado solo como una sugerencia de optimización; el
     * resultado correcto es devuelto  incluso si el iterador devuelve una
     * cantidad diferente de elementos.
     * <p>
     * Este método es equivalente a:  
     * <pre>{@code
     *       * Lista <E> lista = new ListaArreglo<E>(tamanio());
     *       * for (E elemento: this)
     *       * lista.agregar(elemento);
     *       * return lista.paraFormar();
     *       *}</pre>      
     */
    @Override
    public Object[] paraFormar() {
        //Estimar el tamaño de la matriz; prepárate para ver más o menos elementos
        Object[] arreglo = new Object[this.tamanio()];
        Iterator<E> iterador = this.iterator();
        for (int i = 0; i < arreglo.length; i++) {
            if (!iterador.hasNext()) //Menos elementos de lo esperado
            {
                return Arrays.copyOf(arreglo, i);
            }
            arreglo[i] = iterador.next();
        }
        return iterador.hasNext() ? terminarParaFormar(arreglo, iterador) : arreglo;
    }

    /**
     * {@inheritDoc}      
     * <p>
     * Esta implementación devuelve un arreglo que contiene todos los elementos
     * devuelto por el iterador de esta colección en el mismo orden, almacenado
     * en elementos consecutivos del arreglo, comenzando con el índice
     * {@code 0}. Si la cantidad de elementos devueltos por el iterador es
     * demasiado grande para encajar en el arreglo especificado, luego los
     * elementos se devuelven en un arreglo recién asignado con una longitud
     * igual a la cantidad de elementos devuelto por el iterador, incluso si el
     * tamaño de esta colección cambios durante la iteración, como podría
     * suceder si la colección lo permite modificación concurrente durante la
     * iteración. El método {@code size} es llamado solo como una sugerencia de
     * optimización; el resultado correcto es devuelto incluso si el iterador
     * devuelve una cantidad diferente de elementos.      
     * <p>
     * Este método es equivalente a:      
     * <pre> {@code
     * Lista <E> lista = new ListaVector <E> (tamanio());
     * for (E elemento: this)
     * lista.add(elemento);
     * return lista.paraFormar(arreglo);
     * }</pre>
     *
     * @throws ArrayStoreException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     *
     */
    @Override
    public <T> T[] paraFormar(T[] arreglo) {
        //Estimar el tamaño del arreglo; prepárate para ver más o menos elementos
        int tamanio = this.tamanio();
        T[] r = arreglo.length >= tamanio ? arreglo : (T[]) java.lang.reflect.Array.newInstance(arreglo.getClass().getComponentType(), tamanio);
        Iterator<E> iterador = this.iterator();

        for (int i = 0; i < r.length; i++) {
            if (!iterador.hasNext()) { //Menos elementos de lo esperado
                if (arreglo == r) {
                    r[i] = null; //Terminación nula
                } else if (arreglo.length < i) {
                    return Arrays.copyOf(r, i);
                } else {
                    System.arraycopy(r, 0, arreglo, 0, i);
                    if (arreglo.length > i) {
                        arreglo[i] = null;
                    }
                }
                return arreglo;
            }
            r[i] = (T) iterador.next();
        }
        //Más elementos de lo esperado
        return iterador.hasNext() ? terminarParaFormar(r, iterador) : r;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Esta implementación itera sobre la colección buscando el elemento
     * especificado. Si encuentra el elemento, elimina el elemento de la
     * colección usando el método remove del iterador.    
     * <p>
     * Tenga en cuenta que esta implementación arroja un <tt>
     * UnsupportedOperationException</tt> si el iterador devuelto por este El
     * método del iterador de la colección no implementa el <tt>
     * remover</tt>
     * método y esta colección contiene el objeto especificado.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}      
     */
    @Override
    public boolean remover(Object objeto) {
        Iterator<E> iterador = this.iterator();
        if (objeto == null) {
            while (iterador.hasNext()) {
                if (iterador.next() == null) {
                    iterador.remove();
                    return true;
                }
            }
        } else {
            while (iterador.hasNext()) {
                if (objeto.equals(iterador.next())) {
                    iterador.remove();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc} 
     * <p>
     * Esta implementación itera sobre esta colección, verificando cada elemento
     * devuelto por el iterador para ver si está contenido en la colección
     * especificada. Si está tan contenido, se elimina de esta colección con el
     * método <tt>remover</tt> del iterador.      
     * <p>
     * Tenga en cuenta que esta implementación arrojará un
     * <tt>UnsupportedOperationException</tt> si el iterador devuelto por el  El
     * método <tt>iterator</tt> no implementa el método
     * <tt>remover</tt>
     * y esta colección contiene uno o más elementos en común con el colección
     * especificada
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc} @see #remover(Object)
     * @see #contiene(Object)      
     */
    @Override
    public boolean removerTodo(Coleccion<?> coleccion) {
        Objects.requireNonNull(coleccion);
        boolean modificado = false;
        Iterator<?> iterador = this.iterator();
        while (iterador.hasNext()) {
            if (coleccion.contiene(iterador.next())) {
                iterador.remove();
                modificado = true;
            }
        }
        return modificado;
    }

    @Override
    public abstract int tamanio();

    /**
     * Reasigna la matriz que se utiliza dentro de Array cuando el iterador
     * devolvió más elementos de lo esperado, y termina de llenarlo de el
     * iterador.
     *
     * @param arreglo el arreglo, repleta de elementos previamente almacenados
     * @param iterador el iterador en progreso sobre esta colección
     * @return arreglo que contiene los elementos en la matriz dada, más
     * cualquier       * otros elementos devueltos por el iterador, recortados a
     * medida      
     */
    @SuppressWarnings("unchecked")
    private static <T> T[] terminarParaFormar(T[] arreglo, Iterator<?> iterador) {
        int i = arreglo.length;
        while (iterador.hasNext()) {
            int capacidad = arreglo.length;
            if (i == capacidad) {
                int newCap = capacidad + (capacidad >> 1) + 1;
                //código consciente de desbordamiento
                if (newCap - TAMANIO_MAXIMO_ARREGLO > 0) {
                    newCap = granCapacidad(capacidad + 1);
                }
                arreglo = Arrays.copyOf(arreglo, newCap);
            }
            arreglo[i++] = (T) iterador.next();
        }
        //recorte si está sobreasignado
        return (i == arreglo.length) ? arreglo : Arrays.copyOf(arreglo, i);
    }

    /**
     * Devuelve una representación de cadena de esta colección. La cuerda la
     * representación consiste en una lista de los elementos de la colección en
     * el orden en que son devueltos por su iterador, encerrado entre corchetes
     * (<tt> "[]" </tt>). Los elementos adyacentes están separados por los
     * personajes <tt> "," </tt>
     * (coma y espacio). Los elementos se convierten en cadenas como por
     * {@link String # valueOf (Object)}.
     *
     * @return una representación de cadena de esta colección      
     */
    @Override
    public String toString() {
        Iterator<E> iterator = this.iterator();
        if (!iterator.hasNext()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E elemento = iterator.next();
            sb.append(elemento == this ? "(this Coleccion)" : elemento);
            if (!iterator.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
    }

}
