package abstr;

import enums.EnumPozice;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author DuckMcQuack
 * @param <T>
 */
public interface IAbstrDoubleList <T> extends Serializable {
    
    /**
     *
     */
    void zrus();

    /**
     *
     * @return
     */
    boolean jePrazdny();
    
    /**
     *
     * @param data
     */
    void vlozPrvni(T data);

    /**
     *
     * @param data
     */
    void vlozPosledni(T data);

    /**
     *
     * @param data
     */
    void vlozNaslednika(T data);

    /**
     *
     * @param data
     */
    void vlozPredchudce(T data);
    
    void vloz(T data, EnumPozice pozice);
    
    /**
     *
     * @return
     */
    T zpristupniAktualni();

    /**
     *
     * @return
     */
    T zpristupniPrvni();

    /**
     *
     * @return
     */
    T zpristupniPosledni();

    /**
     *
     * @return
     */
    T zpristupniNaslednika();

    /**
     *
     * @return
     */
    T zpristupniPredchudce();
    
    /**
     *
     * @return
     */
    T odeberAktualni();

    /**
     *
     * @return
     */
    T odeberPrvni();

    /**
     *
     * @return
     */
    T odeberPosledni();

    /**
     *
     * @return
     */
    T odeberNaslednika();

    /**
     *
     * @return
     */
    T odeberPredchudce();
    
    /**
     *
     * @return
     */
    Iterator<T> iterator();
    
}
