package agh.edu;

public class Fork {
    private int value = 1;

    public synchronized int getStatus(){
        return this.value;
    }

    public synchronized void putBack(){
        this.value++;
        notify();
    }

    public synchronized void take() throws InterruptedException {
        while(value == 0){
            wait();
        }
        this.value--;
    }
}
