package dat.lyngby;

import dat.lyngby.config.AppConfig;
import dat.lyngby.config.HibernateConfig;
import dat.lyngby.config.Populate;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        Populate.Populate();
        AppConfig.startServer();
    }
}