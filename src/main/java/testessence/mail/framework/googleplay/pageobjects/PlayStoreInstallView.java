package testessence.mail.framework.googleplay.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;
import testessence.mail.framework.core.PageObject;

public class PlayStoreInstallView extends PageObject {
    public PlayStoreInstallView(AppiumDriver<MobileElement> driver){
        super(driver);
        setPageIdentificationElement(buttonRight);
        setCustomTiming(120);
    }
    @AndroidFindBy(id = "com.android.vending:id/install_bar")
    private MobileElement barInstallBar;
    @AndroidFindBy(id = "com.android.vending:id/title_thumbnail")
    private MobileElement imageThumbnail;
    @AndroidFindBy(id = "com.android.vending:id/title")
    private MobileElement labelTitle;
    @AndroidFindBy(id = "com.android.vending:id/creator_name")
    private MobileElement labelCreatorName;
    @AndroidFindBy(id = "com.android.vending:id/right_button")
    private MobileElement buttonRight;

    public String getTitle(){
        waitToLoad(labelTitle);
        return labelTitle.getText();
    }
    public void tapUpdate(){
        waitToLoad(buttonRight);
        Assert.assertEquals(buttonRight.getText(), "Update", "Update is not available");
        buttonRight.click();
        waitForText(buttonRight, "Open");
    }
    public void tapInstall(){
        waitToLoad(buttonRight);
        Assert.assertEquals(buttonRight.getText(), "Install");
        buttonRight.click();
        waitForText(buttonRight, "Open");
    }
    public void tapOpen(){
        waitToLoad(buttonRight);
        Assert.assertEquals(buttonRight.getText(), "Open");
        buttonRight.click();
    }
}
