package agh.edu;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int producerNumber = 30;
        int consumerNumber = 20;
        int servantSize = 200;
        int starvationTime = 3000;
        Servant servant = new Servant(servantSize);
        Scheduler scheduler = new Scheduler();
        scheduler.start();
        List<Producer> producers = IntStream
                .range(0, producerNumber)
                .mapToObj(id -> new Producer(servant, id, new Proxy(scheduler, servant), starvationTime))
                .collect(Collectors.toList());

        List<Consumer> consumers = IntStream
                .range(0, consumerNumber)
                .mapToObj(id -> new Consumer(servant, id, new Proxy(scheduler, servant), starvationTime))
                .collect(Collectors.toList());

        producers.forEach(producer -> {
            Thread t = new Thread(producer);
            t.start();
        });

        consumers.forEach(consumer -> {
            Thread t = new Thread(consumer);
            t.start();
        });

        try {
            scheduler.join();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
