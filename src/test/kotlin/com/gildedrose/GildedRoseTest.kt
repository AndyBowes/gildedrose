package com.gildedrose

import org.junit.Assert.assertEquals
import org.junit.Test

class GildedRoseTest {

    @Test fun nameIsUnchanged() {
        val items = arrayOf<Item>(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }

    @Test fun standardItemBeforeSellByDate() {
        val items = arrayOf<Item>(Item("foo", 20, 25))
        val app = GildedRose(items)
        for (i in 0..19) {
            app.updateQuality()
            assertEquals(19 - i, app.items[0].sellIn)
            assertEquals(24 - i, app.items[0].quality)
        }
    }

    @Test fun standardItemAfterSellByDate() {
        val items = arrayOf<Item>(Item("foo", 0, 25))
        val app = GildedRose(items)
        for (i in 1..10) {
            app.updateQuality()
            assertEquals(-i, app.items[0].sellIn)
            assertEquals(25 - 2 * i, app.items[0].quality)
        }
    }

    @Test fun agedBrieBeforeSellByDate() {
        val items = arrayOf<Item>(Item("Aged Brie", 20, 35))
        val app = GildedRose(items)
        for (i in 1..19) {
            app.updateQuality()
            assertEquals(20 - i, app.items[0].sellIn)
            assertEquals(Math.min(50, 35 + i), app.items[0].quality)
        }
    }

    @Test fun agedBrieAfterSellByDate() {
        val items = arrayOf<Item>(Item("Aged Brie", 0, 40))
        val app = GildedRose(items)
        for (i in 1..10) {
            app.updateQuality()
            assertEquals(-i, app.items[0].sellIn)
            assertEquals(Math.min(50, 40 + 2*i), app.items[0].quality)
        }
    }

    @Test fun sulfurasNeverAges() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        val app = GildedRose(items)
        for (i in 1..30) {
            app.updateQuality()
            assertEquals(0, app.items[0].sellIn)
            assertEquals(80, app.items[0].quality)
        }
    }

    @Test fun backstagePassBeforeSellByDate() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 20, 5))
        val app = GildedRose(items)
        for (i in 1..8) {
            app.updateQuality()
            assertEquals(20 - i, app.items[0].sellIn)
            assertEquals(5 + i, app.items[0].quality)
        }
    }

    @Test fun backstagePassAfterSellByDate() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 0, 5))
        val app = GildedRose(items)
        for (i in 1..30) {
            app.updateQuality()
            assertEquals(-i, app.items[0].sellIn)
            assertEquals(0, app.items[0].quality)
        }
    }

    @Test fun backstagePassSellWithin10Days() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 10, 45))
        val app = GildedRose(items)
        for (i in 1..5 ) {
            app.updateQuality()
            assertEquals(10-i, app.items[0].sellIn)
            assertEquals(Math.min(50,45 + 2*i), app.items[0].quality)
        }
    }

    @Test fun backstagePassSellWithin5Days() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 5, 45))
        val app = GildedRose(items)
        for (i in 1..5 ) {
            app.updateQuality()
            assertEquals(5-i, app.items[0].sellIn)
            assertEquals(Math.min(50,45 + 3*i), app.items[0].quality)
        }
    }
}


