package com.gildedrose.strategy

import com.gildedrose.Item

private val stategies = hashMapOf<String, UpdateStrategy>(
        "Aged Brie" to UpdateStrategy(qualityDelta = { sellIn, quality -> if (sellIn >= 0) 1 else 2 }),
        "Backstage passes to a TAFKAL80ETC concert" to UpdateStrategy(qualityDelta = { sellIn, quality ->
            when {
                sellIn in 6..10 -> 2
                sellIn in 1..5 -> 3
                sellIn < 0 -> -1 * quality
                else -> 1
            }
        }),
        "Sulfuras, Hand of Ragnaros" to UpdateStrategy(sellInDelta = 0, minValue = 80, maxValue = 80),
        "Conjured Mana Cake" to UpdateStrategy(qualityDelta = { sellIn, quality -> if (sellIn >= 0) -2 else -4 })
)

private val DEFAULT_STRATEGY = UpdateStrategy(qualityDelta = { sellIn, quality -> if (sellIn >= 0) -1 else -2 })

fun getStrategy(itemName: String): UpdateStrategy = stategies.getOrDefault(itemName, DEFAULT_STRATEGY)

fun Int.withinBounds(min: Int, max: Int) = Math.max(min, Math.min(max, this))

class UpdateStrategy(val sellInDelta: Int = -1, val minValue: Int = 0, val maxValue: Int = 50,
                     val qualityDelta: (Int, Int) -> Int = { sellIn, quality -> if (sellIn >= 0) -1 else -2 }) {

    infix fun update(item: Item): Item {
        val sellIn = item.sellIn + sellInDelta
        val newQuality = (item.quality + qualityDelta(sellIn, item.quality)).withinBounds(minValue, maxValue)
        return Item(item.name, sellIn, newQuality)
    }
}