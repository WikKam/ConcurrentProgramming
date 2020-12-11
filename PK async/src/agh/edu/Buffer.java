package agh.edu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private Resource resources;
    private ReentrantLock lock = new ReentrantLock();
    private ArrayList<Integer> empty = new ArrayList<>();
    private ArrayList<Integer> busy = new ArrayList<>();

    private int size;
    Condition producerCond = lock.newCondition();
    private Condition freeConsumer = lock.newCondition();

    public Buffer(int size){
        this.size = size;
        for (int i = 0; i < size; i++){
            empty.add(i);
        }
    }

    public int startProduction(){
        lock.lock();
        Integer current = null;
        try {
            while(empty.isEmpty()) {
                producerCond.await();
            }
            current = empty.get(0);
            empty.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return current;
    }

    public void endProduction(Integer current){
        lock.lock();
        busy.add(current);
        freeConsumer.signal();
        System.out.println("Ilość wątków: " + (10 - this.empty.size() - this.busy.size()));
        lock.unlock();
    }



    public int startConsumption(){
        lock.lock();
        Integer current = null;
        try{
            while(busy.isEmpty()){
                freeConsumer.await();
            }
            current = busy.get(0);
            busy.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        return current;
    }



    public void endConsumption(Integer current){
        lock.lock();
        empty.add(current);
        producerCond.signal();
        System.out.println("Ilość wątków: " + (this.size - this.empty.size() - this.busy.size()));
        lock.unlock();

    }
}
