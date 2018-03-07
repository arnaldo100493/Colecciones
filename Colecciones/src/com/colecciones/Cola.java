/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

/**
 * Interfaz Cola para guardar y manipular elementos en una Cola.
 */
/**
 * Una colección diseñada para contener elementos antes del procesamiento.
 * Además de las operaciones básicas {@link Coleccion}, las colas proporcionan
 * inserción adicional, extracción e inspección operaciones. Cada uno de estos
 * métodos existe en dos formas: uno arroja una excepción si la operación falla,
 * la otra devuelve un especial valor (ya sea {@code null} o {@code false},
 * dependiendo de la operación). La última forma de la operación de inserción
 * está diseñada específicamente para su uso con {@code Queue} de capacidad
 * restringida implementaciones; en la mayoría de las implementaciones, las
 * operaciones de inserción no pueden fallar.
 * <table BELLER CELLPADDING = 3 CELLSPACING = 1>
 * <caption> Resumen de los métodos de cola </ caption>
 * <tr>
 * <td> </td>
 * <td ALIGN = CENTER> <em>Lanza una excepción </em> </td>
 *  <td ALIGN = CENTER> <em> Devuelve un valor especial </em></td>
 *  </tr>
 * <tr>
 * <td> <b>Insertar</b></td>
 * <td> {
 *
 * @param <E>
 * @link Cola #agregar agregar (elemento)}</td>
 * <td> {
 * @link Cola #ofrecer ofrecer(elemento)}</td>
 * </tr>
 * <tr>
 * <td> <b>Eliminar</b></td>
 * <td> {
 *
 * @link Cola #remover remover()}</td>
 * <td> {
 * @link Cola #encuestar encuestar()}</td>
 * </tr>
 * <tr>
 * <td><b>Examinar</b></td>
 * <td> {
 * @link Cola #elemento elemento()}</td>
 * <td> {
 * @link Cola #ojear ojear()}</td>
 * </tr>
 * </table>
 *
 *  
 * <p>
 * Las colas típicamente, pero no necesariamente, ordenan elementos en una Forma
 * FIFO (primero en entrar, primero en salir). Entre las excepciones están colas
 * de prioridad, que ordenan elementos de acuerdo con un  comparador, o el orden
 * natural de los elementos, y colas LIFO (o stacks) que ordenan los elementos
 * LIFO (last-in-first-out). Cualquiera que sea el orden utilizado, la <em>
 * cabeza </em> de la cola es elemento que se eliminaría mediante una llamada a
 * {@link #remover()} o {@link #encuestar()}. En una cola FIFO, todos los
 * elementos nuevos se insertan en la <em> cola </ em> de la cola. Otros tipos
 * de colas pueden usar diferentes reglas de colocación. Cada implementación de
 * {@code Queue} debe especificar sus propiedades de ordenamiento.
 * <p>
 * El método {@link #offer offer} inserta un elemento si es posible, de lo
 * contrario, devuelve {@code false}. Esto difiere del {@link
 * método Coleccion # agregar Coleccion.agregar}, que puede fallar   agrega un
 * elemento solo lanzando una excepción sin marcar. los El método {oferta @code}
 * está diseñado para usarse cuando la falla es normal, en lugar de ocurrencia
 * excepcional, por ejemplo, en capacidad fija (o & quot; delimitadas & quot;)
 * colas.
 * <p>
 * Los métodos {@link # remover()} y {@link #pencuestar()} eliminar y devolver
 * el jefe de la cola.Exactamente qué elemento se elimina de la cola es un
 * función de la política de pedidos de la cola, que difiere de implementación a
 * implementación. {@code remover()} y {@code encuestar()} métodos difieren solo
 * en su comportamiento cuando el la cola está vacía: el método
 * {@code remover()} arroja una excepción, mientras que el método
 * {@code encuestar()} devuelve {@code null}.
 * <p>
 * Los métodos {@link #elemento()} y {@link # ojear()} vuelven, pero lo hacen no
 * eliminar, el jefe de la cola. 
 * <p>
 * La interfaz {@code Cola} no define la <i>cola de bloqueo métodos</i>, que son
 * comunes en la programación concurrente. Estos métodos, que esperan que
 * aparezcan los elementos o que el espacio esté disponible, son definido en la
 * interfaz {@link java.util.concurrent.BlockingQueue}, que extiende esta
 * interfaz. Las implementaciones de
 * <p>
 * {
 *
 * @code Cola} generalmente no permiten la inserción de {@code null} elementos,
 * aunque algunas implementaciones, como {
 * @ ListaEnlazada}, no prohíbe la inserción de {@code null}. Incluso en las
 * implementaciones que lo permiten, {@code null} debería No se insertará en una
 * {@code Queue}, ya que {@code null} también es utilizado como valor de retorno
 * especial por el método {@code poll} para indica que la cola no contiene
 * elementos. Las implementaciones de
 * <p>
 * {
 * @code Cola} generalmente no definen versiones de métodos basadas en elementos
 * {@code iguales} y {
 * @code hashCode}, sino heredar las versiones basadas en identidad de la clase
 * {@code Object}, porque la igualdad basada en elementos no es siempre bien
 * definido para colas con los mismos elementos pero diferentes propiedades de
 * pedido.  
 * <p>
 * Esta interfaz es miembro de  
 *
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.  
 *
 * @since 1.5
 * @author FABAME
 * @see Coleccion
 * @see ListaEnlazada
 * @param <E> el tipo de elementos contenidos en esta colección
 *
 */
public interface Cola<E> extends Coleccion<E> {

    /**
     * Inserta el elemento especificado en esta cola si es posible hacerlo
     * inmediatamente sin violar las restricciones de capacidad, regresando
     * {@code true} después del éxito y lanzando una
     * {@code IllegalStateException} si no hay espacio disponible actualmente.
     *
     * @param elemento el elemento para agregar
     * @return {@code true} (según lo especificado por
     * {@link Coleccion # agregar})
     * @throws IllegalStateException si el elemento no se puede agregar en este
     * tiempo debido a restricciones de capacidad
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta cola
     * @throws NullPointerException si el elemento especificado es nulo y esta
     * cola no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad de este elemento
     * impide que se agregue a esta cola      
     */
    @Override
    public boolean agregar(E elemento);

    /**
     * Recupera, pero no elimina, el encabezado de esta cola. Este método
     * difiere de {@link #encuestar encuestar} solo en que arroja una excepción
     * si esta cola está vacía.
     *
     * @return el encabezado de esta cola
     * @throws NoSuchElementException si esta cola está vacía      
     */
    public E elemento();

    /**
     * Recupera y elimina el encabezado de esta cola, o devuelve {@code null} si
     * esta cola está vacía.
     *
     * @return el encabezado de esta cola, o {@code null} si esta cola está
     * vacía      
     */
    public E encuestar();

    /**
     * Inserta el elemento especificado en esta cola si es posible hacerlo tan
     * inmediatamente sin violar las restricciones de capacidad. Cuando se usa
     * una cola de capacidad restringida, este método es generalmente preferible
     * a {@link #agregar}, que no puede insertar solo un elemento lanzando una
     * excepción.
     *
     * @param elemento el elemento para agregar
     * @return {@code true} si el elemento se agregó a esta cola, de lo
     * contrario {@code false}
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta cola
     * @throws NullPointerException si el elemento especificado es nulo y esta
     * cola no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad de este elemento
     * impide que se agregue a esta cola      
     */
    public boolean ofrecer(E elemento);

    /**
     * Recupera, pero no elimina, el encabezado de esta cola, o devuelve
     * {@code null} si esta cola está vacía. 
     *
     * @return el encabezado de esta cola, o {@code null} si esta cola está
     * vacía      
     */
    public E ojear();

    /**
     * Recupera y elimina el encabezado de esta cola. Este método difiere desde
     * {@link #poll poll} solo porque arroja una excepción si esto la cola está
     * vacía.
     *
     * @return el encabezado de esta cola
     * @throws NoSuchElementException si esta cola está vacía      
     */
    public E remover();

}
