package agh.edu;

public class Proxy {
    private final Scheduler scheduler;
    private final Servant servant;

    public Proxy(Scheduler scheduler, Servant servant){
        this.servant = servant;
        this.scheduler = scheduler;
    }

    public Future produce(int amount, int id){
        Future future = new Future();
        Request request = new ProduceRequest(future, servant, amount, id);
        scheduler.addRequest(request);
        return future;
    }
    public Future consume(int amount, int id){
        Future future = new Future();
        Request request = new ConsumeRequest(future, servant, amount, id);
        scheduler.addRequest(request);
        return future;
    }
}
