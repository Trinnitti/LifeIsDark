/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

import java.util.*;

/*******************************************************************************
 * Instance třídy {@code Spolecnost} představuje osobu, kterou jsi si vybral k sobě za společnost
 * Pro zjednodušení je řešena přes sloty, stejně jako inventář, tudíž třída díky řešení přes seznam 
 * umožňuje vzít sebou více postav, pokud by nebyla kapacita nastavena na 1 slot.
 *
 * @author  Tomáš Prokeš
 * 0.00.0000 — 2017-05-21
 */
public class Spolecnost
{

    private static final int KAPACITA = 1;
    private List<Osoba> obsah;

    /***************************************************************************
     * Konstruktor
     */
    public Spolecnost() {
        obsah = new ArrayList<Osoba>();
    }

    /**
     * Metoda pomocí které se k tobě někdo přidá
     *
     * @param - vkládaná osobu
     * @return - Pokud se k tobě osoba přidá, vrátí její název, jinak null
     */
    public Osoba vlozSpolecnost(Osoba vkladana) {
        if (volneMisto()) {
            obsah.add(vkladana);
            return vkladana;
        }
        return null;
    }

    /**
     * Metoda která zjišťuje zda s tebou může ještě někdo jít
     *
     * return - true pokud s tebou ještě někdo může jít
     */
    public boolean volneMisto() {
        if (obsah.size() <KAPACITA) {
            return true;
        }
        return false;
    }

    /**
     * Metoda která ti řekne, kdo s tebou jde.
     *
     * @return vypíše kdo s tebou jde
     */
    public String getSpolecnosti() {
        int misto = KAPACITA - obsah.size();
        String jmeno = "\n\nJde s tebou: ";
        if (obsah.isEmpty()){
            return jmeno + "Jsi sám";
        }
        for (Osoba aktualni: obsah){
            jmeno += aktualni.getJmeno() + ", " ;
        }
        if (misto > 0){
            return jmeno + "Jen si sebou někoho vem, každá pomoc se hodí.";
        }
        return jmeno + "Víc lidí zachránit nemůžeš, zlomil jsi klíč od cely.";
    }

    /**
     * Metoda která najde požadovanou osobu.
     *
     * @param jmeno - jméno hledané osoby
     * @return hledana - vrátí jmeno hledané osoby, pokud nenajde, vátí null
     */
    public Osoba getSpolecnost (String jmeno) {
        Osoba hledana = null;
        for (Osoba aktualni: obsah) {
            if(aktualni.getJmeno().equals(jmeno)) {
                hledana = aktualni;
                break;
            }
        }
        return hledana;
    }

    /**
     * Metoda která zjišťuje zda se chtěná osoba nachází s tebou.
     *
     * @param chtena - chtena osoba
     * return true - vrací true pokud se chtěná osoba s tebou nachází
     */
    public boolean osobaSTebou (String chtena) {
        for (Osoba aktualni: obsah) {
            if (aktualni.getJmeno().equals(chtena)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda která vrací maximální počet osobí, které s tebou můžou jít.
     *
     * @return  - počet osob
     */
    public int getKapacita() {
        return KAPACITA;
    }

    /**
     * Metoda která vrací jméno osoby která se nachází s tebou
     */
    public String getJmeno() {
        if(obsah.size() > 0) {
            return obsah.get(0).getJmeno();
        }
        return "";
    }
}