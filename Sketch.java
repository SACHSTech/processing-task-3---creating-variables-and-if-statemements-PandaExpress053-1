import processing.core.PApplet;
import java.util.Random;

public class Sketch extends PApplet {
  /**
   * The program Sketch.java randomizes the position of the generated images from the past lesson
   * (a house, sun, and foreground), and changes the objects' based on the quadrant of the new
   * position. It will also output the current date and time, updating each frame. It also changes
   * the background colour of the sketch based on the time (ex. if the minute value is even, the 
   * background will turn green)
   * @author: E.Fung
   */
  
	// Initialize Variables and Random Function
	Random myRandom = new Random();
  int [][] intRandomXYVals = new int [3][2];
    
  public void settings() {
	  // put your size call here
    size(400, 400);
    
    // Create and store random numbers
    for (int i = 0; i < intRandomXYVals.length; i++){
      for (int j = 0; j < intRandomXYVals[0].length; j++){
        intRandomXYVals[i][j] = myRandom.nextInt(400) - 200;
      }  
    }
  }

  public void setup() {    
    //Setting up initial stroke weight and background colour
    strokeWeight(2);
    background(255);
  }

  public void draw() {

    //Must change background colour in order to reset canvas to show new analog clock time
    //Changing background colour based on the time 
    if ((int) second() % 2 == 0 || (int) minute() % 2 == 0){
      background(100, 150, 35);
    }
    else if ((int) second() % 5 == 0 && (int) minute() % 3 != 0){
      background(30, 150, 100);
    }
    else{
      background(255);
    }

    // Randomizing position of foreground and changing colour based on delta movement in each quadrant  
    QuadrantToColour(intRandomXYVals[0][0] + 100, intRandomXYVals[0][1], 0);
    rect(intRandomXYVals[0][0], 150 + intRandomXYVals[0][1], 400, 100);

    // Randomizing position of house body and changing colour based on delta movement in each quadrant
    QuadrantToColour(intRandomXYVals[1][0], intRandomXYVals[1][1], 1);
    rect(150 + intRandomXYVals[1][0], 175 + intRandomXYVals[1][1], 100, 100);

    // Randomizing position of triangle roof 
    triangle(150 + intRandomXYVals[1][0], 175 + intRandomXYVals[1][1], 200 + intRandomXYVals[1][0], 125 + intRandomXYVals[1][1], 250 + intRandomXYVals[1][0], 175 + intRandomXYVals[1][1]);

    // Randomizing position of sun and changing colour based on delta movement in each quadrant
    QuadrantToColour(intRandomXYVals[2][0], intRandomXYVals[2][1], 2);
    ellipse(200 + intRandomXYVals[2][0], 200 + intRandomXYVals[2][1], 50, 50);

    // Printing the current date and current time, updated each frame
    fill(0);
    textSize(20);
    text("Current Date: " + day() + "/" + month() + "/" + year(), 10, 28);
    text("Current Time: " + hour() + ":" + minute() + ":" + second(), 10, 50);
  }

  /**
   * Looks at the delta position (between original and randomized)
   * and changes colour of object based on which quadrant it is moving towards
   * @param intDeltaX
   * @param intDeltaY
   * @param intObjectNum
   */
  public void QuadrantToColour(int intDeltaX, int intDeltaY, int intObjectNum){
    
    if (intDeltaX * intDeltaY < 0){
      if (intDeltaX > 0 && intDeltaY < 0){
        fill(255, 255, 255);
      }
      else if (intDeltaX < 0 && intDeltaY > 0){
        fill(0, 255, 0);
      }
    }
    else if (intDeltaX * intDeltaY > 0){
      if (intDeltaX > 0 && intDeltaY > 0){
        fill(255, 0, 0);
      }
      else if (intDeltaX < 0 && intDeltaY < 0){
        fill(0, 100, 150);
      }
    }
    else{
      fill(0, 0, 0);
    }
  }
}