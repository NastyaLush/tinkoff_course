package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager{
    @Override
    public Connection getConnection() {
        if(Math.random()>0.4) return  new FaultyConnection();
        return new StableConnection();
    }
}
