package edu.project2.gameObjects;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DepthCellConcurrent extends DepthCell {

    private final ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();

    public DepthCellConcurrent(Cell cell) {
        super(cell);
    }

    @Override
    public boolean isVisited() {
        reentrantLock.readLock().lock();
        try {
            return visited;
        } finally {
            reentrantLock.readLock().unlock();
        }
    }

    @Override
    public void setVisited(boolean visited) {
        reentrantLock.writeLock().lock();
        try {
            this.visited = visited;
        } finally {
            reentrantLock.writeLock().unlock();
        }
    }
}
