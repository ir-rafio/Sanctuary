import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends QRcodeExtractor implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML TextField userbox, namebox, nidbox, phonebox, emailbox;
    @FXML PasswordField passbox, _passbox;
    @FXML ComboBox<String> blgbox = new ComboBox<>(), genderbox = new ComboBox<>();
    @FXML public Button clickme;

    @FXML void studenthome(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Home.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerhome(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Home.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void studentloginpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Login.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerloginpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Login.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void studentregpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerregpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void studentlogin(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        long user = Long.parseLong(userbox.getText());
        String pass = passbox.getText();

        try
        {
            Student p = Student.login(user, pass);
            Global.id = user;
            Global.pass = pass;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/View.fxml"));
            root = loader.load();
            StudentController controller = loader.getController();
            controller.init(p);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML void ownerlogin(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String user = userbox.getText();
        String pass = passbox.getText();

        try
        {
            Owner p = Owner.login(user, pass);
            Global.user = user;
            Global.pass = pass;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/View.fxml"));
            root = loader.load();
            OwnerController controller = loader.getController();
            controller.init(p);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML void studentreg(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        String gender = genderbox.getValue();
        if(gender != "Male" && gender != "Female") gender = "Male";
        long user = Long.parseLong(userbox.getText());
        String pass = passbox.getText();
        String _pass = _passbox.getText();
        long nid = Long.parseLong(nidbox.getText());
        long phone = Long.parseLong(phonebox.getText());
        String email = emailbox.getText();
        String bloodgroup = blgbox.getValue();
        Student p;

        try
        {
            p = new Student(user, name, pass, _pass, gender, nid, phone, email, bloodgroup);
            Global.id = user;
            Global.pass = pass;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/View.fxml"));
            root = loader.load();

            StudentController controller = loader.getController();
            controller.init(p);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML void ownerreg(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        String gender = genderbox.getValue();
        if(gender != "Male" && gender != "Female") gender = "Male";
        String user = userbox.getText();
        String pass = passbox.getText();
        String _pass = _passbox.getText();
        long nid = Long.parseLong(nidbox.getText());
        long phone = Long.parseLong(phonebox.getText());
        String email = emailbox.getText();
        String bloodgroup = blgbox.getValue();
        Owner p;

        try
        {
            p = new Owner(user, name, pass, _pass, nid, phone, email);
            Global.user = user;
            Global.pass = pass;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/View.fxml"));
            root = loader.load();

            OwnerController controller = loader.getController();
            controller.init(p);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML void exit(ActionEvent event)
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void clickMeAction(ActionEvent ev) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        fileChooser.setInitialDirectory(new File("c:\\"));
                File file = fileChooser.showOpenDialog(stage);
        try {
            userbox.setText(decodeQRCode(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        genderbox.getItems().addAll(User.genderlist);
        genderbox.getSelectionModel().selectFirst();
        blgbox.getItems().addAll(User.bloodglist);
        blgbox.getSelectionModel().selectFirst();
    }
}