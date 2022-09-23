import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Stack;

public class StudentController implements Initializable
{
    Student student;

    private Stage stage;
    private Scene scene;
    private Parent root;

    Stack<String> stack = new Stack<>();

    @FXML private Label nameLabel, batchLabel, deptLabel, sectionLabel, blgLabel;
    @FXML private Button flatbutton = new Button();
    @FXML private ComboBox<String> blgbox = new ComboBox<>(), genderbox = new ComboBox<>();
    @FXML private ImageView blgicon;
    @FXML MenuItem editbutton = new MenuItem(), passbutton = new MenuItem(), deletebutton = new MenuItem(), couponbutton = new MenuItem();

    void init(Student p) throws Exception
    {
        student = p;
        if(student == null) System.exit(0);

        nameLabel.setText(nameLabel.getText()+p.getName());
        batchLabel.setText(batchLabel.getText()+p.getBatch());
        deptLabel.setText(deptLabel.getText()+p.getDept());
        sectionLabel.setText(sectionLabel.getText()+p.getSection());

        setBloodGroup();
        setFlat();
        stack.push("view");
    }

    private void setBloodGroup() throws Exception
    {
        for(int i=1; i<User.bloodglist.length; i++)
        {
            if (student.getBloodGroup().equals(User.bloodglist[i]))
            {
                blgLabel.setText(User.bloodglist[i]);
                return;
            }
        }

        blgicon.setVisible(false);
        blgLabel.setVisible(false);
    }

    private void setFlat() throws Exception
    {
        if(student.getFlat() == null) flatbutton.setVisible(false);
    }

    @FXML void view(ActionEvent event) throws Exception
    {
        stack.push("view");
        if(student == null) System.out.println("NULL!");

        root = FXMLLoader.load(getClass().getResource("fxml/Student/View.fxml"));

        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void discoverpage(ActionEvent event) throws Exception
    {
        /*root = FXMLLoader.load(getClass().getResource("fxml/Student/Discover.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();*/

        int id = 1000000;

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/Display.fxml"));
        root = loader.load();

        FlatDisplayController controller = loader.getController();
        controller.init(Flat.open(id));

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void myflatpage(ActionEvent event) throws Exception
    {
        
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
        blgbox.getItems().addAll(User.bloodglist);
        flatbutton.setVisible(false);

        editbutton.setOnAction(new EventHandler<ActionEvent>()
                               {
                                   @Override public void handle(ActionEvent event)
                                   {
                                       try
                                       {
                                           stage = (Stage) editbutton.getParentPopup().getOwnerWindow();
                                           FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/Edit.fxml"));
                                           root = loader.load();

                                           StudentFormController controller = loader.getController();
                                           controller.init(student);

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
                               }
        );

        passbutton.setOnAction(new EventHandler<ActionEvent>()
                               {
                                   @Override public void handle(ActionEvent event)
                                   {
                                       try
                                       {
                                           stage = (Stage) passbutton.getParentPopup().getOwnerWindow();
                                           FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/Password.fxml"));
                                           root = loader.load();

                                           StudentFormController controller = loader.getController();
                                           controller.init(student);

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
                               }
        );

        deletebutton.setOnAction(new EventHandler<ActionEvent>()
                                 {
                                     @Override public void handle(ActionEvent event)
                                     {
                                         try
                                         {
                                             stage = (Stage) deletebutton.getParentPopup().getOwnerWindow();
                                             FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/Delete.fxml"));
                                             root = loader.load();

                                             StudentFormController controller = loader.getController();
                                             controller.init(student);

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
                                 }
        );

        couponbutton.setOnAction(new EventHandler<ActionEvent>()
                                 {
                                     @Override public void handle(ActionEvent event)
                                     {
                                         try
                                         {
                                             String reqtime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS"));
                                             Database database = new Database("sanctuary", "root", "");
                                             String[] columns = {"RequestTime", "ID"};
                                             Object[] params = {reqtime, student.id};
                                             database.insert("coupon", columns, params);

                                             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                             alert.setContentText("Your coupon request is stored in our database and will be sent to IUT Cafeteria.");
                                             alert.show();
                                         }
                                         catch (Exception e)
                                         {
                                             e.printStackTrace();

                                         }
                                     }
                                 }
        );



    }
}