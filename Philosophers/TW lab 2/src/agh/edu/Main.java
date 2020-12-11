package agh.edu;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Fork[] forks = new Fork[5];
        ArrayList<Philosopher> philosophers = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            forks[i] = new Fork();
        }
        Waiter waiter = new Waiter(forks);
        for(int i = 0; i < 5; i++){
            Philosopher p = new Philosopher(i, forks[i%5], forks[(i + 1)%5], waiter);
            philosophers.add(p);
        }

        philosophers.forEach(philosopher -> {
            Thread t = new Thread(philosopher);
            t.start();
        });
    }
}
