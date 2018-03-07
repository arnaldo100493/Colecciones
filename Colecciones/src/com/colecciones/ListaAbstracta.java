/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/**
 * Esta clase proporciona una implementación esquemática de la interfaz
 * {@link Lista} para minimizar el esfuerzo requerido para implementar esta
 * interfaz respaldado por un almacén de datos de "acceso aleatorio" (como un
 * arreglo). Para secuenciales datos de acceso (como una lista vinculada),
 * {@link AbstractSequentialList} debería se usa con preferencia a esta clase.  
 * <p>
 * Para implementar una lista no modificable, el programador solo necesita
 * extenderesta clase y proporciona implementaciones para {@link #obtener(int)}
 * y métodos {@link Lista#tamanio tamanio()}.  
 * <p>
 * Para implementar una lista modificable, el programador debe adicionalmente
 * anula el método {@link #establecer (int, Object) establecer (int, E)} (que de
 * otro modo arroja una {@code UnsupportedOperationException}). Si la lista es
 * de tamaño variable, el programador debe anular adicionalmente métodos
 * {@link #agregar(int, Object) agregar(int, E)} y {@link # remover(int)}  
 * <p>
 * El programador generalmente debe proporcionar un vacío (sin argumento) y
 * recopilación constructor, según la recomendación en la interfaz
 * {@link Collection} especificación.
 * <p>
 * A diferencia de otras implementaciones de colecciones abstractas, el
 * programador <i>no</i> tiene que proporcionar una implementación de iterador;
 * el iterador y el iterador de lista es implementado por esta clase, además del
 * "acceso aleatorio" métodos: {@link #obtener(int)},
 *  * {@link # establecer(int, Object) establecer(int, E)},
 *  * {@link #agregar(int, Object) agregar(int, E)} y {@link # remover (int)}.
 * <p>
 * La documentación para cada método no abstracto en esta clase describe su
 * implementación en detalle. Cada uno de estos métodos puede ser anulado si la
 * colección que se está implementando admite una implementación más eficiente.
 * <p>
 * Esta clase es miembro de la
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> el tipo de elementos contenidos en esta colección
 * @author FABAME
 * @since 1.2  
 */
public abstract class ListaAbstracta<E> extends ColeccionAbstracta<E>
        implements Lista<E> {

    //Atributo de la clase ListaAbstracta.
    /**
     * La cantidad de veces que esta lista ha sido <i>estructuralmente
     * modificada</i>. Las modificaciones estructurales son aquellas que cambian
     * el tamaño de la enumerar o perturbarlo de tal manera que las iteraciones
     * en el progreso puede arrojar resultados incorrectos.      
     * <p>
     * Este campo es utilizado por el iterador y la implementación del iterador
     * de lista devuelto por los métodos {
     *
     * @ iterator iterator} y {@code listIterator}. Si el valor de este campo
     * cambia inesperadamente, el iterador (o lista iterator) arrojará una
     * {@code ConcurrentModificationException} en respuesta a {@code next},
     * {@code remove}, {@code previous},{@code set} o {@code add} operaciones.
     * Esto proporciona <i>comportamiento a prueba de fallos</i>, en lugar de un
     * comportamiento no determinista en la cara de la modificación concurrente
     * durante la iteración.  
     * <p>
     * <b> El uso de este campo por subclases es opcional.</b> Si una subclase
     * desea proporcionar iteradores a prueba de fallas (y enumerar iteradores),
     * luego simplemente tiene que incrementar este campo en
     * {@code add (int, E)} y {@code remove (int)} métodos (y cualquier otro
     * método que anule que da como resultado modificaciones estructurales a la
     * lista). Una sola llamada a {@code add (int, E)} o {@code remove (int)} no
     * debe agregar más de uno para este campo, o los iteradores (y los
     * iteradores de lista) arrojarán falso
     * {@code ConcurrentModificationExceptions}. Si una implementación no desea
     * proporcionar iteradores a prueba de fallas, este campo puede ser
     * ignorado.      
     */
    protected transient int conteoModulo = 0;

    //Constructor de la clase ListaAbstracta.
    /**
     * Único constructor. (Para invocación por constructores de subclase,
     * típicamente implícito.)      
     */
    protected ListaAbstracta() {

    }

    //Métodos de la clase ListaAbstracta.
    /**
     * Añade el elemento especificado al final de esta lista (opcional
     * operación).      
     * <p>
     * Las listas que admiten esta operación pueden poner limitaciones a lo que
     * los elementos se pueden agregar a esta lista. En particular, algunos las
     * listas rechazarán agregar elementos nulos, y otros impondrán
     * restricciones sobre el tipo de elementos que pueden agregarse. Lista las
     * clases deben especificar claramente en su documentación cualquier
     * restricción sobre qué elementos se pueden agregar.      
     * <p>
     * Esta implementación llama a {@code agregar(tamanio(), elemento)}.      
     * <p>
     * Tenga en cuenta que esta implementación arroja un
     * {@code UnsupportedOperationException} a menos que
     * {@link #agregar(int, Object) agregar(int, E)} se reemplaza.
     *
     * @param elemento elemento que se adjuntará a esta lista
     * @return {@code true} (según lo especificado por
     * {@link Coleccion #agregar})
     * @throws UnsupportedOperationException si la operación {@code agregar} no
     * es compatible con esta lista
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta lista
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * la lista no permite elementos nulos
     * @throws IllegalArgumentException si alguna propiedad de este elemento
     * impide que se agregue a esta lista      
     */
    @Override
    public boolean agregar(E elemento) {
        this.agregar(this.tamanio(), elemento);
        return true;
    }

    /**
     *@inheritDoc}
     * <p>
     * Esta implementación siempre arroja un
     * {@code UnsupportedOperationException}.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public void agregar(int indice, E elemento) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Esta implementación obtiene un iterador sobre la colección especificada y
     * lo itera, insertando los elementos obtenidos del iterador en esta lista
     * en la posición adecuada, uno a la vez, usando {@code agregar(int, E)}.
     * Muchas implementaciones anularán este método por eficiencia.
     * <p>
     * Tenga en cuenta que esta implementación arroja un
     * {@code UnsupportedOperationException} a menos que
     * {@link #agregar(int, Object) agregar (int, E)} se reemplaza.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public boolean agregarTodo(int indice, Coleccion<? extends E> coleccion) {
        this.verificarRangoParaAgregar(indice);
        boolean modificado = false;
        for (E elemento : coleccion) {
            this.agregar(indice++, elemento);
            modificado = true;
        }
        return modificado;
    }

    /**
     * Compara el objeto especificado con esta lista para la igualdad.
     * Devoluciones  {@code true} si y solo si el objeto especificado también es
     * una lista, ambos      * las listas tienen el mismo tamaño y todos los
     * pares de elementos correspondientes en las dos listas son <i>iguales</i>.
     * (Dos elementos {@code e1} y {@code e2} son
     * <i>iguales</i> si {@code (e1 == null? e2 == null:
     *  e1.equals (e2))}.) En otras palabras, se definen dos listas para ser
     *  igual si contienen los mismos elementos en el mismo orden.<p>
     * Esta implementación primero verifica si el objeto especificado es este
     * lista. Si es así, devuelve {@code true}; si no, verifica si el objeto
     * especificado es una lista. Si no, devuelve {@code false}; si es así,
     * itera sobre ambas listas, comparando pares correspondientes de elementos.
     * Si alguna comparación devuelve {@code false}, este método regresa
     * {@code false}. Si cualquiera de los iteradores se queda sin elementos
     * antes del other devuelve {@code false} (ya que las listas son de longitud
     * desigual); de lo contrario, devuelve {@code true} cuando se completan las
     * iteraciones.
     *
     * @param object el objeto que se va a comparar para la igualdad con esta
     * lista
     * @return {@code true} si el objeto especificado es igual a esta lista
     *      
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Lista)) {
            return false;
        }

        ListIterator<E> e1 = listIterator();
        ListIterator<?> e2 = ((Lista<?>) object).listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2))) {
                return false;
            }
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    /**
     * {@inheritDoc}
     * <p>
     * Esta implementación siempre arroja un
     * {@code UnsupportedOperationException}.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E establecer(int indice, E elemento) {
        throw new UnsupportedOperationException();
    }

    /**
     * Devuelve el valor del código hash para esta lista.       *      
     * <p>
     * Esta implementación usa exactamente el código que se usa para definir el
     * enumera la función hash en la documentación del método
     * {@link Lista# hashCode}.
     *
     * @return el valor del código hash para esta lista      
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E elemento : this) {
            hashCode = 31 * hashCode + (elemento == null ? 0
                    : elemento.hashCode());
        }
        return hashCode;
    }

    /**
     * <p>
     * Imprime todos los elementos agregados en la lista.
     *
     * @return los elementos agregados en la lista.
     */
    public abstract String imprimir();

    /**
     * {@inheritDoc}    
     * <p>
     * Esta implementación primero obtiene un iterador de lista (con
     * {@code listIterator()}). Luego, itera sobre la lista hasta que se
     * encuentra el elemento especificado o se llega al final de la lista.
     *
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}      
     */
    @Override
    public int indiceDe(Object objeto) {
        ListIterator<E> listIterador = this.listIterator();
        if (objeto == null) {
            while (listIterador.hasNext()) {
                if (listIterador.next() == null) {
                    return listIterador.previousIndex();
                }
            }
        } else {
            while (listIterador.hasNext()) {
                if (objeto.equals(listIterador.next())) {
                    return listIterador.previousIndex();
                }
            }
        }
        return -1;
    }

    /**
     * Devuelve un iterador sobre los elementos en esta lista en la secuencia
     * correcta. 
     * <p>
     * Esta implementación devuelve una implementación directa del interfaz del
     * iterador, dependiendo de la {@code tamanio()} de la lista de respaldo,
     * métodos. {@code obtener(int)}, y {@code remover(int)}
     *
     * <p>
     * Tenga en cuenta que el iterador devuelto por este método arrojará un
     * {@link UnsupportedOperationException} en respuesta a su {@code remove}
     * método a menos que el método {@code remover(int)} de la lista sea anulado
     * <p>
     * Esta implementación se puede hacer para lanzar excepciones de tiempo de
     * ejecución en cara de la modificación concurrente, como se describe en la
     * especificación para el campo (protegido) {@link #conteoModulo}.
     *
     * @return un iterador sobre los elementos en esta lista en la secuencia
     * correcta      
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Esta implementación devuelve {@code listIterator(0)}.
     *
     *
     * @see #listIterator(int)      
     */
    @Override
    public ListIterator<E> listIterator() {
        return this.listIterator(0);
    }

    /**
     * {@inheritDoc}      
     * <p>
     * Esta implementación devuelve una implementación directa del
     * {@code ListIterator} interfaz que amplía la implementación de la La
     * interfaz de {@code Iterator} devuelta por el método {@code iterator()}.La
     * implementación {@code ListIterator} se basa en la lista de respaldo
     * métodos
     * {@code obtener(int)}, {@code establecer(int, E)}, {@code agregar(int, E)}
     * y {@code remover(int)}.      
     * <p>
     * Tenga en cuenta que el iterador de lista devuelto por esta implementación
     * lanzar una {@link UnsupportedOperationException} en respuesta a sus
     * métodos {@code removre}, {@code establecer} y {@code agregar}a menos que
     * el los métodos de la lista
     * {@code remover(int)}, {@code establecer(int, E)}, y Los métodos
     * {@code agregar(int, E)} se anulan.
     * <p>
     * Esta implementación se puede hacer para lanzar excepciones de tiempo de
     * ejecución en cara de la modificación concurrente, como se describe en la
     * especificación para el campo (protegido) {@link #conteoModulo}.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public ListIterator<E> listIterator(final int indice) {
        this.verificarRangoParaAgregar(indice);
        return new ListItr(indice);
    }

    @Override
    public void limpiar() {
        this.removerRango(0, this.tamanio());
    }

    private String mostrarMensajeFueraDeLosLimites(int indice) {
        return "Índice: " + indice + ", Tamaño: " + this.tamanio();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public abstract E obtener(int indice);

    /**
     * {@inheritDoc}
     * <p>
     * Esta implementación siempre arroja un
     * {@code UnsupportedOperationException}.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}      
     */
    @Override
    public E remover(int indice) {
        throw new UnsupportedOperationException();
    }

    /**
     * Elimina de esta lista todos los elementos cuyo índice está entre
     * {@code desdeIndice}, inclusive, y {@code hastaIndice}, exclusivo. Cambia
     * los elementos sucesivos a la izquierda (reduce su índice). Esta llamada
     * acorta la lista mediante elementos
     * {@code (hastaIndice - desdeIndice)}.(Si
     * {@code hastaIndice == desdeIndice}, esta operación no tiene ningún
     * efecto).      
     * <p>
     * Este método es invocado por la operación {@code clear} en esta lista y
     * sus sublistas. Anulando este método para aprovechar las partes internas
     * de la implementación de la lista pueden<i>
     * sustancialmente</i>
     * mejorar el rendimiento de la operación {@code clear} en esta lista y sus
     * sublistas.      
     * <p>
     * Esta implementación obtiene un iterador de lista posicionado antes
     * {@code desdeIndice}, y llamadas repetidas {@code ListIterator.next}
     * seguido de {@code ListIterator.remove} hasta que todo el rango tenga sido
     * eliminado. <b>Nota: si {@code ListIterator.remove} requiere lineal hora,
     * esta implementación requiere tiempo cuadrático.</b>
     *
     * @param desdeIndice índice del primer elemento a eliminar
     * @param hastaIndice índice después del último elemento a eliminar      
     */
    protected void removerRango(int desdeIndice, int hastaIndice) {
        ListIterator<E> iterator = listIterator(desdeIndice);
        for (int i = 0, n = hastaIndice - desdeIndice; i < n; i++) {
            iterator.next();
            iterator.remove();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * {@inheritDoc}      
     * <p>
     * Esta implementación devuelve una lista de subclases
     * {@code ListaAbstracta}. La subclase almacena, en campos privados,
     * desplazamiento de la sublista dentro de la lista de respaldo, el tamaño
     * de la sublista (que puede cambiar a lo largo de su vida útil) y lo
     * esperado {@code conteoModulo} valor de la lista de respaldo. Hay dos
     * variantes de la subclase, uno de los cuales implementa
     * {@code RandomAccess}. Si esta lista implementa {@code RandomAccess}, la
     * lista devuelta será ser una instancia de la subclase que implementa
     * {@code RandomAccess}.      
     * <p>
     * El {@code establecer(int, E)} de la subclase, todos los métodos null null
     * null null null null null null null null null null null null     {@code obtener(int)},
     * {@code agregar (int, E)}, {@code remover(int)}, {@code agregarTodo(int,
     * Coleccion)} y {@code removerRangg (int, int)} delegar en los métodos
     * correspondientes en la lista de resúmenes de respaldo, después de
     * verificar los límites, verificando el índice y ajustando para el
     * desplazamiento. los El método {@code agregarTodo(Coleccion c)}
     * simplemente devuelve {@code agregarTodo(tamanio,
     * coleccion)}.      
     * <p>
     * El método {@code listIterator (int)} devuelve un "objeto contenedor"
     * sobre un iterador de lista en la lista de respaldo, que se crea con el
     * método correspondiente en la lista de respaldo. El método
     * {@code iterator} simplemente devuelve {@code listIterator()}, y el método
     * {@code tamanio} simplemente devuelve el campo {@code tamanio} de la
     * subclase.
     * <p>
     * Todos los métodos primero verifican si el {@code modCount} real de la
     * lista de respaldo es igual a su valor esperado, y lanza un
     * {@code ConcurrentModificationException} si no lo es. 
     *
     *
     * @throws IndexOutOfBoundsException si un valor de índice de punto final
     * está fuera de rango {@code (desdeIndice <0 || hastaIndice> tamanio)}
     * @throws IllegalArgumentException si los índices de punto final están
     * fuera de servicio {@code (desdeIndice > hastaIndice)}      
     */
    @Override
    public Lista<E> subLista(int desdeIndice, int hastaIndice) {
        return (this instanceof RandomAccess
                ? new SubListaAccesoAleatorio<>(this, desdeIndice, hastaIndice)
                : new SubLista<>(this, desdeIndice, hastaIndice));
    }

    /**
     * {@inheritDoc}  
     * <p>
     * Esta implementación primero obtiene un iterador de lista que apunta al
     * final de la lista (con {@code listIterator (tamanio())}). Luego, itera
     * hacia atrás sobre la lista hasta que se encuentre el elemento
     * especificado, o se alcanza el comienzo de la lista.
     *
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}      
     */
    @Override
    public int ultimoIndiceDe(Object objeto) {
        ListIterator<E> iterador = this.listIterator(this.tamanio());
        if (objeto == null) {
            while (iterador.hasPrevious()) {
                if (iterador.previous() == null) {
                    return iterador.nextIndex();
                }
            }
        } else {
            while (iterador.hasPrevious()) {
                if (objeto.equals(iterador.previous())) {
                    return iterador.nextIndex();
                }
            }
        }
        return -1;
    }

    private void verificarRangoParaAgregar(int indice) {
        if (indice < 0 || indice > this.tamanio()) {
            throw new IndexOutOfBoundsException(
                    this.mostrarMensajeFueraDeLosLimites(indice));
        }
    }

    //Clase interna Itr.
    private class Itr implements Iterator<E> {

        //Atributos de la clase interna Itr.
        /**
         * Índice del elemento que se devolverá mediante la siguiente llamada al
         * siguiente.          
         */
        int cursor = 0;

        /**
         * El valor de conteoModulo que el iterador cree que el respaldo La
         * lista debería tener. Si esta expectativa es violada, el iterador ha
         * detectado una modificación concurrente.          
         */
        int expectedModCount = conteoModulo;

        /**
         * Índice del elemento devuelto por la llamada más reciente a la
         * siguiente o anterior. Restablecer a -1 si un elemento es eliminado
         * por una llamada para eliminar.          
         */
        int lastRet = -1;

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
        public boolean hasNext() {
            return this.cursor != tamanio();
        }

        @Override
        public E next() {
            this.checkForComodification();
            try {
                int i = this.cursor;
                E next = obtener(i);
                this.lastRet = i;
                this.cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException ex) {
                this.checkForComodification();
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }
            this.checkForComodification();

            try {
                ListaAbstracta.this.remover(this.lastRet);
                if (this.lastRet < this.cursor) {
                    this.cursor--;
                }
                this.lastRet = -1;
                this.expectedModCount = conteoModulo;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    //Clase interna ListItr.
    private class ListItr extends Itr implements ListIterator<E> {

        //Constructores de la clase interna ListItr.
        ListItr() {
            this.cursor = 0;
        }

        ListItr(int indice) {
            this.cursor = indice;
        }

        //Métodos de la clase interna ListItr.
        @Override
        public void add(E element) {
            this.checkForComodification();

            try {
                int i = this.cursor;
                ListaAbstracta.this.agregar(i, element);
                this.lastRet = -1;
                this.cursor = i + 1;
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

        @Override
        public E previous() {
            this.checkForComodification();
            try {
                int i = this.cursor - 1;
                E previous = obtener(i);
                this.lastRet = this.cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException ex) {
                this.checkForComodification();
                throw new NoSuchElementException();
            }
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
            this.checkForComodification();

            try {
                ListaAbstracta.this.establecer(this.lastRet, e);
                this.expectedModCount = conteoModulo;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    //Clase interna SubLista.
    class SubLista<E> extends ListaAbstracta<E> {

        //Atributos de la clase interna SubLista.
        private final int compensacion;
        private final ListaAbstracta<E> listaAbstracta;
        private int tamanio;

        //Constructores de la clase interna SubLista.
        SubLista() {
            this.compensacion = 0;
            this.listaAbstracta = null;
            this.tamanio = 0;
        }

        SubLista(ListaAbstracta<E> lista, int desdeIndice, int hastaIndice) {
            if (desdeIndice < 0) {
                throw new IndexOutOfBoundsException("desdeIndice = "
                        + desdeIndice);
            }
            if (hastaIndice > lista.tamanio()) {
                throw new IndexOutOfBoundsException("hastaIndice = "
                        + hastaIndice);
            }
            if (desdeIndice > hastaIndice) {
                throw new IllegalArgumentException("desdeIndice(" + desdeIndice
                        + ") > hastaIndice (" + hastaIndice + ")");
            }
            this.listaAbstracta = lista;
            this.compensacion = desdeIndice;
            this.tamanio = hastaIndice - desdeIndice;
            this.conteoModulo = lista.conteoModulo;
        }

        //Métodos de la clase interna SubLista.
        @Override
        public void agregar(int indice, E elemento) {
            this.verificarRangoParaAgregar(indice);
            this.verificarParaComodificacion();
            this.listaAbstracta.agregar(indice + this.compensacion, elemento);
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
            this.verificarParaComodificacion();
            this.listaAbstracta.agregarTodo(this.compensacion + indice,
                    coleccion);
            this.conteoModulo = this.listaAbstracta.conteoModulo;
            this.tamanio += tamanioColeccion;
            return true;
        }

        @Override
        public E establecer(int indice, E elemento) {
            this.verificarRango(indice);
            this.verificarParaComodificacion();
            return this.listaAbstracta.establecer(indice + this.compensacion,
                    elemento);
        }

        @Override
        public String imprimir() {
            return this.imprimir();
        }

        @Override
        public Iterator<E> iterator() {
            return this.listIterator();
        }

        @Override
        public ListIterator<E> listIterator(final int indice) {
            this.verificarParaComodificacion();
            this.verificarRangoParaAgregar(indice);
            return new ListIterator<E>() {
                /*Clase interna anónima del método listIterator de la 
                clase interna  SubLista.*/

                private final ListIterator<E> listIterator
                        = listaAbstracta.listIterator(indice + compensacion);

                @Override
                public void add(E e) {
                    this.listIterator.add(e);
                    SubLista.this.conteoModulo = listaAbstracta.conteoModulo;
                    tamanio++;
                }

                @Override
                public boolean hasNext() {
                    return this.nextIndex() < tamanio;
                }

                @Override
                public boolean hasPrevious() {
                    return previousIndex() >= 0;
                }

                @Override
                public E next() {
                    if (hasNext()) {
                        return this.listIterator.next();
                    } else {
                        throw new NoSuchElementException();
                    }
                }

                @Override
                public int nextIndex() {
                    return listIterator.nextIndex() - compensacion;
                }

                @Override
                public E previous() {
                    if (hasPrevious()) {
                        return this.listIterator.previous();
                    } else {
                        throw new NoSuchElementException();
                    }
                }

                @Override
                public int previousIndex() {
                    return listIterator.previousIndex() - compensacion;
                }

                @Override
                public void remove() {
                    this.listIterator.remove();
                    SubLista.this.conteoModulo = listaAbstracta.conteoModulo;
                    tamanio--;
                }

                @Override
                public void set(E e) {
                    this.listIterator.set(e);
                }

            };/*Fin de la clase interna anónima del método listIterator de la 
            clase interna SubLista.*/
        }

        private String mostrarMensajeFueraDeLosLimites(int indice) {
            return "Índice: " + indice + ", Tamaño: " + this.tamanio;
        }

        @Override
        public E obtener(int indice) {
            this.verificarRango(indice);
            this.verificarParaComodificacion();
            return this.listaAbstracta.obtener(indice + this.compensacion);
        }

        @Override
        public E remover(int indice) {
            this.verificarRango(indice);
            this.verificarParaComodificacion();
            E resultado = this.listaAbstracta.remover(indice
                    + this.compensacion);
            this.conteoModulo = this.listaAbstracta.conteoModulo;
            this.tamanio--;
            return resultado;
        }

        @Override
        protected void removerRango(int desdeIndice, int hastaIndice) {
            this.verificarParaComodificacion();
            this.listaAbstracta.removerRango(desdeIndice + this.compensacion,
                    hastaIndice + this.compensacion);
            this.conteoModulo = this.listaAbstracta.conteoModulo;
            this.tamanio -= (hastaIndice - desdeIndice);
        }

        @Override
        public Lista<E> subLista(int desdeIndice, int hastaIndice) {
            return new SubLista<>(this, desdeIndice, hastaIndice);
        }

        @Override
        public int tamanio() {
            this.verificarParaComodificacion();
            return this.tamanio;
        }

        private void verificarParaComodificacion() {
            if (this.conteoModulo != this.listaAbstracta.conteoModulo) {
                throw new ConcurrentModificationException();
            }
        }

        private void verificarRango(int indice) {
            if (indice < 0 || indice >= this.tamanio) {
                throw new IndexOutOfBoundsException(
                        mostrarMensajeFueraDeLosLimites(indice));
            }
        }

        private void verificarRangoParaAgregar(int indice) {
            if (indice < 0 || indice > this.tamanio) {
                throw new IndexOutOfBoundsException(
                        mostrarMensajeFueraDeLosLimites(indice));
            }
        }
    }

    //Clase interna SubListaAccesoAleatorio.
    class SubListaAccesoAleatorio<E> extends SubLista<E>
            implements RandomAccess {

        //Constructores de la clase interna SubListaAccesoAleatorio.
        SubListaAccesoAleatorio() {

        }

        SubListaAccesoAleatorio(ListaAbstracta<E> lista, int desdeIndice,
                int hastaIndice) {
            super(lista, desdeIndice, hastaIndice);
        }

        //Método de la clase interna SubListaAccesoAleatorio.
        @Override
        public Lista<E> subLista(int desdeIndice, int hastaIndice) {
            return new SubListaAccesoAleatorio<>(this, desdeIndice,
                    hastaIndice);
        }
    }
}
