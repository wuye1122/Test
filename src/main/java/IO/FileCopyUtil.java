package IO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
public class FileCopyUtil {

    // http://www.cnblogs.com/zq-boke/p/8523710.html
    public static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf))!= -1) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }


    public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            if(null!=inputChannel){
                inputChannel.close();
            }
            if(null!=outputChannel){
                outputChannel.close();
            }
        }
    }

    public static void copyFileUsingApacheCommonsIO(File source, File dest)
            throws IOException {
        FileUtils.copyFile(source, dest);
    }

    public static void copyFileUsingJava7Files(File source, File dest)
            throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }


    public static void main(String[] args) throws IOException {
        File source = new File("E:\\JUC\\Index_Start_1.usml");
        File dest = new File("E:\\JUC\\Index_Start_2.usml");


        File source1 = new File("E:\\JUC\\桌面\\其他桌面文件\\JUC\\800企业\\201810\\IVR自助配置\\test\\Index_Start_1.usml");
        File dest1 = new File("E:\\JUC\\桌面\\其他桌面文件\\JUC\\800企业\\201810\\IVR自助配置\\test\\Index_Start_4.usml");

        // copy file using
        long start = System.nanoTime();
        long end;
        copyFileUsingFileStreams(source1, dest1);
        System.out.println("Time taken by FileStreams Copy = "
                + (System.nanoTime() - start));

        // copy files using java.nio.FileChannelsource = new File("C:\\Users\\nikos7\\Desktop\\files\\sourcefile2.txt");
        dest = new File("E:\\JUC\\Index_Start_3.usml");
        start = System.nanoTime();
        copyFileUsingFileChannels(source, dest);
        end = System.nanoTime();
        System.out.println("Time taken by FileChannels Copy = " + (end - start));

        // copy file using Java 7 Files classsource = new File("C:\\Users\\nikos7\\Desktop\\files\\sourcefile3.txt");
       /* dest = new File("E:\\JUC\\Index_Start_4.usml");
        start = System.nanoTime();
        copyFileUsingJava7Files(source, dest);
        end = System.nanoTime();
        System.out.println("Time taken by Java7 Files Copy = " + (end - start));*/

        // copy files using apache commons iosource = new File("C:\\Users\\nikos7\\Desktop\\files\\sourcefile4.txt");
        dest = new File("E:\\JUC\\Index_Start_5.usml");
        start = System.nanoTime();
        //copyFileUsingApacheCommonsIO(source, dest);
        end = System.nanoTime();
        System.out.println("Time taken by Apache Commons IO Copy = "
                + (end - start));



    }


}
