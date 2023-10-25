package edu.project2.gameObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class Cell implements Comparable {

    private final Integer row;
    private final Integer column;

    private boolean leftWall;
    private boolean bottomWall;

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
