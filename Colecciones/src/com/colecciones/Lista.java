/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.UnaryOperator;

/**
 * Interfaz Lista para guardar y manipular elementos en una lista.
 */
/**
 * Una colección ordenada (también conocida como una <i>secuencia</i>). El
 * usuario de esto la interfaz tiene un control preciso sobre en qué parte de la
 * lista se encuentra cada elemento insertado. El usuario puede acceder a los
 * elementos por su índice entero (posición en la lista), y busca elementos en
 * la lista.<p>
 *
 * A diferencia de los conjuntos, las listas generalmente permiten elementos
 * duplicados. Más formalmente,las listas generalmente permiten pares de
 * elementos <tt>e1</tt> y <tt>e2</tt>
 * tal que <tt>e1.equals(e2)</tt>, y generalmente permiten múltiples elementos
 * nulos si permiten elementos nulos en absoluto. No es inconcebible que alguien
 * desee implementar una lista que prohíba los duplicados, lanzando excepciones
 * de tiempo de ejecución cuando el usuario intenta insertarlas, pero espera que
 * este uso sea raro.
 * <p>
 *
 * La interfaz <tt>Lista</tt> establece estipulaciones adicionales, más allá de
 * las especificado en la interfaz <tt>Coleccion</tt>, en los contratos del
 * <tt>iterator</tt>, <tt>agregar</tt>, <tt>remover
 * </tt>, <tt>es igual a</tt>, y los métodos <tt>hashCode</tt> .Las
 * declaraciones para otros métodos heredados son también se incluye aquí para
 * mayor comodidad.
 * <p>
 *
 * La interfaz <tt> List </tt> proporciona cuatro métodos para posicional
 * (indexado) acceso a los elementos de la lista. Las listas (como las matrices
 * de Java) están basadas en cero. Nota  * que estas operaciones pueden
 * ejecutarse en un tiempo proporcional al valor del índice para algunas
 * implementaciones (la clase <tt>ListaEnlazada</tt>, para ejemplo). Por lo
 * tanto, iterar sobre los elementos en una lista es típicamente preferible a la
 * indexación a través de él si la persona que llama no conoce el
 * implementación.
 * <p>
 *
 * La interfaz <tt> List </tt> proporciona un iterador especial, llamado  
 *
 * <tt>ListIterator</tt>, que permite la inserción y el reemplazo de elementos,
 * y acceso bidireccional además de las operaciones normales que el La interfaz
 * <tt>Iterator</tt> proporciona. Se proporciona un método para obtener un lista
 * iterador que comienza en una posición específica en la lista.
 * <p>
 *
 * La interfaz <tt>Lista</tt> proporciona dos métodos para buscar una
 * especificada objeto Desde el punto de vista del rendimiento, estos métodos
 * deberían usarse con precaución. En muchas implementaciones, realizarán
 * costosos procesos búsquedas.
 * <p>
 *
 * La interfaz <tt>Lista</tt> proporciona dos métodos para insertar y eliminar
 * elementos múltiples en un punto arbitrario en la lista.
 * <p>
 *
 * Nota: Si bien es permisible que las listas se contengan como elementos, se
 * recomienda extrema precaución: <tt>es igual a</tt> y <tt>hashCode
 * </tt> los métodos ya no están bien definidos en dicha lista.
 * <p>
 * Algunas implementaciones de listas tienen restricciones sobre los elementos
 * que pueden contener. Por ejemplo, algunas implementaciones prohíben elementos
 * nulos, y algunos tienen restricciones sobre los tipos de sus elementos.
 * Intentando agregar un elemento inelegible arroja una excepción no verificada,
 * por lo general <tt> NullPointerException </tt> o <tt>
 * ClassCastException </tt>. Intentando  * para consultar la presencia de un
 * elemento inelegible puede arrojar una excepción, o simplemente puede devolver
 * falso; algunas implementaciones exhibirán el anterior comportamiento y
 * algunos exhibirán el último. De manera más general, intentando un operación
 * en un elemento inelegible cuya finalización no resultaría en la inserción de
 * un elemento inelegible en la lista puede arrojar excepción o puede tener
 * éxito, a elección de la implementación. Tales excepciones se marcan como
 * "opcionales" en la especificación para este interfaz.  
 * <p>
 * Esta interfaz es miembro de  
 *
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 *  * Java Collections Framework </a>.
 *
 * @param <E> el tipo de elementos contenidos en esta lista
 * @author FABAME
 * @see Coleccion
 * @see ListaAbstracta
 * @since 1.2
 */
public interface Lista<E> extends Coleccion<E> {

    //Métodos de la interfaz Lista
    /**
     * Añade el elemento especificado al final de esta lista (operación
     * opcional).  
     * <p>
     * Las listas que admiten esta operación pueden poner limitaciones a lo que
     * los elementos se pueden agregar a esta lista. En particular, algunos las
     * listas rechazarán agregar elementos nulos, y otros impondrán
     * restricciones sobre el tipo de elementos que pueden agregarse. Lista las
     * clases deben especificar claramente en su documentación cualquier
     * restricción sobre qué elementos se pueden agregar.
     *
     * @param elemento elemento que se adjuntará a esta lista
     * @return <tt>true</tt> (según lo especificado por
     * {@link Coleccion #agregar})
     * @throws UnsupportedOperationException si la operación <tt>agregar</tt> no
     * es compatible con esta lista
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta lista
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * la lista no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad de este elemento
     * impide que se agregue a esta lista      
     */
    @Override
    public boolean agregar(E elemento);

    /**
     * Inserta el elemento especificado en la posición especificada en esta
     * lista (operación opcional). Cambia el elemento actualmente en esa
     * posición (si corresponde) y cualquier elemento posterior a la derecha
     * (agrega uno a su índices). índice de índice
     *
     * @param indice índice el que se debe insertar el elemento especificado
     * @param elemento elemento a insertar
     * @throws UnsupportedOperationException si la operación <tt>agregar</tt>
     * no es compatible con esta lista @throws ClassCastException si la clase
     * del elemento especificado impide que se agregue a esta lista
     * @throws NullPointerException si el elemento especificado es nulo y esta
     * lista no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad del especificado
     * elemento impide que se agregue a esta lista
     * @throws IndexOutOfBoundsException si el índice está fuera de rango(<tt>
     * indice & lt; 0 || indice & gt; size ()</tt>)      
     */
    public void agregar(int indice, E elemento);

    /**
     * Agrega todos los elementos en la colección especificada hasta el final de
     * esta lista, en el orden en que la especifican iterador de la colección
     * (operación opcional). El comportamiento de esto la operación no está
     * definida si la colección especificada se modifica mientras la operación
     * está en progreso. (Tenga en cuenta que esto ocurrirá si la colección
     * especificada es esta lista, y no está vacía.)
     *
     * @param coleccion colección que contiene elementos para agregar a esta
     * lista
     * @return <tt>true</tt> si esta lista cambia como resultado de la llamada
     * @throws UnsupportedOperationException si la operación
     * <tt>agregarTodo</tt>
     * no es compatible con esta lista
     * @throws ClassCastException si la clase de un elemento de la especificada
     * la colección impide que se agregue a esta lista
     * @throws NullPointerException si lan colección especificada contiene uno o
     * más elementos nulos y esta lista no permite elementos nulos, o si la
     * colección especificada es nula
     * @throws IllegalArgumentException si alguna propiedad de un elemento del
     * la colección especificada impide que se agregue a esta lista @see
     * #agregar(Object)      
     */
    @Override
    public boolean agregarTodo(Coleccion<? extends E> coleccion);

    /**
     * Inserta todos los elementos en la colección especificada en este lista en
     * la posición especificada (operación opcional). Cambia el elemento
     * actualmente en esa posición (si corresponde) y cualquier elemento
     * posterior elementos a la derecha (aumenta sus índices). Los nuevos
     * elementos aparecerá en esta lista en el orden en que el iterador de la
     * colección especificada. El comportamiento de esta operación es indefinido
     * si la colección especificada se modifica mientras la operación está en
     * progreso. (Tenga en cuenta que esto ocurrirá si el especificado colección
     * es esta lista, y no está vacía.)
     *
     * @param indice indice el en cual se inserta el primer elemento de la
     * colección especificada
     * @param coleccion colección que contiene elementos para agregar a esta
     * lista
     * @return <tt>true</tt> si esta lista cambia como resultado de la llamada
     * @throws UnsupportedOperationException si la operación
     * <tt>agregarTodo</tt>
     * no es compatible con esta lista
     * @throws ClassCastException si la clase de un elemento de la especificada
     * la colección impide que se agregue a esta lista
     * @throws NullPointerException si la colección especificada contiene uno o
     * más elementos nulos y esta lista no permite elementos nulos, o si la
     * colección especificada es nula
     * @throws IllegalArgumentException si alguna propiedad de un elemento del
     * la colección especificada impide que se agregue a esta lista
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     * (<tt>indice & lt; 0 || indice & gt; tamanio()</tt>)      
     */
    public boolean agregarTodo(int indice, Coleccion<? extends E> coleccion);

    /**
     * Conserva solo los elementos en esta lista que están contenidos en
     * colección especificada (operación opcional). En otras palabras, elimina
     * de esta lista todos sus elementos que no están contenidos en el colección
     * especificada
     *
     * @param coleccion colección que contiene elementos para ser retenidos en
     * esta lista
     * @return <tt>true</tt> si esta lista cambia como resultado de la llamada
     * @throws UnsupportedOperationException si la operación <tt>
     * conservarTodo</tt>
     * no es compatible con esta lista
     * @throws ClassCastException si la clase de un elemento de esta lista es
     * incompatible con la colección especificada
     * (<a href="Collection.html#optional-restrictions">opcional</a>)
     * @throws NullPointerException si esta lista contiene un elemento nulo y el
     * la colección especificada no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions">opcional</a>), o si la
     * colección especificada es nula
     * @see #remover(Object)
     * @see #contiene(Object)      
     */
    @Override
    public boolean conservarTodo(Coleccion<?> coleccion);

    /**
     * Devuelve <tt>true</tt> si esta lista contiene el elemento especificado.
     * Más formalmente, devuelve <tt>true</tt> si y solo si esta lista contiene
     * al menos un elemento <tt>elmeneto</tt>
     * tal que <tt>(objeto == null & nbsp;? & nbsp; elemento == null & nbsp;: &
     * nbsp; o.equals(elemento))</tt>.
     *
     * @param objeto elemento cuya presencia en esta lista debe probarse
     * @return <tt>true</tt> si esta lista contiene el elemento especificado
     * @throws ClassCastException si el tipo del elemento especificado es
     * incompatible con esta lista
     * (<a href="Collection.html#optional-restrictions"> opcional</a>)
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * la lista no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions"> opcional</a>)      
     */
    @Override
    public boolean contiene(Object objeto);

    /**
     * Devuelve <tt>true</tt> si esta lista contiene todos los elementos del
     * colección especificada
     *
     * @param coleccion colección para verificar la contención en esta lista
     * @return <tt>true</tt> si esta lista contiene todos los elementos del
     * colección especificada
     * @throws ClassCastException si los tipos de uno o más elementos en la
     * colección especificada son incompatibles con esto lista
     * (<a href="Collection.html#optional-restrictions">opcional</a>)
     * @throws NullPointerException si la colección especificada contiene uno o
     * más elementos nulos y esta lista no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions">
     * opcional</a>), o si la colección especificada es nula
     * @see #contiene(Object)      
     */
    @Override
    public boolean contieneTodo(Coleccion<?> coleccion);

    /**
     * Compara el objeto especificado con esta lista para la igualdad.
     * Devoluciones <tt>true</tt> si y solo si el objeto especificado también es
     * una lista, ambos las listas tienen el mismo tamaño y todos los pares de
     * elementos correspondientes en las dos listas son <i>iguales</i>. (Dos
     * elementos <tt>e1</tt> y <tt>e2</tt> son
     * <i>iguales</i> si <tt>(e1 == null? e2 == null: e1.equals (e2))</tt>.) En
     * otras palabras, se definen dos listas para ser igual si contienen los
     * mismos elementos en el mismo orden. Esta la definición asegura que el
     * método equals funciona correctamente a través de diferentes
     * implementaciones de la interfaz
     * <tt>Lista</tt>.
     *
     * @param object el objeto que se va a comparar para la igualdad con esta
     * lista
     * @return <tt>true</tt> si el objeto especificado es igual a esta lista
     *      
     */
    @Override
    public boolean equals(Object object);

    /**
     * Devuelve <tt>true</tt> si esta lista no contiene elementos.      
     *
     * @return <tt>true</tt> si esta lista no contiene elementos      
     */
    @Override
    public boolean estaVacia();

    /**
     * Reemplaza el elemento en la posición especificada en esta lista con el
     * elemento especificado (operación opcional).
     *
     * @param indice índice del elemento a reemplazar
     * @param elemento elemento para ser almacenado en la posición especificada
     * @return el elemento previamente en la posición especificada
     * @throws UnsupportedOperationException si la operación <tt>establecer</tt>
     * no es compatible con esta lista
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta lista
     * @throws NullPointerException si el elemento especificado es nulo y esta
     * lista no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad del especificado
     * elemento impide que se agregue a esta lista
     * @throws IndexOutOfBoundsException si el índice está fuera de rango      
     * (<tt> indice & lt; 0 || indice & gt; = tamanio()</tt>)
     */
    public E establecer(int indice, E elemento);

    /**
     * Devuelve el valor del código hash para esta lista. El código hash de una
     * lista se define como el resultado del siguiente cálculo:      
     * <pre> {@code
     * int hashCode = 1;
     * para (E elemento: lista)
     * hashCode = 31 * hashCode + (elemento == null? 0: elemento.hashCode());
     * }</pre> Esto asegura que <tt>lista1.equals(lista2)</tt> implica que
     * <tt> list1.hashCode () == list2.hashCode () </tt> para dos listas, <tt>
     * lista1 </tt>y<tt> lista2 </tt>, según lo requerido por el general
     * contrato de {@link Object # hashCode}.   
     *
     * @return el valor del código hash para esta lista  
     * @see Object #equals(Object)
     * @see #equals(Object)      
     */
    @Override
    public int hashCode();

    /**
     * Devuelve el índice de la primera aparición del elemento especificado en
     * esta lista, o -1 si esta lista no contiene el elemento. Más formalmente,
     * devuelve el índice más bajo <tt>i
     * </tt> tal que <tt>(objeto == null & nbsp;? & nbsp; obtener(i) == null &
     * nbsp;: & nbsp; objeto.equals (obtener(i)))</tt>, o -1 si no hay tal
     * índice.
     *
     * @param objeto elemento para buscar
     * @return el índice de la primera aparición del elemento especificado en
     * esta lista, o -1 si esta lista no contiene el elemento
     * @throws ClassCastException si el tipo del elemento especificado es
     * incompatible con esta lista
     * (<a href="Collection.html#optional-restrictions">opcional</a>)
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * la lista no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions">opcional</a>)      
     */
    public int indiceDe(Object objeto);

    /**
     * Devuelve un iterador sobre los elementos en esta lista en la secuencia
     * correcta.
     *
     * @return un iterador sobre los elementos en esta lista en la secuencia
     * correcta      
     */
    @Override
    public Iterator<E> iterator();

    /**
     * Elimina todos los elementos de esta lista (operación opcional). La lista
     * estará vacía después de que regrese esta llamada.      
     *
     * @throws UnsupportedOperationException si la operación
     * <tt>limpiar</tt> no es compatible con esta lista      
     */
    @Override
    public void limpiar();

    /**
     * Devuelve un iterador de lista sobre los elementos en esta lista (en
     * secuencia).
     *
     * @return un iterador de lista sobre los elementos en esta lista (en
     * secuencia)      
     */
    public ListIterator<E> listIterator();

    /**
     * Devuelve un iterador de lista sobre los elementos en esta lista (en
     * secuencia), comenzando en la posición especificada en la lista. El índice
     * especificado indica el primer elemento que sería devuelto por una llamada
     * inicial a {@link ListIterator # next next}. Una llamada inicial a
     * {@link ListIterator # previous previous} haría devuelve el elemento con
     * el índice especificado menos uno. índice índice
     *
     * @param indice índice del primer elemento que se devolverá desde el list
     * iterator (por una llamada a {@link ListIterator # next next})
     *
     * @return un iterador de lista sobre los elementos en esta lista (en      
     * secuencia), comenzando en la posición especificada en la lista
     * @throws IndexOutOfBoundsException si el índice está fuera de rango      
     * * ({@code indice <0 || indice> tamanio()})      
     */
    public ListIterator<E> listIterator(int indice);

    /**
     * Devuelve el elemento en la posición especificada en esta lista.      
     *
     *
     * @param indice índice del elemento a devolver
     * @return el elemento en la posición especificada en esta lista
     * @throws IndexOutOfBoundsException si el índice está fuera de rango(<tt>
     * indice & lt; 0 || indice & gt; = tamanio()</tt>)      
     */
    public E obtener(int indice);

    /**
     * Ordena esta lista de acuerdo con el orden inducido por el especificado
     * {@link Comparador}.      
     * <p>
     * Todos los elementos en esta lista deben ser <i>mutuamente comparables</i>
     * usando El comparador especificado (es decir, {@code c.compare (e1, e2)}
     * no debe lanzar a {@code ClassCastException} para elementos {@code e1} y
     * {@code e2} en la lista).         
     * <p>
     * Si el comparador especificado es {@code null}, todos los elementos de
     * este la lista debe implementar la interfaz {@link Comparable} y los
     * elementos{@linkplain Ordenamiento natural comparable} se debe usar.  
     *      
     * <p>
     * Esta lista debe ser modificable, pero no es necesario cambiar el tamaño.
     *
     * @implSpec La implementación predeterminada obtiene una matriz que
     * contiene todos los elementos en esta lista ordena el conjunto e itera
     * sobre esta lista reiniciando cada elemento de la posición correspondiente
     * en el arreglo. (Esto evita el n
     * <sup>2</sup> log(n) rendimiento que resultaría de intentar para ordenar
     * una lista vinculada en su lugar).
     * @implNote Esta implementación es una combinación estable, adaptable e
     * iterativa que requiere mucho menos que n lg (n) comparaciones cuando la
     * matriz de entrada es parcialmente ordenado, mientras que ofrece el
     * rendimiento de un tradicional mergesort cuando la matriz de entrada se
     * ordena al azar. Si el arreglo de entrada está casi ordenado, la
     * implementación requiere aproximadamente n comparaciones. Los requisitos
     * de almacenamiento temporal varían de una pequeña constante para arreglos
     * de entrada casi ordenados a n / 2 referencias de objetos aleatoriamente
     * arreglos de entrada ordenados. 
     * <p>
     * La implementación toma la misma ventaja de ascender y orden descendente
     * en su matriz de entrada, y puede aprovechar orden ascendente y
     * descendente en diferentes partes del mismo arreglo de entrada. Es
     * adecuado para combinar dos o más arreglos ordenados: simplemente
     * concatenar los arreglos y ordenar el arreglo resultante.
     * <p>
     * La implementación fue adaptada del tipo de lista de Tim Peters para
     * Python
     * (<a href=
     * "http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>). Utiliza técnicas de "Optimistic" de Peter McIlroy
     * Clasificación e Información Complejidad Teórica ", en Procedimientos de
     * la Cuarto Simposio Anual de ACM-SIAM sobre Algoritmos Discretos, páginas
     * 467-474, Enero de 1993.
     * @param comparador el {@code Comparator} utilizado para comparar los
     * elementos de la lista.Un valor {@code null} indica que los elementos
     * {@linkplain Ordenamiento natural comparable} se debe usar
     * @throws ClassCastException si la lista contiene elementos que no son
     * <i>mutuamente comparable</i> usando el comparador especificado  
     * @throws UnsupportedOperationException si el list-iterator de la lista lo
     * hace no es compatible con la operación {@code set}
     * @throws IllegalArgumentException
     * (<a href="Collection.html#optional-restrictions">opcional</a>) si se
     * encuentra que el comparador infringe el {@link Comparator} contrato
     * @desde 1.8      
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public default void ordenar(Comparator<? super E> comparador) {
        Object[] arreglo = this.paraFormar();
        Arrays.sort(arreglo, (Comparator) comparador);
        ListIterator<E> i = this.listIterator();
        for (Object elemento : arreglo) {
            i.next();
            i.set((E) elemento);
        }
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos en esta lista en
     * secuencia (del primer al último elemento).    
     * <p>
     * El arreglo devuelto será "seguro" porque no hay referencias a el
     * mantenido por esta lista. (En otras palabras, este método debe asigna un
     * nuevo arreglo incluso si esta lista está respaldada por un arreglo). La
     * persona que llama es libre de modificar el arreglo devuelto.    
     * <p>
     * Este método actúa como puente entre la basada en arreglo y la basada en
     * colecciones APIs.
     *
     * @return un arreglo que contiene todos los elementos de esta lista en
     * secuencia
     * @see Arrays # asList(Object [])      
     */
    @Override
    public Object[] paraFormar();

    /**
     * Devuelve un arreglo que contiene todos los elementos en esta lista en
     * secuencia correcta (del primer al último elemento); el tipo de tiempo de
     * ejecución del arreglo devuelto es la del arreglo especificado. Si la
     * lista se ajusta  en el arreglo especificado, se devuelve allí. De lo
     * contrario, un nuevo arreglo se asigna con el tipo de tiempo de ejecución
     * del arreglo especificado y el tamaño de esta lista.      
     * <p>
     * Si la lista se ajusta a el arreglo especificado con espacio de sobra (es
     * decir, el arreglo tiene más elementos que la lista), el elemento en el
     * arreglo inmediatamente después del final de la lista se establece en <tt>
     * null</tt>. (Esto es útil para determinar la longitud de la lista <i>
     * solamente</i> si la persona que llama sabe que la lista no contiene
     * ningún elemento nulo.)      
     * <p>
     * Al igual que el método {@link #paraFormar()}, este método actúa como
     * puente entre API basada en colección y basada en colección. Además, este
     * método permite control preciso sobre el tipo de tiempo de ejecución del
     * arreglo de salida, y puede, bajo ciertas circunstancias, se puede usar
     * para ahorrar costos de asignación.      
     * <p>
     * Supongamos que <tt>x</tt> es una lista conocida por contener solo
     * cadenas. El siguiente código puede usarse para volcar la lista en un
     * nuevo arreglo asignado de <tt>String</tt>:      
     * <pre>{@code
     * String [] y = x.paraFormar(new String[0]);
     * }</pre> Tenga en cuenta que <tt>paraFormar(new Object[0])</tt> es
     * idéntico en función a <tt>paraFormar()</tt>.
     *
     * @param arreglo el arreglo en el que se encuentran los elementos de esta
     * lista ser almacenado, si es lo suficientemente grande; de lo contrario,
     * un nuevo arreglo de el mismo tipo de tiempo de ejecución se asigna para
     * este propósito.
     * @return un arreglo que contiene los elementos de esta lista
     * @throws ArrayStoreException si el tipo de tiempo de ejecución del arreglo
     * especificado  no es un supertipo del tipo de tiempo de ejecución de cada
     * elemento en esta lista
     * @throws NullPointerException si el arreglo especificado es nulo      
     */
    @Override
    public <T> T[] paraFormar(T[] arreglo);

    /**
     * Reemplaza cada elemento de esta lista con el resultado de aplicar el
     * operador a ese elemento. Errores o excepciones de tiempo de ejecución
     * lanzadas por el operador se retransmite a la persona que llama.
     *
     * @implSpec La implementación predeterminada es equivalente a, para esta
     * {@code list}:            <pre>{@code
     * final ListIterator<E> li = list.listIterator();
     * while(li.hasNext()){li.set (operator.apply(li.next()));}}</pre> Si el
     * list-iterator de la lista no admite la operación {@code set} entonces se
     * lanzará {@code UnsupportedOperationException} cuando reemplazando el
     * primer elemento.
     *
     * @param operador el operador para aplicar a cada elemento
     * @throws UnsupportedOperationException si esta lista no es modificable.
     * Las implementaciones pueden arrojar esta excepción si un elemento no
     * puede ser reemplazado o si, en general, la modificación no es soportado
     * @throws NullPointerException si el operador especificado es nulo o si el
     * resultado del operador es un valor nulo y esta lista no permite elementos
     * nulos (<a href="Collection.html#optional-restrictions">opcional</a>)
     * @since 1.8
     */
    public default void reemplazarTodo(UnaryOperator<E> operador) {
        Objects.requireNonNull(operador);
        final ListIterator<E> li = this.listIterator();
        while (li.hasNext()) {
            li.set(operador.apply(li.next()));
        }
    }

    /**
     * Elimina el elemento en la posición especificada en esta lista (operación
     * opcional). Cambia cualquier elemento posterior a la izquierda (resta uno
     * de sus índices). Devuelve el elemento que se eliminó de la lista.
     *
     * @param indice índice del elemento a eliminar
     * @return el elemento previamente en la posición especificada
     * @throws UnsupportedOperationException si la operación <tt>remover</tt>
     * no es compatible con esta lista
     * @throws IndexOutOfBoundsException si el índice está fuera de rango(<tt>
     * indice & lt; 0 || indice & gt; = tamanio()</tt>)      
     */
    public E remover(int indice);

    /**
     * Elimina la primera aparición del elemento especificado de esta lista, si
     * está presente (operación opcional). Si esta lista no contiene el
     * elemento, no se modifica. Más formalmente, elimina el elemento con el
     * índice más bajo <tt>i</tt> tal que <tt>
     * (objeto == null & nbsp;? & nbsp; obtener(i) == null & nbsp;: & nbsp;
     * o.equals (obtener (i)))</tt>
     * (si tal elemento existe). Devuelve <tt>true</tt> si esta lista contenía
     * el elemento especificado (o de manera equivalente, si esta lista cambiaba
     * como resultado de la llamada).
     *
     * @param objeto elemento que se eliminará de esta lista, si está presente
     * @return <tt>true</tt> si esta lista contiene el elemento especificado
     * @throws ClassCastException si el tipo del elemento especificado es
     * incompatible con esta lista
     * (<a href="Collection.html#optional-restrictions">opcional </a>)
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * la lista no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions">opcional</a>)
     * @throws UnsupportedOperationException si la operación <tt>remover</tt>
     * no es compatible con esta lista      
     */
    @Override
    public boolean remover(Object objeto);

    /**
     * Elimina de esta lista todos sus elementos que están contenidos en
     * colección especificada (operación opcional).
     *
     * @param coleccion colección que contiene elementos que se eliminarán de
     * esta lista
     * @return <tt>true</tt> si esta lista cambia como resultado de la llamada
     * @throws UnsupportedOperationException si la operación
     * <tt>removerTodo</tt>
     *  no es compatible con esta lista
     * @throws ClassCastException si la clase de un elemento de esta lista es
     * incompatible con la colección especificada
     * (<a href="Collection.html#optional-restrictions">opcional</a>)
     * @throws NullPointerException si esta lista contiene un elemento nulo y el
     * la colección especificada no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions">opcional</a>), o si la
     * colección especificada es nula
     * @see #remover(Object)
     * @see #contiene(Object)      
     */
    @Override
    public boolean removerTodo(Coleccion<?> coleccion);

    /**
     * Crea un {@link Spliterator} sobre los elementos en esta lista.   
     * <p>
     * Los informes {
     *
     * @Spliterator} informan {@link Spliterator # SIZED} y
     * {@link Spliterator # ORDERED}. Las implementaciones deben documentar el
     * reporte de valores característicos adicionales.
     * @implSpec La implementación predeterminada crea un
     * <em><a href="Spliterator.html#binding">enlace tardío</a></em> spliterator
     * del {iterator} de la lista} El spliterator hereda
     * <em>fallo-rapido </em> propiedades del iterador de la lista.
     * @implNote El {@code Spliterator} creado también informa
     * {@link Spliterator # SUBSIZED}.
     * @return un{@code Spliterator} sobre los elementos en esta lista
     * @since 1.8      
     */
    @Override
    public default Spliterator<E> spliterator() {
        Object[] arreglo = new Object[this.tamanio()];
        return Spliterators.spliterator(arreglo, Spliterator.ORDERED);
    }

    /**
     * Devuelve una vista de la parte de esta lista entre las especificadas
     * <tt>desdeIndice</tt>, inclusive, y <tt>hastaIndice
     * </tt> , exclusivo. (Si <tt>desdeIndice</tt> y <tt>hastaIndex</tt>
     * son iguales, la lista devuelta es vacío.) La lista devuelta está
     * respaldada por esta lista, por lo que no es estructural los cambios en la
     * lista devuelta se reflejan en esta lista, y viceversa. La lista devuelta
     * admite todas las operaciones de lista opcionales compatibles por esta
     * lista.
     * <p>
     * Este método elimina la necesidad de operaciones de rango explícitas (de
     * el tipo que comúnmente existe para los arreglos). Cualquier operación que
     * espera una lista se puede usar como una operación de rango al pasar una
     * vista sublista en lugar de una lista completa. Por ejemplo, la siguiente
     * expresión idiomática elimina un rango de elementos de una lista:      
     * <pre> {@code
     *  lista.subLista (desde, hasta) .limpiar();
     * }</pre> Se pueden construir expresiones similares para <tt>
     * indiceDe</tt>
     * y <tt>lastIndexOf</tt>, y todos los algoritmos en la clase
     * <tt>Colecciones</tt> se puede aplicar a una sublista.
     * <p>
     * La semántica de la lista devuelta por este método no se define si la
     * lista de respaldo (es decir, esta lista) está <i>
     * estructuralmente modificada</i> en cualquier forma que no sea a través de
     * la lista devuelta. (Las modificaciones estructurales son aquellos que
     * cambian el tamaño de esta lista, o lo perturban en tal una moda que las
     * iteraciones en curso pueden arrojar resultados incorrectos.)
     *
     * @param desdeIndice punto final bajo (inclusive) de la subLista
     * @param hastaIndice highpointpoint (exclusivo) de la subLista
     * @return una vista del rango especificado dentro de esta lista
     * @throws IndexOutOfBoundsException para un valor de índice de punto final
     * ilegal <tt>desdeIndice & lt; 0 || hastaIndice & gt; size || desdeIndice &
     * gt; hasyaIndice</tt>)      
     */
    public Lista<E> subLista(int desdeIndice, int hastaIndice);

    /**
     * Devuelve la cantidad de elementos en esta lista. Si esta lista contiene
     * más que elementos <tt>Integer.MAX_VALUE</tt>, devoluciones
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return la cantidad de elementos en esta lista      
     */
    @Override
    public int tamanio();

    /**
     * Devuelve el índice de la última aparición del elemento especificado  en
     * esta lista, o -1 si esta lista no contiene el elemento. Más formalmente,
     * devuelve el índice más alto <tt> i
     * </tt> tal que <tt>(objeto == null & nbsp;? & nbsp; get (i) == null &
     * nbsp;: & nbsp; o.equals (get (i))) </tt>, o -1 si no hay tal índice.
     *
     * @param objeto elemento para buscar
     * @return el índice de la última ocurrencia del elemento especificado en
     * esta lista, o -1 si esta lista no contiene el elemento
     * @throws ClassCastException si el tipo del elemento especificado  es
     * incompatible con esta lista
     * (<a href="Collection.html#optional-restrictions"> opcional </a>)
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * la lista no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions"> opcional </a>)      
     */
    public int ultimoIndiceDe(Object objeto);
}
