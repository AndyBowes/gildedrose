package com.gildedrose.strategy

import org.junit.Assert.*
import org.hamcrest.CoreMatchers.*
import org.junit.Test

class StrategyTest {

    @Test fun `Standard items decrease by 1 before SellBy`(){
        assertThat( standardDelta.invoke(3, 10), equalTo(-1))
    }

    @Test fun `Standard items decrease by 2 after SellBy`(){
        assertThat( standardDelta.invoke(-1, 10), equalTo(-2))
    }

    @Test fun `Aged Brie increases by 1 before SellBy`(){
        assertThat( brieDelta.invoke(3, 10), equalTo(1))
    }

    @Test fun `Aged Brie increases by 2 after SellBy`(){
        assertThat( brieDelta.invoke(-1, 10), equalTo(2))
    }

    @Test fun `Backstage Tickets increase by when more than 10 days before SellBy`(){
        assertThat( backstageDelta.invoke(12, 10), equalTo(1))
    }

    @Test fun `Backstage Tickets increase by 2 within 10 Days Of SellBy`(){
        assertThat( backstageDelta.invoke(10, 20), equalTo(2))
    }

    @Test fun `Backstage Tickets increase by 3 within 5 Days Of SellBy`(){
        assertThat( backstageDelta.invoke(4, 20), equalTo(3))
    }

    @Test fun `Backstage Tickets fall to zero after SellBy`(){
        assertThat( backstageDelta.invoke(-1, 10), equalTo(-10))
    }

    @Test fun `Conjured items decrease by 2 before SellBy`(){
        assertThat( conjuredDelta.invoke(3, 10), equalTo(-2))
    }

    @Test fun `Conjured items decrease by 4 after SellBy`(){
        assertThat( conjuredDelta.invoke(-1, 10), equalTo(-4))
    }
}




