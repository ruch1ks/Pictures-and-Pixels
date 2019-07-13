package a9;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BlurTool implements Tool {

	private BlurUI ui;
	private ImageEditorModel model;

	public BlurTool(ImageEditorModel model) {
		this.model = model;
		ui = new BlurUI();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		model.blurAt(e.getX(), e.getY(), ui.getBlurSize());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		model.blurAt(e.getX(), e.getY(), ui.getBlurSize());		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Blur";
	}

	@Override
	public JPanel getUI() {
		// TODO Auto-generated method stub
		return ui;
	}
	
}