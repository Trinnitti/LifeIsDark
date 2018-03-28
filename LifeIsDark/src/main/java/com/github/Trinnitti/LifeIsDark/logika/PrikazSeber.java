/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code PrikazSeber} implementují pro hru příkaz Seber.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-20
 */
public class PrikazSeber implements IPrikaz
{

    private static final String NAZEV = "seber";
    private HerniPlan herniPlan;
    private Inventar inventar;

    /***************************************************************************
     * Konstruktor
     */
    public PrikazSeber(HerniPlan herniPlan)
    {
        this.herniPlan = herniPlan;
        this.inventar = herniPlan.getInventar();
    }

    /**
     * Metoda která provádí příkaz seber, věc se nesebere pokud v prostoru není, věc není přenositelná, nebo pokud 
     * v inventáři není volné místo.
     * 
     * @param parametry - název sbírané věci
     * @return - zpráva která se vypíše hráči
     */
    @Override
    public String provedPrikaz (String...parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (název věci), tak ....
            return "Co mám sebrat? Zadej název věci";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        if (aktualniProstor.jeTuVec(nazevVeci) == null) {
            return "To tu není.";
        }
        else {
            Vec sbirana = aktualniProstor.seberVec(nazevVeci);
            if (sbirana == null) {
                //pokud věc není přenositelná
                return "Toto nelze sebrat.";
            }
            else {
                if (inventar.vlozVec(sbirana) == null) {
                    aktualniProstor.pridejVec(sbirana);
                    return "Bohužel máš u sebe moc věcí, něco prvně zahoď";
                }
                else {
                    return "Sebral jsi " + nazevVeci + ".";
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
