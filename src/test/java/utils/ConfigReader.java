package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    Properties properties;

    public ConfigReader(){
        try{
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getUrl()
    {
       return properties.getProperty("url");
    }
    public String getBrowser()
    {
        return properties.getProperty("browser");
    }
    public String getUsername()
    {
        return properties.getProperty("username");
    }
    public String getPassword()
    {
        return properties.getProperty("password");
    }
    public String getProductName()
    {
        return properties.getProperty("productname");
    }
    public String getAlertURL()
    {
        return properties.getProperty("alertUrl");
    }
    public String getIframeURL()
    {
        return properties.getProperty("IframeUrl");
    }
    public String getWindowURL()
    {
        return properties.getProperty("WindowsUrl");
    }
}
