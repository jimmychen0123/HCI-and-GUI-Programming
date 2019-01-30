//Standard fx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Imports for components in this application.
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.application.Platform;

//Imports for layout
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

//Alert 
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//File support
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class FXnote1 extends Application {

	//Declare  components at class scope
	private TextArea txtMain;
	//Menubar and menus 
	private  MenuBar mBar;
	//File, Edit and help menus
	Menu mnuFile;
	Menu mnuEdit;
	Menu mnuHelp;
	
	BorderPane bp;
	
	public FXnote1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		
		//Instantiate the menu and menubar
		mBar = new MenuBar ();
		txtMain = new TextArea ();
		bp = new BorderPane();
		
		//Instantiate menus, menuitems
			//File
		mnuFile = new Menu("File");
		MenuItem fileNewItem = new MenuItem("New");
		mnuFile.getItems().add(fileNewItem);
		
		MenuItem fileOpenItem = new MenuItem("Open");
		fileOpenItem.setOnAction(ae->showFileOpenDialog());
		mnuFile.getItems().add(fileOpenItem);
		
		MenuItem fileSaveItem = new MenuItem("Save");
		mnuFile.getItems().add(fileSaveItem);
		

		MenuItem fileSaveAsItem = new MenuItem("Save As");
		mnuFile.getItems().add(fileSaveAsItem);
		

		MenuItem fileExitItem = new MenuItem("Exit");
		fileExitItem.setOnAction(ae -> Platform.exit() );
		mnuFile.getItems().add(fileExitItem);
			
		//Edit menu
		mnuEdit = new Menu("Edit");
		MenuItem editUndoItem = new MenuItem("Undo");
		editUndoItem.setOnAction(ae -> txtMain.undo());
		mnuEdit.getItems().add(editUndoItem);
		
		MenuItem editRedoItem = new MenuItem("Redo");
		editRedoItem.setOnAction(ae -> txtMain.redo());
		mnuEdit.getItems().add(editRedoItem);
		
		MenuItem editCutItem = new MenuItem("Cut");
		editCutItem.setOnAction(ae -> txtMain.cut());
		mnuEdit.getItems().add(editCutItem);
		
		MenuItem editCopyItem = new MenuItem("Copy");
		editCopyItem.setOnAction(ae->txtMain.copy());
		mnuEdit.getItems().add(editCopyItem);
		
		MenuItem editPasteItem = new MenuItem("Paste");
		editPasteItem.setOnAction(ae->txtMain.paste());
		mnuEdit.getItems().add(editPasteItem);
		
		MenuItem editDeleteItem = new MenuItem("Delete");
		editDeleteItem.setOnAction(ae-> txtMain.deleteText(txtMain.getSelection()));
		mnuEdit.getItems().add(editDeleteItem);
		
		MenuItem editSelectAllItem = new MenuItem("Select All");
		editSelectAllItem.setOnAction(ae->txtMain.selectAll());
		mnuEdit.getItems().add(editSelectAllItem);
		
		//Help Menu
		mnuHelp = new Menu ("Help");
		MenuItem helpAboutItem = new MenuItem("About");
		helpAboutItem.setOnAction(ae->showAboutDialog());
		mnuHelp.getItems().add(helpAboutItem);
		
		
		
		
		//Add the menu to the menubar
		mBar.getMenus().add(mnuFile);
		mBar.getMenus().add(mnuEdit);
		mBar.getMenus().add(mnuHelp);
		
		
		
	}//init()
	
	public void showAboutDialog() {
		
		new Alert (AlertType.INFORMATION,"FXNotes V1.0.0 \n Copyrigt Jimmy").showAndWait();
		
	}//showAboutDialog()
	
	public void showFileOpenDialog() {
		//Support showing a file dialog
		FileChooser fChooser = new FileChooser();
		//Create a file and assign it with the user's selection.
		File fileToOpen = fChooser.showOpenDialog(null);
		
		if(fileToOpen!=null) {
			//open it and show it in the text area
			try {
				StringBuilder strBldr = new StringBuilder();
				BufferedReader br = new BufferedReader (new FileReader(fileToOpen));
				
				String text;
				while ((text = br.readLine())!= null) {
					text = text + "\n";
					strBldr.append(text);
				}//while
				
			// Now set the text into the text area
				
				txtMain.setText(strBldr.toString());
				
				//done
				br.close();
						
			}//try
			catch(IOException ioe) {
				
				
				
			}//catch
		}// if
		
	}//showFileOpenDialog()
	@Override
	public void start(Stage pStage) throws Exception {
		//Set the title
		pStage.setTitle("FXNotes Version 1.0.0");
		
		//Set the width and height
		pStage.setWidth(400);
		pStage.setHeight(300);
		//Instantiate a borderpane container 
		bp= new BorderPane();
		//Add the textarea to the borderpan
		bp.setTop(mBar);
		bp.setCenter(txtMain);
		//Create a scene
		Scene s = new Scene(bp);
		//Set the scene
		pStage.setScene(s);
		//Show the stage
		pStage.show();
		
		
	}

	public void stop() {
		
	}// stop()
	public static void main(String[] args) {
		//Launch the application
		launch();

	}//main()

}//class
