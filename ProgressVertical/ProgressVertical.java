//standard fx im@orts
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//imports for components in this application
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Label;

//Imports for layout
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class ProgressVertical extends Application {

	//Declare components at class level
	Label lblProgress;
	
	Button btnIncrease, btnDecrease;
	
	ProgressBar progBar;
	
	ProgressIndicator progInd;
	
	
	
	public ProgressVertical() {
		// TODO Auto-generated constructor stub
	}//constructor
	
	@Override
	public void init() {
		
		//Instantiate controls with 'new'
		lblProgress = new Label("Progress: ");
		
		progBar = new ProgressBar(0);
		progInd = new ProgressIndicator(0);
		
		btnIncrease = new Button("Increase");
		btnDecrease = new Button("Decrease");
		
		//set the min width for buttons
		btnIncrease.setMinWidth(90);
		btnDecrease.setMinWidth(90);
		
		//Handle events on the increase button
		btnIncrease.setOnAction(ae -> {
			
			//get the progress
			double progress = progBar.getProgress();
			
			
			//Increase progress
			
			progress += 0.05;
			
			//Do not allow the progress controls to go below zero
			
			if(progress > 1) {
				
				progress = 1;
			}
			
			else ;
			
			//color progress bar to
			if(progress <= 0.2) {
				
				progBar.setStyle("-fx-accent: blue");
				progInd.setStyle("-fx-accent: blue");
				
			}
			
			else if(progress >= 0.7) {
				
				progBar.setStyle("-fx-accent: red");
				progInd.setStyle("-fx-accent: red");
				
			}
			
			else {
				
				progBar.setStyle("-fx-accent: green");
				progInd.setStyle("-fx-accent: green");
				
			}
			
			//set the progress
			progBar.setProgress(progress);
			progInd.setProgress(progress);
			
			
			
		});//setOnAction()
		
		//Handle events on the decrease button
		btnDecrease.setOnAction(ae -> {
			
			//get the progress
			double progress = progBar.getProgress();
			
			//decrease progress
			
			progress -= 0.05;
			
			//Do not allow the progress controls to go below zero
			
			if(progress < 0) {
				
				progress = 0;
			}
			
			else ;
			
			//color progress bar to
			if(progress <= 0.2) {
				
				progBar.setStyle("-fx-accent: blue");
				progInd.setStyle("-fx-accent: blue");
				
			}
			
			else if(progress >= 0.7) {
				
				progBar.setStyle("-fx-accent: red");
				progInd.setStyle("-fx-accent: red");
				
			}
			
			else {
				
				progBar.setStyle("-fx-accent: green");
				progInd.setStyle("-fx-accent: green");
				
			}
			
			//set the progress
			progBar.setProgress(progress);
			progInd.setProgress(progress);
			
		});//setOnAction()
		
		
		
		
		
		
	}//init()

	
	@Override
	public void start(Stage pStage) throws Exception {
		//set the titile
		pStage.setTitle("Vertical Progress Bar");
		
		//set the width and height
		pStage.setWidth(280);
		pStage.setHeight(400);
		
		pStage.setResizable(false);
		
		//create a layout
		VBox vb = new VBox();
		vb.setSpacing(50);
		vb.setPadding(new Insets(10));
		
		//A vertical box for the progress bar
		VBox vbProg = new VBox();
		vbProg.getChildren().add(progBar);
		
		VBox vbButtons = new VBox();
		vbButtons.getChildren().addAll(btnIncrease, btnDecrease);
		vbButtons.setSpacing(10);
		vbButtons.setPadding(new Insets(10));
		vbButtons.setAlignment(Pos.BASELINE_CENTER);
		
		
		//add components to the layout
		vb.getChildren().add(lblProgress);
		vb.getChildren().add(vbProg);
		vb.getChildren().add(progInd);
		vb.getChildren().add(vbButtons);
		//vb.getChildren().add(btnDecrease);
		
		//Rotate the progress bar
		vbProg.setRotate(-90);
		
		//set tje min width
		progBar.setMinWidth(150);
		
		//Move the progress indicator down
		progInd.setTranslateY(50);
		
		//configure vbProg
		vbProg.setSpacing(30);
		vbProg.setPadding(new Insets(20));
		
		//create the scene
		Scene s = new Scene(vb);
		
		//set the scene
		pStage.setScene(s);
		
		//Add a style sheet.
		s.getStylesheets().add("style_progress.css");
		
		//show the stage
		pStage.show();

	}//start()

	public void stop() {
		
	}//stop()
	public static void main(String[] args) {
		
		//Launch the application
		launch();
		

	}//main()

}//class()
