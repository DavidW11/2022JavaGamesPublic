import java.io.File;
import java.io.IOException;
import java.net.*;
import java.net.URISyntaxException;
import javax.sound.sampled.*;

public class SoundPlayer
{
  private boolean looping;
  private  Clip clip = null;

	public SoundPlayer(String fileName)
	{
		this(fileName, false);
	}

  public SoundPlayer (String fileName, boolean looping)
  {

        try {

        clip = AudioSystem.getClip();
        URL url2 = this.getClass().getResource(fileName);
        AudioInputStream ais2 = AudioSystem.getAudioInputStream( url2 );

        clip.open(ais2);
        } catch (LineUnavailableException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

    }
    
    public void play()
    {
    	play(false);
    }
    
    public void play(boolean loop)
    {
        clip.setFramePosition(0);
        if(loop)
        	clip.loop(Clip.LOOP_CONTINUOUSLY); 

        clip.start();  	
    } 
    	
    public void stop()
    {
    	clip.stop();
    } 
    	
    public boolean isRunning()
    {
    	return clip.isRunning();
    }	

}