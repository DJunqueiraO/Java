package Experiment._210523.JavaFx;

import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player {
	static MidiChannel midiChannel;
	static Map<String, String> keys = createKeys("keys.txt");
	public static void play(@SuppressWarnings("exports") KeyCode keyCode) {
		play(Integer.parseInt(keys.get(keyCode.toString())), 64);
	}
	public static void play(@SuppressWarnings("exports") KeyEvent keyEvent) {
		play(keyEvent.getCode().getCode(), 64);
	}
	public static void play(int note) {
		play(note, 64);
	}
	
	public static void stop(int note) {
		if(midiChannel == null) return;
		midiChannel.noteOff(note);
	}
	public static void play(int note, int velocity) {
		new Thread(
			() -> {
				try {
					MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
					MidiDevice device = MidiSystem.getMidiDevice(infos[0]);
					device.open();
					Synthesizer synthesizer = MidiSystem.getSynthesizer();
					synthesizer.open();
					midiChannel = synthesizer.getChannels()[0];
					midiChannel.programChange(0);
					midiChannel.noteOn(note, velocity);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		).start();
	}
	private static Map<String, String> createKeys(String file) {
		Map<String, String> keys = new HashMap<>();
		for (String line : FileReader.read(file).split("\n")) {
			String[] key = line.split("=");
			keys.put(key[0], key[1]);
		}
		return keys;
	}
}
