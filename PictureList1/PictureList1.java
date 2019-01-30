
//Standard fx import
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Collections imports
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;


//File imports
import java.io.File;


//Image and imageviews
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

//Components in thus application
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

//import for layout
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//Support quitting the application
import javafx.application.Platform;

public class PictureList1 extends Application {

	//Declare visual components at class level
	Label lblPicFiles;
	
	Button btnNext , btnQuit;
	
	ListView<String>  lvPicFiles;
	
	Image img;
	ImageView imView;
	
	String nameAndPath;
	
	public PictureList1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		

		//Declare visual components at class level
		lblPicFiles = new Label("Pictures: ");
		
		btnNext = new Button("Next >") ;
		btnQuit = new Button("Quit");
		
		lvPicFiles = new ListView<String>();
	
		imView = new ImageView();
		
		//Handle event mouse
		lvPicFiles.setOnMouseClicked(ae ->{
			//first get the picture item that was clicked on
			String picPath;
			picPath = lvPicFiles.getSelectionModel().getSelectedItem().toString();
			
			//construct image with the picture file string 
			img = new Image ("./pics/"+picPath);
			
			//now show the picture in the image view
			imView.setImage(img);
		});
		
		//Handle events on the Next button show the next picture
		
		btnNext.setOnAction(ae ->{
			
			//check  to see if the current position is last in the listview
			if(lvPicFiles.getSelectionModel().getSelectedIndex()+1
			== lvPicFiles.getItems().size()) {
			
				lvPicFiles.getSelectionModel().selectFirst();
				//select the next picture in the image view
				//lvPicFiles.getSelectionModel().selectNext();
				String picPath;
				picPath = lvPicFiles.getSelectionModel().getSelectedItem().toString();
				
				//construct image with the picture file string 
				img = new Image ("./pics/"+picPath);
				
				//now show the picture in the image view 
				imView.setImage(img);
				
				
			}
			else {
				//Index of picture  in list view
				int  index = 0;
				//select the next picture in the image view
				lvPicFiles.getSelectionModel().selectNext();
				String picPath;
				picPath = lvPicFiles.getSelectionModel().getSelectedItem().toString();
				
				//construct image with the picture file string 
				img = new Image ("./pics/"+picPath);
				
				//now show the picture in the image view 
				imView.setImage(img);
				
			}
			
			
		});
		
	}//init()
	
	

	@Override
	public void start(Stage pStage) throws Exception {
		//Set the title
		pStage.setTitle("Picture List v.1.0.0");
		
		//Set the width and height
		pStage.setWidth(1000);
		pStage.setHeight(700);
		
		//create a layout
		GridPane gp = new GridPane();
		
		//Add components to the layput
		gp.add(lblPicFiles, 0, 0);
		
		gp.add(lvPicFiles, 0, 1);
		gp.add(imView, 1, 1);
		
		gp.add(btnNext, 0, 2);
		
		//configure padding and gap for gp
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(10));
		
		//Create a scene
		Scene s = new Scene(gp);
		
		//Set the scene
		pStage.setScene(s);
		
		//call to populate the list view with picture file string
		populateListView("./pics");
		
		//Show a default picture
		lvPicFiles.getSelectionModel().selectFirst();
		showPicture("./pics/"+ lvPicFiles.getSelectionModel().getSelectedItem().toString());
		//Show the stage
		pStage.show();
		
		
	}

	public void stop() {
		
	}// stop()
	
	//Method to show a picture in the image view
	private void showPicture(String picPath) {
		
		//Instantiate the image
		img = new Image(picPath.toString());
		
		//Set the image into the image view
		imView.setImage(img);
		
	}//showPicture()
	
	private void populateListView(String picDir) {
		//Picture are stored in the path ./pics
		
		//A string array to store picture files
		String[] fileList;
		
		//Create a File instance for file support 
		File f = new File(picDir);
		
		//Get a listing of picture files
		fileList = f.list();
		
		//Now add the picture files to the listview
		for (int i=0; i<fileList.length; i++) {
			
			lvPicFiles.getItems().add(fileList[i].toString());
			
		}//for
		
		
	}//populateListView()
	public static void main(String[] args) {
		//Launch the application
		launch();

	}//main()

}//class
