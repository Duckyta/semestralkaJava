package gui;

import abstr.Heap.AbstrHeapImpl;
import abstr.Heap.AbstrHeap;
import firma.Firma;
import firma.IFirma;
import pobocka.IPobocka;
import firma.IZamestnanec;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import komparatory.KomparatorCislo;
import komparatory.KomparatorNazev;
import pomocne.Soubory;

/**
 *
 * @author DuckMcQuack
 */
public class FXMLDocumentController implements Initializable {

    // <editor-fold desc="Semestralni prace B"> 
    private IFirma firma;
    private IPobocka aktualniPobocka;
    private ObservableList<IPobocka> obsPobocky;
    private ObservableList<IZamestnanec> obsZamestnanci;
    @FXML
    private TextField textFieldIndexSyna;

    @FXML
    private Label label;

    @FXML
    private ListView<IPobocka> listViewPobocky;

    @FXML
    private ListView<IZamestnanec> listViewZamestnanci;

    @FXML
    private void handleUloz(ActionEvent event) {
        try {
            Soubory.uloz("data.bin", firma);
            zprava(Alert.AlertType.INFORMATION, "Uložení proběhlo v pořádku!",
                    "Uložení", ButtonType.OK);

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleNacti(ActionEvent event) {
        try {
            Soubory.nactiSerialize("data.bin", firma);
            zobrazPobocky();
            zprava(Alert.AlertType.INFORMATION, "Načtení proběhlo v pořádku!",
                    "Načtení", ButtonType.OK);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //---------------OPERACE POBOCKA---------------//
    @FXML
    private void handlePridejPobocku(ActionEvent event) {
        DialogPridejPobocku dlg = new DialogPridejPobocku(label.getScene().getWindow(), firma);
        dlg.showAndWait();
        zobrazPobocky();
    }

    @FXML
    private void handleOdeberPobocku(ActionEvent event) {
        aktualniPobocka = listViewPobocky.getSelectionModel().getSelectedItem();
        if (aktualniPobocka != null) {
            firma.odeber(aktualniPobocka.getNazev());
            zobrazPobocky();
        } else {
            zprava(Alert.AlertType.ERROR,
                    "Nejdříve vyberte pobočku, kterou chcete odstranit!",
                    "POZOR!",
                    ButtonType.OK);
        }

    }

    //---------------OPERACE ZAMESTNANEC---------------//
    @FXML
    private void handlePridejZamestnance(ActionEvent event) {
        aktualniPobocka = listViewPobocky.getSelectionModel().getSelectedItem();
        if (aktualniPobocka != null) {
            DialogPridejZamestnance dlg = new DialogPridejZamestnance(label.getScene().getWindow(), aktualniPobocka);
            dlg.showAndWait();
            zobrazPobocky();
            zobrazZamestnance();
        } else {
            zprava(Alert.AlertType.ERROR,
                    "Nejdříve vyberte pobočku, které chcete přidat zaměstnance",
                    "POZOR!",
                    ButtonType.OK);
        }

    }

    @FXML
    private void handleOdeberZamestnance(ActionEvent event) {
        aktualniPobocka = listViewPobocky.getSelectionModel().getSelectedItem();
        if (aktualniPobocka != null) {
            aktualniPobocka.odeber(listViewZamestnanci.getSelectionModel().getSelectedItem());
            zobrazZamestnance();
        } else {
            zprava(Alert.AlertType.ERROR,
                    "Nejdříve vyberte pobočku, které chcete přidat zaměstnance",
                    "POZOR!",
                    ButtonType.OK);
        }

    }

    //---------------OPERACE ZPRISTUPNI---------------//
    @FXML
    private void handleZpristupniKoren(ActionEvent event) {
        aktualniPobocka = listViewPobocky.getSelectionModel().getSelectedItem();
        if (aktualniPobocka != null) {
            listViewZamestnanci.getSelectionModel().select(aktualniPobocka.zpristupniVedoucihoPobocky());
        } else {
            zprava(Alert.AlertType.ERROR,
                    "Nejdříve vyberte pobočku!",
                    "POZOR!",
                    ButtonType.OK);
        }
    }

    @FXML
    private void handleZpristupniOtce(ActionEvent event) {
        aktualniPobocka = listViewPobocky.getSelectionModel().getSelectedItem();
        if (aktualniPobocka != null) {
            try {
                int index = Integer.parseInt(textFieldIndexSyna.getText());
                listViewZamestnanci.getSelectionModel().select(aktualniPobocka.zpristupniVedoucihoOddeleni(index));
            } catch (NumberFormatException | NullPointerException ex) {
            }
        } else {
            zprava(Alert.AlertType.ERROR,
                    "Nejdříve vyberte pobočku!",
                    "POZOR!",
                    ButtonType.OK);
        }
    }

    @FXML
    private void handleZpristupniSyna(ActionEvent event) {
        aktualniPobocka = listViewPobocky.getSelectionModel().getSelectedItem();
        if (aktualniPobocka != null) {
            try {
                int index = Integer.parseInt(textFieldIndexSyna.getText());
                listViewZamestnanci.getSelectionModel().select(aktualniPobocka.zpristupni(index));
            } catch (NumberFormatException | NullPointerException ex) {
            }
        } else {
            zprava(Alert.AlertType.ERROR,
                    "Nejdříve vyberte pobočku!",
                    "POZOR!",
                    ButtonType.OK);
        }
    }

    //---------------OPERACE ODEBER---------------//
    @FXML
    private void handleOdeberKoren(ActionEvent event) {
        aktualniPobocka = listViewPobocky.getSelectionModel().getSelectedItem();
        if (aktualniPobocka != null) {
            try {
                int index = Integer.parseInt(textFieldIndexSyna.getText());
                listViewZamestnanci.getSelectionModel().select(aktualniPobocka.zpristupni(index));
            } catch (NumberFormatException | NullPointerException ex) {
            }
        } else {
            zprava(Alert.AlertType.ERROR,
                    "Nejdříve vyberte pobočku!",
                    "POZOR!",
                    ButtonType.OK);
        }
    }

    //---------------POMOCNE METODY---------------//
    private void zobrazPobocky() {
        obsPobocky.clear();
        Iterator<IPobocka> it = firma.iterator();
        while (it.hasNext()) {
            obsPobocky.add(it.next());
        }

        listViewPobocky.setItems(obsPobocky);
    }

    private void zobrazZamestnance() {
        if (aktualniPobocka != null) {
            obsZamestnanci.clear();
            if (aktualniPobocka.getPocetZamestnancu() > 0) {
                Iterator<IZamestnanec> it = aktualniPobocka.iterator();

                while (it.hasNext()) {
                    obsZamestnanci.add(it.next());
                }
                listViewZamestnanci.setItems(obsZamestnanci);
            }
        }
    }

    private void zprava(Alert.AlertType typ, String zprava, String nadpis, ButtonType btnType) {
        Alert alert = new Alert(typ, zprava, btnType);
        alert.setHeaderText(nadpis);
        alert.showAndWait();
    }
    // </editor-fold>

    @FXML
    private ListView<IPobocka> listFrotna;
    private ObservableList<IPobocka> obsFronta;

    @FXML
    private ComboBox comboBoxRazeni;

    // <editor-fold desc="Semestralni prace C"> 
    AbstrHeap fronta;
    private ObservableList<String> obsMoznosti;

    @FXML
    private void handleVybuduj(ActionEvent event) {
        if (firma != null) {
            IPobocka[] pole = getPoleFirma();
            fronta.Vybuduj(new KomparatorCislo(), pole);
            zobrazFrontu();
        }
    }

    @FXML
    private void handleZobraz(ActionEvent event) {
        if (fronta != null) {
            IPobocka temp = (IPobocka) fronta.zpristupniMax();
            listFrotna.getSelectionModel().select(temp);
        }
    }

    @FXML
    private void handlePridejDoFronty(ActionEvent event) {
        handlePridejPobocku(event);
        handleVybuduj(event);

    }

    @FXML
    private void handleOdeberFronta(ActionEvent event) {
        if (fronta != null) {
            fronta.odeberMax();
            zobrazFrontu();
        }
    }
    
    @FXML
    private void handleZrusFronta(ActionEvent event) {
        if (fronta != null) {
            fronta.zrus();
            obsFronta.clear();
        }
    }
    
    @FXML
    private void handleUlozFronta(ActionEvent event) {
        if (fronta != null) {
            try {
                Soubory.ulozHeap("dataHeap.bin", fronta);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void handleNactiFronta(ActionEvent event) {
        if (fronta != null) {
            try {
                Soubory.nactiHeap("dataHeap.bin", fronta);
                zobrazFrontu();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleReorganizuj(ActionEvent event) {
        if (firma != null) {            
            int volba = comboBoxRazeni.getSelectionModel().getSelectedIndex();
            if (volba == 0) {
                fronta.reorganizuj(getPoleFirma(), new KomparatorCislo());
            } else {
                fronta.reorganizuj(getPoleFirma(), new KomparatorNazev());
            }
            zobrazFrontu();
        }
    }

    // </editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firma = new Firma("DuckMcQuack s.r.o");
        //vybranaPobocka = null;
        obsPobocky = FXCollections.observableArrayList();
        obsZamestnanci = FXCollections.observableArrayList();
        label.setText(firma.getNazevFirmy());

        fronta = new AbstrHeapImpl();

        obsMoznosti = FXCollections.observableArrayList("Podle počtu", "Podle jména");
        comboBoxRazeni.setItems(obsMoznosti);
        comboBoxRazeni.getSelectionModel().selectFirst();

        obsFronta = FXCollections.observableArrayList();
        // Listener
        listViewPobocky.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends IPobocka> observable, IPobocka oldValue, IPobocka newValue) -> {
            zobrazZamestnance();
        });
    }

    private IPobocka[] getPoleFirma() {

        if (firma != null) {

            IPobocka[] polePobocek = new IPobocka[firma.getPocet()];
            Iterator<IPobocka> it = firma.iterator();
            int index = 0;
            while (it.hasNext()) {
                polePobocek[index++] = it.next();
            }
            return polePobocek;
        }

        return null;

    }

    private void zobrazFrontu() {
        if (firma != null) {
            obsFronta.clear();
            Iterator<IPobocka> it = fronta.iterator();
            while(it.hasNext())
            {
                obsFronta.add(it.next());
            }
           
            listFrotna.setItems(obsFronta);
        }
    }

}
