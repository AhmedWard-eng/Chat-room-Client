package chatroomclient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class FXMLDocumentBase extends BorderPane {

    protected final TextArea chatText;
    protected final BorderPane borderPane;
    protected final TextField textEnter;
    protected final Button buttonSend;
    Client client;

    public FXMLDocumentBase() {
        client = new Client();
        chatText = new TextArea();
        borderPane = new BorderPane();
        textEnter = new TextField();
        buttonSend = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(chatText, javafx.geometry.Pos.CENTER);
        chatText.setEditable(false);
        chatText.setPrefHeight(200.0);
        chatText.setPrefWidth(200.0);
        setCenter(chatText);
        client.readMessage(chatText);

        BorderPane.setAlignment(borderPane, javafx.geometry.Pos.CENTER);
        BorderPane.setAlignment(textEnter, javafx.geometry.Pos.CENTER);
        textEnter.setPadding(new Insets(5.0));
        textEnter.setOpaqueInsets(new Insets(0.0));
        borderPane.setCenter(textEnter);
        
        
        
        buttonSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!textEnter.getText().isEmpty()) {
                    client.sendMessage(textEnter.getText());
                }
            }
        });
        
        
        BorderPane.setAlignment(buttonSend, javafx.geometry.Pos.CENTER);
        buttonSend.setMnemonicParsing(false);
        buttonSend.setText("SEND");
        buttonSend.setPadding(new Insets(5.0));
        borderPane.setRight(buttonSend);
        setBottom(borderPane);

    }
}
