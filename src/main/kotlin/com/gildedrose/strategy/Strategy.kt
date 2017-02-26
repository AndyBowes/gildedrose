package com.gildedrose.strategy

import com.gildedrose.Item

private val stategies = hashMapOf<String, UpdateStrategy>(
        "Aged Brie" to BrieStategy(),
        "Backstage passes to a TAFKAL80ETC concert" to BackstageStrategy(),
        "Sulfuras, Hand of Ragnaros" to StandardStrategy(sellInDelta = 0, minValue = 80, maxValue = 80)
        )
private val DEFAULT_STRATEGY = StandardStrategy()
fun getStrategy(itemName: String): UpdateStrategy = stategies.getOrDefault(itemName, DEFAULT_STRATEGY)

interface UpdateStrategy {
    infix fun update(item: Item): Item
}

open class StandardStrategy(val sellInDelta: Int = -1, val minValue: Int = 0, val maxValue: Int = 50) : UpdateStrategy {

    override fun update(item: Item): Item {
        val sellIn = item.sellIn + sellInDelta
        val newQuality = Math.max(minValue, Math.min(maxValue, item.quality + qualityDelta(sellIn, item.quality )))
        return Item(item.name, sellIn, newQuality)
    }

    open fun qualityDelta(sellIn: Int, currentQuality: Int) = if (sellIn >= 0) -1 else -2
}

class BrieStategy : StandardStrategy() {
    override fun qualityDelta(sellIn: Int, currentQuality: Int) = if (sellIn >= 0) 1 else 2
}

class BackstageStrategy : StandardStrategy(){
    override fun qualityDelta(sellIn: Int, currentQuality: Int): Int {
        return when {
            sellIn in 6..10 -> 2
            sellIn in 1..5 -> 3
            sellIn < 0 -> -1 * currentQuality
            else -> 1
        }
    }
}