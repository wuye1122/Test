package wuhl.plugin;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.jcraft.jsch.Logger;

public class TestMain {

	/**
	 * @author wuhl
	 * void
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	
	public static void main(String []args) throws IOException, ClassNotFoundException{
       
	/*	Class a=Class.forName("wuhl.plugin.Plugin11");
		System.out.println(a.getCanonicalName());
		*/
		PluginManager manager=new PluginManager();
		
		//����jar��·��
		manager.loadPlugin("wuhl.plugin.Plugin22");
	//	manager.loadPlugin("wuhl.plugin.Plugin11");
	//	manager.loadPlugin("wuhl.plugin.Plugin33");

	//	manager.doSome("wuhl.plugin.Plugin22", "wuhl.plugin.Plugin2");

		
		manager.loadPlugin("wuhl.plugin.Plugin22");

		System.out.println(manager.pluginMap);

		System.out.println(manager.getLoader("wuhl.plugin.Plugin22"));

  //      String pluginurl = "jar:file:/home/dps/resin-4.0.13/webapps/dps/adapter_jar_lib/DataPushForTest1.jar!/";
/*
		URL   url = new URL("wuhl.plugin.Plugin22");

		try {
			manager.getLoader("wuhl.plugin.Plugin22").addURLFile(url);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		*/
	

		System.out.println(manager.getLoader("wuhl.plugin.Plugin22").getURLs().length);
		
		System.out.println(manager.getLoader("wuhl.plugin.Plugin22").getURLs()[0]);

     Class<?> forName = Class.forName("wuhl.plugin.Plugin2", true, manager.getLoader("wuhl.plugin.Plugin22"));//this.pluginMap.get(pluginName).loadClass(packagename);
     //   Class<?> forName = Class.forName("wuhl.plugin.Plugin2");//this.pluginMap.get(pluginName).loadClass(packagename);

        Plugin ins;
		try {
			ins = (Plugin)forName.newInstance();
			//ins.doSome();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  /* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    //String cmd = br.readLine();
	    String cmd= "bye";
	    while(!cmd.equals("bye")){
	        if(cmd.startsWith("do")){
	            String pluginName = cmd.split(" ")[1];
	            manager.doSome(pluginName);
	        }
	        if(cmd.startsWith("load")){
	            String pluginName = cmd.split(" ")[1];
	            manager.loadPlugin(pluginName);
	        }
	        if(cmd.startsWith("unload")){
	            String pluginName = cmd.split(" ")[1];
	            manager.unloadPlugin(pluginName);
	        }
	        cmd = br.readLine();
	    }*/
	}

	}
	
