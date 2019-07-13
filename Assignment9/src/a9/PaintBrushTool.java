package a9;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PaintBrushTool implements Tool {

	private PaintBrushToolUI ui;
	private ImageEditorModel model;

	public PaintBrushTool(ImageEditorModel model) {
		this.model = model;
		ui = new PaintBrushToolUI();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * updated to take the changing brush size from PaintBrushToolUI component 
	 * rather than defining a brush_size variable in this class
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		//if opacity is at 100%, just paint with ordinary paintbrush
		if(ui.getOpacity() == 100) {
			model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), ui.getBrushSize()); 
		}

		//otherwise use overloaded method to paint 
		//taking opacity into account
		else {
			model.paintAt(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()), ui.getBrushColor(), ui.getBrushSize(), ui.getOpacity()); 
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//if opacity is at 100%, just paint with ordinary paintbrush
		if(ui.getOpacity() == 100) {
			model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), ui.getBrushSize()); 
		}

		//otherwise use overloaded method to paint 
		//taking opacity into account
		else {
			model.paintAt(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()), ui.getBrushColor(), ui.getBrushSize(), ui.getOpacity()); 
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "Paint Brush";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

}
