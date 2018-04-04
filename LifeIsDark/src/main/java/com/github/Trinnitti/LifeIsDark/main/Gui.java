/* Soubor je ulozen v kodovani UTF-8.
 * 
 */
package com.github.Trinnitti.LifeIsDark.main;

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
public class Gui extends Application {
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args) {
    	launch(args);
    }
    
    /**
	 * Metoda, ve které se konstruuje okno, kontroler a hra,
	 * která se předává kontroleru
	 */
    @Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("MainWindow.fxml"));    	
    	Parent root = loader.load();
    	
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    	primaryStage.setTitle("LifeIsDark");
		
	}
}

