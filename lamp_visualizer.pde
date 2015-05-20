boolean sunBathing; // is the lamp in solar panel mode or not?

int left_margin;
int top_margin;
int gutter;
int leading;

int label_size;
int field_size;

int lightLevel;
int kwLevel;
int timeCollected;
int moneyGenerated;


void setup() {
	size(1200, 800);

	left_margin = 20;
	top_margin = 50;
	gutter = 300;
	leading = 50;

	label_size = 36;
	field_size = 36;

	sunBathing = false;
}

void draw() {
	background(235, 230, 230);

	//checkStatus(); // check the solar panel mode status
	//checkPersonality(); // check the personality setting

	// updateValues(); // create new "data"

	module_1(); // display our "data"
	module_2();
	module_3();	
}

void updateValues(){

}

void module_1(){

	lightLevel = 0;
	kwLevel = 0;
	timeCollected = 0;
	moneyGenerated = 0;

	String[][] data_1 = {
		{"Light Level", str(lightLevel)}, // labels and fields, row by row
		{"kW Generation", str(kwLevel)},
		{"Time Collected", str(timeCollected)},
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