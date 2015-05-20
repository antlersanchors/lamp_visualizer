boolean sunBathing; // is the lamp in solar panel mode or not?

int margin;
int label_size;
int field_size;

void setup() {
	size(1200, 800);

	margin = 20;
	label_size = 36;
	field_size = 36;

	sunBathing = false;
}

void draw() {
	background(235, 230, 230);

	//checkStatus(); // check the solar panel mode status
	//checkPersonality(); // check the personality setting

	module_1(); // display our "data"
	module_2();
	module_3();	
}

void module_1(){

	int lightLevel = 250;
	int kwLevel = 60;
	int timeCollected = 100;
	int moneyGenerated = 40;

	String[][] data_1 = new String[0][0];



	fill(255, 20, 20, 80);
	noStroke();
	rect(0, 0, (width * 0.6), (height * 0.5));

	textSize(32);
	fill(5);
	text("Light Level", 0, (10 + 0));
	text("kW Generation", 0, (10 + 20));
	text("Time Collected", 0, (10 + 40));
	text("Money Generated", 0, (10 + 60));

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