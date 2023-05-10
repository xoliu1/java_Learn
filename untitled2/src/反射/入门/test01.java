package 反射.入门;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/
public class test01 {
    public static void main(String[] args) throws Exception {
        String path = "反射.入门.cat";
        String method = "hi";
        //1.加载类，返回路径path所对应的Class类型对象aclass
        Class<?> aClass = Class.forName(path);
        //2.获取aclass对象对应的对象实例
        // Class.newInstance()  返回对象实例
        Object obj = aClass.newInstance();
        //obj 的运行类型是Cat类(可以进行强转)
        //此时得到该类
        //我们不知道调用哪个方法，只知道调用method方法，所以
        //3. 通过CLass对象得到类的方法
        //Class.getMethod(String method)返回Class类的method方法
        //在反射中，可以把方法视为对象，正所谓：“万物皆对象”
        Method method1 = aClass.getMethod(method);
        //通过返回的Method类型的对象，来调用这个方法
        method1.invoke(obj);
        //Class.getField(String name) 返回name对应的成员变量
        //getField 不能得到私有属性
        Field age = aClass.getField("age");
        System.out.println(age);            //*输出public int 反射.入门.cat.age
        System.out.println(age.get(obj));   //*输出 age 的值 1

        Constructor<?> constructor = aClass.getConstructor();
        System.out.println(constructor);
        //输出public 反射.入门.cat()
        Constructor<?> constructor1 = aClass.getConstructor(String.class);
        //*这里的String.class指的是String类所对应的Class对象
        //*这里会返回带有String形参的构造器对象，这个构造器对象可以调用方法
        System.out.println(constructor1);
        //输出public 反射.入门.cat(java.lang.String)
//        Constructor<?> constructor2 = aClass.getConstructor(Integer.class, String.class);
//        System.out.println(constructor2);
    }
}
