package com.example.bestmile.data.model

import org.junit.Test

internal class LocationTest {

    @Test
    fun `test burgers location is near burgers location`() {
        val testLocation = Location.burgersLocation

        assert(testLocation.isNearBurgers())
    }

    @Test
    fun `test train station location is near burgers location`() {
        val trainStation = Location(46.5166272,6.6271827)

        assert(trainStation.isNearBurgers())
    }

    @Test
    fun `test espace inventions location is not near burgers location`() {
        val trainStation = Location(46.5457174,6.5438135)

        assert(!trainStation.isNearBurgers())
    }

    @Test
    fun `test parc valency location is not near burgers location`() {
        val trainStation = Location(46.5283239,6.543887)

        assert(!trainStation.isNearBurgers())
    }
}