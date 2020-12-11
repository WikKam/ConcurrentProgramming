package pl.agh;

import java.util.Random;

public class RunableImpl implements Runnable {
    public int Id;
    public IntegerConatiner integerConatiner;
    private Semaphore semaphore;
    public RunableImpl(int Id, IntegerConatiner integerConatiner, Semaphore semaphore){
        this.Id = Id;
        this.integerConatiner = integerConatiner;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            Random generator = new Random();
            Thread.sleep(generator.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(this.Id % 2 == 0){
            try {
                semaphore.down();
                integerConatiner.addOne();
                semaphore.up();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            integerConatiner.addOne();
            semaphore.up();
        }
        else{
            try {
                semaphore.down();
                integerConatiner.removeOne();
                semaphore.up();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(integerConatiner.integer);
    }
}
