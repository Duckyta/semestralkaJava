package firma;


import java.io.Serializable;
import pobocka.IPobocka;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author Karel
 */
public interface IFirma extends Iterable<IPobocka>, Serializable {

  

   IPobocka najdi(String nazev);

   IPobocka odeber(String nazev);

   void vloz(String nazev, IPobocka pobocka);
   
   String getNazevFirmy();
   
   void zrus();
   
   public int getPocet();
   /**
    * Vrací sekvenci v {@code Stream} datové elementy stromu.
    *
    * @return
    */
   default Stream<IPobocka> stream() {
      return StreamSupport.stream(spliterator(), false);
   }
}
