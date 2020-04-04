package testessence.mail.framework.app;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import testessence.mail.framework.device.DeviceInfo;

public class AppCapabilities extends DesiredCapabilities {
    public AppCapabilities(AppInfo appinfo, DeviceInfo deviceInfo){

        setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        setCapability(MobileCapabilityType.DEVICE_NAME, deviceInfo.getDeviceName());
        setCapability(MobileCapabilityType.PLATFORM_VERSION, deviceInfo.getPlatformVersion());
        setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3);

        setCapability("appPackage", appinfo.getPackageName());
        setCapability("appActivity", appinfo.getActivityName());
        if(deviceInfo.isEmulated()){
            setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, deviceInfo.getSystemPort());
        }
    }
}
