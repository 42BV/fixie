package nl._42.fixie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = Application.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
