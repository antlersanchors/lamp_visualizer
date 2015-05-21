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

	void display() {
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
			  
		if(millis() % updateModulo == 0){
			// Slide everything down in the array
			for (int i = 0; i < graphVals.length-1; i++) {
			graphVals[i] = graphVals[i+1]; 
			}
			// Add a new random value
			graphVals[graphVals.length-1] = int(random(0,h));
		}

	}
}