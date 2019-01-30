//Standard java imports
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.stage.Stage;
import javafx.scene.Scene;

//imports for layout
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//components imports
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
//Imports for media
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
//Time measurement
import javafx.util.Duration;

//import for files
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

//media import 
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class MusicPlayer1 extends Application {

	private static final String lvAvaliableTrack = null;

	//Declare controls at class level
	//Label 
	Label lblAvailableTrack, lblSelectedTrack, lblVolume, lblStatus, lblPlayStatus;
	
	//List views
	ListView lvAvailableTrack, lvSelectedTrack;
	
	//Buttons
	Button btnAdd, btnRemove, btnRemoveAll, btnPlay, btnPause, btnStop;
	
	//Slider
	Slider slVolume, slPlayStatus;

	//Media
	Media media;
	MediaPlayer mpPlayer;
	/*
	 * Stat is used to track current status of player. 
     * The initial stat 0 indicates the player was either stopped or no song has been played
     * Stat = 1 indicates the current song was paused and clicking on the play button resumes the song
	 * A better class to use to check status of player is the javafx MediaPlayer.Status. But due to limited
     * time we were unable to use it, because the entire playSong method and event on action for play/pause
     * and stop had to change.
	 */
	int stat = 0; 
	
	//Duration
    Duration duration;
	
	
	public MusicPlayer1() {
		
	}//constructor
	
	@Override
	public void init() {
		
		//instantiate components
		
		//Labels
		lblAvailableTrack = new Label("Available Track");
		lblSelectedTrack = new Label("Selected Tracks"); 
		lblVolume = new Label("Volume");
		lblStatus = new Label("Status"); 
		
		//Buttons
		btnAdd = new Button("Add >");
		btnRemove = new Button("< Remove"); 
		btnRemoveAll = new Button("<< Remove All"); 
		//Button Play will be changed to Pause as a function as well  
		//ImageView to create a button with an icon
		btnPlay = new Button("Play",new ImageView("./icons8-play-filled-50.png"));
		btnStop = new Button("Stop", new ImageView("./icons8-stop-50.png"));
		
		//Set button max width 
		btnAdd.setMaxWidth(130);
		btnRemove.setMaxWidth(130);
		btnRemoveAll.setMaxWidth(130);
		btnPlay.setMaxWidth(130);
		btnStop.setMaxWidth(130);
		
		//List views
		lvAvailableTrack = new ListView<String>();
		lvSelectedTrack = new ListView<String>();
		
		//Slider
		slVolume = new Slider();
		slPlayStatus = new Slider();
		
		//Set btnAdd on action
		btnAdd.setOnAction(ae -> {
			//getSelectionModel returns the currently installed selection model.
			//getSelectedItem returns a read-only ObservableList of all selected items.  
			//if available track list has items
			if(lvAvailableTrack.getSelectionModel().getSelectedItem()!=null) {
				
				//Add the selected item from available track list to selected list
				lvSelectedTrack.getItems().add(lvAvailableTrack.getSelectionModel().getSelectedItem());
				//Remove the selected item from available track list
				lvAvailableTrack.getItems().remove(lvAvailableTrack.getSelectionModel().getSelectedIndex());
				
			}//if
			
		});//btnAdd set on action
		
		
		
		//Set btnRemove on action
		btnRemove.setOnAction(ae -> {
			//if selected track list has items
			if(lvSelectedTrack.getSelectionModel().getSelectedItem()!=null) {
				//Add the selected item from selected track list to available list
				lvAvailableTrack.getItems().add(lvSelectedTrack.getSelectionModel().getSelectedItem());
				//Remove the selected item from selected track list
				lvSelectedTrack.getItems().remove(lvSelectedTrack.getSelectionModel().getSelectedItem());
				
			}//if
			
		});//btnRemove set on action
		

		//Set btnRemove on action
		btnRemoveAll.setOnAction(ae ->{
			//if selected track list is not empty
			if(!(lvSelectedTrack.getItems().isEmpty())) {
				//add all the items in selected track list to available track list
				lvAvailableTrack.getItems().addAll(lvSelectedTrack.getItems());
				//remove all the items in selected track list
				lvSelectedTrack.getItems().removeAll(lvSelectedTrack.getItems());
				
			}//if
			
		});//btnRemoveAll set on action
		
		
		//Handle event btnPlay
		btnPlay.setOnAction(ae -> {
			//if selected track has items
			if(lvSelectedTrack.getSelectionModel().getSelectedItem()!=null) {
				//if button shows pause
				if(btnPlay.getText()=="Pause") {
					//MediaPlayer pause the song
					mpPlayer.pause();
					//change button text to play
					btnPlay.setText("Play");
					//change button icon to play 
					btnPlay.setGraphic(new ImageView("./icons8-play-filled-50.png"));
					//stat = 1 indicate song played is paused
					stat = 1;
					
				}//if
				
				else if(btnPlay.getText()=="Play"){ //if button shows play
					//change button text to pause
					btnPlay.setText("Pause");
					//change button icon to pause
					btnPlay.setGraphic(new ImageView("./icons8-pause-50.png"));
					//if stat = 1 indicate song played is paused
					if(stat == 1) {
						//Resume play
						mpPlayer.play();
						
					}//if
					
					else {
						//play the selected item in selected track list
						playMusic("./songs/"+lvSelectedTrack.getSelectionModel().getSelectedItem().toString());
					}//else
					
				}//else if
					
			}//if
			
			else if (btnPlay.getText() == "Pause" ) { // this else if block makes sure that play/pause button still works even if an
                // item is not currently selected but a song is already playing and resent the status to 0.
				mpPlayer.pause();
				btnPlay.setText("Play");
				btnPlay.setGraphic(new ImageView("./play.png"));
				stat = 0;
			}
			
		});//btnPlay
		
		//Handle events btnStop
		btnStop.setOnAction(ae -> {
			if (lvSelectedTrack.getSelectionModel().getSelectedItem() != null) {
				//Stop playing song
				mpPlayer.stop();
				//stat = 0 indicate play a selected song from beginning
				stat = 0;
				//change button text to play
				btnPlay.setText("Play");
				//change button icon to play
				btnPlay.setGraphic(new ImageView("./icons8-play-filled-50.png"));
			}
			
			else if (btnPlay.getText() == "Pause" ) {// this else if block makes sure that stop button still works even if an
                // item is not currently selected but a song is already play.
				mpPlayer.stop();
				stat = 0;
				btnPlay.setText("Play");
				btnPlay.setGraphic(new ImageView("./play.png"));
			}
			
		});//btnStop
		
		
	}//init()
	
	private void playMusic(String trackToPlay) {
		
		final URL resource = getClass().getResource(trackToPlay);
		
		//instantiate media with name of track to play
		media = new Media(resource.toString());
		mpPlayer = new MediaPlayer(media);
		mpPlayer.play();
		
		//Slide bar
		slVolume.setValue(mpPlayer.getVolume()*100);
		 /*
         * the volume of the player is tracked by adding a listener and 
         * the current value invalidated each time the user move the slide
         * and the current value is set at play volume.
         */
		slVolume.valueProperty().addListener(new InvalidationListener() {
			@Override
		    public void invalidated(javafx.beans.Observable observable) {
				
				mpPlayer.setVolume(slVolume.getValue()/100);
		       
		    }//invalidated
		});
		
		/*
         * Play status slider/time
         * The slider maximum length is set to the length of track in seconds.
         * the slider pointer is set to the newTime value observed by the listener.
         */
		
		mpPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            // set max length of status slider to length of track
            slPlayStatus.setMax(mpPlayer.getTotalDuration().toSeconds());
 
            if (!slPlayStatus.isValueChanging()) {
                // slider
                slPlayStatus.setValue(newTime.toSeconds());
                // timer
                lblStatus.setText(String.format("Status: " + "%4d:%02d:%04.1f", (int) newTime.toHours(),
                        (int) (newTime.toMinutes() % 60), (newTime.toSeconds() % 60)));
            }
        });
 
        // seek when pointer on slider is dragged and start reading from that point
        slPlayStatus.setOnMouseClicked(ae -> {
            mpPlayer.seek(Duration.seconds(slPlayStatus.getValue()));
        });
		
	}//playMusic
	
	private void readMp3(String dirName) {
		
		//read songs from folder
		//https://docs.oracle.com/javase/7/docs/api/java/io/File.html
		File file = new File(dirName);
		File[] files = file.listFiles();
		for(File f: files) {
			//populate the file name to available track list
			lvAvailableTrack.getItems().add(f.getName());
			
		}//for
	}//read Mp3
	
	@Override
	public void start(Stage pStage) throws Exception {
		
		//set the title
		pStage.setTitle("Music Player V1.0.0");	
				
		//set the width and height
		pStage.setWidth(700);
		pStage.setHeight(650);
		pStage.setResizable(true);
		
		//create a layout
		GridPane gp = new GridPane();
		
		//Gridpane spacing and gaps
		gp.setPadding(new Insets(25));
		gp.setVgap(20);
		gp.setHgap(20);
		gp.setAlignment(Pos.CENTER); 
		gp.setGridLinesVisible(false);
		
		//Add components to GridPane
		gp.add(lblAvailableTrack, 0, 0);
		gp.add(lblSelectedTrack, 2, 0);
		
		gp.add(lvAvailableTrack, 0, 1);
		gp.add(lvSelectedTrack, 2, 1);
		
		gp.add(lblStatus, 2, 4);
		gp.add(slPlayStatus, 2, 5);
		
		//Add button to Vbox
		VBox vbBtns = new VBox();
		vbBtns.getChildren().addAll(btnAdd, btnRemove, btnRemoveAll, btnPlay, btnStop, lblVolume, slVolume);
		
		//Disable the btnAdd, btnRemove, btnRemoveAll
		btnAdd.setDisable(true);
		btnRemove.setDisable(true);
		btnRemoveAll.setDisable(true);
		btnPlay.setDisable(true);
		btnStop.setDisable(true);
		
		/*
		 * disableProperty() indicates whether or not this Node is disabled. 
		 * A Node will become disabled if disable is set to true on either itself or one of its ancestors in the scene graph.
		 * create a binding for this Property.
		 */
		
		btnAdd.disableProperty().bind(lvAvailableTrack.getSelectionModel().selectedItemProperty().isNull());
		btnRemove.disableProperty().bind(lvSelectedTrack.getSelectionModel().selectedItemProperty().isNull());
		//btnRemoveAll.disableProperty().bind(lvSelectedTrack.getSelectionModel().selectedItemProperty().isNull());
		btnPlay.disableProperty().bind(lvSelectedTrack.getSelectionModel().selectedItemProperty().isNull());
		btnStop.disableProperty().bind(lvSelectedTrack.getSelectionModel().selectedItemProperty().isNull());
		//Once there is an item in selected list, button will be enabled
		btnRemoveAll.disableProperty().bind(Bindings.isEmpty(lvSelectedTrack.getItems()));

		//set spacing in VBox
		vbBtns.setSpacing(10);
		
		//add VBox to grid pane
		gp.add(vbBtns, 1, 1);
		
		//create a scene
		Scene s = new Scene(gp);
		
		//Apply s style to the scene using a style sheet
		s.getStylesheets().add("style_player.css");
		
		//populate list view
		readMp3("./songs");
		
		//set the scene
		pStage.setScene(s);
		
		//show the stage
		pStage.show();
		
	}//start
	
	@Override
	public void stop() {
		
	}//stop()
	
	public static void main(String[] args) {
		
		//Launch the program
		launch();
	}//main

}//class()
