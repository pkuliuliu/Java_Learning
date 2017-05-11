/**
 *  * Created by liu on 17-5-11.
 *   */
public class Main {
    public static void main(String[] args) {
        Integer n1 = new Integer(1);
        Integer n2 = new Integer(1);
        Integer n3 = -128;
        Integer n4 = -128;
        System.out.println(n1==n2);
        System.out.println(n3==n4);
        System.out.println(n1.equals(n2));
    }
}
