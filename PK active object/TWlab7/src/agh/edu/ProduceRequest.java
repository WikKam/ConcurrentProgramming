package agh.edu;

public class ProduceRequest extends Request {

    public ProduceRequest(Future future, Servant servant, int amount, int id){
        super(future, servant, amount, id);
    }

    @Override
    public String toString(){
        return id + "produce" + amount;
    }

    @Override
    public void call() {
        int result = this.servant.produce(this.amount);
        this.updateFuture(result);
    }

    @Override
    public boolean guard() {
        return this.servant.canProduce(this.amount);
    }
}
