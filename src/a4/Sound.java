package a4;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Sound {

	private AudioClip myClip;
	
	public Sound(String filename)
	{
		try
		{
			File file = new File(filename);
			if(file.exists())
				myClip = Applet.newAudioClip(file.toURI().toURL());
			else
				throw new RuntimeException("Sound: file not found: "+filename);
		}
		catch(MalformedURLException e)
		{
			throw new RuntimeException("Sound: malformed url: "+e);
		}
	}
	
	public void play()
	{
		myClip.play();
	}
	
	public void loop()
	{
		myClip.loop();
	}
	
	public void stop()
	{
		myClip.stop();
	}
}
