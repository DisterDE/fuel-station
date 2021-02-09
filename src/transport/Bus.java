package transport;

public class Bus extends Transport {
    private static int counter = 0;

    public Bus() {
        super(40, 7.5, "bus " + counter++);
    }
}
