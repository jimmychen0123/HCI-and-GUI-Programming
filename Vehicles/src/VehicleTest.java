//Standard import
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Import for components 
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;

//Imports for layout
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;


public class VehicleTest extends Application {
	
	//Declare a car and truck with class scope
	Car c;
	Truck t;
	
	//Declare components at the class level
	Label lblRegNum, lblMake, lblModel,lblColour, lblType;
	
	//Textfields
	TextField txtfRegNum, txtfMake, txtfModel, txtfColour;
	
	//Radiobuttons
	RadioButton rdoCar,rdoTruck;
	
	//Toggle group
	ToggleGroup tgCarTruck;
	
	//combo box for vehicle colour
	ComboBox cmbColour;
	
	//text area to show a class instance's attribute
	TextArea txtDisplay;
	
	//Button for display
	Button btnCreate, btnShowDetails, btnQuit;
	
	
	
	

	public VehicleTest() {
		// TODO Auto-generated constructor stub
	}//constructor
	
	@Override
	public void init() {
		
		//instantiate components
		
		lblRegNum = new Label("Reg Num:");
		lblMake = new Label("Make: ");
		lblModel = new Label("Model: ");
		lblColour  = new Label("Colour: ");
		lblType = new Label("Vehicle Type");
		
		//Textfields
		txtfRegNum = new TextField();
		txtfMake = new TextField();
		txtfModel = new TextField();
		txtfColour = new TextField();
		
		//Radiobuttons
		rdoCar = new RadioButton("Car");
		rdoTruck = new RadioButton("Truck");
		
		//Toggle group
		tgCarTruck = new ToggleGroup();
		
		//put radio button into toggle group
		rdoCar.setToggleGroup(tgCarTruck);
		rdoTruck.setToggleGroup(tgCarTruck);
		
		//combo box for vehicle colour
		cmbColour = new ComboBox();
		
		//Populate 
		cmbColour.getItems().add("Blue");
		cmbColour.getItems().add("Green");
		cmbColour.getItems().add("Red");
		cmbColour.getItems().add("Black");
		cmbColour.getItems().add("Yellow");
		
		//put the first colour on display
		cmbColour.getSelectionModel().selectFirst();
		
		//text area to show a class instance's attribute
		txtDisplay = new TextArea();
		
		//Button
		btnCreate = new Button("Create");
		btnCreate.setMinWidth(70);
		
		btnShowDetails = new Button("Show Details: ");
		btnShowDetails.setMinWidth(70);
		
		btnQuit = new Button("Quit");
		btnQuit.setMinWidth(70);
		
		//Handle events on the create button
		btnCreate.setOnAction(ae ->{
			
			//check to see what type to create 
			if(rdoCar.isSelected()) {
				//create a car
				c = new Car(txtfRegNum.getText());
				c.setMake(txtfMake.getText());
				c.setModel(txtfModel.getText());
				c.setColour(cmbColour.getSelectionModel().getSelectedItem().toString());
				
			}//if
			
			else {
				//create a truck
				t = new Truck();
				t = new Truck(txtfRegNum.getText());
				t.setMake(txtfMake.getText());
				t.setModel(txtfModel.getText());
			}//else
		});//setOnAction
		
		//Handle events on btnShowDetails
		btnShowDetails.setOnAction(ae -> {
			//Display the instance details in the text area
			//Check to see what type to create 
			if(rdoCar.isSelected()) {
				//create a car
				txtDisplay.setText("Vehicle Details: \n"
						+"Registration Number:"+c.getReg()+"\n"+
						"Make: "+c.getMake()+"\n"+"Model: "+c.getModel()+"\n"
						+"Colour: "+c.getColour()+"\n");
				
			}//if
			
			else {
				//create a truck
				txtDisplay.setText("Vehicle Details: \n"
						+"Registration Number:"+t.getReg()+"\n"+
						"Make: "+t.getMake()+"\n"+"Model: "+t.getModel()+"\n"
						+"Colour: "+t.getColour()+"\n");
				
			}//else
			
		});
		
		
		
		
	}//init()
	

	@Override
	public void start(Stage pStage) throws Exception {
		
		//Set the title
		pStage.setTitle("Vehicle");
		//Set the width and height
		pStage.setWidth(450);
		pStage.setHeight(420);
		//create layout
		GridPane gp = new GridPane();
		//add components to the layout
		gp.add(lblType, 0, 0);
		gp.add(rdoCar, 1, 0);
		gp.add(rdoTruck, 2, 0);
		
		gp.add(lblRegNum, 0, 1);
		gp.add(txtfRegNum, 1, 1);
		
		gp.add(lblMake, 0, 2);
		gp.add(txtfMake, 1, 2);
		
		gp.add(lblModel, 0, 3);
		gp.add(txtfModel, 1, 3);
		
		gp.add(lblColour, 0, 4);
		gp.add(cmbColour, 1, 4);
		
		gp.add(btnCreate, 0, 5);
		
		gp.add(txtDisplay, 0, 6, 4, 1);
		
		//button below text area
		gp.add(btnShowDetails, 2, 7);
		gp.add(btnQuit, 3, 7);
		
		//Set padding and gaps for the gp
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);
		
		
		//create scene
		Scene s = new Scene(gp);
		//set the scene
		pStage.setScene(s);
		//show the stage
		pStage.show();
		

	}
	

	@Override
	public void stop() {
		
	}//stop()
	
	public static void main(String[] args) {
		
		//launch the program
		launch();
		
	}//main

}//class()
