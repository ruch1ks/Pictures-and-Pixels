package a9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushToolUI extends JPanel implements ChangeListener {

	//added slider for brush size
	//added slider for opacity
	private JSlider opacitySlider;
	private JSlider brushSlider; 
	private JSlider red_slider;
	private JSlider green_slider;
	private JSlider blue_slider;
	private PictureView color_preview;
	
	//added field used in PaintBrushTool
	//getter and setter below
	private int brushSize;
	private int opacity; 

	public PaintBrushToolUI() {
		setLayout(new GridLayout(0,1));

		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());

		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		//add slider to change brush size
		JPanel brushSizePanel = new JPanel();
		JLabel brushSizeLabel = new JLabel("Brush Size: ");
		brushSizePanel.setLayout(new BorderLayout());
		brushSizePanel.add(brushSizeLabel, BorderLayout.WEST);
		brushSlider = new JSlider(1, 30);
		brushSlider.setValue(5);
		brushSlider.addChangeListener(this);
		brushSizePanel.add(brushSlider, BorderLayout.CENTER);	
		
		//set default brush size
		brushSize = brushSlider.getValue();
		
		//add slider to change opacity 
		//ordinarily, brush is at 100% opacity
		JPanel opacityPanel = new JPanel();
		JLabel opacityLabel = new JLabel("Opacity");
		opacityPanel.setLayout(new BorderLayout());
		opacityPanel.add(opacityLabel, BorderLayout.WEST);
		opacitySlider = new JSlider(0, 100);
		opacitySlider.setValue(100);
		opacitySlider.addChangeListener(this);
		opacityPanel.add(opacitySlider, BorderLayout.CENTER);
				
		//set default opacity
		opacity = opacitySlider.getValue();

		JPanel red_slider_panel = new JPanel();
		JLabel red_label = new JLabel("Red:");
		red_slider_panel.setLayout(new BorderLayout());
		red_slider_panel.add(red_label, BorderLayout.WEST);
		red_slider = new JSlider(0,100);
		red_slider.addChangeListener(this);
		red_slider_panel.add(red_slider, BorderLayout.CENTER);

		JPanel green_slider_panel = new JPanel();
		JLabel green_label = new JLabel("Green:");
		green_slider_panel.setLayout(new BorderLayout());
		green_slider_panel.add(green_label, BorderLayout.WEST);
		green_slider = new JSlider(0,100);
		green_slider.addChangeListener(this);
		green_slider_panel.add(green_slider, BorderLayout.CENTER);

		JPanel blue_slider_panel = new JPanel();
		JLabel blue_label = new JLabel("Blue: ");
		blue_slider_panel.setLayout(new BorderLayout());
		blue_slider_panel.add(blue_label, BorderLayout.WEST);
		blue_slider = new JSlider(0,100);
		blue_slider.addChangeListener(this);
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);

		//***brush size label is now the widest***
		Dimension d = brushSizeLabel.getPreferredSize();
		opacityLabel.setPreferredSize(d);
		red_label.setPreferredSize(d);
		blue_label.setPreferredSize(d);
		green_label.setPreferredSize(d);

		slider_panel.add(brushSizePanel);
		slider_panel.add(opacityPanel);
		slider_panel.add(red_slider_panel);
		slider_panel.add(green_slider_panel);
		slider_panel.add(blue_slider_panel);

		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);

		color_preview = new PictureView(new ObservablePictureImpl(50, 50));
		color_chooser_panel.add(color_preview, BorderLayout.EAST);

		add(color_chooser_panel);

		stateChanged(null);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		//set brush size and opacity based on slider value
		this.setBrushSize(brushSlider.getValue());
		this.setOpacity(opacitySlider.getValue());
		
		Pixel p = new ColorPixel(red_slider.getValue()/100.0,
				green_slider.getValue()/100.0,
				blue_slider.getValue()/100.0);
		
		ObservablePicture preview_frame = color_preview.getPicture();
		preview_frame.suspendObservable();
			
		for (int x=0; x<preview_frame.getWidth(); x++) {
			for (int y=0; y<preview_frame.getHeight(); y++) {
				preview_frame.setPixel(x, y, p);
			}
		}
		preview_frame.resumeObservable();
	}

	public Pixel getBrushColor() {
		return color_preview.getPicture().getPixel(0,0);
	}

	//used in PaintBrushTool
	//the return values are passed
	//as parameters to paintAt()
	public int getBrushSize() {
		return brushSize; 
	}

	public int getOpacity() {
		return opacity;
	}
	
	//brush size/opacity is modified within stateChanged()
	//should not be able to be modified anywhere else
	private void setBrushSize(int size) {
		brushSize = size; 
	}
	
	private void setOpacity(int newOpacity) {
		opacity = newOpacity; 
	}
}
