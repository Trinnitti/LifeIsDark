package com.github.Trinnitti.LifeIsDark;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.Trinnitti.LifeIsDark.logika.Hra;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("temnyLes", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi kamennyDum");
        assertEquals(false, hra1.konecHry());
        assertEquals("kamennyDum", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi temnyLes");
        assertEquals(false, hra1.konecHry());
        assertEquals("kamennyDum", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }
    
    /**
     * Testuje, zda příkaz "inventar" doopravdy vypíše věci které má hráč v baťohu.
     */
    @Test
    public void testPrikazInventar()
    {
        hra1.zpracujPrikaz("prozkoumej skrinka");
        assertEquals("\n\nV inventáři máš následující věci: Tvůj inventář je prázdný", hra1.zpracujPrikaz("inventar"));
        hra1.zpracujPrikaz("seber klicCela");
        assertEquals("klicCela.", hra1.zpracujPrikaz("inventar"));
        assertEquals(true, hra1.getHerniPlan().getInventar().jeTuVec("klicCela"));
        assertEquals(false, hra1.getHerniPlan().getInventar().jeTuVec("boxer"));
    }
    
    /**
     * Testuje, zda příkaz "seber" sebere požadovanou věc - sebere ji z prostoru a vloží do inventáře.
     */
    @Test
    public void testPrikazSeber()
    {
        assertEquals("To tu není.",
        hra1.zpracujPrikaz("seber drez"));
        hra1.zpracujPrikaz("prozkoumej drez");
        assertEquals("Sebral jsi nuz.", hra1.zpracujPrikaz("seber nuz"));
        assertNull(hra1.getHerniPlan().getAktualniProstor().jeTuVec("nuz"));
        assertEquals(true, hra1.getHerniPlan().getInventar().jeTuVec("nuz"));
    }
}
