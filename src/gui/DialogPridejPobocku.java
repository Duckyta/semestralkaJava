/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.svenjacobs.loremipsum.LoremIpsum;
import firma.IFirma;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import pobocka.Adresa;
import pobocka.IPobocka;
import pobocka.Pobocka;

/**
 *
 * @author DuckMcQuack
 */
public class DialogPridejPobocku extends Stage {
    
    private LoremIpsum lorem = new LoremIpsum();
    private IFirma firma;
    private IPobocka pobocka;
    
    private TextField txtNazev = new TextField();
    private TextField txtPocetZam = new TextField();
    private TextField txtMesto = new TextField();
    private TextField txtUlice = new TextField();
    private TextField txtPSC = new TextField();  

    public DialogPridejPobocku(Window okno, IFirma firma) {
        setTitle("Přidej pobočku");
        setWidth(350);
        setHeight(400);
        this.firma = firma;
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
        Label lblNazev = new Label("Název pobočky");
        Label lblPocetZamestnancu = new Label("Počet zaměstnanců");
        Label lblMesto = new Label("Město");
        Label lblUlice = new Label("Ulice");
        Label lblPSC = new Label("PSČ");

        int radek = 0;
        grid.add(lblNazev, 0, radek);
        grid.add(txtNazev, 1, radek++);
        grid.add(lblPocetZamestnancu, 0, radek);
        grid.add(txtPocetZam, 1, radek++);

        grid.add(lblMesto, 0, radek);
        grid.add(txtMesto, 1, radek++);  
        
        grid.add(lblUlice, 0, radek);
        grid.add(txtUlice, 1, radek++);
        
        grid.add(lblPSC, 0, radek);
        grid.add(txtPSC, 1, radek++);
        
        // Tlačítko
        Button tlacitko = new Button("OK");
        tlacitko.setOnAction((ActionEvent e) -> {
            try {
                IPobocka temp = vytvorPob();
                if (temp != null) {
                    firma.vloz(temp.getNazev(), temp);
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
        txtNazev.setText(generujSlova(generujCislo(0, 5), generujCislo(0, 5)));
        txtPocetZam.setText(0 + "");
        txtUlice.setText(generujSlova(generujCislo(0, 5), generujCislo(0, 5)));
        txtMesto.setText(generujSlova(generujCislo(0, 5), generujCislo(0, 5)));
        txtPSC.setText(generujCislo(10000, 0) + "");
    }
    
    private IPobocka vytvorPob() {
        Adresa ad = new Adresa(txtUlice.getText(),
                Integer.parseInt(txtPSC.getText()),
                txtMesto.getText());
        
        return new Pobocka(ad, txtMesto.getText(), Integer.parseInt(txtPocetZam.getText()));        
    }   
}
