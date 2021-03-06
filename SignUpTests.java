import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTests {
    @Test
    public void zipCodeTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed(), "zip code shouldn't be displayed");
        Assert.assertTrue(driver.findElement(By.name("first_name")).isDisplayed(), "First name input should be displayed");
        Thread.sleep(3000);
        driver.quit();

     }
     @Test
    public void zipCodeTestNegative1() throws InterruptedException { //когда мы ничего не вводим в поле 'zip code'
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
         Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(), "zip code should be displayed");
         Thread.sleep(3000);
         driver.quit();
        // Why did the test fail when I added such a check?
         //Assert.assertFalse(driver.findElement(By.name("last_name")).isDisplayed(), "Last name input shouldn't be displayed");
    }
@Test
    public void zipCodeTestNegative2() throws InterruptedException {   //когда мы вводим буквы в поле 'zip code'
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("asdfh");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(), "zip code should be displayed");
        Thread.sleep(3000);
        driver.quit();
    }
@Test
    public void zipCodeTestNegative3() throws InterruptedException { //когда мы вводим буквы и цифры в поле 'zip code'
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("as25g");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.xpath("(//span[text() = 'Oops, error on page. ZIP code should have 5 digits'])")).isDisplayed(), "This message should appear:Oops, error on page. ZIP code should have 5 digits");
        Thread.sleep(3000);
        driver.quit();
    }
@Test
    public void SignUpTestPositive() throws InterruptedException { //регистрация, всё ОК
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
          firstNameInput.sendKeys("Leo");
        WebElement lastNameInput  = driver.findElement(By.name("last_name"));
          lastNameInput.sendKeys("Messi");
        WebElement emailInput  = driver.findElement(By.name("email"));
          emailInput.sendKeys("sergei.G@gmail.com");
        WebElement passwordInput = driver.findElement(By.cssSelector("[name='password1']"));
          passwordInput.sendKeys("88888");
        WebElement cofirmPasswordInput = driver.findElement(By.cssSelector("[name='password2']"));
          cofirmPasswordInput.sendKeys("88888");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
          registerButton.click();
          Assert.assertTrue(driver.findElement(By.cssSelector("[class='confirmation_message']")).isDisplayed() , "This message should appear:Account is created!");
        Thread.sleep(3000);
        driver.quit();

    }
@Test
    public void SignUpTestNegative1() throws InterruptedException { //Не заполнять поля и нажать кнопку‘Зарегистрироваться’.
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys("");
        WebElement lastNameInput  = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys("");
        WebElement emailInput  = driver.findElement(By.name("email"));
        emailInput.sendKeys("");
        WebElement passwordInput = driver.findElement(By.cssSelector("[name='password1']"));
        passwordInput.sendKeys("");
        WebElement cofirmPasswordInput = driver.findElement(By.cssSelector("[name='password2']"));
        cofirmPasswordInput.sendKeys("");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        Assert.assertTrue(driver.findElement(By.xpath("(//span[text()='Oops, error on page. Some of your fields have invalid data or email was previously used'])")).isDisplayed() , "This message should appear:Oops, error on page. Some of your fields have invalid data or email was previously used");
        Thread.sleep(3000);
        driver.quit();

    }
@Test
    public void SignUpTestNegative2() throws InterruptedException{ //вводим в поля 'пароль' и 'подтверждение пароля' РАЗНЫЕ значения
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys("Leo");
        WebElement lastNameInput  = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys("Messi");
        WebElement emailInput  = driver.findElement(By.name("email"));
        emailInput.sendKeys("sergei.G@gmail.com");
        WebElement passwordInput = driver.findElement(By.cssSelector("[name='password1']"));
        passwordInput.sendKeys("88888");
        WebElement cofirmPasswordInput = driver.findElement(By.cssSelector("[name='password2']"));
        cofirmPasswordInput.sendKeys("11111");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        Assert.assertTrue(driver.findElement(By.name("last_name")).isDisplayed(), "Last name input should be displayed");
        Thread.sleep(3000);
        driver.quit();
    }
}
