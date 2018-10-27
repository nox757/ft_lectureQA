package fintechqa.web1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.io.File;

public enum BrowsersFactory {
    chrome {
        public WebDriver create() {
            updateProperty("chrome");
            setPathDriver("chrome");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            return new ChromeDriver(options);
        }
    },
    firefox {
        public WebDriver create() {
            updateProperty("firefox");
            setPathDriver("gecko");

            //Disable login to console and redirect log to an external file
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            File pathToLog = new File("./src/test/java/firefox_logs");
            if (pathToLog.exists() || pathToLog.mkdirs()) {
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "./src/test/java/firefox_logs/log.txt");
            }
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            return new FirefoxDriver(options);
        }
    },
    opera {
        public WebDriver create() {
            updateProperty("opera");
            setPathDriver("opera");

            OperaOptions options = new OperaOptions();
            String pathBrowser = System.getProperty("pathBrowser");
            if (pathBrowser != null) {
                options.setBinary(pathBrowser);
            }
            options.addArguments("--disable-notifications");
            return new OperaDriver(options);
        }
    };

    public WebDriver create() {
        return null;
    }

    void setPathDriver(String nameDriver) {
        String pathDriver = System.getProperty("pathDriver");
        if (pathDriver != null) {
            System.setProperty(String.format("webdriver.%s.driver", nameDriver), pathDriver);
        }
    }

    void updateProperty(String browserName) {
        System.out.println(String.format("\nstarting %s-browser......", browserName));
        if (System.getProperty("browser") == null) System.setProperty("browser", browserName);
    }
}
