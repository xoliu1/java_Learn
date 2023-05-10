package 反射.入门;

/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/
public class create {
    public static void main(String[] args) throws Exception {
        String path = "反射.入门.cat";
        Class<?> cls = Class.forName(path);
        Object o = cls.newInstance();
//        //无参
//        Object o = cls.newInstance();
//        //有参
//        /** 利用String构造器，括号内填形参的类型对应的Class对象*/
//        Constructor<?> constructor = cls.getConstructor(String.class);
//        //然后用返回的构造器构造对象
//        Object o1 = constructor.newInstance("小6");
//        /**只能用这个方法构造出public修饰的构造器
//         * 用getDeclaredConstructor（）+ 暴破，即可获得private的构造器
//         */
//        Constructor<?> constructor1 = cls.getDeclaredConstructor(int.class, String.class);
//        constructor1.setAccessible(true);
//        //这个就是【暴破】，破坏封装性，这样就可以访问private构造器了
//        Object o2 = constructor1.newInstance(123, ":XiaoMiao");


//        Field age = cls.getDeclaredField("age");
//        //set构造，利用 filed.set(Class aclass, int num)
//        //将aclass中field对应的属性修改为num
//        age.set(cls, 80);
//        System.out.println(age.get(cls));//获取age的值
//        //私有属性
//        Field privateAge = cls.getDeclaredField("privateAge");
//        //私有属性开始暴破
//        privateAge.setAccessible(true);
//        privateAge.set(cls, 1);
//        //静态属性
//        Field key = cls.getDeclaredField("key");
//        key.set(null, 9);
//        System.out.println(key.get(null));
//        //只有静态属性可以这样用,因为其属于所有类



    }
}
