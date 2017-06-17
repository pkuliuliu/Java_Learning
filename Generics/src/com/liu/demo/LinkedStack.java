package com.liu.demo;

/**
 * Created by liu on 17-6-17.
 */
public class LinkedStack<T> {
    private  class Node{
        T item;
        Node next;
        Node(){item=null;next=null;}
        Node(T item,Node next){
            this.item = item;
            this.next = next;
        }
        boolean end(){return item==null&&next==null;}
    }

    private Node top = new Node();//end sentinel
    public void push(T item){
        top = new Node(item,top);//压栈
    }

    public T pop(){
        T result = top.item;
        if(!top.end()){
            top = top.next;
        }else{
            System.out.println("!!stack is empty now...");
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> lss = new LinkedStack<>();
        for(String str : "Phasers on stun".split("\\W")){
            lss.push(str);
        }

        String s;
        while((s=lss.pop())!=null){
            System.out.println(s);
        }
    }
}
