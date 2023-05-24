package Experiment._210523.JavaFx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFX extends Application {
	@Override public void start(@SuppressWarnings("exports") Stage stage) throws Exception {
		stage.setTitle("lero lero");
		var scene = new Scene(new Pane(), 100, 100);
		var events = new ArrayList<KeyEvent>();
		scene.setOnKeyPressed(
				event -> {
					if(events.stream().anyMatch($0 -> $0.getCode() == event.getCode())) return;
					events.add(event);
					Player.play(event.getCode());
				}
		);
		scene.setOnKeyReleased(
				event -> {
					events.removeIf($0 -> $0.getCode() == event.getCode());
					Player.stop(event.getCode().getCode());
				}
		);
		stage.setScene(scene);
//		stage.setMaximized(true);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}

