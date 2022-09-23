import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class StudentFormController implements Initializable
{
    Student student;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML TextField namebox, phonebox = new TextField(), emailbox = new TextField(), passbox, _passbox, oldpassbox, flatidbox;
    @FXML ComboBox<String> blgbox = new ComboBox<>(), genderbox = new ComboBox<>();

    void init(Student p) throws Exception
    {
        student = p;

        phonebox.setText(String.valueOf(student.getPhone()));
        emailbox.setText(student.getMail());
        blgbox.setValue(student.getBloodGroup());
    }

    @FXML void back(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/View.fxml"));
        root = loader.load();

        StudentController controller = loader.getController();
        controller.init(student);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void edit(ActionEvent event) throws Exception
    {
        long phone = Long.parseLong(phonebox.getText());
        String email = emailbox.getText();
        String blg = blgbox.getValue();

        String pass = passbox.getText();
        if(!student.matchPassword(pass)) throw new Exception("WRONG PASSWORD!");
        student.update(phone, email, blg);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/View.fxml"));
        root = loader.load();

        StudentController controller = loader.getController();
        controller.init(student);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void resetpass(ActionEvent event) throws Exception
    {
        if(student == null) System.out.println("NULL!");
        else
        {
            String pass = oldpassbox.getText(), _pass;
            if(!student.matchPassword(pass)) return;

            pass = passbox.getText();
            _pass = _passbox.getText();

            student.changePassword(pass, _pass);
        }

        back(event);
    }

    @FXML void delete(ActionEvent event) throws Exception
    {
        if(student == null) System.out.println("NULL!");
        else
        {
            String pass = passbox.getText();
            if(!student.matchPassword(pass)) return;

            student.delete();
        }

        logout(event);
    }

    @FXML void findflat(ActionEvent event) throws Exception
    {
        int id = Integer.parseInt(flatidbox.getText());

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/Display.fxml"));
        root = loader.load();

        FlatDisplayController controller = loader.getController();
        controller.init(Flat.open(id));

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void logout(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/Home.fxml"));
        root = loader.load();

        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        genderbox.getItems().addAll(User.genderlist);
        genderbox.getSelectionModel().selectFirst();
        blgbox.getItems().addAll(User.bloodglist);
    }
}