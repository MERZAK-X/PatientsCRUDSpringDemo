package ma.emsi.patients;

import ma.emsi.patients.entities.Patient;
import ma.emsi.patients.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class PatientsApplication implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(PatientsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        patientRepository.save(new Patient(null, "PatientName1", new Date(), true));
        patientRepository.save(new Patient(null, "PatientName2", new Date(), false));
        patientRepository.save(new Patient(null, "PatientName3", new Date(), false));
        patientRepository.save(new Patient(null, "PatientName4", new Date(), true));
        patientRepository.save(new Patient(null, "PatientName5", new Date(), true));
        patientRepository.save(new Patient(null, "PatientName6", new Date(), true));
        patientRepository.save(new Patient(null, "PatientName7", new Date(), false));

        patientRepository.findAll()
                .forEach(patient -> System.out.println(patient.toString()));
    }
}
