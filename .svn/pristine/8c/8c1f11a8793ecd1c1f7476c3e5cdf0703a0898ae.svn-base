/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.svenjacobs.loremipsum.LoremIpsum;
import enums.Pozice;
import firma.IZamestnanec;
import firma.Zamestnanec;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import pobocka.IPobocka;

/**
 *
 * @author DuckMcQuack
 */
public class DialogPridejZamestnance extends Stage {

    private LoremIpsum lorem = new LoremIpsum();
    private IPobocka pobocka;

    private TextField txtJmeno = new TextField();
    private TextField txtPrijmeni = new TextField();
    private TextField txtId = new TextField();
    private TextField txtCisloKancelare = new TextField();
    private ComboBox pozice = new ComboBox();
    private ComboBox vedouci = new ComboBox();

    ObservableList<String> obsVedouci;
    ObservableList<IZamestnanec> obsVedouciZam;

    public DialogPridejZamestnance(Window okno, IPobocka pobocka) {
        setTitle("Přidej zaměstnance");
        setWidth(600);
        setHeight(400);
        this.pobocka = pobocka;
        initStyle(StageStyle.UTILITY);
        initModality(Modality.WINDOW_MODAL);
        initOwner(okno);
        setScene(vytvorScenu());
    }

    private Scene vytvorScenu() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);

        // Mřížka s TextFieldy a Labely
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Komponenty label
        Label lblJmeno = new Label("Jmeno");
        Label lblPrijmeni = new Label("Příjmení");
        Label lblId = new Label("ID");
        Label lblCisloKancelare = new Label("Číslo kanceláře");
        Label lblPozice = new Label("Pracovní pozice");
        Label lblVedouci = new Label("Vedoucí");

        ObservableList<String> typ = FXCollections.observableArrayList(
                "Vedoucí pobočky",
                "Vedoucí oddělení",
                "Zaměstnanec");
        IZamestnanec[] zam = pobocka.zpristupniSeznamVedoucichOddeleni();
        if (zam == null || zam.length <= 0) {
            obsVedouci = FXCollections.observableArrayList("Žádný vedoucí");
            vedouci.setItems(obsVedouci);
            vedouci.getSelectionModel().selectFirst();
        } else {
            obsVedouciZam = FXCollections.observableArrayList(zam);
            vedouci.maxWidth(30);
            vedouci.setItems(obsVedouciZam);
            //vedouci.getSelectionModel().selectFirst();
        }

        pozice.setItems(typ);
        pozice.getSelectionModel().selectFirst();

        int radek = 0;
        grid.add(lblJmeno, 0, radek);
        grid.add(txtJmeno, 1, radek++);
        grid.add(lblPrijmeni, 0, radek);
        grid.add(txtPrijmeni, 1, radek++);

        grid.add(lblId, 0, radek);
        grid.add(txtId, 1, radek++);

        grid.add(lblCisloKancelare, 0, radek);
        grid.add(txtCisloKancelare, 1, radek++);

        grid.add(lblPozice, 0, radek);
        grid.add(pozice, 1, radek++);

        grid.add(lblVedouci, 0, radek);
        grid.add(vedouci, 1, radek++);

        // Tlačítko
        Button tlacitko = new Button("OK");
        tlacitko.setOnAction((ActionEvent e) -> {
            try {
                IZamestnanec temp = vytvorZam();

                if (temp != null) {
                    switch (temp.getPozice()) {
                        case VEDOUCI_ODDELENI:
                            pobocka.vlozVedoucihoOddeleni(temp);
                            break;
                        case VEDOUCI_POBOCKY:
                            pobocka.vlozVedoucihoPobocky(temp);
                            break;
                        case ZAMESTNANEC:
                            if (pobocka.getPocetZamestnancu() >= 2) {
                                IZamestnanec ved = obsVedouciZam.get(vedouci.getSelectionModel()
                                        .getSelectedIndex());
                                pobocka.vloz(ved, temp);
                            }
                            else
                            {
                                Alert alert = new Alert(Alert.AlertType.ERROR,
                                        "Nejdříve vložte ředitele pak vedoucího.",
                                        ButtonType.OK);
                                alert.showAndWait();
                            }
                            break;
                    }

                }
                hide();
            } catch (IllegalArgumentException ex) {
                System.out.println("Chyba: " + ex.getMessage());
            }
        });

        Button btnUlozZadej = new Button("Generovat náhodně");
        btnUlozZadej.setOnAction((ActionEvent e) -> {
            try {
                generujNahodnePolozky();
            } catch (IllegalArgumentException ex) {
                System.out.println("Chyba: " + ex.getMessage());
            }
        });

        GridPane gridBtn = new GridPane();
        gridBtn.setAlignment(Pos.CENTER);
        gridBtn.setPadding(new Insets(10));
        gridBtn.setHgap(10);
        gridBtn.setVgap(10);

        gridBtn.add(tlacitko, 0, 0);
        gridBtn.add(btnUlozZadej, 1, 0);

        box.getChildren().addAll(grid, gridBtn);

        return new Scene(box);
    }

    private String generujSlova(int pocet, int index) {
        if (index > pocet) {
            return lorem.getWords(pocet, index);
        } else {
            return lorem.getWords(index, pocet);
        }
    }

    private int generujCislo(int max, int min) {
        return (int) (min + (Math.random() * ((max - min) + 1)));
    }

    private void generujNahodnePolozky() {
        txtJmeno.setText(generujSlova(generujCislo(0, 5), generujCislo(0, 5)));
        txtPrijmeni.setText(0 + "");
        txtCisloKancelare.setText(generujCislo(0, 500) + "");
        txtId.setText(generujCislo(0, 10000) + "");
        pozice.getSelectionModel().select(generujCislo(0, 3));
    }

    private Pozice ziskejPozici() {
        switch (pozice.getSelectionModel().getSelectedIndex()) {
            case 0:
                return Pozice.VEDOUCI_POBOCKY;
            case 1:
                return Pozice.VEDOUCI_ODDELENI;
            case 2:
                return Pozice.ZAMESTNANEC;
        }
        return null;
    }

    private IZamestnanec vytvorZam() {
        return new Zamestnanec(txtJmeno.getText(),
                txtPrijmeni.getText(),
                Integer.parseInt(txtCisloKancelare.getText()),
                Integer.parseInt(txtId.getText()),
                ziskejPozici());
    }
}
