package com.ddleon.gestiondocentes.Repositories;

import com.ddleon.gestiondocentes.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {



}
