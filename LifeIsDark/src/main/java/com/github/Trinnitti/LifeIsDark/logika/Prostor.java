package com.github.Trinnitti.LifeIsDark.logika;

import java.util.*;


/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Tomáš Prokeš
 * @version pro školní rok 2016/2017
 */
public class Prostor {

    private String nazev;
    private String anoPopis; // popis místnosti v případě že již byla navštívena
    private String nePopis; // popis místnosti v případě že ještě nebyla navštívena
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private List<Vec> seznamVeci;   // obsahuje seznam viditelných věcí v prostoru
    private List<Osoba> seznamOsob; // obsahuje seznam postav v prostoru
    private boolean zamceno;
    private boolean navstiveno;
    private Vec klic;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String ano, String ne, double x, double y) {
        this.nazev = nazev;
        this.nePopis = ne;
        this.anoPopis = ano;
        vychody = new HashSet<>();
        this.zamceno= false;
        this.navstiveno= false;
        seznamVeci = new ArrayList<Vec>();
        seznamOsob = new ArrayList<Osoba>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna. Rozlišuje zda už byla místnost navštívena.
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        if(navstiveno) {
            return "\n\nNacházíš se " + anoPopis + "\n" + uvodOsoby() + "\n" + uvodVeci() + "\n"
            + popisVychodu() + ".";
        }
        else {
            this.navstiveno = true;
            return "\n\nNacházíš se " + nePopis + "\n" + uvodOsoby() + "\n" + uvodVeci() + "\n"
            + popisVychodu() + ".";
        }
    }

    /**
     * * Vrací textový řetězec, který vypíše jména osob v místnosti, ale pouze v případě že v místnosti nějaké osoby jsou, 
     * * například: "V místnosti jsou následující osoby: nathan ".
     *
     * @return Jména osob - jména osob v místnosti 
     */
    private String uvodOsoby() {
        String uvod = "";
        if(!seznamOsob.isEmpty()) {
            uvod = "V místnosti jsou následující osoby:";
            for (Osoba aktualni: seznamOsob) {
                if (!uvod.equals("V místnosti jsou následující osoby:")) {
                    uvod += ", ";
                }
                uvod += " " + aktualni.getJmeno();
            }
        }
        return uvod;
    }

    /**
     * * Vrací textový řetězec, který vypíše věci v místnosti, ale pouze v případě že jsou v místnosti nějaké viditelné, 
     * * například: "V místnosti jsou následující věci: boxer".
     *
     * @return Názvy věci - věci nacházející se v místnosti 
     */
    private String uvodVeci() {
        String uvod = "";
        if(!seznamVeci.isEmpty()) {
            uvod = "V místnosti jsou následující věci:";
            for (Vec aktualni: seznamVeci) {
                if (!uvod.equals("V místnosti jsou následující věci:")) {
                    uvod += ", ";
                }
                uvod += " " + aktualni.getNazev();
            }
        }
        return uvod;
    }

    /**
     * * Vrací textový řetězec, který vypíše do kterých prostorů vedou dveře z aktuálního prostoru, ale pouze v případě že z místnosti nějaké dveře vedou, 
     * * například: "Odtuď vedou dveře do: kuchyň".
     *
     * @return Popis východů - názvů sousedních prostorů 
     */
    private String popisVychodu() {
        String uvod = "";
        if(!vychody.isEmpty()) {
            uvod = "Odtuď vedou dveře do:";
            for (Prostor aktualni: vychody) {
                if (!uvod.equals("Odtuď vedou dveře do:")) {
                    uvod += ", ";
                }
                uvod += " " + aktualni.getNazev();
            }
        }
        return uvod;
    }

    /**
     * Metoda která přidá věc do prostoru.
     * 
     * @param pridavana - přidávaná věc
     * @return true - v případě že se věc podaří vložit
     */
    public boolean pridejVec (Vec pridavana) {
        seznamVeci.add(pridavana);
        return true;
    }

    /**
     * Metoda která vrací seznam věcí v prostoru, bez věcí ve věcech.
     * 
     * @return seznam věcí
     */
    public List<Vec> getSeznamVeci () {
        return seznamVeci;
    }

    /**
     * Metoda která zjistí zda se poždaovaná věc nachází v prostoru.
     * 
     * @param nazev - název požadované věci
     * @return pozadovana - jméno věci pokud v prostoru je, jinak null
     */
    public Vec jeTuVec (String nazev) {
        Vec pozadovana = null;
        for (Vec aktualni: seznamVeci) {
            if (aktualni.getNazev().equals(nazev)) {
                pozadovana = aktualni;
                break;
            }
        }
        return pozadovana;
    }

    /**
     * Metoda která sebranou věc odstraní z místnosti, ale jen v případě že je přenositelná.
     * 
     * @param nazev - název sbírané věci
     * @return - sebraná věc
     */
    public Vec seberVec (String nazev) {
        Vec sbiranaVec = null;
        for (Vec aktualni: seznamVeci) {
            if (nazev.equals(aktualni.getNazev()) && aktualni.jePrenositelna()){
                sbiranaVec = aktualni;
                seznamVeci.remove(aktualni);
                break;
            }
        }
        return sbiranaVec;
    }

    /**
     * Metoda která přidá osobu do prostoru.
     */
    public boolean vlozOsobu (Osoba pridavana) {
        seznamOsob.add(pridavana);
        return true;
    }

    /**
     * Metoda která vrací seznam osob v prostoru.
     */
    public List<Osoba> getSeznamOsob() {
        return seznamOsob;
    }

    /**
     * Metoda která zjistí zda se osoba nachází v prostoru.
     * 
     * @param jmeno - jméno požadované osoby
     * @return pozadovana - jméno osoby pokud v místnosti je, jinak null
     */
    public Osoba jeTuOsoba(String jmeno) {
        Osoba pozadovana = null;
        for (Osoba aktualni: seznamOsob) {
            if (aktualni.getJmeno().equals(jmeno)) {
                pozadovana = aktualni;
                break;
            }
        }
        return pozadovana;
    }

    /**
     * Metoda která odebere osobu z prostoru.
     * 
     * @param odebirana - jméno osoby kterou chceme odstranit
     * return true - pokud se podaří osobu odebrat
     */
    public boolean odeberOsobu (Osoba odebirana) {
        seznamOsob.remove(odebirana);
        return true;
    }

    /**
     * Metoda která odebere osobu z prostoru, ale jen v případě že je přenositelná.
     * 
     * @param jmeno - jméno osoby kterou chceme odstranit
     * @return - verbovana postava
     */
    public Osoba verbujOsobu (String jmeno) {
        Osoba verbovanaOsoba= null;
        for (Osoba aktualni: seznamOsob) {
            if (jmeno.equals(aktualni.getJmeno()) && aktualni.jeKamaradska()){
                verbovanaOsoba = aktualni;
                seznamOsob.remove(aktualni);
                break;
            }
        }
        return verbovanaOsoba;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        if (nazevSouseda == null) {
            return null;
        }
        for (Prostor sousedni : vychody) {
            if (sousedni.getNazev().equals(nazevSouseda)) {
                return sousedni;
            }
        }
        return null;
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Metoda která odemkne či zamkne zámek.
     * 
     * @param zamceno - pokud chceme zamknout: true
     */
    public void setZamceno (boolean zamceno) {
        this.zamceno = zamceno;
    }

    /**
     * Metoda která zjišťuje zda je odemčeno či zamčeno.
     * 
     *@return true - je zamčeno
     */
    public boolean jeZamceno () {
        return zamceno;
    }

    /**
     * Metoda která přiřadí klíč zamčenému prostoru.
     * 
     * @param klic - klíč od určitého prostoru
     */
    public void setKlic(Vec klic) {
        this.klic = klic;
    }

    /**
     * Metoda která vrací klíč přiřazený konkrétnímu prostoru.
     * 
     * @return klíč
     */
    public Vec getKlic() {
        return klic;
    }

    /**
     * Metoda která řiká, zda hráč v prostoru již byl.
     * 
     * @return navstiveno - true nebo false, podle toho jestli už jsi v místnosti byl
     */
    public boolean getNavstiveno() {
        return this.navstiveno;
    }

	public String seznamVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
             vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
	
}
