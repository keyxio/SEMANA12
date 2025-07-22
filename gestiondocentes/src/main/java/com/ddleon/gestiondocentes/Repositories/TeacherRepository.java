package com.ddleon.gestiondocentes.Repositories;

import com.ddleon.gestiondocentes.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository <Teacher, Long> {

    List<Teacher> findBycity(String city);

    List<Teacher> findByServiceTimeGreaterThanEqual(int years);

    // Page<Teacher> findAll(@NonNull Pageable pageable);
}
