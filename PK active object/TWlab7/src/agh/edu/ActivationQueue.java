package agh.edu;

import java.util.LinkedList;
import java.util.Queue;

public class ActivationQueue {
    private final LinkedList<Request> produceRequests = new LinkedList<>();
    private final LinkedList<Request> consumerRequests = new LinkedList<>();
    private final LinkedList<Request> allRequests = new LinkedList<>();

    public void addRequest(Request request){
        //System.out.println("ADD");
        //allRequests.add(request);
        if(request instanceof ProduceRequest){
            produceRequests.add(request);
        }
        else {
            consumerRequests.add(request);
        }
        /*
        System.out.println("#######PRODUCEQUEUE#########");
        System.out.println(produceRequests);
        System.out.println("#######CONSUMEQUEUE#########");
        System.out.println(consumerRequests);
        System.out.println("#######ALLEQUEUE#########");
        System.out.println(allRequests);
*/
    }


    public boolean isAllQueueEmpty(){
        return allRequests.isEmpty();
    }

    public boolean areConsumeAndProduceQueuesEmpty(){
        return isConsumeQueueEmpty() && isProduceQueueEmpty();
    }
    public boolean isProduceQueueEmpty(){
        return produceRequests.isEmpty();
    }

    public boolean isConsumeQueueEmpty(){
        return consumerRequests.isEmpty();
    }

    public Request getRequestFromAll(){
        return !allRequests.isEmpty() ? allRequests.getFirst() : null;
    }
    public Request getRequestFromProduce(){
        return !produceRequests.isEmpty() ? produceRequests.getFirst() : null;
    }
    public Request getRequestFromConsume(){
        return !consumerRequests.isEmpty() ? consumerRequests.getFirst() : null;
    }
    public boolean removeRequest(Request req){
        //System.out.println("REMOVE");
        //allRequests.remove(req);
        return consumerRequests.remove(req) || produceRequests.remove(req);
/*
        System.out.println("#######PRODUCEQUEUE#########");
        System.out.println(produceRequests);
        System.out.println("#######CONSUMEQUEUE#########");
        System.out.println(consumerRequests);
        System.out.println("#######ALLEQUEUE#########");
        System.out.println(allRequests);
*/
    }
}
