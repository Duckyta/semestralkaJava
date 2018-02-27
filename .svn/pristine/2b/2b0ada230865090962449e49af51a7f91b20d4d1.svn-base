package abstr.Tree;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author Karel
 * @param <E>
 */
public interface IAbstrTree <E> extends Iterable<E>, Serializable {
    
   /**
    * Zrušení celého stromu od listů.
    */
   void zrus();

   /**
    * Test prázdnosti stromu
    *
    * @return Vrací <tt>true</tt>, když je strom prázdný
    */
   boolean jePrazdny();

   /**
    * Počet prvků stromu
    *
    * @return Počet všech aktuálně vložených prvků v stromu
    */
   int mohutnost();

   /**
    * Vložení datového elementu, tj. „inicializačního“ prvku (kořene), do
    * stromu.
    *
    * @param element
    *
    * @throws NullPointerException Když je datový element <tt>null</tt>.
     * @throws abstr.Tree.AbstrTreeException
    *
    * @throws kolekce.AbstrTreeException Když strom již obsahuje kořen. Musí se
    * celý strom zrušit metodou <tt>zrus</tt>nebo se kořen přímo odebere metodu
    * <tt>odeberKoren</tt>
    */
   void vlozKoren(E element) throws NullPointerException, AbstrTreeException;

   /**
    * Vložení datového elementu jako listu do stromu, tj. jako syna aktivniho
    * prvku.
    *
    * Metoda nenastavuje aktuální prvek stromu na kořen stromu.
    *
    * @param element
    *
    * @throws NullPointerException Když je datový element <tt>null</tt>.
    *
    * @throws kolekce.AbstrTreeException Když strom nemá kořen, tj. že je
    * prázdný nebo není nastaven/zpřístupněn aktuální element stromu.
    */
   void vlozList(E element) throws NullPointerException, AbstrTreeException;

   /**
    * Odebrání kořene (pouze když obsahuje jen kořen)
    *
    * @return
    *
    * @throws kolekce.AbstrTreeException Když je strom prázdný nebo když kořen
    * není list.
    */
   E odeberKoren() throws AbstrTreeException;

   
   /**
    * Odebrání „specifikovaného“ listu akt. prvku
    *
    * @param pozice
    *
    * @return
    *
    * @throws kolekce.AbstrTreeException Když element není list nebo když pozice
    * je mimo seznam sourozenců.
    */
   E odeberList(int pozice) throws AbstrTreeException;

   /**
    * Zpřístupnění kořene stromu a nastaví kořen jako aktuální element stromu.
    *
    * @return
    *
    * @throws kolekce.AbstrTreeException Když je strom prázdný.
    */
   E zpristupniKoren() throws AbstrTreeException;

   /**
    * Zpřístupnění specifikovaného syna aktivního datového prvku a posune
    * aktivní uzel na syna.
    *
    * @param pozice
    *
    * @return
    *
    * @throws kolekce.AbstrTreeException
    */
   E zpristupniSyna(int pozice) throws AbstrTreeException;

   /**
    * Pouze zpřístupnění specifikovaného syna aktivního datového prvku bez změny
    * aktivního prvku stromu.
    *
    * @param pozice
    *
    * @return
    *
    * @throws kolekce.AbstrTreeException
    */
   E dejSyna(int pozice) throws AbstrTreeException;
   
   
   /**
    * Pocet synu v aktuálním uzlu/prvku  stromu.
    *
    *
    * @return  
    * 
    * @throws kolekce.AbstrTreeException    
    */
   int  dejPocetSynu() throws AbstrTreeException;
   
    /**
    * Test zda aktualni uzel stromu má na pozici list.
    *
    * @param position
    * 
    * @return Vrací <tt>true</tt>, když je uzel listem.
    * 
    * @throws kolekce.AbstrTreeException Když neni nastaven aktualní uzel stromu.
    */
   boolean jeSynList(int position) throws AbstrTreeException;
   
    /**
    * Test zda aktualni uzel stromu je listem.
    *
    * @return Vrací <tt>true</tt>, když je uzel listem.
    * 
    * @throws kolekce.AbstrTreeException Když neni nastaven aktualní uzel stromu.
    */
   boolean jeList() throws AbstrTreeException;
   
   /**
    * *
    * Iterator vytvoří iterátor, který umožňuje procházení stromu do šířky
    *
    */
   @Override
   Iterator<E> iterator();

   /**
    * Vrací sekvenci v {@code Stream} datové elementy stromu.
    *
    * @return
    */
   default Stream<E> stream() {
      return StreamSupport.stream(spliterator(), false);
   }
    
    
}
