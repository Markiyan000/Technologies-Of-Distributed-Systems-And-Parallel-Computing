package com.derevetskyi.markiyan.lw2.app.service;

import com.derevetskyi.markiyan.lw2.app.dto.StudentUpdateDto;
import com.derevetskyi.markiyan.lw2.app.model.Group;
import com.derevetskyi.markiyan.lw2.app.model.Student;
import com.derevetskyi.markiyan.lw2.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentService {

    private GroupService groupService;

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(GroupService groupService, StudentRepository studentRepository) {
        this.groupService = groupService;
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllWithoutGroup() {
        return studentRepository.findAllWithoutGroup();
    }

    @Transactional
    public Student saveStudent(Long groupId, Student student) {
        Group group = groupService.findById(groupId);
        group.addStudent(student);

        return student;
    }

    @Transactional
    public void transferToOtherGroup(Long studentId, String groupName) {
        Student student = findById(studentId);
        Group newGroup = groupService.findByName(groupName);

        newGroup.addStudent(student);
    }

    @Transactional
    public Student updateStudent(Long id, StudentUpdateDto student) {
        Student toUpdate = findById(id);

        toUpdate.setFirstName(student.getFirstName());
        toUpdate.setLastName(student.getLastName());
        toUpdate.setAge(student.getAge());

        return toUpdate;
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    private Student findById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student with id:" + id + " not found!"));
    }
}
