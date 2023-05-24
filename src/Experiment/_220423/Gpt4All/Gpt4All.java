package Experiment._220423.Gpt4All;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Experiment.utils.Create;

public class Gpt4All  {
    static JTextArea textArea;
    static JTextField textField;
    static JScrollPane scrollPane;
    static JPanel panel;
    static JFrame frame;
	public static void main(String[] args) throws IOException {
	    initialize();
		var processBuilder = new ProcessBuilder(
				"C:/Chat/gpt4all/chat/gpt4all-lora-quantized-win64.exe"
		);
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
						if(
							textArea.getText().contains("LLaMA") ||
							textArea.getText().contains("> ")
						) {
							textField.setEditable(true);
						}
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
						textArea.append(inputText + "\n");
				        outputStream.flush();
					} catch (IOException exception) {
						exception.printStackTrace();
					}

					textField.setEditable(false);
			        textField.setText("");
			    }
			}
		);
	}
	static void initialize() {
	     textArea = Create.createTextArea();
	     textArea.setEditable(false);
	     textField = Create.createTextField(Integer.MAX_VALUE, 50);
	     textField.setEditable(false);
	     scrollPane = Create.createVerticalScrollPane(textArea);
	     Component[] components = {scrollPane, textField};
	     panel = Create.createBoxPanel(components, BoxLayout.Y_AXIS);
	     frame = Create.createWindow(panel);
	     frame.setSize(800, 600);
	}
}

