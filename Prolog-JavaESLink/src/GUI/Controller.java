package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    Button button1 = new Button();
    @FXML
    TextArea textDisplay = new TextArea();
    @FXML
    TextField textInput = new TextField();
    @FXML
    static VBox vBox = new VBox();

    String displayText = new String();

    @FXML
    public void UpdateText(){

        this.displayText+= this.textInput.getText() + "\n";

        this.textDisplay.setText(this.displayText);

        this.textInput.clear();     

    }

}
