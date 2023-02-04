package org.acme.astar;

import org.acme.astar.rule.Scorer;
import org.acme.astar.util.MazeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class RouteFinder<T extends AStar.Node> {

    private final Scorer<T> scorer;

    private final Map<T, RouteNode<T>> allFields = new HashMap<>();
    private final Queue<RouteNode<T>> openSet = new PriorityQueue<>();

    private final MazeUtil<T> mazeUtil;
    private T endField;

    public RouteFinder(int[][] maze, Scorer<T> scorer) {
        this.mazeUtil = new MazeUtil<>(maze);
        this.scorer = scorer;
    }

    /**
     * A*Search algorithm which searches for the path from the beginning to the end field. It stops when find the route
     * or when there is nor route at all (went through all nodes).
     *
     * @param startField field from which the path should begin.
     * @param endField field to which the path should end
     * @return all fields from the beginning to the end of the path / route.
     */
    public List<T> findPathTo(T startField, T endField) {
        this.endField = endField;
        RouteNode<T> startFieldRoute =
                new RouteNode<>(startField, null, 0d, scorer.computeCost(startField, endField));
        allFields.put(startField, startFieldRoute);
        openSet.add(startFieldRoute);

        while (!openSet.isEmpty()) {
            log.debug("Open Set contains: " + openSet.stream().map(RouteNode::getCurrent).collect(Collectors.toSet()));
            RouteNode<T> currentFieldRoute = openSet.poll();
            log.debug("*** Looking at node: " + currentFieldRoute);

            //Exit routine when the end / destination field is found.
            if (Objects.requireNonNull(currentFieldRoute).getCurrent().equals(endField)) {
                return getFieldPath(allFields, currentFieldRoute);
            }

            final T currentField = currentFieldRoute.getCurrent();
            final double currentRouteScore = currentFieldRoute.getRouteScore();
            Set<T> neighbourFields = mazeUtil.getNeighbourFields(currentFieldRoute.getCurrent());
            neighbourFields.forEach(neighbourField -> {
                double distanceStartToNeighbourField =
                        currentRouteScore + scorer.computeCost(currentField, neighbourField);
                if (!allFields.containsKey(neighbourField)) {
                    addNewFieldRoute(currentField, neighbourField, distanceStartToNeighbourField);
                } else {
                    RouteNode<T> neighbourFieldRoute = allFields.get(neighbourField);
                    if (neighbourFieldRoute.getRouteScore() > distanceStartToNeighbourField) {
                        updateFieldRoute(neighbourFieldRoute, currentField, neighbourField, distanceStartToNeighbourField);
                    }
                }
            });
        }

        throw new IllegalStateException("No route found");
    }

    /**
     * Updating the values of the neighbourFieldRoute because shorter path to this field was found.
     *
     * @param currentField the current field from which the neighbour field comes.
     * @param neighbourField one of the neighbour of the current field.
     * @param distanceStartToNeighbourField the distance from the first / beginning field to this one.
     */
    private void updateFieldRoute(RouteNode<T> neighbourFieldRoute, T currentField, T neighbourField,
                                  double distanceStartToNeighbourField) {
        log.debug("Shorter path found for node with position ({}, {}). Was {} will be {}",
                neighbourField.getX(),
                neighbourField.getY(),
                neighbourFieldRoute.getRouteScore(),
                distanceStartToNeighbourField);
        neighbourFieldRoute.setPrevious(currentField);
        neighbourFieldRoute.setRouteScore(distanceStartToNeighbourField);
        neighbourFieldRoute.setEstimatedScore(
                distanceStartToNeighbourField + scorer.computeCost(neighbourField, this.endField));
        neighbourFieldRoute.getCurrent().setG(currentField.getG() + neighbourFieldRoute.getCurrent().getW());
        openSet.add(neighbourFieldRoute);
        log.debug("Found a better route to node: " + neighbourFieldRoute);
    }

    /**
     * Adds new route to the route list because this node was never visited before.
     *
     * @param currentField the current field from which the neighbour field comes.
     * @param neighbourField one of the neighbour of the current field.
     * @param distanceStartToNeighbourField the distance from the first / beginning field to this one.
     */
    private void addNewFieldRoute(T currentField, T neighbourField, double distanceStartToNeighbourField) {
        RouteNode<T> newFieldRoute = new RouteNode<>(
                neighbourField,
                currentField,
                distanceStartToNeighbourField,
                distanceStartToNeighbourField + this.scorer.computeCost(neighbourField, this.endField)
        );
        this.openSet.add(newFieldRoute);
        this.allFields.put(neighbourField, newFieldRoute);
    }

    /**
     * Creates and returns list of all fields of the path from the beginning to the end / destination field.
     *
     * @param allFields         all fields
     * @param currentFieldRoute last field which is the destination fields as well.
     * @return all fields of the path from the beginning to the end / destination field.
     */
    private List<T> getFieldPath(
            Map<T, RouteNode<T>> allFields, RouteNode<T> currentFieldRoute) {
        log.debug("Found our destination!");
        List<T> route = new ArrayList<>();
        RouteNode<T> current = currentFieldRoute;
        do {
            T currentField = current.getCurrent();
            route.add(0, currentField);
            //"previous" is for the while-condition necessary and for bounding the previous nodes for describing
            //the route from the start to the destination.
            current = allFields.get(current.getPrevious());
        } while (current != null);
        log.debug("Route: " + route);
        return route;
    }

}
