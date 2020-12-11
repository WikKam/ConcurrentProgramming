package agh.edu;

import java.util.Random;

public class Consumer implements Runnable {
    private Buffer buffer;
    private int id;
    private final int maxAmount;
    private Random random = new Random();
    public Consumer(Buffer buffer, int id, int maxAmount){
        this.buffer = buffer;
        this.id=id;
        this.maxAmount = maxAmount;
    }

    public void consume(){
        try{
            int toConsume = random.nextInt(maxAmount - 1) + 1;
            System.out.println(id + " wants to consume " + toConsume);
            Future
            System.out.println(id + " consumed " + toConsume + " buffers");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while(true){
            this.consume();
        }
    }
}
