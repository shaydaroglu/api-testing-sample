package test.java.booking.api;

import org.testng.annotations.BeforeSuite;
import retrofit2.Retrofit;

import static main.java.booking.utils.ClientInitializer.setupRetrofit;
import static main.java.booking.utils.ConfigReader.loadConfigFile;

public class BaseTest {

    protected Retrofit retrofit;

    @BeforeSuite
    public void setupConfiguration() {
        retrofit = setupRetrofit();
    }

    @BeforeSuite
    public void readConfigFile() {
        loadConfigFile();
    }
}
