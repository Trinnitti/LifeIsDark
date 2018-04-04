package com.github.Trinnitti.LifeIsDark.main;

import java.util.Observable;
import java.util.Observer;

import com.github.Trinnitti.LifeIsDark.logika.Hra;
import com.github.Trinnitti.LifeIsDark.logika.IHra;
import com.github.Trinnitti.LifeIsDark.logika.Vec;
import com.github.Trinnitti.LifeIsDark.logika.Osoba;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky, Tomáš Prokeš
 *
 */
public class HomeController extends GridPane implements Observer, Initializable {
	
	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private Button jdi;
	@FXML private Button seber;
	@FXML private Button prozkoumej;
	@FXML private Button pouzij;
	@FXML private Button vyhod;
	@FXML private Button branSe;
	@FXML private Button mluv;
	@FXML private Button verbuj;
	@FXML private Button opust;
	@FXML private ListView<Object> seznamVychodu;
	@FXML private ListView<Object> seznamOsob;
	@FXML private ListView<Object> seznamVeci;
	@FXML private ListView<Object> inventar;
	@FXML private ListView<Object> spolecnost;
	@FXML private ImageView hrac;
	
	private IHra hra;
	 private ObservableList<Object> veciProstor = FXCollections.observableArrayList();
	 private ObservableList<Object> osobyProstor = FXCollections.observableArrayList();
     private ObservableList<Object> veciInventar = FXCollections.observableArrayList();
     private ObservableList<Object> osobySpolecnost = FXCollections.observableArrayList();
     private ObservableList<Object> vychody = FXCollections.observableArrayList();
	
	/**
	 * Metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho...
	 */
	public void odesliPrikaz() {
		
		String vypis = hra.zpracujPrikaz(textVstup.getText());
		textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
		textVypis.appendText(vypis);
		textVstup.setText("");
		
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		}
			hra.getHerniPlan().notifyObservers();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textVypis.setText(hra.vratUvitani());
		
			seznamVychodu.setItems(vychody);
			seznamOsob.setItems(osobyProstor);
			seznamVeci.setItems(veciProstor);
			inventar.setItems(veciInventar);
			spolecnost.setItems(osobySpolecnost);
			
		hra.getHerniPlan().addObserver(this);
			hra.getHerniPlan().notifyObservers();	
	}
	
	@FXML public void prikazInventar() {
                List<Vec> seznam;
                seznam = hra.getHerniPlan().getInventar().getObsah();
                int index = inventar.getSelectionModel().getSelectedIndex();
                
                String nazev = "";
                int promena = 0;
                for (Vec vec : seznam) {
                   if(promena == index) {
                       nazev = vec.getNazev();
                   }
                   promena++;
                }
        if(!hra.konecHry()) {
        textVstup.setText("pouzij " + nazev);
        odesliPrikaz();
        }
    }
	
	@FXML public void prikazVyhodVec() {
        List<Vec> seznam;
        seznam = hra.getHerniPlan().getInventar().getObsah();
        int index = inventar.getSelectionModel().getSelectedIndex();
        
        String nazev = "";
        int promena = 0;
        for (Vec vec : seznam) {
           if(promena == index) {
               nazev = vec.getNazev();
           }
           promena++;
        }
        if(!hra.konecHry()) {
        	textVstup.setText("vyhod " + nazev);
        	odesliPrikaz();
        }
	}
	
	@FXML public void prikazSpolecnost() {
        	List<Osoba> seznam;
        	seznam = hra.getHerniPlan().getSpolecnost().getObsah();
        	int index = spolecnost.getSelectionModel().getSelectedIndex();
        
        	String nazev = "";
        	int promena = 0;
        	for (Osoba osoba : seznam) {
        		if(promena == index) {
        			nazev = osoba.getJmeno();
        		}
        		promena++;
        	}
        	if(!hra.konecHry()) {
        		textVstup.setText("opust " + nazev);
        		odesliPrikaz();
        	}
	}
	
	@FXML public void prikazOsoba() {
        	List<Osoba> seznam;
        	seznam = hra.getHerniPlan().getAktualniProstor().getSeznamOsob();
        	int index = seznamOsob.getSelectionModel().getSelectedIndex();
        
        	String nazev = "";
        	int promena = 0;
        	for (Osoba osoba : seznam) {
        		if(promena == index) {
        			nazev = osoba.getJmeno();
        		}
        		promena++;
        	}
        		if(!hra.konecHry()) {
        		textVstup.setText("mluv " + nazev);
        		odesliPrikaz();
        		}
		}
	
	@FXML public void prikazVerbujOsoba() {
    	List<Osoba> seznam;
    	seznam = hra.getHerniPlan().getAktualniProstor().getSeznamOsob();
    	int index = seznamOsob.getSelectionModel().getSelectedIndex();
    
    	String nazev = "";
    	int promena = 0;
    	for (Osoba osoba : seznam) {
    		if(promena == index) {
    			nazev = osoba.getJmeno();
    		}
    		promena++;
    	}
    		if(!hra.konecHry()) {
    		textVstup.setText("verbuj " + nazev);
    		odesliPrikaz();
    		}
	}
	
	@FXML public void prikazBranSeOsoba() {
    	List<Osoba> seznam;
    	seznam = hra.getHerniPlan().getAktualniProstor().getSeznamOsob();
    	int index = seznamOsob.getSelectionModel().getSelectedIndex();
    
    	String nazev = "";
    	int promena = 0;
    	for (Osoba osoba : seznam) {
    		if(promena == index) {
    			nazev = osoba.getJmeno();
    		}
    		promena++;
    	}
    		if(!hra.konecHry()) {
    		textVstup.setText("branSe " + nazev);
    		odesliPrikaz();
    		}
	}
	
	@FXML public void prikazVec() {
                List<Vec> seznam;
                seznam = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();
                int index = seznamVeci.getSelectionModel().getSelectedIndex();
                
                String nazev = "";
                int promena = 0;
                for (Vec vec : seznam) {
                   if(promena == index) {
                       nazev = vec.getNazev();
                   }
                   promena++;
                }
        if(!hra.konecHry()) {
        textVstup.setText("seber " + nazev);
        odesliPrikaz();
        }
    }
	
	@FXML public void prikazProzkoumejVec() {
        List<Vec> seznam;
        seznam = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();
        int index = seznamVeci.getSelectionModel().getSelectedIndex();
        
        String nazev = "";
        int promena = 0;
        for (Vec vec : seznam) {
           if(promena == index) {
               nazev = vec.getNazev();
           }
           promena++;
        }
        if(!hra.konecHry()) {
        	textVstup.setText("prozkoumej " + nazev);
        	odesliPrikaz();
        }
	}
	
	@FXML public void prikazVychod() {
        String nazev = seznamVychodu.getSelectionModel().getSelectedItem().toString();
        if(!hra.konecHry()) {
        textVstup.setText("jdi " + nazev);
        odesliPrikaz();
        }
    }
	
	@FXML public void novaHra() {
            hra = new Hra();
	textVypis.setText(hra.vratUvitani());
	textVstup.setDisable(false);
            hra.getHerniPlan().addObserver(this);
            hra.getHerniPlan().notifyObservers();
    }
    
    @FXML public void konecHry() {
        textVstup.setText("konec");
        odesliPrikaz();
    }
    
     @FXML public void Napoveda() 
    {
        Stage stage = new Stage();
        stage.setTitle("Nápověda");
        WebView webView = new WebView();               
        webView.getEngine().load(com.github.Trinnitti.LifeIsDark.main.Gui.class.getResource("/dalsi/napoveda.html").toExternalForm());
        stage.setScene(new Scene(webView, 1200, 650));
        stage.show();
    }
		
	@Override
	public void update(Observable o, Object arg) {
		hrac.setX(hra.getHerniPlan().getAktualniProstor().getX());
		hrac.setY(hra.getHerniPlan().getAktualniProstor().getY());
                veciProstor.clear();
                veciInventar.clear();
                osobyProstor.clear();
                osobySpolecnost.clear();
                vychody.clear();
		String vychod = hra.getHerniPlan().getAktualniProstor().seznamVychodu();
                String[] oddeleneVychody = vychod.split(" ");
                for (int i = 1; i < oddeleneVychody.length; i++) {
                    vychody.add(oddeleneVychody[i]);
                }
                
                List<Vec> inventar = hra.getHerniPlan().getInventar().getObsah();
                for (Vec vec : inventar) {
                    Vec promena = vec;
                    ImageView obrazek = new ImageView(new Image(com.github.Trinnitti.LifeIsDark.main.Gui.class.getResourceAsStream("/dalsi/"+promena.getObrazek()), 100, 100, false, false));
                    veciInventar.add(obrazek);
                }
                
                List<Vec> veci = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();
                for (Vec vec : veci) {
                    Vec promena = vec;
                    ImageView obrazek = new ImageView(new Image(com.github.Trinnitti.LifeIsDark.main.Gui.class.getResourceAsStream("/dalsi/"+promena.getObrazek()), 100, 100, false, false));
                    veciProstor.add(obrazek);
                }
                
                List<Osoba> osoby = hra.getHerniPlan().getAktualniProstor().getSeznamOsob();
                for (Osoba osoba : osoby) {
                    Osoba promena =osoba;
                    ImageView obrazek = new ImageView(new Image(com.github.Trinnitti.LifeIsDark.main.Gui.class.getResourceAsStream("/dalsi/"+promena.getObrazek()), 100, 100, false, false));
                    osobyProstor.add(obrazek);
                }
                
                List<Osoba> spolecnost = hra.getHerniPlan().getSpolecnost().getObsah();
                for (Osoba osoba : spolecnost) {
                    Osoba promena = osoba;
                    ImageView obrazek = new ImageView(new Image(com.github.Trinnitti.LifeIsDark.main.Gui.class.getResourceAsStream("/dalsi/"+promena.getObrazek()), 100, 100, false, false));
                    osobySpolecnost.add(obrazek);
                }
	}

}
