package com.gildedrose

import org.junit.Assert.assertEquals
import org.junit.Test

class GildedRoseTest{

    @Test fun `Item Name Is Unchanged`() {
        val items = arrayOf<Item>(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }

    @Test fun `Standard Item before SellBy Date - Decrease by 1 each day`() {
        val items = arrayOf<Item>(Item("foo", 20, 25))
        val app = GildedRose(items)
        for (i in 0..19) {
            app.updateQuality()
            assertEquals(19 - i, app.items[0].sellIn)
            assertEquals(24 - i, app.items[0].quality)
        }
    }

    @Test fun `Standard Item after SellBy Date - Decrease by 2 each day`() {
        val items = arrayOf<Item>(Item("foo", 0, 25))
        val app = GildedRose(items)
        for (i in 1..10) {
            app.updateQuality()
            assertEquals(-i, app.items[0].sellIn)
            assertEquals(25 - 2 * i, app.items[0].quality)
        }
    }
}


