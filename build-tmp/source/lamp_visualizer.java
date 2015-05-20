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

// TIME STUFF
long currentTime;
long previousTime;
int elapsedTime;
int minutes;
int seconds;
int hundredths;

// VISUAL FORMATTING STUFF
int left_margin;
int top_margin;
int gutter;
int leading;

int label_size;
int field_size;

// DATA STUFF
boolean sunBathing; // is the lamp in solar panel mode or not?
boolean personPresent; 

float lightLevel;
float kwFactor;
float kwLevel;
String timeCollected;
float moneyGenerated;


public void setup() {
	size(1200, 800);

	left_margin = 20;
	top_margin = 50;
	gutter = 300;
	leading = 50;

	label_size = 36;
	field_size = 36;

	sunBathing = false;
	personPresent = false;

	currentTime = millis();
	previousTime = currentTime;
	elapsedTime = PApplet.parseInt(currentTime - elapsedTime);
	timeCollected = "hai";

	// Initialize all the things
	lightLevel = 400;
	kwFactor = 0.065f;
	kwLevel = 0;
	moneyGenerated = 0;
}

public void draw() {
	background(235, 230, 230);

	// timer();
	checkStatus(); // check the solar panel mode status
	//checkPersonality(); // check the personality setting
	updateValues(); // create new "data"
	module_1(); // display our "data"
	module_2();
	module_3();	

}

public void timer(){
	currentTime = millis(); // update the current time, then run code

	seconds = PApplet.parseInt(currentTime / 1000);
    minutes = seconds / 60;
    seconds = seconds % 60;
    hundredths = PApplet.parseInt(currentTime / 10 % 100);

    // timeCollected) = ;
	
	previousTime = currentTime; // let time catch up
}

public void checkStatus(){
	if (keyPressed){
		if (key == 's'){
			sunBathing = !sunBathing;
		}
	}
}

public void updateValues(){

	float lightChange = random(-2,2);
	
	if (currentTime - previousTime > 300){

		lightLevel = lightLevel + lightChange;
		constrain(lightLevel, 10, 850);
		kwLevel = lightLevel * kwFactor;
		moneyGenerated = 0;
	}

}

public void module_1(){

	String[][] data_1 = {
		{"Light Level", str(lightLevel)}, // labels and fields, row by row
		{"Person Present", str(personPresent)},
		{"Light Level", str(lightLevel)},
		{"kW Generation", str(kwLevel)},
		{"Time Collected", timeCollected},
		{"Money Generated", str(moneyGenerated)}
	};

	fill(255, 20, 20, 80);
	noStroke();
	rect(0, 0, (width * 0.6f), (height * 0.5f));

	for (int i = 0; i < 2; i++){ 	// let's draw the table!
		for (int j=0; j < data_1.length; j++) {
			textSize(32);
			text(data_1[j][i], (left_margin + gutter*i), (top_margin + leading*j));
		}
	}

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
