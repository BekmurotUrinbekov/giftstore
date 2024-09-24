package uz.giftstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MygiftApplication {
//	private final AboutUsRepository repository;
//
//    public MygiftApplication(AboutUsRepository repository) {
//        this.repository = repository;
//    }
//
//	@Bean
//	CommandLineRunner commandLineRunner(){
//		System.out.println(repository.findAll());
//		return null;
//	}

    public static void main(String[] args) {
		SpringApplication.run(MygiftApplication.class, args);
	}

}
