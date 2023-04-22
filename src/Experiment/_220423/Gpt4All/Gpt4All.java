package Experiment._220423.Gpt4All;

import java.awt.Component;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Executable;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class Gpt4All  {
	
	public static void main(String[] args) throws IOException {
	    var textArea = new JTextArea(20, 80);
	    textArea.setEditable(false);
	    textArea.setWrapStyleWord(true);
	    
	    var textField = new JTextField();
	    
	    var scrollPane = new JScrollPane(textArea);
	    scrollPane.setHorizontalScrollBarPolicy(
	    		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
	    );
	    
	    var panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.add(scrollPane);
	    panel.add(textField);
		
		createWindow(panel);
		var processBuilder = new ProcessBuilder("C:/Chat/gpt4all/chat/gpt4all-lora-quantized-win64.exe");
		
		processBuilder.directory(new File("C:/Chat/gpt4all/chat/"));
		processBuilder.redirectErrorStream(true);
		
		var process = processBuilder.start();
		
		new Thread(
			() -> {
				try {
					var inputStream = process.getInputStream();
					var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
					String line = null;
					while ((line = bufferedReader.readLine()) != null) {
						textArea.append(line.replaceAll("\u001B\\[[;\\d]*m", "") + "\n");
					}
				} catch (IOException exception) {
					exception.printStackTrace();
				}
				
				try {
					int exitCode = process.waitFor();
					textArea.append("\nProcesso finalizado com código de saída " + exitCode + ".");
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}
		).start();

		textField.addActionListener(
				new ActionListener() {
			    @Override public void actionPerformed(ActionEvent actionEvent) {
			        String inputText = textField.getText();
			        
			        var outputStream = process.getOutputStream();
			        
			        try {
						outputStream.write((inputText + "\n").getBytes());
				        outputStream.flush();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
			        
			        textField.setText("");
			    }
			}
		);
	}
	static JFrame createWindow(Component view) {
	    var frame = new JFrame("Minha Aplicação");
	    frame.add(view);
	    frame.pack();
	    frame.setVisible(true);
	    return frame;
	}
}

