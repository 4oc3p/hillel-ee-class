package hillelee;

import hillelee.pet.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class HilleleeConfig {

    @Bean
    PetService petService(JpaPetRepository petRepository) {
        return new PetService(petRepository);
    }

    @Bean
    CommandLineRunner initDb(JpaPetRepository repository) {
        return args -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }

            List<Prescription> tomsPrescription = new ArrayList<>();
            tomsPrescription.add(new Prescription("paracetamol", LocalDate.now(), 4));
            tomsPrescription.add(new Prescription("analg", LocalDate.now(), 3));
            List<Prescription> jerrysPrescription = new ArrayList<>();
            jerrysPrescription.add(new Prescription("alaka", LocalDate.now(), 2));
            jerrysPrescription.add(new Prescription("poqqw", LocalDate.now(), 4));

            MedicalCard tomsCard = new MedicalCard(LocalDate.now(), "Bla-bla");
            MedicalCard jerrysCard = new MedicalCard(LocalDate.now(), "Foo-bar");

            repository.save(new Pet("Tom", "Cat", 3, LocalDate.now(), tomsCard, tomsPrescription));
            repository.save(new Pet("Jerry", "Mouse", 1, LocalDate.now(), jerrysCard, jerrysPrescription));
        };
    }
}
