// TIME STUFF
Timer timer;
long currentTime;
long previousTime;

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

void setup() {
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

	timer = new Timer();
	currentTime = millis();
	previousTime = 0;

	// initialize all the things
	lightLevel = 400;
	kwFactor = 0.065;
	kwLevel = 0;
	moneyGenerated = 0;

	defaultGraphWidth = 300;
	defaultLineGraphHeight = 200;
	defaultBarGraphHeight = 20;

	ostrich_32 = loadFont("OstrichSans-Medium-32.vlw");
	proximaNova_32 = loadFont("ProximaNova-Regular-32.vlw");
	proximaNovaSC_32 = loadFont("ProximaNovaS-Semibold-32.vlw");

	// initialize graphs
	earningsGraph = new LineGraph(left_margin, int(0 + height * 0.5 + 100), defaultGraphWidth, defaultLineGraphHeight, "Billions Earned");
	lightGraph = new BarGraph_Horizontal(left_margin, 325, defaultGraphWidth, defaultBarGraphHeight);
}

void draw() {
	background(235, 230, 230);
	
	timer.update();
	//checkStatus(); // check the solar panel mode status
	//checkPersonality(); // check the personality setting
	updateValues(); // create new "data"
	module_1(); // display our "data"
	module_2();
	module_3();	

}

void keyReleased() {
	if (key == 's'){
		sunBathing = !sunBathing;
		timer.pause();
	}
}

void updateValues(){

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

void module_1(){
	noStroke();
	if (sunBathing){	
		fill(255, 20, 20, 80);
		rect(0, 0, (width * 0.6), (height * 0.5));

		fill(80, 80, 90);
		textFont(proximaNovaSC_32, label_size);
		text("solar collection: active", left_margin, top_margin);

	} else {
		fill(255, 20, 20, 10);
		rect(0, 0, (width * 0.6), (height * 0.5));
		fill(80, 80, 90);
		textFont(proximaNovaSC_32, label_size);
		text("solar collection: inactive", left_margin, top_margin);
	}


	String[][] data_1 = {
		{"Light Level", str(lightLevel)}, // labels and fields, row by row
		{"Person Present", str(personPresent)},
		{"kW Generation", str(kwLevel)},
		{"Time Collected", timer.timeCollected},
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
	
	int lightVal = int(map(lightLevel, 10, 850, 0, 300));
	lightGraph.display(lightVal);

}

void module_2(){
	fill(20, 20, 255, 80);
	noStroke();
	rect(0, (0 + height * 0.5), (width * 0.6), (height * 0.5));

	earningsGraph.display();
	earningsGraph.update();
	
}

void module_3(){
	fill(20, 255, 20, 80);
	noStroke();
	rect((width * 0.6), 0, (width * 0.4), height);

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

	tableRender(int(width * 0.6), top_margin, data_3);

}

void tableRender(int tempX, int tempY, String[][] tempText){
	int x = tempX;
	int y = tempY;
	String[][] theText = tempText;

	for (int i = 0; i < 2; i++){ 	// let's draw the table!
		for (int j=0; j < theText.length; j++) {
			fill(80, 80, 90);
			textFont(ostrich_32, label_size);
			pushMatrix();
			translate(x, y);
			text(theText[j][i], (left_margin + gutter*i), (leading*j));
			popMatrix();
		}
	}

}