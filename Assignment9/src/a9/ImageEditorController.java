package a9;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ImageEditorController implements ToolChoiceListener, MouseListener, MouseMotionListener {

	private ImageEditorView view;
	private ImageEditorModel model;
	private Tool current_tool;
	private PixelInspectorTool inspector_tool;
	private PaintBrushTool paint_brush_tool;
	
	//for blur
	private BlurTool blurTool;
	
	public ImageEditorController(ImageEditorView view, ImageEditorModel model) {
		this.view = view;
		this.model = model;

		inspector_tool = new PixelInspectorTool(model);
		paint_brush_tool = new PaintBrushTool(model);
		blurTool = new BlurTool(model);
		
		view.addToolChoiceListener(this);
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		
		this.toolChosen(view.getCurrentToolName());
	}

	@Override
	public void toolChosen(String tool_name) {
		if (tool_name.equals("Pixel Inspector")) {
			view.installToolUI(inspector_tool.getUI());
			current_tool = inspector_tool;
		} else if (tool_name.equals("Paint Brush")) {
			view.installToolUI(paint_brush_tool.getUI());
			current_tool = paint_brush_tool;
		} 
		
		//add blur tool
		else if(tool_name.equals("Blur")) {
			view.installToolUI(blurTool.getUI());
			current_tool = blurTool;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		current_tool.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		current_tool.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		current_tool.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		current_tool.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		current_tool.mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		current_tool.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		current_tool.mouseMoved(e);
	}

}
