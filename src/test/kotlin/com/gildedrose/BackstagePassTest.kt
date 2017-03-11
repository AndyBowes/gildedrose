package com.gildedrose

import org.junit.Assert.assertEquals
import org.junit.Test

class BackstagePassTest {

    @Test fun `Backstage Pass more than 10 days before SellBy Date - Add 1 per day`() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 20, 5))
        val app = GildedRose(items)
        for (i in 1..8) {
            app.updateQuality()
            assertEquals(20 - i, app.items[0].sellIn)
            assertEquals(5 + i, app.items[0].quality)
        }
    }

    @Test fun `Backstage Pass after SellBy Date - Price falls to zero`() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 0, 5))
        val app = GildedRose(items)
        for (i in 1..30) {
            app.updateQuality()
            assertEquals(-i, app.items[0].sellIn)
            assertEquals(0, app.items[0].quality)
        }
    }

    @Test fun `Backstage Pass within 6 to 10 days of SellBy Date - Add 2 per day`() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 10, 45))
        val app = GildedRose(items)
        for (i in 1..5) {
            app.updateQuality()
            assertEquals(10 - i, app.items[0].sellIn)
            assertEquals(Math.min(50, 45 + 2 * i), app.items[0].quality)
        }
    }

    @Test fun `Backstage Pass within 1 to 5 days of SellBy Date - Add 3 per day`() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 5, 45))
        val app = GildedRose(items)
        for (i in 1..5) {
            app.updateQuality()
            assertEquals(5 - i, app.items[0].sellIn)
            assertEquals(Math.min(50, 45 + 3 * i), app.items[0].quality)
        }
    }
}