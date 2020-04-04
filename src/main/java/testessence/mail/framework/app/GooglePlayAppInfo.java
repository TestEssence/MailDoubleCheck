package testessence.mail.framework.app;
public class GooglePlayAppInfo implements AppInfo {
    public String getPackageName() {
        return "com.android.vending";
    }
    public String getActivityName() {
        return ".AssetBrowserActivity";
    }
    public String getAppName() {
        // sample to open facebook app
        //launchIntent.setData(Uri.parse("market://details?id=com.facebook.katana"));
        return "GooglePlay";
    }
}
