package uz.mygift;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.mygift.repository.AboutUsRepository;


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
