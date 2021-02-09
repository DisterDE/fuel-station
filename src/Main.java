import transport.Bus;
import transport.Car;
import transport.Transport;
import transport.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        FuelStation station = new FuelStation();
        List<Transport> transportList = new ArrayList<>(List.of(
                new Bus(), new Bus(), new Bus(),
                new Car(), new Car(), new Car(), new Car(),
                new Truck(), new Truck(), new Truck(), new Truck()
        ));

        ExecutorService executorService = Executors.newCachedThreadPool();
        transportList.forEach(executorService::execute);

        while (!transportList.isEmpty()) {
            for (int i = 0; i < transportList.size(); i++) {
                Transport transport = transportList.get(i);
                if (transport.isEmpty()) {
                    station.addToQueue(transport);
                    transportList.remove(i);
                    i--;
                }
            }
        }

        executorService.shutdown();
    }
}
