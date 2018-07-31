/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * Clase para guardar y manipular elementos en una lista enlazada.
 */
/**
 * Implementación de lista doblemente enlazada de {@code Lista} y
 * {@code ColaDeque} interfaces. Implementa todas las operaciones de lista
 * opcionales, y permite todo elementos (incluido {@code null}).  
 * <p>
 * Todas las operaciones funcionan como se podría esperar para un enlace doble
 * lista. Las operaciones que se indexan en la lista atravesarán la lista desde
 * el comienzo o el final, lo que esté más cerca del índice especificado.  
 * <p>
 * <strong>Tenga en cuenta que esta implementación no está sincronizada.
 * </strong>
 * Si varios subprocesos acceden a una lista vinculada al mismo tiempo, y al
 * menos uno de los hilos modifica estructuralmente la lista, <i>debe</i> ser
 * sincronizado externamente. (Una modificación estructural es cualquier
 * operación que agrega o elimina uno o más elementos; simplemente establecer el
 * valor de un elemento no es una modificación estructural.) Esto es típicamente
 * logrado sincronizando en algún objeto que naturalmente encapsula la lista. Si
 * no existe tal objeto, la lista debe ser "envuelta" usando el
 * {@link Colecciones # synchronizedList Colecciones.synchronizedList} método.
 * Esto se hace mejor en el momento de la creación, para evitar accidentes
 * acceso no sincronizado a la lista:
 * <pre>
 * Lista lista = Coleccioens.synchronizedList (new ListaEnlazada (...));
 * </pre>
 * <p>
 * Los iteradores devueltos por {
 *
 * @iterador de la clase} y Los métodos de {@code listIterator} son
 * <i>rápidos:</i>: si la lista es estructuralmente modificado en cualquier
 * momento después de que se crea el iterador, en de cualquier forma, excepto a
 * través de la {@code remove} propia del iterador o {@code agregar} métodos, el
 * iterador lanzará un {@link
 * ConcurrentModificationException}. Por lo tanto, frente a la concurrencia
 * modificación, el iterador falla rápida y limpiamente, en lugar de arriesgar
 * el comportamiento arbitrario, no determinista en un indeterminado tiempo en
 * el futuro.  
 * <p>
 * Tenga en cuenta que no se puede garantizar el comportamiento a prueba de
 * errores de un iterador como lo es, en términos generales, es imposible hacer
 * ninguna garantía dura en el presencia de modificación concurrente no
 * sincronizada. Iteradores a prueba de fallas lanza
 * {@code ConcurrentModificationException} sobre una base de mejor esfuerzo. Por
 * lo tanto, sería incorrecto escribir un programa que dependiera de esto
 * excepción por su corrección: <i>el comportamiento a prueba de fallas de los
 * iteradores debe usarse solo para detectar errores.</i>
 *
 * <p>
 * Esta clase es miembro de la
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 * @since 1.2
 * @author FABAME
 * @see Lista
 * @see ListaArreglo
 * @param <E> el tipo de elementos contenidos en esta colección  
 */
public class ListaEnlazada<E> extends ListaSecuencialAbstracta<E>
        implements Lista<E>, ColaDeque<E>, Cloneable, Serializable {

    //Atributos de la clase ListaEnlazada.
    /**
     * Número serial version UID generado de la clase ListaEnlazada.
     */
    private static final long serialVersionUID = -8748872431550970992L;

    transient int tamanio = 0;

    /**
     * Puntero al primer nodo.Invariante: (primero == null && ultimo == null) ||
     * (primero.anterior == null && primero.elemento ! = null)      
     */
    transient Nodo<E> primero;

    /**
     * Puntero al último nodo. Invariante: (primero == nulo && ultimo == null)
     * || (ultimo.siguiente == null && last.item! = null)      
     */
    transient Nodo<E> ultimo;

    //Constructores de la clase ListaEnlazada.
    /**
     * Construye una lista vacía.      
     */
    public ListaEnlazada() {
        this.primero = null;
        this.ultimo = null;
    }

    /**
     * Construye una lista que contiene los elementos de la especificada
     * colección, en el orden en que son devueltos por la colección iterador.
     *
     * @param coleccion la colección cuyos elementos se deben colocar en esta
     * lista
     * @throws NullPointerException si la colección especificada es nula      
     */
    public ListaEnlazada(Coleccion<? extends E> coleccion) {
        this();
        this.agregarTodo(coleccion);
    }

    //Métodos de la clase ListaEnlazada.
    /**
     * Añade el elemento especificado al final de esta lista.      
     * <p>
     * Este método es equivalente a {@link #agregarUltimo}.
     *
     * @param elemento elemento que se adjuntará a esta lista
     * @return {@code true} (según lo especificado por
     * {@link Coleccion # agregar})      
     */
    @Override
    public boolean agregar(E elemento) {
        this.enlazarUltimo(elemento);
        return true;
    }

    /**
     * Inserta el elemento especificado en la posición especificada en esta
     * lista. Cambia el elemento actualmente en esa posición (si corresponde) y
     * cualquier elementos posteriores a la derecha (agrega uno a sus
     * índices).índice de índice
     *
     * @param indice indice que se debe insertar el elemento especificado
     * elemento
     * @param elemento elemento a insertar
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public void agregar(int indice, E elemento) {
        this.verificarIndicePosicion(indice);

        if (indice == this.tamanio) {
            this.enlazarUltimo(elemento);
        } else {
            this.enlazarAntes(elemento, this.nodo(indice));
        }
    }

    /**
     * Inserta el elemento especificado al principio de esta lista.
     *
     * @param elemento el elemento para agregar      
     */
    @Override
    public void agregarPrimero(E elemento) {
        this.enlazarPrimero(elemento);
    }

    /**
     * Agrega todos los elementos en la colección especificada hasta el final de
     * esta lista, en el orden en que la especifican iterador de la colección.
     * El comportamiento de esta operación no está definido si la colección
     * especificada se modifica mientras la operación está en Progreso. (Tenga
     * en cuenta que esto ocurrirá si la colección especificada es esta lista, y
     * no es vacía.)
     *
     * @param coleccion colección que contiene elementos para agregar a esta
     * lista
     * @return {@code true} si esta lista cambió como resultado dela llamada
     * @throws NullPointerException si la colección especificada es nula      
     */
    @Override
    public boolean agregarTodo(Coleccion<? extends E> coleccion) {
        return this.agregarTodo(this.tamanio, coleccion);
    }

    /**
     * Inserta todos los elementos en la colección especificada en este lista,
     * comenzando en la posición especificada. Cambia el elemento actualmente en
     * esa posición (si corresponde) y cualquier elemento posterior a la derecha
     * (aumenta sus índices). Los nuevos elementos aparecerán en la lista en el
     * orden en que son devueltos por iterador de la colección especificada.
     *
     * @param indice índice en el que insertar el primer elemento de la
     * colección especificada
     * @param coleccion colección que contiene elementos para agregar a esta
     * lista
     * @return {@code true} si esta lista cambió como resultado de la llamada
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException si la colección especificada es nula      
     */
    @Override
    public boolean agregarTodo(int indice, Coleccion<? extends E> coleccion) {
        this.verificarIndicePosicion(indice);

        Object[] arreglo = coleccion.paraFormar();
        int numeroNuevo = arreglo.length;
        if (numeroNuevo == 0) {
            return false;
        }

        Nodo<E> anterior, siguiente;
        if (indice == this.tamanio) {
            siguiente = null;
            anterior = this.ultimo;
        } else {
            siguiente = nodo(indice);
            anterior = siguiente.anterior;
        }

        for (Object objeto : arreglo) {
            @SuppressWarnings("unchecked")
            E elemento = (E) objeto;
            Nodo<E> nuevoNodo = new Nodo<>(anterior, elemento, null);
            if (anterior == null) {
                this.primero = nuevoNodo;
            } else {
                anterior.siguiente = nuevoNodo;
            }
            anterior = nuevoNodo;
        }

        if (siguiente == null) {
            this.ultimo = anterior;
        } else {
            anterior.siguiente = siguiente;
            siguiente.anterior = anterior;
        }

        this.tamanio += numeroNuevo;
        this.conteoModulo++;
        return true;
    }

    /**
     * Añade el elemento especificado al final de esta lista.      
     * <p>
     * Este método es equivalente a {@link #agregar}.
     *
     * @param elemento el elemento para agregar      
     */
    @Override
    public void agregarUltimo(E elemento) {
        this.enlazarUltimo(elemento);
    }

    public int buscar(Object objeto) {
        int i = this.ultimoIndiceDe(objeto);

        if (i >= 0) {
            return this.tamanio - i;
        }
        return -1;
    }

    /**
     * Devuelve una copia superficial de esta {
     *
     * @ListaEnlazada}. (Los elementos ellos mismos no están clonados.)
     * @return una copia superficial de esta instancia de {
     * @ListaEnlazada}      
     */
    @Override
    public Object clone() {
        ListaEnlazada<E> clone = this.superClone();

        //Poner el clon en estado "virgen".
        clone.primero = clone.ultimo = null;
        clone.tamanio = 0;
        clone.conteoModulo = 0;

        //Inicializa clon con nuestros elementos.
        for (Nodo<E> nodo = this.primero; nodo != null; nodo = nodo.siguiente) {
            clone.agregar(nodo.elemento);
        }

        return clone;
    }

    /**
     * Desenlaza el primer nodo no nulo nodo.      
     */
    private E desenlazar(Nodo<E> nodo) {
        // assert x != null;
        final E elemento = nodo.elemento;
        final Nodo<E> siguiente = nodo.siguiente;
        final Nodo<E> anterior = nodo.anterior;

        if (anterior == null) {
            this.primero = siguiente;
        } else {
            anterior.siguiente = siguiente;
            nodo.anterior = null;
        }

        if (siguiente == null) {
            this.ultimo = anterior;
        } else {
            siguiente.anterior = anterior;
            nodo.siguiente = null;
        }

        nodo.elemento = null;
        this.tamanio--;
        this.conteoModulo++;
        return elemento;
    }

    /**
     * Devuelve {@code true} si esta lista contiene el elemento especificado.Más
     * formalmente, devuelve {@code true} si y solo si esta lista contieneal
     * menos un elemento {@code elemento} tal que      
     * * <tt>(objeto == null & nbsp;? & nbsp; elemento == null & nbsp;: & nbsp;
     * objeto.equals (elemento))</tt>.
     *
     * @param objeto elemento cuya presencia en esta lista debe probarse @return
     * {@code true} si esta lista contiene el elemento especificado      
     */
    @Override
    public boolean contiene(Object objeto) {
        return this.indiceDe(objeto) != -1;
    }

    /**
     * @since 1.6
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new ListaEnlazada.DescendingIterator();
    }

    /**
     * Desenlaza el primer nodo no nulo primero.      
     */
    private E desenlazarPrimero(Nodo<E> primero) {
        // assert f == first && f != null;
        final E elemento = primero.elemento;
        final Nodo<E> siguiente = primero.siguiente;
        primero.elemento = null;
        primero.siguiente = null; //Ayuda GC.
        primero = siguiente;
        if (siguiente == null) {
            this.ultimo = null;
        } else {
            siguiente.anterior = null;
        }
        this.tamanio--;
        this.conteoModulo++;
        return elemento;
    }

    /**
     * Desenlaza el último nodo no nulo ultimo.      
     */
    private E desenlazarUltimo(Nodo<E> ultimo) {
        // assert l == last && l != null;
        final E elemento = ultimo.elemento;
        final Nodo<E> anterior = ultimo.anterior;
        ultimo.elemento = null;
        ultimo.anterior = null; //Ayuda GC.
        this.ultimo = anterior;
        if (anterior == null) {
            this.primero = null;
        } else {
            anterior.siguiente = null;
        }
        this.tamanio--;
        this.conteoModulo++;
        return elemento;
    }

    /**
     * Recupera, pero no elimina, el encabezado (primer elemento) de esta lista.
     *
     * @return el encabezado de esta lista
     * @throws NoSuchElementException si esta lista está vacía
     * @since 1.5      
     */
    @Override
    public E elemento() {
        return this.obtenerPrimero();
    }

    /**
     * Empuja un elemento sobre la pila representada por esta lista. En otra  
     * palabras, inserta el elemento al principio de esta lista.    
     * <p>
     * Este método es equivalente a {@link #agregarPrimero}.
     *
     * @param elemento el elemento para empujar
     * @since 1.6      
     */
    @Override
    public void empujar(E elemento) {
        this.agregarPrimero(elemento);
    }

    @Override
    public E encuestar() {
        final Nodo<E> primero = this.primero;
        return (primero == null) ? null : this.desenlazarPrimero(primero);
    }

    /**
     * Recupera y elimina el primer elemento de esta lista, o devuelve
     * {@code null} si esta lista está vacía. @return el primer elemento de esta
     * lista, o {@code null} si esta lista está vacía
     *
     * @since 1.6      
     */
    @Override
    public E encuestarPrimero() {
        final Nodo<E> primero = this.primero;
        return (primero == null) ? null : this.desenlazarPrimero(primero);
    }

    /**
     * Recupera y elimina el último elemento de esta lista, o devuelve
     * {@code null} si esta lista está vacía. @return el último elemento de esta
     * lista, o {@code null} si esta lista está vacía
     *
     * @since 1.6      
     */
    @Override
    public E encuestarUltimo() {
        final Nodo<E> ultimo = this.ultimo;
        return (ultimo == null) ? null : this.desenlazarUltimo(ultimo);
    }

    /**
     * Inserta el elemento elemento antes del nodo no nulo succ.      
     */
    void enlazarAntes(E elemento, Nodo<E> siguiente) {
        //assert succ != null;
        final Nodo<E> anterior = siguiente.anterior;
        final Nodo<E> nuevoNodo = new Nodo<>(anterior, elemento, siguiente);
        siguiente.anterior = nuevoNodo;
        if (anterior == null) {
            this.primero = nuevoNodo;
        } else {
            anterior.siguiente = nuevoNodo;
        }
        this.tamanio++;
        this.conteoModulo++;
    }

    /**
     * Enlaza elemento como primer elemento.      
     */
    private void enlazarPrimero(E elemento) {
        final Nodo<E> primero = this.primero;
        final Nodo<E> nuevoNodo = new Nodo<>(null, elemento, primero);
        this.primero = nuevoNodo;
        if (primero == null) {
            this.ultimo = nuevoNodo;
        } else {
            primero.anterior = nuevoNodo;
        }
        this.tamanio++;
        this.conteoModulo++;
    }

    /**
     * Enlaza elemento como último elemento.      
     */
    void enlazarUltimo(E elemento) {
        final Nodo<E> ultimo = this.ultimo;
        final Nodo<E> nuevoNodo = new Nodo<>(ultimo, elemento, null);
        this.ultimo = nuevoNodo;
        if (ultimo == null) {
            this.primero = nuevoNodo;
        } else {
            ultimo.siguiente = nuevoNodo;
        }
        this.tamanio++;
        this.conteoModulo++;
    }

    /**
     * Indica si el argumento es el índice de un elemento existente.      
     */
    private boolean esIndiceElemento(int indice) {
        return indice >= 0 && indice < this.tamanio;
    }

    /**
     * Indica si el argumento es el índice de una posición válida para un
     * iterador o una operación de agregar.      
     */
    private boolean esIndicePosicion(int indice) {
        return indice >= 0 && indice <= this.tamanio;
    }

    /**
     * Guarda el estado de esta instancia de {
     *
     * @ListaEnlazada} en una transmisión (es decir, lo serializa).
     * @serialData El tamaño de la lista (el número de elementos que contiene)
     * se emite (int), seguido de todos sus elementos (cada uno un Objeto) en el
     * orden correcto.      
     */
    private void escribirObjeto(ObjectOutputStream salida) throws IOException {
        //Escribe cualquier magia de serialización oculta.
        salida.defaultWriteObject();

        //Escribe el tamaño
        salida.writeInt(this.tamanio);

        // Escribe todos los elementos en el orden correcto.
        for (Nodo<E> nodo = this.primero; nodo != null; nodo = nodo.siguiente) {
            salida.writeObject(nodo.elemento);
        }
    }

    /**
     * Reemplaza el elemento en la posición especificada en esta lista con el
     * elemento especificado.  índice de índice
     *
     * @param indice índioe del elemento a reemplazar
     * @param elemento elemento para ser almacenado en la posición especificada
     * @return el elemento previamente en la posición especificada
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E establecer(int indice, E elemento) {
        this.verificarIndiceElemento(indice);
        Nodo<E> nodo = nodo(indice);
        E valorAntiguo = nodo.elemento;
        nodo.elemento = elemento;
        return valorAntiguo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String imprimir() {
        String s = "";
        for (int i = 0; i < this.tamanio; i++) {
            s += this.obtener(i) + "\n";
        }
        return s;
    }

    /**
     *  Devuelve el índice de la primera aparición del elemento especificado en
     * esta lista, o -1 si esta lista no contiene el elemento. Más formalmente,
     * devuelve el índice más bajo {@code i} tal que <tt>(objeto == null &
     * nbsp;? & nbsp; obteber (i) == null & nbsp;: & nbsp; objeto.equals
     * (obtener(i)))</tt>, o -1 si no hay tal índice.
     *
     * @param objeto elemento para buscar      
     *
     * @return el índice de la primera aparición del elemento especificado en
     * esta lista, o -1 si esta lista no contiene el elemento      
     */
    @Override
    public int indiceDe(Object objeto) {
        int indice = 0;
        if (objeto == null) {
            for (Nodo<E> nodo = this.primero; nodo != null; nodo = nodo.siguiente) {
                if (nodo.elemento == null) {
                    return indice;
                }
                indice++;
            }
        } else {
            for (Nodo<E> nodo = this.primero; nodo != null; nodo = nodo.siguiente) {
                if (objeto.equals(nodo.elemento)) {
                    return indice;
                }
                indice++;
            }
        }
        return -1;
    }

    /**
     * Reconstituye esta instancia de {@code ListaEnalazada} de una flujo(es
     * decir, lo deserializa).      
     */
    @SuppressWarnings("unchecked")
    private void leerObjeto(ObjectInputStream entrada) throws IOException, ClassNotFoundException {
        //Leer en cualquier magia de serialización oculta.
        entrada.defaultReadObject();

        //Leer en tamaño.
        int tamanio = entrada.readInt();

        // Leer en todos los elementos en el orden correcto.
        for (int i = 0; i < tamanio; i++) {
            this.enlazarUltimo((E) entrada.readObject());
        }
    }

    /**
     * Elimina todos los elementos de esta lista. La lista estará vacía después
     * de que regrese esta llamada.      
     */
    @Override
    public void limpiar() {
        //Borrar todos los enlaces entre nodos es "innecesario", pero:
        //-ayuda a un GC generacional si los nodos desechados habitan
        //más de una generación
        //-está seguro de liberar memoria incluso si hay un Iterador alcanzable
        for (Nodo<E> nodo = this.primero; nodo != null;) {
            Nodo<E> siguiente = nodo.siguiente;
            nodo.elemento = null;
            nodo.siguiente = null;
            nodo.anterior = null;
            nodo = siguiente;
        }
        this.primero = this.ultimo = null;
        this.tamanio = 0;
        this.conteoModulo++;
    }

    /**
     * Devuelve un iterador de lista de los elementos en esta lista (en
     * secuencia), comenzando en la posición especificada en la lista. Obedece
     * el contrato general de {@code List.listIterator (int)}.
     * <p>
     * El iterador de lista es <i>fall-fast</i>: si la lista es estructuralmente
     * modificado en cualquier momento después de que se crea el iterador, de
     * cualquier forma excepto a través del propio {@code remove} del
     * list-iterator o {@code agregar} métodos, el list-iterator lanzará un
     * {@code ConcurrentModificationException}. Por lo tanto, frente a
     * modificación simultánea, el iterador falla de forma rápida y limpia, en
     * lugar de que arriesgar el comportamiento arbitrario, no determinista en
     * un indeterminado tiempo en el futuro.índice índice
     *
     * @param indice índice del primer elemento que se devolverá desde el
     * list-iterator (por una llamada a {@code next})
     * @return a ListIterator de los elementos en esta lista (en secuencia),
     * comenzando en la posición especificada en la lista  
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @see Lista # listIterator (int)      
     */
    @Override
    public ListIterator<E> listIterator(int indice) {
        return this.listIterator();
    }

    /**
     * Construye un mensaje de detalle de IndexOutOfBoundsException. De las
     * muchas refactorizaciones posibles del código de manejo de errores, este
     * "perfilado" funciona mejor con máquinas virtuales tanto de servidor como
     * de cliente.      
     */
    private String mostrarMensajeFueraDeLosLimites(int indice) {
        return "Índice: " + indice + ", Tamaño: " + this.tamanio;
    }

    /**
     * Devuelve el nodo (no nulo) en el índice del elemento especificado.      
     */
    Nodo<E> nodo(int indice) {
        // assert isElementIndex(index);

        if (indice < (this.tamanio >> 1)) {
            Nodo<E> nodo = this.primero;
            for (int i = 0; i < indice; i++) {
                nodo = nodo.siguiente;
            }
            return nodo;
        } else {
            Nodo<E> nodo = this.ultimo;
            for (int i = this.tamanio - 1; i > indice; i--) {
                nodo = nodo.anterior;
            }
            return nodo;
        }
    }

    /**
     * Devuelve el elemento en la posición especificada en esta lista.
     *
     * @param indice índice del elemento a devolver
     * @return el elemento en la posición especificada en esta lista
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E obtener(int indice) {
        this.verificarIndiceElemento(indice);
        return this.nodo(indice).elemento;
    }

    /**
     * Devuelve el primer elemento en esta lista.
     *
     * @return el primer elemento en esta lista
     * @throws NoSuchElementException si esta lista está vacía      
     */
    @Override
    public E obtenerPrimero() {
        final Nodo<E> primero = this.primero;
        if (primero == null) {
            throw new NoSuchElementException();
        }
        return primero.elemento;
    }

    /**
     * Devuelve el último elemento en esta lista.
     *
     * @return el último elemento en esta lista
     * @throws NoSuchElementException si esta lista está vacía      
     */
    @Override
    public E obtenerUltimo() {
        final Nodo<E> ultimo = this.ultimo;
        if (ultimo == null) {
            throw new NoSuchElementException();
        }
        return ultimo.elemento;
    }

    /**
     * Agrega el elemento especificado como la cola (último elemento) de esta
     * lista. @param e el elemento para agregar
     *
     * @return {@code true} (según lo especificado por {@link Cola # ofrecer})
     * @since 1.5      
     */
    @Override
    public boolean ofrecer(E elemento) {
        return this.agregar(elemento);
    }

    /**
     * Inserta el elemento especificado al principio de esta lista.
     *
     * @param elemento el elemento para insertar
     * @return {@code true} (según lo especificado por
     * {@link ColaDeque # ofrecerPrimero})
     * @since 1.6      
     */
    @Override
    public boolean ofrecerPrimero(E elemento) {
        this.agregarPrimero(elemento);
        return true;
    }

    /**
     * Inserta el elemento especificado al final de esta lista.
     *
     * @param elemento el elemento para insertar
     * @return {@code true} (según lo especificado por
     * {@link ColaDeque # ofrecerUltimo})      
     * @since 1.6      
     */
    @Override
    public boolean ofrecerUltimo(E elemento) {
        this.agregarUltimo(elemento);
        return true;
    }

    /**
     * Recupera, pero no elimina, el encabezado (primer elemento) de esta lista.
     *
     * @return el encabezado de esta lista, o {@code null} si esta lista está
     * vacía
     * @since 1.5      
     */
    @Override
    public E ojear() {
        final Nodo<E> primero = this.primero;
        return (primero == null) ? null : primero.elemento;
    }

    /**
     * Recupera, pero no elimina, el primer elemento de esta lista, o devuelve
     * {@code null} si esta lista está vacía.
     *
     * @return el primer elemento de esta lista, o {@code null} si esta lista
     * está vacía
     * @since 1.6      
     */
    @Override
    public E ojearPrimero() {
        final Nodo<E> primero = this.primero;
        return (primero == null) ? null : primero.elemento;
    }

    /**
     * Recupera, pero no elimina, el último elemento de esta lista, o devuelve
     * {@code null} si esta lista está vacía.
     *
     * @return el último elemento de esta lista, o {@code null} si esta lista
     * está vacía
     * @since 1.6      
     */
    @Override
    public E ojearUltimo() {
        final Nodo<E> ultimo = this.ultimo;
        return (ultimo == null) ? null : ultimo.elemento;
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos en esta lista en la
     * secuencia correcta (del primer al último elemento).    
     * <p>
     * El arreglo devuelto será "seguro" porque no hay referencias a el
     * mantenido por esta lista. (En otras palabras, este método debe asignar
     * una nuevo arreglo). La persona que llama es libre de modificar el arreglo
     * devuelto.   
     * <p>
     * Este método actúa como puente entre la basada en arreglos y la basada en
     * colecciones APIs.
     *
     * @return un arreglo que contiene todos los elementos en esta lista en la
     * secuencia apropiada      
     */
    @Override
    public Object[] paraFormar() {
        Object[] resultado = new Object[this.tamanio];
        int i = 0;
        for (Nodo<E> nodo = this.primero; nodo != null; nodo = nodo.siguiente) {
            resultado[i++] = nodo.elemento;
        }
        return resultado;
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos en esta lista en
     * secuencia correcta (del primer al último elemento); el tipo de tiempo de
     * ejecución del arreglo devuelta es la del arreglo especificada. Si la
     * lista se ajusta en el arreglo especificado, se devuelve allí. De lo
     * contrario, un nuevo arreglo se asigna con el tipo de tiempo de ejecución
     * del arreglo especificada y el tamaño de esta lista.
     *
     *      
     * <p>
     * Si la lista se ajusta a el arreglo especificada con espacio de sobra (es
     * decir, el arreglo tiene más elementos que la lista), el elemento en el
     * arreglo inmediatamente después del final de la lista se establece en
     * {@code null}.(Esto es útil para determinar la longitud de la lista
     * <i>solamente</i> si la persona que llama sabe que la lista no contiene
     * ningún elemento nulo.)      
     * <p>
     * Al igual que el método {@link #paraFormar()}, este método actúa como
     * puente entre API basada en colección y basada en colección. Además, este
     * método permite control preciso sobre el tipo de tiempo de ejecución de
     * lel arreglo de salida, y puede,bajo ciertas circunstancias, se puede usar
     * para ahorrar costos de asignación.        
     * <p>
     * Supongamos que {@code x} es una lista conocida por contener solo cadenas.
     * El siguiente código puede usarse para volcar la lista en un nuevo arreglo
     * asignado de {@code String}:   
     * <pre>
     * String [] y = x.paraFormar(new String[0]);</pre> Tenga en cuenta que
     * {@code paraFormar(new Object[0])} es idéntico en función a
     * {@code paraFormar()}.
     *
     * @param arreglo el arreglo en el que se encuentran los elementos de la
     * lista ser almacenado, si es lo suficientemente grande; de lo contrario,
     * un nuevo arreglo de el mismo tipo de tiempo de ejecución se asigna para
     * este propósito.
     * @return un arreglo que contiene los elementos de la lista
     * @throws ArrayStoreException si el tipo de tiempo de ejecución del arreglo
     * especificado no es un supertipo del tipo de tiempo de ejecución de cada
     * elemento en esta lista
     * @throws NullPointerException si el arreglo especificado es nulo      
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] paraFormar(T[] arreglo) {
        if (arreglo.length < this.tamanio) {
            arreglo = (T[]) Array.newInstance(
                    arreglo.getClass().getComponentType(), this.tamanio);
        }
        int i = 0;
        Object[] resultado = arreglo;
        for (Nodo<E> nodo = this.primero; nodo != null; nodo = nodo.siguiente) {
            resultado[i++] = nodo.elemento;
        }

        if (arreglo.length > this.tamanio) {
            arreglo[this.tamanio] = null;
        }

        return arreglo;
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos en esta lista en
     * secuencia correcta (del primer al último elemento); el tipo de tiempo de
     * ejecución del arreglo devuelta es la del arreglo especificada. Si la
     * lista se ajusta en el arreglo especificado, se devuelve allí. De lo
     * contrario, un nuevo arreglo se asigna con el tipo de tiempo de ejecución
     * del arreglo especificada y el tamaño de esta lista.
     *
     *      
     * <p>
     * Si la lista se ajusta a el arreglo especificada con espacio de sobra (es
     * decir, el arreglo tiene más elementos que la lista), el elemento en el
     * arreglo inmediatamente después del final de la lista se establece en
     * {@code null}.(Esto es útil para determinar la longitud de la lista
     * <i>solamente</i> si la persona que llama sabe que la lista no contiene
     * ningún elemento nulo.)      
     * <p>
     * Al igual que el método {@link #paraFormar()}, este método actúa como
     * puente entre API basada en colección y basada en colección. Además, este
     * método permite control preciso sobre el tipo de tiempo de ejecución de
     * lel arreglo de salida, y puede,bajo ciertas circunstancias, se puede usar
     * para ahorrar costos de asignación.        
     * <p>
     * Supongamos que {@code x} es una lista conocida por contener solo cadenas.
     * El siguiente código puede usarse para volcar la lista en un nuevo arreglo
     * asignado de {@code String}:   
     * <pre>
     * String [] y = x.paraFormar(new String[0]);</pre> Tenga en cuenta que
     * {@code paraFormar(new Object[0])} es idéntico en función a
     * {@code paraFormar()}.
     *
     * @param arreglo el arreglo en el que se encuentran los elementos de la
     * lista ser almacenado, si es lo suficientemente grande; de lo contrario,
     * un nuevo arreglo de el mismo tipo de tiempo de ejecución se asigna para
     * este propósito.
     * @return un arreglo que contiene los elementos de la lista
     * @throws ArrayStoreException si el tipo de tiempo de ejecución del arreglo
     * especificado no es un supertipo del tipo de tiempo de ejecución de cada
     * elemento en esta lista
     * @throws NullPointerException si el arreglo especificado es nulo      
     */
    @SuppressWarnings("unchecked")
    public <T> T[][] paraFormar(T[][] arreglo) {
        if (arreglo.length < this.tamanio) {
            arreglo = (T[][]) Array.newInstance(
                    arreglo.getClass().getComponentType(), this.tamanio);
        }
        int i = 0;
        Object[] resultado = arreglo;
        for (Nodo<E> nodo = this.primero; nodo != null; nodo = nodo.siguiente) {
            resultado[i++] = nodo.elemento;
        }

        if (arreglo.length > this.tamanio) {
            arreglo[this.tamanio] = null;
        }

        return arreglo;
    }

    /**
     * Aparece un elemento de la pila representada por esta lista. En otras
     * palabras, elimina y devuelve el primer elemento de esta lista.     
     * <p>
     * Este método es equivalente a {@link #removerPrimero()}.
     *
     * @return el elemento al principio de esta lista (que es la parte superior
     * de la pila representada por esta lista) @throws NoSuchElementException si
     * esta lista está vacía
     * @since 1.6      
     */
    @Override
    public E quitar() {
        return this.removerPrimero();
    }

    /**
     * Recupera y elimina el encabezado (primer elemento) de esta lista.
     *
     * @return el jefe de esta lista
     * @throws NoSuchElementException si esta lista está vacía
     * @since 1.5      
     */
    @Override
    public E remover() {
        return this.removerPrimero();
    }

    /**
     * Elimina el elemento en la posición especificada en esta lista. Cambia
     * cualquier elementos posteriores a la izquierda (resta uno de sus
     * índices). Devuelve el elemento que se eliminó de la lista.
     *
     * @param indice el índice del elemento a eliminar
     * @return el elemento previamente en la posición especificada
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E remover(int indice) {
        this.verificarIndiceElemento(indice);
        return this.desenlazar(this.nodo(indice));
    }

    /**
     * Elimina la primera aparición del elemento especificado de esta lista,si
     * está presente. Si esta lista no contiene el elemento, es sin cambios. Más
     * formalmente, elimina el elemento con el índice más bajo  {@code i} tal
     * que <tt>(objeto == null & nbsp;? & nbsp; obtener(i) == null & nbsp;: &
     * nbsp; objeto.equals (obtener(i)))</tt>
     * (si tal elemento existe). Devuelve {@code true} si esta lista contenía el
     * elemento especificado (o equivalentemente, si esta lista cambiado como
     * resultado de la llamada).
     *
     * @param objeto elemento que se eliminará de esta lista, si está presente
     * @return {@code true} si esta lista contiene el elemento especificado
     *      
     */
    @Override
    public boolean remover(Object objeto) {
        if (objeto == null) {
            for (Nodo<E> nodo = this.primero; nodo != null; nodo = nodo.siguiente) {
                if (nodo.elemento == null) {
                    this.desenlazar(nodo);
                    return true;
                }
            }
        } else {
            for (Nodo<E> nodo = this.primero; nodo != null; nodo = nodo.siguiente) {
                if (objeto.equals(nodo.elemento)) {
                    this.desenlazar(nodo);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Elimina la primera aparición del elemento especificado en este lista (al
     * atravesar la lista de la cabeza a la cola). Si la lista no contiene el
     * elemento, no se modifica. @param objeto elemento que se eliminará de esta
     * lista, si está presente
     *
     * @return {@code true} si la lista contiene el elemento especificado
     * @since 1.6      
     */
    @Override
    public boolean removerPrimeraOcurrencia(Object objeto) {
        return this.remover(objeto);
    }

    /**
     * Elimina y devuelve el primer elemento de esta lista.
     *
     * @return el primer elemento de esta lista
     * @throws NoSuchElementException si esta lista está vacía      
     */
    @Override
    public E removerPrimero() {
        final Nodo<E> primero = this.primero;
        if (primero == null) {
            throw new NoSuchElementException();
        }
        return this.desenlazarPrimero(primero);
    }

    /**
     * Elimina la última aparición del elemento especificado en este lista (al
     * atravesar la lista de la cabeza a la cola). Si la lista no contiene el
     * elemento, no se modifica.
     *
     * @param objeto elemento que se eliminará de esta lista, si está presente
     * @return{@code true} si la lista contiene el elemento especificado
     * @since 1.6      
     */
    @Override
    public boolean removerUltimaOcurrencia(Object objeto) {
        if (objeto == null) {
            for (Nodo<E> nodo = this.ultimo; nodo != null; nodo = nodo.anterior) {
                if (nodo.elemento == null) {
                    this.desenlazar(nodo);
                    return true;
                }
            }
        } else {
            for (Nodo<E> nodo = this.ultimo; nodo != null; nodo = nodo.anterior) {
                if (objeto.equals(nodo.elemento)) {
                    this.desenlazar(nodo);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Elimina y devuelve el último elemento de esta lista.
     *
     * @return el último elemento de esta lista
     * @throws NoSuchElementException si esta lista está vacía      
     */
    @Override
    public E removerUltimo() {
        final Nodo<E> ultimo = this.ultimo;
        if (ultimo == null) {
            throw new NoSuchElementException();
        }
        return this.desenlazarUltimo(ultimo);
    }

    /**
     * Crea un <em><a href="Spliterator.html#binding"> enlace de último
     * momento</a></em>
     * y <em>fail-fast </em> {@link Spliterator} sobre los elementos en este
     * lista.     
     * <p>
     * Los informes {
     *
     * @Spliterator} informan {@link Spliterator # SIZED} y
     * {@link Spliterator # ORDERED}. Imposición de implementaciones debe
     * documentarel informe de valores característicos adicionales.
     * @implNote El {@code Spliterator} también informa
     * {@link Spliterator # SUBSIZED} e implementa {@code trySplit} para
     * permitir un paralelismo limitado.
     * @return un {@code Spliterator} sobre los elementos en esta lista
     * @since 1.8     
     */
    @Override
    public Spliterator<E> spliterator() {
        return new ListaEnlazada.LLSpliterator<E>(this, -1, 0);
    }

    @SuppressWarnings("unchecked")
    private ListaEnlazada<E> superClone() {
        try {
            return (ListaEnlazada<E>) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError(ex);
        }
    }

    /**
     * Devuelve la cantidad de elementos en esta lista.
     *
     * @return la cantidad de elementos en esta lista      
     */
    @Override
    public int tamanio() {
        return this.tamanio;
    }

    /**
     * Devuelve el índice de la última aparición del elemento especificado en
     * esta lista, o -1 si esta lista no contiene el elemento. Más formalmente,
     * devuelve el índice más alto {@code i} tal que <tt>(objeto == null &
     * nbsp;? & nbsp; obtener(i) == null & nbsp;: & nbsp; objeto.equals
     * (obtener(i)))</tt>, o -1 si no hay tal índice.
     *
     * @param objeto elemento para buscar
     * @return el índice de la última ocurrencia del elemento especificado en
     * esta lista, o -1 si esta lista no contiene el elemento      
     */
    @Override
    public int ultimoIndiceDe(Object objeto) {
        int indice = this.tamanio;
        if (objeto == null) {
            for (Nodo<E> nodo = this.ultimo; nodo != null; nodo = nodo.anterior) {
                indice--;
                if (nodo.elemento == null) {
                    return indice;
                }
            }
        } else {
            for (Nodo<E> nodo = this.ultimo; nodo != null; nodo = nodo.anterior) {
                indice--;
                if (objeto.equals(nodo.elemento)) {
                    return indice;
                }
            }
        }
        return -1;
    }

    private void verificarIndiceElemento(int indice) {
        if (!this.esIndiceElemento(indice)) {
            throw new IndexOutOfBoundsException(this.mostrarMensajeFueraDeLosLimites(indice));
        }
    }

    private void verificarIndicePosicion(int indice) {
        if (!this.esIndicePosicion(indice)) {
            throw new IndexOutOfBoundsException(this.mostrarMensajeFueraDeLosLimites(indice));
        }
    }

    //Clase interna DescendingIterator.
    /**
     * Adaptador para proporcionar iteradores descendentes a través de
     * ListItr.previous      
     */
    private class DescendingIterator implements Iterator<E> {

        //Atributos de la clase interna DescendingIterator.
        private final ListItr itr = new ListItr(tamanio());

        //Constructor de la clase interna DescendingIterator.
        DescendingIterator() {

        }

        //Métodos de la clase interna DescendingIterator.
        @Override
        public boolean hasNext() {
            return itr.hasPrevious();
        }

        @Override
        public E next() {
            return itr.previous();
        }

        @Override
        public void remove() {
            itr.remove();
        }
    }

    //Clase interna LLSpliterator.
    /**
     * Una variante personalizada de Spliterators.IteratorSpliterator
     */
    static final class LLSpliterator<E> implements Spliterator<E> {
        //Atributos de la clase interna LLSpliterator.

        static final int BATCH_UNIT = 1 << 10;  //Nncremento del tamaño de la matriz por lotes.
        static final int MAX_BATCH = 1 << 25;  //Tamaño máximo de arreglo por lotes;
        int batch;            //Tamaño de lote para splits.
        Nodo<E> current;      //Nodo actual; nulo hasta inicializado.
        int est;              //Estimación del tamaño; -1 hasta que sea necesario.
        int expectedModCount; //Inicializado cuando se establece.
        final ListaEnlazada<E> list; //Null OK a menos que se cruce.

        //Constructores de la clase interna LLSpliterator.
        LLSpliterator() {
            this.list = null;
            this.est = 0;
            this.expectedModCount = 0;
        }

        LLSpliterator(ListaEnlazada<E> list, int est, int expectedModCount) {
            this.list = list;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        //Métodos de la clase interna LLSpliterator.
        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public long estimateSize() {
            return (long) this.getEst();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Nodo<E> p;
            int n;
            if (action == null) {
                throw new NullPointerException();
            }
            if ((n = getEst()) > 0 && (p = this.current) != null) {
                this.current = null;
                this.est = 0;
                do {
                    E e = p.elemento;
                    p = p.siguiente;
                    action.accept(e);
                } while (p != null && --n > 0);
            }
            if (this.list.conteoModulo != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        final int getEst() {
            int s; //Forzar la inicialización.
            final ListaEnlazada<E> lst;
            if ((s = this.est) < 0) {
                if ((lst = this.list) == null) {
                    s = this.est = 0;
                } else {
                    this.expectedModCount = lst.conteoModulo;
                    this.current = lst.primero;
                    s = this.est = lst.tamanio;
                }
            }
            return s;
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            ListaEnlazada.Nodo<E> p;
            if (action == null) {
                throw new NullPointerException();
            }
            if (getEst() > 0 && (p = this.current) != null) {
                --this.est;
                E e = p.elemento;
                this.current = p.siguiente;
                action.accept(e);
                if (this.list.conteoModulo != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<E> trySplit() {
            ListaEnlazada.Nodo<E> p;
            int s = this.getEst();
            if (s > 1 && (p = this.current) != null) {
                int n = this.batch + BATCH_UNIT;
                if (n > s) {
                    n = s;
                }
                if (n > MAX_BATCH) {
                    n = MAX_BATCH;
                }
                Object[] a = new Object[n];
                int j = 0;
                do {
                    a[j++] = p.elemento;
                } while ((p = p.siguiente) != null && j < n);
                this.current = p;
                this.batch = j;
                this.est = s - j;
                return Spliterators.spliterator(a, 0, j, Spliterator.ORDERED);
            }
            return null;
        }
    }

    //Clase interna ListItr.
    private class ListItr implements ListIterator<E> {

        //Atributos de la clase interna ListItr.
        private int expectedModCount = conteoModulo;
        private Nodo<E> lastReturned;
        private Nodo<E> next;
        private int nextIndex;

        //Constructores de la clase interna ListItr.
        ListItr() {
            this.next = null;
            this.nextIndex = 0;
        }

        ListItr(int index) {
            //Afirmar esIndicePosicion(indice);
            this.next = (index == tamanio) ? null : nodo(index);
            this.nextIndex = index;
        }

        //Métodos de la clase interna ListItr.
        @Override
        public void add(E e) {
            this.checkForComodification();
            this.lastReturned = null;
            if (this.next == null) {
                enlazarUltimo(e);
            } else {
                enlazarAntes(e, this.next);
            }
            this.nextIndex++;
            this.expectedModCount++;
        }

        final void checkForComodification() {
            if (conteoModulo != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (conteoModulo == this.expectedModCount && this.nextIndex < tamanio) {
                action.accept(this.next.elemento);
                this.lastReturned = this.next;
                this.next = this.next.siguiente;
                this.nextIndex++;
            }
            this.checkForComodification();
        }

        @Override
        public boolean hasNext() {
            return this.nextIndex < tamanio;
        }

        @Override
        public boolean hasPrevious() {
            return this.nextIndex > 0;
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            this.lastReturned = this.next;
            this.next = this.next.siguiente;
            this.nextIndex++;
            return lastReturned.elemento;
        }

        @Override
        public int nextIndex() {
            return this.nextIndex;
        }

        @Override
        public E previous() {
            checkForComodification();
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            this.lastReturned = this.next = (this.next == null) ? ultimo : this.next.anterior;
            this.nextIndex--;
            return lastReturned.elemento;
        }

        @Override
        public int previousIndex() {
            return this.nextIndex - 1;
        }

        @Override
        public void remove() {
            this.checkForComodification();
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }

            Nodo<E> lastNext = this.lastReturned.siguiente;
            desenlazar(this.lastReturned);
            if (this.next == this.lastReturned) {
                this.next = lastNext;
            } else {
                this.nextIndex--;
            }
            this.lastReturned = null;
            this.expectedModCount++;
        }

        @Override
        public void set(E e) {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            this.checkForComodification();
            this.lastReturned.elemento = e;
        }
    }

    //Clase interna Nodo.
    private static class Nodo<E> {

        //Atributos de la clase interna Nodo.
        Nodo<E> anterior;
        E elemento;
        Nodo<E> siguiente;

        //Constructores de la clase interna Nodo.
        Nodo() {
            this.anterior = null;
            this.elemento = null;
            this.siguiente = null;
        }

        Nodo(E elemento) {
            this.anterior = null;
            this.elemento = elemento;
            this.siguiente = null;
        }

        Nodo(Nodo<E> anterior, E elemento, Nodo<E> siguiente) {
            this.anterior = anterior;
            this.elemento = elemento;
            this.siguiente = siguiente;
        }

        //Métodos de la clase interna Nodo.
        void setAnterior(Nodo<E> anterior) {
            this.anterior = anterior;
        }

        Nodo<E> getAnterior() {
            return this.anterior;
        }

        void setElemento(E elemento) {
            this.elemento = elemento;
        }

        E getElemento() {
            return this.elemento;
        }

        void setSiguiente(Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }

        Nodo<E> getSiguiente() {
            return this.siguiente;
        }
    }
}
