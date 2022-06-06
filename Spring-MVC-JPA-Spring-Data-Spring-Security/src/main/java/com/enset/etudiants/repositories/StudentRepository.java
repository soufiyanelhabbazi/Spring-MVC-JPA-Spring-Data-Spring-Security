package com.enset.etudiants.repositories;

import com.enset.etudiants.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    void deleteById(Long Id);
    Optional<Student> findById(Long id);
    Page<Student> findByNomContaining(String kw, Pageable pageable);
    List<Student> findByNomContaining(String kw);
    @Query(value = " select * from Student s where s.nom like %:keyword% or s.prenom like %:keyword%",countQuery = "SELECT count(*) FROM Student",nativeQuery = true)
    Page<Student> findByKeyword(@Param("keyword") String keyword , Pageable pageable);
}
