package testessence.mail.framework.app;

public class GmailAppInfo implements testessence.mail.framework.app.AppInfo {
    public String getAppName() {
        return "Gmail";
    }
    public String getActivityName() {
        return "com.google.android.gm.ConversationListActivity";
    }

    public String getPackageName() {
        return "com.google.android.gm";
    }
}
