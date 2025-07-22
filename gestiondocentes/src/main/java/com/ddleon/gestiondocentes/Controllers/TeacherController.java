package com.ddleon.gestiondocentes.Controllers;

import com.ddleon.gestiondocentes.Models.Teacher;
import com.ddleon.gestiondocentes.Services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Obtener todos los docentes
    @GetMapping("/all")
    public List<Teacher> getAll() {
        return teacherService.listAll();
    }

    // Obtener docente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable Long id) {
        return teacherService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo docente
    @PostMapping
    public ResponseEntity<Teacher> create(@Valid @RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.save(teacher));
    }

    // Actualizar docente
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Long id, @Valid @RequestBody Teacher updated) {
        return teacherService.update(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar docente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (teacherService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Docentes por city
    @GetMapping("/city/{city}")
    public List<Teacher> getBycity(@PathVariable String city) {
        return teacherService.findBycity(city);
    }

    // Docentes por años de experiencia
    @GetMapping("/experience/{years}")
    public List<Teacher> getByExperience(@PathVariable int years) {
        return teacherService.findByServiceTimeGreaterThanEqual(years);
    }

    // Edad promedio
    @GetMapping("/average-age")
    public double getAverageAge() {
        return teacherService.calculateAverageAge();
    }

    // Paginación
    @GetMapping
    public Page<Teacher> getPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return teacherService.listPaged(PageRequest.of(page, size));
    }
}
