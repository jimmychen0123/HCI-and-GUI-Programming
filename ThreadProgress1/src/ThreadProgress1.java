//Standard fx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Imports for components in this application
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

//Imports for layout
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//imports for concurrency
import javafx.concurrent.Task;




public class ThreadProgress1 extends Application {
	
	Task <Void> task;
	
	//Declare visual components at class scope
	Button btnStart, btnStop;
	ProgressBar progBar;
	ProgressIndicator progInd;
	Label lblProgress;
	
	@Override
	public void init() {
		
		//Instantiate components with "new"
		lblProgress = new Label("Progress: ");
		progBar = new ProgressBar(0);
		progInd = new ProgressIndicator(0);
		progInd.setScaleX(1.5);
		progInd.setScaleY(1.5);
		progInd.setPadding(new Insets(10));
		btnStart = new Button("Start");
		btnStop = new Button("Stop");
		
		//adjust components size
		progBar.setMinWidth(350);
		btnStart.setMinWidth(70);
		btnStop.setMinWidth(70);
		
		//Handle events on the buttons
		btnStart.setOnAction(ae -> startTask());
		btnStop.setOnAction(ae ->stopTask());
			
		
	}//init()

	private void startTask() {
		
		task = new Task<Void>() {
			
			@Override
			public Void call() {
				final long max = 100000000;
				for(long i = 1; i<=max; i++) {
					if(isCancelled()) {
						updateProgress(0, max);
						break;
					}//if
					
					//update the progress
					updateProgress(i, max);
				}//for
				return null;
			}//call()
			
		};//Task
		
		//Bind the progress bar to the task
		progBar.progressProperty().bind(task.progressProperty());
		progInd.progressProperty().bind(task.progressProperty());
		
		//create and start the thread
		new Thread(task).start();
		
	}//startTask
	
	//Method to stop the task
	private void stopTask() {
		
		task.cancel();
		
	}//stoptask

	public ThreadProgress1() {
		
	}//constructor

	
	@Override
	public void start(Stage pStage) throws Exception {
		
		//set the title
		pStage.setTitle("ThreadProgress1");
		
		//set the width and height
		pStage.setWidth(400);
		pStage.setHeight(300);
		pStage.setResizable(false);
		
		//create layout
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		
		HBox hbButtons = new HBox();
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		//Add the buttons to hbox
		hbButtons.getChildren().addAll(btnStart,btnStop);
		hbButtons.setSpacing(10);
		hbButtons.setPadding(new Insets(10));
		
		//add components to the layout
		vb.getChildren().addAll(lblProgress, progBar,progInd,hbButtons);
		
		//create scene
		Scene s = new Scene(vb);
		//set the scene
		pStage.setScene(s);
		//show the stage
		pStage.show();
		

	}//start
	
	@Override
	public void stop() {
		
	}//stop

	
	public static void main(String[] args) {
		
		launch();
		

	}//main()

}//class()
