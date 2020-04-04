package testessence.mail.framework.app;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Test
public class TestAppiumInfo {
    @Test(groups = {"validate"})
    public void shouldFormAppiumUrl() throws MalformedURLException {
        AppiumInfo info = new AppiumInfo("192.168.0.230",4723);
        Assert.assertEquals(info.getUrl(), new URL("http://192.168.0.230:4723/wd/hub"));
    }
}
