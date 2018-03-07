/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colecciones;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Interfaz Coleccion para guardar y manipular elementos en una coleción.
 */
/**
 * La interfaz raíz en la <i>jerarquía de colecciones</i>. Una colección
 * representa un grupo de objetos, conocidos como sus <i>elementos</i>. Algunas
 * colecciones permiten elementos duplicados y otros no. Algunos están ordenados
 * y otros desordenados. El JDK no proporciona ningún<i>
 * directo</i>
 * implementaciones de esta interfaz: proporciona implementaciones de más
 * subinterfaces específicas como <tt>Establecer</tt> y <tt>Lista</tt>. Esta
 * interfaz se usa generalmente para pasar colecciones y manipularlas donde se
 * desea la máxima generalidad.  
 * <p>
 * <i>Las bolsas</i> o <i>multisedes</i> (colecciones desordenadas que pueden
 * contener elementos duplicados) debe implementar esta interfaz directamente.  
 * <p>
 * Todas las clases de implementación de <tt>Coleccion</tt> de uso general (que
 * normalmente implementa <tt>Coleccion</tt> indirectamente a través de uno de
 * sus  subinterfaces) debería proporcionar dos constructores "estándar": un
 * vacío (no argumentos) constructor, que crea una colección vacía, y una
 * constructor con un único argumento de tipo <tt>Collection
 * </tt> ,que crea una nueva colección con los mismos elementos que su
 * argumento. En efecto, este último constructor permite al usuario copiar
 * cualquier colección, produciendo una colección equivalente del tipo de
 * implementación deseado. No hay forma de hacer cumplir esta convención (ya que
 * las interfaces no pueden contener constructores) pero toda la <tt>
 * colección</tt> de propósito general las implementaciones en las bibliotecas
 * de la plataforma Java cumplen.  
 * <p>
 * Los métodos "destructivos" contenidos en esta interfaz, es decir, el los
 * métodos que modifican la colección en la cual operan, están especificados
 * para throw <tt>UnsupportedOperationException</tt> si esta colección no apoyar
 * la operación. Si este es el caso, estos métodos pueden, pero no son requerido
 * para, lanzar una <tt>UnsupportedOperationException</tt> si el la invocación
 * no tendría ningún efecto en la colección. Por ejemplo, invocar el método
 * {@link #addAll (Collection)} en una colección no modificable puede, pero no
 * es obligatorio, lanzar la excepción si la colección se agregará esta vacio.  
 * <p>
 * <a name="optional-restrictions">
 * Algunas implementaciones de colecciones tienen restricciones sobre los
 * elementos que pueden contener.</a> Por ejemplo, algunas implementaciones
 * prohíben elementos nulos, y algunos tienen restricciones sobre los tipos de
 * sus elementos. Intentando agregar un elemento inelegible arroja una excepción
 * no verificada, por lo general <tt>NullPointerException</tt> o
 * <tt>ClassCastException</tt>. Intentando para consultar la presencia de un
 * elemento inelegible puede arrojar una excepción,  o simplemente puede
 * devolver falso; algunas implementaciones exhibirán el anterior comportamiento
 * y algunos exhibirán el último. De manera más general, intentando un operación
 * en un elemento inelegible cuya finalización no resultaría en la inserción de
 * un elemento inelegible en la colección puede arrojar excepción o puede tener
 * éxito, a elección de la implementación. Tales excepciones se marcan como
 * "opcionales" en la especificación para este interfaz.
 * <p>
 * Depende de cada colección determinar su propia sincronización política. A
 * falta de una garantía más sólida por parte del implementación, comportamiento
 * indefinido puede resultar de la invocación de cualquier método en una
 * colección que está siendo mutada por otro hilo; esto incluye invocaciones
 * directas, pasar la colección a un método que podría realizar invocaciones, y
 * el uso de una existente iterador para examinar la colección.  
 * <p>
 * Muchos métodos en las interfaces del Framework de Colecciones se definen en
 * los términos del método {@link Object # equals (Object) es igual a} method.
 * Por ejemplo, la especificación para
 * {@link #contains (Object) contains (Object objeto)} el método dice: "devuelve
 * <tt>true</tt> si y solo si esta colección contiene al menos un elemento
 * <tt>e</tt> tal que <tt>(objeto == null? elemento == null: objeto.equals
 * (elemento))
 * </tt>."Esta especificación debería <i>no</i> se interpretará como que implica
 * que la invocación de<tt>Colleccion.contiene</tt>
 * con un argumento no nulo<tt>o</tt> hará que <tt>objeto.equals(elemento)</tt>
 * sea invocado para cualquier elemento <tt>elemento</tt>.Las implementaciones
 * son libres de implementar se evitan las optimizaciones por las cuales se usa
 * la invocación <tt>igual a</tt>, para ejemplo, primero comparando los códigos
 * hash de los dos elementos. (Los {@link Object # hashCode()} especificación
 * garantiza que dos objetos con los códigos hash desiguales no pueden ser
 * iguales). De manera más general, las implementaciones de las diversas
 * interfaces del Framework Collections son gratuitas para aprovechar el
 * comportamiento especificado de los métodos subyacentes {@link Object}
 * dondequiera que el el implementador lo considera apropiado.  
 * <p>
 * Algunas operaciones de recopilación que realizan un recorrido recursivo de la
 * recopilación puede fallar con una excepción para instancias
 * autorreferenciales donde la colección se contiene directa o indirectamente.
 * Esto incluye el {@code clone ()}, {@code equals ()}, {@code hashCode ()} y
 * {@code toString ()} métodos. Las implementaciones pueden manejar
 * opcionalmente el escenario autorreferencial, Sin embargo, la mayoría de las
 * implementaciones actuales no lo hacen.  
 * <p>
 * Esta interfaz es miembro de
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.  
 *
 * @implSpec Las implementaciones de métodos por defecto (heredadas o no) no
 * aplican ninguna protocolo de sincronización. Si la implementación
 * {@code Collection} tiene un protocolo de sincronización específico, entonces
 * debe anular el predeterminado implementaciones para aplicar ese protocolo.
 *
 * @since 1.8
 * @author FABAME
 * @see Lista
 * @param <E> el tipo de elementos contenidos en esta colección
 */
public interface Coleccion<E> extends Iterable<E> {

    //Métodos de la interfaz Coleccion.
    /**
     * Asegura que esta colección contiene el elemento especificado (opcional
     * operación). Devuelve <tt>true</tt> si esta colección ha cambiado como
     * resultado de la llamada. (Devuelve <tt>false</tt> si esta colección no no
     * permite duplicados y ya contiene el elemento especificado.)
     * <p>
     * Las colecciones que respaldan esta operación pueden poner limitaciones a
     * lo que los elementos se pueden agregar a esta colección. En particular,
     * algunos colecciones se negarán a agregar elementos <tt>null</tt> , y
     * otros lo harán  imponer restricciones sobre el tipo de elementos que
     * pueden agregarse. Las clases de colección deben especificar claramente en
     * su documentación restricciones sobre qué elementos se pueden agregar.<p>
     * Si una colección se niega a agregar un elemento en particular por alguna
     * razón aparte de que ya contiene el elemento,
     * <i>debe</i> lanzar una excepción (en lugar de devolver
     * <tt>false</tt>). Esto conserva la invariante de que una colección siempre
     * contiene el elemento especificado después de que esta llamada regrese.
     *
     * @param elemento elemento cuya presencia en esta colección debe
     * garantizarse     
     * @return <tt>true</tt> si esta colección ha cambiado como resultado de la
     * llamada
     * @throws UnsupportedOperationException si la operación <tt>agregar</tt> no
     * es compatible con esta colección
     * @throws ClassCastException si la clase del elemento especificado impide
     * que se agregue a esta colección @throws NullPointerException si el
     * elemento especificado es nulo y esto la colección no permite elementos
     * nulos
     * @throws IllegalArgumentException si alguna propiedad del elemento impide
     * que se agregue a esta colección
     * @throws IllegalStateException si el elemento no se puede agregar en este
     * tiempo debido a restricciones de inserción      
     */
    public boolean agregar(E elemento);

    /**
     * Agrega todos los elementos de la colección especificada a esta colección
     * (operación opcional). El comportamiento de esta operación no está
     * definido si la colección especificada se modifica mientras la operación
     * está en progreso. (Esto implica que el comportamiento de esta llamada no
     * está definido si colección especificada es esta colección, y esta
     * colección es no vacío.)
     *
     * @param coleccion colección que contiene elementos para agregar a esta
     * colección
     * @return <tt>true</tt> si esta colección ha cambiado como resultado de la
     * llamada
     * @throws UnsupportedOperationException si la operación
     * <tt>agregarTodo</tt> no es compatible con esta colección
     * @throws ClassCastException si la clase de un elemento de la especificada
     * la colección impide que se agregue a esta colección
     * @throws NullPointerException si la colección especificada contiene una
     * elemento nulo y esta colección no permite elementos nulos, o si la
     * colección especificada es nula
     * @throws IllegalArgumentException si alguna propiedad de un elemento del
     * la colección especificada impide que se agregue a este colección
     * @throws IllegalStateException si no todos los elementos se pueden agregar
     * a esta vez debido a restricciones de inserción      
     * @see #agregar(Object)
     */
    public boolean agregarTodo(Coleccion<? extends E> coleccion);

    /**
     * Conserva solo los elementos de esta colección que están contenidos en
     * colección especificada (operación opcional). En otras palabras, elimina
     * de esta colección, todos sus elementos que no están contenidos en
     * colección especificada.
     *
     * @param coleccion colección que contiene elementos para ser retenidos en
     * esta colección
     * @return <tt>true</tt> si esta colección ha cambiado como resultado de la
     * llamada
     * @throws UnsupportedOperationException si la operación <tt>
     * conservarTodo</tt> no es compatible con esta colección
     * @throws ClassCastException si los tipos de uno o más elementos en esta
     * colección son incompatibles con la colección especificada
     * (<a href="#optional-restrictions"> opcional</a>)
     * @throws NullPointerException si esta colección contiene uno o más
     * elementos nulos y la colección especificada no permite nulo elementos
     * (<a href="#optional-restrictions"> opcional</a>), o si la colección
     * especificada es nula @see #remover (Object)
     * @see #contiene (Object)      
     */
    public boolean conservarTodo(Coleccion<?> coleccion);

    /**
     * Devuelve <tt>true</tt> si esta colección contiene el elemento
     * especificadoMás formalmente, devuelve <tt>true</tt> si y solo si esta
     * colección contiene al menos un elemento <tt>e</tt> tal que <tt>(objeto ==
     * null & nbsp;? & nbsp; e == null & nbsp;: & nbsp; objeto.equals (e))
     * </tt>.
     *
     * @param objeto elemento cuya presencia en esta colección debe probarse
     * @return <tt>true</tt> si esta colección contiene el especificado elemento
     * @throws ClassCastException si el tipo del elemento especificado es
     * incompatible con esta colección (<a href="#optional-restrictions">
     * opcional</a>)
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * la colección no permite elementos nulos
     * (<a href="#optional-restrictions"> opcional</a> )      
     *
     */
    public boolean contiene(Object objeto);

    /**
     * Devuelve <tt>true</tt> si esta colección contiene todos los elementos en
     * la colección especificada.
     *
     * @param coleccion colección para verificar la contención en esta colección
     * @return<tt>true</tt> si esta colección contiene todos los elementos en la
     * colección especificada
     * @throws ClassCastException si los tipos de uno o más elementos en la
     * colección especificada son incompatibles con esta colección
     * (<a href="#optional-restrictions"> opcional</a>)
     * @throws NullPointerException si la colección especificada contiene uno o
     * más elementos nulos y esta colección no permite null elementos
     * (<a href="#optional-restrictions"> opcional</a>), o si la colección
     * especificada es nula.
     * @see #contiene(Object)      
     */
    public boolean contieneTodo(Coleccion<?> coleccion);

    /**
     * Devuelve <tt>true</tt> si esta colección no contiene elementos.
     *
     * @return verdadero si esta colección no contiene elementos.
     */
    public boolean estaVacia();

    /**
     * Devuelve un {@code Stream} secuencial con esta colección como fuente. 
     * <p>
     * Este método debe ser anulado cuando {@link #spliterator()} el método no
     * puede devolver un spliterator que sea {@code IMMUTABLE},      
     * {@code CONCURRENT}, o <em>último enlace</em>. (Ver {@link #spliterator()}
     * para detalles.)
     *
     * @implSpec La implementación predeterminada crea un {@code Stream}
     * secuencial del colección {@code Spliterator}.
     * @return una {@code Stream} secuencial sobre los elementos de esta
     * colección
     * @since 1.8      
     */
    public default Stream<E> flujo() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    /**
     * Devuelve un {@code Stream} posiblemente paralelo con esta colección como
     * su fuente. Se permite que este método devuelva un flujo secuencial.
     * <p>
     * Este método debe ser anulado cuando {@link #spliterator()} el método no
     * puede devolver un spliterator que sea {@code IMMUTABLE},      
     * {@code CONCURRENT}, o <em>último enlace</em>. (Ver {@link #spliterator()}
     * para detalles.)
     *
     * @implSpec  La implementación predeterminada crea un {@code Stream}
     * paralelo desde el colección {@code Spliterator}.
     * @return una {@code Stream} posiblemente paralela sobre los elementos en
     * esta colección
     * @since 1.8      
     */
    public default Stream<E> flujoParalelo() {
        return StreamSupport.stream(this.spliterator(), true);
    }

    /**
     * Compara el objeto especificado con esta colección para la igualdad.<p>
     * Mientras que la interfaz <tt>Coleccion</tt> no agrega ninguna
     * estipulación al contrato general para los
     * <tt>Object.equals</tt>, programadores que implementar la interfaz
     * <tt>Colleccion</tt> "directamente" (en otras palabras, crea una clase que
     * es una <tt>Colección</tt> pero no es un <tt>conjunto</tt>
     * o una <tt>Lista</tt>) deben tener cuidado si eligen anular el
     * <tt>Object.equals</tt>. No es necesario hacerlo, y el más simple el curso
     * de acción es confiar en la implementación de
     * <tt>Object</tt>, pero el implementador puede desear implementar una
     * "comparación de valores" en lugar de la "comparación de referencia"
     * predeterminada. (La <tt> Lista</tt> y <tt>Las interfaces</tt> imponen
     * tales comparaciones de valores.)
     * <p>
     *  El contrato general para el método <tt>Object.equals</tt>
     * establece que equals debe ser simétrico (en otras palabras,
     * <tt>a.equals (b)</tt> if y solo si <tt>b.equals (a)</tt>). Los contratos
     * para <tt>List.equals</tt>
     * y <tt>Set.equals</tt> indican que las listas solo son iguales a otras
     * listas, y establece en otros conjuntos. Por lo tanto, un método
     * personalizado <tt>es igual a</tt> para un clase de colección que no
     * implementa ni la <tt>Lista</tt> ni <tt>La interfaz</tt> debe devolver
     * <tt>false</tt> cuando esta colección se compara con cualquier lista o
     * conjunto. (Por la misma lógica, no es posible para escribir una clase que
     * implemente correctamente tanto el <tt>Set</tt> como
     * <tt>Interfaces</tt>.)
     *
     * @param object objeto a comparar por igualdad con esta colección
     * @return <tt>true</tt> si el objeto especificado es igual a este colección
     * @see Object #equals(Object) see Set #equals(Object)
     * @see Lista #equals (Object)      
     */
    @Override
    public boolean equals(Object object);

    /**
     * Devuelve el valor del código hash para esta colección. Mientras que la
     * interfaz <tt>Coleccion</tt> no agrega ninguna estipulación al general
     * contrato para el método
     * <tt>Object.hashCode</tt> , los programadores deberían tenga en cuenta que
     * cualquier clase que anule el <tt>Object.equals</tt>
     * El método también debe anular el método <tt>Object.hashCode</tt>
     * en orden  para satisfacer el contrato general para el método
     * <tt>Object.hashCode</tt>. En particular, <tt>c1.equals(c2)</tt>
     * implica que <tt>c1.hashCode() == c2.hashCode()</tt> .
     *
     * @return el valor del código hash para esta colección
     * @see Object #hashCode()
     * @see Object #equals(Object)      
     */
    @Override
    public int hashCode();

    /**
     * Devuelve un iterador sobre los elementos en esta colección. No
     * existengarantías sobre el orden en que se devuelven los elementos (a
     * menos que esta colección sea una instancia de alguna clase que
     * proporcione garantía).
     *
     * @return un <tt>Iterator</tt> sobre los elementos de esta colección      
     */
    @Override
    public Iterator<E> iterator();

    /**
     * Elimina todos los elementos de esta colección (operación opcional). La
     * colección estará vacía después de que este método regrese.
     *
     * @throws UnsupportedOperationException si la operación <tt>limpiar</tt> no
     * es compatible con esta colección      
     */
    public void limpiar();

    /**
     * Devuelve un arreglo que contiene todos los elementos de esta colección.
     * Si esta colección hace alguna garantía en cuanto a qué orden sus
     * elementos son devueltos por su iterador, este método debe devolver los
     * elementos en el mismo orden   
     * <p>
     * El arreglo devuelto será "seguro" porque no hay referencias a el
     * mantenido por esta colección. (En otras palabras, este método debe      
     * asigna un nuevo arreglo incluso si esta colección está respaldada por una
     * matriz). La persona que llama es libre de modificar el arreglo
     * devuelto.    
     * <p>
     * Este método actúa como puente entre la basada en matrices y la basada en
     * colecciones APIs.
     *
     * @return un arreglo que contiene todos los elementos de esta colección
     *      
     */
    public Object[] paraFormar();

    /**
     * Devuelve un arreglo que contiene todos los elementos de esta colección;
     * el tipo de tiempo de ejecución del arreglo devuelta es el del arreglo
     * especificado. Si la colección se ajusta al arreglo especificado, se
     * devuelve allí. De lo contrario, se asigna un nuevo arreglo con el tipo de
     * tiempo de ejecución de la matriz especificada y el tamaño de esta
     * colección.      
     * <p>
     * Si esta colección se ajusta al arreglo especificado con espacio de sobra
     * (es decir, el arreglo tiene más elementos que esta colección), el
     * elemento      * en el conjunto inmediatamente posterior al final de la
     * colección está configurado para <tt>null</tt>. (Esto es útil para
     * determinar la longitud de este colección <i>solamente</i> si la persona
     * que llama sabe que esta colección no contiene ningún elemento
     * <tt>null</tt>).      
     * <p>
     * Si esta colección hace alguna garantía en cuanto a qué orden sus
     * elementos son devueltos por su iterador, este método debe devolver los
     * elementos en el mismo orden      
     * <p>
     * Al igual que el método {@link #paraFormar()}, este método actúa como
     * puente entre API basada en colección y basada en colección. Además, este
     * método permite control preciso sobre el tipo de tiempo de ejecución del
     * arreglo de salida, y puede, bajo ciertas circunstancias, se puede usar
     * para ahorrar costos de asignación.      
     * <p>
     * Supongamos que <tt>x</tt> es una colección conocida por contener solo
     * cadenas. El siguiente código se puede utilizar para volcar la colección
     * en un nuevo arreglo asignado de <tt>String</tt>:      
     * <pre>
     * String [] y = x.paraFormar(new String [0]);</pre> Tenga en cuenta que
     * <tt>paraFormar(new Object [0])</tt> es idéntico en función a <tt>
     * paraFormar()</tt> .
     *
     * @param <T> el tipo de tiempo de ejecución del arreglo para contener la
     * colección
     * @param arreglo el arreglo en el que se van a usar los elementos de esta
     * colección  almacenado, si es lo suficientemente grande; de lo contrario,
     * un nuevo arreglo de la misma el tipo de tiempo de ejecución se asigna
     * para este propósito.
     * @return un arreglo que contiene todos los elementos de esta colección
     * @throws ArrayStoreException si el tipo de tiempo de ejecución del arreglo
     * especificado no es un supertipo del tipo de tiempo de ejecución de cada
     * elemento en esta colección
     * @throws NullPointerException si el arreglo especificado es nula 
     */
    public <T> T[] paraFormar(T[] arreglo);

    /**
     * Elimina una sola instancia del elemento especificado de este colección,
     * si está presente (operación opcional). Más formalmente, elimina un
     * elemento <tt>e</tt> tal que <tt>(objeto == null & nbsp;? & nbsp; elemento
     * == null & nbsp;: & nbsp; objeto.equals (e))</tt>, si esta colección
     * contiene uno o más de tales elementos. Devoluciones <tt>true</tt> si esta
     * colección contenía el elemento especificado (o de manera equivalente, si
     * esta colección cambió como resultado de la llamada).
     *
     * @param objeto elemento que se eliminará de esta colección, si está
     * presente
     * @return <tt>true</tt> si se eliminó un elemento como resultado de esta
     * llamada
     * @throws ClassCastException si el tipo del elemento especificado es
     * incompatible con esta colección (<a href="#optional-restrictions">
     * opcional</a>)      
     * @throws NullPointerException si el elemento especificado es nulo y esto
     * la colección no permite elementos nulos
     * (<a href="#optional-restrictions"> opcional</a>)
     * @throws UnsupportedOperationException si la operación <tt>remover</tt>
     * no es compatible con esta colección
     */
    public boolean remover(Object objeto);

    /**
     * Elimina todos los elementos de esta colección que satisfacen los
     * requisitos predicado. Errores o excepciones de tiempo de ejecución
     * lanzadas durante la iteración o por el predicado se transmite a la
     * persona que llama.
     *
     * @implSpec La implementación predeterminada atraviesa todos los elementos
     * de la colección usando es {@link #iterator}. Cada elemento coincidente se
     * elimina usando {@link Iterator#remove()}. Si el iterador de la colección
     * no la eliminación de soporte, entonces una
     * {@code UnsupportedOperationException} será lanzado en el primer elemento
     * coincidente.
     * @param filtro un predicado que devuelve {@code true} para que los
     * elementos sean eliminado      
     *
     * @return {@code true} si se eliminaron elementos
     * @throws NullPointerException si el filtro especificado es nulo
     * @throws UnsupportedOperationException si los elementos no se pueden
     * eliminar de esta colección. Las implementaciones pueden arrojar esta
     * excepción si el elemento coincidente no puede eliminarse o si, en
     * general, la eliminación no es soportado.
     *
     * @since 1.8      
     */
    public default boolean removerSi(Predicate<? super E> filtro) {
        Objects.requireNonNull(filtro);
        boolean removido = false;
        final Iterator<E> cada = iterator();
        while (cada.hasNext()) {
            if (filtro.test(cada.next())) {
                cada.remove();
                removido = true;
            }
        }
        return removido;
    }

    /**
     * Elimina todos los elementos de esta colección que también están
     * contenidos en colección especificada (operación opcional). Después de que
     * esta llamada regrese, esta colección no contendrá ningún elemento en
     * común con el especificado colección.
     *
     * @param coleccion colección que contiene elementos que se eliminarán de
     * esta colección
     * @return <tt>true</tt> si esta colección ha cambiado como resultado de la
     * llamada
     * @throws UnsupportedOperationException si el método <tt>removerTodo</tt>
     * no es compatible con esta colección
     * @throws ClassCastException si los tipos de uno o más elementos en esta
     * colección son incompatibles con lo especificado colección
     * (<a href="#optional-restrictions"> opcional</a>)
     * @throws NullPointerException si esta colección contiene uno o más
     * elementos nulos y la colección especificada no es compatible elementos
     * nulos (<a href="#optional-restrictions"> opcional</a>), o si la colección
     * especificada es nula
     * @see #remover(Object)
     * @see #contiene(Object)
     */
    public boolean removerTodo(Coleccion<?> coleccion);

    /**
     * Crea un {@link Spliterator} sobre los elementos en esta colección. Las
     * implementaciones deben documentar los valores característicos informados
     * por el spliterator. Tales valores característicos no requieren ser
     * reportados si el spliterator informa {@link Spliterator # SIZED} y esta
     * colección no contiene elementos      
     * <p>
     * La implementación predeterminada debe ser reemplazada por subclases que
     * puede devolver un spliterator más eficiente. A fin de que preservar el
     * comportamiento de pereza esperado para {@link #stream ()} y
     * {@link #parallelStream ()}} métodos, los spliterators deben tener el
     * característica de {@code IMMUTABLE} o {@code CONCURRENT}, o sea
     * <em> <a href="Spliterator.html#binding"> enlace tardío</a> </em>.  Si
     * ninguno de estos es práctico, la clase principal debe describir el
     * política documentada del spliterator de interferencia estructural y de
     * enlace y debe anular el {@link #stream ()} y {@link #parallelStream ()}
     * métodos para crear secuencias usando un {@code Supplier} del spliterator,
     * como en:      
     * <pre>{@code
     *  Stream <E> s = StreamSupport.stream (() -> spliterator (), spliteratorCharacteristics)
     * }</pre>      
     * <p>
     * Estos requisitos garantizan que los flujos producidos por los métodos
     * {@link #flujo()} y {@link #parallelStream()} reflejarán el contenido de
     * la colección desde el inicio de la transmisión de la terminal operación.
     *   
     *
     * @implSpec       La implementación predeterminada crea un
     * <em><a href="Spliterator.html#binding"> enlace tardío </a> </em>
     * spliterator de {@code Iterator} de las colecciones. El spliterator hereda
     * <em>fail-fast</em> propiedades del iterador de la colección.      
     * <p>
     * Los informes {@link Spliterator#SIZED}. creados {@code Spliterator}
     *
     * @implNote El {@code Spliterator} creado también informa
     * {@link Spliterator # SUBSIZED}.      
     * <p>
     * Si un spliterator no cubre elementos, entonces el informe de valores
     * característicos, más allá de {@code SIZED} y {@code SUBSIZED}, no ayuda a
     * los clientes a controlar, especializar o simplificar el cálculo. Sin
     * embargo, esto permite el uso compartido de un espacio inmóvil y vacío
     * instancia de spliterator (consulte
     * {@link Spliterators # emptySpliterator ()}) para Colecciones vacías, y
     * permite a los clientes determinar si dicho spliterator no cubre
     * elementos.
     * @return un {@code Spliterator} sobre los elementos de esta colección
     * @since 1.8
     */
    @Override
    public default Spliterator<E> spliterator() {
        Collection<E> collection = (Collection<E>) this;
        return Spliterators.spliterator(collection, 0);
    }

    /**
     * Devuelve la cantidad de elementos en esta colección. Si esta colección
     * contiene más elementos <tt>Integer.MAX_VALUE</tt>, devoluciones
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return el número de elementos en esta colección
     */
    public int tamanio();

}
