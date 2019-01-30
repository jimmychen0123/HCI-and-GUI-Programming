//Standard javafx imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

//Imports for components in this application
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;

//Import for layout
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * @author 2971978 Yen Lung Chen(Jimmy) HDC_HGP Assignment 1
 *
 */
public class FXInsurance extends Application {
	
	//Components declared at class scope
	Button btnShowDialog;
	
	//Labels
	Label lblHead, lblShowValue, lblShowOption, lblShowCost,
	lblValue, lblOption, lblCost;
	
	//Components in the dialog 
	Label lblDgpValue, lblDgpOption;
	Button btnCancel, btnOk;
	TextField homeValue;
	CheckBox c;
	
	public FXInsurance() {
		
	}

	@Override
	public void init() {
		
		lblHead = new Label("Insurance details: ");
		//source: https://docs.oracle.com/javafx/2/text/jfxpub-text.htm
		lblHead.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		
		lblValue = new Label("Home Value: ");
		lblShowValue = new Label("");
		
		lblOption = new Label("Insurance option selected: ");
		lblShowOption = new Label();
		
		lblCost = new Label("Total Cost: ");
		lblShowCost = new Label();
		
		btnShowDialog = new Button("Insurance Quote");
		
		//Handle events on the Button
		btnShowDialog.setOnAction(ae -> showDialog());
			
	}//init()
	
	private void showDialog() {
		
		//create stage
		Stage dlgStage = new Stage();
		
		//set the title
		dlgStage.setTitle("Enter Insurance Quote Details");
		
		//set the width and height
		dlgStage.setWidth(500);
		dlgStage.setHeight(300);
		dlgStage.setResizable(false);
		
		//layout in dialog, main container grid pane
		GridPane dgp = new GridPane();
		dgp.setGridLinesVisible(false);
		VBox vb = new VBox();
		HBox hb = new HBox();
	
		// create a checkbox, resource: https://www.geeksforgeeks.org/javafx-checkbox/
        CheckBox a = new CheckBox("Home emergency cover"); 
        CheckBox b = new CheckBox("Legal expense cover");
        CheckBox c = new CheckBox("Personal injury cover");
        //add the check box to Vbox
        vb.getChildren().addAll(a,b,c); 
        
        //spacing the elements in vb
        vb.setSpacing(10);
		
		//Labels to identify the controls
		Label lblDgpValue = new Label("Home Value: ");
		Label lblDgpOption = new Label("Options: ");
		
		//TextField
		TextField txtfValue = new TextField();
		
		//The ok and cancel buttons
		Button btnCancel = new Button("Cancel"); 
		Button btnOk = new Button("Ok");
		
		//add the check box to hbox
		hb.getChildren().addAll(btnOk, btnCancel);
		
		//spacing the elements in hb
		hb.setSpacing(10);
		
		//size the buttons
		btnOk.setMinWidth(60);
		btnCancel.setMinWidth(60);
		
		
		//handle event of the cancel button
		btnCancel.setOnAction(ae -> dlgStage.close());
		
		//handle event of the ok button
		btnOk.setOnAction(ae ->{
			if(txtfValue.getText().isEmpty()) {
				//Alert the user that the text field is empty
				new Alert(AlertType.ERROR,
						"Home Value field is empty. Please enter a value").showAndWait();
				
			}//if textfield is empty
			
			double homeValue = Double.parseDouble(txtfValue.getText());
			double totalCost = 0;
			double basic;
			StringBuilder s = new StringBuilder();
			
			if(homeValue > 0) {
				//Show the total cost in the maininterface label
				lblShowValue.setText(""+homeValue);
				basic = homeValue * 0.002;
				totalCost = basic;
				
				if(a.isSelected()) {
					totalCost += basic * 0.15;
					s.append("-Home Emergency Cover"+"\n");
				}//if a 
				
				if(b.isSelected()) {
					totalCost += basic * 0.1;
					s.append("-Legal Expense Cover"+"\n");
				}//if b
				
				if(c.isSelected()) {
					totalCost += basic * 0.1;
					s.append("-Personal Injury Cover "+"\n");
				}//if c	
				
				lblShowCost.setText(""+totalCost);
				lblShowOption.setText(""+s);
				
				//final close the dialog
				dlgStage.close();
				
			}//if home value > 0
			
			else {
				new Alert(AlertType.ERROR,"Invalid Value");
			}
			
		});//btnOK setOnAction

			
		//set the padding and gaps
		dgp.setPadding(new Insets(10));
		dgp.setHgap(10);
		dgp.setVgap(10);
		
		
	
		//Setting the Grid alignment 
		//Source: https://www.tutorialspoint.com/javafx/layout_gridpane.htm
	    dgp.setAlignment(Pos.CENTER); 
		
		//add the components to the grid pane
		dgp.add(lblDgpValue, 0, 0);
		dgp.add(txtfValue, 1,  0);
		
		dgp.add(lblDgpOption, 0, 1);
		dgp.add(vb, 1, 1);
		
		dgp.add(hb, 2, 2);
		
		//create scene
		Scene ds = new Scene(dgp);
		//set the scene
		dlgStage.setScene(ds);
		
		//Apply s style to the scene using a style sheet
		ds.getStylesheets().add("style_insurance.css");
		
		//show the stage
		dlgStage.show();
		
		
		
	}//showDialog()
	
	@Override
	public void start(Stage pStage) throws Exception {
		
		//set the title
		pStage.setTitle("FXInsurance V1.0.0");
		
		//set the width and height
		pStage.setWidth(600);
		pStage.setHeight(300);
		
		pStage.setResizable(false);
		
		//create a layout
		GridPane gp = new GridPane();
		gp.setPrefSize(400, 300);
		gp.setGridLinesVisible(false);
		
		HBox hb = new HBox();
		
		
		
		//Add the button to hbox
		hb.getChildren().add(btnShowDialog);
		
		//Configure padding and spacing
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);
		
		//Setting the Grid alignment 
		//Source: https://www.tutorialspoint.com/javafx/layout_gridpane.htm
	    gp.setAlignment(Pos.CENTER); 
	    
		//Add components to the layout
		gp.add(lblHead, 0, 0);
		
		gp.add(lblValue, 0, 1);
		gp.add(lblShowValue, 1, 1);
		
		gp.add(lblOption, 0, 2);
		gp.add(lblShowOption, 1, 2);
		
		gp.add(lblCost, 0, 3);
		gp.add(lblShowCost, 1, 3);
		
		gp.add(hb, 2, 4);
		
		//create a scene
		Scene s = new Scene(gp);
		
		//Set the scene
		pStage.setScene(s);
		
		//Apply s style to the scene using a style sheet
		s.getStylesheets().add("style_insurance.css");
		
		//Show the stage
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
