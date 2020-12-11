package agh.edu;

public class ConsumeRequest extends Request {

    public ConsumeRequest(Future future, Servant servant, int amount, int id){
        super(future, servant, amount, id);
    }

    @Override
    public String toString(){
        return id + "consume" + amount ;
    }

    @Override
    public void call() {
        int result = this.servant.consume(this.amount);
        this.updateFuture(result);
    }

    @Override
    public boolean guard() {
        return this.servant.canConsume(this.amount);
    }
}
