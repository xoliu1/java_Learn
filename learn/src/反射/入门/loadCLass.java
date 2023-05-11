package 反射.入门;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/
public class loadCLass {
    public static void main(String[] args) throws Exception {
        String path = "反射.入门.cat";
        //首先获取Class对象
        Class<?> cls = Class.forName(path);
        //得到详细类名
        System.out.println(cls.getName());
        //得到简单类名
        System.out.println(cls.getSimpleName());
        //得到public变量，包括本类以及父类的成员属性数组
        Field[] fields = cls.getFields();
        for (Field i: fields) {
            System.out.print(i.getName() + " ");//要加getName得到名字
        }
        //如果想要获取所有属性（包括父类的），使用getDeclaredFields()  //不要少了s
        //父类的父类都可以获取
        Field[] declaredField = cls.getDeclaredFields();
        //获取方法，构造器的也一样,改成Methods即可
        //Package形式返回包的信息
        System.out.println(cls.getPackage());
        //以Class形式返回父类信息
        Class<?> superclass = cls.getSuperclass();
        //以Class[]形式返回接口信息
        Class<?>[] interfaces = cls.getInterfaces();
        //返回注解信息
        Annotation[] annotations = cls.getAnnotations();
    }
}
