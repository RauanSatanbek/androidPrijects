import java.util.Random;

/**
 * Created by Рауан on 15.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Test("Rauan"));
        Thread t2 = new Thread(new Test("China"));
        t1.start();
        t2.start();
    }
}
class Test implements Runnable{
    String name;
    int time;
    Random r = new Random();
    Test(String name){
        this.name = name;
        time = r.nextInt(1000);
    }
    @Override
    public void run() {
        try {
            Thread.sleep(time);
        }catch(Exception e){}
        System.out.println(name + " " + time);
    }
}
