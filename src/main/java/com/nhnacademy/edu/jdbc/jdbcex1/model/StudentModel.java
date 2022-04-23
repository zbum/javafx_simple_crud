package com.nhnacademy.edu.jdbc.jdbcex1.model;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.model.validators.IntegerRangeValidator;
import com.dlsc.formsfx.model.validators.StringLengthValidator;
import com.nhnacademy.edu.jdbc.jdbcex1.repository.Students;

import static com.dlsc.formsfx.model.event.FormEvent.EVENT_FORM_PERSISTED;

public class StudentModel {

    private final Students students = Students.getInstance();
    private final Student student = new Student();
    private Form formInstance;

    public Form getFormInstance() {
        if (this.formInstance == null) {
            createForm();
        }
        return this.formInstance;
    }

    private void createForm() {
        this.formInstance = Form.of(
                        Group.of(
                                Field.ofIntegerType(student.seqProperty())
                                        .label("번호")
                                        .placeholder("학생정보")
                                        .required("번호를 입력해 주세요.")
                                        .validate(IntegerRangeValidator.between(1, 30, "1 ~ 30")),
                                Field.ofStringType(student.nameProperty())
                                        .label("이름")
                                        .placeholder("학생정보")
                                        .required("학생이름을 입력하세요.")
                                        .validate(StringLengthValidator.atLeast(2, "두 글자 이상입력해 주세요.")),
                                Field.ofIntegerType(student.scoreProperty())
                                        .label("점수")
                                        .placeholder("학생정보")
                                        .required("점수를 입력하세요.")
                                        .validate(IntegerRangeValidator.between(0, 100, "0 ~ 100"))

                        )
                ).title("학생관리 시스템")
                .addEventHandler(EVENT_FORM_PERSISTED, event -> {

                    students.findBySeq(student.getSeq())
                            .ifPresentOrElse(
                                    it -> it.update(student),
                                    () -> students.addStudent(student.deepClone())
                            );
                    student.clear();
                });
    }

    public void updateStudent(Student student) {
        this.student.clear();
        this.student.update(student);
    }

}
