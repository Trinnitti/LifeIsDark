/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Trinnitti.LifeIsDark;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.Trinnitti.LifeIsDark.logika.Inventar;
import com.github.Trinnitti.LifeIsDark.logika.Vec;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída {@code InventarTest} slouží ke komplexnímu otestování
 * třídy {@link InventarTest}.
 *
 * @author  Tomáš Prokeš
 * @version 0.00.0000 — 2017-05-28
 */
public class InventarTest
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
     * Testuje, zda do inventáře nejde vložit víc věcí než je kapacita.
     */
    @Test
    public void testVlozVicJakKapacita()
    {
        Inventar I2 = new Inventar();
        Vec V1 = new Vec("V1","V1", true, true);
        Vec V2 = new Vec("V2","V2", true, true);
        Vec V3 = new Vec("V3","V3", true, true);
        Vec V4 = new Vec("V4","V4", true, true);
        Vec V5 = new Vec("V5","V5", true, true);
        Vec V6 = new Vec("V6","V6", true, true);
        Vec V7 = I2.vlozVec(V1);
        assertEquals(V7, V7);
        Vec V8 = I2.vlozVec(V2);
        assertEquals(V8, V8);
        Vec V9 = I2.vlozVec(V3);
        assertEquals(V9, V9);
        Vec V10 = I2.vlozVec(V4);
        assertEquals(V10, V10);
        Vec V11 = I2.vlozVec(V5);
        assertEquals(V11, V11);
        assertNull(I2.vlozVec(V6));
    }

    /**
     * Testuje vložení věci do inventáře.
     */
    @Test
    public void testVlozVec()
    {
        Inventar I1 = new Inventar();
        Vec V1 = new Vec("V1", "V1", true, true);
        assertEquals(V1, I1.vlozVec(V1));
        assertEquals(true, I1.jeTuVec("V1"));
    }

    /**
     * Testuje smazání věci z batohu.
     */
    @Test
    public void testVyhodVec()
    {
        Inventar I1 = new Inventar();
        Vec V1 = new Vec("V1","V1", true, true);
        I1.vlozVec(V1);
        assertEquals(V1, I1.vyhodVec("V1"));
        assertEquals("\n\nV inventáři máš následující věci: Tvůj inventář je prázdný.", I1.getVeci());
    }

}
