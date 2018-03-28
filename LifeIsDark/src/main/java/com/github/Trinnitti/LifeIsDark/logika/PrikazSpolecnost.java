/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code PrikazSpolecnost} implementují pro hru příkaz Společnost
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-20
 */
public class PrikazSpolecnost implements IPrikaz
{

    private static final String NAZEV = "spolecnost";
    private HerniPlan herniPlan;

    /***************************************************************************
     * Konstruktor
     */
    public PrikazSpolecnost(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metoda která vrací postavu která tě doprovází
     * 
     * @return Zpráva která se vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (herniPlan.getSpolecnost().getSpolecnosti().isEmpty()) {
            return "Jsi sám.";
        }
        else {
            return "" + herniPlan.getSpolecnost().getSpolecnosti() + ".";
        }
    }

    /**
     *  Metoda vrací název příkazu
     *  
     *  @ return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
