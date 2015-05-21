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

int defaultGraphWidth;
int defaultLineGraphHeight;
int defaultBarGraphHeight;

// GRAPH STUFF
LineGraph earningsGraph;
BarGraph_Horizontal lightGraph;

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

	// initialize all the things
	lightLevel = 400;
	kwFactor = 0.065f;
	kwLevel = 0;
	moneyGenerated = 0;

	defaultGraphWidth = 300;
	defaultLineGraphHeight = 200;
	defaultBarGraphHeight = 20;

	ostrich_32 = loadFont("OstrichSans-Medium-32.vlw");
	proximaNova_32 = loadFont("ProximaNova-Regular-32.vlw");
	proximaNovaSC_32 = loadFont("ProximaNovaS-Semibold-32.vlw");

	// initialize graphs
	earningsGraph = new LineGraph(left_margin, PApplet.parseInt(0 + height * 0.5f + 100), defaultGraphWidth, defaultLineGraphHeight, "Billions Earned");
	lightGraph = new BarGraph_Horizontal(left_margin, 325, defaultGraphWidth, defaultBarGraphHeight);
}

public void draw() {
	background(235, 230, 230);

	currentTime = millis(); // update the current time, then run code
	timer();
	//checkStatus(); // check the solar panel mode status
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
		text("solar collection: active", left_margin, top_margin);

	} else {
		fill(255, 20, 20, 10);
		rect(0, 0, (width * 0.6f), (height * 0.5f));
		fill(80, 80, 90);
		textFont(proximaNovaSC_32, label_size);
		text("solar collection: inactive", left_margin, top_margin);
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
			pushMatrix();
			translate(0, 50);
			text(data_1[j][i], (left_margin + gutter*i), (top_margin + leading*j));
			popMatrix();
		}
	}
	
	int lightVal = PApplet.parseInt(map(lightLevel, 10, 850, 0, 300));
	lightGraph.display(lightVal);

}

public void module_2(){
	fill(20, 20, 255, 80);
	noStroke();
	rect(0, (0 + height * 0.5f), (width * 0.6f), (height * 0.5f));

	earningsGraph.display();
	
}

public void module_3(){
	fill(20, 255, 20, 80);
	noStroke();
	rect((width * 0.6f), 0, (width * 0.4f), height);

	String[][] data_3 = {
		{"Things I'm Saving For", ""}, // labels and fields, row by row
		{"Solar Array", " 92%"},
		{"High Efficiency Bulb", "47%"},
		{"", ""},

		{"Stocks I'm Investing In", ""}, 
		{"LumberWorld", " blah foo bar"},
		{"CIID Inc.", " foo blah bar"},
		{"", ""},

		{"How's My Stock Doing?", ""}, 
		{"lampBot Inc.", " $15.02"},
		{"", ""},
		
		{"Bots That Are My Friends", ""},
		{"augmentedToaster2014", ""},
		{"xxxINTERNET_FRIDGExxx", ""}
		
	};

	tableRender(PApplet.parseInt(width * 0.6f), top_margin, data_3);

	// for (int i = 0; i < 2; i++){ 	// let's draw the table!
	// 	for (int j=0; j < data_3.length; j++) {
	// 		fill(80, 80, 90);
	// 		textFont(ostrich_32, label_size);
	// 		text(data_3[j][i], (left_margin + gutter*i + (width * 0.6)), (top_margin + leading*j));
	// 	}
	// }
}

public void tableRender(int tempX, int tempY, String[][] tempText){
	int x = tempX;
	int y = tempY;
	String[][] theText = tempText;

	for (int i = 0; i < 2; i++){ 	// let's draw the table!
		for (int j=0; j < theText.length; j++) {
			fill(80, 80, 90);
			textFont(ostrich_32, label_size);
			text(theText[j][i], (x + left_margin + gutter*i), (y + leading*j));
		}
	}

}
// for to display beautiful fake data
class BarGraph_Horizontal {
	int x;
	int y;
	int w;
	int h;

	int v;

	BarGraph_Horizontal(int tempX, int tempY, int tempW, int tempH){
		x = tempX;
		y = tempY;
		w = tempW;
		h = tempH;

		v = 0;

	}

	public void display(int tempV) {
		v = tempV;

		fill(220);
		rect(x, y, w, h);

		fill(85);
		rect(x, y, v, h);

	}
}
// for to display beautiful fake data
class LineGraph {
	int x;
	int y;
	int w;
	int h;
	String title;
	int titleLeading;

	int[] graphVals;
	int updateModulo;

	LineGraph(int tempX, int tempY, int tempW, int tempH, String tempT){
		x = tempX;
		y = tempY;
		w = tempW;
		h = tempH;
		title = tempT;
		titleLeading = leading; // separation between title and graph
		updateModulo = 50; // how fast the graph moves

		graphVals = new int[w];
		for (int i = 0; i < graphVals.length; i++) {
	    	graphVals[i] = 0;
		}
	}

	public void display() {
		textFont(proximaNovaSC_32, label_size);
		text(title, x, y);

		fill(238);
		rect(x, (y + titleLeading), w, h);

		for (int i = 0; i < graphVals.length-1; i++) {
			stroke(80);
			strokeWeight(1);
			pushMatrix();
			translate(x, (y + titleLeading));
			line(i,graphVals[i],i+1,graphVals[i+1]);
			popMatrix();
		}
	}

	public void update(){
		if(millis() % updateModulo == 0){
			// Slide everything down in the array
			for (int i = 0; i < graphVals.length-1; i++) {
			graphVals[i] = graphVals[i+1]; 
			}
			// Add a new random value
			graphVals[graphVals.length-1] = PApplet.parseInt(random(0,h));
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
