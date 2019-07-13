package a9;

public class ImageEditorModel {

	private Picture original;
	private ObservablePicture current;

	public ImageEditorModel(Picture f) {
		original = f;
		current = original.copy().createObservable();
	}

	public ObservablePicture getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size) {
		current.suspendObservable();
		
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
						xpos < current.getWidth() &&
						ypos >= 0 &&
						ypos < current.getHeight()) {
					
					current.setPixel(xpos, ypos, brushColor);
				}
			}
		}
		current.resumeObservable();
	}
	
	//overloaded paintAt to account for opaqueness
	public void paintAt(int x, int y, Pixel atXY, Pixel brushColor, int brush_size, int opacity) {
		current.suspendObservable();

		//convert to percentage
		double opacityPercent = (double) opacity / 100;
		
		//calculate new r/g/b with opacity
		double newRed = (brushColor.getRed() * opacityPercent) + (atXY.getRed() * (1 - opacityPercent));
		double newBlue = (brushColor.getBlue() * opacityPercent) + (atXY.getBlue() * (1 - opacityPercent));
		double newGreen = (brushColor.getGreen() * opacityPercent) + (atXY.getGreen() * (1 - opacityPercent));
		
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
						xpos < current.getWidth() &&
						ypos >= 0 &&
						ypos < current.getHeight()) {
					
					//create new pixel with opaque values
					current.setPixel(xpos, ypos, new ColorPixel(newRed, newGreen, newBlue));
				}
			}
		}
		
		current.resumeObservable();
	}

	public void blurAt(int x, int y, int blurSize) {
		current.suspendObservable();

		//accumulate color components here
		//keep track of number of pixels for avg
		double totalR = 0;
		double totalB = 0;
		double totalG = 0;
		double numOfPixs = 0;

		for(int i = x - (blurSize + 1); i < x + (blurSize - 1); i++) {
			for(int j = y - (blurSize + 1); j < y + (blurSize - 1); j++) {

				//iterating through pixels surrounding pixel to blur
				//arbitrary # of pixels to blur together
				for(int k = x - 5; k <= x + 5; k++) {
					for(int l = y - 5; l <= y + 5; l++) {

						//check for out of bounds, do nothing
						if (k < 0 || k >= current.getWidth()
								|| l < 0 || l >= current.getHeight()) {

						}

						//if not out of bounds, accumulate
						else {
							totalR += current.getPixel(k, l).getRed();
							totalG += current.getPixel(k, l).getGreen();
							totalB += current.getPixel(k, l).getBlue();
							numOfPixs++;
						}
					}
				}
				//do nothing if out of bounds
				try {
					current.setPixel(i, j, new ColorPixel(totalR / numOfPixs, totalG / numOfPixs, totalB / numOfPixs));
				}
				catch(IllegalArgumentException e) {

				}
			}

		}
		current.resumeObservable();
	}
}
