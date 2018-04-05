package com.github.Trinnitti.LifeIsDark.logika;

import java.util.Observable;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *  
 *  Vytváří všechny postavy a věci ve hře a rozmísťuje je do prostorů. Také jim nastavuje parametry.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Tomáš Prokeš
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan extends Observable {

    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Inventar inventar;
    private Spolecnost spolecnost;
    private boolean vyhra = false;
    private boolean prohra = false;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví les. Vytváří taktéž inventář s společnost
     */
    public HerniPlan() {
        zalozProstoryHry();
        inventar = new Inventar();
        spolecnost = new Spolecnost();
    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů, také je může zamknout a vytvořit jim klíče
     *  Také vytváří osoby a věci které rovnou rozmístí
     *  Jako výchozí aktuální prostor nastaví temný les.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory i s krátkým popisem
        Prostor temnyLes = popisProstor("temnyLes");
        Prostor krovi = popisProstor("krovi");
        Prostor mytina = popisProstor("mytina");
        Prostor kamennyDum = popisProstor("kamennyDum");
        Prostor chodba = popisProstor("chodba");
        Prostor kuchyn = popisProstor("kuchyn");
        Prostor obyvaciPokoj = popisProstor("obyvaciPokoj");
        Prostor koupelna = popisProstor("koupelna");
        Prostor schodiste = popisProstor("schodiste");
        Prostor zachod = popisProstor("zachod");
        Prostor loznice = popisProstor("loznice");
        Prostor detskyPokoj = popisProstor("detskyPokoj");
        Prostor sklep = popisProstor("sklep");
        Prostor sklepniChodba = popisProstor("sklepniChodba");
        Prostor vezeni = popisProstor("vezeni");
        Prostor cela = popisProstor("cela");
        Prostor pracovna = popisProstor("pracovna");
        Prostor sklad = popisProstor("sklad");
        Prostor zahrada = popisProstor("zahrada");
        Prostor kamaradovaChata = popisProstor("kamaradovaChata");

        Prostor[] poleProstoru = {temnyLes, krovi, mytina, kamennyDum, chodba, kuchyn, obyvaciPokoj, koupelna, 
                schodiste, zachod, loznice, detskyPokoj, sklep, sklepniChodba, vezeni,
                cela, pracovna, sklad, zahrada, kamaradovaChata};

        // přiřazují se průchody mezi prostory (sousedící prostory)
        temnyLes.setVychod(krovi);
        temnyLes.setVychod(mytina);
        temnyLes.setVychod(kamennyDum);
        krovi.setVychod(vezeni);
        kamennyDum.setVychod(chodba);
        chodba.setVychod(kuchyn);
        chodba.setVychod(kamennyDum);
        chodba.setVychod(obyvaciPokoj);
        chodba.setVychod(schodiste);
        chodba.setVychod(koupelna);
        chodba.setVychod(sklep);
        kuchyn.setVychod(chodba);
        kuchyn.setVychod(obyvaciPokoj);
        obyvaciPokoj.setVychod(kuchyn);
        obyvaciPokoj.setVychod(chodba);
        koupelna.setVychod(chodba);
        schodiste.setVychod(chodba);
        schodiste.setVychod(zachod);
        schodiste.setVychod(loznice);
        schodiste.setVychod(detskyPokoj);
        zachod.setVychod(schodiste);
        loznice.setVychod(schodiste);
        detskyPokoj.setVychod(schodiste);
        detskyPokoj.setVychod(loznice);
        loznice.setVychod(detskyPokoj);
        sklep.setVychod(sklepniChodba);
        sklep.setVychod(zahrada);
        zahrada.setVychod(kamaradovaChata);
        sklepniChodba.setVychod(sklep);
        sklepniChodba.setVychod(zahrada);
        sklepniChodba.setVychod(sklad);
        sklepniChodba.setVychod(vezeni);
        sklad.setVychod(sklepniChodba);
        vezeni.setVychod(sklepniChodba);
        vezeni.setVychod(cela);
        vezeni.setVychod(pracovna);
        pracovna.setVychod(vezeni);
        cela.setVychod(vezeni);

        aktualniProstor = temnyLes;  // hra začíná v temném lese  
        viteznyProstor = kamaradovaChata ;

        //založí se jednotlivé osoby a jsou popsány hráči
        Osoba malyKluk = popisOsobu("malyKluk");
        Osoba mladyKluk = popisOsobu("mladyKluk");
        Osoba velmiStaraPani = popisOsobu("velmiStaraPani");
        Osoba starsiMuz = popisOsobu("starsiMuz");
        Osoba nathan = popisOsobu("nathan");
        Osoba alex = popisOsobu("alex");
        Osoba chloe = popisOsobu("chloe");
        Osoba david = popisOsobu("david");
        Osoba bara = popisOsobu("bara");
        Osoba policistka = popisOsobu("policistka");

        Osoba[] poleOsob = {malyKluk, mladyKluk, velmiStaraPani, starsiMuz, nathan, alex, chloe, david, bara, policistka};

        // vytvoří se jednotlivé věci a jsou popsány hráči
        //Vec klicSklep = popisVec("klicSklep");
        //Vec policistkaKlic = popisVec("policistkaKlic");
        //Vec univerzalniKlic = popisVec("univerzalniKlic");
        //Vec klicStolek = popisVec("klicStolek");
        //Vec klicVen = popisVec("klicVen");
        //Vec klicDetskyPokoj = popisVec("klicDetskyPokoj");
        //Vec klicCela = popisVec("klicCela");
        //Vec boxer = popisVec("boxer");
        //Vec puska = popisVec("puska");
        //Vec nuz = popisVec("nuz");
        //Vec revolver = popisVec("revolver");
        //Vec naboj = popisVec("naboj");
        //Vec zrcatko = popisVec("zrcatko");
        //Vec pizza = popisVec("pizza");
        //Vec puskovyNaboj = popisVec("puskovyNaboj");
        //Vec rakev = popisVec("rakev");
        //Vec spolecenskeBoty = popisVec("spolecenskeBoty");
        //Vec tenisky = popisVec("tenisky");
        //Vec drez = popisVec("drez");
        //Vec kos = popisVec("kos");
        //Vec odpadky = popisVec("odpadky");
        //Vec linka = popisVec("linka");
        //Vec lednice = popisVec("lednice");
        //Vec krb = popisVec("krb");
        //Vec invalidniVozik = popisVec("invalidniVozik");
        //Vec staraPani = popisVec("staraPani");
        //Vec nadobaSHrackami = popisVec("nadobaSHrackami");
        //Vec plysovyMedved = popisVec("plysovyMedved");
        //Vec malaPokladnicka = popisVec ("malaPokladnicka");
        //Vec nocniStolek = popisVec("nocniStolek");
        //Vec fotka = popisVec("fotka");
        //Vec svicenNaZdi = popisVec("svicenNaZdi");
        //Vec splachovaciNadoba = popisVec("splachovaciNadoba");
        //Vec voda = popisVec("voda");
        //Vec skrinka = popisVec("skrinka");
        //Vec bedna = popisVec("bedna");

        //poleVeci = new Vec[]{klicSklep, policistkaKlic, univerzalniKlic, klicStolek, klicVen, klicDetskyPokoj, klicCela, boxer,
        //                  puska, nuz, revolver, naboj, zrcatko, pizza, puskovyNaboj, rakev, spolecenskeBoty, tenisky,
        //                  drez, kos, odpadky, linka, lednice, krb, invalidniVozik, staraPani, nadobaSHrackami,
        //                  plysovyMedved, malaPokladnicka, nocniStolek, fotka, svicenNaZdi, splachovaciNadoba, voda, 
        //                  skrinka, bedna};

        //přidá do hry klíče 
        Vec klicSklep = new Vec ("klicSklep","klicSklep", "klic.jpg", true, true);
        Vec klicPolicistka = new Vec ("klicPolicistka","klicPolicistka", "klic.jpg", true, false);
        Vec klicUniverzalni = new Vec ("klicUniverzalni","klicUniverzalni", "klic.jpg", true, false);
        Vec klicStolek = new Vec ("klicStolek","klicStolek", "klic.jpg", true, false);
        Vec klicVen = new Vec ("klicVen","klicVen", "klic.jpg", true, false);
        Vec klicDetskyPokoj = new Vec ("klicDetskyPokoj","klicDetskyPokoj", "klic.jpg", true, false);
        Vec klicCela = new Vec ("klicCela","klicCela", "klic.jpg", true, false);
        Vec svicenNaZdi = new Vec ("svicenNaZdi", "svicenNaZdi", "svicen.jpg", true, true);

        //přidá do hry věci ve kterých budou schované klíče
        Vec krb = new Vec ("krb", "krb", "krb.jpg", false, true);
        Vec invalidniVozik = new Vec ("invalidniVozik", "invalidniVozik", "invalidniVozik.jpg", false, true);
        Vec staraPani = new Vec ("staraPani", "staraPani", "oldLady.jpg", false, false);
        Vec nocniStolek = new Vec ("nocniStolek", "nocniStolek", "stolek.jpg", false, true);
        Vec voda = new Vec ("voda", "voda", "zachod.jpg", false, true);
        Vec skrinka = new Vec ("skrinka", "skrinka", "skrinka.jpg", false, true);

        //rozmístí věci ve kterých jsou věci do místností
        obyvaciPokoj.pridejVec(krb);
        obyvaciPokoj.pridejVec(invalidniVozik);
        loznice.pridejVec(nocniStolek);
        zachod.pridejVec(voda);
        pracovna.pridejVec(skrinka);

        //rozmístí klíče 
        invalidniVozik.vlozVec(staraPani);
        krb.vlozVec(klicStolek);
        staraPani.vlozVec(klicUniverzalni);
        nocniStolek.vlozVec(klicSklep);
        voda.vlozVec(klicDetskyPokoj);
        skrinka.vlozVec(klicCela);
        skrinka.vlozVec(klicVen);
        loznice.pridejVec(svicenNaZdi);
        policistka.vlozVec(klicPolicistka);
        

        //Zamkne požadované prostory
        zahrada.setZamceno(true);
        sklep.setZamceno(true);
        //kamaradovaChata.setZamceno(true);
        //nocniStolek.setZamceno(true);
        detskyPokoj.setZamceno(true);
        cela.setZamceno(true);

        //Přiřadí klíče prostorům
        zahrada.setKlic(klicVen);
        //zahrada.setKlic(univerzalniKlic);
        sklep.setKlic(klicSklep);
        //sklep.setKlic(univerzalniKlic);
        nocniStolek.setKlic(klicStolek);
        detskyPokoj.setKlic(klicDetskyPokoj);
        //detsky_pokoj.setKlic(univerzalniKlic);
        detskyPokoj.setKlic(svicenNaZdi);
        cela.setKlic(klicCela);
        //cela.setKlic(univerzalniKlic);
        //kamaradovaChata.setKlic(klicPolicistka);
        //nocniStolek.setKlic(klicStolek);

        rozmistiOsobyVeci(poleProstoru, poleOsob);
    }

    /**
     * Metoda která popisuje jednotlivé prostory
     * 
     * @param nazev - jméno prostoru
     * @return - vrátí nově popsaný prostor
     */
    private Prostor popisProstor (String nazev) {
        Prostor prostor = null;
        switch (nazev) {
            case "temnyLes":
            prostor = new Prostor("temnyLes","", 
                "v temném lese. Vidíš před sebou polorozpadlý kamenny dům, je z velké části porostlý břečťanema a některá\n"
                + "okna jsou vymlácená, kdyby jsi zkrze jedno z oken neviděl záchvěvy světla, určitě by\n"
                + "sis řekl že je opuštěný. Najednou se znovu ozval ten zoufalý křik, který tě k domu přivedl.\n"
                + "Před tebou se rozkládají masivní dřevěné dveře s klepadlem ve tvaru gryfa, jsou pootevřené.\n"
                + "\n"
                + "Rozhodneš se vydat dovnitř, či poodejdeš aby jsi mohl zavolat policii?",0,0);
            break;

            case "krovi":
            prostor = new Prostor("krovi","", 
                "u křoví. Došel jsi k nedalekému křoví, vapadá jako dobré krytí, od tuď můžeš zavolat pomoc.\n"
                + "V tu chvíli se však pod tebou země propadne a padáš do vezeni"
                + "\n"
                + "Zavolej policii",0,0);
            break;

            case "mytina":
            prostor = new Prostor("mytina","", 
                "na mýtině. Došel jsi až na nedalekou mýtinu, zde to už vypadá bezpečně, můžeš zavolat pomoc.\n"
                + "\n"
                + "Zavolej policii",0,0);
            break;

            case "kamennyDum":
            prostor = new Prostor("kamennyDum","v kamenném domě. nic se nezměnilo od té doby co jsi tu byl naposledy", 
                "v kamenném domě. V okamžiku kdy vejdeš dovnitř tě do nosu praští velice silný zápach, kombinace zatuchliny\n"
                + "a...smrti? Všude jsou silné vrstvy prachu a pavučiny. \n"
                + "Nacházíš se jen v malé předsíni, jediné co vidíš je starý botník, který drží pohromadě\n"
                + "jen silou vůle, skoro se na něj bojíš sáhnout. Leží u něj dvoje boty.\n"
                +"Další čeho si všimneš je že ve vnitř není mobilní signál"
                + "\n"
                + "Otočíš se zpět na dveře, ty jsou zavřené, ty jsi je však nezavíral, zkoušíš s nimi\n"
                +"lomcovat, drží však jako přibité. Jediná šance je pokračovat dveřmi na chodbu",0,0);
            break;

            case "chodba":
            prostor = new Prostor("chodba","na chodbě, vše při starém.", 
                "na chodbě. Vešel jsi na dlouhou chodbu, obrovský lustr s několika žárovkama nad tebou problikavá,\n"
                + "což dodává domu na zlovolnosti. Jediný zvuk který slyšíš jsou velké hodiny stojící na\n"
                + "konci chodby. Na zdech jsou pověšené různé obrazy, některé znázorňují krajinu a krásný\n"
                + "dům, možná ten ve kterém se nacházíš ale v lepších časech? Na zdi jsou také portréty,\n"
                + "tváře ani jména napsaná na rámech ti však nic neříkají."
                + "\n"
                + "Vydáš se jedněmi ze čtyř dveří? Čí snad využiješ schody?",0,0);
            break;

            case "kuchyn":
            prostor = new Prostor("kuchyn","v kuchyni. Pořád stejný bordel.", 
                "v kuchyni. V místnosti je obrovský nepořádek, všude po lince i ve dřezu je špinavé nádobí s hnijícím\n"
                + "jídlem. V rohu místnosti je přeplněný odpadkový koš, který opravdu nechuťně smrdí.\n"
                + "Zaujme tě také lednice, která se otevřená a na zbytcích jídla které vypadli na zem\n"
                + "hodují krysy. Jediné co vypadá čerstvě je pizza na stole.\n"
                + "\n"
                + "Budeš pokračovat dál? Nebo se vrátíš odkuď jsi přišel?",0,0);
            break;

            case "obyvaciPokoj":
            prostor = new Prostor("obyvaciPokoj","v obývacím pokoji. Přijde ti že se paní trochu pohnula, nebo opravdu ti to jen přijde?", 
                "v obývacím pokoji. Vstoupíš do rozlehlé místnosti, které vévodí obrovský krb ve kterém hoří oheň.\n"
                + "Dříve byl pokoj dozajista chlouba celého domu, na zdech stále vidíš vybledlé obrysy\n"
                + "obrazů, loveckých kořistí a historických zbraňí. Ze stropu se houpe řetěz, který pravděpodobně\n"
                + "kdysy nesl výstavní lustr. Dnes je však místnost do čista prázdná, kromě invalidního vozíku\n"
                + "uprostřed místnosti. Když ho obejdeš, všimneš si že v něm sedí stará paní, nevypadá však že by\n"
                + "si tě všimla, i když stojíš přímo před ní, nadále zírá do ohně.\n"
                + "\n"
                + "Budeš pokračovat dál? Nebo se vrátíš odkuď jsi přišel?",0,0);
            break;

            case "koupelna":
            prostor = new Prostor("koupelna","v koupelně. Na zemi leží bezvládné tělo kluka.", 
                "v koupelně. V okamžiku kdy otevřeš dveře ucítíš neuvěřitelný zápach hnijích těl, následuje pohled na vanu\n"
                + "plnou odřezaných lidských končetin. Ani si ji však nestihneš pořádně prohlídnout, jelikož\n"
                + "v tu chvíli se za tebou zabouchnou dveře, hned se otočíš a vidíš malého kluka v otrhaném\n"
                + "oblečení jak proti tobě zvedá pušku kterou sotva unese\n"
                + "\n"
                + "Pravděpodobně by jsi se měl nějak bránit. Nebo zkušíš kluka přesvěčit aby zbraň sklonil?",0,0);
            break;

            case "schodiste":
            prostor = new Prostor("schodiste","na schodisti. Jakmile na něj znovu vstoupíš, zase strašně zavrže", 
                "na schodišti. Každý další schod na který šlápneš vrzá snad ještě víc než ten předchozí, bojíš se aby tě nikdo neslyšel,\n"
                + "Došel jsi však až nahoru a neslyšíší že by se někdo blížil. Podél schodiště je pověšeno nekolik\n"
                + "kukaček, ani jedny však již nefungujía jsou pokryty silnou vrstvou prachu.\n"
                + "\n"
                + "Nahoře se před tebou vynořila trojice dveří",0,0);
            break;

            case "loznice":
            prostor = new Prostor("loznice","v loznici. Na zemi se válí bezvládné tělo postaršího muže.", 
                "v ložnici. Vejdeš do velké místnosti, ze stropu visí žárovka jen na drátu, rozstříštěný lustr je zametený\n"
                + "do ruhu místnosti. Tapety které dříve byly na všech zdích jsou napůl strhané, nebo se válí\n"
                + "rovnou utržené na zemi. Na levo je velká šatní skříň, které chybí nejen dveře, ale i jedna stěna\n"
                + "Vevnitř vidíš dámské oblečené prožrané moly. Místnosti vévodí velká postel, která se do místnosti\n"
                + "nehodí, je totiž poctivě ustlaná čistým povlečením. Vedle stojí noční stolek a na něm položné\n"
                +"zrcátko a zarámovaná fotka pokrytá prachem. Stolek by dříve zamčený, nyní je však už vylomený\n"
                +"Asi už se do něj někdo snažil bez klíče dostat. Na pravo tě taktéž zaujme velký nástěný svícen.\n"
                + "\n"
                + "Najednou na tebe vyskočil z poza potele postarší muž a rozběhl se proti tobě, budeš se brátnit,\n"
                + "nebo se ho budeš snažit přesvědčit aby tě nechal?",0,0);
            break;

            case "zachod":
            prostor = new Prostor("zachod","na záchodě. Jestli s tím nevydržíš ven, tak co se dá dělat no.", 
                "na záchodě. Když otevřeš dveře, vynoří se před tebou velmi, ale velmi špinavý záchod, voda v míse je spíše\n"
                + "černá a velice zapáchá.\n"
                + "\n"
                + "Můžeš si záchod prohlédnout, ale s použitím bych radši poškal ven.",0,0);
            break;

            case "detskyPokoj":
            prostor = new Prostor("detskyPokoj","v dětském pokoji. Na zemi se válí mrtvola mladého kluka.", 
                "v dětském pokoji. Odemkl jsi dveře a vešel dovnitř, očividně dětský pokoj, dětské postele a po zemi válející se hračky\n"
                + "Vidíš dvě klučicí postele a jednu menší s růžovými nebesy, na té jsou položeny kytky.\n"
                + "Po zemi se válejí hračky, vetšinou rozbité a v rohu místnosti je velký plyšový medvěd.\n"
                + "\n"
                + "Ze zádu uslyšíš pohyb, než se stihneš otočit, mlady kluk ti ze zadu přiloží nůž ke krku\n"
                + "Pravděpodobně by ses měl bránit, nebo ho zkusíš přemluvit aby tě nechal jít?",0,0);
            break;

            case "sklep":
            prostor = new Prostor("sklep","ve sklepě. Pořád nic nevidíš.", 
                "ve sklepě. Když se ti masivní dveře podařilo odemknout, vidíš před soubou dva, tři strmé schody a dál jen\n"
                + "temnotu. Vypínač nikde nevidíš, tak se přidržujíc zdi vydáš dolů po tmě. Ze zhora citíš slabý\n"
                + "průvan, všimneš si že nad tebou je poklop, ten je však zamčený.\n"
                + "\n"
                + "Našlapuješ po jednom schodu, dokud nenarazíš do dveří a nahmatáš kliku.",0,0);
            break;

            case "zahrada":
            prostor = new Prostor("zahrada","", 
                "na zahradě. Konečně se ti podařilo dostat se z toho prokletého domu. Zahrada je totálně zarostlá, sotva vidíš\n"
                + "plot s ostnatým drátem který se nachází okolo pozemku. Z jedné strany je vysoká brána s masivním\n"
                + "zámkem, to není dobré. Pak si však všimneš zrezivělých pantů, bouchneš do brány a celá spadne.\n"
                + "\n"
                + "Jediné co ti zbývá v tuto chvli ze zavolat pomoc",0,0);
            break;

            case "kamaradovaChata":
            prostor = new Prostor("kamaradovaChata","", 
                "u kamarádovy chaty. Před tebou se konečně vynořil cíl tvé cesty.",0,0);
            break;

            case "sklepniChodba":
            prostor = new Prostor("sklepniChodba","na chodbě ve sklepě. Sténání již utichlo.", 
                "na chodbě ve sklepě. Ze stropu visí na drátu žárovka, ale je tak špinavá že ani moc nesvítí. Sklep je kamenný a nic\n"
                + "na chodbě není.\n"
                + "\n"
                + "Před sebou vidíš jen dvoje dveře, z pod obou vychází tlumené světlo a z jedněch slyšíš také\n"
                + "tlumené sténání a nářek.",0,0);
            break;

            case "sklad":
            prostor = new Prostor("sklad","ve skladu. Pokud jsi šel zkontrolovat pokud se muž nezvedl, tak pořád leží.", 
                "ve skladu. Místnost je proměněna na jakési mauzoleum, uprostřed leží skleněná rakev která je obložená velkým\n"
                + "množstvím jak velmi seschlých květin, tak i naprosto čerstvých. Všude hoří velké svíčky.\n"
                +"V rakvi je mrtvola muže a ačkoliv vidíš podle silné vrstvy vosku že už je tam velmi dlouho,\n"
                +"je pořád ve velmi dobrém stavu.\n"
                + "\n"
                + "Nic jiného v místnosti není, spíš se vydej jinudy.",0,0);
            break;

            case "vezeni":
            prostor = new Prostor("vezeni","ve vězení. Všichni se znovu vrhnou k mřížím a prosí o záchranu.", 
                "ve vězení. První co zaregistruješ je ostrý pach moči a krve, další pohled tě zděsí, vevnitř je 6 cel, jedna\n"
                + "prázdná a v 5 jsou zavření lidé. Vypadají ve velmi špatném stavu, v okamžiku kdy tě uvidí\n"
                + "se všichni vrhnou namříže a začnou se dožadovat pomoci. Navzájem se překřikují, co však pochopíš\n"
                + "je že klíč od cel je vedle v pracovně. \n"
                + "\n"
                + "Teď by ses asi měl vydat pro klíč, nebo věříš že budeš mít víc šancí sám?",0,0);
            break;

            case "cela":
            prostor = new Prostor("cela","v cele. Bohužel, další cely jsou zamčený a klíč jsi zlomil. Ostatní musí počkat.", 
                "v cele. Cela je špinavá, v rohu stojí špinavý kýbl, a na zemi se válí trochu sena.",0,0);
            break;

            case "pracovna":
            prostor = new Prostor("pracovna","v pracovně. Vrátil jsi se pro suvenýr?", 
                "v pracovně. Ačkoliv nápis na dveřích jasně udával že by místnost měla být pracovna, vybavení místnosti\n"
                + "poměrně rychle prozradilo že jde spíš o mučírnu. Ne však o takovou jako si představíš\n"
                + "při čtení Fifty shades of grey, ale spíš takovou kterou vídáváš v horrorech.\n"
                + "Všchny zlověstně vypadající nástroje v místnosti jsou pokryté krví.\n"
                + "\n"
                + "Zaujme tě hlavně malá skřínka na stole a bedna v rohu místnosti",0,0);
            break;

            default: 
            break;
        }
        return prostor;
    }

    /** Metoda která popisuje jednotlivé osoby
     * 
     * @param - jméno osoby
     * @return - vrátí nově popsanou osobu
     */
    private Osoba popisOsobu (String jmeno) {
        Osoba osoba = null;
        switch (jmeno) {
            case "malyKluk":
            osoba = new Osoba("malyKluk", 
                "TY: Skloň prosím tu zbraň, nechci ti ublížit.\n"
                + "KLUK: 'mlčí a kouká na tebe ale zbraň neskloní'\n"
                + "Ty: Opatrně s tím, polož tu zbraň prosím, mě se bát nemusíš\n"
                + "\n"
                + "Kluk moc přesvěčený nyvypadá a stiskne spoušť","", "kluk.jpg", false);
            break;

            case "mladyKluk":
            osoba = new Osoba("mladyKluk", 
                "TY: Dej ten nůž prosímtě pryč, není potřeba dělat žádné ukvapené rozhodnutí, nehci ti ublížit\n"
                + "KLUK: Ale já tobě jo, nemáš tu co dělat.\n"
                + "TY: Neboj, ani tu být nechci, chci se odsuď dostat pryč.\n"
                + "KLUK: Na to už je pozdě.\n"
                + "\n"
                + "S těmity slovy tě podřízne","", "ramsay.jpg", false);
            break;

            case "velmiStaraPani":
            osoba = new Osoba("velmiStaraPani", 
                "'Paní nereaguje na nic co jí řikáš, povídat si sám se sebou můžeš i jinde'","Paní stále nereaguje", "oldLady.jpg", true);
            break;

            case "starsiMuz":
            osoba = new Osoba("starsiMuz",
                "'Když na muže promluvíš, ani nespomalí, skočí na tebe a povalí tě a zakousne se ti do krku","", "oldGuy.jpg",false );
            break;

            case "nathan":
            osoba = new Osoba("nathan",
                "Mladý kluk, velmi špinavý a zarostlý, ale na oblečené vidíš že kdysi muselo být velice drahé.\n"
                + "Na rukou stále má zlaté hodinky.\n"
                + "\n"
                + "NATHAN: Prosím, dostaň mě odsuď, už mě tu drží dva měsíce, nechci tu umřít! Moje rodina je\n"
                + "        velmi bohatá, když mě odsuď dostaneš bude z tebe milionář! Prosím!","Kluk žadoní\n"
                +"a slibuje ti všechno možné", "nathan.png", true);
            break;

            case "alex":
            osoba = new Osoba("alex", 
                "Chlap jak horam 30 let, tetování po celém těle, první co tě napadlo je jak ho sem vůbec dostali.\n"
                + "Osloví tě silným ruským přízvukem:\n"
                + "\n"
                + "ALEX: Otevři mi, pomůžu ti dostat se na svobodu, je ti snad jasný že někdo jako ti dost pomůže","\n"
                + "Jsem jediný kdo ti od tuď může pomoc, otevři mi.", "alex.jpg", true);
            break;

            case "chloe":
            osoba = new Osoba("chloe", 
                "Mladá dívky, kolem 20 let, první co tě na ní zaujme jsou jasně modré vlasy a potetovaná ruka.\n"
                + "Kdyby nebyla v tak zbídačeném stavu, byla by velmi pěkná.\n"
                + "\n"
                + "CHLOE: Prosím, dostaň mě ven, jsem tu už několik měsíců, nechci chcípnout ve sklepě.....","\n"
                + "Dostaň mě odsuď prosimtě, budu ti moc vděčná!", "chloe.jpg", true);
            break;

            case "david":
            osoba = new Osoba("david", 
                "Muž ve středních letech, velice pohublý a zarostlý, vypadá že je tu zavřený nejdéle.\n"
                + "\n"
                + "DAVID: Prosím, odemkni mě, jsem tu zavřený už strašně dlouho, chtěl bych vidět svojí rodinu,\n"
                + "za tu dobu co tu jsem už museli strašně vyrůst, když mě pustíš budu ti strašně vděčný stejně\n"
                + "jako manželka a děti, vrať jim prosím tátu.","Otevřeš mi? Chci k rodině, prosím.", "david.jpg", true);
            break;

            case "bara":
            osoba = new Osoba("bara", 
                "Když se blížíš k její cele, pokusí se zvednout, ale spadne zpátky na zem. Vidíš že je těhotná,\n"
                + "odhaduješ tak v 8. měsíci. Podívá se na tebe a jen se rozbrečí.","Bára vypadá že není při vědomí.", "bara.jpg", true);
            break;

            case "policistka":
            osoba = new Osoba("policistka", 
                "Mladší žena v policijní uniformě","", "policistka.png", true);
            break;

            default: 
            break;
        }
        return osoba;
    }

    /**
     * Metoda která popisuje jednotlivé věci
     * 
     * @param - název věci
     * @return - vrátí nově popsanou věc
     *
    private Vec popisVec (String nazev) {
    Vec vec = null;
    switch (nazev) {
    
    case "klicSklep":
    vec = new Vec("klicSklep",
    "Klíč byl přilepený pod šuplíkem, mazaný. Jinak je to klasický klíč od dveří.", true, false);
    break;

    case "univezalniKlic":
    vec = new Vec("univerzalniKlic",
    "Tento klíč vypadá velice složitě, pravděpodobně bude velice důležitý, když ho měla paní u sebe"
    +"Při podrobnějším prozkoumání sis bohužel všiml že je olámaný, s tímhle asi nic neodemkneš", true, false);
    break;

    case "klicStolek":
    vec = new Vec("klicStolek",
    "Klíč je celý černý od sazí, když saze setřeš, klíč tě zklame, je velice jednoduchý,\n"
    + "pravděpodobně jen od nějaká skřéně", true, false);
    break;

    case "policistkaKlic":
    vec = new Vec("policistkaKlic",
    "Klic je celý od krve, otřeš si ho o kalhoty, klíč vypadá že by mohl pasovat do masivního zámku\n"
    + "na bráně.", true, false);
    break;

    case "klicVen":
    vec = new Vec("klicVen",
    "Když vyndáš klíč ze skříňky, všimneš si velkého nápisu 'Poklop', vypadá to že se odsuď přece\n"
    + "jen dostaneš živý.", true, false);
    break;

    case "klicDetskyPokoj":
    vec = new Vec("klicDetskyPokoj",
    "Klíč je mokrý a nechutně zapáchá, ostatně stejně jako celá tvoje ruka. Doufej že ten klíč bude\n"
    + "hodně důležitý, když si pro něj podstoupil toto.", true, false);
    break;

    case "klicCela":
    vec = new Vec("klicCela",
    "Klasický klíč od vězení, velký, masivní, rezavý, přesně jak si ho představíš z filmů.", true, false);
    break;

    case "boxer":
    vec = new Vec("boxer",
    "klasický, masivní boxer, nic zvláštního, ale při cestě hrůzostrašným domem určitě neohrneš\n"
    +"nosem nad žádnou zbraní.", true, false);
    break;

    case "puska":
    vec = new Vec("puska",
    "Klasická Garandka, pravděpodobně ještě po dědovi, ale když sloužila celou válku, snad po slouží i těď.\n"
    + "Bohužel bez nábojů, měl by sis nějaké sehnat.", true, false);
    break;

    case "nuz":
    vec = new Vec("nuz",
    "Dlouhý, úzký nůž. Podle ohnilých kousků masa na čepeli odhandeš že pravděpodobně sloužil\n"
    + "ke kuchání masa, tobě teď možná poslouží k tomu samému účelu.\n"
    + "", true, false);
    break;

    case "revolver":
    vec = new Vec("revolver",
    "Revolver jak z Westernovky, jenže s ráží typu 'hodí se když se zloděj schová do lednice, ve vedlejší\n"
    + "místnosti'. Jediné co je potřeba jsou náboje. Původně jsi si myslel že je na kapslíky,\n"
    + "kdo by taky čekal pravý revolver mezi hračkami že.", true, false);
    break;

    case "naboj":
    vec = new Vec("naboj",
    "Při odtrhnutí medvědovy hlavy jsi našel jeden náboj do revolveru, ještě že rodiče měli aspoň\n"
    + "tolik rozumu aby náboj k synově revolveru zašili do plyšáka....", true, false);
    break;

    case "zrcatko":
    vec = new Vec("zrcatko",
    "Velmi staré zrcátko, i když očištíš silnou vrstvu prachu, stejně se v něm moc nevidíš.", true, true);
    break;

    case "pizza":
    vec = new Vec("pizza",
    "Hmm, Ananasová, tu máš nejraději.", true, true);
    break;

    case "puskovyNaboj":
    vec = new Vec("puskovyNaboj",
    "Náboj do pušky", true, false);
    break;

    case "rakev":
    vec = new Vec("rakev",
    "Vevnitř je mrtvola mladého muže ve svátečním obleku, podle silné vrstvy rozteklého vosku\n"
    + "pokrývající velkou část rakve soudíš že tu již bude asi dlouho. I přesto tělo vypadá\n"
    + "že je stále ve velmi dobrém stavu. Nij jiného vevnitř není.", false, true);
    break;

    case "spolecenskeBoty":
    vec = new Vec("spolecenskeBoty",
    "Kdysy určitě překrásně vypadající společenské boty, dnes už by jsi v nich nešel ani vynést koš.\n"
    + "Když je chceš vrátit, něco z nich vypadne...", false, false);
    break;

    case "tenistky":
    vec = new Vec("tenistky",
    "V tenistkách se vůbec nevyznáš, kdybys však měl odhadnout, vidíš to tak na prototyp tenisek Adidas.", false, false);
    break;

    case "drez":
    vec = new Vec("drez",
    "Tak takhle nechutný dřez jste neměli snad ani na koleji, smrdí už z dálky, ale zase tam třeba\n"
    + "může být něco co by se mohlo hodit...", false, true);
    break;

    case "kos":
    vec = new Vec("kos",
    "Říkat tomu koš je možná docela odvážné, jelikož spíš odhaduješ že se pod tou pyramidou odpadků\n"
    + "opravdu nachází. Možná je majitel nemá v čem vynést?", false, true);
    break;

    case "odpadky":
    vec = new Vec("odpadky",
    "nechutně zapáchající hromada odpadků. hlavně zbytky hnijícího jídla, spoustu krabic od pizzy,\n"
    + "vážně do téhle díry někdo doručuje pizzu?", false, false);
    break;

    case "linka":
    vec = new Vec("linka",
    "kuchyňská linka která je snad celá pokrytá špinavým nádobím, podle počtu by jsi na něm zvládl\n"
    + "obsloužit celý hotel.", false, true);
    break;

    case "lednice":
    vec = new Vec("lednice",
    "Lednice je otevřená a pár věcí zní už vypadlo na zem kde se o něj perou krysy. Máš hlad, ale\n"
    + "v lednici se nic poživatelného nenachází.", false, true);
    break;

    case "krb":
    vec = new Vec("krb",
    "Obrovský krb, cítíš se jak na zámku, vevnitř se válí pár polínek a kupa popelu. Co to je ? Že\n"
    + "by se v popelu něco lesklo?", false, true);
    break;

    case "invalidniVozik":
    vec = new Vec("invalidniVozik",
    "Invalidní vozík, vyrobený částečně ze dřeva, čalounění je rozthané a účelu pravděpodobě už moc\n"
    + "neslouží. Jediné co při prohledání vozíku najdeš je paní která v něm sedí, co jsi taky čekal že", false, true);
    break;

    case "staraPani":
    vec = new Vec("staraPani",
    "Paní musí být velmi stará, máš z ní velmi špatný pocit, ale nebezpečí ti pravděpodobně nehrozí,"
    + "paní nevypadá že by si tě vůbec všimla. Nevím jak tě napadlo ošahávat starou paní, ale\n"
    + "vyplatilo se to, nahmatal jsi totiž velmi zajímavý klíč.", false, false);
    break;

    case "nadobaSHrackami":
    vec = new Vec("nadobaSHrackami",
    "Velká bedna plná hraček, dnes už se takové hračky nevyrábí, ty však spoustu znáš ze svého mládí.\n"
    + "Včetně téhleté kapslíkovky, vypadá skoro jako prává...počkat...", false, true);
    break;

    case "plysovyMedved":
    vec = new Vec("plysovyMedved",
    "Medvěd je už dost vypelichaný, oči už dávno nemá, ale jinak je celý. Když ho vezmeš do ruky,\n"
    + "nahmatáš v hlavě něco tvrdého, utrhneš mu hlavu, tak tohle by se mohlo hodit.", false, true);
    break;

    case "malaPokladnicka":
    vec = new Vec("malaPokladnicka",
    "Malá pokladnička, osoba které patřila patrně uměla šetřit stejně jako ty, vevnitř je maxímálně prach", false, true);
    break;

    case "nocniStolek":
    vec = new Vec("nocniStolek",
    "Když otevřeš šuplík, uvidíš klasické věci co si tak lidé skladují vedle postele, mnohem víc tě\n"
    + "zaujme to co nahmatáš ze spoda...", false, true);
    break;

    case "fotka":
    vec = new Vec("fotka",
    "Očividně rodinna fotka, na fotce je vdiět starý pár, mladší pár, dva kluky a novorozeně které\n"
    + "drží žena v rukou.", false, true);
    break;

    case "svicenNaZdi":
    vec = new Vec("svicenNaZdi",
    "První co tě napadlo při pohledu na svícen je, že tahle vypadá ukázkový tajný vstup, zkusíš s ním\n"
    + "pohnout a ono se opravdu něco děje, ozve se zapraskání a konec. \n"
    + "Bohužel, dřívě by se ti asi otevřela skrýtá cesta, dnes je to však zbytečné.", true, true);
    break;

    case "splachovaciNadoba":
    vec = new Vec("x",
    "Ve splachovací nádobě si vždy tvoje teta schovávala víno, zde ovšem nic není. Holt ne všichni\n"
    + "jsou alkoholici. Stejně, co by jsi dělal teď s vínem.....", false, true);
    break;

    case "voda":
    vec = new Vec("voda",
    "Když jsi spláchl, voda se vevnitř začala točit, ale neodtekla, záchod je určitě ucpaný. Zatímco\n"
    + "se však voda v míse točila, něco vevnitř zacinkalo....", false, true);
    break;

    case "skrinka":
    vec = new Vec("skrinka",
    "Když malou skřínku otevřeš, vevnitř vidíš dva klíče", false, true);
    break;

    case "bedna":
    vec = new Vec("bedna",
    "To co vidíš vevnitř nechceš ani vědět k čemu slouží, natož abys to bral sebou....", false, true);
    break;

    default: 
    break;
    }
    return vec;
    }
     */

    /**
     * Metoda která rozmístí  osoby a věci do místností
     * 
     * @param prostor - název prostoru
     * @param osoba - jméno osoby
     */
    private void rozmistiOsobyVeci (Prostor prostor[], Osoba osoba[]) {
        // rozmístí osoby do prostorů
        prostor[7].vlozOsobu(osoba[0]);
        prostor[6].vlozOsobu(osoba[2]);
        prostor[11].vlozOsobu(osoba[1]);
        prostor[10].vlozOsobu(osoba[3]);
        prostor[15].vlozOsobu(osoba[4]);
        prostor[15].vlozOsobu(osoba[5]);
        prostor[15].vlozOsobu(osoba[6]);
        prostor[15].vlozOsobu(osoba[7]);
        prostor[15].vlozOsobu(osoba[8]);
        //prostor[18].vlozOsobu(osoba[9]);

        //založí věci, ve kterých budou jiné věci, kromě klíčů
        Vec spolecenskeBoty = new Vec ("spolecenskeBoty", "spolecenskeBoty", "spolecenskeBoty.jpg", false, true);
        Vec drez = new Vec ("drez", "drez", "drez.jpg", false, true);
        Vec kos = new Vec ("kos", "kos", "kos.jpg", false, true);
        Vec nadobaSHrackami = new Vec ("nadobaSHrackami", "nadobaSHrackami", "nadoba.jpg", false, true);
        Vec plysovyMedved = new Vec ("plysovyMedved", "plysovyMedved", "plysak.jpg", false, true);

        //založí věci v prostorech
        prostor[10].pridejVec(new Vec ("zrcatko", "zrcatko", "zrcatko.jpg", true, true));
        prostor[3].pridejVec(new Vec ("tenisky", "zkouskaPopis", "tenisky.jpg", false, true));
        prostor[5].pridejVec(new Vec ("pizza", "pizza", "pizza.png", true, true));
        prostor[17].pridejVec(new Vec ("rakev", "rakev", "rakev.jpg", false, true));
        prostor[5].pridejVec(new Vec ("linka", "linka", "linka.jpg", false, true));
        prostor[5].pridejVec(new Vec ("lednice", "lednice", "ldnice.jpg", false, true));
        prostor[11].pridejVec(new Vec ("malaPokladnicka", "malaPokladnicka", "pokladnicka.jpg", false, true));
        prostor[10].pridejVec(new Vec ("fotka", "fotka", "fotka.jpg", false, true));
        prostor[9].pridejVec(new Vec ("splachovaciNadoba", "splachovaciNadoba", "splachovaciNadoba.jpg", false, true));
        prostor[16].pridejVec(new Vec ("bedna", "bedna", "bedna.jpg", false, true));
        prostor[3].pridejVec(spolecenskeBoty);
        prostor[5].pridejVec(drez);
        prostor[5].pridejVec(kos);
        prostor[11].pridejVec(nadobaSHrackami);
        prostor[11].pridejVec(plysovyMedved);

        //založí věci které budou v jiných věcech
        spolecenskeBoty.vlozVec(new Vec ("boxer", "boxer", "boxer.jpg", true, false));
        drez.vlozVec(new Vec ("nuz", "nuz", "nuz.jpg", true, false));
        kos.vlozVec(new Vec ("odpadky", "odpadky", "odpadky.jpg", false, false));
        nadobaSHrackami.vlozVec(new Vec ("revolver", "revolver", "revolver.jpg", true, false));
        plysovyMedved.vlozVec(new Vec ("naboj", "naboj", "naboj.jpg", true, false));

        //přidá věci osobám
        osoba[0].vlozVec(new Vec ("puska", "puska", "puska.jpg", true, false));

        //prostor[10].pridejVec(poleVeci[12]);
        //prostor[12].pridejVec(poleVeci[14]);
        //prostor[3].pridejVec(poleVeci[15]);
        //prostor[3].pridejVec(poleVeci[16]);
        //prostor[5].pridejVec(poleVeci[17]);
        //prostor[5].pridejVec(poleVeci[18]);
        //prostor[5].pridejVec(poleVeci[20]);
        //prostor[5].pridejVec(poleVeci[21]);
        //prostor[6].pridejVec(poleVeci[22]);
        //prostor[6].pridejVec(poleVeci[23]);
        //prostor[11].pridejVec(poleVeci[25]);
        //prostor[11].pridejVec(poleVeci[26]);
        //prostor[11].pridejVec(poleVeci[27]);
        //prostor[10].pridejVec(poleVeci[28]);
        //prostor[10].pridejVec(poleVeci[29]);
        //prostor[10].pridejVec(poleVeci[30]);
        //prostor[9].pridejVec(poleVeci[31]);
        //prostor[9].pridejVec(poleVeci[32]);
        //prostor[16].pridejVec(poleVeci[33]);

        //vec[15].vlozVec(poleVeci[7]);
        //vec[17].vlozVec(poleVeci[9]);
        //vec[18].vlozVec(poleVeci[19]);
        //vec[22].vlozVec(poleVeci[4]);
        //vec[13].vlozVec(poleVeci[24]);
        //vec[24].vlozVec(poleVeci[3]);
        //vec[25].vlozVec(poleVeci[10]);
        //vec[26].vlozVec(poleVeci[11]);
        //vec[27].vlozVec(poleVeci[1]);
        //vec[31].vlozVec(poleVeci[6]);
        //vec[32].vlozVec(poleVeci[5]);
        //vec[32].vlozVec(poleVeci[34]);

    }

    /**
     *  Metoda vrací inventář
     * 
     *  @return - inventář používaný ve hře
     */
    public Inventar getInventar() {
        return this.inventar;
    }

    /**
     *  Metoda vrací jméno toho kdo tě doprovází
     * 
     *  @return    spolecnost    tvoje spolecnost
     */
    public Spolecnost getSpolecnost() {
        return this.spolecnost;
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }
    
    /**
     *  Metoda vrací odkaz na vitezny prostor,.
     *
     *@return vitezny prostor
     */

    public Prostor getViteznyProstor() {
        return viteznyProstor;
    }

    public boolean jeVyhra() {
        return vyhra;
    }

    public void setVyhra(boolean stav) {
        this.vyhra = stav;
    }

    public boolean jeProhra() {
        return prohra;
    }

    public void setProhra(boolean stav) {
        this.prohra = stav;
    }
    
    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }

}
