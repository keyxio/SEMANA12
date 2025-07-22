package com.ddleon.gestiondocentes.Controllers;

import com.ddleon.gestiondocentes.Models.Student;
import com.ddleon.gestiondocentes.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
// @RequestMapping("/")
@SuppressWarnings("unused")

public class StudentController {

    @Autowired
    private StudentRepository repo;

    @PostMapping
    public Student add(@RequestBody Student student) {
        return repo.save(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> mod(@PathVariable Long id, @RequestBody Student date) {
        return repo.findById(id)
                .map(std->{
                    std.setNameStudent(date.getNameStudent());
                    std.setAddressStudent(date.getAddressStudent());
                    std.setcityStudent(date.getcityStudent());
                    return ResponseEntity.ok(repo.save(std));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // @GetMapping({"/", ""})
    @GetMapping
    public List<Student> listAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Student> searchId(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

