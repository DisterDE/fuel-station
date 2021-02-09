package transport;

import utils.ThreadUtils;

public abstract class Transport implements Runnable {
    private final double initCapacity;
    private final double consumptionRate;
    private final String name;

    private double capacity;
    private boolean isEmpty;
    private volatile boolean isFilled;

    public Transport(int capacity, double consumptionRate, String name) {
        this.capacity = capacity;
        this.initCapacity = capacity;
        this.consumptionRate = consumptionRate;
        this.name = name;
    }

    @Override
    public void run() {
        new Thread(() -> {
            while (!isFilled) {
                ThreadUtils.waitASecond();
                capacity -= consumptionRate;
            }
        }).start();

        while (!isFilled) {
            ThreadUtils.wait(3);
            if (capacity < initCapacity * 0.2) {
                isEmpty = true;
            }
        }
    }

    public void fillTank() {
        capacity = initCapacity;
        isFilled = true;
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}