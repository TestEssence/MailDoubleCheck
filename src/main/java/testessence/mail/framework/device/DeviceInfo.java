package testessence.mail.framework.device;

public class DeviceInfo {
    private String name = "Pixel 2 API 26";
    private String platformVersion = "8.0";
    private boolean isEmulated = true;
    private int systemPort = 8200;
    public static DeviceInfo getDefaultDeviceInfo(){
        return new DeviceInfo();
    }
    public  DeviceInfo(String name, String platformVersion, boolean isEmulated ){
        this.name = name;
        this.platformVersion = platformVersion;
        this.isEmulated = isEmulated;
    }
    private  DeviceInfo(){}
    public String getDeviceName(){
        return  name;
    }
    public String getPlatformVersion(){
        return platformVersion;
    }
    public int getSystemPort (){
        return systemPort;
    }
    public boolean isEmulated(){return isEmulated;}
}
