/* Soubor je ulozen v kodovani UTF-8.
 * 
 */
package com.github.Trinnitti.LifeIsDark.main;

import com.github.Trinnitti.LifeIsDark.logika.Hra;
import com.github.Trinnitti.LifeIsDark.logika.IHra;
import com.github.Trinnitti.LifeIsDark.ui.TextoveRozhrani;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková, Tomáš Prokeš
 * @version   ZS 2016/2017
 */

/**
 * Metoda, ve které se konstruuje okno, kontroler a hra,
 * která se předává kontroleru
 */
public class Gui extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../ui/MainWindow.fxml"));    	
    	Parent root = loader.load();
        
        primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    	primaryStage.setTitle("Life is Dark");
    }

    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace. Při zadání parametru "-text"
     * se aplikace spustí  textovém rozhraní bez GUI.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {

    	if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neplatný parametr");
            }
        }
    	
    	//		  TODO parametrické spuštění hry
//        IHra hra = new Hra();
//        TextoveRozhrani ui = new TextoveRozhrani(hra);
//        ui.hraj();
    	
    	launch(args);
    }
    
}