package testessence.mail.framework.googleplay;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testessence.mail.framework.app.AppCapabilities;
import testessence.mail.framework.app.AppiumInfo;
import testessence.mail.framework.app.GooglePlayAppInfo;
import testessence.mail.framework.device.DeviceInfo;
import testessence.mail.framework.googleplay.pageobjects.PlayStoreInstallView;
import testessence.mail.framework.googleplay.pageobjects.PlayStoreView;
import testessence.mail.framework.googleplay.pageobjects.SignInView;

import java.net.MalformedURLException;

@Test
public class TestGooglePlay {
    private AppiumDriver<MobileElement> driver;

    @BeforeTest
    public void beforeTest() throws MalformedURLException {
        AppCapabilities cap = new AppCapabilities(
                new GooglePlayAppInfo(),
                new DeviceInfo("Pixel 2 API 26", "10.0", true));
        driver = new AndroidDriver<MobileElement>(new AppiumInfo("192.168.0.230").getUrl(), cap);
    }

    @Test(groups = {"validate"})
    public void shouldOpenGooglePlayStore() {
        PlayStoreView playStoreView = (PlayStoreView) new PlayStoreView(driver).get();
    }

    @Test(groups = {"validate"})
    public void shouldFindGmailInGooglePlayStore()  {
        PlayStoreView playStoreView = (PlayStoreView) new PlayStoreView(driver).get();
        PlayStoreInstallView installView = playStoreView.searchApp("gmail");
        Assert.assertEquals(installView.getTitle(), "Gmail");
    }

    @Test(groups = {"validate"})
    public void shouldUpdateGmailInGooglePlayStore()  {
        PlayStoreView playStoreView = (PlayStoreView) new PlayStoreView(driver).get();
        PlayStoreInstallView installView = playStoreView.searchApp("gmail");
        installView.tapUpdate();
    }

    @Test(groups = {"validate"})
    public void shouldOpenGmailFromGooglePlayStore()  {
        PlayStoreView playStoreView = (PlayStoreView) new PlayStoreView(driver).get();
        PlayStoreInstallView installView = playStoreView.searchApp("gmail");
        installView.tapOpen();
    }


    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
