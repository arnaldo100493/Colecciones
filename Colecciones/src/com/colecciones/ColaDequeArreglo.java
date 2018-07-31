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
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

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
 * iterator.remover()}, y las operaciones masivas, todas las cuales se ejecutan
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

    //Atributos de la clase ColaDequeArreglo.
    /**
     * Número serial version UID generado de la clase ColaDequeArreglo.
     */
    private static final long serialVersionUID = 2340985798034038923L;
    /**
     * El índice del elemento a la cabeza del deque (que es el elemento que
     * sería eliminado por remover() o quitar()); o un número arbitrario igual a
     * cola si la cola deque está vacía.      
     */
    transient int cabeza;

    /**
     * El índice en el que el siguiente elemento se agregaría a la cola de la
     * cola deque (vía agregarUltimo(E), agregar(E), o empujar(E)).      
     */
    transient int cola;

    /**
     * La capacidad mínima que usaremos para un deque recién creado. Debe ser un
     * poder de 2.      
     */
    private static final int CAPACIDAD_INICIAL_MINIMA = 8;

    /**
     * El arreglo en el que se almacenan los elementos del deque. La capacidad
     * del deque es la longitud de este conjunto, que es siempre un poder de
     * dos. El arreglo nunca puede convertirse completo, excepto
     * transitoriamente dentro de un método agregarX donde es redimensionado
     * (ver duplicarCapacidad) inmediatamente después de estar lleno, evitando
     * así la envoltura de la cabeza y la cola para igualar a cada otro. También
     * garantizamos que todas las celdas del arreglo no tengan los elementos
     * deque son siempre nulos.      
     */
    transient Object[] listadoElementos; //No privado para simplificar el acceso de clase anidado.

    //Constructores de la clase ColaDequeArreglo.
    /**
     * Construye un deque de matriz vacío con una capacidad inicial suficiente
     * para contener 16 elementos.      
     */
    public ColaDequeArreglo() {
        this.listadoElementos = new Object[16];
    }

    /**
     * Construye una cola deque de arreglo vacío con una capacidad inicial
     * suficiente para contener la cantidad de elementos especificada.
     *
     * @param numeroElementos límite inferior en la capacidad inicial del deque
     *      
     */
    public ColaDequeArreglo(int numeroElementos) {
        this.asignarElementos(numeroElementos);
    }

    /**
     * Construye una deque que contiene los elementos de la especificada
     * colección, en el orden en que son devueltos por la colección iterador.
     * (El primer elemento devuelto por la colección iterator se convierte en el
     * primer elemento, o <i>front</i> del deque)
     *
     * @param coleccion la colección cuyos elementos deben ser colocados en la
     * cola deque
     * @throws NullPointerException si la colección especificada es nula      
     */
    public ColaDequeArreglo(Coleccion<? extends E> coleccion) {
        this.asignarElementos(coleccion.tamanio());
        this.agregarTodo(coleccion);
    }

    //Métodos de la clase ColaDequeArreglo.
    /**
     * Inserta el elemento especificado al final de esta cola deque.      
     * <p>
     * Este método es equivalente a {@link #agregarUltimo}.
     *
     * @param elemento el elemento para agregar
     * @return {@code true} (según lo especificado por
     * {@link Coleccion #agregar})
     * @throws NullPointerException si el elemento especificado es nulo      
     */
    @Override
    public boolean agregar(E elemento) {
        this.agregarUltimo(elemento);
        return true;
    }

    /**
     * Inserta el elemento especificado al frente de esta cola deque.
     *
     * @param elemento el elemento para agregar
     * @throws NullPointerException si el elemento especificado es nulo      
     */
    @Override
    public void agregarPrimero(E elemento) {
        if (elemento == null) {
            throw new NullPointerException();
        }
        this.listadoElementos[this.cabeza = (this.cabeza - 1)
                & (this.listadoElementos.length - 1)] = elemento;
        if (this.cabeza == this.cola) {
            this.duplicarCapacidad();
        }
    }

    /**
     * Inserta el elemento especificado al final de esta cola deque.    
     * <p>
     * Este método es equivalente a {@link #agregar}.
     *
     * @param elemento el elemento para agregar
     * @throws NullPointerException si el elemento especificado es nulo      
     */
    @Override
    public void agregarUltimo(E elemento) {
        if (elemento == null) {
            throw new NullPointerException();
        }
        this.listadoElementos[this.cola] = elemento;
        if ((this.cola = (this.cola + 1) & (this.listadoElementos.length - 1))
                == this.cabeza) {
            this.duplicarCapacidad();
        }
    }

    /**
     * Asigna arreglo vacío para contener la cantidad de elementos dada.
     *
     * @param numeroElementos la cantidad de elementos para contener      
     */
    private void asignarElementos(int numeroElementos) {
        int capacidadInicial = CAPACIDAD_INICIAL_MINIMA;
        //Encuentra la mejor potencia de dos para contener elementos.
        //Prueba "<=" porque los arreglos no se mantienen llenas.
        if (numeroElementos >= capacidadInicial) {
            capacidadInicial = numeroElementos;
            capacidadInicial |= (capacidadInicial >>> 1);
            capacidadInicial |= (capacidadInicial >>> 2);
            capacidadInicial |= (capacidadInicial >>> 4);
            capacidadInicial |= (capacidadInicial >>> 8);
            capacidadInicial |= (capacidadInicial >>> 16);
            capacidadInicial++;

            if (capacidadInicial < 0) { //Demasiados elementos, deben retroceder.
                capacidadInicial >>>= 1; //Buena suerte asignando 2 ^ 30 elementos.
            }
            this.listadoElementos = new Object[capacidadInicial];
        }
    }

    /**
     * Devuelve una copia de esta cola deque.
     *
     * @return una copia de esta cola deque      
     */
    @Override
    public ColaDequeArreglo<E> clone() {
        try {
            @SuppressWarnings("unchecked")
            ColaDequeArreglo<E> resultado = (ColaDequeArreglo<E>) super.clone();
            resultado.listadoElementos = Arrays.copyOf(this.listadoElementos,
                    this.listadoElementos.length);
            return resultado;
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }

    /**
     * Devuelve {@code true} si esta deque contiene el elemento especificado.
     * Más formalmente, devuelve {@code true} si y solo si esta deque contiene
     * al menos un elemento {@code e} tal que {@code objeto.equals (elemento)}.
     *
     * @param objeto objeto que debe verificarse para la contención en esta cola
     * deque
     *
     * @return {@code true} si esta deque contiene el elemento especificado
     *      
     */
    @Override
    public boolean contiene(Object objeto) {
        if (objeto == null) {
            return false;
        }
        int mascara = this.listadoElementos.length - 1;
        int i = this.cabeza;
        Object x;
        while ((x = this.listadoElementos[i]) != null) {
            if (objeto.equals(x)) {
                return true;
            }
            i = (i + 1) & mascara;
        }
        return false;
    }

    /**
     * Copia los elementos de nuestro arreglo de elementos en la matriz
     * especificada, en orden (del primer al último elemento en la cola deque).
     * Es asumido que el conjunto es lo suficientemente grande como para
     * contener todos los elementos de la cola deque.
     *
     * @return su argumento      
     */
    private <T> T[] copiarElementos(T[] arreglo) {
        if (this.cabeza < this.cola) {
            System.arraycopy(this.listadoElementos, this.cabeza, arreglo, 0,
                    this.tamanio());
        }
        return arreglo;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }

    /**
     * Duplica la capacidad de esta deque. Llamar solo cuando está lleno, es
     * decir, cuando la cabeza y la cola se han enrollado para igualarse.      
     */
    private void duplicarCapacidad() {
        assert this.cabeza == this.cola;
        int p = this.cabeza;
        int n = this.listadoElementos.length;
        int r = n - p; //Número de elementos a la derecha de p.
        int nuevaCapacidad = n << 1;
        if (nuevaCapacidad < 0) {
            throw new IllegalStateException("Lo sentimos, cola deque muy grande");
        }
        Object[] a = new Object[nuevaCapacidad];
        System.arraycopy(this.listadoElementos, p, a, 0, r);
        System.arraycopy(this.listadoElementos, 0, a, r, p);
        this.listadoElementos = a;
        this.cabeza = 0;
        this.cola = n;
    }

    /**
     * Recupera, pero no elimina, el encabezado de la cola representado por esta
     * deque. Este método difiere de {@link #ojear ojear} solo en que arroja una
     * excepción si esta deque está vacía.      
     * <p>
     * Este método es equivalente a {@link #obtenerPrimero}.
     *
     * @return el encabezado de la cola representado por esta cola deque
     * @throws NoSuchElementException {@inheritDoc}      
     */
    @Override
    public E elemento() {
        return this.obtenerPrimero();
    }

    private boolean eliminar(int i) {
        this.verificarInvariantes();
        final Object[] listadoElementos = this.listadoElementos;
        final int mascara = listadoElementos.length - 1;
        final int h = this.cabeza;
        final int t = this.cola;
        final int frente = (i - h) & mascara;
        final int espalda = (t - i) & mascara;

        // Invariante: cabeza <= i < cola modudlo circularidad.
        if (frente >= ((t - h) & mascara)) {
            throw new ConcurrentModificationException();
        }

        //Optimiza para el movimiento de elementos mínimos.
        if (frente < espalda) {
            if (h <= i) {
                System.arraycopy(listadoElementos, h, listadoElementos, h + 1, frente);
            } else { //Envuelve alrededor.

                System.arraycopy(listadoElementos, 0, listadoElementos, 1, i);
                listadoElementos[0] = listadoElementos[mascara];
                System.arraycopy(listadoElementos, h, listadoElementos, h + 1, mascara - h);
            }
            listadoElementos[h] = null;
            this.cabeza = (h + 1) & mascara;
            return false;
        } else {
            if (i < t) { //Copia la cola nula tan bién.
                System.arraycopy(listadoElementos, i + 1, listadoElementos, i, espalda);
                this.cola = t - 1;
            } else { //Envuelve alrededor.
                System.arraycopy(listadoElementos, i + 1, listadoElementos, i, mascara - i);
                listadoElementos[mascara] = listadoElementos[0];
                System.arraycopy(listadoElementos, 1, listadoElementos, 0, t);
                cola = (t - 1) & mascara;
            }
            return true;
        }
    }

    /**
     * Empuja un elemento sobre la pila representada por esta cola deque. En
     * otras palabras, inserta el elemento al frente de esta cola deque.
     * <p>
     * Este método es equivalente a {@link #agregarPrimero}.
     *
     * @param elemento el elemento para empujar
     * @throws NullPointerException si el elemento especificado es nulo      
     */
    @Override
    public void empujar(E elemento) {
        this.agregarPrimero(elemento);
    }

    /**
     * Recupera y elimina el encabezado de la cola representada por esta cola
     * deque (en otras palabras, el primer elemento de esta cola deque), o
     * devoluciones {@code null} si esta deque está vacía.     
     * <p>
     * Este método es equivalente a {@link #encuestarPrimero}.
     *
     * @return el encabezado de la cola representado por esta cola deque, o
     * {@code null} si esta cola deque está vacía      
     */
    @Override
    public E encuestar() {
        return this.encuestarPrimero();
    }

    @Override
    public E encuestarPrimero() {
        int h = this.cabeza;
        @SuppressWarnings("unchecked")
        E resultado = (E) this.listadoElementos[h];
        //Elemento es nulo if la cola deque está vacía.
        if (resultado == null) {
            return null;
        }
        this.listadoElementos[h] = null; //Debe anular la ranura.
        this.cabeza = (h + 1) & (this.listadoElementos.length - 1);
        return resultado;
    }

    @Override
    public E encuestarUltimo() {
        int t = (this.cola - 1) & (this.listadoElementos.length - 1);
        @SuppressWarnings("unchecked")
        E resultado = (E) this.listadoElementos[t];
        if (resultado == null) {
            return null;
        }
        this.listadoElementos[t] = null;
        this.cola = t;
        return resultado;
    }

    /**
     * Guarda esta deque en una secuencia (es decir, la serializa).
     *
     * @serialData El tamaño actual ({@code int}) de la coladeque, seguido de
     * todos sus elementos (cada uno una referencia de objeto) en primer a
     * último orden.      
     */
    private void escribirObjeto(ObjectOutputStream salida) throws IOException {
        salida.defaultWriteObject();

        //Escribe el tamaño.
        salida.writeInt(this.tamanio());

        //Escribe los elementos en orden.
        int mascara = this.listadoElementos.length - 1;
        for (int i = this.cabeza; i != this.cola; i = (i + 1) & mascara) {
            salida.writeObject(this.listadoElementos[i]);
        }
    }

    /**
     * Devuelve {@code true} si esta cola deque no contiene elementos.
     *
     * @return {@code true} si esta cola deque no contiene elementos      
     */
    @Override
    public boolean estaVacia() {
        return this.cabeza == this.cola;
    }

    /**
     * Devuelve un iterador sobre los elementos en esta cola deque. Los
     * elementos se ordenará desde la primera (cabeza) hasta la última (cola).
     * Esto es lo mismo ordenar que los elementos sean eliminados (a través de
     * llamadas sucesivas a {@link #remover} o aparecido (a través de llamadas
     * sucesivas a {@link #quitar}).
     *
     * @return un iterador sobre los elementos en esta cola deque      
     */
    @Override
    public Iterator<E> iterator() {
        return new DeqIterator();
    }

    /**
     * Reconstituye esta cola deque de una secuencia (es decir, la deserializa).
     *      
     */
    private void leerObjeto(ObjectInputStream entrada) throws IOException,
            ClassNotFoundException {
        entrada.defaultReadObject();

        //Leer en tamaño y asignar arreglo.
        int tamanio = entrada.readInt();
        this.asignarElementos(tamanio);
        this.cabeza = 0;
        this.cola = tamanio;

        //Lee en todos los elementos en el orden correcto.
        for (int i = 0; i < tamanio; i++) {
            this.listadoElementos[i] = entrada.readObject();
        }
    }

    /**
     * Elimina todos los elementos de esta cola deque. La cola deque estará
     * vacío después de que esta llamada regrese.      
     */
    @Override
    public void limpiar() {
        int h = this.cabeza;
        int t = this.cola;
        if (h != t) { //Limpia toda las celdas.
            this.cabeza = this.cola = 0;
            int i = h;
            int mascara = this.listadoElementos.length - 1;
            do {
                this.listadoElementos[i] = null;
                i = (i + 1) & mascara;
            } while (i != t);
        }
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Override
    public E obtenerPrimero() {
        @SuppressWarnings("unchecked")
        E resultado = (E) this.listadoElementos[this.cabeza];
        if (resultado == null) {
            throw new NoSuchElementException();
        }
        return resultado;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Override
    public E obtenerUltimo() {
        @SuppressWarnings("unchecked")
        E resultado = (E) this.listadoElementos[(this.cola - 1) & (this.listadoElementos.length - 1)];
        if (resultado == null) {
            throw new NoSuchElementException();
        }
        return resultado;
    }

    /**
     * Inserta el elemento especificado al final de esta cola deque.      
     * <p>
     * Este método es equivalente a {@link #ofrecerUltimo}.
     *
     * @param elemento el elemento para agregar @return {@code true} (según lo
     * especificado por {@link Cola#ofrecer})
     * @throws NullPointerException si el elemento especificado es nulo      
     */
    @Override
    public boolean ofrecer(E elemento) {
        return this.ofrecerUltimo(elemento);
    }

    /**
     * Inserta el elemento especificado al frente de esta cola deque.
     *
     * @param elemento el elemento para agregar
     * @return{@code true} (según lo especificado por
     * {@link ColaDeque #ofrecerPrimero})
     * @throws NullPointerException si el elemento especificado es nulo      
     */
    @Override
    public boolean ofrecerPrimero(E elemento) {
        this.agregarPrimero(elemento);
        return true;
    }

    /**
     * Inserta el elemento especificado al final de esta deque.
     *
     * @param elemento el elemento para agregar return {@code true} (según lo
     * especificado por {@link ColaDeque #ofrecerUltimo})
     * @throws NullPointerException si el elemento especificado es nulo      
     */
    @Override
    public boolean ofrecerUltimo(E elemento) {
        this.agregarUltimo(elemento);
        return true;
    }

    /**
     * Recupera, pero no elimina, el encabezado de la cola representado por esta
     * deque, o devuelve {@code null} si esta cola deque está vacía.   
     * <p>
     * Este método es equivalente a {@link #ojearPrimero}.
     *
     * @return el encabezado de la cola representado por esta cola deque, o
     * {@code null} si esta coladeque está vacía      
     */
    @Override
    public E ojear() {
        return this.ojearPrimero();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E ojearPrimero() {
        //this.listadoElementos[this.cabeza] es nula si cola deque está vacía.
        return (E) this.listadoElementos[this.cabeza];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E ojearUltimo() {
        return (E) this.listadoElementos[(this.cola - 1) & (this.listadoElementos.length - 1)];
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos en esta cola deque
     * en la secuencia correcta (del primer al último elemento).
     *
     * <p>
     * El arreglo devuelto será "seguro" porque no hay referencias a el
     * mantenido por esta cola deque. (En otras palabras, este método debe
     * asignar un nuevo arreglo). La persona que llama es libre de modificar el
     * arreglo devuelto  
     * <p>
     * Este método actúa como puente entre la basada en arreglos y la basada en
     * colecciones APIs.
     *
     * @return un arreglo que contiene todos los elementos en esta cola deque
     *      
     */
    @Override
    public Object[] paraFormar() {
        return this.copiarElementos(new Object[this.tamanio()]);
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos en esta cola deque
     * en secuencia correcta (del primer al último elemento); el tipo de tiempo
     * de ejecución del arreglo devuelto es la del arreglo especificado. Si el
     * la deque cabe en el arreglo especificado, se devuelve allí. De lo
     * contrario, un nuevo arreglo se asigna con el tipo de tiempo de ejecución
     * del arreglo especificado y el tamaño de esta cola deque.      
     * <p>
     * Si esta cola deque encaja en el arreglo especificado con espacio libre
     * (es decir, el arreglo tiene más elementos que esta cola deque), el
     * elemento en el arreglo inmediatamente posterior al final de la cola deque
     * está configurada para {@code null}.      
     * <p>
     * Al igual que el método {@link #paraFormar()}, este método actúa como
     * puente entre API basada en colección y basada en colección. Además, este
     * método permite control preciso sobre el tipo de tiempo de ejecución del
     * arreglo de salida, y puede, bajo ciertas circunstancias, se puede usar
     * para ahorrar costos de asignación.      
     * <p>
     * Supongamos que {@code x} es conocido porque contiene solo cadenas. El
     * siguiente código puede usarse para volcar la cola deque en un nuevo
     * arreglo asignado de {@code String}:      
     * <pre> {@code String[] y = x.paraFormar(new String [0]);} </ pre>
     *
     * Tenga en cuenta que {@code paraFormar(new Object [0])} es idéntico en función a
     * {@code paraFormar()}.
     *
     * @param arreglo el arreglo en el que se encuentran los elementos de la cola deque
     * ser almacenado, si es lo suficientemente grande; de lo contrario, un nuevo arreglo de
     *  el mismo tipo de tiempo de ejecución se asigna para este propósito
     * @return un arreglo que contiene todos los elementos en esta cola deque
     * @throws ArrayStoreException si el tipo de tiempo de ejecución del arreglo
     * especificado no es un supertipo del tipo de tiempo de ejecución de cada
     * elemento en esta cola deque  @throws NullPointerException si el arreglo
     * especificado es nulo
     *
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] paraFormar(T[] arreglo) {
        int tamanio = this.tamanio();
        if (arreglo.length < tamanio) {
            arreglo = (T[]) java.lang.reflect.Array.newInstance(
                    arreglo.getClass().getComponentType(), tamanio);
        }
        this.copiarElementos(arreglo);
        if (arreglo.length > tamanio) {
            arreglo[tamanio] = null;
        }
        return arreglo;
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos en esta cola deque
     * en secuencia correcta (del primer al último elemento); el tipo de tiempo
     * de ejecución del arreglo devuelto es la del arreglo especificado. Si el
     * la deque cabe en el arreglo especificado, se devuelve allí. De lo
     * contrario, un nuevo arreglo se asigna con el tipo de tiempo de ejecución
     * del arreglo especificado y el tamaño de esta cola deque.      
     * <p>
     * Si esta cola deque encaja en el arreglo especificado con espacio libre
     * (es decir, el arreglo tiene más elementos que esta cola deque), el
     * elemento en el arreglo inmediatamente posterior al final de la cola deque
     * está configurada para {@code null}.      
     * <p>
     * Al igual que el método {@link #paraFormar()}, este método actúa como
     * puente entre API basada en colección y basada en colección. Además, este
     * método permite control preciso sobre el tipo de tiempo de ejecución del
     * arreglo de salida, y puede, bajo ciertas circunstancias, se puede usar
     * para ahorrar costos de asignación.      
     * <p>
     * Supongamos que {@code x} es conocido porque contiene solo cadenas. El
     * siguiente código puede usarse para volcar la cola deque en un nuevo
     * arreglo asignado de {@code String}:      
     * <pre> {@code String[] y = x.paraFormar(new String [0]);} </ pre>
     *
     * Tenga en cuenta que {@code paraFormar(new Object [0])} es idéntico en función a
     * {@code paraFormar()}.
     *
     * @param arreglo el arreglo en el que se encuentran los elementos de la cola deque
     * ser almacenado, si es lo suficientemente grande; de lo contrario, un nuevo arreglo de
     *  el mismo tipo de tiempo de ejecución se asigna para este propósito
     * @return un arreglo que contiene todos los elementos en esta cola deque
     * @throws ArrayStoreException si el tipo de tiempo de ejecución del arreglo
     * especificado no es un supertipo del tipo de tiempo de ejecución de cada
     * elemento en esta cola deque  
     * @throws NullPointerException si el arreglo especificado es nulo
     *
     */
    @SuppressWarnings("unchecked")
    public <T> T[][] paraFormar(T[][] arreglo) {
        int tamanio = this.tamanio();
        if (arreglo.length < tamanio) {
            arreglo = (T[][]) java.lang.reflect.Array.newInstance(
                    arreglo.getClass().getComponentType(), tamanio);
        }
        this.copiarElementos(arreglo);
        if (arreglo.length > tamanio) {
            arreglo[tamanio] = null;
        }
        return arreglo;
    }

    /**
     * Quita un elemento de la pila representada por esta deque. En otra
     * palabras, elimina y devuelve el primer elemento de esta cola deque.   
     * <p>
     * Este método es equivalente a {@link #removerPrimero()}.
     *
     * @return el elemento al frente de esta cola deque (que es la parte
     * superior de la pila representada por esta cola deque)
     * @throws NoSuchElementException {@inheritDoc}      
     */
    @Override
    public E quitar() {
        return this.removerPrimero();
    }

    /**
     * Recupera y elimina el encabezado de la cola representada por esta cola
     * deque. Este método difiere de {@link #encuestaer encuestar} solo en que
     * arroja un excepción si esta cola deque está vacía.   
     * <p>
     * Este método es equivalente a {@link #removerPrimero}.
     *
     * @return el encabezado de la cola representado por esta cola deque
     * @throws NoSuchElementException {@inheritDoc}      
     */
    @Override
    public E remover() {
        return this.removerPrimero();
    }

    /**
     * Elimina una sola instancia del elemento especificado de esta cola deque.
     * Si la cola deque no contiene el elemento, no se modifica. Más
     * formalmente, elimina el primer elemento {@code elemento} tal que
     * {@code objeto.equals (elemento)} (si tal elemento existe). Devuelve
     * {@code true} si esta cola deque contiene el elemento especificado (o de
     * manera equivalente, si esta cola deque cambió como resultado de la
     * llamada).    
     * <p>
     * Este método es equivalente a {@link #removerPrimeraOcurrencia(Object)}.
     *
     * @param objeto elemento que se eliminará de esta cola deque, si está
     * presente
     * @return {@code true} si esta cola deque contiene el elemento especificado
     *      
     */
    @Override
    public boolean remover(Object objeto) {
        return this.removerPrimeraOcurrencia(objeto);
    }

    /**
     * Elimina la primera ocurrencia del elemento especificado en este deque (al
     * atravesar la cola deque de la cabeza a la cola). Si la cola deque no
     * contiene el elemento, no se modifica. Más formalmente, elimina el primer
     * elemento {@code elemento} tal que {@code objeto.equals (elemento)} (si
     * tal elemento existe). Devuelve {@code true} si esta cola deque contiene
     * el elemento especificado (o de manera equivalente, si esta cola deque
     * cambió como resultado de la llamada).
     *
     * @param objeto elemento que se eliminará de esta cola deque, si está
     * presente @return {@code true} si la cola deque contiene el elemento
     * especificado      
     */
    @Override
    public boolean removerPrimeraOcurrencia(Object objeto) {
        if (objeto == null) {
            return false;
        }
        int mascara = this.listadoElementos.length - 1;
        int i = this.cabeza;
        Object x;
        while ((x = this.listadoElementos[i]) != null) {
            if (objeto.equals(x)) {
                this.eliminar(i);
                return true;
            }
            i = (i + 1) & mascara;
        }
        return false;

    }

    /**
     * Elimina la última ocurrencia del elemento especificado en esta cola deque
     * (al atravesar la cola deque de la cabeza a la cola). Si la cola deque no
     * contiene el elemento, no se modifica. Más formalmente, elimina el último
     * elemento {@code elemento} tal que {@code objeto.equals (elemento)} (si
     * tal elemento existe). Devuelve {@code true} si esta deque contiene el
     * elemento especificado (o de manera equivalente, si esta deque cambió como
     * resultado de la llamada).
     *
     * @param objeto elemento que se eliminará de esta cola deque, si está
     * presente
     * @return {@code true} si la cola deque contiene el elemento especificado
     *      
     */
    @Override
    public boolean removerUltimaOcurrencia(Object objeto) {
        if (objeto == null) {
            return false;
        }

        int mascara = this.listadoElementos.length - 1;
        int i = (this.cola - 1) & mascara;
        Object x;
        while ((x = this.listadoElementos[i]) != null) {
            if (objeto.equals(x)) {
                this.eliminar(i);
                return true;
            }
            i = (i - 1) & mascara;
        }
        return false;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Override
    public E removerPrimero() {
        E x = this.encuestarPrimero();
        if (x == null) {
            throw new NoSuchElementException();
        }
        return x;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Override
    public E removerUltimo() {
        E x = this.encuestarUltimo();
        if (x == null) {
            throw new NoSuchElementException();
        }
        return x;
    }

    /**
     * Crea un <em> <a href="Spliterator.html#binding"> enlace de último momento
     * </a> </em>
     * y <em> fail-fast </em> {@link Spliterator} sobre los elementos en esta
     * cola deque    
     * <p>
     * Los informes {
     *
     * @Spliterator} informan {@link Spliterator # SIZED},
     * {@link Spliterator # SUBSIZED}, {@link Spliterator # ORDERED}, y
     * {@link Spliterator # NONNULL}. Imposición de implementaciones debe
     * documentar el informe de valores característicos adicionales.
     * @return un {@code Spliterator} sobre loselementos en esta cola deque
     * @since 1.8      
     */
    @Override
    public Spliterator<E> spliterator() {
        return new ColaDequeArreglo.DeqSpliterator<E>(this, -1, -1);
    }

    /**
     * Devuelve la cantidad de elementos en esta cola deque.
     *
     * @return la cantidad de elementos en esta cola deque      
     */
    @Override
    public int tamanio() {
        return (this.cola - this.cabeza) & (this.listadoElementos.length - 1);
    }

    private void verificarInvariantes() {
        assert this.listadoElementos[this.cola] == null;
        assert this.cabeza == this.cola ? this.listadoElementos[this.cabeza]
                == null
                : (this.listadoElementos[this.cabeza] != null
                && this.listadoElementos[(this.cola - 1) & (this.listadoElementos.length - 1)]
                != null);
        assert this.listadoElementos[(this.cabeza - 1) & (this.listadoElementos.length - 1)]
                == null;
    }

    //Clase interna DeqIterator.
    private class DeqIterator implements Iterator<E> {

        //Atributos de la clase interna DeqIterator.
        /**
         * Índice del elemento que se devolverá mediante la siguiente llamada al
         * siguiente.          
         */
        private int cursor = cabeza;

        /**
         * Cola registrada en la construcción (también en quitar), para detener
         * iterador y también para verificar la comodificación.          
         */
        private int fence = cola;

        /**
         * Índice del elemento devuelto por la llamada más reciente al
         * siguiente. Restablecer a -1 si el elemento es eliminado por una
         * llamada para eliminar.          
         */
        private int lastRet = -1;

        //Constructor de la clase interna DeqIterator.
        DeqIterator() {

        }

        //Métodos de la clase interna DeqIterator.
        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            Object[] a = listadoElementos;
            int m = a.length - 1, f = fence, i = cursor;
            cursor = f;
            while (i != f) {
                @SuppressWarnings("unchecked")
                E e = (E) a[i];
                i = (i + 1) & m;
                if (e == null) {
                    throw new ConcurrentModificationException();
                }

                action.accept(e);
            }
        }

        @Override
        public boolean hasNext() {
            return this.cursor != fence;
        }

        @Override
        public E next() {
            if (this.cursor == this.fence) {
                throw new NoSuchElementException();
            }
            @SuppressWarnings("unchecked")
            E result = (E) listadoElementos[this.cursor];
            //Esta comprobación no captura todas las comodifications posibles,
            //Pero atrapa los que corrompen el recorrido.
            if (cola != this.fence || result == null) {
                throw new ConcurrentModificationException();
            }
            this.lastRet = this.cursor;
            this.cursor = (this.cursor + 1) & (listadoElementos.length - 1);
            return result;
        }

        @Override
        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }
            if (eliminar(this.lastRet)) { //Si se desplaza hacia la izquierda, deshace el incremento en next ().
                this.cursor = (this.cursor - 1) & (listadoElementos.length - 1);
                this.fence = cola;
            }
            this.lastRet = -1;
        }
    }

    //Clase interna DeqSpliterator.
    static final class DeqSpliterator<E> implements Spliterator<E> {

        //Atributos de la clase interna DeqSpliterator.
        private final ColaDequeArreglo<E> deq;
        private int fence;  // -1 hasta el primer uso.
        private int index; // Índice actual, modificado en poligonal / división.

        //Constructores de la clase interna DeqSpliterator.
        DeqSpliterator() {
            this.deq = null;
        }

        /**
         * Crea un spliterator nuevo que cubre el areeglo y rango dados
         */
        DeqSpliterator(ColaDequeArreglo<E> deq, int origin, int fence) {
            this.deq = deq;
            this.index = origin;
            this.fence = fence;
        }

        //Métodos de la clase interna DeqSpliterator.
        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED
                    | Spliterator.NONNULL | Spliterator.SUBSIZED;
        }

        @Override
        public long estimateSize() {
            int n = getFence() - this.index;
            if (n < 0) {
                n += this.deq.listadoElementos.length;
            }
            return (long) n;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> consumer) {
            if (consumer == null) {
                throw new NullPointerException();
            }
            Object[] a = deq.listadoElementos;
            int m = a.length - 1, f = getFence(), i = this.index;
            this.index = f;
            while (i != f) {
                @SuppressWarnings("unchecked")
                E e = (E) a[i];
                i = (i + 1) & m;
                if (e == null) {
                    throw new ConcurrentModificationException();
                }
                consumer.accept(e);
            }
        }

        private int getFence() { //Forza la inicialización.
            int t;
            if ((t = this.fence) < 0) {
                t = this.fence = this.deq.cola;
                this.index = this.deq.cabeza;
            }
            return t;
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> consumer) {
            if (consumer == null) {
                throw new NullPointerException();
            }
            Object[] a = this.deq.listadoElementos;
            int m = a.length - 1, f = getFence(), i = this.index;
            if (i != this.fence) {
                @SuppressWarnings("unchecked")
                E e = (E) a[i];
                this.index = (i + 1) & m;
                if (e == null) {
                    throw new ConcurrentModificationException();
                }
                consumer.accept(e);
                return true;
            }
            return false;
        }

        @Override
        public DeqSpliterator<E> trySplit() {
            int t = getFence(), h = this.index, n = this.deq.listadoElementos.length;
            if (h != t && ((h + 1) & (n - 1)) != t) {
                if (h > t) {
                    t += n;
                }
                int m = ((h + t) >>> 1) & (n - 1);
                return new DeqSpliterator<>(this.deq, h, this.index = m);
            }
            return null;
        }
    }

    //Clase interna DescendingIterator.
    private class DescendingIterator implements Iterator<E> {

        /*
          * Esta clase es casi una imagen espejo de DeqIterator, usando
          * cola en lugar de cabeza para el cursor inicial, y cabeza en lugar de
          * cola para valla.
          */
        //Atributos de la clase interna DescendingIterator.
        private int cursor = cola;
        private int fence = cabeza;
        private int lastRet = -1;

        //Constructor de la clase interna DescendingIterator.
        DescendingIterator() {

        }

        //Métodos de la clase interna DescendingIterator.
        @Override
        public boolean hasNext() {
            return this.cursor != this.fence;
        }

        @Override
        public E next() {
            if (this.cursor == this.fence) {
                throw new NoSuchElementException();
            }
            this.cursor = (this.cursor - 1) & (listadoElementos.length - 1);
            @SuppressWarnings("unchecked")
            E result = (E) listadoElementos[this.cursor];
            if (cabeza != this.fence || result == null) {
                throw new ConcurrentModificationException();
            }
            this.lastRet = this.cursor;
            return result;
        }

        @Override
        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }
            if (!eliminar(this.lastRet)) {
                this.cursor = (this.cursor + 1) & (listadoElementos.length - 1);
                this.fence = cabeza;
            }
            lastRet = -1;
        }
    }
}
