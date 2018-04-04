/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

import java.util.*;

/*******************************************************************************
 * Instance třídy {@code Vec} představují věci které lze najít ve hře.
 * The {@code Vec} class instances represent things you can find in the game.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-16
 */
public class Vec
{

    private String nazev;
    private String popis;
    private boolean prenositelna;
    private boolean viditelna;
    private boolean prozkoumana;
    private boolean zamceno;
    private Collection<Vec> veciVevnitr;
    private Vec klic;
    private String obrazek;

    /***************************************************************************
     * Konstruktor
     */
    public Vec(String nazev, String popis, String obrazek, boolean prenositelna, boolean viditelna) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.viditelna = viditelna;
        this.prozkoumana = false;
        this.veciVevnitr = new ArrayList<>();
        this.zamceno= false;
        this.obrazek = obrazek;
    }

    /**
     * Metoda která vrací název věci.
     * 
     * @return název věci
     */
    public String getNazev() {
        return this.nazev;
    }

    /**
     * Metoda která vrací popis věci.
     * 
     * @return popis věci
     */
    public String getPopis() {
        return this.popis;
    }

    /**
     * Metoda která vrací zda je věc přenositelná.
     * 
     * @return vrací true pokud je přenositelná
     */
    public boolean jePrenositelna() {
        return this.prenositelna;
    }

    /**
     * Metoda která nastaví přenositelnost věci.
     *
     * @param jePrenositelna  -true, pokud je přenositelná
     */
    public void setPrenositelna(boolean jePrenositelna) {
        this.prenositelna = jePrenositelna;
    }

    /**
     * Metoda která vrací zda je věc viditelná.
     * 
     * @return vrací věc pokud je viditelná
     */
    public boolean jeViditelna() {
        return this.viditelna;
    }

    /**
     * Metoda která nastaví jestli věc bude v prostoru viditelná či nikoliv.
     *
     * @param jeViditelna  -true, pokud je viditelná
     */
    public void setViditelna (boolean jeViditelna) {
        this.viditelna = jeViditelna;
    }

    /**
     * Metoda který vrací zda je věc prozkoumaná.
     *
     * @return vrací pokud je věc prozkoumaná
     */
    public boolean jeProzkoumana() {
        return this.prozkoumana;
    }

    /**
     * Metoda která nastaví jestli věc je již prozkoumána.
     *
     * @param jeProzkoumana  -true, pokud je prozkoumaná
     */
    public void setProzkoumana (boolean jeProzkoumana) {
        this.prozkoumana = jeProzkoumana;
    }

    /**
     * Metoda která vloží věc do jiné věci.
     *
     * @return vkládaná věc
     */
    public void vlozVec(Vec pridavana) {
        veciVevnitr.add(pridavana);
    }

    /**
     * Metoda která odemkne či zamkne zámek.
     */
    public void setZamceno (boolean zamceno) {
        this.zamceno = zamceno;
    }

    /**
     * Metoda která zjišťuje zda je odemčeno či zamčeno.
     */
    public boolean jeZamceno () {
        return zamceno;
    }

    /**
     * Metoda která přiřadí klíč zamčenému zámku.
     */
    public void setKlic(Vec klic) {
        this.klic = klic;
    }

    /**
     * Metoda vrací klíč přiřazený konkrétnímu prostoru.
     */
    public Vec getKlic() {
        return klic;
    }

    /**
     * Metoda která zjistí zda se požadovaná věc nachází v jiné.
     * 
     * @param nazev -název hledané věci
     * @return true - pukud se věc ve věci nachází
     */
    public boolean obsahujeVec (String nazev) {
        boolean obsahuje = false;
        for(Vec aktualni: veciVevnitr) {
            if(aktualni.getNazev().equals(nazev)) {
                obsahuje = true;
                break;
            }
        }
        return obsahuje;
    }

    /**
     * Metoda která vrací seznam věcí v jiné věci.
     *
     * @return vecVevnitr -seznam věcí v List<>
     */
    public Collection<Vec> getVeciVevnitr() {
        return veciVevnitr;
    }
    
    public String getObrazek() {
        return obrazek;
    }

}