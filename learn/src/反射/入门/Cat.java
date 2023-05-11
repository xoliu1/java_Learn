package 反射.入门;

/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/
public class Cat {
}


class cat {
    public int age ;
    public String name;
    public void hi(){
        System.out.println("Hello,FGH!");
    }

    private int privateAge ;

    static int key = 2;
    public cat() {
    }

    public void f1(){}

    public cat(int age) {
        this.age = age;
    }

    public cat(String name) {
        this.name = name;
    }

   private cat(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", protectAge=" + privateAge +
                '}';
    }
}