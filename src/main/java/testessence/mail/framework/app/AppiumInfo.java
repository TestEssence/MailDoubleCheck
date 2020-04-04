package testessence.mail.framework.app;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumInfo {
    private String appiumHost;
    private int port = 4723;

    public AppiumInfo(String appiumHost, int port){
        this.appiumHost = appiumHost;
        this.port = port;
    }
    public AppiumInfo(String appiumHost){
        this.appiumHost = appiumHost;
    }
    public URL getUrl() throws MalformedURLException {
        return new URL(String.format("http://%s:%d/wd/hub", appiumHost, port));
    }
}
