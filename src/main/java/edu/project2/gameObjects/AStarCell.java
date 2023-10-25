package edu.project2.gameObjects;

import java.util.Objects;

public class AStarCell {

    private boolean visited;
    private AStarCell parent;

    public AStarCell(Cell cell) {
        this.cell = cell;
    }

    private final Cell cell;
    private Long weight = Long.MAX_VALUE;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public AStarCell getParent() {
        return parent;
    }

    public void setParent(AStarCell parent) {
        this.parent = parent;
    }

    public Cell getCell() {
        return cell;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AStarCell aStarCell = (AStarCell) o;
        return Objects.equals(cell, aStarCell.cell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visited, parent, cell, weight);
    }

    @Override public String toString() {
        return "AStarCell{" +
            "cell=" + cell +
            '}';
    }
}
