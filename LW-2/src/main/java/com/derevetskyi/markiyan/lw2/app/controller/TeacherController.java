package com.derevetskyi.markiyan.lw2.app.controller;

import com.derevetskyi.markiyan.lw2.app.dto.TeacherDto;
import com.derevetskyi.markiyan.lw2.app.dto.TeacherSmallDto;
import com.derevetskyi.markiyan.lw2.app.model.Teacher;
import com.derevetskyi.markiyan.lw2.app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
@CrossOrigin
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherSmallDto> findAll() {
        List<Teacher> teachers = teacherService.findAll();

        return teachers.stream().map(TeacherSmallDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto findById(@PathVariable Long id) {
        Teacher teacher = teacherService.findById(id);

        return new TeacherDto(teacher);
    }

    @GetMapping("/notCurator")
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherSmallDto> findAllNotCurators() {
        List<Teacher> teachers = teacherService.findAllNotCurators();

        return teachers.stream().map(TeacherSmallDto::new).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher saveTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }
}
