package view;

import controller.LoginController;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class LoginWindow extends Stage {
    private Button btIngresar;
    private TextField name;
    private VBox vb;
    private Scene scene;
    private Text title;
    private Text subTittle;
    private Text notAccess;

    private LoginController controller;

    public LoginWindow(){
        init();
    }

    public void init(){
        vb =  new VBox ( );
        title = new Text ( "PRChat" );
        subTittle = new Text ( "Escoge tu nombre de ussuario" );
        notAccess = new Text();
        name = new TextField ( "nombre de usuario" );
        btIngresar = new Button ( "ingresar" );

        vb.getChildren ().addAll ( title, subTittle, name, notAccess, btIngresar );
        scene = new Scene (vb);
        this.setScene ( scene );
        controller = new LoginController ( this);
    }

    public Button getBtIngresar ( ) {
        return btIngresar;
    }

    public TextField getName ( ) {
        return name;
    }

    public Text getNotAccess ( ) {
        return notAccess;
    }
}
