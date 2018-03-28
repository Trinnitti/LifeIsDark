/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code PrikazMluv} implementují pro hru příkaz Mluv.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-20
 */
public class PrikazMluv implements IPrikaz
{

    private static final String NAZEV = "mluv";
    private Hra hra;
    private HerniPlan herniPlan;

    /***************************************************************************
     * Konstruktor třídy
     */
    public PrikazMluv(Hra hra) {
        this.hra = hra;
        this.herniPlan = hra.getHerniPlan();
    }

    /**
     * Metoda která vypíše odpověď požadované postavy, to samozřejmě jen v případě, že se postava nachází v místnosti.
     * 
     * @param - jméno osoby
     * @return - odpověd osoby
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (jméno osoby), tak ....
            return "S kým chceš mluvit?";
        }

        String jmeno = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Osoba osoba = aktualniProstor.jeTuOsoba(jmeno);

        if (osoba == null) {
            return "Tato osoba tu není";
        }
        else {
            if (osoba.getVec() !=null) {
                // Pokud u sebe měla osoba nějaký předmět, po rozhovoru ho vloží do prostoru.
                osoba.getVec().setViditelna(true);
                aktualniProstor.pridejVec(osoba.getVec());
                osoba.odeberVec();
            }
            if (osoba.getJmeno().equals("malyKluk") || osoba.getJmeno().equals("mladyKluk") || osoba.getJmeno().equals("starsiMuz")) {
                herniPlan.setProhra(true);
                return "nevyplatí se mluvit na postavu která tě chce zabít.";
            }
            return osoba.getPromluva();
        }
    }

    /**
     *  Metoda vrací název příkazu 
     *  
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
