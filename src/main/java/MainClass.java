import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import java.io.*;

public class MainClass {
    public static void main(String[] args) {
        // две пустые строки (логин - myLogin и пароль - myPassword) будут принимать значения, считывая их из текстового файла
        String myLogin = null, myPassword = null;
        try {
            File file = new File("C:\\Users\\Anatoly\\Desktop\\WebData.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                myLogin = line;
                line = reader.readLine();
                myPassword = line;
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//////////////////////////////////////////////////////  Событие - Sign In   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        // подключаем web-driver
        System.setProperty("webdriver.operaDriver", "C:\\Users\\Anatoly\\Desktop\\SignUp\\drivers\\operadriver.exe");
        WebDriver driver = new OperaDriver();

        driver.manage().window().maximize();        // задаём параметры открывающегося окна браузера (full screen)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.get("https://apps.bpc.ru");          // открываем сайт в окне браузера
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  передаём значения ранее считанных строк в поля для логина и пароля
        driver.findElement(By.xpath("//*[@id=\"user-id\"]")).sendKeys(myLogin);
        driver.findElement(By.xpath("//*[@id=\"pw-id\"]")).sendKeys(myPassword);

        // находим button Login и кликаем по нему
        WebElement login = driver.findElement(By.xpath("/html/body/form/div[3]/div/div/div/input"));
        login.click();

        // то же самое делаем для button TimeSheet
        WebElement timeSheet = driver.findElement(By.xpath("//*[@id=\"apps\"]/div[1]/div[8]/a/span[1]"));
        timeSheet.click();

        // небольшая задержка перед нажатием на Check In
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /** Поскольку xPath для button "CheckIn" - динамический,
         ** будем имитировать нажатие мыши по указанным координатам */
        Actions checkIn = new Actions(driver);
        checkIn.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
        checkIn.moveByOffset(940, 215).click().build().perform();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /** для button Enter - xPath также динамический */
        Actions quitButton = new Actions(driver);
        quitButton.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
        quitButton.moveByOffset(1765, 165).click().build().perform();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //////////////////////
        /** для button OR - xPath также динамический */
        Actions OK = new Actions(driver);
        OK.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
        OK.moveByOffset(1210, 267).click().build().perform();
        //////////////////////
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // закрываем браузер и ожидаем 5 минут, после чего повторяем всё аналогично
        driver.quit();
        try {
            Thread.sleep(3_000);   // must be 300_000
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//////////////////////////////////////////////////////  Событие - Sign Out   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        WebDriver driver2 = new OperaDriver();
        driver2.manage().window().maximize();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver2.get("https://apps.bpc.ru");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  передаём значения ранее считанных строк в поля для логина и пароля
        driver2.findElement(By.xpath("//*[@id=\"user-id\"]")).sendKeys(myLogin);
        driver2.findElement(By.xpath("//*[@id=\"pw-id\"]")).sendKeys(myPassword);
        WebElement login2 = driver2.findElement(By.xpath("/html/body/form/div[3]/div/div/div/input"));
        login2.click();

        WebElement timeSheet2 = driver2.findElement(By.xpath("//*[@id=\"apps\"]/div[1]/div[8]/a/span[1]"));
        timeSheet2.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Actions checkIn2 = new Actions(driver2);
        checkIn2.moveToElement(driver2.findElement(By.tagName("body")), 0, 0);
        checkIn2.moveByOffset(940, 215).click().build().perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Actions quitButton2 = new Actions(driver2);
        quitButton2.moveToElement(driver2.findElement(By.tagName("body")), 0, 0);
        quitButton2.moveByOffset(1765, 165).click().build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //////////////////////
        Actions OK2 = new Actions(driver);
        OK2.moveToElement(driver2.findElement(By.tagName("body")), 0, 0);
        OK2.moveByOffset(1210, 267).click().build().perform();
        //////////////////////
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver2.quit();
    }
}


