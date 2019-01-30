import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;


/**
 * 
 */

/**
 * @author 2971978
 *
 */
public class Simple1 extends Application {
	
	//Component declaration. Components qill have class scope
	TextArea txtMain;

	/**
	 * 
	 */
	public Simple1() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void init () {
		
	}//init()
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Set the title
		primaryStage.setTitle("Simple JavaFX Application V1.0.0");
		
		//Set the width and height
		primaryStage.setWidth(400);
		primaryStage.setHeight(300);
		
		//Instantiate the text area 
		txtMain = new TextArea();
		
		//Create a scene
		Scene s = new Scene(txtMain);
		
		//Set the scene
		primaryStage.setScene(s);
		//show the stage 
		primaryStage.show();

	}
	
	@Override
	public void stop () {
		
	}//stop()

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Launch the application 
		launch();
		
	}//main()

}//class
