package zw.co.els.englishlearningsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import zw.co.els.englishlearningsystem.model.User;
import zw.co.els.englishlearningsystem.service.OpenAIClient;
import zw.co.els.englishlearningsystem.service.ResponseService;

import java.io.IOException;
import java.net.URISyntaxException;

public class HomeController {
    @FXML
    private Label fullNameLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private ListView<String> favouritesList;
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField questionField;

    private ObservableList<String> favouriteQuestions;

    public void setUser(User user) {
        fullNameLabel.setText(user.fullName());
        System.out.println(user.fullName());
        phoneNumberLabel.setText(user.phoneNumber());
    }

    @FXML
    void initialize() {
        favouriteQuestions = FXCollections.observableArrayList();
        favouritesList.setItems(favouriteQuestions);
        questionField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    handleSendQuestion();
                } catch (IOException | URISyntaxException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void handleSendQuestion() throws IOException, URISyntaxException, InterruptedException {
        String question = questionField.getText();
        if (!question.isEmpty()) {

            chatArea.appendText("You: " + question + "\n");
            questionField.clear();

            favouriteQuestions.add(question);

            String jsonResponse = OpenAIClient.getAIResponse(question);
            var response = ResponseService.extractText(jsonResponse);
            chatArea.appendText("AI: " + response + "\n");
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        LogoutController.handleLogout(event);
    }

}
