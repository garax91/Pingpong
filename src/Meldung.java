import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Meldung {
	
	private BufferedImage lookStart;
	private float posStartX;
	private float posStartY;
	private BufferedImage lookPause;
	private float posPauseX;
	private float posPauseY;
	
	Meldung(int fieldsizeX, int fieldsizeY){
		
		try {lookStart = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/startmeldung_test.png"));
		} catch (IOException e) {e.printStackTrace();}
		try {lookPause = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/pausenmeldung_test.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		posStartX = fieldsizeX/2 - lookStart.getWidth()/2;
		posStartY = fieldsizeY/2 - lookStart.getHeight()/2;
		posPauseX = fieldsizeX/2 - lookStart.getWidth()/2;
		posPauseY = fieldsizeY/2 - lookStart.getHeight()/2;
	}
	
	public BufferedImage getLookStart(){
		return lookStart;
	}
	public float getPosStartX(){
		return posStartX;
	}
	public float getPosStartY(){
		return posStartY;
	}
	public BufferedImage getLookPause(){
		return lookPause;
	}
	public float getPosPauseX(){
		return posPauseX;
	}
	public float getPosPauseY(){
		return posPauseY;
	}
}
