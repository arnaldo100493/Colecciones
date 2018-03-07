/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.util.Iterator;

/**
 * Interfaz ColaDeque para guardar y manipular elementos en una ColaDeque.
 */
/**
 * Una colección lineal que admite la inserción y eliminación de elementos en  *
 * ambos extremos. El nombre <i> deque </i> es la abreviatura de "cola de doble
 * final" y generalmente se pronuncia "cubierta". La mayoría {@code Deque} las
 * implementaciones no establecen límites fijos en la cantidad de elementos
 * pueden contener, pero esta interfaz admite capacidad restringida deques así
 * como aquellos sin límite de tamaño fijo.  
 * <p>
 * Esta interfaz define métodos para acceder a los elementos en ambos final del
 * deque. Se proporcionan métodos para insertar, eliminar y examinar el
 * elemento. Cada uno de estos métodos existe en dos formas: uno lanza una
 * excepción si la operación falla, la otra devuelve un valor especial
 * ({@code null} o {@code false}, según  * la operacion). La última forma de la
 * operación de inserción es diseñado específicamente para su uso con capacidad
 * restringida implementaciones {@code ColaDeque}; en la mayoría de las
 * implementaciones, inserte las operaciones no pueden fallar    
 * <p>
 * Los doce métodos descritos anteriormente se resumen en siguiente tabla:  
 * <table BORDER CELLPADDING=3 CELLSPACING=1>
 *   <caption> Resumen de métodos ColaDeque </caption>
 *   <tr>
 *   <td> </td>
 *   <td ALIGN = CENTRE COLSPAN = 2> <b> Primer elemento (Encabezado) </b> </td>
 *   <td ALIGN = CENTRE COLSPAN = 2> <b> Último elemento (cola) </b> </td>
 *  </tr>
 * <tr>
 *   <td> </td>
 *   <td ALIGN = CENTER> <em> lanza una excepción </em> </td>
 *   <td ALIGN = CENTER> <em> Valor especial </em> </td>
 *   <td ALIGN = CENTER> <em> lanza una excepción </em> </td>
 * <td ALIGN = CENTER> <em> Valor especial </em> </td>
 *   </tr>
 * <tr>
 *  <td> <b> Insertar </b> </td>
 * <td> {
 *
 * @param <E>
 * @link ColaDeque #agregarPrimero agregarPrimero (elemento)} </td>
 *  td> {
 * @link ColaDeque #ofrecerPrimero ofrecerPrimero (elemento)} </td>
 *  <td> {
 * @link ColaDeque #agregarUltimo agregarUltimo (elemento)} </td>
 *  <td> {
 * @link ColaDeque #ofrecerUltimo  ofrecerUltimo (elemento)} </td>
 *  </tr>
 * <tr>
 * <td> <b> Eliminar </b> </td>
 *  <td> {
 * @link ColaDeque #removerPrimero removerPrimero()} </ td>
 *  <td> {
 * @link ColaDeque #encuestarPrimero encuestarPrimero()} </ td>
 *   <td> {
 * @link ColaDeque #removerUltimo removerUltimo()} </td>
 * <td> {
 * @link ColaDeque #encuestarUltimo encuestarUltimo()} </td>
 * </tr>
 * <tr>
 * <td> <b> Examinar </b> </td>
 * <td> {
 * @link ColaDeque #obtenerPrimero obtenerPrimero()} </td>
 * <td> {
 * @link ColaDeque #ojearPrimero ojearPrimero()} </td>
 * <td> {
 * @link ColaDeque #obtenerUltimo obtenerUltimo()} </td>
 * <td> {
 * @link ColaDeque #ojearUltimo ojearUltimo()} </td>
 *   </tr>
 * </table>
 *
 *  
 * <p>
 * Esta interfaz amplía la interfaz {@link Queue}. Cuando un deque es utilizado
 * como una cola, resultados de comportamiento FIFO (Primero en entrar, primero
 * en salir). Los elementos son agregado al final del deque y eliminado desde el
 * principio. Los métodos heredado de la interfaz {@code Queue} son equivalentes
 * a  {@code Deque} métodos como se indica en la siguiente tabla:
 * <table BELLER CELLPADDING = 3 CELLSPACING = 1>
 * <caption> Comparación de los métodos Queue y Deque </caption>
 * <tr>
 * <td ALIGN = CENTER> <b> {
 *
 * @code Cola} Método </b> </td>
 * <td ALIGN = CENTER> <b> Método equivalente {@code ColaDeque} </b> </td>
 *   </ tr>
 * <tr>
 *   <td> {
 * @link com.coleccione.Cola #agregar agregar(elemento)} </td>
 *   <td> {
 * @link #agregarUltimo agregarUltimo(elemento)} </td>
 * </tr>
 * <tr>
 * <td> {
 * @link com.coleccione.Cola #ofrecer ofrecer(elemento)} </td>
 * <td> {
 * @link com.coleccione.Cola #ofrecerUltimo ofertaUltimo(e)} </td>
 * </tr>
 * <tr>
 * <td> {
 * @link com.coleccione.Cola #remover remover()} </td>
 * <td> {
 * @link com.coleccione.Cola #removerPrimero removerPrimero()} </td>
 * </tr>
 * <tr>
 *  <td> {
 * @link com.coleccione.Cola #encuesta encuesta()} </td>
 * <td> {
 * @link #encuestarPrimero encuestarPrimero()} </td>
 *  </tr>
 * <tr>
 * <td> {
 * @link com.coleccione.Cola #elemento elemento()} </td>
 * <td> {
 * @link #obtenerPrimero obtenerPrimero()} </td>
 * </tr>
 *  <tr>
 *  <td> {
 * @link com.coleccione.Cola #ojear ojear()} </td>
 *  <td> {
 * @link #peek peekFirst ()} </td>
 * </tr>
 * </table>
 *  
 * <p>
 * Deques también se puede usar como pilas LIFO (Last-In-First-Out). Esta la
 * interfaz debe usarse con preferencia a la clase heredada {@link Stack}.
 * Cuando una deque se usa como una pila, los elementos se empujan y se sacan de
 * la  principio de la deque Los métodos de pila son precisamente equivalentes a
 * {@code Deque} métodos como se indica en la tabla a continuación:  *  
 *
 * <table BELLER CELLPADDING = 3 CELLSPACING = 1>
 * <caption> Comparación de los métodos Stack y Deque </ caption>
 * <tr>
 * <td ALIGN = CENTER> <b> Método de pila </ b> </ td>
 *   <td ALIGN = CENTER> <b> Método equivalente {@code Deque} </ b> </td>
 * </tr>
 * <tr>
 * <td> {
 * @link #push push (e)} </td>
 * <td> {
 * @link #addFirst addFirst (e)} </td>
 * </tr>
 *  <tr>
 *  <td> {
 * @link # pop pop ()} </td>
 * <td> {
 * @link #removeFirst removeFirst ()} </td>
 * </tr>
 *   <tr>
 *  <td> {
 * @link #peek peek ()} </td>
 * <td> {
 * @link #peekFirst peekFirst ()} </td>
 * </tr>
 * </table>
 *
 *  
 * <p>
 * Tenga en cuenta que el método {@link #peek peek} funciona igual de bien
 * cuando un deque se usa como una cola o una pila; en cualquier caso, los
 * elementos son dibujado desde el comienzo del deque.  
 * <p>
 * Esta interfaz proporciona dos métodos para eliminar el interior elementos,
 * {@link #removeFirstOccurrence removeFirstOccurrence} y
 * {@link #removeLastOccurrence removeLastOccurrence}.  
 * <p>
 * A diferencia de la interfaz {@link List}, esta interfaz no proporcionar
 * soporte para el acceso indexado a los elementos.  
 * <p>
 * Mientras que las implementaciones de {@code Deque} no son estrictamente
 * necesarias para prohibir la inserción de elementos nulos, son fuertemente
 * Animado a hacerlo. Usuarios de implementaciones de {@code ColaDeque} que
 * permiten que los elementos nulos sean fuertemente alentados <i> no </i> a
 * aproveche la posibilidad de insertar valores nulos. Esto es así porque
 * {@code null} se utiliza como un valor de retorno especial por varios métodos
 *  * para indicar que el deque está vacío.Las implementaciones
 * <p>
 * {
 * @code ColaDeque} generalmente no definen versiones basadas en elementos de
 * {@code iguales} y {
 * @code hashCode} métodos, pero en su lugar heredan las versiones basadas en la
 * identidad de la clase {
 * @code Object}.  
 * <p>
 * Esta interfaz es miembro de
 * <a href = "{@ docRoot} /../ technicalotes / guides / collections / index.html">
 * Colecciones de Java Marco </a>.
 *
 * @param <E> el tipo de elementos contenidos en esta colección
 * @author FABAME
 * @since 1.6 
 */
public interface ColaDeque<E> extends Cola<E> {

    /**
     * Inserta el elemento especificado en la cola representada por esta deque
     *  (en otras palabras, en la cola de esta deque) si es posible hacerlo
     * inmediatamente sin violar las restricciones de capacidad, regresando
     * {@code true} luego del éxito y lanzando un {@code IllegalStateException}
     * si no hay espacio disponible actualmente. Cuando se usa una deque con
     * capacidad restringida, generalmente es preferible use
     * {@link #ofrecer(Object) oferta}.     
     * <p>
     * Este método es equivalente a {@link #agregarUltimo}.
     *
     * @param elemento el elemento para agregar
     *
     * @return {@code true} (según lo especificado
     * por{@link Coleccion # agregar})
     *
     * @throws IllegalStateException si el elemento no se puede agregar en este
     * tiempo debido a restricciones de capacidad
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta deque
     * @throws NullPointerException si el elemento especificado es nulo y este
     * deque no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad del especificado
     * elemento impide que se agregue a esta deque      
     */
    @Override
    public boolean agregar(E elemento);

    /**
     * Inserta el elemento especificado al frente de esta deque si es es posible
     * hacerlo inmediatamente sin violar las restricciones de capacidad,
     * lanzando una {@code IllegalStateException} si no hay espacio actualmente
     * disponible. Cuando se usa una deque con capacidad restringida,
     * generalmente es método preferible para usar {@link #offerFirst}.
     *
     * @param elemento el elemento para agregar
     * @throws IllegalStateException si el elemento no se puedeagregar en este
     * tiempo debido a restricciones de capacidad      
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta deque  
     * @throws NullPointerException si el elemento especificado es nulo y esta
     * deque no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad del especificado
     * elemento impide que se agregue a esta deque      
     */
    public void agregarPrimero(E elemento);

    /**
     * Inserta el elemento especificado al final de esta deque si es es posible
     * hacerlo inmediatamente sin violar las restricciones de capacidad,lanzando
     * una {@code IllegalStateException} si no hay espacio actualmente
     * disponible. Cuando se usa una deque con capacidad restringida,
     * generalmente es método preferible para usar {@link #ofrecerUltimo}. 
     * <p>
     * Este método es equivalente a {@link #agregar}.
     *
     * @param elemento el elemento para agregar
     * @throws IllegalStateException si el elemento no se puede agregar en este
     * tiempo debido a restricciones de capacidad
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta deque
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * deque no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad del especificado
     * elemento impide que se agregue a esta deque      
     */
    public void agregarUltimo(E elemento);

    /**
     * Devuelve {@code true} si esta deque contiene el elemento especificado.
     *       * Más formalmente, devuelve {@code true} si y solo si esta deque
     * contiene       * al menos un elemento {@code eleemnto} tal que      
     *
     * <tt>(objeto == null & nbsp;? & nbsp; elemento == null & nbsp;: & nbsp;
     * o.equals (elemento))</tt>.       *       * @param objeto elemento cuya
     * presencia en este deque debe probarse       * @return {@code true} si
     * esta deque contiene el elemento especificado       * @throws
     * ClassCastException si el tipo del elemento especificado       * es
     * incompatible con esta deque       *
     * (<a href="Collection.html#optional-restrictions"> opcional</a>)      
     *
     *
     * @throws NullPointerException si el elemento especificado es nulo y esto
     *       * deque no permite elementos nulos       *
     * (<a href="Collection.html#optional-restrictions"> opcional</a>)      
     */
    @Override
    public boolean contiene(Object objeto);

    /**
     * Devuelve un iterador sobre los elementos en este deque en reversa orden
     * secuencial. Los elementos serán devueltos en orden desde último (cola) a
     * primero (cabeza).
     *
     * @return un iterador sobre los elementos en este deque en reversa
     * secuencia      
     */
    public Iterator<E> descendingIterator();

    /**
     * Recupera, pero no elimina, el encabezado de la cola representado por este
     * deque (en otras palabras, el primer elemento de este deque).Este método
     * difiere de {@link #ojear ojear} solo en que arroja un excepción si esta
     * deque está vacía.  
     * <p>
     * Este método es equivalente a {@link #obtenerPrimero()}.
     *
     * @return el encabezado de la cola representado por este deque  
     * @throws NoSuchElementException si esta deque está vacía      
     */
    @Override
    public E elemento();

    /**
     * Empuja un elemento sobre la pila representada por esta deque (en otras
     * palabras, a la cabeza de este deque) si es posible hacerlo inmediatamente
     * sin violar las restricciones de capacidad, lanzando un
     * {@code IllegalStateException} si no hay espacio disponible actualmente. 
     * <p>
     * Este método es equivalente a {@link #agregarPrimero}.
     *
     * @param elemento el elemento para empujar
     * @throws IllegalStateException si el elemento no se puede agregar en este
     * tiempo debido a restricciones de capacidad
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta deque
     * @throws NullPointerException si el elemento especificado es nulo y esto
     *  deque no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad del especificado
     * elemento impide que se agregue a esta deque      
     */
    public void empujar(E elemento);

    /**
     *  Recupera y elimina el encabezado de la cola representada por esta deque
     * (en otras palabras, el primer elemento de esta deque), o devoluciones
     * {@code null} si esta deque está vacía.
     * <p>
     * Este método es equivalente a {@link #encuestarPrimero()}.
     *
     * @return el primer elemento de este deque, o {@code null} si esta deque
     * está vacía      
     */
    @Override
    public E encuestar();

    /**
     * Recupera y elimina el primer elemento de esta deque, o devuelve
     * {@code null} si esta deque está vacía.
     *
     * @return el encabezado de este deque, o {@code null} si este deque está
     * vacío      
     */
    public E encuestarPrimero();

    /**
     * Recupera y elimina el último elemento de esta deque, o devuelve
     * {@code null} si esta deque está vacía.
     *
     * @return la cola de esta deque, o {@code null} si esta deque está vacía
     *      
     */
    public E encuestarUltimo();

    /**
     * Devuelve un iterador sobre los elementos en este deque en la secuencia
     * correcta. Los elementos serán devueltos en orden desde el primero
     * (cabeza) hasta el último (cola).
     *
     * @return un iterador sobre los elementos en este deque en la secuencia
     * correcta      
     */
    @Override
    public Iterator<E> iterator();

    /**
     * Recupera, pero no elimina, el primer elemento de esta deque. Este método
     * difiere de {@link #ojearPrimero ojearPrimero} solo en que arroja una
     * excepción si esta deque está vacía.  
     *
     * @return el encabezadp de esta deque
     *
     * @throws NoSuchElementException si esta deque está vacía      
     */
    public E obtenerPrimero();

    /**
     * Recupera, pero no elimina, el último elemento de esta deque. Este método
     * difiere de {@link #obtenerUltimo obtenerUltimo} solo en que arroja una
     * excepción si esta deque está vacía.
     *
     * @return la cola de esta deque
     * @throws NoSuchElementException si esta deque está vacía      
     */
    public E obtenerUltimo();

    /**
     * Inserta el elemento especificado en la cola representada por esta deque
     * (en otras palabras, en la cola de esta deque) si es posible hacerlo
     * inmediatamente sin violar las restricciones de capacidad, regresando
     * {@code true} tras el éxito y {@code false} si no hay espacio actualmente
     * disponible. Cuando se usa un deque con capacidad restringida, este método
     * es generalmente es preferible al método {@link #add}, que puede fallar
     * inserte un elemento solo lanzando una excepción.      
     * <p>
     * Este método es equivalente a {@link #ofrecerUltimo}.
     *
     * @param elemento el elemento para agregar
     * @return {@code true} si el elemento se agregó a esta deque, sino
     * {@code false}
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta deque
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * deque no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad delespecificado
     * elemento impide que se agregue a esta deque      
     */
    @Override
    public boolean ofrecer(E elemento);

    /**
     * Inserta el elemento especificado al frente de esta deque a menos que lo
     * haga violar las restricciones de capacidad. Cuando se utiliza una deque
     * con capacidad restringida, este método generalmente es preferible al
     * método {@link #addFirst}, que no puede insertar un elemento solo lanzando
     * una excepción.     
     *
     * @param elemento el elemento para agregar
     * @return {@code true} si el elemento se agregó a esta deque,
     * sino{@code false}      
     *
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta deque
     * @throws NullPointerException si el elemento especificado es nulo y este
     * deque no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad del especificado
     * elemento impide que se agregue a esta deque      
     */
    public boolean ofrecerPrimero(E elemento);

    /**
     * Inserta el elemento especificado al final de esta deque a menos que lo
     * haga violar las restricciones de capacidad. Cuando se utiliza una deque
     * con capacidad restringida, este método generalmente es preferible al
     * método {@link #agregarUltimo},que no puede insertar un elemento solo
     * lanzando una excepción.
     *
     * @param elemento el elemento para agregar
     * @return {@code true} si el elemento se agregó a esta deque,
     * sino{@code false}
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta deque
     * @throws NullPointerException si el elemento especificado es nulo y esta
     * deque no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad del especificado
     * elemento impide que se agregue a esta deque      
     */
    public boolean ofrecerUltimo(E elemento);

    /**
     * Recupera, pero no elimina, el encabezado de la cola representado por este
     * deque (en otras palabras, el primer elemento de esta deque), o devuelve
     * {@code null} si esta deque está vacía.      
     * <p>
     * Este método es equivalente a {@link #ojearPrimero()}.
     *
     * @return el encabezado de la cola representado por este deque, o
     * {@code null} si esta deque está vacía      
     */
    @Override
    public E ojear();

    /**
     * Recupera, pero no elimina, el primer elemento de esta deque, o devuelve
     * {@code null} si esta deque está vacía.
     *
     * @return el encabezado de este deque, o {@code null} si este deque está
     * vacío      
     */
    public E ojearPrimero();

    /**
     * Recupera, pero no elimina, el último elemento de esta deque, o devuelve
     * {@code null} si esta deque está vacía.
     *
     * @return la cola de esta deque, o {@code null} si esta deque está vacía
     *      
     */
    public E ojearUltimo();

    /**
     * Aparece un elemento de la pila representada por esta deque. En otra
     * palabras, elimina y devuelve el primer elemento de este deque.     
     * <p>
     * Este método es equivalente a {@link #removerPrimero()}.
     *
     * @return el elemento al frente de esta deque (que es la parte superior de
     * la pila representada por esta deque)
     * @throws NoSuchElementException si esta deque está vacía      
     */
    public E quitar();

    /**
     * Recupera y elimina el encabezado de la cola representada por esta deque
     * (en otras palabras, el primer elemento de esta deque). Este método
     * difiere de {@link #encuestar encuestar} solo en que arroja un excepción
     * si esta deque está vacía.
     * <p>
     * Este método es equivalente a {@link #removerPrimero()}.
     *
     * @return el encabezado de la cola representado por este deque
     * @throws NoSuchElementException si esta deque está vacía
     */
    @Override
    public E remover();

    /**
     * Elimina la primera aparición del elemento especificado de esta deque. Si
     * el deque no contiene el elemento, no se modifica. Más formalmente,
     * elimina el primer elemento {@code e} tal que<tt>
     * (objeto == null & nbsp;? & nbsp; elemento == null & nbsp;: & nbsp;
     * objeto.equals(elemento)) </tt>
     * (si tal elemento existe). Devuelve {@code true} si esta deque contiene el
     * elemento especificado (o de manera equivalente, si esta deque cambió como
     * resultado de la llamada).      
     * <p>
     * Este método es equivalente a {@link #removerPrimeraOcurrencia(Object)}.
     *
     * @param objeto elemento que se eliminará de esta deque, si está presente
     * @return {@code true} si se eliminó un elemento como resultado de esta
     * llamada
     * @throws ClassCastException si la clase del elemento especificado es
     * incompatible con esta deque
     * (<a href="Collection.html#optional-restrictions"> opcional </a>)
     * @throws NullPointerException si el elemento especificado es nulo y este
     * deque no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions"> opcional </a>) 
     */
    @Override
    public boolean remover(Object objeto);

    /**
     * Elimina la primera aparición del elemento especificado de esta deque. Si
     * el deque no contiene el elemento, no se modifica. Más formalmente,
     * elimina el primer elemento {@code e} tal que<tt>
     * (oobjeto == null & nbsp;? & nbsp; elemento == null & nbsp;: & nbsp;
     * objeto.equals(elemento))
     * </tt>
     * (si tal elemento existe). Devuelve {@code true} si esta deque contiene el
     * elemento especificado (o de manera equivalente, si esta deque cambió como
     * resultado de la llamada).
     *
     * @param objeto elemento que se eliminará de esta deque, si está presente
     * @return {@code true} si se eliminó un elemento como resultado de esta
     * llamada
     * @throws ClassCastException si la clase del elementoespecificad es
     * incompatible con esta deque
     * (<a href="Collection.html#optional-restrictions"> opcional </a>)      
     *
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * deque no permite elementos nulos
     * (<a href="Collection.html#optional-restrictions"> opcional </a>)
     */
    public boolean removerPrimeraOcurrencia(Object objeto);

    /**
     *  Elimina la última aparición del elemento especificado de esta deque. Si
     * la deque no contiene el elemento, no se modifica. Más formalmente,
     * elimine el último elemento {@code elemento} tal que
     * <tt> (objeto == null & nbsp ;? & nbsp; elemento == null & nbsp ;: & nbsp;
     * objeto.equals (elemento)) </tt>
     * (Si tal elemento existe)Devuelve {@code true} si esto deque contenía el
     * elemento especificado (o equivalentemente, si esto cambio deque como
     * resultado de la llamada). elemento param elemento para ser eliminado de
     * esta deque, si está presente
     *
     * @return {@code true} si se eliminó un elemento como resultado de esta
     * llamada
     * @throws  ClassCastException si la clase del elemento especificado es
     * incompatible con esta deque
     */
    public boolean removerUltimaOcurrencia(Object objeto);

    /**
     * Recupera y elimina el primer elemento de esta deque. Este método difiere
     * de {@link #encuestarPrimero encuestarPrimero} solo en que arroja un
     * excepción si esta deque está vacía.
     *
     * @return el encabezado de esta deque
     * @throws NoSuchElementException si esta deque está vacía
     */
    public E removerPrimero();

    /**
     * Recupera y elimina el último elemento de esta deque. Este método difiere
     * de {@link #encuestarUltimo encuestarUltimo} solo en que arroja un
     * excepción si esta deque está vacía.
     *
     * @return la cola de esta deque
     * @throws NoSuchElementException si esta deque está vacía      
     */
    public E removerUltimo();

    /**
     * Devuelve la cantidad de elementos en esta deque.
     *
     * @return la cantidad de elementos en esta deque      
     */
    @Override
    public int tamanio();

}
