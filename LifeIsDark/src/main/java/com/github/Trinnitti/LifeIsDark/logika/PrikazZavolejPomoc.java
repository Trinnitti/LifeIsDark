/* UTF-8 codepage: Příliš žluťoučký ků�? úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */

package com.github.Trinnitti.LifeIsDark.logika;

/*******************************************************************************
 * Instance třídy {@code PrikazZavolejPomoc} implementuje pro hru příkaz Zavolej Pomoc
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-21
 */
public class PrikazZavolejPomoc implements IPrikaz
{
    private static final String NAZEV = "zavolejPomoc";
    private Hra hra;
    private HerniPlan herniPlan;
    private Spolecnost spolecnost;

    /***************************************************************************
     * Konstruktor
     */
    public PrikazZavolejPomoc(Hra hra)  {
        this.hra = hra;
        this.herniPlan = hra.getHerniPlan();
        spolecnost = herniPlan.getSpolecnost();
    }

    /**
     * Metoda kterou je potřeba pro zavolání pomoci.
     * Voláš pomocí mobilu, který máš u sebe automaticky, ačkolik se nezobrazuje v inventáři a ani nezabírá místo
     * Pro použití metody je potřeba být ve správném prostoru. 
     * 
     *@param - jmeno osoby, které budeš volat o pomoc (v tomto případně policistka)
     *@return - zprava která se vypíše hráči.
     */
    @Override
    public String provedPrikaz (String... parametry){   
        if (parametry.length > 0) {
            return "Komu chceš zavolat? Nejlepší by bylo asi policajtům.";
        }

        String zprava;
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Spolecnost spolecnost = herniPlan.getSpolecnost();

        if (aktualniProstor.getNazev().equals("mytina")){
            zprava = "Došel jsi až na mýtinu a vytočil 156, z mobilu se ozval příjemný ženský hlas:\n"
            +"'Městská policie, jak vám můžu pomoct?' Rychle jsi vychrlil co jsi objevil a policistka\n"
            +"na druhé straně tě ujistila že za chvíli přijede. A opravdu přijela, v okamžiku kdy jsi se\n"
            +"k ní otočil zády aby jsi jí úkázal dům, jsi dostal něčím těžkým po hlavě a probudil se až v cele.\n"
            +"Za mřížemi stojí policistka a jen se ti směje. Tak odsuď se už nikdy nedostaneš.";
            herniPlan.setProhra(true);
        }
        else {        
            zprava = "Bohužel namáš mobilní signál, to žádná pomoc nepřijede";
        }

        if (aktualniProstor.getNazev().equals("krovi")){          
            zprava = "Skrčil jsi se do křoví a vytáhl mobil aby jsi mohl zavolat policii.\n"
            +"V tu chvíli se ozvalo zlověstná praskání a zem se po tebou propadla.\n"
            +"Stihl jsi se chytnou kořenů, snažíš se vytáhnout nahoru, ale zem se pod tebou drolí\n"
            +"a kořeny trhají, jediná tvoje možnost je pustit se dolů, zem je relativně nízko,\n"
            +"pod sebou vidíš sklep přeměněný na vězení.";
        }
        else {        
            zprava = "Bohužel namáš mobilní signál, to žádná pomoc nepřijede";
        }

        if (aktualniProstor.getNazev().equals("zahrada")){ 
            if (herniPlan.getSpolecnost().getJmeno().equals("chloe")) {
                zprava = "Konečně se ti podařilo dostat se společne s Chloe ven. Hned zkontroluješ signál.\n"
                +"Sláva! Konečně můžeš zavolat pomoc. Na lince se ozve ženský hlas a ujistí tě že pomoc již je na cestě.\n"
                +"Za chvíli přijede policistka které oba začnete líčit svůj příběh. K vašemu překvapení\n"
                +"na vás ale vytáhne zbraň. Když si policistka všimne Chloe, skloní svoji zbraň a jde směrem k ní,\n" 
                +"najednou se rozbrečí. Přes brek jde policistce těžko rozumět, ale pochopíš že si Chloe spletla se\n"
                +" svojí dcerou která umřela jako malá. Rozhodně si ale s Chloe neplánujete vyslechnout celý příběh\n"
                +"a využijete situace a utíkáte co nejrychleji pryč";
            }
            else {
                herniPlan.setProhra(true);
                zprava = "Konečně se vám podařilo dostat se ven. Hned zkontroluješ signál.\n"
                +"Sláva! Konečně můžeš zavolat pomoc. Na lince se ozve ženský hlas a ujistí tě že pomoc již je na cestě.\n"
                +"Za chvíli přijede policistka které oba začnete líčit svůj příběh. K vašemu překvapení\n"
                +"na vás ale vytáhne zbraň. Policistka k vám přijde a nasadí vám pouta, nevzdorujete, jen se snažíte vše vysvětlit.\n"
                +"Najendou ucitíš velkou bolest na zátylku a následně se probudíš v jedné cel. V tu chvíli ti dojde že policistka\n"
                +" spolupracuje s lidmi z domu, teď už je ti to však na nic, od tuď se již nikdy nedostaneš";
            }
        }
        else {        
            zprava = "Bohužel namáš moilní signál, to žádná pomoc nepřijede";
        }
        return zprava;
    }


    /**
     *  Metoda vrací název příkazu.
     *  
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
