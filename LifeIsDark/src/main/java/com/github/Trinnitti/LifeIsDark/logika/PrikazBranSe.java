/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

import java.util.*;
/*******************************************************************************
 * Instance třídy {@code PrikazBranSe} implementují pro hru příkaz Braň se
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-20
 */
public class PrikazBranSe implements IPrikaz
{
    private static final String NAZEV = "branSe";
    private Hra hra;
    private HerniPlan herniPlan;
    private Inventar inventar;
    private List<Osoba> seznamOsob;

    /***************************************************************************
     * Konstruktor
     */
    public PrikazBranSe(Hra hra) {
        this.hra = hra;
        this.herniPlan = hra.getHerniPlan();
        inventar = herniPlan.getInventar(); 
        seznamOsob = new ArrayList<Osoba>();
    }

    /**
     * Metoda která použije věc z inventáře v případě že na tebe někdo zaútočí.
     * 
     * @param - používaná věc
     * @return - zpráva která se vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "\nPěstmi se bohužel neubráníš. Napiš čím se chceš bránit.";
        }
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        String zprava;
        String pouzitaVec = parametry[0];
        Inventar inventar = herniPlan.getInventar();
        if (aktualniProstor.getNazev().equals("koupelna") || aktualniProstor.getNazev().equals("loznice") 
        || aktualniProstor.getNazev().equals("detskyPokoj")) {

            if (inventar.getVec(pouzitaVec) == null) {           
                zprava = "\nTohle u sebe nemáš.";
            }
            else {

                switch (pouzitaVec) {
                    case "boxer":
                    zprava = vyjimkaBoxer(aktualniProstor);
                    break;

                    case "nuz":
                    zprava = vyjimkaNuz(aktualniProstor);
                    break;

                    case "revolver":
                    zprava = vyjimkaRevolver(aktualniProstor);
                    break;

                    case "puska":
                    zprava = vyjimkaPuska(aktualniProstor);
                    break;

                    case "zrcatko":

                    zprava = "\nNamířil jsi na útočníka zrcátko, ale on se ke tvému překvapení svého odrazu\n"
                    +"nelekl. Nebo, co ji od toho vlastně čekal?";
                    herniPlan.setProhra(true);
                    break;

                    case "naboj":

                    zprava = "\nNáboj je lepší nejdříve nabít do zbraně, ale to už je teď jedno.";
                    herniPlan.setProhra(true);
                    break;

                    case "puskovyNaboj":

                    zprava = "\nNáboj je lepší nejdříve nabít do zbraně, ale to už je teď jedno.";
                    herniPlan.setProhra(true);
                    break;

                    case "pizzu":

                    zprava = "\nMožná kdyby to nebyla pizza, kterou si původně ukradl jemu, třeba by ho to uklidnilo.";
                    herniPlan.setProhra(true);
                    break;

                    default:

                    zprava = "\nKlíčem se bohužel neubráníš.";
                    herniPlan.setProhra(true);
                    break;
                }
            }
        }
        else {
            zprava = "\nNikdo na tebe neútočí, tak čemu by ses bránil?";
        }
        return zprava;
    }

    /**Metoda řeší výjimku pro obranu nožem
     * 
     * @param prostor ve kterém se hráč nachází
     * @return zpráva která se zobrazí hráči
     */
    private String vyjimkaNuz (Prostor prostor) {
        String zprava;
            if (prostor.getNazev().equals("koupelna")) {  
                herniPlan.setProhra(true);
                return "\nNež jsi stihl nůž vůbec vytáhnout, kluk stiskl poušť a ty jsi padl mrtvý k zemi.";
            }
            if (prostor.getNazev().equals("detskyPokoj")) { 
                //odstranění postavy
                return "\nNenápadně jsi vytáhl z poza opasku nůž a zabodl ho klukovi do boku, ten tě hned pustil\n"
                + "a padl v bolestech k zemi. Ten už žádně nebezpečí nepředstavuje.";
            }
            if (prostor.getNazev().equals("loznice")) { 
                //odstranění postavy
                return "\nV okamžiku kdy po tobě muž skočil jsi před sebe nastavil nůž. Na tebe dopadla již jen\n"
                + "mužova mrtvola.";
            } 
        return "";
    }

    /**Metoda řeší výjimku pro obranu boxerem
     * 
     * @param prostor ve kterém se hráč nachází
     * @return zpráva která se zobrazí hráči
     */
    private String vyjimkaBoxer (Prostor prostor) {
        String zprava;
            if (prostor.getNazev().equals("koupelna")) {  
                herniPlan.setProhra(true);
                zprava =  "\nNež jsi stihl boxer vůbec vytáhnout, kluk stiskl poušť a ty jsi padl mrtvý k zemi.";
            }
            if (prostor.getNazev().equals("detskyPokoj")) {  
                herniPlan.setProhra(true);
                zprava = "\nNenápadně jsi vytáhl z kapsy boxer a vrazil ho klukovi do boku, ten zakřičel bolestí\n"
                + "a okamžitě tě podřízl.";
            }
            if (prostor.getNazev().equals("loznice")) {  
                //odstranění postavy
                zprava =  "\nV okamžiku kdy po tobě muž skočil jsi se ohnal boxerem. Ozvalo se zakřupání a muž na tebe\n"
                + "dopadl v bezvědomí.";
            }
        return "";
    }

    /**Metoda řeší výjimku pro obranu revolverem
     * 
     * @param prostor ve kterém se hráč nachází
     * @return zpráva která se zobrazí hráči
     */
    private String vyjimkaRevolver (Prostor prostor) { 
        String zprava;
            if (inventar.getVec("naboj") != null) {
                if (prostor.getNazev().equals("koupelna")) {  
                    zprava ="\nV rychlosti vytáhneš revolver a vystřelíš. Kluk stihl vystřelit taky ale netrefil.\n"
                    +"Ty ano, kluk padl mrtvý k zemi.";
                    //odstranit postavu z prostoru + přidat do prostoru zbraň
                }
                if (prostor.getNazev().equals("detskyPokoj")) {  
                    herniPlan.setProhra(true);
                    zprava = "\nChtěl jsi z poza opasku vytáhnout revolver, kluk si toho však všiml a hned tě podřízl.";
                }
                if (prostor.getNazev().equals("loznice")) {  
                    zprava = "\nV okamžiku kdy po tobě muž skočil jsi stiskl spoušť. Muž padl mrtvý k zemi";
                    //odstranit postavu z prostoru
                }
            }
            else {
                herniPlan.setProhra(true);
                zprava = "\nStiskl si spoušť a...nic se nestalo. Jakto? Nemáš nabito. Smrtelná chyba.";
            } 
        return "";
    }

    /**Metoda řeší výjimku pro obranu puskou
     * 
     * @param prostor ve kterém se hráč nachází
     * @return zpráva která se zobrazí hráči
     */
    private String vyjimkaPuska (Prostor prostor) {
        String zprava;
            if (inventar.getVec("puskovyNaboj") != null) {
                if (prostor.getNazev().equals("koupelna")) {
                    herniPlan.setProhra(true);
                    zprava = "\nSundal jsi z ramene pušku a vystřelil jsi na kluka, ale netrefil jsi, kluk ano a ty ses\n."
                    + "svalil mrtvý k zemi.";
                }
                if (prostor.getNazev().equals("detskyPokoj")) {  
                    herniPlan.setProhra(true);
                    zprava = "\nVážně jsi se snažil být nenápadný zatímco jsi zvedal pušku a snažil se jí klukovi,\n"
                    +"který ti drží nůž u krku, otočit do obličeje. Ovšem to by kluk musel být slepý, podřízl tě.  ";
                }
                if (prostor.getNazev().equals("loznice")) {  
                    zprava = "\nV okamžiku kdy po tobě muž skočil jsi stiskl spoušť. Muž padl mrtvý k zemi.";
                    //odstranit postavu z prostoru
                }
            }
            else {
                herniPlan.setProhra(true);
                zprava = "\nStiskl si spoušť a...nic se nestalo. Jakto? Nemáš nabito. Smrtelná chyba.";
            } 
        return "";
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
