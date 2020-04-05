package testessence.mail.framework.core;




import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


abstract public class PageObject extends LoadableComponent<PageObject> {
    protected AppiumDriver<MobileElement> driver;

    private int TimeoutImplicitlyWait = 1; //seconds
    protected int TimeoutPageLoad = 120; //seconds
    private int TimeoutFastLoook = 12; //seconds
    protected int SwipesToBottom = 30; // number of wide swipes needed to get to the bottom
    private boolean isScreenshotTaken = false;
    private boolean isScreenshotCaptureMode = true;
    private int swipeDelay = 700;
    private int scrollCounter = 0;
    private MobileElement pageIdentificationElement = null;


    public PageObject() {
        this.driver = null;
    }

    public PageObject(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(className = "UIAKeyboard")
    private MobileElement keyboard;


    /**
     * @return the list of visible MobileElement
     */
    private List<MobileElement> getVisibleElementsList(){
        List<MobileElement> labels =  driver.findElements(By.xpath("//android.widget.TextView"));
        labels.addAll( driver.findElements(By.xpath("//android.widget.Button")));
        labels.addAll(driver.findElementsByXPath("//android.widget.CheckedTextView"));
        return labels;
    }

    /**
     * scroll down until the element become visible
     * @param element
     * @return true if element is found
     */
    public  boolean swipeDownToControl (MobileElement element){
        int scrollAttemptsTimeout = 10;
        while ( !isElementPresent(element) && (scrollAttemptsTimeout-- > 0)){
            Dimension dim = driver.manage().window().getSize();
            int starty = (int) (dim.getHeight() * 0.8);
            int endy = (int) (dim.getHeight()* 0.2);
            int startx = dim.getWidth()/ 2;
            //  System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

            TouchAction ts = new AndroidTouchAction(driver);
            ts.press(PointOption.point(startx, starty))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeDelay)))
                    .moveTo(PointOption.point(startx, endy))
                    .release()
                    .perform();
            scrollCounter++;
        }
        return (isElementPresent(element));
    }

    public void swipeDownALittle(){
        swipeALittle(true);
    }


    /**
     * swipe a little
     * android only
     * @param isSwipeDown
     */
    private void swipeALittle(boolean isSwipeDown){
        Dimension dim = driver.manage().window().getSize();
        int starty =(int) (dim.getHeight() * 0.35);
        int endy = (int) (dim.getHeight() * 0.65);

        if (isSwipeDown) {
            starty = (int) (dim.getHeight() * 0.65);
            endy = (int) (dim.getHeight() * 0.35);
        }
        int startx = dim.getWidth()/ 2;

        TouchAction ts = new AndroidTouchAction (driver);
        ts.press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeDelay)))
                .moveTo(PointOption.point(startx, endy))
                .release()
                .perform();
        scrollCounter++;
    }

    /**
     * @param first element to cmpare
     * @param second element to compare
     * @return true if Mobile Elements attributes mathces
     */
    private boolean arElementsEqual(MobileElement first, MobileElement second){
        return first.getText().equals(second.getText())&&
                first.getAttribute("resource-id").equals(second.getAttribute("resource-id"))&&
                first.getAttribute("class").equals(second.getAttribute("class"));
    }

    /**
     * Scroll one page down (roughly)
     * android only
     */
    protected void swipePageDown(){
        Dimension dim = driver.manage().window().getSize();
        int starty = (int) (dim.getHeight() * 0.75);
        int endy = (int) (dim.getHeight()* 0.25);
        int startx = dim.getWidth()/ 2;

        TouchAction ts = new AndroidTouchAction(driver);
        ts.press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeDelay)))
                .moveTo(PointOption.point(startx, endy))
                .release()
                .perform();
        scrollCounter++;
    }

    /**
     * Indicate that page view was changed, and is different from the last captured screenshot
     * @return
     */
    private PageObject invalidateScreenshotCaptured(){
        isScreenshotTaken = false;
        return this;
    }


    /**
     * Wait for an element to load
     * @param mobileElement:
     * @return
     */
    public PageObject waitToLoad(MobileElement mobileElement ){
        WebDriverWait wait = new WebDriverWait(driver, TimeoutPageLoad);
        wait.until(ExpectedConditions.visibilityOf(mobileElement));
        return this;
    }

    /**
     * Wait for an element to load
     * @param mobileElement:
     * @return
     */
    public PageObject waitForText(MobileElement mobileElement, String text ){
        WebDriverWait wait = new WebDriverWait(driver, TimeoutPageLoad);
        wait.until(ExpectedConditions.attributeToBe(mobileElement, "text", text));
        return this;
    }


    public PageObject waitToLoad(MobileElement mobileElement1, MobileElement mobileElement2){
        WebDriverWait wait = new WebDriverWait(driver, TimeoutPageLoad);
        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.visibilityOf(mobileElement1),
                        ExpectedConditions.visibilityOf(mobileElement2)
                )
        );
        return this;
    }

    public PageObject waitToLoad(List<MobileElement> mobileElements ){
        if (mobileElements.isEmpty())
            throw new NotFoundException("No Elements Found");
        return this;
    }

    public void hideKeyboardIfVisible() {
        if (keyboard != null) {
            driver.hideKeyboard();
            //labelElement.click();
            //
        }
    }
    public String getPageId(){
        return this.getClass().getSimpleName();
    }


    protected void tapElement (MobileElement mobileElement ){
        new Actions(driver).
                click(mobileElement).perform();
    }

    /**
     * @param element1
     * @return true if element is found on the screen
     */
    public boolean isElementPresent(MobileElement element1) {
        WebDriverWait wait = new WebDriverWait(driver, 1);
        try {
            return wait.until(ExpectedConditions.visibilityOf(element1)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    private PageObject setFastTiming(){
        driver.manage().timeouts().implicitlyWait(TimeoutFastLoook, TimeUnit.SECONDS);
        return this;
    }

    private PageObject setDefaultTiming(){
        driver.manage().timeouts().implicitlyWait(TimeoutPageLoad, TimeUnit.SECONDS);
        return this;
    }

    protected PageObject setCustomTiming(int sec){
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
        return this;
    }


    public PageObject setPageIdentificationElement(MobileElement element){
        pageIdentificationElement =  element;
        return this;
    }


    @Override
    protected void load() {
        waitToLoad(pageIdentificationElement);
    }

    @Override
    protected void isLoaded() throws Error {
            Assert.assertNotNull(pageIdentificationElement, "Page Identification Element is not set");
            WebDriverWait wait = new WebDriverWait(driver, TimeoutFastLoook);
            wait.until(ExpectedConditions.visibilityOf(pageIdentificationElement));
    }
}
