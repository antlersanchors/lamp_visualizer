class Timer {
	long currentTime;

	long startTime;
	long totalCollectedTime;
	long offset;

	int seconds;
	int minutes;
	int hundredths;

	String timeCollected;

	boolean running;
	boolean continued;

	Timer(){
		totalCollectedTime = 0;
		running = false;
		continued = false;

	}

	void update(){
		if (running){
		totalCollectedTime = int(millis() - startTime);
		if(continued){
			totalCollectedTime += offset;
		}
	}

	seconds = int(totalCollectedTime / 1000);
	minutes = seconds / 60;
	seconds = seconds % 60;
	hundredths = int(totalCollectedTime / 10 % 100);
	timeCollected = nf(minutes, 2, 0) + " : " + nf(seconds, 2, 0) + " : " + nf(hundredths, 2, 0);

	}

	void pause(){
		if (!running){
			startTime = millis();
			offset = totalCollectedTime;
			continued = true;
		} else {
			continued = false;
		}
		running = !running;
		println("running: "+running);
	}


} 