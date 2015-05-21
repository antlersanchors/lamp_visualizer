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

	void display(int tempV) {
		v = tempV;

		fill(220);
		rect(x, y, w, h);

		fill(85);
		rect(x, y, v, h);

	}
}