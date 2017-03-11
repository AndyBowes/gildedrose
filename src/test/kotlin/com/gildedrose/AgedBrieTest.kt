package com.gildedrose

import org.junit.Assert.assertEquals
import org.junit.Test

class AgedBrieTest {
    @Test fun `Aged Brie Before SellBy Date - Add 1 for each day`() {
        val items = arrayOf<Item>(Item("Aged Brie", 20, 35))
        val app = GildedRose(items)
        for (i in 1..19) {
            app.updateQuality()
            assertEquals(20 - i, app.items[0].sellIn)
            assertEquals(Math.min(50, 35 + i), app.items[0].quality)
        }
    }

    @Test fun `Aged Brie After SellBy Date - Add 2 for each day`() {
        val items = arrayOf<Item>(Item("Aged Brie", 0, 40))
        val app = GildedRose(items)
        for (i in 1..10) {
            app.updateQuality()
            assertEquals(-i, app.items[0].sellIn)
            assertEquals(Math.min(50, 40 + 2 * i), app.items[0].quality)
        }
    }
}