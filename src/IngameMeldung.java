import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class IngameMeldung {
	private static BufferedImage look;
	static float zeit;
	
	public IngameMeldung(int meldungsID) {
		
		if(meldungsID == 0){
			zeit=0;
			try {
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testNachricht_+1Leben.png"));
			} catch (IOException e) {e.printStackTrace();}
		}else if(meldungsID == 1){
			zeit=0;
			try {
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testNachricht_großerStab.png"));
			} catch (IOException e) {e.printStackTrace();}
		}else if(meldungsID == 2){
			zeit=0;
			try {
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testNachricht_kleinerStab.png"));
			} catch (IOException e) {e.printStackTrace();}
		}else if(meldungsID == 3){
			try {
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testNachricht_-1Leben.png"));
			} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	
	public static void update(float timeSinceLastFrame){
		zeit+=timeSinceLastFrame;
		

		if(zeit>3){
			zeit=0;
			look = null;
		}
	}
	
	public static BufferedImage getLook(){
		return look;
	}
}
