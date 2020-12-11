package agh.edu;

import java.util.HashMap;
import java.util.Random;

public class Consumer implements Runnable {
    private Buffer buffer;
    private int id;
    private final int maxAmount;
    private Random random = new Random();
    private HashMap<Integer, Integer> map;
    public Consumer(Buffer buffer, int id, int maxAmount, HashMap<Integer, Integer> map){
        this.buffer = buffer;
        this.id=id;
        this.maxAmount = maxAmount;
        this.map = map;
    }

    public void consume(){
        try{
            System.out.println(id + " zaczął konsumpcje");
            int current = this.buffer.startConsumption();
            this.map.put(current, 0);
            this.buffer.endConsumption(current);
            System.out.println(id + " skończył konsumpcje");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while(true){
            //this.startConsume
            this.consume();
            //this.buffer.consume;
            //this.endConsume
        }
    }
}
