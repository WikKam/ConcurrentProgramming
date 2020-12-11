package agh.edu;

import java.util.LinkedList;

public class Resource {
    private final LinkedList<Integer> array = new LinkedList<>();
    private int size;

    public Resource(int size){
        this.size = size;
    }

    public boolean canConsume(int amount){
        return this.array.size() >= amount;
    }

    public boolean canProduce(int amount){
        return this.array.size() + amount < size;
    }

    public void produce(int val, int amount){
        for (int i = 0; i < amount; i++){
            this.array.push(val);
        }
    }

    public void consume(int amount){
        for (int i = 0; i < amount; i++){
            this.array.pop();
        }
    }
}
