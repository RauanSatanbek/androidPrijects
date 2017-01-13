package ru.srba.asynctasktesting;
public class test {
    Thread t = new Thread(new myRun());
}

class myRun implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            try {
                Thread.sleep(1000);
                System.out.println(i + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}