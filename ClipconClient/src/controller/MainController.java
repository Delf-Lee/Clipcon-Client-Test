package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController implements Initializable{
	@FXML private Button exitBtn;
	@FXML private Button addBtn;
	@FXML private Button deleteBtn;
	@FXML private Button searchBtn;
	@FXML private Button inviteBtn;
	
	@FXML private TextField groupNameTF;
	@FXML private TextField groupKeyTF;
	@FXML private TextField searchTF; 
	
//	controller.EntryControl entryControl = new controller.EntryControl();
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
//		String groupName = entryControl.getGroupName();
//		groupNameTF.setText(groupName);
		
		addBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				System.out.println("�ּҷϿ��� ģ�� �߰��� �����մϴ�.");
				
//				try {				
//					Parent newGroup = FXMLLoader.load(getClass().getResource("/view/EntryView.fxml"));
//					Scene newScene = new Scene(newGroup);
//					Stage newStage = new Stage();
//					newStage.setScene(newScene);
//					newStage.show();
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			}
		});
		
			
		deleteBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				System.out.println("�ּҷϿ��� ģ�� ������ �����մϴ�.");
				

			}
		});
		
		inviteBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				System.out.println("�ּҷϿ��� ģ�� �ʴ븦 �����մϴ�.");
				

			}
		});
		
		searchBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				String searchTarget = searchTF.getText();
				System.out.println("�ּҷϿ��� ģ�� �˻��� �����մϴ�. �˻� ����� < "+searchTarget+" > �Դϴ�.");
				

			}
		});
		
		exitBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				System.out.println("�׷쿡�� �����ϴ�.");
				
				try {				
					Parent goBack = FXMLLoader.load(getClass().getResource("/view/EntryView.fxml"));
					Scene scene = new Scene(goBack);
					Stage backStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					
					backStage.hide();
					backStage.setScene(scene);
					backStage.show();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
