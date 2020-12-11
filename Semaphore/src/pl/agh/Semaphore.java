package pl.agh;

public class Semaphore {
    private int value = 1;

    public synchronized void up(){
        this.value++;
        notify();
    }

    public synchronized void down() throws InterruptedException {
        while(value == 0){
            wait();
        }
        this.value--;
    }
}
