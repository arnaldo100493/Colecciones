/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Clase ListaVector para guardar y manipular elementos en una lista de vector.
 */
/**
 * La clase {@code ListaVector} implementa un arreglo creíble de objetos. Al
 * igual que un rreglo, contiene componentes que pueden ser se accede usando un
 * índice entero. Sin embargo, el tamaño de un {@code ListaVector} puede crecer
 * o reducirse según sea necesario para acomodar agregar y eliminar elementos
 * después de que se haya creado {@code ListaVector}.  
 * <p>
 * Cada vector intenta optimizar la gestión del almacenamiento manteniendo un
 * {@code capacidad} y {@code incrementoCapacidad}. los {@code capacidad}
 * siempre es al menos tan grande como el vector tamaño; generalmente es más
 * grande porque a medida que se agregan los componentes al vector, el
 * almacenamiento del vector aumenta en trozos del tamaño de
 * {@code aumentoCapacidad}. Una aplicación puede aumentar capacidad de un
 * vector antes de insertar un gran número de componentes; esto reduce la
 * cantidad de reasignación incremental.  
 * <p>
 * <a name="fail-fast">
 * Los iteradores devueltos por {@link #iterator() iterator} de esta clase y
 * {@link #listIterator(int) listIterator} los métodos son
 * <em>rápidos:</em></a>: si el vector está estructuralmente modificado en
 * cualquier momento después de que el iterador esté creado, de cualquier manera
 * excepto a través del propio iterador los métodos,
 * {@link ListIterator #remover() remover} o
 * {@link ListIterator #agregar (Object) agregar}, el iterador lanzará un
 * {@link ConcurrentModificationException}. Por lo tanto, frente a modificación
 * simultánea, el iterador falla de forma rápida y limpia, en lugar de que
 * arriesgar el comportamiento arbitrario, no determinista en un indeterminado
 * tiempo en el futuro. Las {@link Enumeration Enumerations} devueltas por el
 * método {@link #elementos() elementos} no es <em>no</em>
 * rápido.  
 * <p>
 * Tenga en cuenta que no se puede garantizar el comportamiento a prueba de
 * errores de un iterador como lo es, en términos generales, es imposible hacer
 * ninguna garantía dura en el presencia de modificación concurrente no
 * sincronizada. Iteradores a prueba de fallas throw
 * {@code ConcurrentModificationException} sobre una base de mejor esfuerzo. Por
 * lo tanto, sería incorrecto escribir un programa que dependiera de esto
 * excepción por su corrección:<i>el comportamiento a prueba de fallas de los
 * iteradores debe usarse solo para detectar errores.</i>
 *
 *  
 * <p>
 * A partir de la plataforma Java 2 v1.2, esta clase se adaptó a implementar la
 * interfaz {@link Lista}, convirtiéndolo en miembro del  
 *
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework </a>. A diferencia de la nueva colección
 * implementaciones, {@code Vector} está sincronizado. Si es seguro para
 * subprocesos la implementación no es necesaria, se recomienda usar
 * {@link ListaArreglo} en lugar de {@code ListaVector}.
 *
 * @author FABAME
 * @see Coleccion
 * @see ListaEnlazada
 * @since JDK 1.0
 * @param <E> el tipo de elementos contenidos en esta colección 
 */
public class ListaVector<E> extends ListaAbstracta<E>
        implements Lista<E>, RandomAccess, Cloneable, Serializable {

    //Atributos de la clase ListaVector.
    /**
     * El tamaño máximo de la matriz para asignar. Algunas máquinas virtuales
     * reservan algunas palabras de encabezado en una matriz. Los intentos de
     * asignar arreglos más grandes pueden resultar en  OutOfMemoryError: el
     * tamaño de la matriz solicitada excede el límite de VM      
     */
    private static final int TAMANIO_MAXIMO_ARREGLO = Integer.MAX_VALUE - 8;

    /**
     * La cantidad de componentes válidos en este objeto {@code ListaVector}.
     * Componentes {@code listadoDatosElemento[0]} a través de
     * {@code listadoDatosElemento [elementCount-1]} son los elementos reales.
     *
     * @serial      
     */
    protected int conteoElemento;

    /**
     * La cantidad por la cual la capacidad del vector es automáticamente
     * incrementado cuando su tamaño se vuelve mayor que su capacidad. Si el
     * incremento de capacidad es menor o igual a cero, la capacidad del vector
     * se duplica cada vez que necesita crecer.
     *
     * @serial      
     */
    protected int incrementoCapacidad;

    /**
     * El búfer de arreglo en el que se encuentran los componentes del vector
     * almacenado. La capacidad del vector es la longitud de este búfer de
     * matriz, y es al menos lo suficientemente grande como para contener todos
     * los elementos del vector.    
     * <p>
     * Cualquier elemento de arreglo que siga al último elemento del Vector es
     * nulo.
     *
     * @serial      
     */
    protected Object[] listadoDatosElemento;

    /**
     * Usa serialVersionUID de JDK 1.0.2 para interoperabilidad.
     */
    private static final long serialVersionUID = -2767605614048989439L;

    //Constructores de la clase ListaVector.
    /**
     * Construye un vector vacío para que su arreglo de datos internos tiene el
     * tamaño {@code 10} y su incremento de capacidad estándar es cero.      
     */
    public ListaVector() {
        this(10);
    }

    /**
     * Construye un vector que contiene los elementos de la especificada
     * colección, en el orden en que son devueltos por la colección iterador.
     *
     * @param coleccion la colección cuyos elementos deben colocarse en este
     * vector
     * @throws NullPointerException si la colección especificada es nula
     * @since 1.2      
     */
    public ListaVector(Coleccion<? extends E> coleccion) {
        this.listadoDatosElemento = coleccion.paraFormar();
        this.conteoElemento = this.listadoDatosElemento.length;
        //Coleccion.paraFormar podría (incorrectamente) no devolver Object[] (ver 6260652)
        if (this.listadoDatosElemento.getClass() != Object[].class) {
            this.listadoDatosElemento = Arrays.copyOf(this.listadoDatosElemento, this.conteoElemento, Object[].class);
        }
    }

    /**
     * Construye un vector vacío con la capacidad inicial especificada y con su
     * incremento de capacidad igual a cero.
     *
     * @param capacidadInicial la capacidad inicial del vector
     * @throws IllegalArgumentException si la capacidad inicial especificada es
     * negativo      
     */
    public ListaVector(int capacidadInicial) {
        this(capacidadInicial, 0);
    }

    /**
     * Construye un vector vacío con la capacidad inicial especificada y
     * incremento de capacidad.
     *
     * @param capacidadInicial la capacidad inicial del vector
     * @param incrementoCapacidad la cantidad por la cual la capacidad es
     * aumentado cuando el vector se desborda
     * @throws IllegalArgumentException si la capacidad inicial especificada es
     * negativo      
     */
    public ListaVector(int capacidadInicial, int incrementoCapacidad) {
        super();
        if (capacidadInicial < 0) {
            throw new IllegalArgumentException("Capacidad Ilegal: "
                    + capacidadInicial);
        }
        this.listadoDatosElemento = new Object[capacidadInicial];
        this.incrementoCapacidad = incrementoCapacidad;
    }

    //Métodos de la clase ListaVector.
    /**
     * Añade el elemento especificado al final de este Vector.
     *
     * @param elemento elemento para adjuntar a esta ListaVector
     * @return {@code true} (según lo especificado por
     * {@link Coleccion #agregar})
     * @since 1.2      
     */
    @Override
    public synchronized boolean agregar(E elemento) {
        this.conteoModulo++;
        this.ayudanteAsegurarCapacidad(this.conteoElemento + 1);
        this.listadoDatosElemento[this.conteoElemento++] = elemento;
        return true;
    }

    /**
     * Inserta el elemento especificado en la posición especificada en esta
     * ListaVector. Cambia el elemento actualmente en esa posición (si
     * corresponde) y cualquier elementos posteriores a la derecha (agrega uno a
     * sus índices).
     *
     * @param indice índice en el que se debe insertar el elemento especificado
     * @param elemento elemento a insertar
     * @throws ArrayIndexOutOfBoundsException si el índice está fuera de rango
     * ({@code indice < 0 || indice > tamanio()})
     * @since 1.2      
     */
    @Override
    public void agregar(int indice, E elemento) {
        this.insertarElementoEn(elemento, indice);
    }

    /**
     * Agrega el componente especificado al final de este vector,      
     * aumentando su tamaño en uno. La capacidad de este vector es aumentado si
     * su tamaño es mayor que su capacidad.    
     * <p>
     * Este método es idéntico en funcionalidad al método
     * {@link #agregar(Object) agregar(E)} (que es parte de la interfaz
     * {@link Lista}).
     *
     * @param elemento el componente que se agregará      
     */
    public synchronized void agregarElemento(E elemento) {
        this.conteoModulo++;
        this.ayudanteAsegurarCapacidad(this.conteoElemento + 1);
        this.listadoDatosElemento[this.conteoElemento++] = elemento;
    }

    /**
     * Inserta todos los elementos en la colección especificada en esta
     * ListaVector en la posición especificada. Cambia el elemento actualmente
     * en esa posición (si la hay) y cualquier elemento posterior a la derecha
     * (aumenta sus índices). Los nuevos elementos aparecerán en la ListaVector
     * en el orden en que son devueltos por la Colección especificada iterador.
     * índice de índice
     *
     * @param indice índice en el que se inserta el primer elemento del
     * colección especificada
     * @param coleccion elementos para ser insertados en esta ListaVector
     * @return {@code true} si esta ListaVector cambió como resultado de la
     * llamada
     * @throws ArrayIndexOutOfBoundsException si el índice está fuera de rango
     * ({@code index <0 || index> size ()})
     * @throws NullPointerException si la colección especificada es nula
     * @since 1.2      
     */
    @Override
    public synchronized boolean agregarTodo(int indice, Coleccion<? extends E> coleccion) {
        this.conteoModulo++;
        if (indice < 0 || indice > this.conteoElemento) {
            throw new ArrayIndexOutOfBoundsException(indice);
        }

        Object[] arreglo = coleccion.paraFormar();
        int numeroNuevo = arreglo.length;
        this.ayudanteAsegurarCapacidad(this.conteoElemento + numeroNuevo);

        int numeroMovido = this.conteoElemento - indice;
        if (numeroMovido > 0) {
            System.arraycopy(this.listadoDatosElemento,
                    indice,
                    this.listadoDatosElemento,
                    indice + numeroNuevo,
                    numeroMovido);
        }

        System.arraycopy(arreglo,
                0,
                this.listadoDatosElemento,
                indice,
                numeroNuevo);
        this.conteoElemento += numeroNuevo;
        return numeroNuevo != 0;
    }

    /**
     * Agrega todos los elementos en la Colección especificada hasta el final de
     * esta ListaVector, en el orden en que son devueltos por el Iterador de la
     * colección. El comportamiento de esta operación no está definido si la
     * Colección especificada se modifica mientras la operación está en
     * progreso. (Esto implica que el comportamiento de esta llamada no está
     * definido si Colección especificada es esta ListaVector, y esta
     * ListaVector es no vacío.)
     *
     * @param coleccion elementos para ser insertados en este Vector
     * @return {@code true} si este Vector cambió como resultado de la llamada
     * @throws NullPointerException si la colección especificada es nula
     * @since 1.2      
     */
    @Override
    public synchronized boolean agregarTodo(Coleccion<? extends E> coleccion) {
        this.conteoModulo++;
        Object[] arreglo = coleccion.paraFormar();
        int numeroNuevo = arreglo.length;
        this.ayudanteAsegurarCapacidad(this.conteoElemento + numeroNuevo);
        System.arraycopy(arreglo,
                0,
                this.listadoDatosElemento,
                this.conteoElemento,
                numeroNuevo);
        this.conteoElemento += numeroNuevo;
        return numeroNuevo != 0;
    }

    /**
     * Aumenta la capacidad de este vector, si es necesario, para garantizar que
     * puede contener al menos la cantidad de componentes especificados por el
     * argumento de capacidad mínima.      
     * <p>
     * Si la capacidad actual de este vector es menor que
     * {@code capacidadMinima}, luego se aumenta su capacidad reemplazando su
     * arreglo de datos interno, guardada en el campo
     * {@code listadoDatosElemento}, con un más grande. El tamaño del nuevo
     * arreglo de datos será el tamaño antiguo más {@code incrementoCapacidad},
     * a menos que el valor de {@code incrementoCapacidad} es menor o igual que
     * cero, en cuyo caso la nueva capacidad será el doble de la capacidad
     * anterior; pero si este nuevo tamaño es aún más pequeño que
     * {@code capacidadMinima}, entonces la nueva capacidad sea
     * {@code capacidadMinima}.
     *
     * @param capacidadMinima la capacidad mínima deseada      
     */
    public synchronized void asegurarCapacidad(int capacidadMinima) {
        if (capacidadMinima > 0) {
            this.conteoModulo++;
            this.ayudanteAsegurarCapacidad(capacidadMinima);
        }
    }

    /**
     * Esto implementa la semántica no sincronizada de asegurarCapacidad. Los
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

    /**
     * Devuelve la capacidad actual de este vector.
     *
     * @return la capacidad actual (la longitud de su interno arreglo de datos,
     * guardada en el campo {@code listadoDatosElemento} de este vector)      
     */
    public synchronized int capacidad() {
        return this.listadoDatosElemento.length;
    }

    /**
     * Devuelve un clon de este vector. La copia contendrá un referencia a un
     * clon de la matriz de datos internos, no una referencia a el arreglo de
     * datos internos original de este objeto {@code Vector}.
     *
     * @return un clon de este vector      
     */
    @Override
    public synchronized Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ListaVector<E> listaVector = (ListaVector<E>) super.clone();
            listaVector.listadoDatosElemento
                    = Arrays.copyOf(this.listadoDatosElemento,
                            this.conteoElemento);
            listaVector.conteoModulo = 0;
            return listaVector;
        } catch (CloneNotSupportedException ex) {
            //Esto no debería suceder, ya que somos clonables.
            throw new InternalError(ex);
        }
    }

    /**
     * Retiene solo los elementos en este Vector que están contenidos en
     * Colección especificada. En otras palabras, elimina de este Vector todo de
     * sus elementos que no están contenidos en la Colección especificada.
     *
     * @param coleccion una colección de elementos que se conservarán en este
     * Vector (todos los demás elementos se eliminan)
     * @return true si este Vector cambió como resultado de la llamada
     * @throws ClassCastException si los tipos de uno o más elementos en este
     * vector son incompatibles con el especificado colección
     * (<a href="Collection.html#optional-restrictions">opcional</a>)
     * @throws NullPointerException si este vector contiene uno o más    
     * elementos nulos y la colección especificada no admite elementos nulos
     * (<a href="Collection.html#optional-restrictions">opcional</a>), o si la
     * colección especificada es nula
     * @since 1.2      
     */
    @Override
    public synchronized boolean conservarTodo(Coleccion<?> coleccion) {
        return super.conservarTodo(coleccion);
    }

    /**
     * Devuelve {@code true} si este vector contiene el elemento especificado.
     * Más formalmente, devuelve {@code true} si y solo si este vector contiene
     * al menos un elemento {@code elemento} tal que      
     * <tt>(objeto == null & nbsp;? & nbsp; elemento == null & nbsp;: & nbsp;
     * objeto.equals (elemento))</tt>.
     *
     * @param objeto elemento cuya presencia en este vector se va a probar
     * @return {@code true} si este vector contiene el elemento especificado
     *      
     */
    @Override
    public boolean contiene(Object objeto) {
        return this.indiceDe(objeto, 0) >= 0;
    }

    /**
     * Devuelve verdadero si esta ListaVector contiene todos los elementos en
     * Colección especificada.
     *
     * @param coleccion una colección cuyos elementos se probarán para su
     * contención en esta ListaVector
     * @return true si esta ListaVector contiene todos los elementos en la
     * colección especificada
     * @throws NullPointerException si la colección especificada es nula      
     */
    @Override
    public synchronized boolean contieneTodo(Coleccion<?> coleccion) {
        return super.contieneTodo(coleccion);
    }

    /**
     * Copia los componentes de este vector en el arreglo especificadao. El
     * elemento en el índice {@code k} en este vector se copia en componente
     * {@code k} de {@code unArreglo}.
     *
     * @param unArreglo el arreglo en la que se copian los componentes
     * @throws NullPointerException si el arreglo dado es nulo
     * @throws IndexOutOfBoundsException si el arreglo especificada no es lo
     * suficientemente grande como para contener todos los componentes de este
     * vector
     * @throws ArrayStoreException si un componente de este vector no es de un
     * tipo de tiempo de ejecución que se puede almacenar en el arreglo
     * especificado
     * @see #paraFormar(Object[])      
     */
    public synchronized void copiarEn(Object[] unArreglo) {
        System.arraycopy(this.listadoDatosElemento, 0, unArreglo, 0, this.conteoElemento);
    }

    private void crecer(int capacidadMinima) {
        //Código consciente de desbordamiento.
        int capacidadAntigua = this.listadoDatosElemento.length;
        int nuevaCapacidad = capacidadAntigua + ((this.incrementoCapacidad > 0)
                ? this.incrementoCapacidad : capacidadAntigua);
        if (nuevaCapacidad - capacidadMinima < 0) {
            nuevaCapacidad = capacidadMinima;
        }
        if (nuevaCapacidad - TAMANIO_MAXIMO_ARREGLO > 0) {
            nuevaCapacidad = granCapacidad(capacidadMinima);
        }
        this.listadoDatosElemento = Arrays.copyOf(this.listadoDatosElemento, nuevaCapacidad);
    }

    /**
     * Guarda el estado de la instancia de {@code Vector} en una secuencia (que
     * es, serializarlo). Este método realiza la sincronización para garantizar
     * la coherencia de los datos serializados.      
     */
    private void escribirObjeto(ObjectOutputStream salida) throws IOException {
        final java.io.ObjectOutputStream.PutField fields = salida.putFields();
        final Object[] listadoDatos;
        synchronized (this) {
            fields.put("incrementoCapacidad", this.incrementoCapacidad);
            fields.put("conteoElemento", this.conteoElemento);
            listadoDatos = this.listadoDatosElemento.clone();
        }
        fields.put("listadoDatos", listadoDatos);
        salida.writeFields();
    }

    /**
     * Devuelve el componente en el índice especificado.   
     * <p>
     * Este método es idéntico en funcionalidad al {@link #obtener(int)} método
     * (que es parte de la interfaz {@link Lista}).
     *
     * @param indice un índice en este vector
     * @return el componente en el índice especificado
     * @throws ArrayIndexOutOfBoundsException si el índice está fuera de rango
     * ({@code indice < 0 || indice > = tamanio()})      
     */
    public synchronized E elementoEn(int indice) {
        if (indice >= this.conteoElemento) {
            throw new ArrayIndexOutOfBoundsException(indice + " >= " + this.conteoElemento);
        }

        return this.listadoDatosElemento(indice);
    }

    /**
     * Devuelve una enumeración de los componentes de este vector. los el objeto
     * devuelto {
     *
     * @Enumeración}} generará todos los elementos en este vector El primer
     * elemento generado es el elemento en el índice {@code 0}, luego el
     * elemento en el índice {@code 1}, y así sucesivamente.
     * @return una enumeración de los componentes de este vector
     * @see Iterator      
     */
    public Enumeration<E> elementos() {
        return new Enumeration<E>() {//Inicio de la clase anónima Enumeration.
            //Atributo de la clase anónima Enumeration.
            int count = 0;

            //Métodos de la clase anónima Enumeration.
            @Override
            public boolean hasMoreElements() {
                return this.count < conteoElemento;
            }

            @Override
            public E nextElement() {
                synchronized (ListaVector.this) {
                    if (this.count < conteoElemento) {
                        return listadoDatosElemento(this.count++);
                    }
                }
                throw new NoSuchElementException("Enumeración del Vector");
            }
        };//Fin de la clase anónima Enumeration.
    }

    /**
     * Compara el objeto especificado con este vector para la igualdad.
     * Devoluciones verdadero si y solo si el Objeto especificado también es una
     * Lista, ambas Listas tienen el mismo tamaño, y todos los pares
     * correspondientes de elementos en los dos Las listas son <em>iguales</em>.
     * (Dos elementos {@code e1} y {@code e2} son <em>iguales</em> si {@code (e1 == null? e2 == null:
     * e1.equals (e2))}.) En otras palabras, dos listas se definen para ser
     * igual si contienen los mismos elementos en el mismo orden.  @param o el
     * Objeto que se debe comparar para la igualdad con esta ListaVector
     *
     * @return true si el objeto especificado es igual a este Vector      
     */
    @Override
    public synchronized boolean equals(Object object) {
        return super.equals(object);
    }

    /**
     * Indica si el argumento es el índice de un elemento existente.      
     */
    private boolean esIndiceElemento(int indice) {
        return indice >= 0 && indice < this.conteoElemento;
    }

    /**
     * Indica si el argumento es el índice de una posición válida para un
     * iterador o una operación de agregar.      
     */
    private boolean esIndicePosicion(int indice) {
        return indice >= 0 && indice <= this.conteoElemento;
    }

    /**
     * Prueba si este vector no tiene componentes.
     *
     * @return {@code true} si y solo si este vector tiene sin componentes, es
     * decir, su tamaño es cero;{@code false} de lo contrario.      
     */
    @Override
    public synchronized boolean estaVacia() {
        return this.conteoElemento == 0;
    }

    /**
     * Reemplaza el elemento en la posición especificada en este Vector con el
     * elemento especificado.
     *
     * @param indice índice del elemento a reemplazar
     * @param elemento elemento para ser almacenado en la posición especificada
     * @return el elemento previamente en la posición especificada
     * @throws ArrayIndexOutOfBoundsException si el índice está fuera de rango
     * ({@code indice < 0 || indice >= tamanio()})
     * @sincce 1.2      
     */
    @Override
    public synchronized E establecer(int indice, E elemento) {
        if (indice >= this.conteoElemento) {
            throw new ArrayIndexOutOfBoundsException(indice);
        }

        E valorAntiguo = this.listadoDatosElemento(indice);
        this.listadoDatosElemento[indice] = elemento;
        return valorAntiguo;
    }

    /**
     * Establece el componente en el {@code indice} especificado de este vector
     * para ser el objeto especificado. El componente anterior en ese la
     * posición se descarta.   
     * <p>
     * El índice debe ser un valor mayor o igual que {@code 0} y menor que el
     * tamaño actual del vector.    
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
    public synchronized void establecerElementoEn(E objeto, int indice) {
        if (indice >= this.conteoElemento) {
            throw new ArrayIndexOutOfBoundsException(indice + " >= "
                    + conteoElemento);
        }
        this.listadoDatosElemento[indice] = objeto;
    }

    /**
     * Establece el tamaño de este vector. Si el nuevo tamaño es mayor que el
     * tamaño actual, se agregan nuevos elementos {@code null} al final de el
     * vector. Si el nuevo tamaño es menor que el tamaño actual, todo los
     * componentes en el índice {@code nuevoTamanio} y mayor se descartan.
     *
     * @param nuevoTamanio el nuevo tamaño de este vector
     * @throws ArrayIndexOutOfBoundsException si el nuevo tamaño es negativo
     *      
     */
    public synchronized void establecerTamanio(int nuevoTamanio) {
        this.conteoModulo++;
        if (nuevoTamanio > this.conteoElemento) {
            this.ayudanteAsegurarCapacidad(nuevoTamanio);
        } else {
            for (int i = nuevoTamanio; i < this.conteoElemento; i++) {
                this.listadoDatosElemento[i] = null;
            }
        }
        this.conteoElemento = nuevoTamanio;
    }

    @Override
    public synchronized void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        final int expectedModCount = this.conteoModulo;
        @SuppressWarnings("unchecked")
        final E[] elementData = (E[]) this.listadoDatosElemento;
        final int elementCount = this.conteoElemento;
        for (int i = 0; this.conteoModulo == expectedModCount && i < elementCount; i++) {
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
     * Devuelve el valor del código hash para esta ListaVector.      
     */
    @Override
    public synchronized int hashCode() {
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized String imprimir() {
        String s = "";
        for (int i = 0; i < this.conteoElemento; i++) {
            s += this.obtener(i) + "\n";
        }
        return s;
    }

    /**
     * Devuelve el índice de la primera aparición del elemento especificado en
     * este vector, o -1 si este vector no contiene el elemento. Más
     * formalmente, devuelve el índice más bajo {@code i} tal que <tt>(objeto ==
     * null & nbsp;? & nbsp; obtener(i) == null & nbsp;: & nbsp; objeto.equals
     * (obtener(i))) </tt>, o -1 si no hay tal índice.  
     *
     * @param objeto elemento para buscar
     * @return el índice de la primera aparición del elemento especificado en
     * este vector, o -1 si este vector no contiene el elemento      
     */
    @Override
    public int indiceDe(Object objeto) {
        return this.indiceDe(objeto, 0);
    }

    /**
     * Devuelve el índice de la primera aparición del elemento especificado en
     * este vector, buscando hacia adelante desde {@code indice}, o devuelve -1
     * si el elemento no se encuentra. Más formalmente, devuelve el índice más
     * bajo {@code i} tal que
     * <tt>(i & nbsp; & gt; = & nbsp; indice & nbsp; & amp;; & nbsp; (objeto ==
     * null & nbsp;? & nbsp; obtener (i) == null & nbsp;: & nbsp; objeto.equals
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
    public synchronized int indiceDe(Object objeto, int indice) {
        if (objeto == null) {
            for (int i = indice; i < this.conteoElemento; i++) {
                if (this.listadoDatosElemento[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = indice; i < this.conteoElemento; i++) {
                if (objeto.equals(this.listadoDatosElemento[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Inserta el objeto especificado como un componente en este vector en el
     * especificado {@code indice}. Cada componente en este vector con un índice
     * mayor o igual al {@code indice} especificado es se desplazó hacia arriba
     * para tener un índice uno mayor que el valor que tenía previamente.      
     * <p>
     * El índice debe ser un valor mayor o igual que {@code 0} y menor o igual
     * que el tamaño actual del vector. (Si el índice es igual al tamaño actual
     * del vector, el nuevo elemento se adjunta a la ListaVector.)      
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
    public synchronized void insertarElementoEn(Object objeto, int indice) {
        this.conteoModulo++;
        if (indice > this.conteoElemento) {
            throw new ArrayIndexOutOfBoundsException(indice
                    + " > " + this.conteoElemento);
        }
        this.ayudanteAsegurarCapacidad(this.conteoElemento + 1);
        System.arraycopy(this.listadoDatosElemento,
                indice,
                this.listadoDatosElemento,
                indice + 1,
                this.conteoElemento - indice);
        this.listadoDatosElemento[indice] = objeto;
        this.conteoElemento++;
    }

    /**
     * Devuelve un iterador sobre los elementos en esta lista en la secuencia
     * correcta. 
     * <p>
     * El iterador devuelto es <a href="#fail-fast"><i>fail-fast</i></a>.      
     *
     * @return un iterador sobre los elementos en esta lista en la secuencia
     * correcta      
     */
    @Override
    public synchronized Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Elimina todos los elementos de esta ListaVector. La ListaVector lo hará
     * estar vacía después de que regrese esta llamada (a menos que arroje una
     * excepción).
     *
     * @since 1.2      
     */
    @Override
    public void limpiar() {
        this.removerTodosLosElementos();
    }

    /**
     * Devuelve un iterador de lista sobre los elementos en esta lista (en
     * secuencia).   
     * <p>
     * El iterador de la lista devuelta es <a href="#fail-fast">
     * <i>fail-fast</i></a>.
     *
     * @see #listIterator(int)      
     */
    @Override
    public synchronized ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    /**
     * Devuelve un iterador de lista sobre los elementos en esta lista (en
     * secuencia), comenzando en la posición especificada en la lista.El índice
     * especificado indica el primer elemento que sería devuelto por una llamada
     * inicial a {@link ListIterator #next next}. Una llamada inicial a
     * {@link ListIterator #previous previous} haría devuelve el elemento con el
     * índice especificado menos uno.    
     * <p>
     * El iterador de la lista devuelta es <a href="#fail-fast"> <i>fail-fast
     * </i></a>.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public synchronized ListIterator<E> listIterator(int indice) {
        if (indice < 0 || indice > this.conteoElemento) {
            throw new IndexOutOfBoundsException("Index: " + indice);
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
        return "Índice: " + indice + ", Tamaño: " + this.conteoElemento;
    }

    /**
     * Devuelve el elemento en la posición especificada en este Vector.
     *
     * @param indice índice del elemento a devolver
     *
     * @return el objeto en el índice especificado
     * @throws ArrayIndexOutOfBoundsException si el índice está fuera de rango
     * ({@code indice < 0 || indice >= tamanio()})
     * @since 1.2      
     */
    @Override
    public synchronized E obtener(int indice) {
        if (indice >= this.conteoElemento) {
            throw new ArrayIndexOutOfBoundsException(indice);
        }

        return this.listadoDatosElemento(indice);
    }

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void ordenar(Comparator<? super E> comparador) {
        final int conteoModuloEsperado = this.conteoModulo;
        Arrays.sort((E[]) this.listadoDatosElemento, 0, this.conteoElemento, comparador);
        if (this.conteoModulo != conteoModuloEsperado) {
            throw new ConcurrentModificationException();
        }
        this.conteoModulo++;
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos de esta ListaVector
     * en el orden correcto.
     *
     * @since 1.2      
     */
    @Override
    public synchronized Object[] paraFormar() {
        return Arrays.copyOf(this.listadoDatosElemento, this.conteoElemento);
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos de este ListaVector
     * en el orden correcto; el tipo de tiempo de ejecución de la arreglo
     * devuelto es la del arreglo especificado. Si el Vector encaja en la matriz
     * especificada, es devuelto allí. De lo contrario, se asigna un nuevo
     * arreglo con el tiempo de ejecución tipo del arreglo especificado y el
     * tamaño de esta ListaVector.      
     * <p>
     * Si la ListaVector encaja en el arreglo especificado con espacio libre (es
     * decir, eel arreglo tiene más elementos que la ListaVector), el elemento
     * en el conjunto inmediatamente posterior al final del el vector está
     * configurado como nulo. (Esto es útil para determinar la longitud de la
     * ListaVector <em>solamente</em> si la persona que llama sabe que la
     * ListaVector no contiene ningún elemento nulo.)  @param arreglo el arreglo
     * en la que se encuentran los elementos de la ListaVector ser almacenado,
     * si es lo suficientemente grande; de lo contrario, un nuevo arreglo de el
     * mismo tipo de tiempo de ejecución se asigna para este propósito.
     *
     * @return un arreglo que contiene los elementos de la ListaVector
     * @throws ArrayStoreException si el tipo de tiempo de ejecución de a no es
     * un supertipo del tipo de tiempo de ejecución de cada elemento en este
     * Vector
     * @throws NullPointerException si el arreglo dado es nulo
     * @since 1.2
     */
    @SuppressWarnings("unchecked")
    @Override
    public synchronized <T> T[] paraFormar(T[] arreglo) {
        if (arreglo.length < this.conteoElemento) {
            return (T[]) Arrays.copyOf(this.listadoDatosElemento,
                    this.conteoElemento,
                    arreglo.getClass());
        }

        System.arraycopy(this.listadoDatosElemento,
                0,
                arreglo,
                0,
                this.conteoElemento);

        if (arreglo.length > this.conteoElemento) {
            arreglo[this.conteoElemento] = null;
        }

        return arreglo;
    }

    /**
     * Devuelve el primer componente (el elemento en el índice{@code 0}) de este
     * vector
     *
     * @return el primer componente de este vector
     * @throws NoSuchElementException si este vector no tiene componentes      
     */
    public synchronized E primerElemento() {
        if (this.conteoElemento == 0) {
            throw new NoSuchElementException();
        }
        return this.listadoDatosElemento(0);
    }

    /**
     * Recorta la capacidad de este vector para ser el vector actual tamaño. Si
     * la capacidad de este vector es mayor que su actual tamaño, luego la
     * capacidad se cambia para igualar el tamaño reemplazando su arreglo de
     * datos interno, guardado en el campo {@code listadoDatosElemento}, con uno
     * más pequeño. Una aplicación puede usar esta operación para minimizar el
     * almacenamiento de un vector.      
     */
    public synchronized void recortarATamanio() {
        this.conteoModulo++;
        int capacidadAntigua = this.listadoDatosElemento.length;
        if (this.conteoElemento < capacidadAntigua) {
            this.listadoDatosElemento = Arrays.copyOf(this.listadoDatosElemento, this.conteoElemento);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized void reemplazarTodo(UnaryOperator<E> operador) {
        Objects.requireNonNull(operador);
        final int conteoModuloEsperado = this.conteoModulo;
        final int tamanio = this.conteoElemento;
        for (int i = 0; this.conteoModulo == conteoModuloEsperado && i < tamanio; i++) {
            this.listadoDatosElemento[i] = operador.apply((E) this.listadoDatosElemento[i]);
        }
        if (this.conteoModulo != conteoModuloEsperado) {
            throw new ConcurrentModificationException();
        }
        this.conteoModulo++;
    }

    /**
     * Elimina el elemento en la posición especificada en esta ListaVector.
     * Desplaza cualquier elemento posterior a la izquierda (resta uno de sus
     * índices). Devuelve el elemento que se eliminó de la ListaVector.
     *
     * @throws ArrayIndexOutOfBoundsException si el índice está fuera de
     * rango({@code indice < 0 || indice >= tamanio()})      
     *
     *
     * @param indice el índice del elemento a eliminar
     * @return elemento que se eliminó
     * @since 1.2      
     */
    @Override
    public synchronized E remover(int indice) {
        this.conteoModulo++;
        if (indice >= this.conteoElemento) {
            throw new ArrayIndexOutOfBoundsException(indice);
        }
        E valorAntiguo = this.listadoDatosElemento(indice);

        int numeroMovido = this.conteoElemento - indice - 1;
        if (numeroMovido > 0) {
            System.arraycopy(this.listadoDatosElemento,
                    indice + 1,
                    this.listadoDatosElemento,
                    indice,
                    numeroMovido);
        }
        this.listadoDatosElemento[--this.conteoElemento] = null; //Deja que gc haga su trabajo.

        return valorAntiguo;
    }

    /**
     * Elimina la primera aparición del elemento especificado en esta
     * ListaVector  Si la ListaVector no contiene el elemento, no se modifica.
     * Más formalmente, elimina el elemento con el índice más bajo i tal que
     * {@code (objeto == null ? obtener(i) == null: objeto.equals(obtener(i)))}
     * (si tal existe un elemento).
     *
     * @param objeto elemento que se eliminará de esta ListaVector, si está
     * presente
     * @return true si la ListaVector contiene el elemento especificado
     * @since 1.2
     */
    @Override
    public boolean remover(Object objeto) {
        return this.removerElemento(objeto);
    }

    /**
     * Elimina la primera aparición (más indexada) del argumento de este vector
     * Si el objeto se encuentra en este vector, cada componente en el vector
     * con un índice mayor o igual al el índice del objeto se desplaza hacia
     * abajo para tener un índice más pequeño que el valor que tenía
     * previamente.    
     * <p>
     * Este método es idéntico en funcionalidad al {@link #remover(Objeto)}
     * método (que es parte del interfaz {@link Lista}).
     *
     * @param objeto el componente que se eliminará
     * @return {@code true} si el argumento era un componente de este vector;
     * {@code false} de lo contrario.      
     */
    public synchronized boolean removerElemento(Object objeto) {
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
     * vector con un índice mayor o igual al especificado {@code indice} se
     * desplaza hacia abajo para tener un índice uno más pequeño que el valor
     * que tenía anteriormente. El tamaño de este vector se reduce en
     * {@code 1}.     
     * <p>
     * El índice debe ser un valor mayor o igual que {@code 0}y menor que el
     * tamaño actual del vector.      
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
    public synchronized void removerElementoEn(int indice) {
        this.conteoModulo++;
        if (indice >= this.conteoElemento) {
            throw new ArrayIndexOutOfBoundsException(indice + " >= "
                    + this.conteoElemento);
        } else if (indice < 0) {
            throw new ArrayIndexOutOfBoundsException(indice);
        }
        int j = this.conteoElemento - indice - 1;
        if (j > 0) {
            System.arraycopy(this.listadoDatosElemento, indice + 1,
                    this.listadoDatosElemento, indice, j);
        }
        this.conteoElemento--;
        this.listadoDatosElemento[this.conteoElemento] = null;
        /* Deja que gc haga su trabajo*/
    }

    private synchronized boolean removerLote(Coleccion<?> coleccion, boolean complemento) {
        Object[] listadoDatosElemento = this.listadoDatosElemento;
        int r = 0, w = 0;
        boolean modificado = false;
        try {
            for (; r < this.conteoElemento; r++) {
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
            if (r != this.conteoElemento) {
                System.arraycopy(listadoDatosElemento, r, listadoDatosElemento,
                        w, this.conteoElemento - r);
                w += this.conteoElemento - r;
            }
            if (w != this.conteoElemento) {
                //Limpia para dejar que GC haga su trabajo.
                for (int i = w; i < this.conteoElemento; i++) {
                    listadoDatosElemento[i] = null;
                }
                this.conteoModulo += this.conteoElemento - w;
                this.conteoElemento = w;
                modificado = true;
            }
        }
        return modificado;
    }

    /*
      * Método de eliminación privada que omite la verificación de límites y no
      * devuelve el valor eliminado.
      */
    private synchronized void removerRapido(int indice) {
        this.conteoModulo++;
        int numeroMovido = this.conteoElemento - indice - 1;
        if (numeroMovido > 0) {
            System.arraycopy(this.listadoDatosElemento, indice + 1,
                    this.listadoDatosElemento, indice, numeroMovido);
        }
        this.listadoDatosElemento[--this.conteoElemento] = null;
        /*Limpia para dejar 
        que GC haga su trabajo.*/
    }

    /**
     * Elimina de esta lista todos los elementos cuyo índice está entre
     * {@code desdeIndice}, inclusivo, y {@code hastaIndice}, exclusivo. Cambia
     * los elementos sucesivos a la izquierda (reduce su índice). Esta llamada
     * acorta la lista mediante elementos
     * {@code (hastaIndice - desdeIndice)}.(Si
     * {@code hastaIndice == desdeIndice}, esta operación no tiene ningún
     * efecto).      
     */
    @Override
    protected synchronized void removerRango(int desdeIndice, int hastaIndice) {
        this.conteoModulo++;
        int nuumeroMovido = this.conteoElemento - hastaIndice;
        System.arraycopy(this.listadoDatosElemento,
                hastaIndice,
                this.listadoDatosElemento,
                desdeIndice,
                nuumeroMovido);

        //Deja que gc haga su trabajo.
        int nuevoConteoElemento = this.conteoElemento - (hastaIndice - desdeIndice);
        while (this.conteoElemento != nuevoConteoElemento) {
            this.listadoDatosElemento[--this.conteoElemento] = null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized boolean removerSi(Predicate<? super E> filtro) {
        Objects.requireNonNull(filtro);
        //Averiguar qué elementos deben eliminarse.
        //Cualquier excepción arrojada desde el predicado filtro en esta etapa.
        //Dejará la colección sin modificaciones.
        int removerConteo = 0;
        final int tamanio = this.conteoElemento;
        final BitSet removerConjunto = new BitSet(tamanio);
        final int conteoModuloEsperado = this.conteoModulo;
        for (int i = 0; this.conteoModulo == conteoModuloEsperado && i < tamanio; i++) {
            @SuppressWarnings("unchecked")
            final E element = (E) this.listadoDatosElemento[i];
            if (filtro.test(element)) {
                removerConjunto.set(i);
                removerConteo++;
            }
        }
        if (this.conteoModulo != conteoModuloEsperado) {
            throw new ConcurrentModificationException();
        }

        /*Desplaza los elementos supervivientes que quedan sobre los espacios 
          que dejan los elementos eliminados.*/
        final boolean cualquieraParaRemover = removerConteo > 0;
        if (cualquieraParaRemover) {
            final int nuevoTamanio = tamanio - removerConteo;
            for (int i = 0, j = 0; (i < tamanio) && (j < nuevoTamanio); i++, j++) {
                i = removerConjunto.nextClearBit(i);
                this.listadoDatosElemento[j] = this.listadoDatosElemento[i];
            }
            for (int k = nuevoTamanio; k < tamanio; k++) {
                this.listadoDatosElemento[k] = null; //Deja que gc haga su trabajo.
            }
            this.conteoElemento = nuevoTamanio;
            if (this.conteoModulo != conteoModuloEsperado) {
                throw new ConcurrentModificationException();
            }
            this.conteoModulo++;
        }

        return cualquieraParaRemover;
    }

    /**
     * Elimina de este Vector todos sus elementos que están contenidos en
     * Colección especificada.
     *
     * @param coleccion una colección de elementos que se eliminarán del Vector
     * @return devuelve true si este Vector cambió como resultado de la llamada
     * @throws ClassCastException si los tipos de uno o más elementos  en este
     * vector son incompatibles con el especificado
     * colección(<a href="Collection.html#optional-restrictions">
     * opcional</a>)      
     *
     * @throws NullPointerException si este vector contiene uno o más elementos
     * nulos y la colección especificada no admite null elementos
     * (<a href="Collection.html#optional-restrictions"> opcional </a>), o si la
     * colección especificada es nula
     * @since 1.2      
     */
    @Override
    public synchronized boolean removerTodo(Coleccion<?> coleccion) {
        return super.removerTodo(coleccion);
    }

    /**
     * Elimina todos los componentes de este vector y establece su tamaño en
     * cero.   
     * <p>
     * Este método es idéntico en funcionalidad al {@link #limpiar} método (que
     * es parte de la interfaz {@link Lista}).      
     */
    public synchronized void removerTodosLosElementos() {
        this.conteoModulo++;
        //Deja que gc haga su trabajo.
        for (int i = 0; i < this.conteoElemento; i++) {
            this.listadoDatosElemento[i] = null;
        }

        this.conteoElemento = 0;
    }

    /**
     * Crea un <em><a href="Spliterator.html#binding"> enlace de último
     * momento</a></em>
     * y <em>fail-fast</em> {@link Spliterator} sobre los elementos en este
     * lista.    
     * <p>
     * Los informes{
     *
     * @Spliterator} informan {@link Spliterator #SIZED},
     * {@Spliterator #SUBSIZED} y {
     * @Spliterator #ORDERED}.Las implementaciones prioritarias deben documentar
     * el informe de valores característicos.
     * @return a {@code Spliterator} sobre los elementos en esta lista
     * @since 1.8      
     */
    @Override
    public Spliterator<E> spliterator() {
        return new ListaVectorSpliterator<>(this, null, 0, -1, 0);
    }

    /**
     * Devuelve una vista de la porción de esta lista entre desdeIndice,
     * inclusive, y hastaIndice, exclusivo. (Si desdeIndice y hastaIndice son
     * igual, la Lista devuelta está vacía.) La Lista devuelta está respaldada
     * por este  Lista, por lo que los cambios en la lista devuelta se reflejan
     * en esta lista, y viceversa. La Lista devuelta es compatible con toda la
     * Lista opcional  operaciones compatibles con esta lista.      
     * <p>
     * Este método elimina la necesidad de operaciones de rango explícitas (de
     * el tipo que comúnmente existe para las matrices). Cualquier operación que
     * espera una lista se puede usar como una operación de rango operando en
     * una vista sublista en lugar de una lista completa. Por ejemplo, la
     * siguiente expresión idiomática elimina un rango de elementos de una
     * lista:      
     * <pre>
     * lista.subLista (de, a) .limpiar();
     * </pre> Se pueden construir modismos similares para indiceDe y
     * ultimoIndiceDe, y todos los algoritmos de la clase Colecciones se pueden
     * aplicar a una sublista
     *
     * <p>
     * La semántica de la Lista devuelta por este método no se define si la
     * lista de respaldo (es decir, esta Lista) está <i>estructuralmente
     * modificada</i> en cualquier forma que no sea a través de la lista
     * devuelta. (Las modificaciones estructurales son aquellos que cambian el
     * tamaño de la Lista, o lo perturban en tal una moda que las iteraciones en
     * curso pueden arrojar resultados incorrectos.)
     *
     * @param desdeIndice punto final bajo (inclusivo) de la subLista
     * @param hastaIndice punto final alto(exclusivo) de la subLista
     * @return una vista del rango especificado dentro de esta lista
     * @throws IndexOutOfBoundsException si un valor de índice de punto final
     * está fuera de rango {@code (desdeIndice < 0 || hastaIndice > tamanio)}
     * @throws IllegalArgumentException si los índices de punto final están
     * fuera de servicio {@code (fromIndex> toIndex)}
     */
    @Override
    public synchronized Lista<E> subLista(int desdeIndice, int hastaIndice) {
        return (Lista<E>) Collections.synchronizedList((List<E>) super.subLista(desdeIndice, hastaIndice));
    }

    @SuppressWarnings("unchecked")
    private ListaVector<E> superClone() {
        try {
            return (ListaVector<E>) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError(ex);
        }
    }

    /**
     * Devuelve la cantidad de elementos en este vector.
     *
     * @return la cantidad de elementos en este vector      
     */
    @Override
    public synchronized int tamanio() {
        return this.conteoElemento;
    }

    /**
     * Devuelve una representación de cadena de este Vector, que contiene la
     * representación de cadena de cada elemento.      
     */
    @Override
    public synchronized String toString() {
        return super.toString();
    }

    /**
     * Devuelve el último componente del vector.
     *
     * @return el último componente del vector, es decir, el componente en el
     * índice <code>tamanio() & nbsp; - & nbsp; 1</code>.
     * @throws NoSuchElementException si este vector está vacío      
     */
    public synchronized E ultimoElemento() {
        if (this.conteoElemento == 0) {
            throw new NoSuchElementException();
        }
        return this.listadoDatosElemento(this.conteoElemento - 1);
    }

    /**
     * Devuelve el índice de la última aparición del elemento especificado en
     * este vector, o -1 si este vector no contiene el elemento. Más
     * formalmente, devuelve el índice más alto {@code i} tal que <tt>(objeto ==
     * null & nbsp;? & nbsp; obtener(i) == null & nbsp;: & nbsp; o.equals
     * (obtener(i)))</tt>, o -1 si no hay tal índice.
     *
     * @param objeto elemento para buscar
     * @return el índice de la última ocurrencia del elemento especificado en
     * este vector, o -1 si este vector no contiene el elemento      
     */
    @Override
    public synchronized int ultimoIndiceDe(Object objeto) {
        return this.ultimoIndiceDe(objeto, this.conteoElemento - 1);
    }

    /**
     * Devuelve el índice de la última aparición del elemento especificado en
     * este vector, buscando hacia atrás desde {@code indice}, o devuelve -1 si
     * el elemento no se encuentra. Más formalmente, devuelve el índice más alto
     * {@code i} tal que
     * <tt>(i & nbsp; & lt; = & nbsp; index & nbsp; & amp;; & nbsp; (objeto ==
     * null & nbsp;? & nbsp; obtener(i) == null & nbsp;: & nbsp;
     * objeto.equals(obtener (i))))</tt>, o -1 si no hay tal índice.
     *
     * @param objeto elemento para buscar
     * @param indice índice para comenzar a buscar hacia atrás desde
     * @return el índice de la última aparición del elemento en posición menor o
     * igual que {@code indice} en este vector; -1 si el elemento no se
     * encuentra.
     * @throws IndexOutOfBoundsException si el índice especificado es mayor
     * igual o igual al tamaño actual de este vector      
     */
    public synchronized int ultimoIndiceDe(Object objeto, int indice) {
        if (indice >= this.conteoElemento) {
            throw new IndexOutOfBoundsException(indice + " >= " + this.conteoElemento);
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
        if (indice >= this.conteoElemento) {
            throw new IndexOutOfBoundsException(
                    this.mostrarMensajeFueraDeLosLimites(indice));
        }
    }

    /**
     * Una versión de verificarRango utilizada por agregar y agregarTodo.      
     */
    private void verificarRangoParaAgregar(int indice) {
        if (indice > this.conteoElemento || indice < 0) {
            throw new IndexOutOfBoundsException(
                    this.mostrarMensajeFueraDeLosLimites(indice));
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

    //Clase interna Itr.
    /**
     * Una versión optimizada de ListaAbstracta.Itr      
     */
    private class Itr implements Iterator<E> {

        //Atributos de la clase interna Itr.
        int cursor;       //Índice del próximo elemento a devolver.
        int expectedModCount = conteoModulo;
        int lastRet = -1; //Índice del último elemento devuelto; -1 si no hay tal.

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
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            synchronized (ListaVector.this) {
                final int size = conteoElemento;
                int i = this.cursor;
                if (i >= size) {
                    return;
                }
                @SuppressWarnings("unchecked")
                final E[] listadoDatosElemento
                        = (E[]) ListaVector.this.listadoDatosElemento;
                if (i >= listadoDatosElemento.length) {
                    throw new ConcurrentModificationException();
                }
                while (i != size && conteoModulo == this.expectedModCount) {
                    action.accept(listadoDatosElemento[i++]);
                }
                /*Actualiza una vez al final de la iteración para reducir el 
                tráfico de escritura de montón.*/
                this.cursor = i;
                this.lastRet = i - 1;
                this.checkForComodification();
            }
        }

        @Override
        public boolean hasNext() {
            /*Racy pero dentro de las especificaciones, 
            ya que las modificaciones se verifican.*/
 /*Dentro o después de la sincronización en siguiente / anterior.*/
            return this.cursor != conteoElemento;
        }

        @Override
        public E next() {
            synchronized (ListaVector.this) {
                this.checkForComodification();
                int i = this.cursor;
                if (i >= conteoElemento) {
                    throw new NoSuchElementException();
                }
                this.cursor = i + 1;
                return listadoDatosElemento(lastRet = i);
            }
        }

        @Override
        public void remove() {
            if (this.lastRet == -1) {
                throw new IllegalStateException();
            }
            synchronized (ListaVector.this) {
                this.checkForComodification();
                ListaVector.this.remover(this.lastRet);
                this.expectedModCount = conteoModulo;
            }
            this.cursor = this.lastRet;
            this.lastRet = -1;
        }
    }

    //Clase interna ListItr.
    /**
     * Una versión optimizada de AbstractList.ListItr      
     */
    final class ListItr extends Itr implements ListIterator<E> {

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
            int i = this.cursor;
            synchronized (ListaVector.this) {
                this.checkForComodification();
                ListaVector.this.agregar(i, e);
                this.expectedModCount = conteoModulo;
            }
            this.cursor = i + 1;
            this.lastRet = -1;
        }

        @Override
        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        @Override
        public int nextIndex() {
            return this.cursor;
        }

        @Override
        public E previous() {
            synchronized (ListaVector.this) {
                checkForComodification();
                int i = this.cursor - 1;
                if (i < 0) {
                    throw new NoSuchElementException();
                }
                this.cursor = i;
                return listadoDatosElemento(this.lastRet = i);
            }
        }

        @Override
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override
        public void set(E e) {
            if (this.lastRet == -1) {
                throw new IllegalStateException();
            }
            synchronized (ListaVector.this) {
                this.checkForComodification();
                ListaVector.this.establecer(this.lastRet, e);
            }
        }
    }

    //Clase interna ListaVectorSplIterator.
    /**
     * Similar a Spliterator de la clase ListaArreglo
     */
    static final class ListaVectorSpliterator<E> implements Spliterator<E> {

        //Atributos de la clase interna ListaVectorSpliterator.
        private Object[] array;
        private int expectedModCount; // Inicializado cuando se establece la cerca.
        private int fence; // - 1 hasta que se use; luego un último índice pasado.
        private int index; //Índice actual, modificado en avanzado/dividido.
        private final ListaVector<E> list;

        //Constructores de la clase interna ListaVectorSpliterator.
        /**
         * Crear un nuevo spliterator vacío.          
         */
        ListaVectorSpliterator() {
            this.array = null;
            this.expectedModCount = 0;
            this.fence = 0;
            this.index = 0;
            this.list = null;
        }

        /**
         * Crear un nuevo spliterator que cubra el rango dado.          
         */
        ListaVectorSpliterator(ListaVector<E> list, Object[] array, int origin,
                int fence, int expectedModCount) {
            this.array = array;
            this.expectedModCount = expectedModCount;
            this.fence = fence;
            this.index = origin;
            this.list = list;
        }

        //Métodos de la clase interna ListaVectorSpliterator.
        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public long estimateSize() {
            return (long) (getFence() - index);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            int i, hi; //Acceso al polipasto y cheques desde el bucle.
            ListaVector<E> lst;
            Object[] a;
            if (action == null) {
                throw new NullPointerException();
            }
            if ((lst = this.list) != null) {
                if ((hi = this.fence) < 0) {
                    synchronized (lst) {
                        this.expectedModCount = lst.conteoModulo;
                        a = this.array = lst.listadoDatosElemento;
                        hi = this.fence = lst.conteoElemento;
                    }
                } else {
                    a = this.array;
                }
                if (a != null && (i = this.index) >= 0 && (this.index = hi) <= a.length) {
                    while (i < hi) {
                        action.accept((E) a[i++]);
                    }
                    if (lst.conteoModulo == this.expectedModCount) {
                        return;
                    }
                }
            }
            throw new ConcurrentModificationException();
        }

        private int getFence() { //Initialize en el primer uso.
            int hi;
            if ((hi = this.fence) < 0) {
                synchronized (this.list) {
                    this.array = this.list.listadoDatosElemento;
                    this.expectedModCount = this.list.conteoModulo;
                    hi = this.fence = this.list.conteoElemento;
                }
            }
            return hi;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            int i;
            if (action == null) {
                throw new NullPointerException();
            }
            if (getFence() > (i = this.index)) {
                this.index = i + 1;
                action.accept((E) this.array[i]);
                if (this.list.conteoModulo != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<E> trySplit() {
            int hi = getFence(), lo = this.index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null
                    : new ListaVectorSpliterator<E>(this.list,
                            this.array,
                            lo,
                            this.index = mid,
                            this.expectedModCount);
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
            this.conteoModulo = ListaVector.this.conteoModulo;
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
            E valorAntiguo = ListaVector.this.listadoDatosElemento(
                    this.compensacion + indice);
            ListaVector.this.listadoDatosElemento[this.compensacion + indice]
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
                int expectedModCount = ListaVector.this.conteoModulo;

                /*Métodos de la clase anónima interna ListIterator de la clase
                interna SubLista.*/
                @Override
                public void add(E e) {
                    this.checkForComodification();

                    try {
                        int i = this.cursor;
                        ListaVector.SubLista.this.agregar(i, e);
                        this.cursor = i + 1;
                        this.lastRet = -1;
                        this.expectedModCount = ListaVector.this.conteoModulo;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                final void checkForComodification() {
                    if (this.expectedModCount != ListaVector.this.conteoModulo) {
                        throw new ConcurrentModificationException();
                    }
                }

                @SuppressWarnings("unchecked")
                @Override
                public void forEachRemaining(Consumer<? super E> consumer) {
                    Objects.requireNonNull(consumer);
                    final int size = ListaVector.SubLista.this.tamanio;
                    int i = this.cursor;
                    if (i >= size) {
                        return;
                    }
                    final Object[] elementData
                            = ListaVector.this.listadoDatosElemento;
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
                    return this.cursor != ListaVector.SubLista.this.tamanio;
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
                    if (i >= ListaVector.SubLista.this.tamanio) {
                        throw new NoSuchElementException();
                    }
                    Object[] elementData
                            = ListaVector.this.listadoDatosElemento;
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
                            = ListaVector.this.listadoDatosElemento;
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
                        ListaVector.SubLista.this.remover(this.lastRet);
                        this.cursor = this.lastRet;
                        this.lastRet = -1;
                        this.expectedModCount = ListaVector.this.conteoModulo;
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
                        ListaVector.this.establecer(offset + this.lastRet, e);
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
            return ListaVector.this.listadoDatosElemento(this.compensacion
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
            return new ListaVectorSpliterator<E>(ListaVector.this, ListaVector.this.listadoDatosElemento,
                    this.compensacion, this.compensacion + this.tamanio,
                    this.conteoModulo);
        }

        @Override
        public Lista<E> subLista(int desdeIndice, int hastaIndice) {
            verificarRangoSubLista(desdeIndice, hastaIndice, this.tamanio);
            return new ListaVector.SubLista(this, this.compensacion,
                    desdeIndice, hastaIndice);
        }

        @Override
        public int tamanio() {
            this.verificarParaMercantilizacion();
            return this.tamanio;
        }

        private void verificarParaMercantilizacion() {
            if (ListaVector.this.conteoModulo != this.conteoModulo) {
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
