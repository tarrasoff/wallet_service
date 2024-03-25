package itrum.testexercisewallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
public class TestExerciseWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestExerciseWalletApplication.class, args);
    }
}