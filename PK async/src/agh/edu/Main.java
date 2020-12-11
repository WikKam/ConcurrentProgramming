package agh.edu;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<>();
    	int size = 5;
        Buffer buffer = new Buffer( 2 * size);
	    for(int i = 0; i<5; i++){
	        Producer p = new Producer(buffer, i, size - 1, map);
	        Consumer c = new Consumer(buffer,i, size - 1, map);
	        Producer p2 = new Producer(buffer, i+1, size - 1, map);
	        Thread t1 = new Thread(p);
	        Thread t2 = new Thread(c);
	        Thread t3 = new Thread(p2);

	        t1.start();
	        t2.start();
	        t3.start();
        }
    }
}
