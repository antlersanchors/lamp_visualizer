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

	String[] data_1 = new String[0][0];
	
	labels_1 = {
		{"Light Level", str(lightLevel)},
		{"kW Generation", str(kwLevel)},
		{"Time Collected", str(timeCollected)},
		{"Money Generated", str(moneyGenerated)}
	};

	fill(255, 20, 20, 80);
	noStroke();
	rect(0, 0, (width * 0.6), (height * 0.5));

	for (int i=0; i < 2; i++){
		for (int j=0; j < data_1.length; j++) {
			textSize(32);
			// String displayText = data_1[j][i];
			text("hello world", 0, (10 + 10*j);

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