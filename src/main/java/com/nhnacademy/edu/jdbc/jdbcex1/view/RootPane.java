package com.nhnacademy.edu.jdbc.jdbcex1.view;

import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.dlsc.formsfx.view.util.ViewMixin;
import com.nhnacademy.edu.jdbc.jdbcex1.model.Student;
import com.nhnacademy.edu.jdbc.jdbcex1.model.StudentModel;
import com.nhnacademy.edu.jdbc.jdbcex1.repository.Students;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class RootPane extends BorderPane implements ViewMixin {
    private GridPane controls;
    private ScrollPane scrollContent;
    private ScrollPane listScrollContent;
    private HBox formButtons;

    private Button save;
    private Button reset;

    private Label changedLabel;

    private StudentModel model;
    private FormRenderer displayForm;

    private TableView<Student> studentTableView;

    public RootPane(StudentModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeSelf() {
        getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        getStyleClass().add("root-pane");
    }

    @Override
    public void initializeParts() {
        save = new Button("Save");
        reset = new Button("Reset");
        save.getStyleClass().add("save-button");
        reset.getStyleClass().add("reset-button");

        changedLabel = new Label("This form is not changed.");

        formButtons = new HBox();

        controls = new GridPane();
        scrollContent = new ScrollPane();
        listScrollContent = new ScrollPane();

        displayForm = new FormRenderer(model.getFormInstance());

        studentTableView = new TableView<>();
        TableColumn<Student, Integer> seqCol = new TableColumn<>("학번");
        TableColumn<Student, String> nameCol = new TableColumn<>("이름");
        TableColumn<Student, Integer> scoreCol = new TableColumn<>("점수");
        seqCol.prefWidthProperty().bind(studentTableView.widthProperty().multiply(0.2));
        nameCol.prefWidthProperty().bind(studentTableView.widthProperty().multiply(0.5));
        scoreCol.prefWidthProperty().bind(studentTableView.widthProperty().multiply(0.3));

        seqCol.setCellValueFactory( new PropertyValueFactory<>("seq"));
        nameCol.setCellValueFactory( new PropertyValueFactory<>("name"));
        scoreCol.setCellValueFactory( new PropertyValueFactory<>("score"));

        studentTableView.getColumns().addAll(seqCol, nameCol, scoreCol);
        studentTableView.setItems(Students.getInstance().getStudents());
    }

    @Override
    public void setupBindings() {
        save.disableProperty().bind(model.getFormInstance().persistableProperty().not());
        reset.disableProperty().bind(model.getFormInstance().changedProperty().not());
        displayForm.prefWidthProperty().bind(scrollContent.prefWidthProperty());
    }


    @Override
    public void setupValueChangedListeners() {
        model.getFormInstance().changedProperty()
                .addListener((observable, oldValue, newValue)
                        -> changedLabel.setText("The from has " + (newValue ? "" : "not ") + "changed."));


    }

    @Override
    public void layoutParts() {
        scrollContent.setContent(displayForm);
        scrollContent.setFitToWidth(true);
        scrollContent.getStyleClass().add("scroll-pane");

        listScrollContent.setContent(studentTableView);
        listScrollContent.setFitToHeight(true);
        listScrollContent.setFitToWidth(true);
        listScrollContent.getStyleClass().add("scroll-pane");


        save.setMaxWidth(Double.MAX_VALUE);
        reset.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(save, Priority.ALWAYS);
        HBox.setHgrow(reset, Priority.ALWAYS);
        formButtons.getChildren().addAll(save, reset);
        formButtons.setPadding(new Insets(10));
        formButtons.setSpacing(10);
        formButtons.setPrefWidth(200);
        formButtons.getStyleClass().add("bordered");
        controls.add(formButtons, 0, 1);

        setCenter(scrollContent);
        setRight(controls);
        setBottom(listScrollContent);
    }

    @Override
    public void setupEventHandlers() {
        reset.setOnAction(event -> model.getFormInstance().reset());
        save.setOnAction(event -> model.getFormInstance().persist());
    }
}
