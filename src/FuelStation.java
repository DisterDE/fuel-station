import transport.Transport;
import utils.ThreadUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class FuelStation {
    private final Queue<Transport> queue;
    private final Semaphore semaphore;
    private final AtomicInteger carsFilled;

    public FuelStation() {
        queue = new LinkedList<>();
        semaphore = new Semaphore(3, true);
        carsFilled = new AtomicInteger(10);
        fillTransports();
    }

    public void addToQueue(Transport transport) {
        System.out.println(transport.getName() + " встал в очередь.");
        queue.add(transport);
    }

    private void fillTransports() {
        new Thread(() -> {
            ExecutorService executorService = Executors.newCachedThreadPool();
            while (carsFilled.get() > 0) {
                if (!queue.isEmpty()) {
                    Transport t = queue.poll();
                    executorService.execute(() -> {
                        try {
                            semaphore.acquire();
                            System.out.println(t.getName() + " заправляется.");
                            ThreadUtils.wait(5);
                            t.fillTank();
                            System.out.println(t.getName() + " заправлен.");
                            carsFilled.decrementAndGet();
                            semaphore.release();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            executorService.shutdown();
        }).start();
    }
}
