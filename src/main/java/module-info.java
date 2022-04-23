module com.nhnacademy.edu.jdbc.jdbcex1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;


    opens com.nhnacademy.edu.jdbc.jdbcex1 to javafx.fxml;

    exports com.nhnacademy.edu.jdbc.jdbcex1;
    exports com.nhnacademy.edu.jdbc.jdbcex1.model;
    exports com.nhnacademy.edu.jdbc.jdbcex1.repository;
}