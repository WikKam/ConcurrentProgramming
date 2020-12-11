package agh.edu;

public class Main {

    public static void main(String[] args) {
    	int size = 5;
        Buffer buffer = new Buffer( 2 * size);
	    for(int i = 0; i<4; i++){
	        Producer p = new Producer(buffer, i, size - 1);
	        Consumer c = new Consumer(buffer,i, size - 1);
	        Producer p2 = new Producer(buffer, i+1, size - 1);
	        Thread t1 = new Thread(p);
	        Thread t2 = new Thread(c);
	        Thread t3 = new Thread(p2);

	        t1.start();
	        t2.start();
	        t3.start();
        }
    }
}
