package JUC.plugin;

import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * �������������ڲ��Ŀ¼������jar������Ϊ���ֵ���Դ(jar)����һ���������,����Ӧ��jar��ӵ�classpath��
 * @author strawxdl
 */
public class PluginClassLoader extends URLClassLoader {

    private List<JarURLConnection> cachedJarFiles = new ArrayList<JarURLConnection>();
    public PluginClassLoader() {
        super(new URL[] {}, findParentClassLoader());
    }

    /**
     * ��ָ�����ļ�url��ӵ����������classpath��ȥ��������jar connection�������Ժ�ж��jar
     * @param һ���������������classpath����ӵ��ļ�url
     * 
     * ����addURLFile��jar:file://home/dps/resin-4.0.13/webapps/dps/adapter_jar_lib/DataPushForTest1.jar!/
2018-03-27 17:11:11,252 DEBUG - [pool-2-thread-1] [PluginClassLoader] PluginClassLoader.addURLFile() �쳣: java.net.UnknownHostException: home

     */
    public void addURLFile(URL file) {
        try {
            // �򿪲������ļ�url����
            
            URLConnection uc = file.openConnection();
            System.out.println("UC:"+uc);
            if (uc instanceof JarURLConnection) {
                uc.setUseCaches(true);
                ((JarURLConnection) uc).getManifest();
                cachedJarFiles.add((JarURLConnection)uc);
            }
        } catch (Exception e) {
            System.err.println("Failed to cache plugin JAR file: " + file.toExternalForm());
        }
        addURL(file);
    }
    
    /**
     * ж��jar��
     */
    public void unloadJarFiles() {
        for (JarURLConnection url : cachedJarFiles) {
            try {
                System.err.println("Unloading plugin JAR file " + url.getJarFile().getName());
                url.getJarFile().close();
                url=null;
            } catch (Exception e) {
                System.err.println("Failed to unload JAR file\n"+e);
            }
        }
    }

    /**
     * ��λ���ڵ�ǰ�����ĵĸ��������
     * @return ���ؿ��õĸ��������.
     */
    private static ClassLoader findParentClassLoader() {
        ClassLoader parent = PluginManager.class.getClassLoader();
        if (parent == null) {
            parent = PluginClassLoader.class.getClassLoader();
        }
        if (parent == null) {
            parent = ClassLoader.getSystemClassLoader();
        }
        return parent;
    }
}
