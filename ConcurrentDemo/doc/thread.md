##线程
####1.1 Thread
        ①线程可以驱动任务，因此你需要一种描述任务的方式，这可以由Runnable接口来提供。要想定义任务，只需实现Runnable接口并编写run()函数，使得该任务可以执行你的命令。
        ②将Runnable对象转变为工作任务的传统方式是把它提交给一个Thread构造器。
        ③Thread构造器只需要一个Runnable对象，调用Thread对象的start()方法为该线程执行必须的初始化操作，然后调用Runnable的run()方法，以便在这个线程中启动该任务。尽管start()看起来是产生了一个长期运行的方法的调用，但是从输出的结果可以看到，start()函数迅速返回了。实际上LiftOff.run()还在执行。。
```Java
package com.liu.demo;

/**
 * Created by liu on 17-6-19.
 */
public class LiftOff implements Runnable{
    protected int countDown = 10;//default
    private static int taskCount=0;
    private final int id = taskCount++;

    public LiftOff(){}
    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "(" + (countDown>0?countDown:"LiftDown") + ")";
    }
    @Override
    public void run() {
        while(countDown-->0)
            System.out.print(status()+" ");
            Thread.yield();
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(new LiftOff()).start();
            System.out.println("waiting for liftoff "+i);
        }

    }
}
output:
waiting for liftoff 0
#0(9) #0(8) #0(7) #0(6) #0(5) #0(4) #0(3) #0(2) #0(1) #0(LiftDown)
```
####1.2 Executer
        ①Java SE5的java.util.concurrent包中的执行器（Executor）将为你管理Thread对象，从而简化了并发编程。Executor在客户端和任务执行之间提供了一个间接层；与客户端直接执行任务不同，这个中介对象将执行任务。Executor允许你管理异步任务的执行，而无须显示地管理线程的生命周期。Executor在Java SE5/6中是启动任务的优选方法。
        ②ExecutorService（具有服务生命周期的Executor，例如关闭）知道如何构建恰当的上下文类执行Runnable对象。在下面的例子中，CachedThreadPool将为每个任务都创建一个线程。注意。ExecutorService对象是使用静态的Executor方法创建的。这个方法可以确定Executor类型。
```Java
package com.liu.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liu on 17-6-20.
 * 创建一个CachedThreadPool,这是一个ExecutorService对象，也是一个Executor对象
 * 用CachedThreadPool来管理多个线程
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new LiftOff());
            System.out.println("waiting for liftoff : "+i);
        }
        exec.shutdown();
    }
}
```
        注：这是一个非常常见的情况，单个Executor被用来创建和管理系统中的所有任务。
        调用shutdown()方法可以防止新任务被提交给这个Executor，当前线程（在本例中，即驱动main()的线程）将继续运行在shutdown被调用之前提交的所有任务。这个程序将在Executor中的所有任务完成后尽快退出。
        其他的Executor：
        ①FixedThreadPool，一次性与执行代价高昂的线程分配，因而也就可以限制线程的数量了。着可以节省时间，因为你不用为每个任务都固定地付出创建线程的开销。在事件驱动的系统中，需要线程的事件处理器，通过直接从池中获取线程，可以尽快的得到服务。FixedThreadPool使用Thread对象的数量是有限的，不要滥用可以或得的资源。
        ②singleThreadExecutor就像是线程数量为1的FixedThreadPool。这对于希望在一个线程中连续运行的任何事物（长期存货的任务）来说，都是很有用的，例如监听进入的套接字连接的任务，它对于希望在线程中运行的短任务也同样方便，例如，更新本地或远程日志的小任务，或者是事件分发线程。如果向SingleThreadExecutor提交了多个任务，那么这些任务将排队。
####1.3 从任务中产生返回值
        Runnable是执行工作的独立任务，但是她不返回任何值。如果你希望任务在完成时能够返回一个值，那可以实现Callable接口。在java se5中引入的Callable是一个具有类型参数的泛型接口，他的类型参数表示的是从call()中返回的值，并且必须使用ExecutorService.sbumit()方法来调用他，下面是一个简单的实例：
```java
package com.liu.demo;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by liu on 17-6-20.
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> result = new ArrayList<>();
        for(int i=0;i<10;i++){
            result.add(executorService.submit(new TaskWithResult(i)));
        }
        for(Future<String> stringFuture : result){
            try{
                //get() blocks until completion;
                System.out.println(stringFuture.get());
            }catch (InterruptedException e){
                System.out.println(e);
                return;
            }catch (ExecutionException e){
                System.out.println(e);
            }finally {
                executorService.shutdown();//neccessary
            }
        }
    }
}


class TaskWithResult implements Callable<String>{
    private int taskid;
    public TaskWithResult(int taskid){
        this.taskid = taskid;
    }
    @Override
    public String call() throws Exception {
        return "result of task: " + taskid;
    }
}
```
        注：submit()方法会产生Future对象，它用Callable返回结果的特征类型进行了参数化。可以用isDone()方法来查询Futue是否已经完成。当任务完成时，它具有一个结果，你可以调用get()方法来获取结果。也可以不用isDone()，直接使用get()来获取结果，在这种情况下，get()将阻塞，知道结果准备就绪。
####1.4 休眠
        影响任务行为的一种简单方法及时调用sleep()。这将使任务中止执行给定的时间。
```Java
package com.liu.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by liu on 17-6-20.
 */
public class SleepingTask extends LiftOff {
    @Override
    public void run() {
        try {
            while(countDown-->0){
                System.out.print(status());
                //old style
                //Thread.sleep(100);
                //java se5/6
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            executorService.execute(new SleepingTask());
            System.out.println("waiting for liftoff "+ i);
        }
        executorService.shutdown();
    }
}
```
####1.5 优先级
        线程的优先级将该线程的重要性传递给了调度器。尽管CPU处理线程集的顺序是不确定的，但是调度器将倾向去让优先级最高的线程线程先先执行。但是，这并不意味着优先权较低的线程得不到执行（也就是说，优先权不会导致死锁）。优先级较低的线程仅仅是执行的频率较低。
        下面是一个演示优先级等级的示例，可以使用getPriority()来读取现有线程的优先级，并且在任何时刻都可以通过setPriority()来修改它。
```Java
package com.liu.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liu on 17-6-20.
 */
public class SimplePriorities implements Runnable{
    private int countDown = 5;
    private volatile double d;//volatile确保计算不会被编译器优化
    private int priority;

    public SimplePriorities(int priority){
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread()+": "+countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while(true){
            for(int i =0;i<10000;i++){
                d+=(Math.PI + Math.E)/(double)i;
                if(i%1000 == 0){
                    Thread.yield();
                }
            }
            System.out.println(this);
            if(--countDown==0){
                return;
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
```
        注：尽管JDK有10个优先级，但是它与多数操作系统都不能映射的很好。比如Windows有7个优先级且不是固定的，所以这种映射关系也是不确定的。Sun的Solaris有2^31个优先级。唯一可移植的方法是当调整优先级的时候，只是用MAX_PRORITY、NORM_PRIORITY和MIN_PRIORITY。
####1.6 让步
        如果知道已经完成了run()方法的循环的一次迭代过程中所需的工作，就可以给线程调度机制一个暗示：你的工作已经完成的差不多了。可以让个别的线程CPU了。这个暗示也已通过调用yield()方法来做出（不过这只是一个暗示，没有任何机制保证它会被采纳）。
####1.7 后台（daemon）线程//守护线程
        所谓后台线程，是指在程序运行的时候在后台提供一种通用服务的线程，并且这种线程并不属于程序中不可或缺的部分。因此，当所有的非后台线程执行结束时，程序也就终止了，同时会杀死进行中所有的后台线程。反过来说，只要任何非后台线程还在运行，程序就不会终止。比如,main()是非后台线程。
```Java
package com.liu.demo;

import java.util.concurrent.TimeUnit;

/**
 * Created by liu on 17-6-21.
 */
public class Daemons {
    public static void main(String[] args) throws Exception{
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon() = "+d.isDaemon()+", ");
        TimeUnit.MILLISECONDS.sleep(100);
    }
}

class DaemonSpawn implements Runnable{
    @Override
    public void run() {
        while(true){
            Thread.yield();
        }
    }
}

class Daemon implements Runnable{
    private Thread[] threads = new Thread[10];
    @Override
    public void run() {
        for(int i=0;i< threads.length;i++){
            threads[i] = new Thread(new DaemonSpawn());
            threads[i].start();
            System.out.println("DaemonSpawn "+i+" started. ");
        }
        for(int i=0;i< threads.length;i++){
            System.out.println("thread["+i+"].isDaemon() = "+threads[i].isDaemon());
        }
        while(true){
            Thread.yield();
        }
    }
}
```
        注：Daemon线程被设置成了后台模式，然后派生出许多子线程，这些线程并没有被显示的设置为后台模式，不过它们的确是后台线程。接着，Daemon线程进入了无限循环，并在循环里调用yield()方法将控制权交给了其他线程。此外后台线程不执行run()中的try-catch-finally的finally块。
####1.8 异常捕获
        由于线程的本质特性，是得你不能捕获从线程中逃逸的异常，一旦异常逃出任务的run()方法，就会向外传播到控制台，除非你采用特殊的步骤捕获这种错误的异常。、、
        为了解决这个问题，我们要修改Executor产生线程的方式，Thread.UncaughtExceptionHandler是Java SE5红的新接口，它允许你在每个Thread对想上都附着一个异常处理器。
        Thread.UncaughtExceptionHandler.uncaughtException()会在线程因未捕获的异常而临近死亡时被调用。为了使用它，我们创建爱你一个新类型的ThreadFactory，它将在每个新创建的Thread对象上附着一个Thread.UncaughtExveptionHandler。我们将这个工厂传递给Executors创建新的ExecutorService方法。
```Java
package com.liu.demo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/**
 * Created by liu on 17-6-21.
 */
public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread());
//        exec.execute(new Runnable() {
//            @Override
//            public void run() {
//                Thread thread = Thread.currentThread();
//                System.out.println("run() by annonymous class "+thread);
//                System.out.println("eh = "+ thread.getUncaughtExceptionHandler());
//                throw new RuntimeException();
//            }
//        });
    }
}

class ExceptionThread implements Runnable{
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by "+t);
        System.out.println("eh = "+t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandle implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught "+e);
    }
}


class HandlerThreadFactory implements ThreadFactory{
    //创建一个工厂类。。为每个线程附着一个异常处理器（new MyUncaughtExceptionHandler()）
    //MyUncaughtExceptionHandler类中含有一个捕获异常的方法，会自动捕获异常
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this+" creating new Thread");
        Thread thread = new Thread(r);
        System.out.println("created "+thread);
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandle());
        System.out.println("eh = "+thread.getUncaughtExceptionHandler());
        return thread;
    }
}
```
```Java
package com.liu.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liu on 17-6-21.
 * 为了在代码中处处使用相同的异常处理器，更简单的方式是在Thread类中设置静态域，并将这个处理器设置为默认的未捕获异常处理器。
 */
public class SettingDefaultHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandle());
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExceptionThread());
    }
}
```