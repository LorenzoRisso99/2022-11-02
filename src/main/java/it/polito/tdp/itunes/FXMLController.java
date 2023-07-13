/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.itunes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPlaylist"
    private Button btnPlaylist; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGenere"
    private ComboBox<String> cmbGenere; // Value injected by FXMLLoader

    @FXML // fx:id="txtDTOT"
    private TextField txtDTOT; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="txtMin"
    private TextField txtMin; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaPlaylist(ActionEvent event) {

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	String genere = this.cmbGenere.getValue();
    	
    	if(genere == null) {
    		txtResult.appendText("Seleziona un genere");
    		return;
    	}
    	
    	int min;
    	
		try {

			min = Integer.parseInt(txtMin.getText())*1000;

		} catch (NumberFormatException e) {
			txtResult.appendText("Inserire valore Min");
			return;
		}
		
		if(min<(1071/1000)) {
			txtResult.appendText("Inserire valore Min più alto");
		}
		
		int max;

		try {

			max = Integer.parseInt(txtMax.getText())*1000;

		} catch (NumberFormatException e) {
			txtResult.appendText("Inserire valore Max");
			return;
		}
		
		if(max<(5286953/1000)) {
			txtResult.appendText("Inserire valore Max più basso");
		}
		
		this.model.creaGrafo(genere, min, max);
		
		txtResult.appendText("Grafo creato!\n");
    	txtResult.appendText(String.format("# VERTICI: %d\n", this.model.nVertici()));
    	txtResult.appendText(String.format("# ARCHI: %d\n", this.model.nArchi()));
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPlaylist != null : "fx:id=\"btnPlaylist\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGenere != null : "fx:id=\"cmbGenere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDTOT != null : "fx:id=\"txtDTOT\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMin != null : "fx:id=\"txtMin\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbGenere.getItems().addAll(this.model.getGeneri());
    }

}
