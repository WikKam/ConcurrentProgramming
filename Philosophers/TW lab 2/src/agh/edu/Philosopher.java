package agh.edu;

import java.util.Random;

public class Philosopher implements Runnable {
    public int id;
    private Random generator = new Random();
    private final Fork leftFork;
    private final Fork rightFork;
    private int forkNumber = 0;
    private final Waiter waiter;

    public boolean hasAtLeastOneFork(){
        return this.forkNumber > 0;
    }

    public Philosopher(int id, Fork leftFork, Fork rightFork, Waiter waiter){
        this.id = id;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        this.waiter = waiter;
    }

    public void think() {
        try{
            Thread.sleep(generator.nextInt(200));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    };

    private void waitForPermissionAndPickFork(Fork fork) throws InterruptedException {
        waiter.waitForPermission(this);
        fork.take();
        this.forkNumber++;
    }

    private void putForkBack(Fork fork){
        fork.putBack();
        this.forkNumber--;
    }

    public void eat() {
        try {
            waitForPermissionAndPickFork(leftFork);
            System.out.println("left picked");
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            waitForPermissionAndPickFork(rightFork);
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            putForkBack(rightFork);
            System.out.println("right done");
            putForkBack(leftFork);
            System.out.println("left done");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        while(true){
            eat();
            think();
        }
    }
}
//drugue rozw 5 filozfów na next zajęcia + monitory + skrin z zakleszczenia (struktury danych w monitorze)
