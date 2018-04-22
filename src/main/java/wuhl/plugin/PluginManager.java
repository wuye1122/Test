package wuhl.plugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.jcraft.jsch.Logger;

public class PluginManager {
    static{
      //  System.out.println(PluginManager.class.getName());
    }
    public Map<String ,PluginClassLoader> pluginMap = new HashMap<String,PluginClassLoader>();
   // private static String packagename = "wuhl.plugin.Plugin1";
    public PluginManager(){

    }
    
    public void doSome(String pluginName,String packagename){

        try{
        	System.out.println(pluginMap.toString());
        	System.out.println(getLoader(pluginName));

            Class<?> forName = Class.forName(packagename, true, getLoader(pluginName));//this.pluginMap.get(pluginName).loadClass(packagename);
            Plugin ins = (Plugin)forName.newInstance();
            ins.doSome();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void addLoader(String pluginName,PluginClassLoader loader){
        this.pluginMap.put(pluginName, loader);
    }
    public PluginClassLoader getLoader(String pluginName){
        return this.pluginMap.get(pluginName);
    }
    public void loadPlugin(String pluginName){
        this.pluginMap.remove(pluginName);
        PluginClassLoader loader = new PluginClassLoader();
      //  String pluginurl = "jar:file:/D:/testclassloader/"+pluginName+".jar!/";
        String pluginurl = "jar:file:D:/testclassloader/"+pluginName+".jar!/";
        System.out.println("正在加载上传jar:-------"+pluginurl);
        
//jar:file://root/resin-4.0.13/webapps/dps/adapter_jar_lib/DataPushForTest1.jar!/

	 URL url = null;
try {
    url = new URL(pluginurl);
    loader.addURLFile(url);
            addLoader(pluginName, loader);
            System.out.println("load " + pluginName + "  success");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("正在加载上传jar:加载异常！");

        }
       
    }
    public void unloadPlugin(String pluginName){
        this.pluginMap.get(pluginName).unloadJarFiles();
        this.pluginMap.remove(pluginName);
    }
}