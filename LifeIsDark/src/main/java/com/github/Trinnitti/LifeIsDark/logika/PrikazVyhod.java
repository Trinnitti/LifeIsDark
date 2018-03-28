/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code PrikazVyhod} implementují pro hru příkaz Vyhod.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-21
 */
public class PrikazVyhod implements IPrikaz
{
    private static final String NAZEV = "vyhod";
    private HerniPlan herniPlan;

    /***************************************************************************
     * Konstruktor
     */
    public PrikazVyhod(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metoda která vyhodí z inventáře věc do aktuálního prostoru. Pokud danou požadovanou věc v inventáři 
     * nemáš, objeví se chybová hláška.
     * 
     * @param - název vyhazované věci
     * return - zpráva která se vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Cože to chceš zahodit? Napiš jméno věci.";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Inventar inventar = herniPlan.getInventar();
        Vec vyhazovana = inventar.getVec(nazevVeci);

        if (vyhazovana == null) {
            return "To u sebe nemáš.";            
        }
        else {
            vyhazovana = inventar.vyhodVec(nazevVeci);
            aktualniProstor.pridejVec(vyhazovana);
            return "Vyhodil jsi " + nazevVeci + ".";
        }
    }

    /**
     *  Metoda vrací název příkazu.
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
