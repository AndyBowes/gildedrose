package com.gildedrose

import org.junit.Assert.assertEquals
import org.junit.Test

class SulfurasTest {
    @Test fun `Sulfuras Never Ages - Price always equals 80`() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        val app = GildedRose(items)
        for (i in 1..30) {
            app.updateQuality()
            assertEquals(0, app.items[0].sellIn)
            assertEquals(80, app.items[0].quality)
        }
    }
}