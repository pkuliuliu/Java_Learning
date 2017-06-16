#正则表达式

###字符
|符号|意义|
|:----:|:----:|
|B|匹配指定字符B|
|\xhh|十六进制值为oxhh的字符串|
|\uhhh|十六进制表示为oxhhh的Unicode字符|
|\t|制表符Tab|
|\n|换行符|
|\r|回车|
|\f|换页|
|\e|转义（Escape）|


###字符类
|符号|意义|
|:----:|:----:|
|.|任意字符|
|[abc]|包含abc的任何字符|
|[^abc]|除了a、b、c之外的任何字符（否定）|
|[a-zA-Z]|从a-z或从A-Z的任何字符（范围）|
|[abc[hij]]|任意a b c 和h i j字符（与a|b|c|h|i|j作用相同）（合并）|
|[abc&&[hij]]|任意h i j(交集)|
|\s|空白符（空格、tab、换行、换页、回车）|
|\S|非空白符([^\s])|
|\d|数字[0-9]|
|\D|非数字[^0-9]|
|\w|词字符[a-zA-Z0-9]|
|\W|非词字符[^\W]|


###逻辑操作符
|符号|意义|
|:----:|:----:|
|XY|Y跟在X后面|
|X\|Y|X或Y|
|(X)|捕获组(capturing group).可以在表达式中用\i引用第i个捕获组|
###边界匹配符
|字符|意义|字符|意义|
|:----:|:----:|:----:|:----:|
|^|一行的开始|\B|非词的边界|
|$|一行的结束|\G|前一个匹配的结束|
|\b|词的边界|||
###代码
```java
public class MatchRudolph {
    public static void main(String[] args) {
        /**
         * [rR]匹配r或者R中的任意一个
         * [R.*]R后面跟着任意个任意字符
         */
        for (String pattern :
                new String[]{"Rudolph","[rR]udolph","[rR][aeiou][a-z]ol.*","R.*"}) {
            System.out.println("Rudolph".matches(pattern));
            /**
             * true
             */
        }

        System.out.println("R".matches("R.+"));//false
        System.out.println("R".matches("R.?"));//true
        System.out.println("R".matches("R.+"));//false
    }
}
```
###量词
量词描述了一个字符吸收输入文本的方式：
    1. 贪婪型：量词总是贪婪的。除非有其他的选项被设置。贪婪表达式会为所有可能的模式发现尽可能多的匹配。导致此问题的一个典型理由就是假定我们的模式仅能匹配第一个可能的字符组，如果他是贪婪的，那么它就会继续往下匹配
    2. 勉强型：用问号来指定，这个量词匹配满足模式所需的最少字符数。因此也称作懒惰的最小匹配的、非贪婪的。
    3. 占有型：暂时Java独有
|贪婪型|勉强型|占有型|如何匹配|
|:----:|:----:|:----:|:----:|
|X?|X??|X?+|一个或零个X|
|X*|X*?|X*+|零个或多个X|
|X+|X+?|X++|一个或多个X|
|X{n}|X{n}?|X{n}+|恰好n次X|
|X{n,}|X{n,}?|X{n,}+|至少n次X|
|X{n,m}|X{n,m}?|X{n,m}+|X至少n次，且不超过m次|

### Pattern和Matcher
```java
public class TestRegularExpression {
    public static void main(String[] args) {
        if(args.length<2){
            System.out.println("Usage:\njava TestRegularExpression characterSequence regularExpression");
            System.exit(0);
        }
        System.out.println("Input: \""+args[0]+"\"");

        for(String arg :args){
            System.out.println("Regular expression:\""+arg+"\"");
            Pattern p  = Pattern.compile(arg);//从arg表示的正则表达式中获得模式
            Matcher matcher = p.matcher(args[0]);//获得模式对字符串args[0]的代理
            while(matcher.find()){
                System.out.println("Match \""+matcher.group()+"\" at positions "+ matcher.start()+","+(matcher.end()-1));
            }
        }
        /**
         * matched()--整个字符串是否匹配正则表达式
         * lookingat()--字符串的始部分是否匹配正则表达式
         * find()--依次简单匹配，每次匹配一个目标
         * find(int start)--从start处开始匹配，每次匹配一个目标
         */
        Pattern pattern = Pattern.compile("abc");
        Matcher matcher = pattern.matcher("abcdefabcabc");
        System.out.println(matcher.matches());
        matcher = pattern.matcher("abcdefabcabc");
        System.out.println(matcher.lookingAt());
        matcher = pattern.matcher("abcdefabcabc");
        System.out.println(matcher.find());
        matcher = pattern.matcher("abcdefabcabc");
        System.out.println(matcher.find(0));
    }
}


public class Finding {
    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");

        while(matcher.find()){
            System.out.print(matcher.group()+" ");
        }

        System.out.println();
        int i=0;
        while (matcher.find(i)){
            System.out.println(matcher.group()+" ");
            i++;
        }
    }
}
```