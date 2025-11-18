class MyRunnableTask implements Runnable {
    public void run() {
        // Code to be executed by the new thread
        System.out.println("Thread " + Thread.currentThread().getName() + " is running.");
        // Example: Perform some task
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(100); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class MainRunnableTask {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnableTask(), "Thread-1");
        Thread t2 = new Thread(new MyRunnableTask(), "Thread-2");
        Thread t3 = new Thread(new MyRunnableTask(), "Thread-3");
        Thread t4 = new Thread(new MyRunnableTask(), "Thread-4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}