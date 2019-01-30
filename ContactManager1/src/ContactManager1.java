//Standard javafx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Imports for components in this application.
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

//File supports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

//Supporting quitting the application
import javafx.application.Platform;
//Imports for layout
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

/**
 * 
 */

/**
 * @author Jimmy
 *
 */
public class ContactManager1 extends Application {

	//Declare components with class scope
	Label lblNames, lblEmail, lblAddress;
	
	//Textfields
	TextField txtfEmail;
	TextArea txtAddress;
	
	//the close button
	Button btnClose;
	
	//List view for contact names
	ListView <String> lvNames;
	
	public ContactManager1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		
		//Instantiate components
		lvNames = new ListView<String>();
		
		lblNames = new Label("Name: ");
		lblEmail = new Label("Email: ");
		lblAddress = new Label("Address: ");
		
		txtfEmail = new TextField();
		txtAddress = new TextArea();
		
		btnClose = new Button("Close");
		
		//Handle event on the close button
		btnClose.setOnAction(ae->Platform.exit());
		
		//Handle events on the list view.
		lvNames.setOnMousePressed(ae ->{
			//get the 
			String selectedName = 
					lvNames.getSelectionModel().getSelectedItem().toString();
			//call method to search the file and populate email and address
			showEmailAndAddr("./contacts.csv", selectedName);
			
		});
		
		lvNames.setOnKeyReleased(ae ->{
			//get the 
			String selectedName = 
					lvNames.getSelectionModel().getSelectedItem().toString();
			//call method to search the file and populate email and address
			showEmailAndAddr("./contacts.csv", selectedName);
			
		});
		
		
	}//init()
	
	private void showEmailAndAddr(String fileName, String selectedName) {
		//Read through names from the contacts file and find a match
		try {
			String line;
			FileReader fr = new FileReader(fileName);
			BufferedReader buf = new BufferedReader(fr);
			
			//Iterate through the file.
			while((line = buf.readLine())!=null) {
				
				if(line.startsWith(selectedName)) {
					//A 3 item array to store split line contents
					String [] contactDataArray = new String [3];
					
					//Split the string
					contactDataArray = line.split(":");
					
					//add the email and geo address
					//txtfEmail.setText(contactDataArray[1]);
					txtAddress.setText(contactDataArray[2]);
				}//if
				else;
				
				
			}//while
		}//try
		catch(IOException ioe) {
			
			ioe.toString();
			
		}
	}//showEmailAndAddr()
	
	@Override
	public void start(Stage pStage) throws Exception {
		//set the title
		pStage.setTitle("Contact Manager V1.0.1");
		
		//Set the width and height
		pStage.setWidth(460);
		pStage.setHeight(300);

		//create a layout
		GridPane gp = new GridPane();
		
		
		//create a scene
		Scene s = new Scene(gp);
		
		//Add components to the gridpane
		gp.add(lblNames, 0, 0);
		gp.add(lblEmail, 1, 0);
		
		gp.add(lvNames, 0, 1,1,3);
		gp.add(txtfEmail, 1, 1);
	
		gp.add(lblAddress, 1, 2);
		gp.add(txtAddress, 1, 3);
		
		
		
		//A hbox for the button
		HBox hbButton = new HBox();
		hbButton.getChildren().add(btnClose);
		hbButton.setAlignment(Pos.BASELINE_RIGHT);
		
		gp.add(hbButton, 1, 4);
		
		//Set the button width
		btnClose.setMinWidth(60);
		lvNames.setMinWidth(120);
		
		//Set insets for gp 
		gp.setPadding(new Insets(10));
		
		//set the spacing
		gp.setHgap(10);
		gp.setVgap(10);
		
		//set the scene
		pStage.setScene(s);
		
		//Add a style sheet
		s.getStylesheets().add("style_contactManager.css");
		
		//Populate the listview ith contact names
		readContacts("./contacts.csv");
		
		//Show the list in sorted(ASC) order
		lvNames.getItems().sort(null);
		
		//show the stage
		pStage.show();
		
	}//start()
	
	@Override
	public void stop() {
		
	}//stop()

	private void readContacts(String contactsFile) {
		
		//Read name from the contact file
		try {
			// try open the contact file
			String line;
			FileReader fr = new FileReader (contactsFile);
			BufferedReader buf = new BufferedReader(fr);
			//Iterate through the file line by line
			while((line = buf.readLine())!=null) {
				
				//A 3 item String Array to store contact details
				String[] contactDataArray = new String[3];
				//Split the string on the ":" character
				contactDataArray = line.split(":");
				
				//Now just add the name to the listview
				lvNames.getItems().add(contactDataArray[0]);
			}
			
		}//try
		catch(IOException ioe) {
			
			ioe.toString();
			
		}//catch
	}//readContacts
	
	public static void main(String[] args) {
		//Launch the application
		launch();

	}//Main()

}//Class
