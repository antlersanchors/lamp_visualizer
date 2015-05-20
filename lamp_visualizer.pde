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

	int lightLevel = 0;
	int kwLevel = 0;
	int timeCollected = 0;
	int moneyGenerated = 0;

	String[][] data_1 = {
		{"Light Level", str(lightLevel)},
		{"kW Generation", str(kwLevel)},
		{"Time Collected", str(timeCollected)},
		{"Money Generated", str(moneyGenerated)}
	};

	fill(255, 20, 20, 80);
	noStroke();
	rect(0, 0, (width * 0.6), (height * 0.5));

	textSize(32);
	text("Kw Generated", 50, 50);

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