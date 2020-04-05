package testessence.mail.framework.googleplay.pageobjects;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import testessence.mail.framework.core.PageObject;

public class PlayStoreView extends PageObject {
    public PlayStoreView(AppiumDriver<MobileElement> driver){
        super(driver);
        setPageIdentificationElement(containerSearchBox);
        setCustomTiming(120);
    }
    @AndroidFindBy(id = "com.android.vending:id/search_bar")
    private MobileElement containerSearchBox;
    @AndroidFindBy(id = "com.android.vending:id/search_bar_text_input")
    private MobileElement editSearch;


    private void tapSearch(){
        containerSearchBox.click();
        waitToLoad(editSearch);
    }
    public PlayStoreInstallView searchApp(String appName){
        tapSearch();
        editSearch.sendKeys(appName);
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        return new PlayStoreInstallView(driver);
    }
}
