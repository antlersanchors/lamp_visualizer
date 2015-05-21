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
long startTimer;
long endTimer;
long totalTimeCollected;
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

PFont ostrich_32;
PFont proximaNova_32;
PFont proximaNovaSC_32;

// DATA STUFF
boolean sunBathing; // is the lamp in solar panel mode or not?
boolean personPresent; 

float lightLevel;
float kwFactor;
float kwLevel;
String timeCollected;
float moneyGenerated;
int earnRate;

int earnGraphWidth;
int earnGraphHeight;
int[] earnRateVals;


public void setup() {
	size(1200, 800);
	smooth();

	left_margin = 20;
	top_margin = 50;
	gutter = 300;
	leading = 50;

	label_size = 32;
	field_size = 32;

	sunBathing = false;
	personPresent = false;

	currentTime = millis();
	previousTime = currentTime;
	totalTimeCollected = 0;
	startTimer = 0;
	timeCollected = "";

	// Initialize all the things
	lightLevel = 400;
	kwFactor = 0.065f;
	kwLevel = 0;
	moneyGenerated = 0;

	earnGraphWidth = 300;
	earnGraphHeight = 200;
	earnRateVals = new int[earnGraphWidth];
	for (int i = 0; i < earnRateVals.length; i++) {
    	earnRateVals[i] = 0;
	}

	ostrich_32 = loadFont("OstrichSans-Medium-32.vlw");
	proximaNova_32 = loadFont("ProximaNova-Regular-32.vlw");
	proximaNovaSC_32 = loadFont("ProximaNovaS-Semibold-32.vlw");
}

public void draw() {
	background(235, 230, 230);

	currentTime = millis(); // update the current time, then run code
	timer();
	checkStatus(); // check the solar panel mode status
	//checkPersonality(); // check the personality setting
	updateValues(); // create new "data"
	module_1(); // display our "data"
	module_2();
	module_3();	

}

public void timer(){

	if (sunBathing){
		totalTimeCollected = (totalTimeCollected + (millis() - startTimer));
	}

	seconds = PApplet.parseInt(totalTimeCollected / 1000);
    minutes = seconds / 60;
    seconds = seconds % 60;
    hundredths = PApplet.parseInt(totalTimeCollected / 10 % 100);

    timeCollected = nf(minutes, 2, 0) + " : " + nf(seconds, 2, 0) + " : " + nf(hundredths, 2, 0);
	
}

public void checkStatus(){
	
}

public void keyReleased() {
	if (key == 's'){
		sunBathing = !sunBathing;
	}

	if (sunBathing){
		startTimer = millis();
	} else {

	}
}

public void updateValues(){

	float lightChange = random(-10,10);
	lightLevel = lightLevel + lightChange;
	constrain(lightLevel, 10, 850);
	nf(lightLevel, 0, 2);
	
	if (currentTime - previousTime > 300){
		if (sunBathing){
			kwLevel = lightLevel * kwFactor;
			nf(kwLevel, 0, 1);
			moneyGenerated = 0;

		} else {

		}
		previousTime = currentTime; // let time catch up
	}

}

public void module_1(){
	noStroke();
	if (sunBathing){	
		fill(255, 20, 20, 80);
		rect(0, 0, (width * 0.6f), (height * 0.5f));

		fill(80, 80, 90);
		textFont(proximaNovaSC_32, label_size);
		text("solar collection: active", left_margin, 10);

	} else {
		fill(255, 20, 20, 10);
		rect(0, 0, (width * 0.6f), (height * 0.5f));
		fill(80, 80, 90);
		textFont(proximaNovaSC_32, label_size);
		text("solar collection: inactive", left_margin, 10);
	}


	String[][] data_1 = {
		{"Light Level", str(lightLevel)}, // labels and fields, row by row
		{"Person Present", str(personPresent)},
		{"kW Generation", str(kwLevel)},
		{"Time Collected", timeCollected},
		{"Money Generated", str(moneyGenerated)}
	};

	for (int i = 0; i < 2; i++){ 	// let's draw the table!
		for (int j=0; j < data_1.length; j++) {
			fill(80, 80, 90);
			textFont(ostrich_32, label_size);
			text(data_1[j][i], (left_margin + gutter*i), (top_margin + leading*j));
		}
	}

	fill(220);
	rect(left_margin, 350, 300, 20);
	int barVal = PApplet.parseInt(map(lightLevel, 10, 850, 0, 300));
	fill(85);
	rect(left_margin, 350, barVal, 20);

}

public void module_2(){
	fill(20, 20, 255, 80);
	noStroke();
	rect(0, (0 + height * 0.5f), (width * 0.6f), (height * 0.5f));

	// for (int i = 0; i < vals.length; i++) {
	//     vals[i] = random(height);
	// }

	noFill();
	stroke(200);
	rect(left_margin, (0 + height * 0.5f + 100), earnGraphWidth, earnGraphHeight);

	for (int i = 0; i < earnRateVals.length-1; i++) {
		stroke(0);
		strokeWeight(2);
		pushMatrix();
		translate(left_margin, 0 + height * 0.5f + 100);
		line(i,earnRateVals[i],i+1,earnRateVals[i+1]);
		popMatrix();
	}
		  
	if(millis() % 50 == 0){
		// Slide everything down in the array
		for (int i = 0; i < earnRateVals.length-1; i++) {
		earnRateVals[i] = earnRateVals[i+1]; 
		}
		// Add a new random value
		earnRateVals[earnRateVals.length-1] = PApplet.parseInt(random(0,earnGraphHeight));
	}
}

public void module_3(){
	fill(20, 255, 20, 80);
	noStroke();
	rect((width * 0.6f), 0, (width * 0.4f), height);

	String[][] data_3 = {
		{"Things I'm Saving For", ""}, // labels and fields, row by row
		{"Solar Array", " 92%"},
		{"High Efficiency Bulb", "47%"},
		
	};

	for (int i = 0; i < 2; i++){ 	// let's draw the table!
		for (int j=0; j < data_3.length; j++) {
			fill(80, 80, 90);
			textFont(ostrich_32, label_size);
			text(data_3[j][i], (left_margin + gutter*i + (width * 0.6f)), (top_margin + leading*j));
		}
	}

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
