package a9;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlurUI extends JPanel implements ChangeListener{

	private JSlider blurSizeSlider;
	private int blurSize; 
	
	public BlurUI() {
		setLayout(new BorderLayout());
		
		//add slider to change size of blurring
		//this obj is its own changeListener
		blurSizeSlider = new JSlider(3, 30);
		blurSizeSlider.setValue(10);
		blurSizeSlider.addChangeListener(this);
		add(blurSizeSlider, BorderLayout.CENTER);
		
		//set blurSize to the value of the slider
		blurSize = blurSizeSlider.getValue();
		
		//add label
		add(new JLabel("Blur Size"), BorderLayout.WEST);
		
	}
	
	    //used in BlurTool
		//the return value is passed
		//as parameter to paintAt()
		public int getBlurSize() {
			return blurSize; 
		}

		//blur size is modified within stateChanged()
		//should not be able to be modified anywhere else
		private void setBlurSize(int size) {
			blurSize = size; 
		}
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		//set brush size based on slider value
		this.setBlurSize(blurSizeSlider.getValue());

	}
	
	
}