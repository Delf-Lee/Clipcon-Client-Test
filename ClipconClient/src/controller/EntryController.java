package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EntryController implements Initializable {
	
	@FXML private Button createBtn;
	@FXML private Button joinBtn;
	@FXML private TextField groupNameTF;
	@FXML private TextField groupKeyTF;
	@FXML private Button backBtn;
	
	public void setGroupName(String groupName) {
		this.groupKeyTF.setText(groupName);
	}
	
	public String getGroupName() {
		return groupNameTF.getText();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		createBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub			
				System.out.println("�׷����");
				System.out.println("�׽�Ʈ");
				
				try {				
					Parent toMain = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
					Scene mainScene = new Scene(toMain);
					Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					
					mainStage.hide();
					mainStage.setScene(mainScene);
					mainStage.show();				
					
					System.out.println("������� �׷���� "+getGroupName()+" �Դϴ�.");
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		joinBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub			
				System.out.println("�׷�����");
				
				try {				
					Parent toMain = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
					Scene mainScene = new Scene(toMain);
					Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					
					mainStage.hide();
					mainStage.setScene(mainScene);
					mainStage.show();					
					
					System.out.println("������ �׷�Ű�� "+groupKeyTF.getText()+" �Դϴ�.");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		backBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub			
				System.out.println("�ʱ�ȭ������");
				
				try {				
					Parent toMain = FXMLLoader.load(getClass().getResource("/view/StartingView.fxml"));
					Scene mainScene = new Scene(toMain);
					Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					
					mainStage.hide();
					mainStage.setScene(mainScene);
					mainStage.show();					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
