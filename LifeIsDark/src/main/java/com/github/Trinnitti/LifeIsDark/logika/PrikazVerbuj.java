/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code PrikazVerbuj} implementuji pro hráče příkaz Verbuj
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-27
 */
public class PrikazVerbuj implements IPrikaz
{
    private static final String NAZEV = "verbuj";
    private HerniPlan herniPlan;
    private Spolecnost spolecnost;

    /***************************************************************************
     * Konstruktor
     */
    public PrikazVerbuj(HerniPlan herniPlan)
    {
        this.herniPlan = herniPlan;
        this.spolecnost = herniPlan.getSpolecnost();
    }

    /**
     * Metoda která provádí příkaz verbuj, a to jen v případě že s tebou ještě nikdo není.
     * 
     * @param parametry - jméno verbované postavy
     * @return - zpráva která se vypíše hráči
     */
    @Override
    public String provedPrikaz (String...parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (název věci), tak ....
            return "\nKoho chceš zachránit? Napiš jméno.";
        }

        String jmenoOsoby = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        if (aktualniProstor.jeTuOsoba(jmenoOsoby) == null) {
            return "\nTato osoba tu není.";
        }
        else {
            Osoba verbovana = aktualniProstor.verbujOsobu(jmenoOsoby);
            if (verbovana == null) {
                //pokud osoba není kamaradska
                return "\nOsoba není ochotná jít s tebou.";
            }
            else {
                if (spolecnost.vlozSpolecnost(verbovana) == null) {
                    aktualniProstor.vlozOsobu(verbovana);
                    return "\nNikoho dalšího bohužel zachránit nemůžeš, zlomil jsi klíč od cel.";
                }
                else {
                    return "\n" + jmenoOsoby + " se k tobě přidal/a.";
                }
            }
        }
    }

    /**
     * Metoda která vrací název příkazu
     * 
     * @return - název příkazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
