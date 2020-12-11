package agh.edu;

import java.util.Random;

public class Consumer implements Runnable {
    private Servant buffer;
    private int id;
    private final int maxAmount;
    private Proxy proxy;
    private Random random = new Random();
    private long lastPrint = 0;
    private final long starvationTime;
    public Consumer(Servant buffer, int id, Proxy proxy, long starvationTime){
        this.buffer = buffer;
        this.id=id;
        this.maxAmount = buffer.size() / 2;
        this.proxy = proxy;
        this.starvationTime = starvationTime;
    }

    public void consume(){
        try{
            int toConsume = random.nextInt(maxAmount - 1) + 1;
            //System.out.println(id + " wants to consume " + toConsume);
            Future future = proxy.consume(toConsume, id);
            long startTime = System.currentTimeMillis();
            while (!future.isReady()){
                long currentTime = System.currentTimeMillis();
                if(lastPrint != currentTime - startTime){
                    //System.out.println("consumer " + id + "waiting for " + (currentTime - startTime) + " milis");
                    lastPrint = currentTime - startTime;
                    if(lastPrint > starvationTime){
                        System.out.println("CONSUMER " + id + " STARVED");
                        System.exit(1);
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println(id + "consumed " + future.getResult() + " in " + (endTime - startTime));
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
