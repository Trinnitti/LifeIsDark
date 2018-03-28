/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.Trinnitti.LifeIsDark.logika.Vec;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída {@code VecTest} slouží ke komplexnímu otestování
 * třídy {@link VecTest}.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-28
 */
public class VecTest
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
     * Testuje, zda funguje vkládání věcí do jiných věcí.
     */
    @Test
    public void testVlozVecDoVeci()
    {
        Vec V1 = new Vec ("V1", "V1", false, true);
        Vec V2 = new Vec ("V2", "V2", true, false);
        V1.vlozVec(V2);
        assertEquals(true, V1.obsahujeVec("V2"));
        assertEquals(false, V2.obsahujeVec("V3"));
    }

}
