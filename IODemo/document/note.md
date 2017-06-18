##InputSteeam类型
inputstream的作用是用来表示那些从不同数据源产生输入的类。这些数据员包括：
1. 字节数组
2. String对象
3. 文件
4. “管道”，工作方式与实际管道相似，即，从一端输入，另一端输出。
5. 一个有其他种类的刘组成的序列，以便我们可以将他们收集合并到一个流内
6. 其他数据源，如Internet等等

|类|功能|构造器参数|如何使用|
|:--:||||
|ByteArrayInputStream|允许将内存的缓冲区当做InputStream使用|缓冲区，字节将从中取出|作为一种数据源：将其与FilterInputStream对象相连以提供有用的接口|
|StringBufferInputStream|讲String转换成InputStream|字符串。底层实现使用StringBuffer|作为一种数据源|
|FileInputStream|用于从文件中读取信息|字符串,表示文件名、文件或者FileDescriptor对象|作为一种数据源|
|PipedInputStream|昌盛用于写入相关PipedOutputStream的数据|PipedOutputStrean|作为多线程中的数据源，将其与FilterInputStream对象。。。。|
|SequenceInputStream|将多个InputStream对象转换成一个InputStream|两个InputStream对象或一个容纳InputStream对象的容器Enumeration|作为一种数据源|
|FilterInputStream|抽象类，作为“装饰器”的接口|||
####FilterInputStream
|类|功能|构造器参数|如何使用|
|:--:||||
|DataInputStream|与DataOutputStream搭配使用，因此可以按照移植方式从流读取基本数据类型int,char,long等，类似自定义序列化|InputStream|包含用于度与基本类型数据的全部接口|
|BufferedInputStream|使用它可以防止每次读取都得进行实际写操作。代表“使用缓冲区”|InputStream|可以指定缓冲区的大小|
|LineNumberInputStream|跟踪输入流中的行号，可调用getLineNumber()和setLineNumber(int)|InputStream|仅增加行号因此可能要与接口对象搭配使用|
|PushbackInputStream|具有“能弹出一个字节的缓冲区”。因此可以讲多的最后一个字符回退|InputStream|通常作为编译器的扫描器，之所以包含在内是因为Java编译器的需要，我们可能永远用不到|

##OutputStream类型
该类别的类决定了输出所要去往的目标“字符数组、文件或者管道
|类|功能|构造器参数|如何使用|
|:--:||||
|ByteArrayOutputStream|在内存中创建缓冲区。送往“流”的数据都要放置在次缓冲区|缓冲区初始化尺寸（可选）|用于指定数据的目的地：将其与FilterOutputStream对象相连以提供有用接口|
|FileOutputStream|用于将信息写入文件|字符串，表示文件名、文件或者FileDescriptor对象|指定。。。|
|PipedOutputStream|任何写入其中的信息都会自动作为相关PipedInputStream的输出。实现“管道化”|PipedInputStream|指定。。。|
|FilterOutputStream|抽象类，作为“装饰器”的接口。|||
####FilterOutputStream
|类|功能|构造器参数|如何使用|
|:--:||||
|DataOutputStream|与DataInputStream搭配使用，因此可以按照可移植方式向六中写入基本类型数据(int,cahr,long等)|OutputStream|包含用于写入基本数据类型数据的全部接口|
|PrintStream|用于产生格式化输出。其中DataOutputStream处理数据的存储，PrintStream处理显示|OutputStream，可以中boolean值指示是否在每次换行时清空缓冲区（可选），应该是对OutputStream对象final封装|可能经常会用到他|
|BufferedOutputStream|使用它以避免每次发送数据时都要进行实际的写操作。代表“使用缓冲区”，可以调用flush()函数清空缓冲区|OutputStream，可以指定缓冲区大小（可选）|本质上不提供接口，仅向进程中添加缓冲区所必须。与接口对象搭配|

##Reader类

##Writer类


##常见用法
####1. 缓冲输入文件
```Java
package com.liu.demo.streamdemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by liu on 17-6-18.
 * 缓冲文件输入
 * 使用以String或File对象作为文件名的FileReader
 * 为了提高速度，将产生的引用传给一个BufferedReader构造器
 */
public class BufferedInputFile {

    public static String read(String filename) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();//StringBuilder 底层用的char[]数组实现
        while((s=bufferedReader.readLine())!=null){
            sb.append(s+"\n");
        }
        bufferedReader.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException{
        System.out.println(read("src/com/liu/demo/streamdemo/BufferedInputFile.java"));
    }
}

```
####2. 从内存输入
```Java
package com.liu.demo.streamdemo;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

/**
 * Created by liu on 17-6-18.
 * 从内存输入
 * 读入一个String来创建一个StringReader，然后调用read()每次读一个字符，并把它发送到控制台
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException{
        String str = "I am learning Java\n I am learning Java\n I am learning Java\n I am learning Java\n I am learning Java\n";
        StringReader stringReader = new StringReader(str);
        char[] buff = new char[20];
        while(stringReader.read(buff)!=-1){
            System.out.print(buff);
        }
//        int ch;
//        while((ch=stringReader.read())!=-1){
//            System.out.print((char) ch);
//        }
    }
}

```

####3. 基本文件输入输出
```Java
package com.liu.demo.streamdemo;

import java.io.*;

/**
 * Created by liu on 17-6-18.
 * 首先，创建一个与指定文件链接的FileWriter。然后，通过BufferedWriter将其包装起来用缓冲区输出
 * 然后再用用PrintWriter包装输出到新文件
 */
public class BasicFileOutput {
    static String file = "data/BasicFileOutput.out";

    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/com/liu/demo/streamdemo/BasicFileOutput.java"));

        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        String line;
        int i=0;
        while((line = bufferedReader.readLine())!=null){
            printWriter.write((i++) + line + "\n");
        }
        printWriter.flush();
        printWriter.close();
        bufferedReader.close();
    }
}
```
####4. 文本文件输出的快捷方式
```Java
package com.liu.demo.streamdemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liu on 17-6-18.
 * 文本文件输出的快捷方式
 * java SE5 在PrintWriter中添加了一个辅助构造器，是得你不必在每次希望创建文本文件并向其中写入时，都去执行所有的装饰操作。
 */
public class FileOutputShortCut {
    static String filename = "data/BasicFileOutputShortCut.out";

    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/liu/Workspace/Java/Java_Learning/IODemo/src/com/liu/demo/streamdemo/FileOutputShortCut.java"));
        PrintWriter printWriter = new PrintWriter(filename);
        int i=0;
        String line;
        while((line = bufferedReader.readLine())!=null){
            printWriter.write((i++)+" "+line+"\n");
        }
        printWriter.flush();
        printWriter.close();
        bufferedReader.close();
    }
}
```
####5. 存储和恢复数据
```Java
package com.liu.demo.streamdemo;

import java.io.*;

/**
 * Created by liu on 17-6-18.
 * 使用DataOutputStream输出写入，然后使用DataInputStream按照写入的顺序读
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) throws IOException{
        String filename = "data/data.dat";
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        dataOutputStream.writeDouble(3.1415926);
        dataOutputStream.writeUTF("That was pi");
        dataOutputStream.writeDouble(1.41413);
        dataOutputStream.writeUTF("that is root of 2");
        dataOutputStream.flush();
        dataOutputStream.close();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readUTF());
        dataInputStream.close();
    }
}
```

####6. 读写随机访问文件
```Java
package com.liu.demo.streamdemo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Created by liu on 17-6-18.
 * RandomAccessFile实现了DataInput和DataOutput两种接口，支持读和写，"r",,"rw",,不支持“w”
 * seek()函数可以到处移动，修改相应的值
 */
public class UsingRandomAccessFile {
    static String file ="data/rtest.dat";
    static void display() throws IOException{
        RandomAccessFile rf = new RandomAccessFile(file,"r");
        for(int i =0;i<7;i++){
            System.out.println("Value "+i+": "+rf.readDouble());
        }
        System.out.println(rf.readUTF());
        rf.close();
    }

    public static void main(String[] args) throws  IOException{
        RandomAccessFile rf = new RandomAccessFile(file,"rw");
        for(int i=0;i<7;i++){
            rf.writeDouble(i*1.414);
        }
        rf.writeUTF("The end of the file");
        rf.close();
        display();
        rf = new RandomAccessFile(file,"rw");
        rf.seek(5*8);
        rf.writeDouble(47.0001);//覆盖了第5个double，，覆盖了8个字节
        rf.close();
        display();
    }
}
```
####7.管道流
```Java
package com.liu.demo.streamdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by liu on 17-6-14.
 * 管道的价值只有在多线程中才能体现出来。因为管道流用于任务之间的通信
 *
 * 一个简单的生产者消费者模式demo
 */
public class PipedStreamDemo {

    public static void main(String[] args) throws Exception{
        // create pos and pis object ,then connect them
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis =  new PipedInputStream(pos);
        // create action demo
        InputStreamRunnable in = new InputStreamRunnable(pis);
        OutStreamRunnable out = new OutStreamRunnable(pos);
        // create two thread to run the demo
        new Thread(in).start();
        new Thread(out).start();
    }
    static class InputStreamRunnable implements Runnable{
        private PipedInputStream pis;
        public InputStreamRunnable(PipedInputStream pis){
            System.out.println("i am inStream, prepared to get message");
            this.pis = pis;
        }

        @Override
        public void run() {
            BufferedReader br = new BufferedReader(new InputStreamReader(pis));
            try {
                System.out.println(br.readLine());
                //System.out.println(br.readLine());
                br.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    static class OutStreamRunnable implements Runnable{
        private PipedOutputStream pos;
        public OutStreamRunnable(PipedOutputStream pos){
            this.pos = pos;
            System.out.println("I am OutStream, prepared to print something");
        }

        @Override
        public void run() {
            try{
                pos.write("hello world\n you cant get me".getBytes());
                pos.flush();
                pos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

```