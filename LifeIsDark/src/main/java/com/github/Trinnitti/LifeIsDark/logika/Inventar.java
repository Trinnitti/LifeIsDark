/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

import java.util.*;

/*******************************************************************************
 * Instance třídy {@code Inventar} představují jakýsi baťoh, do kterého si hráč ukládá věci nalezené během hry.
 * The {@code Inventar} class instances represent ...
 *
 * @author  Tomáš Prokeš
 * 0.00.0000 — 2017-05-19
 */
public class Inventar
{

    private static final int KAPACITA = 5;
    private List<Vec> obsah;

    /***************************************************************************
     * Konstruktor
     */
    public Inventar() {
        obsah = new ArrayList<Vec>();
    }

    /**
     * Metoda která vloží věc do inventáře.
     * 
     * @param - vkládaná věc
     * @return - Pokud se věc podaří vložit do inventáře, vrátí její název, jinak null
     */
    public Vec vlozVec(Vec vkladana) {
        if (volneMisto()) {
            obsah.add(vkladana);
            return vkladana;
        }
        return null;
    }

    /**
     * Metoda která zjišťuje zda je v inventáří volné místo.
     * 
     * return - true pokud je v inventáři místo
     */
    public boolean volneMisto() {
        if (obsah.size() <KAPACITA) {
            return true;
        }
        return false;
    }

    /**
     * Metoda která zjišťuje zda je věc v inventáři.
     *  
     * @param   pozadovana    požadovaná věc
     * @return  true       pokud je věc v inventáři, pokud ne tak false
     */
    public boolean jeTuVec(String pozadovana) {
        for (Vec aktualni: obsah) {
            if (aktualni.getNazev().equals(pozadovana)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda která vrací seznam věcí v inventáři.
     * 
     * @return vypíše seznam věcí z inventáře
     */
    public String getVeci() {
        int misto = KAPACITA - obsah.size();
        String nazvy = "\n\nV inventáři máš následující věci: ";
        if (obsah.isEmpty()){
            return nazvy + "Tvůj inventář je prázdný.";
        }
        for (Vec aktualni: obsah){
            nazvy += aktualni.getNazev() + ", " ;
        }
        if (misto > 0){
            return nazvy + "Do inventáře můžeš vložit ještě " + misto + " věcí.";
        }
        return nazvy + "V inventáři už nemáš žádné místo, pokud chceš něco sebrat, musíš vyhodit něco co již nebudeš potřebovat";
    }

    /**
     * Metoda která najde požadovanou věc.
     * 
     * @param nazev - název hledané věci
     * @return hledana - vrátí název hledané věci, pokud nenajde, vátí null
     */
    public Vec getVec(String nazev) {
        Vec hledana = null;
        for (Vec aktualni: obsah) {
            if(aktualni.getNazev().equals(nazev)) {
                hledana = aktualni;
                break;
            }
        }
        return hledana;
    }

    /**
     * Metoda která vyhodí věci z inventáře.
     * 
     * @param  vyhazovana - Vyhazovaná věc
     * @return  vyhozena - Vrací jméno vyhozené věci. Pokud věc nebyla v inventáři, vrací null
     */
    public Vec vyhodVec (String vyhazovana) {
        Vec vyhozena = null;
        for(Vec aktualni: obsah) {
            if(aktualni.getNazev().equals(vyhazovana)) {
                vyhozena = aktualni;
                obsah.remove(aktualni);
                break;
            }
        }
        return vyhozena;
    }

    /**
     * Metoda která vrací maximální počet věcí, které lze přidat do batohu.
     * 
     * @return  - počet věcí
     */
    public int getKapacita() {
        return KAPACITA;
    }

    public List<Vec> getObsah() {
        return obsah;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
