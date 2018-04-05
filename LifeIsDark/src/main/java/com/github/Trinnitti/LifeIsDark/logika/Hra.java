package com.github.Trinnitti.LifeIsDark.logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Tomáš Prokeš
 *@version    pro školní rok 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVerbuj(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazInventar(herniPlan)); 
        platnePrikazy.vlozPrikaz(new PrikazSpolecnost(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVyhod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOpust(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazJdi(this));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(this));
        platnePrikazy.vlozPrikaz(new PrikazBranSe(this));       
        platnePrikazy.vlozPrikaz(new PrikazZavolejPomoc(this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazMluv(this));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(this));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte ve hře Life is Dark!\n" +
        "Byl jsi pozván ke kamarádovi na párty. Párty se koná u něj na chatě, která je uprostřed hlubokého\n" +
        "lesa, jelikož celou noc pršelo, přístupová cesta byla zatopená, proto ses rozhodl jet lesní cestou.\n" +
        "Naneštěstí jsi však už několik kilometrů od chaty zapadl a musel pokračovat pěšky. Cestou jsi uslyšel\n" +
        "ženský křik, ze kterého ti tuhla krev v žilách, hned ses rozhodl běžet se podívat co se děje. Najednou\n" +
        "se před tebou zpoza stromů vynořil polorozpadlý kamenný dům, za oknem vidíš záchvěvy světla a\n" +
        "slyšíš volání o pomoc.\n" +
        "\n" +
        "Pokud nevíš co dělat, napiš [napoveda]\n" +
        "\n" +
        herniPlan.getAktualniProstor().dlouhyPopis() + "\n";
    }

    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "\nDoufám že si ti hra líbila a díky, že jsi si zahrál.";
    }

    /** 
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
            if (herniPlan.getAktualniProstor()== herniPlan.getViteznyProstor() ) {
                setKonecHry(true);
                return "Úspěšně jsi společně s Chloe dorazil ke kamarádovi na chatu, ostatní tu již jsou,\n" +
                "Když vidí v jakém stavu oba jste, zděsí se. Vy jste se však již na cestě ze strachu dohodli, že svůj\n" +
                "příběh nebudete nikde vyprávět. Proto jste si vymysleli uvěřitelný příběh o bouračce, aby vás netrápilo\n" +
                "svědomí, poslali jste anonymní tip na policii. Párty může začít, a důvod oslovovat je velký\n" +
                "\n" +
                "Ráno se s Chloe probudíte s novým pohledem na život, štěstí z nového života snad jen může zkazit\n" +
                "zpráva z televize o nalezených 11 tělech v domě uprostřed lesa, včetně policistky a mrtvolky dítěte na zahradě.";
            }
        }
        else {
            textKVypsani="\nTak tenhle příkaz neexistuje, zkus se podívat do nápovědy. ";
        }

        if (herniPlan.jeVyhra()){
            konecHry = true;
            return "Úspěšně jsi společně s Chloe dorazil ke kamarádovi na chatu, ostatní tu již jsou,\n" +
            "Když vidí v jakém stavu oba jste, zděsí se. Vy jste se však již na cestě ze strachu dohodli, že svůj\n" +
            "příběh nebudete nikde vyprávět. Proto jste si vymysleli uvěřitelný příběh o bouračce, aby vás netrápilo\n" +
            "svědomí, poslali jste anonymní tip na policii. Párty může začít, a důvod oslovovat je velký\n" +
            "\n" +
            "Ráno se s Chloe probudíte s novým pohledem na život, štěstí z nového života snad jen může zkazit\n" +
            "zpráva z televize o nalezených 11 tělech v domě uprostřed lesa, včetně policistky a mrtvolky dítěte na zahradě.";
        }
        if (herniPlan.jeProhra()){
            konecHry = true;
            return "Umřel jsi, vic už řešit nemusíš.\n";
        } 
        return textKVypsani;
    }

    /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }

}

