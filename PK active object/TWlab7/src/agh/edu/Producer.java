package agh.edu;

import java.util.Random;

public class Producer implements Runnable {

    private Servant buffer;
    private int id;
    private final int maxAmount;
    private Random random = new Random();
    Proxy proxy;
    private long lastPrint = 0;
    private final long starvationTime;
    public Producer(Servant buffer, int id, Proxy proxy, long starvationTime){
        this.buffer = buffer;
        this.id=id;
        this.maxAmount = buffer.size() / 2;
        this.proxy = proxy;
        this.starvationTime = starvationTime;
    }

    public void produce(){
        try{
            int toProduce = random.nextInt(maxAmount - 1) + 1;
            //System.out.println(id + " wants to produce " + toProduce);
            Future future = proxy.produce(toProduce, id);
            long startTime = System.currentTimeMillis();
            while (!future.isReady()){
                long currentTime = System.currentTimeMillis();
                if(lastPrint != currentTime - startTime){
                    //System.out.println("producer " + id + "waiting for " + (currentTime - startTime) + " milis");
                    lastPrint = currentTime - startTime;
                    if(lastPrint > starvationTime){
                        System.out.println("PRODUCER " + id + " STARVED");
                        System.exit(1);
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println( id + " produced " + future.getResult() + " in " + (endTime - startTime));
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
