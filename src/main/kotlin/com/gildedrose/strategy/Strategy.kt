package com.gildedrose.strategy

import com.gildedrose.Item

class UpdateStrategy(val sellInDelta: Int = -1,
                     val minQuality: Int = 0,
                     val maxQuality: Int = 50,
                     val qualityDelta: (Int, Int) -> Int = standardDelta) {

    infix fun update(item: Item): Item {
        val sellIn = item.sellIn + sellInDelta
        val newQuality = (item.quality + qualityDelta(sellIn, item.quality)).withinBounds(minQuality, maxQuality)
        return Item(item.name, sellIn, newQuality)
    }
}

val standardDelta: (Int, Int) -> Int = { sellIn, quality -> if (sellIn >= 0) -1 else -2 }

val brieDelta: (Int, Int) -> Int = { sellIn, quality -> if (sellIn >= 0) 1 else 2 }

val backstageDelta: (Int, Int) -> Int = { sellIn, quality ->
    when {
        sellIn < 0 -> -1 * quality
        sellIn in 1..5 -> 3
        sellIn in 6..10 -> 2
        else -> 1
    }
}

val conjuredDelta: (Int, Int) -> Int = { sellIn, quality -> if (sellIn >= 0) -2 else -4 }

private val stategies = hashMapOf<String, UpdateStrategy>(
        "Aged Brie" to UpdateStrategy(qualityDelta = brieDelta),
        "Backstage passes to a TAFKAL80ETC concert" to UpdateStrategy(qualityDelta = backstageDelta),
        "Sulfuras, Hand of Ragnaros" to UpdateStrategy(sellInDelta = 0, minQuality = 80, maxQuality = 80),
        "Conjured Mana Cake" to UpdateStrategy(qualityDelta = conjuredDelta)
)

private val DEFAULT_STRATEGY = UpdateStrategy()

fun getStrategy(itemName: String): UpdateStrategy = stategies.getOrDefault(itemName, DEFAULT_STRATEGY)

fun Int.withinBounds(min: Int, max: Int) = Math.max(min, Math.min(max, this))