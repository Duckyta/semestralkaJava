package abstr.zasobnik;

import java.io.Serializable;
import abstr.AbstrDoubleList;

/**
 *
 * @author DuckMcQuack
 */
public class Zasobnik<E> implements IZasobnik<E>, Serializable{
   private AbstrDoubleList<E> zasobnik;

    public Zasobnik() {
        zasobnik = new AbstrDoubleList<>();
    }


    @Override
    public boolean jePrazdny() {
        return zasobnik.jePrazdny();
    }

    @Override
    public E odeber() {
        return zasobnik.odeberPosledni();
        

    }

    @Override
    public void vloz(E element) {
        zasobnik.vlozPosledni(element);
    }

    @Override
    public void zrus() {
        zasobnik.zrus();

    }

}

