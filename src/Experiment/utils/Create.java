package Experiment.utils;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Create {
	public static JFrame createWindow(Component view) {
	    var frame = new JFrame();
	    frame.add(view);
	    frame.pack();
	    frame.setVisible(true);
	    return frame;
	}
	public static JPanel createBoxPanel(Component[] views, int axis) {
	    var panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, axis));
	    for (Component component : views) {
	    	panel.add(component);
		}
	    return panel;
	}
	public static JScrollPane createVerticalScrollPane(Component view) {
	    var scrollPane = new JScrollPane(view);
	    scrollPane.setHorizontalScrollBarPolicy(
	    		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
	    );
	    return scrollPane;
	}
	public static JTextArea createTextArea() {
	    var textArea = new JTextArea();
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    return textArea;
	}
	public static JTextField createTextField(int width, int height) {
	    var textField = new JTextField();
	    textField.setMaximumSize(new Dimension(width, height));
	    return textField;
	}
}
