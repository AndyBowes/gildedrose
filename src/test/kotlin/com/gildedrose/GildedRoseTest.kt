package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class GildedRoseTest {

    @Test fun nameIsUnchanged() {
        val items = arrayOf<Item>(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }

    @Test fun standardItemBeforeSellByDate(){
        val items = arrayOf<Item>(Item("foo", 20, 25))
        val app = GildedRose(items)
        for (i in 0..19){
            app.updateQuality()
            assertEquals(19 - i,app.items[0].sellIn)
            assertEquals(24 - i,app.items[0].quality)
        }
    }

    @Test fun standardItemAtSellByDate(){
        val items = arrayOf<Item>(Item("foo", 0, 25))
        val app = GildedRose(items)
        for (i in 1..10){
            app.updateQuality()
            assertEquals(-i,app.items[0].sellIn)
            assertEquals(25 - 2*i,app.items[0].quality)
        }
    }

    @Test fun agedBrieBeforeSellByDate(){
        val items = arrayOf<Item>(Item("Aged Brie", 20, 35))
        val app = GildedRose(items)
        for (i in 1..19){
            app.updateQuality()
            assertEquals(20 - i,app.items[0].sellIn)
            assertEquals(Math.min(50,35 + i),app.items[0].quality)
        }
    }

    @Test fun agedBrieAtSellByDate(){
        val items = arrayOf<Item>(Item("Aged Brie", 0, 40))
        val app = GildedRose(items)
        for (i in 1..10){
            app.updateQuality()
            assertEquals(-i,app.items[0].sellIn)
            assertEquals(Math.min(50,40 + i),app.items[0].quality)
        }
    }

    @Test fun sulfurasNeverAges(){
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        val app = GildedRose(items)
        for (i in 1..30){
            app.updateQuality()
            assertEquals(0,app.items[0].sellIn)
            assertEquals(80,app.items[0].quality)
        }
    }

}


