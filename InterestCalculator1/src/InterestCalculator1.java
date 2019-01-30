//Standard javafx import
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Imports for components 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;

//Imports for layout
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

//Geometry etc.
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.application.Platform;

//Import for number/currency formatting
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

//Data components. DatePicker show a small calender when activated
import javafx.scene.control.DatePicker;

//Alert
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class InterestCalculator1 extends Application {
	//Instantiate compenents with keyword "new"
	boolean startSelected = false;
	boolean endSelected = false;
	//Currency formatting Euro
	NumberFormat euroFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
	
	//Declare components at class level
	//Labels
	Label lblCapital, lblIntRate, lblTerm;
	
	//Buttons
	Button btnClose, btnCalculate, btnDialog;
	
	//TextFields
	TextField txtfCapital, txtfIntRate, txtfTerm;
	
	//TextArea
	
	TextArea txtAnalysis;
	
	//Check box
	CheckBox chkAnalysis;
	
	public InterestCalculator1() {
		
	}//contructor()

	@Override
	public void init() {
		
		//Labels
		lblCapital = new Label("Capital:");
		lblIntRate = new Label("Interest rate");
		lblTerm = new Label("Term (yrs):");
		
		//Buttons
		btnClose = new Button("Close");
		btnCalculate = new Button("Calculate");
		btnDialog = new Button("...");
		
		//TextFields
		txtfCapital = new TextField();
		txtfIntRate = new TextField();
		txtfTerm = new TextField();
		
		//TextArea
		txtAnalysis = new TextArea();
		
		//Check box
		chkAnalysis = new CheckBox("Show analysis");
		
		//Manage event handling on the close button
		btnClose.setOnAction(ae -> Platform.exit());
		
		//Event handling on the calculate button
		btnCalculate.setOnAction(ae -> doInterest());
		
		//handle the event on the dialog
		btnDialog.setOnAction(ae -> showDialog());
		
	}//init()
	
	private void showDialog() {
		
		//create stage
		Stage dlgStage = new Stage();
		
		//set the title
		dlgStage.setTitle("Select Investment Term: ");
		
		//set the width and height
		dlgStage.setWidth(300);
		dlgStage.setHeight(200);
		
		//components in the dialog
		//Label
		Label lblStart = new Label("Start Date: "); 
		Label lblEnd = new Label("End Date: ");
		
		//Datepicker for start and end date
		DatePicker dpStart = new DatePicker();
		DatePicker dpEnd = new DatePicker();
		
		LocalDate todaysDate = LocalDate.now();
		//put default values into the datepicker
		dpStart.setValue(todaysDate);
		dpStart.editableProperty().set(false);
		dpEnd.setValue(todaysDate);
		dpEnd.editableProperty().set(false);
		
		//buttons
		Button btnCancel = new Button("Cancel");
		Button btnOk = new Button("Ok");
		
		//Manage control size
		dpStart.setMinWidth(90);
		dpEnd.setMinWidth(90);
		
		btnOk.setMinWidth(70);
		btnCancel.setMinWidth(70);
		
		//Disable the ok button initially
		btnOk.setDisable(true);
		
		//create layout
		GridPane gpd = new GridPane();
		gpd.setPadding(new Insets(10));
		
		//Setting the Grid alignment 
		//Source: https://www.tutorialspoint.com/javafx/layout_gridpane.htm
		gpd.setAlignment(Pos.CENTER); 
		
		//set the gaps for gpd
		gpd.setHgap(10);
		gpd.setVgap(10);
		
		
		//Add components to the layout
		gpd.add(lblStart, 0, 0);
		gpd.add(dpStart, 0, 1);
		
		gpd.add(lblEnd, 0, 2);
		gpd.add(dpEnd, 0, 3);
		
		gpd.add(btnCancel, 1, 4);
		gpd.add(btnOk, 2, 4);
		
		
		//create the scene
		Scene ds = new Scene(gpd);
		//set the scene
		dlgStage.setScene(ds);
		//show the stage
		dlgStage.show();
		//Handle the events on cancel buttons
		
		btnCancel.setOnAction(ae -> dlgStage.close());
		
		//enable the ok button
		dpStart.setOnMouseClicked(ae ->{
			startSelected = true;
			//Now check both booleans
			if(startSelected&&endSelected) btnOk.setDisable(false);
			else;
			
		});
		
		dpEnd.setOnMouseClicked(ae ->{
			endSelected = true;
			//Now check both booleans
			if(startSelected&&endSelected) btnOk.setDisable(false);
			else;
		});
		
		
		//Handle the events on ok buttons
		btnOk.setOnAction(ae -> {
			
			//Get the dates selected by the user
			LocalDate startDate = dpStart.getValue();
			LocalDate endDate = dpEnd.getValue();
			
			//check the end date is after the start date
			if(endDate.isAfter(startDate)) {
				
				long years = ChronoUnit.YEARS.between(startDate, endDate);
				
				//set the number of years into the textfield on the main UI
				txtfTerm.setText(years + "");
				
				//close the dialog
				dlgStage.close();
				
			}//if
			
			else {
				//Alert 
				new Alert(AlertType.ERROR,
						"End date should be after the start date").showAndWait();
				
			}//else
			
			
		});//setOnaction
		
		
		
		
	}//showDialog()
	
	private void doInterest() {
		
		//Just do simple interest to test
		//First, get the capital, interest rate and term in years
		
		double capital = Double.parseDouble(txtfCapital.getText());
		double rate = Double.parseDouble(txtfIntRate.getText());
		int years = Integer.parseInt(txtfTerm.getText());
		
		//use a loop to iteratively calculate compound interest
		int i = 1;
		double amt = capital;
		for(i=1; i<= years; i++) {
			
			//Now call the helper method to get simple interest.
			double interestAmt = getSimpleInterest(amt, rate, 1);
			
			//add on accumulated interest each time through the loop
			amt = amt + interestAmt;
			
			if(chkAnalysis.isSelected()) {
				
				//show formatted output in the text area
				txtAnalysis.setText(txtAnalysis.getText()+
						"Year " + i +"\t"+"Interest earned: "+ euroFormat.format(interestAmt) 
						+ "\tAccumulated total:" +euroFormat.format(amt)+"\n");
				
			}//if
			else {
				//Show the interest figures in the text area
				txtAnalysis.setText("Final amount after interest:\n"+euroFormat.format(amt)+"\n");
				
			}//else
		}//for
		
		//Now call the helper method to get simple interest.
		double interestAmt = getSimpleInterest(capital, rate, years);
		
		
		
	}//doInterest()
	
	private double getSimpleInterest(double cap, double iRate, int yrs) {
		
		double amt = cap*(iRate/100)*yrs;
		
		return amt;
		
	}//getSimpleInterest()
	
	@Override
	public void start(Stage pStage) throws Exception {
		//Set the title
		pStage.setTitle("Interest Calculator V1.0.0");
		
		//Set the width and height
		pStage.setWidth(450);
		pStage.setHeight(300);
		
		//create layout
		GridPane gp = new GridPane();
		
		//Setting the Grid alignment 
		//Source: https://www.tutorialspoint.com/javafx/layout_gridpane.htm
		gp.setAlignment(Pos.CENTER); 
		
		//set the gap
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(10));
		//add components to layout
		gp.add(lblCapital, 1, 0);
		gp.add(txtfCapital, 2, 0);
		
		gp.add(lblIntRate, 1, 1);
		gp.add(txtfIntRate, 2, 1);
		
		gp.add(lblTerm, 1, 2);
		
		//add the term textfield and the dialog button to hbox
		HBox hbTerm = new HBox();
		hbTerm.getChildren().addAll(txtfTerm, btnDialog);
		hbTerm.setSpacing(10);
		
		gp.add(hbTerm, 2, 2);
		
		gp.add(chkAnalysis, 2, 3);
		gp.add(txtAnalysis, 0, 4, 3, 1);
		
		
		
		//Put the close and calculate buttons into hbox
		HBox hbButtons = new HBox();
		hbButtons.getChildren().addAll(btnClose, btnCalculate);
		
		//set the spacing for hbox
		hbButtons.setSpacing(10);
		
		//size the buttons
		btnClose.setMinWidth(70);
		btnCalculate.setMinWidth(70);
		
		//Manage the textfield size
		txtfIntRate.setMaxWidth(50);
		txtfTerm.setMaxWidth(50);
		
		
		gp.add(hbButtons, 2, 5);
		
		//Align the buttons
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		
		
		//create the scene
		Scene s = new Scene(gp);
		
		//set the scene
		pStage.setScene(s);
		//show the stage
		pStage.show();

	}//start()
	
	@Override
	public void stop() {
		
	}//stop()

	
	public static void main(String[] args) {
		// Launch the application
		launch();

	}//main()

}//class()
