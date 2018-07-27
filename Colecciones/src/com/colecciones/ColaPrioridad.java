/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Una prioridad sin límites {@linkplain Cola cola} basada en un montón de
 * prioridad. Los elementos de la cola de prioridad se ordenan de acuerdo con su
 * {@linkplain Ordenamiento natural comparable}, o por un {@link Comparator}
 * proporcionado en el tiempo de construcción de la cola, dependiendo de qué
 * constructor usado. Una cola de prioridad no permite elementos {@code null}.
 * Una cola de prioridad que depende del orden natural tampoco permite inserción
 * de objetos no comparables (si lo hace puede resultar en
 * {@code ClassCastException}).  
 * <p>
 * El<em>encabezado</em> de esta cola es el elemento <em>menos</em>
 * con respecto al orden especificado. Si hay varios elementos atado por valor
 * mínimo, la cabeza es uno de esos elementos: los lazos son roto
 * arbitrariamente. Las operaciones de recuperación de cola {@code encuestar},
 * {@code remover}, {@code ojear} y {@code elemento} acceden al elemento a la
 * cabeza de la cola.  
 * <p>
 * Una cola de prioridad no tiene límites, pero tiene un interno
 * <i>capacidad</i> que rige el tamaño de un arreglo utilizada para almacenar el
 * elementos en la cola. Siempre es al menos tan grande como la cola tamaño. A
 * medida que los elementos se agregan a una cola de prioridad, su capacidad
 * crece automáticamente Los detalles de la política de crecimiento no son
 * especificado.  
 * <p>
 * Esta clase y su iterador implementan todas las <em>métodos</em>
 * opcionales de {@link Coleccion} y {@link
 * Iterator} interfaces. El iterador provisto en el método {@link
 * #iterator()} está <em>no</em> garantizado para atravesar los elementos de la
 * cola de prioridad en cualquier orden en particular. Si necesita ordenado
 * recorrido, considere usar {@code Arrays.sort(pq.paraFormar())}.  
 * <p>
 * <strong> Tenga en cuenta que esta implementación no está sincronizada.
 * </ strong>
 * Múltiples hilos no deberían tener acceso a {@code ColaPrioridad} instancia al
 * mismo tiempo si alguno de los hilos modifica la cola. En su lugar, use el
 * hilo {@link
 *  java.util.concurrent.PriorityBlockingQueue} clase.  
 * <p>
 * Nota de implementación: esta implementación proporciona O (log(n)) tiempo
 * para los métodos de enqueuing y dequeuing({
 *
 * @code ofrecer}, {@code encuestar}, {@code remover()} y {@code agregar});
 * tiempo lineal para los métodos{@code remover (Object)} y
 * {@code contiene (Object)}; y tiempo constante para los métodos de
 * recuperación ({@code ojear}, {@code elemento} y {@code tamanio}).  
 * <p>
 * Esta clase es miembro de la  
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 *  Java Collections Framework </a>.  
 */
/**
 *
 * @author FABAME
 * @param <E> el tipo de elementos contenidos en esta colección
 */
public class ColaPrioridad<E> extends ColaAbstracta<E> implements Serializable {

    public ColaPrioridad() {

    }
    
    @Override
    public E encuestar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ofrecer(E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E ojear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public int tamanio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
