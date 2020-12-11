package agh.edu;

import java.util.Random;

public class Producer implements Runnable {

    private Buffer buffer;
    private int id;
    private final int maxAmount;
    private Random random = new Random();
    public Producer(Buffer buffer, int id, int maxAmount){
        this.buffer = buffer;
        this.id=id;
        this.maxAmount = maxAmount;
    }

    public void produce(){
        try{
            int toProduce = random.nextInt(maxAmount - 1) + 1;
            this.buffer.produce(this.id, toProduce);
            System.out.println(id + " produced " + toProduce + " buffers");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            this.produce();
        }
    }
}
