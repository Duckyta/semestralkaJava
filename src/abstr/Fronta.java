/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstr;

import enums.EnumPozice;
import java.util.Iterator;

/**
 *
 * @author DuckMcQuack
 */
public class Fronta<E> {

     private IAbstrDoubleList<E> list;
    public Fronta() {
        list = new AbstrDoubleList<>();
    }
    public void pridej(E data) {
        list.vloz(data, EnumPozice.PRVNI);
    }
    public E zpristupni() {
        return list.zpristupniPosledni();
    }
    public boolean jePrazdny(){
        return list.jePrazdny();
    }
    public E odeber() {
        if (!list.jePrazdny()) {
            return list.odeberPosledni();
        }
        return null;
    }
}
