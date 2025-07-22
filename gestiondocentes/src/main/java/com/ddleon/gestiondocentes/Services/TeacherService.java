package com.ddleon.gestiondocentes.Services;


import com.ddleon.gestiondocentes.Models.Teacher;
import com.ddleon.gestiondocentes.Repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    // CRUD
    public List<Teacher> listAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Optional<Teacher> update(Long id, Teacher newTeacher) {
        return teacherRepository.findById(id)
                .map(teacher -> {
                    teacher.setName(newTeacher.getName());
                    teacher.setAddress(newTeacher.getAddress());
                    teacher.setcity(newTeacher.getcity());
                    teacher.setEmail(newTeacher.getEmail());
                    teacher.setBirthday(newTeacher.getBirthday());
                    teacher.setServiceTime(newTeacher.getServiceTime());
                    return teacherRepository.save(teacher);
                });
    }

    public boolean delete(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar por city
    public List<Teacher> findBycity(String city) {
        return teacherRepository.findBycity(city);
    }

    // Buscar por experiencia mínima
    public List<Teacher> findByServiceTimeGreaterThanEqual(int years) {
        return teacherRepository.findByServiceTimeGreaterThanEqual(years);
    }

    // Edad promedio
    public double calculateAverageAge() {
        List<Teacher> teachers = teacherRepository.findAll();
        if (teachers.isEmpty()) return 0.0;

        int agesum = 0;
        for (Teacher d : teachers) {
            if (d.getBirthday() != null) {
                agesum += Period.between(d.getBirthday(), LocalDate.now()).getYears();
            }
        }
        return (double) agesum / teachers.size();
    }

    // Paginación
    public Page<Teacher> listPaged(@NonNull Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

}