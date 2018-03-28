/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.Trinnitti.LifeIsDark.logika.Spolecnost;
import com.github.Trinnitti.LifeIsDark.logika.Osoba;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída {@code SpolecnostTest} slouží ke komplexnímu otestování
 * třídy {@link SpolecnostTest}.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-28
 */
public class SpolecnostTest
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

    /**
     * Testuje, zda se k tobě nemůže přidat víc jak jedna osoba
     */
    @Test
    public void testPridejVicJakKapacita()
    {
        Spolecnost S2 = new Spolecnost();
        Osoba O1 = new Osoba("O1","O1", "O1", true);
        Osoba O2 = new Osoba("O2","O2", "O2", true);
        Osoba O3 = S2.vlozSpolecnost(O1);
        assertEquals(O3, O3);
        Osoba O4 = S2.vlozSpolecnost(O2);
        assertEquals(O4, O4);
    }

    /**
     * Testuje vložení osoby do společnosti.
     */
    @Test
    public void testVlozOsobu()
    {
        Spolecnost S1 = new Spolecnost();
        Osoba O1 = new Osoba("O1", "O1", "O1", true);
        assertEquals(O1, S1.vlozSpolecnost(O1));
        assertEquals(true, S1.osobaSTebou("O1"));
    }

}