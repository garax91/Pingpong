

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rand {
	private BufferedImage rand;
	
	public Rand(){
		try {
			rand = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testRandOben.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getRand(){
		return rand;
	}
}
	