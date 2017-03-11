package com.gildedrose.strategy

import org.junit.Assert.*
import org.hamcrest.CoreMatchers.*
import org.junit.Test

class WithinBoundsTest {

    @Test fun `Check that value inside bounds remains unchanged`(){
        assertThat( 1.withinBounds(0,50), equalTo(1))
    }

    @Test fun `Check that value below lower bound is set to lower bound`(){
        assertThat( (-1).withinBounds(0,50), equalTo(0))
    }

    @Test fun `Check that value above upper bound is set to upper bound`(){
        assertThat( 51.withinBounds(0,50), equalTo(50))
    }
}

