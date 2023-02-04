package org.acme.astar.rule

import org.acme.astar.AStarKotlin
import org.junit.Test
import kotlin.test.assertEquals

class DiagonalScorerKotlinKotlinTest {

    //absolute precision is not necessary here.
    //heuristics and differences are calculated with this calculation.
    //14 instead of 15.
    private val delta = 1e-14

    @Test
    fun computeCostDefaultFalse() {
        //Arrange
        val diagonalScorerKotlin = DiagonalScorerKotlin(true)
        val from = AStarKotlin.Node(0, 0, 0)
        val to = AStarKotlin.Node(7, 7, 0)

        //Act
        val actualComputedCost = diagonalScorerKotlin.computeCost(from, to)

        //Assert
        assertEquals(9.899494936611665, actualComputedCost, delta)
    }

    @Test
    fun computeCostDefaultNextWeightTrue() {
        //Arrange
        val diagonalScorerKotlin = DiagonalScorerKotlin(true)
        val from = AStarKotlin.Node(0, 0, 0)
        val to = AStarKotlin.Node(7, 7, 100)


        //Act
        val actualComputedCost = diagonalScorerKotlin.computeCost(from, to)

        //Assert
        assertEquals(
            9.899494936611665,
            actualComputedCost, delta
        )
    }


    @Test
    fun computeCostDefaultNextWeightFalse() {
        //Arrange
        val diagonalScorerKotlin = DiagonalScorerKotlin(false)
        val from = AStarKotlin.Node(0, 0, 0)
        val to = AStarKotlin.Node(7, 7, 100)

        //Act
        val actualComputedCost = diagonalScorerKotlin.computeCost(from, to)

        //Assert
        assertEquals(
            109.89949493661166,
            actualComputedCost, delta
        )
    }

}