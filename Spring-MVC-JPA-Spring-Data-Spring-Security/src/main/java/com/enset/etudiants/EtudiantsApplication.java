package com.enset.etudiants;

import com.enset.etudiants.entities.Genre;
import com.enset.etudiants.entities.Student;
import com.enset.etudiants.repositories.StudentRepository;
import com.enset.etudiants.services.IStudentServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class EtudiantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtudiantsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IStudentServices studentServices, StudentRepository studentRepository) {
        return args -> {
            for (int i = 0; i < 3; i++) {
                Stream.of("soufiyan", "amine", "salma", "ikram", "imad", "saad", "soufiyan").forEach(name -> {
                    Student student = new Student();
                    student.setNom(name);
                    student.setPrenom("elhabbazi");
                    student.setGenre(Math.random() > 0.5 ? Genre.MASCULIN : Genre.FEMININ);
                    student.setEmail(name + "@gmail.com");
                    student.setDateNaissance(new Date());
                    student.setEnRegle(Math.random() < 0.5 ? false : true);

                    studentServices.saveStudent(student);
                });
            }
        };
    }
}

    /*@Bean
    CommandLineRunner start(IStudentServices studentServices , StudentRepository studentRepository ){
        return args -> {

            *//*studentServices.saveStudent(new Student(null,"soufiyan","elhabbazi","soufiyan@gmail.com",new Date(),Genre.MASCULIN,true,null));

            Student student = studentServices.findStudent(1L);
            studentServices.saveAbscence(new Abscence(null,new Date(),"test",student));

            System.out.println("Nom : "+student.getNom());*//*
        };
    }
}*/
