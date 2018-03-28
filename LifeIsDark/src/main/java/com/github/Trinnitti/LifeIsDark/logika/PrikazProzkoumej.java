/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code PrikazProzkoumej} implementují pro hru příkaz Prozkoumej.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-20
 */
public class PrikazProzkoumej implements IPrikaz
{

    private static final String NAZEV = "prozkoumej";
    private Hra hra;
    private HerniPlan herniPlan;

    /***************************************************************************
     * Konstruktor
     */
    public PrikazProzkoumej(Hra hra) {
        this.hra = hra;
        this.herniPlan = hra.getHerniPlan();
    }

    /**
     * Metoda která prozkoumá danou věc v prostoru.
     * Bere v úvahu zda se věc v prostoru vůbec nachází či zda je věc v místnosti nebo ve věci.
     * 
     * @param - název prohledávané věci
     * @return - zpráva pro hráče
     */
    @Override
    public String provedPrikaz (String... parametry) {
        if (parametry.length == 0) {
            return "Cože to chceš prozkoumat? Zadej název předmětu.";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        if (aktualniProstor.jeTuVec(nazevVeci) == null) {
            return "To tu bohužel není.";
        }
        else {
            Vec prozkoumavana = aktualniProstor.jeTuVec(nazevVeci);
            //věc je přímo v místnosti, ne v jiné věci
            if (prozkoumavana.jeZamceno()) {
                //výjimka pro zamčený šuplík
                return "Šuplík je zamčený.";
            }
            else {
                return vyjimkaVevnitr(prozkoumavana, aktualniProstor);
            }
        }
    }

    /** Metoda která řeší vyjímku pro věci vevnitř.
     * 
     * @param vec - prozkoumávaná věc
     * @param prostor - kde se nacházíš
     * @return - zpráva pro hráče
     */
    private String vyjimkaVevnitr (Vec vec, Prostor prostor) {
        String zprava;
        if (vec.getVeciVevnitr().isEmpty()) {
            //Pokud ve věci nic není.
            zprava = "Nic vevnitř není.";
        }
        else {
            //Pokud ve věci něco je, umístí věc do prostoru
            zprava =  "Našel jsi ";
            for (Vec item: vec.getVeciVevnitr()) {
                item.setViditelna(true);
                if (!zprava.equals("Našel jsi ")) {
                    zprava += ", ";
                }
                zprava += item.getNazev();
                prostor.getSeznamVeci().add(item);
            }
            zprava += ".";
            vec.getVeciVevnitr().clear();
        }
        return zprava;
    }

    /**
     *  Metoda vrací název příkazu 
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
