package com.github.Trinnitti.LifeIsDark.ui;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.github.Trinnitti.LifeIsDark.logika.Hra;
import com.github.Trinnitti.LifeIsDark.logika.IHra;
import com.github.Trinnitti.LifeIsDark.logika.Osoba;
import com.github.Trinnitti.LifeIsDark.logika.Vec;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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
	@FXML private Button zavolejPomoc;
	@FXML private CheckBox mapa;
	@FXML private ListView<Object> seznamVychodu = new ListView<>();
	@FXML private ListView<Object> seznamOsob = new ListView<>();
	@FXML private ListView<Object> seznamVeci = new ListView<>();
	@FXML private ListView<Object> inventar = new ListView<>();
	@FXML private ListView<Object> spolecnost = new ListView<>();
	
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
		}
			hra.getHerniPlan().notifyObservers();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
				hra = new Hra();
			textVypis.setText(hra.vratUvitani());
			textVypis.setEditable(false);
			
			seznamVychodu.setItems(vychody);
			seznamOsob.setItems(osobyProstor);
			seznamVeci.setItems(veciProstor);
			inventar.setItems(veciInventar);
			spolecnost.setItems(osobySpolecnost);
			
		hra.getHerniPlan().addObserver(this);
			hra.getHerniPlan().notifyObservers();	
	}
	
	 /***************************************************************************
     * Všechny následující příkazy spouštějí daný příkaz a to tak, že do textového pole vypíší požadovaný 
     * příkaz a rovnou ho odešlou
     *
     * @param nazev objekt, se kterým chceme nakládat
     */
	
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
	
	@FXML public void prikazZavolejPomoc() {
    		if(!hra.konecHry()) {
    		textVstup.setText("zavolejPomoc ");
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
	
	 /***************************************************************************
     * Metoda která spouští novou hru
     *
     */
	
	@FXML public void novaHra() {
            hra = new Hra();
	textVypis.setText(hra.vratUvitani());
	textVstup.setDisable(false);
            hra.getHerniPlan().addObserver(this);
            hra.getHerniPlan().notifyObservers();
    }
	
	 /***************************************************************************
     * Metoda která končí rozehranou hru
     *
     */
    
    @FXML public void konecHry() {
        textVstup.setText("konec");
        odesliPrikaz();
    }
    
	 /***************************************************************************
     * Metoda která vyvolává nápovědu z html a nastavuje velikost okna
     *
     */
    
     @FXML public void Napoveda() 
    {
        Stage stage = new Stage();
        stage.setTitle("Nápověda");
        WebView webView = new WebView();               
        webView.getEngine().load(com.github.Trinnitti.LifeIsDark.main.Gui.class.getResource("napoveda.html").toExternalForm());
        stage.setScene(new Scene(webView, 900, 500));
        stage.show();
    }
     
	 /***************************************************************************
      * Metoda která vyvolává mapu z html a nastavuje velikost okna
      *
      */
     
     @FXML public void Mapa() 
     {
         Stage stage = new Stage();
         stage.setTitle("Mapa");
         WebView webView = new WebView();               
         webView.getEngine().load(com.github.Trinnitti.LifeIsDark.main.Gui.class.getResource("mapa.html").toExternalForm());
         stage.setScene(new Scene(webView, 900, 650));
         stage.show();
     }
     
	 /***************************************************************************
      * Metoda která přidá do listu věci které tam mají být a najde jim přes cestu správný obrázek
      *
      */
		
	@Override
	public void update(Observable o, Object arg) {
                veciProstor.clear();
                veciInventar.clear();
                osobyProstor.clear();
                osobySpolecnost.clear();
                vychody.clear();
                
                String vychod1 = hra.getHerniPlan().getAktualniProstor().seznamVychodu();
                String[] vychod2 = vychod1.split(" ");
                for (int i = 1; i < vychod2.length; i++) {
                    vychody.add(vychod2[i]);
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
