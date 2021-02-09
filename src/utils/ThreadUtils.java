package utils;

public class ThreadUtils {
    private ThreadUtils() {
    }

    public static void waitASecond() {
        wait(1);
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}