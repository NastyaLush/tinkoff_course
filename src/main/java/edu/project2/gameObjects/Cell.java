package edu.project2.gameObjects;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
@Data
public class Cell implements Comparable {

    private final Integer row;
    private final Integer column;

    private boolean leftWall;
    private boolean bottomWall;

    public Cell(@NotNull Integer row, @NotNull Integer column, boolean leftWall, boolean bottomWall) {
        if (row < 0 || column < 0) {
            log.info("can't create cell because of illegal arguments row {} column {}", row, column);
            throw new IllegalArgumentException();
        }
        this.row = row;
        this.column = column;
        this.leftWall = leftWall;
        this.bottomWall = bottomWall;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        Cell otherCell = (Cell) o;
        if (this.row.equals(otherCell.row)) {
            return this.column.compareTo(otherCell.column);
        } else {
            return this.row.compareTo(otherCell.row);
        }
    }

}
