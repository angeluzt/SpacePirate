package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import enums.Sounds;

// Reference: https://www.youtube.com/watch?v=nUHh_J2Acy8&ab_channel=RyiSnow
public class Sound {

	private Clip clip;
	private FloatControl volumeCtrl;
	private float volumeA = 0.4f; // Maximum = 6.0206

	public void loadSound(Sounds sound) {
		Constants.sounds.put(sound, sound.filepath());
	}
	
	public Sound setFile(Sounds sound, float volumeLevel) {
		try {
			if(!Constants.sounds.containsKey(sound)) {
				this.loadSound(sound);
			}

			AudioInputStream ais;

			ais = AudioSystem.getAudioInputStream(Constants.sounds.get(sound));
				
			clip = AudioSystem.getClip();
			clip.open(ais);

			
			volumeCtrl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			this.setVolume(volumeLevel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public Sound play() {
		clip.start();
		
		return this;
	}
	
	public Sound loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
		return this;
	}
	
	public Sound loop(int times) {
		clip.loop(times);
		
		return this;
	}
	
	public Sound stop() {
		clip.stop();
		
		return this;
	}
    
    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public Sound setVolume(float newVolume) {
    	this.volumeA = newVolume;
        if (volumeA < 0f || volumeA > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volumeA);
        volumeCtrl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        volumeCtrl.setValue(20f * (float) Math.log10(volumeA));
        
        return this;
    }
}
