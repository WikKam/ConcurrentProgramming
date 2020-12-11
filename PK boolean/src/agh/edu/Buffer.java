package agh.edu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private Resource resources;
    private ReentrantLock lock = new ReentrantLock();
    private final Condition firstConsumer;
    private final Condition firstProducer;

    private final Condition restConsumer;
    private final Condition restProducer;

    private boolean isAnyProducerFirst = false;
    private boolean isAnyConsumerFirst = false;

    private int producersAtFirstCondition = 0;
    private int producersAtSecondCondition = 0;
    private int consumersAtFirstCondition = 0;
    private int consumersAtSecondCondition = 0;


    public Buffer(int size){
        this.resources = new Resource(size);
        this.firstConsumer = lock.newCondition();
        this.firstProducer = lock.newCondition();

        this.restConsumer = lock.newCondition();
        this.restProducer = lock.newCondition();
    }

    public void produce(int id, int amount) throws InterruptedException {
        lock.lock();
        while(isAnyProducerFirst){
            producersAtFirstCondition++;
            restProducer.await();
            producersAtFirstCondition--;
        }
        while(!this.resources.canProduce(amount)){
            producersAtSecondCondition++;
            isAnyProducerFirst = true;
            firstProducer.await();
            isAnyProducerFirst = false;
            producersAtSecondCondition--;
        }
        this.resources.produce(id, amount);
        restProducer.signal();
        firstConsumer.signal();
        lock.unlock();
    }

    public void consume(int amount) throws InterruptedException {
        lock.lock();
        while(isAnyConsumerFirst){
            consumersAtFirstCondition++;
            printAll();
            restConsumer.await();
            consumersAtFirstCondition--;
        }
        while(!this.resources.canConsume(amount)){
            consumersAtSecondCondition++;
            printAll();
            isAnyConsumerFirst = true;
            firstConsumer.await();
            isAnyConsumerFirst = false;
            consumersAtSecondCondition--;
        }
        this.resources.consume(amount);
        restConsumer.signal();
        firstProducer.signal();
        lock.unlock();
    }

    public void printAll(){
        System.out.println(
                "Producenci na pierwszym warunku: " +
                producersAtFirstCondition +
                        " Producenci na drugim warunku " +
                        producersAtSecondCondition
        );

        System.out.println(
                "Konsumenci na pierwszym warunku: " +
                        consumersAtFirstCondition +
                        " Konsumenci na drugim warunku " +
                        consumersAtSecondCondition
        );
    }
}
