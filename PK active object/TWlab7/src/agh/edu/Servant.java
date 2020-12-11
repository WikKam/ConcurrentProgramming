package agh.edu;

import java.util.LinkedList;

public class Servant {
    private final LinkedList<Integer> array = new LinkedList<>();
    private int size;

    public Servant(int size){
        this.size = size;
    }

    public boolean canConsume(int amount){
        return this.array.size() >= amount;
    }

    public boolean canProduce(int amount){
        return this.array.size() + amount < size;
    }

    public int produce(int amount){
        int ret = 0;
        for (int i = 0; i < amount; i++){
            this.array.push(1);
            ret++;
        }
        return ret;
    }

    public int size(){
        return size;
    }

    public int consume(int amount){
        int ret = 0;
        for (int i = 0; i < amount; i++){
            this.array.pop();
            ret++;
        }
        return ret;
    }
}
