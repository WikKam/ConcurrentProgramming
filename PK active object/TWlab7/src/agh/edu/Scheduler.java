package agh.edu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Scheduler extends Thread {
    private final ActivationQueue queue = new ActivationQueue();
    private final Lock lock = new ReentrantLock();
    private final Condition emptyQueue = lock.newCondition();
    private final Condition emptyConsumerQueue = lock.newCondition();
    private final Condition emptyProducerQueue = lock.newCondition();


    public void addRequest(Request request){
        lock.lock();
        queue.addRequest(request);
        emptyQueue.signal();
        //System.out.println("all queue signalled");
        /*if(request instanceof ProduceRequest){
            //emptyProducerQueue.signal();
            //System.out.println("producer queue signalled");
        }
        else {
            //emptyConsumerQueue.signal();
            //System.out.println("consumer queue signalled");
        }*/
        lock.unlock();
    }

    private boolean tryToCall(Request req){
        if (req != null && req.guard()) {
            lock.lock();
            queue.removeRequest(req);
            req.call();
            lock.unlock();
            return true;
        }
        return false;
    }

    @Override
    public void run(){
        while (true) {
            lock.lock();
            Request produceRequest = queue.getRequestFromProduce();
            Request consumeRequest = queue.getRequestFromConsume();
            lock.unlock();
            boolean wasAnyCallSuccessful = tryToCall(produceRequest) || tryToCall(consumeRequest);
            if (!wasAnyCallSuccessful) {
                try {
                    lock.lock();
                    while (queue.areConsumeAndProduceQueuesEmpty()) {
                        emptyQueue.await();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
