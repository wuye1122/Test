package channelsoft.client;

import channelsoft.server.impl.WeatherImpl;

public class DDD {

    public static void main(String[] args) {
        //创建服务视图
        WeatherImplService weatherImplService = new WeatherImplService();
        //
        WeatherImpl portType = weatherImplService.getWeatherImplPort();
        //调用服务器端方法
        String weatherInfo = portType.getWeatherByCity("北京");
        System.out.println("info:" + weatherInfo);
    }
}
