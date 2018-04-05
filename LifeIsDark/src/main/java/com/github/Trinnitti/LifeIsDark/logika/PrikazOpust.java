/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code PrikazOpust} implementují pro hru příkaz Opust.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-21
 */
public class PrikazOpust implements IPrikaz
{
    private static final String NAZEV = "opust";
    private HerniPlan herniPlan;

    /***************************************************************************
     * Konstruktor
     */
    public PrikazOpust(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metoda která vyhodí ze spolecnosti osobu do aktuálního prostoru. Pokud danou požadovanou osobu ve spolecnosti 
     * nemáš, objeví se chybová hláška.
     * 
     * @param - jméno vyhazované osoby
     * return - zpráva která se vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Koho chceš opustit? napiš jméno osoby.";
        }

        String jmenoOsoby = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Spolecnost spolecnost = herniPlan.getSpolecnost();
        Osoba opoustena = spolecnost.getSpolecnost(jmenoOsoby);

        if (opoustena == null) {
            return "Tato osoba s tebou vůbec není.";            
        }
        else {
            opoustena = spolecnost.opustOsobu(jmenoOsoby);
            aktualniProstor.vlozOsobu(opoustena);
            return "Opustil jsi " + jmenoOsoby + ".";
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
