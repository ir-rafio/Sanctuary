import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class FlatDisplayController implements Initializable
{
    Flat flat;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML Label nameLabel = new Label(), levelLabel = new Label(), roomcountLabel = new Label();
    @FXML CheckBox liftbox = new CheckBox(), generatorbox = new CheckBox();
    @FXML MenuButton roomlist;
    @FXML Button reservationbutton = new Button();
    @FXML MenuItem editbutton = new MenuItem(), deletebutton = new MenuItem(), addroombutton = new MenuItem();
    @FXML Hyperlink locationbutton = new Hyperlink();

    void init(Flat f) throws Exception
    {
        flat = f;

        nameLabel.setText(flat.getName());
        levelLabel.setText("Level " + flat.getLevel());
        roomcountLabel.setText("Room Count" + flat.rooms.size());
        liftbox.setSelected(flat.getLift());
        generatorbox.setSelected(flat.getGenerator());

        int rent = flat.getRent();

        for(Room r: flat.rooms) {
            MenuItem item = new MenuItem(r.name);
            /*item.setOnAction(new EventHandler<ActionEvent>()
                             {
                                 @Override public void handle(ActionEvent event)
                                 {
                                     try
                                     {
                                         stage = (Stage) item.getParentPopup().getOwnerWindow();
                                         Flat f = Flat.open(.id);

                                         FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Room/View.fxml"));
                                         root = loader.load();
                                         FlatController controller = loader.getController();
                                         controller.init(f);

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
            );*/
            roomlist.getItems().add(item);
        }
    }

    @FXML void back(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try
        {
            Student p = Student.login(Global.id, Global.pass);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/View.fxml"));
            root = loader.load();
            StudentController controller = loader.getController();
            controller.init(p);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/Home.fxml"));
            root = loader.load();

            scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void next(ActionEvent event) throws Exception
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {


        editbutton.setOnAction(new EventHandler<ActionEvent>()
                               {
                                   @Override public void handle(ActionEvent event)
                                   {
                                       try
                                       {
                                           stage = (Stage) editbutton.getParentPopup().getOwnerWindow();
                                           FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/Edit.fxml"));
                                           root = loader.load();

                                           FlatFormController controller = loader.getController();
                                           controller.init(flat);

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
                                             FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/Delete.fxml"));
                                             root = loader.load();

                                             FlatFormController controller = loader.getController();
                                             controller.init(flat);

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

        addroombutton.setOnAction(new EventHandler<ActionEvent>()
                                  {
                                      @Override public void handle(ActionEvent event)
                                      {
                                          try
                                          {
                                              stage = (Stage) addroombutton.getParentPopup().getOwnerWindow();
                                              FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
                                              root = loader.load();

                                              FlatFormController controller = loader.getController();
                                              controller.init(flat);

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

        locationbutton.setOnAction(new EventHandler<ActionEvent>()
                                   {
                                       @Override public void handle(ActionEvent event)
                                       {
                                           try
                                           {
                                               Desktop.getDesktop().browse(new URI(flat.getLocation()));
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

        reservationbutton.setOnAction(new EventHandler<ActionEvent>()
                                   {
                                       @Override public void handle(ActionEvent event)
                                       {
                                           try
                                           {
                                               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                               alert.setContentText("Your request has been sent to the Owner. Keep an eye for an e-mail regarding your appointment.");
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