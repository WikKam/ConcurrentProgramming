package agh.edu;

import java.util.Arrays;

public class Waiter {
    public Fork[] forks;
    public Waiter(Fork[] forks){
        this.forks = forks;
    }
    public synchronized void waitForPermission(Philosopher p) throws InterruptedException {
        while (!p.hasAtLeastOneFork() && !(Arrays.stream(forks).filter(fork -> fork.getStatus() == 0).count() < 4)){
            System.out.println("waiting for permission " + p.id);
            wait();
        }
        System.out.println("permission granted to " + p.id);
        notify();
    }
}
