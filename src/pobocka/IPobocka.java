package pobocka;

import firma.IZamestnanec;
import java.io.Serializable;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author Karel
 */
public interface IPobocka extends Iterable<IZamestnanec>, Serializable, Comparable {

   Adresa getAdresa();

   String getNazev();

   void vlozVedoucihoPobocky(IZamestnanec zamestnanec);

   void vlozVedoucihoOddeleni(IZamestnanec zamestnanec);

   void vloz(IZamestnanec vedouciOddeleni, IZamestnanec zamestnanec);

   IZamestnanec odeber(IZamestnanec zamestnanec);

   IZamestnanec zpristupni(int pozice);

   IZamestnanec zpristupniVedoucihoOddeleni(int oddeleni);

   IZamestnanec zpristupniVedoucihoPobocky();

   IZamestnanec[] zpristupniSeznamVedoucichOddeleni();

   IZamestnanec[] zpristupniSeznamZamestnancuOddeleni(IZamestnanec vedouciOddeleni);
   
   int getPocetZamestnancu();
   
   /**
    * Vrací sekvenci v {@code Stream} datové elementy stromu.
    *
    * @return
    */
   default Stream<IZamestnanec> stream() {
      return StreamSupport.stream(spliterator(), false);
   }
}
