package channelsoft.client;

import channelsoft.server.impl.WeatherImpl;

public class DDD {

    public static void main(String[] args) {
        //����������ͼ
        WeatherImplService weatherImplService = new WeatherImplService();
        //
        WeatherImpl portType = weatherImplService.getWeatherImplPort();
        //���÷������˷���
        String weatherInfo = portType.getWeatherByCity("����");
        System.out.println("info:" + weatherInfo);
    }
}
