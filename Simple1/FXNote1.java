//Standard javafx.imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Import for components in this application 
import javafx.scene.control.TextArea;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


/**
 * 
 */

/**
 * @author 2971978
 *
 */
public class FXNote1 extends Application {

	//Components have class scope when declare here
	
	TextArea txtMain;
	/**
	 * 
	 */
	public FXNote1() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	
	@Override 
	public void init() {
		
	}
	
	
	
	@Override
	public void start(Stage pStage) throws Exception {
		
		//Set the width and height
		pStage.setWidth(400);
		pStage.setHeight(300);
		
		//Set the title
		pStage.setTitle("FXNotes1 V1.0.0");
		
		//Instantiate a text area 
		txtMain = new TextArea();
		//create scene
		Scene s = new Scene(txtMain);
		//Set the scene
		pStage.setScene(s);
		//show the stage
		pStage.show();
		
		
	}//start
	
	@Override
	public void stop() {
		
	}//stop()

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Launch the application
		launch();
		
	}//main()

}//class
