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


void setup() {
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
	elapsedTime = int(currentTime - elapsedTime);
	timeCollected = "hai";

	// Initialize all the things
	lightLevel = 400;
	kwFactor = 0.065;
	kwLevel = 0;
	moneyGenerated = 0;
}

void draw() {
	background(235, 230, 230);

	timer();
	checkStatus(); // check the solar panel mode status
	//checkPersonality(); // check the personality setting
	updateValues(); // create new "data"
	module_1(); // display our "data"
	module_2();
	module_3();	

}

void timer(){
	currentTime = millis(); // update the current time, then run code

	seconds = int(currentTime / 1000);
    minutes = seconds / 60;
    seconds = seconds % 60;
    hundredths = int(currentTime / 10 % 100);

    timeCollected = nf(minutes, 2, 0) + " : " + nf(seconds, 2, 0) + " : " + nf(hundredths, 2, 0);
	
}

void checkStatus(){
	if (keyPressed){
		if (key == 's'){
			sunBathing = !sunBathing;
		}
	}
}

void updateValues(){

	float lightChange = random(-10,10);
	
	if (currentTime - previousTime > 300){

		lightLevel = lightLevel + lightChange;
		constrain(lightLevel, 10, 850);
		nf(lightLevel, 0, 2);
		kwLevel = lightLevel * kwFactor;
		nf(kwLevel, 0, 1);
		moneyGenerated = 0;

		previousTime = currentTime; // let time catch up
	}

}

void module_1(){

	String[][] data_1 = {
		{"Light Level", str(lightLevel)}, // labels and fields, row by row
		{"Person Present", str(personPresent)},
		{"kW Generation", str(kwLevel)},
		{"Time Collected", timeCollected},
		{"Money Generated", str(moneyGenerated)}
	};

	fill(255, 20, 20, 80);
	noStroke();
	rect(0, 0, (width * 0.6), (height * 0.5));

	for (int i = 0; i < 2; i++){ 	// let's draw the table!
		for (int j=0; j < data_1.length; j++) {
			textSize(32);
			text(data_1[j][i], (left_margin + gutter*i), (top_margin + leading*j));
		}
	}

	fill(220);
	rect(left_margin, 350, 300, 20);
	int barVal = int(map(lightLevel, 10, 850, 0, 300));
	fill(85);
	rect(left_margin, 350, barVal, 20);

}

void module_2(){
	fill(20, 20, 255, 80);
	noStroke();
	rect(0, (0 + height * 0.5), (width * 0.6), (height * 0.5));

}

void module_3(){
	fill(20, 255, 20, 80);
	noStroke();
	rect((width * 0.6), 0, (width * 0.4), height);

}