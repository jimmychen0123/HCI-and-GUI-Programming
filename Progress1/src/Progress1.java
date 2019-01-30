//Standard javafx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Imports for components in this application
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

//imports for layout
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Progress1 extends Application {
	
	//Declare components at class level
	ProgressBar progBar1;
	
	ProgressIndicator progInd;
	
	Label lblProgress;
	
	Button btnIncrease, btnDecrease;

	
	public Progress1() {
		
	}//constructor
	
	@Override
	public void init() {
		
		progBar1 = new ProgressBar(0);
		progBar1.setMinWidth(360);
		
		progInd = new ProgressIndicator(0);
		progInd.setScaleX(1.25);
		progInd.setScaleY(1.25);
		
		lblProgress = new Label("Progress:");
		
		btnIncrease = new Button("Increase"); 
		btnDecrease = new Button("Decrease");
		
		//Manage button sizes.
		btnIncrease.setMinWidth(60);
		btnDecrease.setMinWidth(60);
		
		//Handle events on the increase button
		btnIncrease.setOnAction(ae ->{
			
			//first, get the current progress from the progress bar
			double progress = progBar1.getProgress();
			
			if(progress <1) {
				
				/*now increase it. Remember the progress bar is at
				 * full scale when the progress is 1
				 */
				progress = progress + 0.05;
				
				
			}//if
			else{
				
				progress=1;
				
			}//else
			
			
			//color progress bar to 
			
			if(progress >= 0.7) {
				progBar1.setStyle("-fx-accent: red;");
				progInd.setStyle("-fx-accent: red;");
			}
			else if (progress <= 0.25) {
				progBar1.setStyle("-fx-accent: blue;");
				progInd.setStyle("-fx-accent: blue;");
				
			}
			else {
				progBar1.setStyle("-fx-accent: green;");
				progInd.setStyle("-fx-accent: green;");
				
			}
				
			//Now set the progress
			progBar1.setProgress(progress);
			progInd.setProgress(progress);
			
			
			
			
		});//events on the increase
		
		//Handle events on the decrease button
		btnDecrease.setOnAction(ae ->{
			
			//first, get the current progress from the progress bar
			double progress = progBar1.getProgress();
			
			if(progress > 0.05) {
				
				/*now decrease it. Remember the progress bar is at
				 *full scale when the progress is 1
				 */
				
				progress = progress - 0.05;
				
			}//if
			
			else{
				
				progress = 0;
				
			};//else
			
			//colour progress bar to alert user if >=70% full scale
			
			if(progress >= 0.7) {
				progBar1.setStyle("-fx-accent: red;");
				progInd.setStyle("-fx-accent: red;");
			}
			else if(progress <= 0.25) {
				progBar1.setStyle("-fx-accent: blue;");
				progInd.setStyle("-fx-accent: blue;");
			}
			else {
				progBar1.setStyle("-fx-accent: green;");
				progInd.setStyle("-fx-accent: green;");
			}
									
			//Now set the progress
			progBar1.setProgress(progress);
			progInd.setProgress(progress);
						
		});//events on the decrease
		
	}//init()
	
	@Override
	public void start(Stage pStage) throws Exception {
		
		
		//Set the title
		pStage.setTitle("Progress 1");
		
		//Set the width and height
		pStage.setWidth(400);
		pStage.setHeight(250);
		
		//create layout
		VBox vb = new VBox();
		GridPane gp = new GridPane();
		HBox hb = new HBox();
		
		
		//Add components to the layout. Main container is vb.
		vb.getChildren().add(lblProgress);
		vb.getChildren().add(progBar1);
		vb.getChildren().add(progInd);
		
		//Add the buttons to hbox.
		hb.getChildren().addAll(btnIncrease, btnDecrease);
		hb.setSpacing(10);
		
		//Align the hb components 
		hb.setAlignment(Pos.BASELINE_RIGHT);
		
		//Add the hbox to the vbox
		vb.getChildren().add(hb);
		
		//set the spacing and padding
		vb.setPadding(new Insets(10));
		vb.setSpacing(10);
		
		//create scene
		Scene s = new Scene(vb);
		
		//set the scene
		pStage.setScene(s);
		
		//add a style
		s.getStylesheets().add("style_progress.css");
		
		//show the stage
		pStage.show();
		

	}//start()
	
	@Override
	public void stop() {
		
	}//stop()

	
	public static void main(String[] args) {
		
		//Launch the application
		launch();
		

	}//main()

}//class()
