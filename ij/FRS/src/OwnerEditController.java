import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class OwnerEditController implements Initializable
{
    Owner owner;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML TextField namebox = new TextField(), nidbox, phonebox, emailbox;
    @FXML PasswordField passbox, _passbox, oldpassbox;
    @FXML ComboBox<String> blgbox = new ComboBox<>(), genderbox = new ComboBox<>();

    void init(Owner p)
    {
        owner = p;
        if(owner == null) System.exit(0);

        namebox.setText(owner.getName());
        nidbox.setText(String.valueOf(owner.getNID()));
        phonebox.setText(String.valueOf(owner.getPhone()));
        emailbox.setText(owner.getMail());
        blgbox.getSelectionModel().select(owner.getBloodGroup());
    }

    @FXML void back(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/View.fxml"));
        root = loader.load();
        OwnerController controller = loader.getController();
        controller.init(owner);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void editpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        namebox.setText(owner.getName());
        /*nidbox.setText(String.valueOf(owner.getNID()));
        phonebox.setText(String.valueOf(owner.getPhone()));
        emailbox.setText(owner.getMail());*/

        System.out.println("Name: " + namebox.getText());
        System.out.println("NID Number: " + owner.getNID());
        System.out.println("Phone Number: " + "+880" + owner.getPhone());
        System.out.println("E-mail Address: " + owner.getMail());

        stage.setScene(scene);
        stage.show();
    }

    public void deletepage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Delete.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void edit(ActionEvent event) throws Exception
    {
        String pass = passbox.getText();
        if(!owner.matchPassword(pass))
        {
            Global.notify("WRONG PASSWORD!");
            return;
        }

        String _name = owner.getName(), _email = owner.getMail(), _bloodgroup = owner.getBloodGroup();
        long _nid = owner.getNID(), _phone = owner.getPhone();

        if(!owner.updateName(namebox.getText()) || !owner.updateNID(Long.parseLong(nidbox.getText())) || !owner.updateEmail(emailbox.getText()) || !owner.updatePhone(Long.parseLong(phonebox.getText())) || !owner.updateBloodGroup(blgbox.getValue()))
        {
            owner.updateName(_name);
            owner.updateNID(_nid);
            owner.updateEmail(_email);
            owner.updatePhone(_phone);
            owner.updateBloodGroup(_bloodgroup);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/View.fxml"));
        root = loader.load();
        OwnerController controller = loader.getController();
        controller.init(owner);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void resetpass(ActionEvent event) throws Exception
    {
        if(owner == null) System.out.println("NULL!");
        else
        {
            String pass = oldpassbox.getText(), _pass;
            if(!owner.matchPassword(pass)) return;

            pass = passbox.getText();
            _pass = _passbox.getText();

            owner.setPassword(pass, _pass);
        }

        // back(event);
    }

    public void delete(ActionEvent event) throws Exception
    {
        if(owner == null) System.out.println("NULL!");
        else
        {
            String pass = passbox.getText();
            if(!owner.matchPassword(pass)) return;

            owner.delete();
        }

        logout(event);
    }

    @FXML void logout(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/Home.fxml"));
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