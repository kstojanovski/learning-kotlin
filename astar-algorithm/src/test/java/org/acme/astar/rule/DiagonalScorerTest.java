package org.acme.astar.rule;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.acme.astar.AStar;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class DiagonalScorerTest {

    //absolute precision is not necessary here.
    //heuristics and differences are calculated with this calculation.
    //14 instead of 15.
    private static final double DELTA = 1e-14;

    @Test
    public void computeCostDefaultFalse() {
        //Arrange
        DiagonalScorer diagonalScorer = new DiagonalScorer(true);
        double expectedComputedCost = 9.8994949366116653416118210694679;
        AStar.Node from = new AStar.Node(0, 0, 0);
        AStar.Node to = new AStar.Node(7, 7, 0);

        //Act
        double actualComputedCost = diagonalScorer.computeCost(from, to);

        //Assert
        assertEquals(expectedComputedCost, actualComputedCost, DELTA);
    }

    @Test
    @Parameters({"true", "false"})
    public void computeCostDefaultNextWeight(boolean ignoreWeight) {
        //Arrange
        DiagonalScorer diagonalScorer = new DiagonalScorer(ignoreWeight);
        double expectedComputedCost = ignoreWeight ? 9.8994949366116653416118210694679 : 109.8994949366116653416118210694679;
        AStar.Node from = new AStar.Node(0, 0, 0);
        AStar.Node to = new AStar.Node(7, 7, 100);

        //Act
        double actualComputedCost = diagonalScorer.computeCost(from, to);

        //Assert
        assertEquals(expectedComputedCost, actualComputedCost, DELTA);
    }
}