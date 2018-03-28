/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.Trinnitti.LifeIsDark.logika.Osoba;
import com.github.Trinnitti.LifeIsDark.logika.Vec;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída {@code OsobaTest} slouží ke komplexnímu otestování
 * třídy {@link OsobaTest}.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-28
 */
public class OsobaTest
{

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }


    //\TT== TESTS PROPER ===========================================================

    /**
     * Testuje, zda postava vrací jinou odpověď v případě že na ní už bylo mluveno.
     */
    @Test
    public void testGetMluveno()
    {
        Osoba osoba1 = new Osoba("O1", "P1", "P2", true);
        assertEquals("P1", osoba1.getPromluva());
        assertEquals("P2", osoba1.getPromluva());
        assertEquals("P2", osoba1.getPromluva());
    }
    
    /**
     * Testuje, zda funguje vkládání věcí do jiných věcí.
     */
    @Test
    public void testVlozVecOsobe()
    {
        Vec vec1 = new Vec ("V1","V1", false, true);
        Osoba osoba2 = new Osoba ("O2", "O2", "O2", true);
        osoba2.vlozVec(vec1);
        assertEquals(vec1, osoba2.getVec());
    }

}
