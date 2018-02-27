package pomocne;

import abstr.Heap.AbstrHeap;
import firma.IFirma;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Objects;
import komparatory.KomparatorCislo;
import komparatory.KomparatorNazev;
import pobocka.IPobocka;

/**
 *
 * @author DuckMcQuack
 */
public class Soubory {

    public static IFirma nactiSerialize(String jmenoSouboru, IFirma firma) throws IOException {

        try {
            ObjectInputStream in;
            try (FileInputStream fileIn = new FileInputStream(jmenoSouboru)) {
                in = new ObjectInputStream(fileIn);
                firma.zrus();
                int pocetZaznamu = in.readInt();

                for (int i = 0; i < pocetZaznamu; i++) {
                    IPobocka pom = (IPobocka) in.readObject();
                    firma.vloz(pom.getNazev(), pom);
                }

            }
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException(ex);
        }
        return firma;
    }

    public static void uloz(String jmenoSouboru, IFirma firma) throws IOException {
        try {
            Objects.requireNonNull(firma);
            try (FileOutputStream fileOut = new FileOutputStream(jmenoSouboru);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

                Iterator<IPobocka> it = firma.iterator();
                
                int pocet = 0;
                while(it.hasNext())
                {
                    it.next();
                    pocet++;
                }
                
                out.writeInt(pocet);
                it = firma.iterator();
                while (it.hasNext()) {
                    out.writeObject(it.next());
                }
                out.close();
                fileOut.close();
            }
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }
    
    public static AbstrHeap nactiHeap(String jmenoSouboru, AbstrHeap heap) throws IOException {

        try {
            Objects.requireNonNull(heap);
            ObjectInputStream in;
            try (FileInputStream fileIn = new FileInputStream(jmenoSouboru)) {
                in = new ObjectInputStream(fileIn);
                heap.zrus();
                int pocetZaznamu = in.readInt();
                IPobocka[] tempPole = new IPobocka[pocetZaznamu];
                for (int i = 0; i < pocetZaznamu; i++) {
                    tempPole[i] = (IPobocka) in.readObject();                                        
                }
                heap.Vybuduj(new KomparatorCislo(), tempPole);
            }
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException(ex);
        }
        return heap;
    }
    
    public static void ulozHeap(String jmenoSouboru, AbstrHeap heap) throws IOException {
        try {
            Objects.requireNonNull(heap);
            try (FileOutputStream fileOut = new FileOutputStream(jmenoSouboru);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                
                Iterator it = heap.iterator();
                out.writeInt(heap.getVelikost());
                while (it.hasNext()) {
                    out.writeObject(it.next());
                }
                out.close();
                fileOut.close();
            }
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }
}
