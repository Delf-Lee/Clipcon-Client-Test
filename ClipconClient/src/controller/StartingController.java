package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.awt.event.KeyEvent;

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

public class StartingController implements Initializable {
	
	@FXML private Button loginBtn;
	@FXML private Button signupBtn;	
	@FXML private TextField idTF;	
	@FXML private PasswordField pwPF;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		loginBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("�α��� ����");
						
				if(idTF.getText().equals("test") && pwPF.getText().equals("12")){
					System.out.println("�α��� ����");
									
					try {
						Parent entry = FXMLLoader.load(getClass().getResource("/view/EntryView.fxml"));
						Scene entryScene = new Scene(entry);
						Stage entryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
						
						entryStage.hide();
						entryStage.setScene(entryScene);
						entryStage.show();
						
						System.out.println("��Ʈ�� ȭ�� (����� or ����) ���� �����մϴ�.");
						
						startHookProcess();
						
						
					} catch (Exception e){
						e.printStackTrace();
					}
					
				}else{
					System.out.println("�α��� ����");
				}
			
			
			}
		});
		
		signupBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("ȸ������ ����");	
				
				try {
					Parent signup = FXMLLoader.load(getClass().getResource("/view/SignupView.fxml"));
					Scene signupScene = new Scene(signup);
					Stage tempStage = new Stage();
					tempStage.setScene(signupScene);
					tempStage.show();
//					Stage signupStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//					signupStage.setScene(signupScene);
//					signupStage.show();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public void startHookProcess() {
		hookManager.GlobalKeyboardHook hook = new hookManager.GlobalKeyboardHook();
		int vitrualKey = KeyEvent.VK_H;
        boolean CTRL_Key = true;
        boolean ALT_Key = true;
        boolean SHIFT_Key = false;
        boolean WIN_Key = false;
		
        hook.setHotKey(vitrualKey, ALT_Key, CTRL_Key, SHIFT_Key, WIN_Key);
        hook.startHook();
        // waiting for the event
        hook.addGlobalKeyboardListener(new hookManager.GlobalKeyboardListener() {
            public void onGlobalHotkeysPressed() {
                System.out.println("CTRL + ALT + H was pressed");
                System.out.println(ClipboardController.readClipboard());
            }
        });
	}

}
