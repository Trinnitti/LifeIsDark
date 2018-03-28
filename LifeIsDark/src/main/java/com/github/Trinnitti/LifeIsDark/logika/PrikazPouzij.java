/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code PrikazPoužij} implementují pro hru příkaz Použij
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-20
 */
public class PrikazPouzij implements IPrikaz
{
    private static final String NAZEV = "pouzij";
    private Hra hra;
    private HerniPlan herniPlan;
    private Inventar inventar;

    /***************************************************************************
     * Konstruktor
     */
    public PrikazPouzij(Hra hra) {
        this.hra = hra;
        this.herniPlan = hra.getHerniPlan();
        inventar = herniPlan.getInventar(); 
    }

    /**
     * Metoda která použije věc z inventáře, pokud je to v daném prostoru možné.
     * 
     * @param - používaná věc
     * @return - zpráva která se vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co chceš použít? Musíš napsat název věci.";
        }

        String zprava;
        String pouzitaVec = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Inventar inventar = herniPlan.getInventar();

        if (inventar.getVec(pouzitaVec) == null) {           
            zprava =  "Tohle u sebe nemáš.";
        }
        else {

            switch (pouzitaVec) {
                case "klicSklep":
                aktualniProstor.vratSousedniProstor("sklep").setZamceno(false);
                zprava = "Odemkl jsi dveře do sklepa";
                inventar.vyhodVec(pouzitaVec);
                break;

                case "klicVen":
                aktualniProstor.vratSousedniProstor("zahrada").setZamceno(false);
                zprava = "Odemkl jsi poklop vedoucí ven";
                inventar.vyhodVec(pouzitaVec);
                break;

                case "klicDetskyPokoj":
                aktualniProstor.vratSousedniProstor("detskyPokoj").setZamceno(false);
                zprava = "Odemkl jsi dveře do dětského pokoje";
                inventar.vyhodVec(pouzitaVec);
                break;

                case "klicCela":
                aktualniProstor.vratSousedniProstor("cela").setZamceno(false);
                zprava = "Odemkl jsi celu, klíč se ti však zlomil v zámku";
                inventar.vyhodVec(pouzitaVec);
                break;

                case "klicPolicistka":
                aktualniProstor.vratSousedniProstor("kamaradova_chata").setZamceno(false);
                zprava = "Odemkl jsi zámek na masivní bráně";
                inventar.vyhodVec(pouzitaVec);
                break;

                //case "klicStolek":
                //    return vyjimkaStolek(aktualniProstor);
                //    break;               

                case "svicenNaZdi":
                zprava = vyjimkaSvicen(aktualniProstor);
                break;

                case "naboj":
                zprava = "Zbraně 101: Náboj se střílí z revolveru.";
                break;

                case "puskovyNaboj":
                zprava = "Zbraně 101: Náboj se střílí z pušky.";
                break;

                case "zrcatko":
                zprava = " Neboj, sluší ti to.";
                break;

                case "pizza":
                zprava = "Hmmm, ananasová, tvoje oblíbená.";
                inventar.vyhodVec(pouzitaVec);
                break;

                default:
                zprava = "Toto ti teď na nic není";
                break;
            }
        }
        return zprava;
    }

    /**Metoda řeší výjimku pro odemykání stolku
     * 
     * @param prostor ve kterém se hráč nachází
     * @return zpráva která se zobrazí hráči
     *
    private String vyjimkaStolek (Prostor prostor) {
    if (prostor.getNazev().equals("loznice")) {  
    vec.getNazev("stolek").setZamceno(false);
    return "Šuplík se s cvaknutím odemkl";
    inventar.vyhodVec("klicStolek");
    }
    }
     */

    /**Metoda řeší výjimku pro tajný vchod
     * 
     * @param prostor ve kterém se hráč nachází
     * @return zpráva která se zobrazí hráči
     */
    private String vyjimkaSvicen (Prostor prostor) {
        String zprava;
        if (prostor.getNazev().equals("loznice")) {  
            prostor.vratSousedniProstor("detskyPokoj").setZamceno(false);
            zprava = "Ozvalo se zaskřípění a zeď před tebou se odklopila a odhalila tajnou chodbu.";
            inventar.vyhodVec("svicenNaZdi");
        }
        else {
            zprava = "Věc zde nelze použít";
        }
        return zprava;
    }

    /**
     * Metoda vrací název příkazu
     * 
     * @return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
