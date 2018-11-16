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
        out.defaultWriteObject(); //���л����з�transient�ֶ�,�����Ǹ÷����ĵ�һ������
        out.writeObject(password); //���л�transient�ֶ�
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); //�����л����з�transient�ֶ�,�����Ǹ÷����ĵ�һ������
        password = (String)in.readObject(); //�����л�transient�ֶ�
    }*/
     @Override
     public String toString() {
        return "userName:" + userName + " password:" + password;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
//���л�һ������(�洢��һ���ļ�)
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.out"));
        oos.writeObject("Save a object:\n");
        oos.writeObject(new Person("Bruce", "123456"));
        oos.close();

//�����л�,���ö���ָ�(�洢��һ���ļ�)
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.out"));
        String s = (String)ois.readObject();
        Person p = (Person)ois.readObject();
        System.out.println(s + p);

//���л�һ������(�洢���ֽ�����)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos2 = new ObjectOutputStream(baos);
        oos2.writeObject("Save another object:\n");
        oos2.writeObject(new Person("Phil", "654321"));
        oos2.close();

//�����л�,���ö���ָ�(�洢���ֽ�����)
        ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        s = (String)ois2.readObject();
        p = (Person)ois2.readObject();
        System.out.println(s + p);
    }
}


