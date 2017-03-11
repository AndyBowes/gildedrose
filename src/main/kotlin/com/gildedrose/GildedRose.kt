package com.gildedrose

import com.gildedrose.strategy.getStrategy

class GildedRose(var items: Array<Item>) {
    fun updateQuality() {
        items = items.map { item -> getStrategy(item.name) update item }.toTypedArray()
    }
}

