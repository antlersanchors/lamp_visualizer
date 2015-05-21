class LineGraph {
	int x;
	int y;
	int w;
	int h;
	String title;

	LineGraph(int tempX, int tempY, int tempW, int tempH, String tempT){
		x = tempX;
		y = tempY;
		w = tempW;
		h = tempH;
		title = tempT;
	}

	void update() {
		textFont(proximaNovaSC_32, label_size);
		text(title, left_margin, 0 + height * 0.5 + 75);

		fill(238);
		rect(left_margin, (0 + height * 0.5 + 100), earnGraphWidth, earnGraphHeight);

		for (int i = 0; i < earnRateVals.length-1; i++) {
			stroke(80);
			strokeWeight(1);
			pushMatrix();
			translate(left_margin, 0 + height * 0.5 + 100);
			line(i,earnRateVals[i],i+1,earnRateVals[i+1]);
			popMatrix();
		}
			  
		if(millis() % 50 == 0){
			// Slide everything down in the array
			for (int i = 0; i < earnRateVals.length-1; i++) {
			earnRateVals[i] = earnRateVals[i+1]; 
			}
			// Add a new random value
			earnRateVals[earnRateVals.length-1] = int(random(0,earnGraphHeight));
		}

	}
}