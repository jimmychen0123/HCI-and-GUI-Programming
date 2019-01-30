//Standard javafx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Imports for components in this application
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;

//Import for layout
import javafx.scene.layout.BorderPane;

//Imports for image
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

//Imports for file support 
import javafx.stage.FileChooser;
import java.io.File;

/**
 * 
 */

/**
 * @author Jimmy
 *
 */
public class PictureViewer1 extends Application {
	
	//Instantiate a menu and menu items.
	MenuBar mBar;
	Menu mnuFile;
	MenuItem mitemOpen;
	
	//The image
	Image img;
	
	//The image view.
	ImageView imView;
	
	//A label to provide feedback
	Label lblStatus;

	/**
	 * 
	 */
	public PictureViewer1() {
		// TODO Auto-generated constructor stub
	}//constructor()

	@Override
	public void init() {
		//Instantiate components using "new"
		mBar = new MenuBar();
		mnuFile = new Menu("File");
		mitemOpen= new MenuItem("Open");
		
		//Add the menu item to the menu
		mnuFile.getItems().add(mitemOpen);
		
		//Add the menu to the menubar
		mBar.getMenus().add(mnuFile);
		
		//Instantiate the image view
		imView = new ImageView();
		
		//Handle events on the "Open" menu item
		mitemOpen.setOnAction(ae-> openImage());
		lblStatus = new Label("File: ");
	}//init()
	
	private void openImage() {
		//Display a dialog to support selection of an image
		
		FileChooser fc = new FileChooser();
		fc.setTitle("Select an Image: ");
		
		//Set the extension filter. We want .jpg and jpeg.
		fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("JPG", "jpg"));
		
		//Construct a file and try to open it
		File file;
		
		//If the user chooses a file and confirms the dialog
		if ((file = fc.showOpenDialog(null)) !=null) {
			
			//construct an image with the file chosen
			img = new Image (file.toURI().toString());
			
			//Update the status label with the file name.
			lblStatus.setText("Image path:"+file.toURI().toString());
			
			//set the image into the image view 
			imView.setImage(img);
			
			
		}//if
		
		else {
			//Dialog cancelled. Do nothing 
		}
		
	}
	
	@Override
	public void start(Stage pStage) throws Exception {
		//Set the title.
		pStage.setTitle("Picture Viewer V1.0.1");
		
		//Set the Width and height
		pStage.setWidth(400);
		pStage.setHeight(300);
		
		//Create a layout
		BorderPane bp = new BorderPane();
		bp.setTop(mBar);
		bp.setCenter(imView);
		bp.setBottom(lblStatus);
		
		
		//Create a scene
		Scene s = new Scene (bp);
		
		//Set the Scene
		pStage.setScene(s);
		
		//Show the stage
		pStage.show();
		

	}//start()
	
	@Override
	public void stop() {
		
	}//stop()

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Launch the application.
		launch();

	}

}
