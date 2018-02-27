package abstr.Table;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author Karel
 * @param <K>
 * @param <V>
 */
public interface IAbstrTable<K extends Comparable<K>, V> extends Iterable<V> {

   /**
    * void zrus()	zrušení celé tabulky boolean
    *
    */
   public void zrus();

   /**
    * jePrazdny()	test prázdnosti tabulky
    *
    * @return
    */
   public boolean jePrazdny();

   /**
    * V najdi(K key)	vyhledá prvek dle klíče
    *
    * @param key
    *
    * @return
    *
    * @throws kolekce.AbstrTableException
    */
   public V najdi(K key) throws AbstrTableException;

   /**
    * void vloz(K key, V value)	vloží prvek do tabulky
    *
    * @param key
    *
    * @param value
    *
    * @throws kolekce.AbstrTableException
    */
   public void vloz(K key, V value) throws AbstrTableException;

   /**
    * V odeber(K key)	odebere prvek dle klíče z tabulky
    *
    * @param key
    * 
    * @return 
    * 
    * @throws kolekce.AbstrTableException
    */
   public V odeber(K key) throws AbstrTableException;

   /**
    * Iterator vytvorIterator() vytvoří iterátor, který umožňuje procházení
    * tabulky
    */
   
   @Override
   Iterator<V> iterator();

   /**
    * Vrací sekvenci v {@code Stream} datové elementy stromu.
    *
    * @return
    */
   default Stream<V> stream() {
      return StreamSupport.stream(spliterator(), false);
   }
   
   public int getPocetZaznamu();
}
