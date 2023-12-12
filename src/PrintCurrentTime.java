public class PrintCurrentTime {
    public static void main(String[] args) {
        while (true) {
            System.out.println(java.time.LocalTime.now());
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
