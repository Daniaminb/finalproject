import  sun.audio.*;
import java.io.*;

/**
 * Play music during the code
 */
public class Music {
    //directory of music
    public String name;
    AudioStream BGM;
    AudioPlayer MGP;
    public Music()
    {
        name = null;
    }
    /**
     * get Name of music and play
     */
    public void playMusic(String name) throws FileNotFoundException {
        this.name=name;
             MGP = AudioPlayer.player;
            try {
                BGM = new AudioStream(new FileInputStream(name));
                //MD = BGM.getData();
                //loop = new ContinuousAudioDataStream(MD);
                MGP.start(BGM);
            }
             catch (IOException e) {
                e.printStackTrace();
            }
    }
}
