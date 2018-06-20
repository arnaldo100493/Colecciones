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
import java.util.BitSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Clase ListaArreglo para guardar y manipular elementos en una lista de
 * arreglo.
 */
/**
 * Implementación de arreglos redimensionables de la interfaz <tt>Lista
 * </tt>. Implementos todas las operaciones de lista opcionales, y permite todos
 * los elementos, incluidos <tt>null</tt>. Además de implementar la interfaz
 * <tt>Lista</tt>, esta clase proporciona métodos para manipular el tamaño de la
 * matriz que es utilizado internamente para almacenar la lista. (Esta clase es
 * más o menos equivalente a <tt>ListaVector</tt>, excepto que no está
 * sincronizado).
 * <p>
 * El <tt>tamanio</tt>, <tt>es Vacío</tt>, <tt>obtener</tt>, <tt>establecer
 * </tt> <tt>iterator</tt>, y las operaciones <tt>listIterator</tt>
 * se ejecutan en constante hora. La operación <tt> agregar </tt> se ejecuta en
 * <i> tiempo constante amortizado </i>, es decir, agregar n elementos requiere
 * O (n) tiempo. Todas las otras operaciones ejecute en tiempo lineal
 * (aproximadamente hablando). El factor constante es bajo comparado a eso para
 * la implementación de <tt> ListaEnlazada</tt>.  
 * <p>
 * Cada instancia de <tt>ListaArreglo</tt> tiene una <i>capacidad</i>. La
 * capacidad es el tamaño del arreglo utilizado para almacenar los elementos en
 * la lista. Es siempre al menos tan grande como el tamaño de la lista. A medida
 * que se agregan elementos a ListaArreglo, su capacidad crece automáticamente.
 * Los detalles de la política de crecimiento no son especificado más allá del
 * hecho de que la adición de un elemento tiene amortización constante costo de
 * tiempo
 * <p>
 * Una aplicación puede aumentar la capacidad de una instancia de
 * <tt>ListaArreglo
 * </tt>
 * antes de agregar una gran cantidad de elementos usando <tt>asegurarCapacidad
 * </tt>
 * operación. Esto puede reducir la cantidad de reasignación incremental.  
 * <p>
 * <strong> Tenga en cuenta que esta implementación no está sincronizada.
 * </strong>
 * Si varios subprocesos acceden a una instancia de <tt>ListaArreglo</tt> al
 * mismo tiempo, y al menos uno de los hilos modifica la lista estructuralmente,
 * <i>debe</i> estar sincronizado externamente. (Una modificación estructural es
 * cualquier operación que agregue o elimine uno o más elementos, o
 * explícitamente redimensiona el arreglo de respaldo; simplemente establecer el
 * valor de un elemento no es una modificación estructural.) Esto generalmente
 * se logra mediante sincronización en algún objeto que naturalmente encapsula
 * la lista. Si no existe tal objeto, la lista debe ser "envuelta" usando el
 * meétodo{@link Colecciones #synchronizedList Colecciones.synchronizedList}.
 * Esto se hace mejor en el momento de la creación, para evitar accidentes
 * acceso no sincronizado a la lista:
 * <pre>
 * Lista lista = Colecciones.synchronizedList(new ListaArreglo (...));</pre>  
 * <p>
 * <a name="fail-fast">
 * Los iteradores devueltos por {@link #iterator () iterator} de esta clase y
 * {@link #listIterator (int) listIterator} los métodos son
 * <em>fallo rápido:</em></a>
 * si la lista se modifica estructuralmente en cualquier momento después de que
 * el iterador esté creado, de cualquier manera excepto a través del propio
 * iterador {@link ListIterator #remove ()remove} o
 * {@link ListIterator # add (Object) add} métodos, el iterador lanzará un
 * {@link ConcurrentModificationException}. Por lo tanto, frente a modificación
 * simultánea, el iterador falla de manera rápida y limpia, en lugar de que
 * arriesgar el comportamiento arbitrario, no determinista en un indeterminado
 * tiempo en el futuro.
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
 *  
 * <p>
 * Esta clase es miembro de la  
 *
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.2
 * @author FABAME
 * @see Coleccion
 * @see Lista
 * @see ListaEnlazada
 * @see ListaVector
 * @param <E> el tipo de elementos contenidos en esta colección 
 */
public class ListaArreglo<E> extends ListaAbstracta<E>
        implements Lista<E>, RandomAccess, Cloneable, Serializable {

    //Atributos de la clase ListaArreglo.
    /**
     * Número serial version UID generado de la clase ListaArreglo.
     */
    private static final long serialVersionUID = 1381238123920755114L;

    /**
     * Capacidad inicial predeterminada.
     */
    private final int CAPACIDAD_PREDETERMINADA = 10;

    /**
     * Instancia de arreglo vacía compartida utilizada para instancias vacías de
     * tamaño predeterminado. Nosotros distinguimos esto de
     * LISTADO_DATOS_ELEMENTO_VACIO para saber cuánto inflar cuando primer
     * elemento es agregado.
     */
    private final Object[] CAPACIDAD_PREDETERMINADA_LISTADO_DATOS_ELEMENTO_VACIO
            = {};
    /**
     * Listado de elementos en una lista de arreglo. El búfer de arreglo en el
     * que se almacenan los elementos de ListaArreglo. La capacidad de
     * ListaArreglo es la longitud de este búfer de arreglo. Algun    
     * ListaArreglo vacío con listadoDatosElemento ==
     * CAPACIDAD_PREDETERMINADA_LISTADO_DATOS_ELEMENTO_VACIO se expandirá a
     * CAPACIDAD_PREDETERMINADA cuando se agregue el primer elemento.
     */
    transient Object[] listadoDatosElemento;
    /*No privado para simplificar el 
    acceso de clase anidada.*/

    /**
     * Instancia de arreglo vacía compartida utilizada para instancias vacías.
     */
    private Object[] LISTADO_DATOS_ELEMENTO_VACIO = {};

    /**
     * El tamaño de ListaArreglo (la cantidad de elementos que contiene).
     *
     * @serial
     */
    private int tamanio;

    /**
     * El tamaño máximo del arreglo para asignar. Algunas máquinas virtuales
     * reservan algunas palabras de encabezado en una matriz. Los intentos de
     * asignar arreglos más grandes pueden resultar en OutOfMemoryError: el
     * tamaño de la matriz solicitada excede el límite de VM      
     */
    private static final int TAMANIO_MAXIMO_ARREGLO = Integer.MAX_VALUE - 8;

    //Constructores de la clase ListaArreglo.
    /**
     * Construye una lista vacía con una capacidad inicial de diez elementos.
     */
    public ListaArreglo() {
        this.listadoDatosElemento
                = this.CAPACIDAD_PREDETERMINADA_LISTADO_DATOS_ELEMENTO_VACIO;
    }

    /**
     * Construye una lista que contiene los elementos de la colección
     * especificada, en el orden en que el iterador de la colección los
     * devuelve.
     *
     * @param coleccion la coleccion cuyos elementos deben ser colocados en esta
     * lista
     * @throws NullPointerException - si la colección especificada es nula
     */
    public ListaArreglo(Coleccion<? extends E> coleccion) {
        this.listadoDatosElemento = coleccion.paraFormar();
        if ((this.tamanio = this.listadoDatosElemento.length) != 0) {
            /*coleccion.paraFormar podría (incorrectamente) no devolver el 
            Object[] (ver 6260652).*/
            if (this.listadoDatosElemento.getClass() != Object[].class) {
                this.listadoDatosElemento = Arrays.copyOf(
                        this.listadoDatosElemento, this.tamanio,
                        Object[].class);
            } else {
                //Reemplazar con un arreglo vacío.
                this.listadoDatosElemento = this.LISTADO_DATOS_ELEMENTO_VACIO;
            }
        }
    }

    /**
     * Construye una lista vacía con la capacidad inicial especificada.
     *
     * @param capacidadInicial la capacidad inicial de la lista
     * @throws IllegalArgumentException - si la capacidad inicial especificada
     * es negativa
     */
    public ListaArreglo(int capacidadInicial) {
        if (capacidadInicial > 0) {
            this.listadoDatosElemento = new Object[capacidadInicial];
        } else if (capacidadInicial == 0) {
            this.listadoDatosElemento = this.LISTADO_DATOS_ELEMENTO_VACIO;
        } else {
            throw new IllegalArgumentException("Capacidad Ilegal: "
                    + capacidadInicial);
        }
    }

    //Métodos de la clase ListaArreglo.
    /**
     * Agrega el elemento especificado al final de esta lista.
     *
     * @param elemento elemento que se adjuntará a esta lista
     * @return <tt>true</tt> (como se especifica por Coleccion.agregar(E)).
     */
    @Override
    public boolean agregar(E elemento) {
        this.asegurarCapacidadInterna(
                this.tamanio + 1); //Incrementa conteoModulo!!
        this.listadoDatosElemento[this.tamanio++] = elemento;
        return true;
    }

    /**
     * Inserta el elemento especificado en la posición especificada en esta
     * lista. Cambia el elemento actualmente en esa posición (si existe) y
     * cualquier elemento posterior a la derecha (agrega uno a sus índices).
     *
     * @param indice índice en el que se debe insertar el elemento especificado
     * @param elemento elemento a insertar
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void agregar(int indice, E elemento) {
        this.verificarRangoParaAgregar(indice);
        this.asegurarCapacidadInterna(
                this.tamanio + 1); //Incrementa conteoModulo!!
        System.arraycopy(this.listadoDatosElemento, indice,
                this.listadoDatosElemento, indice + 1, this.tamanio - indice);
        this.listadoDatosElemento[indice] = elemento;
        this.tamanio++;
    }

    /**
     * Agrega el componente especificado al final de este arreglo,      
     * aumentando su tamaño en uno. La capacidad de este arreglo es aumentado si
     * su tamaño es mayor que su capacidad.    
     * <p>
     * Este método es idéntico en funcionalidad al método
     * {@link #agregar(Object) agregar(E)} (que es parte de la interfaz
     * {@link Lista}).
     *
     * @param elemento el componente que se agregará      
     */
    public void agregarElemento(E elemento) {
        this.conteoModulo++;
        this.ayudanteAsegurarCapacidad(this.tamanio + 1);
        this.listadoDatosElemento[this.tamanio++] = elemento;
    }

    /**
     * Agrega todos los elementos de la colección especificada al final de esta
     * lista, en el orden en que son devueltos por el iterador de la colección
     * especificada. El comportamiento de esta operación no está definido si la
     * colección especificada se modifica mientras la operación está en
     * progreso. (Esto implica que el comportamiento de esta llamada no está
     * definido si la colección especificada es esta lista, y esta lista no está
     * vacía).
     *
     * @param coleccion colección que contiene elementos para agregar a esta
     * lista
     * @return <tt>true</tt> si esta lista cambió como resultado de la llamada
     * @throws NullPointerException si la colección especificada es nula
     */
    @Override
    public boolean agregarTodo(Coleccion<? extends E> coleccion) {
        Object[] arregloObjetos = coleccion.paraFormar();
        int numeroNuevo = arregloObjetos.length;
        this.asegurarCapacidadInterna(
                this.tamanio + numeroNuevo); //Incrementa conteoModulo!!
        System.arraycopy(arregloObjetos, 0, this.listadoDatosElemento,
                this.tamanio, numeroNuevo);
        this.tamanio += numeroNuevo;
        return numeroNuevo != 0;
    }

    /**
     * Inserta todos los elementos en la colección especificada en esta lista,
     * comenzando en la posición especificada. Cambia el elemento actualmente en
     * esa posición (si existe) y cualquier elemento posterior a la derecha
     * (aumenta sus índices). Los nuevos elementos aparecerán en la lista en el
     * orden en que son devueltos por el iterador de la colección especificada.
     *
     * @param indice índice en el que insertar el primer elemento de la
     * colección especificada
     * @param coleccion colección que contiene elementos para agregar a esta
     * lista
     * @return <tt>true</tt> si esta lista cambió como resultado de la llamada
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException si la colección especificada es null
     */
    @Override
    public boolean agregarTodo(int indice, Coleccion<? extends E> coleccion) {
        this.verificarRango(indice);
        Object[] arregloObjetos = coleccion.paraFormar();
        int numeroNuevo = arregloObjetos.length;
        this.asegurarCapacidadInterna(this.tamanio + numeroNuevo);
        /*Incrementa 
         conteoModulo!!*/
        int numeroMovido = this.tamanio - indice;
        if (numeroMovido > 0) {
            System.arraycopy(this.listadoDatosElemento, indice,
                    this.listadoDatosElemento, indice + numeroNuevo,
                    numeroMovido);
        }
        System.arraycopy(arregloObjetos, 0, this.listadoDatosElemento, indice,
                numeroNuevo);
        this.tamanio += numeroNuevo;
        return numeroNuevo != 0;
    }

    /**
     * Aumenta la capacidad de esta instancia de <tt>ListaArreglo</tt> , si es
     * necesario, para garantizar que pueda contener al menos la cantidad de
     * elementos especificada por el argumento de capacidad mínima.
     *
     * @param capacidadMinima la capacidad mínima deseada
     */
    public void asegurarCapacidad(int capacidadMinima) {
        int expansionMinima = (this.listadoDatosElemento
                != this.CAPACIDAD_PREDETERMINADA_LISTADO_DATOS_ELEMENTO_VACIO)
                        ? 0 /*Cualquier tamaño si no es tabla de elementos por 
                defecto*/
                        /*Más grande que el predeterminado para la tabla vacía 
                predeterminada. Ya esta*/
                        /*se supone que está en el tamaño predeterminado.*/
                        : this.CAPACIDAD_PREDETERMINADA;
        if (capacidadMinima > expansionMinima) {
            this.asegurarCapacidadExplicita(capacidadMinima);
        }
    }

    private void asegurarCapacidadExplicita(int capacidadMinima) {
        this.conteoModulo++;
        //Código consciente de desbordamiento.
        if (capacidadMinima - this.listadoDatosElemento.length > 0) {
            this.crecer(capacidadMinima);
        }
    }

    private void asegurarCapacidadInterna(int capacidadMinima) {
        if (this.listadoDatosElemento
                == this.CAPACIDAD_PREDETERMINADA_LISTADO_DATOS_ELEMENTO_VACIO) {
            capacidadMinima = Math.max(this.CAPACIDAD_PREDETERMINADA,
                    capacidadMinima);
        }
        this.asegurarCapacidadExplicita(capacidadMinima);
    }

    /**
     * Esto implementa la semántica no sincronizada de ensureCapacity. Los
     * métodos sincronizados en esta clase pueden llamar internamente este
     * método para asegurar la capacidad sin incurrir en el costo de una
     * sincronización adicional.
     *
     * @see #asegurarCapacidad(int)      
     */
    private void ayudanteAsegurarCapacidad(int capacidadMinima) {
        //Código consciente de desbordamiento.
        if (capacidadMinima - this.listadoDatosElemento.length > 0) {
            this.crecer(capacidadMinima);
        }
    }
    
    public int buscar(Object objeto){
         int i = this.ultimoIndiceDe(objeto);

        if (i >= 0) {
            return this.tamanio - i;
        }
        return -1;
    }

    /**
     * Devuelve una copia superficial de esta instancia de <tt>
     * ListaArreglo</tt>. (Los elementos mismos no se copian)
     *
     * @return una copia de esta instancia de <tt>ListaArreglo</tt>      
     */
    @Override
    public Object clone() {
        try {
            ListaArreglo<?> arreglo = (ListaArreglo<?>) super.clone();
            arreglo.listadoDatosElemento = Arrays.copyOf(
                    this.listadoDatosElemento, this.tamanio);
            arreglo.conteoModulo = 0;
            return arreglo;
        } catch (CloneNotSupportedException ex) {
            //Esto no debería suceder, ya que somos Clonables.
            throw new InternalError(ex);
        }
    }

    /**
     * Conserva solo los elementos en esta lista que están contenidos en
     * colección especificada En otras palabras, elimina de esta lista todo de
     * sus elementos que no están contenidos en la colección especificada.
     *
     * @param coleccion coleccion que contiene elementos para ser retenidos en
     * esta lista
     * @return {@code true} si esta lista cambió como resultado de la llamada}
     * @throws ClassCastException si la clase de un elemento de esta lista es
     * incompatible con la colección especificada
     * (<a href="Collection.html#optional-restrictions">opcional</a>)      
     *
     * @throws NullPointerException si esta lista contiene un elemento nulo y el
     * la colección especificada no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions"> opcional</a>), o si la
     * colección especificada es nula
     * @see Coleccion #contiene(Object)      
     */
    @Override
    public boolean conservarTodo(Coleccion<?> coleccion) {
        Objects.requireNonNull(coleccion);
        return this.removerLote(coleccion, true);
    }

    /**
     * Devuelve <tt>true</tt> si esta lista contiene el elemento especificado.
     * Más formalmente, devuelve <tt>true</tt> si y solo si esta lista contiene
     * al menos un elemento <tt>elemento</tt>
     * tal que <tt> (objeto == null & nbsp;? & nbsp; eelemento == null & nbsp;:
     * & nbsp; objeto.equals(e))</tt>.
     *
     * @param objeto elemento cuya presencia en esta lista debe probarse
     * @return <tt>true</tt> si esta lista contiene el elemento especificado
     *      
     */
    @Override
    public boolean contiene(Object objeto) {
        return this.indiceDe(objeto) >= 0;
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
        return super.contieneTodo(coleccion);
    }

    /**
     * Copia los componentes de este arreglo en el arreglo especificadao. El
     * elemento en el índice {@code k} en este arreglo se copia en componente
     * {@code k} de {@code unArreglo}.
     *
     * @param unArreglo el arreglo en la que se copian los componentes
     * @throws NullPointerException si el arreglo dado es nulo
     * @throws IndexOutOfBoundsException si el arreglo especificada no es lo
     * suficientemente grande como para contener todos los componentes de este
     * arreglo
     * @throws ArrayStoreException si un componente de este arreglo no es de un
     * tipo de tiempo de ejecución que se puede almacenar en el arreglo
     * especificado
     * @see #paraFormar(Object[])      
     */
    public void copiarEn(Object[] unArreglo) {
        System.arraycopy(this.listadoDatosElemento, 0, unArreglo, 0, this.tamanio);
    }

    /**
     * Aumenta la capacidad para garantizar que pueda contener al menos la
     * cantidad de elementos especificados por el argumento de capacidad mínima.
     *
     * @param capacidadMinima la capacidad mínima deseada      
     */
    private void crecer(int capacidadMinima) {
        //Código consciente de desbordamiento.
        int capacidadAntigua = this.LISTADO_DATOS_ELEMENTO_VACIO.length;
        int capacidadNueva = capacidadAntigua + (capacidadAntigua >> 1);
        if (capacidadNueva - capacidadMinima < 0) {
            capacidadNueva = capacidadMinima;
        }
        if (capacidadNueva - TAMANIO_MAXIMO_ARREGLO > 0) {
            capacidadNueva = granCapacidad(capacidadMinima);
        }
        /*/capacidadMinima suele ser similar al tamaño, por lo que esta es una 
        ganancia:*/
        this.listadoDatosElemento = Arrays.copyOf(this.listadoDatosElemento,
                capacidadNueva);
    }

    /**
     * Devuelve el componente en el índice especificado.   
     * <p>
     * Este método es idéntico en funcionalidad al {@link #obtener(int)} método
     * (que es parte de la interfaz {@link Lista}).
     *
     * @param indice un índice en este arreglo
     * @return el componente en el índice especificado
     * @throws ArrayIndexOutOfBoundsException si el índice está fuera de rango
     * ({@code indice < 0 || indice > = tamanio()})      
     */
    public E elementoEn(int indice) {
        if (indice >= this.tamanio) {
            throw new ArrayIndexOutOfBoundsException(indice + " >= " + this.tamanio);
        }

        return this.listadoDatosElemento(indice);
    }

    /**
     * Devuelve una enumeración de los componentes de este arreglo. los el
     * objeto devuelto {
     *
     * @Enumeración}} generará todos los elementos en este arreglo El primer
     * elemento generado es el elemento en el índice {@code 0}, luego el
     * elemento en el índice {@code 1}, y así sucesivamente.
     * @return una enumeración de los componentes de este arreglo
     * @see Iterator      
     */
    public Enumeration<E> elementos() {
        return new Enumeration<E>() {//Inicio de la clase anónima Enumeration.
            //Atributo de la clase anónima Enumeration.
            int count = 0;

            //Métodos de la clase anónima Enumeration.
            @Override
            public boolean hasMoreElements() {
                return this.count < tamanio;
            }

            @Override
            public E nextElement() {
                synchronized (ListaArreglo.this) {
                    if (this.count < tamanio) {
                        return listadoDatosElemento(this.count++);
                    }
                }
                throw new NoSuchElementException("Enumeración del Vector");
            }
        };//Fin de la clase anónima Enumeration.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        return super.equals(object);
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
     * Guarde el estado de la instancia <tt>ListaArreglo</tt> en una secuencia
     * (que es, serializarlo).
     *
     * @serialData La longitud del arreglo que respalda la <tt>ListaArreglo</tt>
     * se emite la instancia(int), seguido de todos sus elementos(cada uno un
     * <tt>Object</tt>) en el orden correcto.      
     */
    private void escribirObjeto(ObjectOutputStream salida) throws IOException {
        //Escribe el conteo de elementos y cualquier material oculto.
        int contadorModEsperado = this.conteoModulo;
        salida.defaultWriteObject();
        /*Escribe el tamaño como capacidad para compatibilidad de comportamiento 
        con clone().*/
        salida.writeInt(this.tamanio);
        //Escribe todos los elementos en el orden correcto.
        for (int i = 0; i < this.tamanio; i++) {
            salida.writeObject(this.listadoDatosElemento[i]);
        }
        if (this.conteoModulo != contadorModEsperado) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * Devuelve <tt>true</tt> si esta lista no contiene elementos.      
     *
     * @return <tt>true</tt> si esta lista no contiene elementos      
     */
    @Override
    public boolean estaVacia() {
        return tamanio == 0;
    }

    /**
     * Reemplaza el elemento en la posición especificada en esta lista con el
     * elemento especificado.
     *
     * @param indice índice del elemento a reemplazar
     * @param elemento elemento para ser almacenado en la posición especificada
     * @return el elemento previamente en la posición especificada
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E establecer(int indice, E elemento) {
        this.verificarRango(indice);
        E valorAntiguo = this.listadoDatosElemento(indice);
        this.listadoDatosElemento[indice] = elemento;
        return valorAntiguo;
    }

    /**
     * Establece el componente en el {@code indice} especificado de este arreglo
     * para ser el objeto especificado. El componente anterior en ese la
     * posición se descarta.   
     * <p>
     * El índice debe ser un valor mayor o igual que {@code 0} y menor que el
     * tamaño actual del arreglo.    
     * <p>
     * Este método es idéntico en funcionalidad al
     * {@link #establecer(int, Object) establecer(int, E)} método (que es parte
     * de la interfaz {@link Lista}). Tenga en cuenta que El método
     * {@code establecer} invierte el orden de los parámetros, para acercarse
     * más coincide con el uso del conjunto. Tenga en cuenta también que el
     * método {@code establecer} devuelve el valor anterior que se almacenó en
     * la posición especificada.
     *
     * @param objeto en que se configurará el componente
     * @param indice el índice especificado
     * @throws ArrayIndexOutOfBoundsException si el índice está fuera de rango
     * ({@code indice < 0 || indice> = tamanio()})      
     */
    public void establecerElementoEn(E objeto, int indice) {
        if (indice >= this.tamanio) {
            throw new ArrayIndexOutOfBoundsException(indice + " >= "
                    + this.tamanio);
        }
        this.listadoDatosElemento[indice] = objeto;
    }

    /**
     * Establece el tamaño de este arreglo. Si el nuevo tamaño es mayor que el
     * tamaño actual, se agregan nuevos elementos {@code null} al final de el
     * arreglo. Si el nuevo tamaño es menor que el tamaño actual, todo los
     * componentes en el índice {@code nuevoTamanio} y mayor se descartan.
     *
     * @param nuevoTamanio el nuevo tamaño de este arreglo
     * @throws ArrayIndexOutOfBoundsException si el nuevo tamaño es negativo
     *      
     */
    public void establecerTamanio(int nuevoTamanio) {
        this.conteoModulo++;
        if (nuevoTamanio > this.tamanio) {
            this.ayudanteAsegurarCapacidad(nuevoTamanio);
        } else {
            for (int i = nuevoTamanio; i < this.tamanio; i++) {
                this.listadoDatosElemento[i] = null;
            }
        }
        this.tamanio = nuevoTamanio;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        final int expectedModCount = this.conteoModulo;
        @SuppressWarnings("unchecked")
        final E[] elementData = (E[]) this.listadoDatosElemento;
        final int size = this.tamanio;
        for (int i = 0; this.conteoModulo
                == expectedModCount && i < size; i++) {
            action.accept(elementData[i]);
        }
        if (this.conteoModulo != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    private static int granCapacidad(int capacidadMinima) {
        if (capacidadMinima < 0) { //Desbordamiento.
            throw new OutOfMemoryError();
        }
        return (capacidadMinima > TAMANIO_MAXIMO_ARREGLO)
                ? Integer.MAX_VALUE
                : TAMANIO_MAXIMO_ARREGLO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
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
     * Devuelve el índice de la primera aparición del elemento especificado en
     * esta lista, o -1 si esta lista no contiene el elemento. Más formalmente,
     * devuelve el índice más bajo <tt>i
     * </tt> tal que <tt>(objeto == null & nbsp;? & nbsp; obtener(i) == null &
     * nbsp;: & nbsp; objeto.equals(obtener(i)))</tt>, o -1 si no hay tal
     * índice.      
     */
    @Override
    public int indiceDe(Object objeto) {
        if (objeto == null) {
            for (int i = 0; i < this.tamanio; i++) {
                if (this.listadoDatosElemento[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.tamanio; i++) {
                if (objeto.equals(this.listadoDatosElemento[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Devuelve el índice de la primera aparición del elemento especificado en
     * este arreglo, buscando hacia adelante desde {@code indice}, o devuelve -1
     * si el elemento no se encuentra. Más formalmente, devuelve el índice más
     * bajo {@code i} tal que
     * <tt>(i & nbsp; & gt; = & nbsp; indice & nbsp; & amp;; & nbsp; (objeto ==
     * null & nbsp;? & nbsp; obtener(i) == null & nbsp;: & nbsp; objeto.equals
     * (obtener (i))) )</tt>, o -1 si no hay tal índice.
     *
     * @param objeto elemento para buscar
     * @param indice índice para comenzar a buscar desde
     * @return el índice de la primera aparición del elemento en este vector en
     * la posición {@code indice} o posterior en el vector;{@code -1} si el
     * elemento no se encuentra.
     * @throws IndexOutOfBoundsException si el índice especificado es negativo
     * @see Object equals(Object)      
     */
    public int indiceDe(Object objeto, int indice) {
        if (objeto == null) {
            for (int i = indice; i < this.tamanio; i++) {
                if (this.listadoDatosElemento[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = indice; i < this.tamanio; i++) {
                if (objeto.equals(this.listadoDatosElemento[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Inserta el objeto especificado como un componente en este arreglo en el
     * especificado {@code indice}. Cada componente en este arreglo con un
     * índice mayor o igual al {@code indice} especificado es se desplazó hacia
     * arriba para tener un índice uno mayor que el valor que tenía previamente.
     *      
     * <p>
     * El índice debe ser un valor mayor o igual que {@code 0} y menor o igual
     * que el tamaño actual del vector. (Si el índice es igual al tamaño actual
     * del arreglo, el nuevo elemento se adjunta a la ListaArreglo.)      
     * <p>
     * Este método es idéntico en funcionalidad al
     * {@link #agregar(int, Object) agregar(int, E)} método (que es parte de la
     * interfaz {@link Lista}). Tenga en cuenta que el método {@code agregar}
     * invierte el orden de los parámetros, para acercarse más coincide con el
     * uso del conjunto.
     *
     * @param objeto el componente para insertar
     * @param indice donde insertar el nuevo componente
     * @throws ArrayIndexOutOfBoundsException si el índice está fuera de
     * rango({@code indice < 0 || indice > tamanio()})
     */
    public void insertarElementoEn(Object objeto, int indice) {
        this.conteoModulo++;
        if (indice > this.tamanio) {
            throw new ArrayIndexOutOfBoundsException(indice
                    + " > " + this.tamanio);
        }
        this.ayudanteAsegurarCapacidad(this.tamanio + 1);
        System.arraycopy(this.listadoDatosElemento,
                indice,
                this.listadoDatosElemento,
                indice + 1,
                this.tamanio - indice);
        this.listadoDatosElemento[indice] = objeto;
        this.tamanio++;
    }

    /**
     * Devuelve un iterador sobre los elementos en esta lista en la secuencia
     * correcta.    
     * <p>
     * El iterador devuelto es <a href="#fail-fast"> <i>Fallar rápido</i> </a>.
     *
     * @return un iterador sobre los elementos en esta lista en la secuencia
     * correcta      
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Reconstituir la instancia <tt>ListaArreglo</tt> de una secuencia (es
     * decir, deserializarlo).      
     */
    private void leerObjeto(ObjectInputStream salida) throws IOException,
            ClassNotFoundException {
        this.listadoDatosElemento = this.LISTADO_DATOS_ELEMENTO_VACIO;
        //Leer en tamaño y cualquier material oculto.
        salida.defaultReadObject();
        //Capacidad de lectura en.
        salida.readInt();//Ignorado.
        if (this.tamanio > 0) {
            /*Ser como clone(), asignar arreglo en función del tamaño y no de 
            la capacidad*/
            this.asegurarCapacidadInterna(this.tamanio);
            Object[] arregloObjetos = this.listadoDatosElemento;
            // Leer en todos los elementos en el orden correcto.
            for (int i = 0; i < this.tamanio; i++) {
                arregloObjetos[i] = salida.readObject();
            }
        }
    }

    /**
     * Elimina todos los elementos de esta lista. La lista estar vacío después
     * de que regrese esta llamada.      
     */
    @Override
    public void limpiar() {
        this.conteoModulo++;
        //Limpia para dejar que GC haga su trabajo.
        for (int i = 0; i < this.tamanio; i++) {
            this.listadoDatosElemento[i] = null;
        }
        this.tamanio = 0;
    }

    /**
     * Devuelve un iterador de lista sobre los elementos en esta lista (en
     * secuencia).
     * <p>
     * El iterador de la lista devuelta es <a href="#fail-fast"><i>Fallar
     * rápido</i></a>. @see #listIterator(int)      
     */
    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    /**
     * Devuelve un iterador de lista sobre los elementos en esta lista (en
     * secuencia), comenzando en la posición especificada en la lista. El índice
     * especificado indica el primer elemento que sería devuelto por una llamada
     * inicial a {@link ListIterator #next next}. Una llamada inicial a
     * {@link ListIterator #previous previous} haría devuelve el elemento con el
     * índice especificado menos uno.    
     * <p>
     * El iterador de la lista devuelta es <a href="#fail-fast"> <i>Fallar
     * rápido</i></a>.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public ListIterator<E> listIterator(int indice) {
        if (indice < 0 || indice > this.tamanio) {
            throw new IndexOutOfBoundsException("Índice: " + indice);
        }
        return new ListItr(indice);
    }

    @SuppressWarnings("unchecked")
    E listadoDatosElemento(int indice) {
        return (E) this.listadoDatosElemento[indice];
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
     * Devuelve el elemento en la posición especificada en esta lista.
     *
     * @param indice índice del elemento a devolver
     * @return el elemento en la posición especificada en esta lista
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E obtener(int indice) {
        this.verificarRango(indice);
        return listadoDatosElemento(indice);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void ordenar(Comparator<? super E> c) {
        final int expectedModCount = this.conteoModulo;
        Arrays.sort((E[]) this.listadoDatosElemento, 0, this.tamanio, c);
        if (this.conteoModulo != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        this.conteoModulo++;
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos de esta lista en la
     * secuencia correcta (del primer al último elemento).
     * <p>
     * El arreglo devuelta será "seguro" ya que esta lista no mantiene ninguna
     * referencia a ella. (En otras palabras, este método debe asignar un nuevo
     * arreglo). La persona que llama es libre de modificar el arreglo devuelta.
     *
     * <p>
     * Este método actúa como puente entre las API basadas en arreglos y basadas
     * en arreglos.
     *
     * @return un arreglo que contiene todos los elementos en esta lista en la
     * secuencia correcta
     */
    @Override
    public Object[] paraFormar() {
        return Arrays.copyOf(this.listadoDatosElemento, this.tamanio);
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos de esta lista en la
     * secuencia correcta (del primer al último elemento); el tipo de tiempo de
     * ejecución de la arreglo devuelta es el del arreglo especificado. Si la
     * lista se ajusta al arreglo especificado, se devuelve allí. De lo
     * contrario, se asigna un nuevo arreglo con el tipo de tiempo de ejecución
     * del arreglo especificado y el tamaño de esta lista.
     * <p>
     * Si la lista cabe en el arreglo especificado con espacio de sobra (es
     * decir, el arreglo tiene más elementos que la lista), el elemento en el
     * arreglo inmediatamente después del final de la colección se establece en
     * <tt>null</tt> . (Esto es útil para determinar la longitud de la lista
     * <i>solamente</i> si la persona que llama sabe que la lista no contiene
     * ningún elemento nulo).
     *
     * @param <T> el tipo de elementos contenidos en este arreglo
     * @param arreglo el arreglo en la que se almacenarán los elementos de la
     * lista, si es lo suficientemente grande; de lo contrario, se asigna un
     * nuevo arreglo del mismo tipo de tiempo de ejecución para este fin
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
            //Crear un nuevo arreglo del tipo de tiempo de ejecución de a, pero mi contenido:
            return (T[]) Arrays.copyOf(this.listadoDatosElemento,
                    this.tamanio,
                    arreglo.getClass());

        }
        System.arraycopy(this.listadoDatosElemento,
                0,
                arreglo,
                0,
                this.tamanio);
        if (arreglo.length > this.tamanio) {
            arreglo[this.tamanio] = null;
        }
        return arreglo;
    }

    /**
     * Devuelve el primer componente (el elemento en el índice{@code 0}) de este
     * erreglo
     *
     * @return el primer componente de este arreglo
     * @throws NoSuchElementException si este arreglo no tiene componentes      
     */
    public E primerElemento() {
        if (this.tamanio == 0) {
            throw new NoSuchElementException();
        }
        return this.listadoDatosElemento(0);
    }

    /**
     * Recorta la capacidad de esta instancia de <tt>ListaArreglo</tt> para ser
     * el tamaño actual de la lista Una aplicación puede usar esta operación
     * para minimizar el almacenamiento de una instancia de
     * <tt>ListaArreglo</tt>.
     */
    public void recortarATamanio() {
        this.conteoModulo++;
        if (this.tamanio < this.listadoDatosElemento.length) {
            this.listadoDatosElemento = (this.tamanio == 0)
                    ? this.LISTADO_DATOS_ELEMENTO_VACIO
                    : Arrays.copyOf(this.listadoDatosElemento, this.tamanio);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void reemplazarTodo(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final int expectedModCount = this.conteoModulo;
        final int size = this.tamanio;
        for (int i = 0; this.conteoModulo == expectedModCount
                && i < size; i++) {
            this.listadoDatosElemento[i]
                    = operator.apply((E) this.listadoDatosElemento[i]);
        }
        if (this.conteoModulo != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        this.conteoModulo++;
    }

    /**
     * Elimina el elemento en la posición especificada en esta lista. Desplaza
     * cualquier elemento posterior a la izquierda (resta uno de sus índices).
     *
     * @param indice el índice del elemento a eliminar
     * @return el elemento que se eliminó de la lista
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E remover(int indice) {
        this.verificarRango(indice);
        this.conteoModulo++;
        E valorAntiguo = this.listadoDatosElemento(indice);
        int numeroMovido = this.tamanio - indice - 1;
        if (numeroMovido > 0) {
            System.arraycopy(this.listadoDatosElemento, indice + 1,
                    this.listadoDatosElemento, indice, numeroMovido);
        }
        this.listadoDatosElemento[--this.tamanio] = null;
        /*Limpia para dejar 
        que GC haga su trabajo.*/
        return valorAntiguo;
    }

    /**
     * Elimina la primera aparición del elemento especificado de esta lista, si
     * está presente. Si la lista no contiene el elemento, es sin cambios. Más
     * formalmente, elimina el elemento con el índice más bajo <tt>i</tt> tal
     * que <tt> (objeto == null & nbsp;? & nbsp; obtener(i) == null & nbsp;: &
     * nbsp; objeto.equals (obtener(i)))
     * </tt>
     * (si tal elemento existe). Devuelve <tt>true</tt> si esta lista contenía
     * el elemento especificado (o equivalentemente, si esta lista  cambiado
     * como resultado de la llamada).
     *
     * @param objeto elemento que se eliminará de esta lista, si está presente  
     * @return <tt>true</tt> si esta lista contiene el elemento especificado
     *      
     */
    @Override
    public boolean remover(Object objeto) {
        if (objeto == null) {
            for (int indice = 0; indice < this.tamanio; indice++) {
                if (this.listadoDatosElemento[indice] == null) {
                    this.removerRapido(indice);
                    return true;
                }
            }
        } else {
            for (int indice = 0; indice < this.tamanio; indice++) {
                if (objeto.equals(this.listadoDatosElemento[indice])) {
                    this.removerRapido(indice);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Elimina la primera aparición (más indexada) del argumento de este arreglo
     * Si el objeto se encuentra en este arreglo, cada componente en el arregñp
     * con un índice mayor o igual al el índice del objeto se desplaza hacia
     * abajo para tener un índice más pequeño que el valor que tenía
     * previamente.    
     * <p>
     * Este método es idéntico en funcionalidad al {@link #remover(Objeto)}
     * método (que es parte del interfaz {@link Lista}).
     *
     * @param objeto el componente que se eliminará
     * @return {@code true} si el argumento era un componente de este arreglo;
     * {@code false} de lo contrario.      
     */
    public boolean removerElemento(Object objeto) {
        this.conteoModulo++;
        int i = this.indiceDe(objeto);
        if (i >= 0) {
            this.removerElementoEn(i);
            return true;
        }
        return false;
    }

    /**
     * Elimina el componente en el índice especificado. Cada componente en este
     * arreglo con un índice mayor o igual al especificado {@code indice} se
     * desplaza hacia abajo para tener un índice uno más pequeño que el valor
     * que tenía anteriormente. El tamaño de este arreglo se reduce en
     * {@code 1}.     
     * <p>
     * El índice debe ser un valor mayor o igual que {@code 0}y menor que el
     * tamaño actual del arreglo.      
     * <p>
     * Este método es idéntico en funcionalidad al método
     * {@link #remover(int)}(que es parte de la interfaz {@link Lista}). Tenga
     * en cuenta que el método {@code remover} devuelve el valor anterior que
     * estaba almacenado en el posición especificada.
     *
     * @param indice el índice del objeto para eliminar
     * @throws ArrayIndexOutOfBoundsException si el índice está fuera de rango
     * ({@code indice < 0 || indice > = tamanio()})      
     */
    public void removerElementoEn(int indice) {
        this.conteoModulo++;
        if (indice >= this.tamanio) {
            throw new ArrayIndexOutOfBoundsException(indice + " >= "
                    + this.tamanio);
        } else if (indice < 0) {
            throw new ArrayIndexOutOfBoundsException(indice);
        }
        int j = this.tamanio - indice - 1;
        if (j > 0) {
            System.arraycopy(this.listadoDatosElemento, indice + 1,
                    this.listadoDatosElemento, indice, j);
        }
        this.tamanio--;
        this.listadoDatosElemento[this.tamanio] = null;
        /* Deja que gc haga su trabajo*/

    }

    private boolean removerLote(Coleccion<?> coleccion, boolean complemento) {
        Object[] listadoDatosElemento = this.listadoDatosElemento;
        int r = 0, w = 0;
        boolean modificado = false;
        try {
            for (; r < this.tamanio; r++) {
                if (coleccion.contiene(this.listadoDatosElemento[r])
                        == complemento) {
                    this.listadoDatosElemento[w++]
                            = this.listadoDatosElemento[r];
                }
            }
        } finally {
            /*Preservar la compatibilidad de comportamiento con 
            ColeccionAbstracta*,*/
            //incluso si coleccion.contiene() lanza.
            if (r != this.tamanio) {
                System.arraycopy(listadoDatosElemento, r, listadoDatosElemento,
                        w, this.tamanio - r);
                w += this.tamanio - r;
            }
            if (w != this.tamanio) {
                //Limpia para dejar que GC haga su trabajo.
                for (int i = w; i < this.tamanio; i++) {
                    listadoDatosElemento[i] = null;
                }
                this.conteoModulo += this.tamanio - w;
                this.tamanio = w;
                modificado = true;
            }
        }
        return modificado;
    }

    /**
     * Elimina de esta lista todos los elementos cuyo índice está entre
     * {@code desdeIndice}, inclusive, y {@code hastaIndice}, exclusivo.  
     * Cambia los elementos sucesivos a la izquierda (reduce su índice). Esta
     * llamada acorta la lista mediante elementos
     * {@code (hastaIndice - desdeIndice}. (Si
     * {@code hastaIndice == desdeIndice}, esta operación no tiene ningún
     * efecto).@throws IndexOutOfBoundsException if {@code desdeIndice} o
     * {@code hastaIndice} está fuera de rango ({@code desdeIndice <0 ||
     * desdeIndice > = tamanio() ||
     *  hastaIndice> tamanio() ||
     *  hastaIndice < desdeindice})      
     */
    @Override
    protected void removerRango(int desdeIndice, int hastaIndice) {
        this.conteoModulo++;
        int numeroMovido = this.tamanio - hastaIndice;
        System.arraycopy(this.listadoDatosElemento, hastaIndice,
                this.listadoDatosElemento, desdeIndice, numeroMovido);

        //Claro para dejar que GC haga su trabajo
        int nuevoTamanio = this.tamanio - (hastaIndice - desdeIndice);
        for (int i = nuevoTamanio; i < this.tamanio; i++) {
            this.listadoDatosElemento[i] = null;
        }
        this.tamanio = nuevoTamanio;
    }

    /*
      * Método de eliminación privada que omite la verificación de límites y no
      * devuelve el valor eliminado.
      */
    private void removerRapido(int indice) {
        this.conteoModulo++;
        int numeroMovido = this.tamanio - indice - 1;
        if (numeroMovido > 0) {
            System.arraycopy(this.listadoDatosElemento, indice + 1,
                    this.listadoDatosElemento, indice, numeroMovido);
        }
        this.listadoDatosElemento[--this.tamanio] = null;
        /*Limpia para dejar 
        que GC haga su trabajo.*/
    }

    @Override
    public boolean removerSi(Predicate<? super E> filtro) {
        Objects.requireNonNull(filtro);
        //Averiguar qué elementos deben eliminarse.
        /*Cualquier excepción lanzada desde el predicado de filtro en 
        esta etapa.*/
        //Dejará la colección sin modificaciones.
        int removerConteo = 0;
        final BitSet removerConjunto = new BitSet(this.tamanio);
        final int conteoModuloEsperado = this.conteoModulo;
        final int tamanio = this.tamanio;
        for (int i = 0; this.conteoModulo == conteoModuloEsperado
                && i < tamanio; i++) {
            @SuppressWarnings("unchecked")
            final E elemento = (E) this.listadoDatosElemento[i];
            if (filtro.test(elemento)) {
                removerConjunto.set(i);
                removerConteo++;
            }
        }
        if (this.conteoModulo != conteoModuloEsperado) {
            throw new ConcurrentModificationException();
        }

        /*Desplazar los elementos supervivientes que quedaron sobre los espacios 
        que dejaron los elementos eliminados.*/
        final boolean cualquieraParaRemover = removerConteo > 0;
        if (cualquieraParaRemover) {
            final int nuevoTamanio = tamanio - removerConteo;
            for (int i = 0, j = 0; (i < tamanio)
                    && (j < nuevoTamanio); i++, j++) {
                i = removerConjunto.nextClearBit(i);
                this.listadoDatosElemento[j] = this.listadoDatosElemento[i];
            }
            for (int k = nuevoTamanio; k < tamanio; k++) {
                this.listadoDatosElemento[k] = null;
                /*Deja que gc haga 
                su trabajo.*/
            }
            this.tamanio = nuevoTamanio;
            if (this.conteoModulo != conteoModuloEsperado) {
                throw new ConcurrentModificationException();
            }
            this.conteoModulo++;
        }

        return cualquieraParaRemover;
    }

    /**
     * Elimina de esta lista todos sus elementos que están contenidos en
     * colección especificada.
     *
     * @param coleccion colección que contiene elementos que se eliminarán de
     * esta lista
     * @return {@code true} si esta lista cambió como resultado de la llamada
     * @throws ClassCastException si la clase de un elemento de esta lista es
     * incompatible con la colección especificada
     * (<a href="Collection.html#optional-restrictions"> opcional</a>)
     * @throws NullPointerException si esta lista contiene un elemento nulo y la
     * colección especificada no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions"> opcional</a>), o si la
     * colección especificada es nula
     * @see Coleccion #contiene(Object)      
     */
    @Override
    public boolean removerTodo(Coleccion<?> coleccion) {
        Objects.requireNonNull(coleccion);
        return this.removerLote(coleccion, false);
    }

    /**
     * Elimina todos los componentes de este arreglo y establece su tamaño en
     * cero.   
     * <p>
     * Este método es idéntico en funcionalidad al {@link #limpiar} método (que
     * es parte de la interfaz {@link Lista}).      
     */
    public void removerTodosLosElementos() {
        this.conteoModulo++;
        //Deja que gc haga su trabajo.
        for (int i = 0; i < this.tamanio; i++) {
            this.listadoDatosElemento[i] = null;
        }

        this.tamanio = 0;
    }

    /**
     * Crea un <em><a href="Spliterator.html#binding"> enlace de último momento
     * </a></em> y <em>Fallar rápido</em> {@link Spliterator} sobre los
     * elementos en esta lista.
     * <p>
     * Los {@code Spliterator} informan {@link Spliterator #SIZED},
     * {@link Spliterator #SUBSIZED} y {@link Spliterator #ORDERED}. Las
     * implementaciones prioritarias deben documentar el informe de  valores
     * característicos.
     *
     * @return a {@code Spliterator} sobre los elementos en esta lista
     * @since 1.8      
     */
    @Override
    public Spliterator<E> spliterator() {
        return new ListaArregloSpliterator<>(this, 0, -1, 0);
    }

    /**
     * Devuelve una vista de la parte de esta lista entre las especificadas
     * {@code desdeIndice}, inclusive, y {@code hastaIndice}, exclusivo. (Si
     * {@code desdeIndice} y {@code hastaIndice} son iguales, la lista devuelta
     * es vacío.) La lista devuelta está respaldada por esta lista, por lo que
     * no es estructural los cambios en la lista devuelta se reflejan en esta
     * lista, y viceversa. La lista devuelta admite todas las operaciones de
     * lista opcionales.      
     * <p>
     * Este método elimina la necesidad de operaciones de rango explícitas (de
     * el tipo que comúnmente existe para las matrices). Cualquier operación que
     * espera una lista se puede usar como una operación de rango al pasar una
     * vista sublista en lugar de una lista completa. Por ejemplo, la siguiente
     * expresión idiomática elimina un rango de elementos de una lista:      
     * <pre>
     * lista.subLista(de, a) .limpiar();
     *  </pre>Se pueden construir modismos similares para
     * {@link #indiceDe(Object)} y {@link #ultimoIndiceDe(Object)}, y todos los
     * algoritmos en el La clase {{link Collections} se puede aplicar a una
     * subList.
     *
     * <p>
     * La semántica de la lista devuelta por este método no se define si  la
     * lista de respaldo (es decir, esta lista) está <i>estructuralmente
     * modificada</i> en   cualquier forma que no sea a través de la lista
     * devuelta. (Las modificaciones estructurales son  aquellos que cambian el
     * tamaño de esta lista, o lo perturban en tal  una moda que las iteraciones
     * en curso pueden arrojar resultados incorrectos.)    @throws
     * IndexOutOfBoundsException {@inheritDoc}    @throws
     * IllegalArgumentException {@inheritDoc}      
     */
    @Override
    public Lista<E> subLista(int desdeIndice, int hastaIndice) {
        verificarRangoSubLista(desdeIndice, hastaIndice, this.tamanio);
        return new SubLista(this, 0, desdeIndice, hastaIndice);
    }

    @SuppressWarnings("unchecked")
    private ListaArreglo<E> superClone() {
        try {
            return (ListaArreglo<E>) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError(ex);
        }
    }

    static void verificarRangoSubLista(int desdeIndice, int hastaIndice,
            int tamanio) {
        if (desdeIndice < 0) {
            throw new IndexOutOfBoundsException("desdeIndice = " + desdeIndice);
        }
        if (hastaIndice > tamanio) {
            throw new IndexOutOfBoundsException("hastaIndice = " + hastaIndice);
        }
        if (desdeIndice > hastaIndice) {
            throw new IllegalArgumentException("desdeIndice(" + desdeIndice
                    + ") > hastaIndice(" + hastaIndice + ")");
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Devuelve el último componente del arreglo.
     *
     * @return el último componente del arreglo, es decir, el componente en el
     * índice <code>tamanio() & nbsp; - & nbsp; 1</code>.
     * @throws NoSuchElementException si este vector está vacío      
     */
    public E ultimoElemento() {
        if (this.tamanio == 0) {
            throw new NoSuchElementException();
        }
        return this.listadoDatosElemento(this.tamanio - 1);
    }

    /**
     * Devuelve el índice de la última aparición del elemento especificado en
     * esta lista, o -1 si esta lista no contiene el elemento. Más formalmente,
     * devuelve el índice más alto <tt>i</tt>
     * tal que <tt>(objeto == null & nbsp;? & nbsp; obtener(i) == null & nbsp;:
     * & nbsp; objeto.equals(obtener (i)))</tt>, o -1 si no hay tal índice.
     *      
     */
    @Override
    public int ultimoIndiceDe(Object objeto) {
        if (objeto == null) {
            for (int i = this.tamanio - 1; i >= 0; i--) {
                if (this.listadoDatosElemento[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = this.tamanio - 1; i >= 0; i--) {
                if (objeto.equals(this.listadoDatosElemento[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Devuelve el índice de la última aparición del elemento especificado en
     * este arreglo, buscando hacia atrás desde {@code indice}, o devuelve -1 si
     * el elemento no se encuentra. Más formalmente, devuelve el índice más alto
     * {@code i} tal que
     * <tt>(i & nbsp; & lt; = & nbsp; index & nbsp; & amp;; & nbsp; (objeto ==
     * null & nbsp;? & nbsp; obtener(i) == null & nbsp;: & nbsp;
     * objeto.equals(obtener (i))))</tt>, o -1 si no hay tal índice.
     *
     * @param objeto elemento para buscar
     * @param indice índice para comenzar a buscar hacia atrás desde
     * @return el índice de la última aparición del elemento en posición menor o
     * igual que {@code indice} en este arreglo; -1 si el elemento no se
     * encuentra.
     * @throws IndexOutOfBoundsException si el índice especificado es mayor
     * igual o igual al tamaño actual de este arreglo      
     */
    public int ultimoIndiceDe(Object objeto, int indice) {
        if (indice >= this.tamanio) {
            throw new IndexOutOfBoundsException(indice + " >= " + this.tamanio);
        }

        if (objeto == null) {
            for (int i = indice; i >= 0; i--) {
                if (this.listadoDatosElemento[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = indice; i >= 0; i--) {
                if (objeto.equals(this.listadoDatosElemento[i])) {
                    return i;
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

    /**
     * Verifica si el índice dado está dentro del rango. Si no, lanza un
     * apropiado excepción en tiempo de ejecución. Este método no verifica si el
     * índice es negativo: siempre se usa inmediatamente antes del acceso a un
     * arreglo, que arroja una ArrayIndexOutOfBoundsException si el índice es
     * negativo.      
     */
    private void verificarRango(int indice) {
        if (indice >= this.tamanio) {
            throw new IndexOutOfBoundsException(
                    this.mostrarMensajeFueraDeLosLimites(indice));
        }
    }

    /**
     * Una versión de verificarRango utilizada por agregar y agregarTodo.      
     */
    private void verificarRangoParaAgregar(int indice) {
        if (indice > this.tamanio || indice < 0) {
            throw new IndexOutOfBoundsException(
                    this.mostrarMensajeFueraDeLosLimites(indice));
        }
    }

    //Clase interna Itr.
    /**
     * Una versión optimizada de ListaAbstracta.Itr.      
     */
    private class Itr implements Iterator<E> {

        //Atributos de la clase interna Itr.
        int expectedModCount = conteoModulo;
        int cursor;       //Índice del próximo elemento a devolver.
        int lastRet = -1;

        /*Índice del último elemento devuelto; -1 
                      si no hay tal.*/
        //Constructor de la clase interna Itr.
        Itr() {
            this.cursor = 0;
            this.expectedModCount = conteoModulo;
            this.lastRet = -1;
        }

        //Métodos de la clase interna Itr.
        final void checkForComodification() {
            if (conteoModulo != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> consumer) {
            Objects.requireNonNull(consumer);
            final int size = ListaArreglo.this.tamanio;
            int i = this.cursor;
            if (i >= size) {
                return;
            }
            final Object[] elementData = ListaArreglo.this.listadoDatosElemento;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            while (i != size && conteoModulo == this.expectedModCount) {
                consumer.accept((E) elementData[i++]);
            }
            /*Actualizar una vez al final de la iteración para reducir el 
            tráfico de escritura de montón.*/
            this.cursor = i;
            this.lastRet = i - 1;
            checkForComodification();
        }

        @Override
        public boolean hasNext() {
            return this.cursor != tamanio;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            checkForComodification();
            int i = this.cursor;
            if (i >= tamanio) {
                throw new NoSuchElementException();
            }
            Object[] elementData = ListaArreglo.this.listadoDatosElemento;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            this.cursor = i + 1;
            return (E) elementData[this.lastRet = i];
        }

        @Override
        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                ListaArreglo.this.remover(this.lastRet);
                this.cursor = this.lastRet;
                this.lastRet = -1;
                this.expectedModCount = conteoModulo;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    //Clase interna ListItr.
    /**
     * Una versión optimizada de ListaAbstracta.ListItr.      
     */
    private class ListItr extends Itr implements ListIterator<E> {

        //Constructores de la clase interna ListItr.
        ListItr() {
            super();
            this.cursor = 0;
        }

        ListItr(int index) {
            super();
            this.cursor = index;
        }

        //Métodos de la clase interna ListItr.
        @Override
        public void add(E e) {
            this.checkForComodification();

            try {
                int i = this.cursor;
                ListaArreglo.this.agregar(i, e);
                this.cursor = i + 1;
                this.lastRet = -1;
                this.expectedModCount = conteoModulo;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        @Override
        public int nextIndex() {
            return this.cursor;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E previous() {
            this.checkForComodification();
            int i = this.cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            Object[] elementData = ListaArreglo.this.listadoDatosElemento;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            this.cursor = i;
            return (E) elementData[this.lastRet = i];
        }

        @Override
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override
        public void set(E e) {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                ListaArreglo.this.establecer(this.lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

//Clase interna ListaArregloSpliterator.
    /**
     * Spliterator disidente por-dos indexado basado en índices
     */
    static final class ListaArregloSpliterator<E> implements Spliterator<E> {

        //Atributos de la clase inerna ListaArregloSpliterator.
        /*
         * Si las ListasArreglo era inmutable o estructuralmente inmutable (no
         * agrega, elimina, etc.), podríamos implementar sus spliterators
         * con Arrays.spliterator. En cambio, detectamos tanto
         * interferencia durante el cruce como sea práctico sin
         * sacrificando mucho rendimiento. Confiamos principalmente en
         * ModCounts. Estos no están garantizados para detectar concurrencia
         * violaciones, y a veces son excesivamente conservadores
         * interferencia dentro del hilo, pero detecta suficientes problemas 
         * para
         * valer la pena en la práctica. Para llevar esto a cabo, nosotros (1) 
         *perezosamente
         * initialize fence y expectedModCount hasta la última
         * señale que debemos comprometernos con el estado que estamos 
         *verificando
         * en contra; mejorando así la precisión. (Esto no se aplica a
         * SubLists, que crean spliterators con actual no perezoso
         * valores). (2) Realizamos solo un solo
         * Comprobación de ConcurrentModificationException al final de forEach
         * (el método más sensible al rendimiento). Cuando se usa paraCada
         * (a diferencia de los iteradores), normalmente solo podemos detectar
         * Interferencia después de las acciones, no antes. Promover
         * Las verificaciones de activación de CME se aplican a todos los demás 
         *posibles
         * violaciones de supuestos, por ejemplo, nulos o muy pequeños
         * elementData array dado su tamaño (), que solo podría tener
         * ocurrió debido a la interferencia. Esto permite que el bucle interno
         * de forEach para ejecutarse sin más controles, y
         * simplifica la resolución lambda. Si bien esto implica una
         * número de cheques, tenga en cuenta que en el caso común de
         * list.stream (). forEach (a), sin controles u otro cálculo
         * ocurre en cualquier lugar que no sea dentro de cada uno. El otro
         * los métodos menos utilizados a menudo no pueden aprovechar la mayor 
         * parte de
         * estas fusiones
         */
        private int expectedModCount;
        /*Se inicializó cuando el conjunto de 
        cercado.*/
        private int fence; //-1 hasta que se use; luego un último índice pasado.
        private int index; //Índice actual, modificado en avance / división.
        private final ListaArreglo<E> list;

        //Constructores de la clase interna ListaArregloSpliterator.
        ListaArregloSpliterator() {
            this.expectedModCount = 0;
            this.fence = 0;
            this.index = 0;
            this.list = null; //OK si es nulo a menos que se cruce.
        }

        /**
         * Crear un nuevo spliterator que cubra el rango dado.          
         */
        ListaArregloSpliterator(ListaArreglo<E> list, int origin, int fence,
                int expectedModCount) {
            this.expectedModCount = expectedModCount;
            this.fence = fence;
            this.index = origin;
            this.list = list; //OK si es nulo a menos que se cruce.
        }

        //Métodos de la clase interna ListaArregloSpliterator.
        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED
                    | Spliterator.SUBSIZED;
        }

        @Override
        public long estimateSize() {
            return (long) (getFence() - this.index);
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            int i, hi, mc; //Acceso al polipasto y cheques desde el bucle.
            ListaArreglo<E> lista;
            Object[] arreglo;
            if (action == null) {
                throw new NullPointerException();
            }
            if ((lista = this.list) != null && (arreglo
                    = lista.listadoDatosElemento) != null) {
                if ((hi = this.fence) < 0) {
                    mc = lista.conteoModulo;
                    hi = lista.tamanio;
                } else {
                    mc = this.expectedModCount;
                }
                if ((i = this.index) >= 0 && (index = hi) <= arreglo.length) {
                    for (; i < hi; ++i) {
                        @SuppressWarnings("unchecked")
                        E e = (E) arreglo[i];
                        action.accept(e);
                    }
                    if (lista.conteoModulo == mc) {
                        return;
                    }
                }
            }
            throw new ConcurrentModificationException();
        }

        private int getFence() { //Inicializar cerca a medida en el primer uso.
            int hi;
            /*(Aparece una variante especializada en el método para cada 
            uno).*/
            ListaArreglo<E> lista;
            if ((hi = fence) < 0) {
                if ((lista = this.list) == null) {
                    hi = this.fence = 0;
                } else {
                    this.expectedModCount = lista.conteoModulo;
                    hi = this.fence = lista.tamanio;
                }
            }
            return hi;
        }

        @Override
        public ListaArregloSpliterator<E> trySplit() {
            int hi = getFence(), lo = this.index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null
                    : /*Divide el rango por la mitad a menos que sea demasiado 
                    pequeño.*/ new ListaArregloSpliterator<E>(
                            this.list, lo, this.index = mid,
                            this.expectedModCount);
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action == null) {
                throw new NullPointerException();
            }
            int hi = getFence(), i = this.index;
            if (i < hi) {
                this.index = i + 1;
                @SuppressWarnings("unchecked")
                E e = (E) this.list.listadoDatosElemento[i];
                action.accept(e);
                if (this.list.conteoModulo != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return true;
            }
            return false;
        }
    }

    //Clase interna SubLista.
    private class SubLista extends ListaAbstracta<E> implements RandomAccess {

        //Atributos de la clase interna SubLista.
        private final ListaAbstracta<E> padre;
        private final int compensacionDePadres;
        private final int compensacion;
        int tamanio;

        //Constructores de la clase interna SubLista.
        SubLista() {
            this.padre = null;
            this.compensacionDePadres = 0;
            this.compensacion = 0;
            this.tamanio = 0;
            this.conteoModulo = 0;
        }

        SubLista(ListaAbstracta<E> padre, int compensacion, int desdeIndice,
                int hastaIndice) {
            this.padre = padre;
            this.compensacionDePadres = desdeIndice;
            this.compensacion = compensacion + desdeIndice;
            this.tamanio = hastaIndice - desdeIndice;
            this.conteoModulo = ListaArreglo.this.conteoModulo;
        }

        //Métodos de la clase interna SubLista.
        @Override
        public void agregar(int indice, E elemento) {
            this.verificarRangoParaAgregar(indice);
            this.verificarParaMercantilizacion();
            this.padre.agregar(this.compensacionDePadres + indice, elemento);
            this.conteoModulo = this.padre.conteoModulo;
            this.tamanio++;
        }

        @Override
        public boolean agregarTodo(Coleccion<? extends E> coleccion) {
            return this.agregarTodo(this.tamanio, coleccion);
        }

        @Override
        public boolean agregarTodo(int indice, Coleccion<? extends E> coleccion) {
            this.verificarRangoParaAgregar(indice);
            int tamanioColeccion = coleccion.tamanio();
            if (tamanioColeccion == 0) {
                return false;
            }

            this.verificarParaMercantilizacion();
            this.padre.agregarTodo(this.compensacionDePadres
                    + indice, coleccion);
            this.conteoModulo = this.padre.conteoModulo;
            this.tamanio += tamanioColeccion;
            return true;
        }

        @Override
        public E establecer(int indice, E elemento) {
            this.verificarRango(indice);
            this.verificarParaMercantilizacion();
            E valorAntiguo = ListaArreglo.this.listadoDatosElemento(
                    this.compensacion + indice);
            ListaArreglo.this.listadoDatosElemento[this.compensacion + indice]
                    = elemento;
            return valorAntiguo;
        }

        @Override
        public String imprimir() {
            return new ListaArreglo<>().imprimir();
        }

        @Override
        public Iterator<E> iterator() {
            return this.listIterator();
        }

        @Override
        public ListIterator<E> listIterator(final int indice) {
            this.verificarParaMercantilizacion();
            this.verificarRangoParaAgregar(indice);
            final int offset = this.compensacion;

            return new ListIterator<E>() {/*Inicio de la clase anónima interna 
                ListIterator de la clase interna SubLista.*/

 /*Atributos de la clase anónima interna ListIterator de la clase
                interna SubLista.*/
                int cursor = indice;
                int lastRet = -1;
                int expectedModCount = ListaArreglo.this.conteoModulo;

                /*Métodos de la clase anónima interna ListIterator de la clase
                interna SubLista.*/
                @Override
                public void add(E e) {
                    this.checkForComodification();

                    try {
                        int i = this.cursor;
                        ListaArreglo.SubLista.this.agregar(i, e);
                        this.cursor = i + 1;
                        this.lastRet = -1;
                        this.expectedModCount = ListaArreglo.this.conteoModulo;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                final void checkForComodification() {
                    if (this.expectedModCount != ListaArreglo.this.conteoModulo) {
                        throw new ConcurrentModificationException();
                    }
                }

                @SuppressWarnings("unchecked")
                @Override
                public void forEachRemaining(Consumer<? super E> consumer) {
                    Objects.requireNonNull(consumer);
                    final int size = ListaArreglo.SubLista.this.tamanio;
                    int i = this.cursor;
                    if (i >= size) {
                        return;
                    }
                    final Object[] elementData
                            = ListaArreglo.this.listadoDatosElemento;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    while (i != size && conteoModulo == this.expectedModCount) {
                        consumer.accept((E) elementData[offset + (i++)]);
                    }
                    /*Actualizar una vez al final de la iteración para reducir 
                    el tráfico de escritura de montón.*/
                    this.lastRet = this.cursor = i;
                    checkForComodification();
                }

                @Override
                public boolean hasNext() {
                    return this.cursor != ListaArreglo.SubLista.this.tamanio;
                }

                @Override
                public boolean hasPrevious() {
                    return this.cursor != 0;
                }

                @SuppressWarnings("unchecked")
                @Override
                public E next() {
                    checkForComodification();
                    int i = this.cursor;
                    if (i >= ListaArreglo.SubLista.this.tamanio) {
                        throw new NoSuchElementException();
                    }
                    Object[] elementData
                            = ListaArreglo.this.listadoDatosElemento;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    this.cursor = i + 1;
                    return (E) elementData[offset + (this.lastRet = i)];
                }

                @Override
                public int nextIndex() {
                    return this.cursor;
                }

                @SuppressWarnings("unchecked")
                @Override
                public E previous() {
                    this.checkForComodification();
                    int i = this.cursor - 1;
                    if (i < 0) {
                        throw new NoSuchElementException();
                    }
                    Object[] elementData
                            = ListaArreglo.this.listadoDatosElemento;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    this.cursor = i;
                    return (E) elementData[offset + (this.lastRet = i)];
                }

                @Override
                public int previousIndex() {
                    return this.cursor - 1;
                }

                @Override
                public void remove() {
                    if (this.lastRet < 0) {
                        throw new IllegalStateException();
                    }
                    this.checkForComodification();

                    try {
                        ListaArreglo.SubLista.this.remover(this.lastRet);
                        this.cursor = this.lastRet;
                        this.lastRet = -1;
                        this.expectedModCount = ListaArreglo.this.conteoModulo;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override
                public void set(E e) {
                    if (this.lastRet < 0) {
                        throw new IllegalStateException();
                    }
                    this.checkForComodification();

                    try {
                        ListaArreglo.this.establecer(offset + this.lastRet, e);
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }
            };/*Fin de la clase anónima interna ListIterator de la clase interna 
            SubLista.*/
        }

        private String mostrarMensajeFueraDeLosLimites(int indice) {
            return "Índice: " + indice + ", Tamaño: " + this.tamanio;
        }

        @Override
        public E obtener(int indice) {
            this.verificarRango(indice);
            this.verificarParaMercantilizacion();
            return ListaArreglo.this.listadoDatosElemento(this.compensacion
                    + indice);
        }

        @Override
        public E remover(int indice) {
            this.verificarRango(indice);
            this.verificarParaMercantilizacion();
            E resultado = this.padre.remover(this.compensacionDePadres
                    + indice);
            this.conteoModulo = this.padre.conteoModulo;
            this.tamanio--;
            return resultado;
        }

        @Override
        protected void removerRango(int desdeIndice, int hastaIndice) {
            this.verificarParaMercantilizacion();
            this.padre.removerRango(this.compensacionDePadres + desdeIndice,
                    this.compensacionDePadres + hastaIndice);
            this.conteoModulo = this.padre.conteoModulo;
            this.tamanio -= hastaIndice - desdeIndice;
        }

        @Override
        public Spliterator<E> spliterator() {
            this.verificarParaMercantilizacion();
            return new ListaArregloSpliterator<E>(ListaArreglo.this,
                    this.compensacion, this.compensacion + this.tamanio,
                    this.conteoModulo);
        }

        @Override
        public Lista<E> subLista(int desdeIndice, int hastaIndice) {
            verificarRangoSubLista(desdeIndice, hastaIndice, this.tamanio);
            return new ListaArreglo.SubLista(this, this.compensacion,
                    desdeIndice, hastaIndice);
        }

        @Override
        public int tamanio() {
            this.verificarParaMercantilizacion();
            return this.tamanio;
        }

        private void verificarParaMercantilizacion() {
            if (ListaArreglo.this.conteoModulo != this.conteoModulo) {
                throw new ConcurrentModificationException();
            }
        }

        private void verificarRango(int indice) {
            if (indice < 0 || indice >= this.tamanio) {
                throw new IndexOutOfBoundsException(
                        this.mostrarMensajeFueraDeLosLimites(indice));
            }
        }

        private void verificarRangoParaAgregar(int indice) {
            if (indice < 0 || indice > this.tamanio) {
                throw new IndexOutOfBoundsException(
                        this.mostrarMensajeFueraDeLosLimites(indice));
            }
        }
    }
}
