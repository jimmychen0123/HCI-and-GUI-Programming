//Standard javafx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
//import for components in this application
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

//Data components. DatePicker show a small calender when activated
import javafx.scene.control.DatePicker;

//Imports for layout
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

//Special date/time imports
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * 
 */

/**
 * @author Jimmy
 *An application to demonstrate a basic dialog for the 
 *input and retrieval of user information
 */
public class MachineHire1 extends Application {

	//Components declared at class scope
	Button btnShowDialog;
	
	//Labels 
	Label lblStartDate, lblEndDate, lblShowStartDate,
	lblShowEndDate, lblTotalCost, lblShowTotalCost;
	
	//Datepicker for start and end date of hire
	DatePicker dpStartDate, dpEndDate;
	
	public MachineHire1() {
		
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	
	
	public void init() {
		
		lblStartDate = new Label("Start date:");
		lblShowStartDate = new Label();
		
		lblEndDate = new Label("End date:");
		lblShowEndDate = new Label();
		
		lblTotalCost = new Label ("Total cost:");
		lblShowTotalCost = new Label();
		
		btnShowDialog = new Button ("New Booking");
		
		//Handle events on the button
		btnShowDialog.setOnAction(ae->showDialog());
		
	}//init()
	
	private void showDialog() {
		//create new stage
		Stage dlgStage = new Stage();
		
		//set the title
		dlgStage.setTitle("Enter Booking Details: ");
		
		//set the width and height.
		dlgStage.setWidth(300);
		dlgStage.setHeight(250);
		
		//Create the dialog components
		dpStartDate = new DatePicker();
		dpStartDate.setValue(LocalDate.now());
		
		dpEndDate = new DatePicker();
		dpEndDate.setValue(LocalDate.now());
		
		//Labels to identify the controls
		Label lblStart = new Label ("Start date:");
		Label lblEnd = new Label ("End date");
		Label lblStatus = new Label("Status");
		Label lblCostPerday = new Label("Cost/Day");
		TextField  txtfCostPerDay = new TextField("0");
		
		//The ok and cancel buttons
		Button btnOK = new Button("OK");
		Button btnCancel = new Button("Cancel");
		
		//Size the buttons
		btnOK.setMinWidth(60);
		btnCancel.setMinWidth(60);
		
		//Handle events on the dialog buttons
		btnCancel.setOnAction(ae->dlgStage.close());
		
		//The OK button
		btnOK.setOnAction(ae->{
			
			//Assign the dates entered by the user.
			LocalDate start = dpStartDate.getValue();
			LocalDate end = dpEndDate.getValue();
			
			//Check the end date is after the start date
			if (end.isAfter(start)|| end.isEqual(start)) {
				

				//Show the dates in the main interface labels
				lblShowStartDate.setText(dpStartDate.getValue().toString());
				lblShowEndDate.setText(dpEndDate.getValue().toString());
				
				//Get the cost per day
				double costPerDay, totalCost;
				if(txtfCostPerDay.getText().isEmpty()) {
					//Alert the user that the text field is empty
					new Alert(AlertType.ERROR,
							"Cost/Day field is empty. Please enter a cost per day").showAndWait();
					
					
				}//if text field is empty
				costPerDay=Double.parseDouble(txtfCostPerDay.getText());
				
				if(costPerDay>0) {
					
					//Get the number of days between start and finish dates
					long p = ChronoUnit.DAYS.between(start, end);
					
					//Days are inclusive
					p++;
					//Calculate the total cost
					totalCost = p * costPerDay;
					
					//Show the total cost in the maininterface label
					lblShowTotalCost.setText("" + totalCost);
					
					//final close the dialog
					dlgStage.close();
					
				}//if cost per day
				else {
					
					new Alert (AlertType.ERROR,"Invalid cost").showAndWait();
					
				}
					
			}//if
			else {
				new Alert (AlertType.ERROR,"The END date should be after Start Date.").showAndWait();
			}//else
			
			
		});
		
		
		
		//Use a gridpane to contain dialog components
		GridPane dgp = new GridPane();
		
		//set the padding and gaps
		dgp.setPadding(new Insets(10));
		dgp.setHgap(10);
		dgp.setVgap(10);
		
		
		//Add components 
		dgp.add(lblStart, 0, 0);
		dgp.add(dpStartDate, 0, 1);
		
		dgp.add(lblEnd, 0 , 2);
		dgp.add(dpEndDate, 0, 3);
		
		dgp.add(lblCostPerday, 0, 4);
		dgp.add(txtfCostPerDay, 0, 5);
		
		
		dgp.add(btnCancel, 1, 5);
		dgp.add(btnOK, 2, 5);
		
		//create a scene
		Scene ds = new Scene (dgp);
		
		//set the scene
		dlgStage.setScene(ds);
		
		//Apply the stylesheet to the dialog
		ds.getStylesheets().add("style_machine1.css");
		//show the scene
		dlgStage.show();
		
	}
	
	@Override
	public void start(Stage pStage) throws Exception {
		
		//set the title.
		pStage.setTitle("Machine Hire V1.0.0");
		
		//set the width and height
		pStage.setWidth(400);
		pStage.setHeight(300);
		
		//Create a layout
		GridPane gp = new GridPane();
		//gp.setGridLinesVisible(true);
		
		//Configure padding and spacing
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);
		
		//Add components to the layout
		
		gp.add(lblStartDate, 0, 0);
		gp.add(lblShowStartDate, 1, 0);
		
		gp.add(lblEndDate, 0, 1);
		gp.add(lblShowEndDate, 1, 1);
		
		gp.add(lblTotalCost, 0, 2);
		gp.add(lblShowTotalCost, 1, 2);
		
		gp.add(btnShowDialog, 0, 3);
		
		//create a scene
		Scene s = new Scene(gp);
		//set the scene
		pStage.setScene(s);
		
		//Apply a style to the scene using a style sheet
		s.getStylesheets().add("style_machine1.css");
		//show the stage
		pStage.show();

	}//start
	
	
	@Override
	public void stop() {
		
	}//stop()

	
	public static void main(String[] args) {
		//launch application 
		launch();
	}//main()

}//class()
