package Demo;

import java.io.*;

public class Person implements Serializable {

    private String userName;
    private  transient String password;

    public Person(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
 /*   private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); //序列化所有非transient字段,必须是该方法的第一个操作
        out.writeObject(password); //序列化transient字段
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); //反序列化所有非transient字段,必须是该方法的第一个操作
        password = (String)in.readObject(); //反序列化transient字段
    }*/
     @Override
     public String toString() {
        return "userName:" + userName + " password:" + password;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
//序列化一个对象(存储到一个文件)
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.out"));
        oos.writeObject("Save a object:\n");
        oos.writeObject(new Person("Bruce", "123456"));
        oos.close();

//反序列化,将该对象恢复(存储到一个文件)
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.out"));
        String s = (String)ois.readObject();
        Person p = (Person)ois.readObject();
        System.out.println(s + p);

//序列化一个对象(存储到字节数组)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos2 = new ObjectOutputStream(baos);
        oos2.writeObject("Save another object:\n");
        oos2.writeObject(new Person("Phil", "654321"));
        oos2.close();

//反序列化,将该对象恢复(存储到字节数组)
        ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        s = (String)ois2.readObject();
        p = (Person)ois2.readObject();
        System.out.println(s + p);
    }
}


