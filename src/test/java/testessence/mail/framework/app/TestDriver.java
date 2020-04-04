package testessence.mail.framework.app;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import testessence.mail.framework.device.DeviceInfo;

import java.net.MalformedURLException;

@Test
public class TestDriver {
    @Test(groups = {"validate"})
    public void shouldStartDriverForGooglePlay() throws MalformedURLException {
        AppCapabilities cap = new AppCapabilities(
                    new GooglePlayAppInfo(),
                    new DeviceInfo("Pixel 2 API 26", "10.0", true));
        AndroidDriver driver = new AndroidDriver(new AppiumInfo("192.168.0.230").getUrl(), cap);
        driver.quit();
    }
    @Test(groups = {"validate"})
    public void shouldStartDriverForGmail() throws MalformedURLException {
        AppCapabilities cap = new AppCapabilities(
                new GmailAppInfo(),
                new DeviceInfo("Pixel 2 API 26", "10.0", true));
        AndroidDriver driver = new AndroidDriver(new AppiumInfo("192.168.0.230").getUrl(), cap);
        driver.quit();
    }
}
