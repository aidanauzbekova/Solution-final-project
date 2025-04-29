//import javax.sound.sampled.*;
//import java.io.File;
//import java.io.IOException;
//
//public class SoundPlayer {
//    public static void playSound(String relativePath) {
//        try {
//            File soundFile = new File(relativePath);
//            if (!soundFile.exists()) {
//                System.err.println("Sound file not found: " + soundFile.getAbsolutePath());
//                return;
//            }
//
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioIn);
//            clip.start();
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            e.printStackTrace();
//        }
//    }
//}