package pl.agh;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        IntegerConatiner container = new IntegerConatiner(0);
        Semaphore semaphore = new Semaphore();
	    for(int i = 1; i <= 40; i++){
	        Thread t = new Thread(new RunableImpl(i, container, semaphore));
            threads.add(t);
	        t.start();
        }
	    threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(container.integer);
    }
}
