package IO;

import java.io.*;

public class writeFile {
    public void method1() {
        FileWriter fw = null;
        try {
//����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
            File f=new File("E:\\dd.txt");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println("׷������");
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void method2(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(conent+"\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("����ִ��:"+conent);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void method3(String fileName, String content) {
        try {
// ��һ����������ļ���������д��ʽ
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
// �ļ����ȣ��ֽ���
            long fileLength = randomFile.length();
// ��д�ļ�ָ���Ƶ��ļ�β��
            randomFile.seek(fileLength);
            randomFile.writeBytes(content+"\r\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        for(int i=0;i<100;i++){
            writeFile.method2("E:\\JUC\\����\\���������ļ�\\JUC\\800��ҵ\\201811\\СӮ���\\4.5sessionid��ͬ\\11.txt",i+"");

        }
    }
}

