//Standard javafx imports.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Imports for components in this application
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

//Import for layout
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

//Import for timing of the tests
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class TypingTutor1 extends Application {
	
	//Variable to store error count
	int errors = 0;
	
	//String array to store the sentences to be typed
	String [] sentences = new String[5];
	
	//Just an indexx for the array items.
	int i = 0;
	
	//For measuring time
	Timeline tLine;
	int elapsedTime = 0;
	
	
	
	//Declare components at class level scope
	Label lblExample, lblType, lblErrors, lblShowErrors;
	
	//Labels for the time 
	Label lblTime, lblShowTime;
	
	Button btnStart, btnStop;
	
	TextField txtfExample, txtfType;
	
	public TypingTutor1() {
		
	}
	
	@Override
	public void init() {
		
		lblExample = new Label("Example:");
		lblType = new Label("Type:");
		lblErrors = new Label("Errors: ");
		lblShowErrors = new Label("0");
		
		btnStart = new Button("Start");
		btnStop = new Button("Stop");
		
		txtfExample = new TextField();
		txtfType = new TextField();
		
		lblTime = new Label("Time:");
		lblShowTime = new Label("0");
		
		//Size the textfields and buttons
		txtfExample.setMinWidth(350);
		txtfType.setMinWidth(350);
		
		btnStart.setMinWidth(60);
		btnStop.setMinWidth(60);
		
		//Initialise 5 sentence for typing practice
		sentences[0] = "Mary had a little lamb";
		sentences[1] = "Whose fleece was white as snow";
		sentences[2] = "And everywhere that Mary went";
		sentences[3] = "The lamb was sure to go";
		sentences[4] = "That's all folks";
		
		
		//Handle events on the start button
		btnStart.setOnAction(ae -> {
			
			//zero the array index
			i=0;
			errors = 0;
			
			//clear the error count
			lblShowErrors.setText(errors + "");
			
			//clear typing practice textfield
			txtfType.clear();
			
			//Reset the timer readout to zero
			elapsedTime = 0 ;
			lblShowTime.setText(0 + "");
			
			//show the first sentence to be typed
			txtfExample.setText(sentences[i]);
			
			//Now create and start the test timer
			tLine = new Timeline(new KeyFrame(
					Duration.millis(1000),
					timertick -> {
				elapsedTime++;
				lblShowTime.setText(elapsedTime + "");
				
			}));
			
			tLine.setCycleCount(Animation.INDEFINITE);
			tLine.play();
			
		});//btnStart handle event
		
		//Handle events on the stop button.
		btnStop.setOnAction(ae -> {
			//Stop the timer
			tLine.stop();
		});
		

		//Handle events on the typing textfield
		txtfType.setOnKeyReleased(ae -> { 
			
			//First check if the start of each sentence is the same
			if(txtfExample.getText().startsWith(txtfType.getText())) {
				
				//No error. The 
				txtfType.setStyle("-fx-text-inner-color: black");
				
			}//if startWith()
			
			else {
				//There is a typing error. color the text red
				txtfType.setStyle("-fx-text-inner-color: red");
				
				//Increment the errors
				errors++;
				//Show the error count in the error count lable
				lblShowErrors.setText(errors+"");
				
			}//else
			
			//Now check for a complete match. If there is a full
			//match, show the next sentence
			if(txtfType.getText().equals(txtfExample.getText())) {
				
				if(i+1 < sentences.length) {
					
					//Increment the sentence index i
					i++;
					
					//Now show the next sentence
					txtfExample.setText(sentences[i]);
				
					//Also clear the typing text field.
					txtfType.clear();
					
				}//if array in bound check
				
							
			}
			
		});
		
	}//init()
	

	@Override
	public void start(Stage pStage) throws Exception {
		
		//Set the title.
		pStage.setTitle("TypingTutor V1.0.0");
		
		//Set the width and height.
		pStage.setWidth(400);
		pStage.setHeight(250);
		
		//Create layout
		GridPane gp = new GridPane();
		
		//Set the gp gaps.
		
		gp.setHgap(10);
		gp.setVgap(10);
		
		//set the padding for the gp.
		gp.setPadding(new Insets(10));
		
		/*Add components to the layout
		 * Components must have been instantiated with 'new' at this point
		 */
		gp.add(lblExample, 0, 0);
		gp.add(lblErrors, 2, 0);
		gp.add(lblShowErrors, 3, 0);
		
		//Add the time labels to a hbox
		HBox hbTime = new HBox();
		hbTime.setSpacing(5);
		hbTime.getChildren().addAll(lblTime,lblShowTime);
		gp.add(hbTime,4, 0);
		
		
		gp.add(txtfExample, 0, 1, 4, 1);
		gp.add(lblType, 0, 2);
		
		gp.add(txtfType, 0, 3, 4, 1);
		
		//create a hbox and add the buttons
		HBox hbButtons = new HBox();
		hbButtons.getChildren().addAll(btnStop, btnStart);
		
		//Now add the hbox with buttons to the gp
		gp.add(hbButtons, 4, 4);
		
		//set the hbox spacing.
		hbButtons.setSpacing(10);
		
		//Create a scene
		Scene s = new Scene(gp);
		
		//Set the scene
		pStage.setScene(s);
		
		//Show the stage
		pStage.show();
		
	}
	
	@Override
	public void stop() {
		
	}//stop()

	
	public static void main(String[] args) {
		
		//launch application 
		launch();	

	}//main()

}//class()
