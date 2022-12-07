import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JumiaWebSigninTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() throws InterruptedException {

        //locate where the chromedriver is
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        //open your Chrome browser
        driver = new ChromeDriver();

        //input the Jumia Webpage URL
        driver.get("https://www.jumia.com.ng/");
            //Verify that webpage url is correct
            if (driver.getCurrentUrl().contains("https://www.jumia.com.ng/"))
                //Pass
                System.out.println("Correct Webpage");
            else
                //Fail
                System.out.println("Wrong Webpage");
        Thread.sleep(5000);

        //maximize the browser
        driver.manage().window().maximize();
    }

    @Test (priority = 0)
    public void pageTitle() {
        //T1 - Get the page title
        if (driver.getTitle().contains("Jumia Nigeria | Online Shopping for Groceries, Appliances & More!"))
            //Pass
            System.out.println("Correct page title");
        else
            //Fail
            System.out.println("Wrong page title");
    }

    @Test (priority = 1)
    public void signInPage() throws InterruptedException {
        //click on the Account button
        driver.findElement(By.xpath("//*[@id=\"jm\"]/header/section/div[2]/div[1]/label")).click();
        Thread.sleep(4000);

        //click on the SIGN IN button
        driver.findElement(By.xpath("//*[@id=\"dpdw-login-box\"]/div/div/a")).click();
            //T2 - Verify that user was redirected to the sign-in page
            String expectedUrl = "https://my.jumia.com.ng/interaction/9AIRpyfOMIQQcKHsPfSwp/en-ng/sign-in/email";
            String actualUrl = driver.getCurrentUrl();
            if(actualUrl.contains(expectedUrl))
                //Pass
                System.out.println("Correct Sign In page");
            else
                //Fail
                System.out.println("Wrong Sign In page");
        Thread.sleep(5000);
    }

    @Test (priority = 2)
    public void validEmail() throws InterruptedException {
        //input email address on email field
        driver.findElement(By.xpath("/html/body/div/div[4]/form/div[2]/div[2]/label/input")).sendKeys("kisego5309@eilnews.com");

        //click on the Continue button
        driver.findElement(By.xpath("/html/body/div/div[4]/form/div[2]/div[3]/div/button/span[3]")).click();
            //T3 - Verify that email was inputted successfully
            String expectedUrl = "https://my.jumia.com.ng/interaction/cNuismBVgMytk_q1L4WLC/en-ng/sign-in";
            String actualUrl = driver.getCurrentUrl();
            if (actualUrl.contains(expectedUrl))
                //Pass
                System.out.println("Email address recorded");
            else
                //Fail
                    System.out.println("Email address not recorded");
        Thread.sleep(5000);
    }

    @Test (priority = 3)
    public void validPassword() throws InterruptedException {
        //input your password on password field
        driver.findElement(By.xpath("//*[@id=\"passwordForm\"]/div[2]/div[3]/label/input")).sendKeys("kisego5309");

        //click on the Login button
        driver.findElement(By.xpath("//*[@id=\"loginButton\"]/span[3]")).click();
            //T4 - Verify that user is logged in successfully
            String expectedUrl = "https://www.jumia.com.ng/";
            String actualUrl = driver.getCurrentUrl();
            if(actualUrl.contains(expectedUrl))
                //Pass
                System.out.println("Login Successful");
            else
                //Fail
                System.out.println("Login Unsuccessful");
        Thread.sleep(5000);
    }

    @Test (priority = 4)
    public void logOut() throws InterruptedException {
        //click on the Hi User button
        driver.findElement(By.xpath("//*[@id=\"jm\"]/header/section/div[2]/div[1]/label")).click();
        Thread.sleep(4000);

        //click on the Logout button
        driver.findElement(By.xpath("//*[@id=\"dpdw-login-box\"]/div/form/button")).click();
            //T5 - Verify that user is logged out successfully
            String expectedUrl = "https://www.jumia.com.ng/";
            String actualUrl = driver.getCurrentUrl();
            if(actualUrl.contains(expectedUrl))
                //Pass
                System.out.println("Log out successful");
            else
                //Fail
                System.out.println("Log out successful");
        Thread.sleep(4000);
    }

    @Test (priority = 5)
    public void negativeSignIn() throws InterruptedException {
        //click on the Account button
        driver.findElement(By.xpath("//*[@id=\"jm\"]/header/section/div[2]/div[1]/label")).click();
        Thread.sleep(3000);

        //click on the SIGN IN button
        driver.findElement(By.xpath("//*[@id=\"dpdw-login-box\"]/div/div/a")).click();
        Thread.sleep(3000);

        //input registered email address on email field
        driver.findElement(By.xpath("/html/body/div/div[4]/form/div[2]/div[2]/label/input")).sendKeys("kisego5309@eilnews.com");

        //click on the Continue button
        driver.findElement(By.xpath("/html/body/div/div[4]/form/div[2]/div[3]/div/button/span[3]")).click();
        Thread.sleep(3000);

        //input invalid password on password field
        driver.findElement(By.xpath("//*[@id=\"passwordForm\"]/div[2]/div[3]/label/input")).sendKeys("kisego");

        //click on the Login button
        driver.findElement(By.xpath("//*[@id=\"loginButton\"]/span[3]")).click();
            //T6 - Verify that user is not logged in successfully
            String expectedUrl = "https://my.jumia.com.ng/interaction/_WLJV_M4WSsWankUFg4Ea/en-ng/sign-in";
            String actualUrl = driver.getCurrentUrl();
            if(actualUrl.contains(expectedUrl))
                //Pass
                System.out.println("Login unsuccessful");
            else
                //Fail
                System.out.println("Login successful");
        Thread.sleep(2000);
    }

    @AfterTest
    public void closeBrowser() {
        //quit the browser
        driver.quit();
    }
}

