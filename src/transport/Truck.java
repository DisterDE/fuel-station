package transport;

public class Truck extends Transport {
    private static int counter = 0;

    public Truck() {
        super(60, 15, "truck " + counter++);
    }
}
