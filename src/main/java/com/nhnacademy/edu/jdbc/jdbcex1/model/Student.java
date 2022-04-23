package com.nhnacademy.edu.jdbc.jdbcex1.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student implements Cloneable {
    private IntegerProperty seq = new SimpleIntegerProperty(0);
    private StringProperty name = new SimpleStringProperty("");
    private IntegerProperty score = new SimpleIntegerProperty(0);

    public Student() {
    }

    public int getSeq() {
        return seq.get();
    }

    public IntegerProperty seqProperty() {
        return seq;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }


    protected Student deepClone()  {
        Student cloneP = new Student();

        cloneP.seq = new SimpleIntegerProperty(getSeq());
        cloneP.name = new SimpleStringProperty(getName());
        cloneP.score = new SimpleIntegerProperty(getScore());

        return cloneP;
    }


        @Override
        public String toString () {
            return "Student{" +
                    "seq=" + seq +
                    ", name=" + name +
                    ", score=" + score +
                    '}';
        }

    public void update(String name, int score) {
        this.name.setValue(name);
        this.score.setValue(score);
    }
}
