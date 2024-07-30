package model;

import game.competition.Competitor;
import utilities.Point;

import java.util.Observable;

public class CompetitorImpl extends Observable implements Competitor, Runnable {
    private String name;
    private int age;
    private double maxSpeed;
    private double acceleration;
    private double currentPosition;
    private double currentSpeed;
    private boolean finished;

    public CompetitorImpl(String name, int age, double maxSpeed, double acceleration) {
        this.name = name;
        this.age = age;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.currentPosition = 0;
        this.currentSpeed = 0;
        this.finished = false;
    }

    @Override
    public void run() {
        while (!finished) {
            try {
                Thread.sleep(100);
                updatePosition();
                setChanged();
                notifyObservers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updatePosition() {
        if (currentPosition < 700) { // Assuming 700 is the end of the arena
            currentSpeed = Math.min(currentSpeed + acceleration, maxSpeed);
            currentPosition += currentSpeed * 0.1; // Update every 100ms
        } else {
            finished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void initRace() {
        this.currentPosition = 0;
        this.currentSpeed = 0;
        this.finished = false;
    }

    @Override
    public void move(double friction) {
        // Implement the move logic considering friction
        if (!finished) {
            currentSpeed = Math.min(currentSpeed + acceleration - friction, maxSpeed);
            currentPosition += currentSpeed * 0.1;
            if (currentPosition >= 700) {
                finished = true;
            }
        }
    }

    @Override
    public Point getLocation() {
        return null;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPosition() {
        return currentPosition;
    }

    // Other getters and setters
}
