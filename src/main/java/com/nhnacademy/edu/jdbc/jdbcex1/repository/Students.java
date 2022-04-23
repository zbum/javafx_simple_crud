package com.nhnacademy.edu.jdbc.jdbcex1.repository;

import com.nhnacademy.edu.jdbc.jdbcex1.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class Students {
    private static final Students INSTANCE = new Students();

    private final ObservableList<Student> students = FXCollections.observableArrayList();

    public static Students getInstance() {
        return INSTANCE;
    }

    private Students() {
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public boolean contains(int seq) {
        return students.stream().map(it -> seq == it.getSeq()).findFirst().isPresent();
    }

    public Optional<Student> findBySeq(int seq) {
        return students.stream().filter(it -> seq == it.getSeq()).findFirst();
    }


    public void addStudent(Student student){
        this.students.add(student);
    }
}
