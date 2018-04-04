/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code Osoba} představují osoby ve hře.
 * The {@code Osoba} class instances represent characters in the game.
 *
 * @author  Tomáš prokeš
 * @version 0.00.0000 — 2017-05-15
 */
public class Osoba
{

    private String jmeno;
    private String promluva1;
    private String promluva2;
    private boolean kamaradska;
    private int oslovena;
    private Vec vecOsoby; //Veci která má postava u sebe
    private String obrazek;

    /***************************************************************************
     * Konstruktor
     */
    public Osoba(String jmeno, String promluva1, String promluva2, String obrazek, boolean kamaradska)
    {
        this.jmeno = jmeno;
        this.promluva1 = promluva1;
        this.promluva2 = promluva2;
        this.kamaradska = kamaradska;
        this.oslovena = 0;
        this.obrazek = obrazek;
    }

    /*******
     * Metoda která vrací jméno osoby.
     * 
     * @return - Jmeno osoby
     */
    public String getJmeno(){
        return jmeno; 
    }

    /**
     * Metoda která vrací promluvu osoby, rozlišuje zda bylo na osoby již mluveno.
     * 
     * return String Promluva osoby
     */
    public String getPromluva () {
        if (oslovena == 0) {
            oslovena++;
            return promluva1;
        }
        else {
            return promluva2;
        }
    }

    /**
     * Metoda která vloží osobě danou věc.
     * 
     * @param vecOsoby - vkládaná věc
     */
    public void vlozVec (Vec vecOsoby) {
        this.vecOsoby = vecOsoby;
    }

    /**
     * Metoda která vrací věc osoby.
     * 
     * @return vrátí věc osoby
     */
    public  Vec getVec () {
        return this.vecOsoby;
    }

    /**
     * Metoda která odebere osobě věc kterou měla u sebe.
     */
    public void odeberVec () {
        this.vecOsoby = null;
    }

    /**
     * Metoda která nastavuje zda jsi s postavou již mluvil
     * 
     * @return true pokud jsi s ní už mluvil
     */
    public int getOslovena () {
        return this.oslovena;
    }

    /**
     * Metoda která vrací zda je osoba kamarádská.
     * 
     * @return vrací true pokud je kamarádská
     */
    public boolean jeKamaradska() {
        return this.kamaradska;
    }

    /**
     * Metoda která nastaví zda je osoba kamarádská.
     *
     * @param jeKamaradska  -true, pokud je kamarádská
     */
    public void setKamaradska(boolean jeKamaradska) {
        this.kamaradska = jeKamaradska;
    }
    
    public String getObrazek() {
        return obrazek;
    }
}
