package com.github.Trinnitti.LifeIsDark.logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Tomáš Prokeš
 *@version    pro školní rok 2016/2017
 *  
 */
class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;

    /**
     *  Konstruktor třídy
     *  
     *  @param platnePrikazy seznam příkazů,
     *                       které je možné ve hře použít,
     *                       aby je nápověda mohla zobrazit uživateli. 
     */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Tvým úkolem je dostat se živý ke kamarádovi na chatu.\n"
        + "Cestou ses však připletl do nechtěné situace, ze které se nyní musíš dostat.\n"
        + "Je jen na tobě jak se z ní dostaneš a jak se u toho zachováš,"
        + "Dostaneš se z domu živý? Jak se zachováš? A co tvé svědomí?"
        + "Mysli na to že jen několik rozhodnutí vede k tomu, aby ses dostal z domu živý"
        + "/n"
        + "Při tvé cestě ke svobodě potřebuješ určité předměty, proto celý dům důkladně prohledej"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
