package testessence.mail.framework.googleplay.pageobjects;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import testessence.mail.framework.core.PageObject;

import java.util.List;


public class SignInView extends PageObject {
    public SignInView(AppiumDriver<MobileElement> driver) {
        super(driver);
        setPageIdentificationElement(buttonSignIn);
    }
    @AndroidFindBy(id = "com.android.vending:id/unauth_home_sign_in_button")
    private MobileElement buttonSignIn;
}
