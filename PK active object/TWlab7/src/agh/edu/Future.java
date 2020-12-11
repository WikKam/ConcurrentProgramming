package agh.edu;

public class Future {
    private volatile boolean isReady = false;
    public volatile int result;


    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
