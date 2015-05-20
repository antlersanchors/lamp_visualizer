import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class lamp_visualizer extends PApplet {

boolean sunBathing; // is the lamp in solar panel mode or not?

int margin;
int label_size;
int field_size;

public void setup() {
	size(1200, 800);

	margin = 20;
	label_size = 36;
	field_size = 36;

	sunBathing = false;
}

public void draw() {
	background(235, 230, 230);

	//checkStatus(); // check the solar panel mode status
	//checkPersonality(); // check the personality setting

	module_1(); // display our "data"
	module_2();
	module_3();	
}

public void module_1(){

	int lightLevel = 250;
	int kwLevel = 60;
	int timeCollected = 100;
	int moneyGenerated = 40;

	String[][] data_1 = new String[0][0];



	fill(255, 20, 20, 80);
	noStroke();
	rect(0, 0, (width * 0.6f), (height * 0.5f));

	textSize(32);
	fill(5);
	text("Light Level", 0, (10 + 0));
	text("kW Generation", 0, (10 + 20));
	text("Time Collected", 0, (10 + 40));
	text("Money Generated", 0, (10 + 60));

}

public void module_2(){
	fill(20, 20, 255, 80);
	noStroke();
	rect(0, (0 + height * 0.5f), (width * 0.6f), (height * 0.5f));

}

public void module_3(){
	fill(20, 255, 20, 80);
	noStroke();
	rect((width * 0.6f), 0, (width * 0.4f), height);

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lamp_visualizer" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
