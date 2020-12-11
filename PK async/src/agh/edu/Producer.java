package agh.edu;

import java.util.HashMap;
import java.util.Random;

public class Producer implements Runnable {

    private Buffer buffer;
    private int id;
    private final int maxAmount;
    private Random random = new Random();
    private HashMap<Integer, Integer> map;
    public Producer(Buffer buffer, int id, int maxAmount, HashMap<Integer, Integer> map){
        this.buffer = buffer;
        this.id=id;
        this.maxAmount = maxAmount;
        this.map = map;
    }

    public void produce(){
        try{
            System.out.println(id + " zaczął produkcje");
            int current = this.buffer.startProduction();
            //this.buffer.produce(this.id, toProduce);
            this.map.put(current, 1);
            this.buffer.endProduction(current);
            System.out.println(id + " skonczył produkcje");
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
