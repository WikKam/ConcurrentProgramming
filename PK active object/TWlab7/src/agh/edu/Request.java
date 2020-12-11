package agh.edu;

public abstract class Request {
    protected final Future future;
    protected final Servant servant;
    protected final int amount;
    protected final int id;

    protected Request(Future future, Servant servant, int amount, int id) {
        this.future = future;
        this.servant = servant;
        this.amount = amount;
        this.id = id;
    }

    protected void updateFuture(int result){
        future.setResult(result);
        future.setReady(true);
    }

    public abstract void call();
    public abstract boolean guard();
    public abstract String toString();

}
