package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController implements Initializable{
	
	@FXML private TextField email;
	@FXML private PasswordField password1;
	@FXML private PasswordField password2;
	@FXML private TextField nickname;
	@FXML private Button confirmBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
		confirmBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(email.getText().equals("") || password1.getText().equals("") || password2.getText().equals("") || nickname.getText().equals("")){
					System.out.println("�ϳ� �����̴ϱ� ����� Ȯ���ض� ");
				}else{
					if(password1.getText().equals(password2.getText())){
						System.out.println("���߾�");
						
						Stage stage = (Stage) confirmBtn.getScene().getWindow();
					    stage.close();
					}else{
						
						System.out.println("��û�� ��й�ȣ Ȯ���ض�");
					}
				}
			}
			
		});
	}

}
