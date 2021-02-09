package transport;

public class Car extends Transport {
    private static int counter = 0;

    public Car() {
        super(20, 2.5, "car " + counter++);
    }
}
