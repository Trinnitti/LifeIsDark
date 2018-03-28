/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code PrikazInventar} implementují pro hru příkaz Inventář
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-20
 */
public class PrikazInventar implements IPrikaz
{

    private static final String NAZEV = "inventar";
    private HerniPlan herniPlan;

    /***************************************************************************
     * Konstruktor
     */
    public PrikazInventar(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metoda která vrací obsah inventáře
     * 
     * @return Zpráva která se vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (herniPlan.getInventar().getVeci().isEmpty()) {
            return "Tvůj inventář je prázdný";
        }
        else {
            return "" + herniPlan.getInventar().getVeci() + ".";
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
