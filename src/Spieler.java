import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Spieler {
	float f_posx;
	float f_posy;			//nur hin und her m�glich
//	private Rectangle bounding;
	private BufferedImage look;
	private BufferedImage lookNormal;
	private BufferedImage lookGro�;
	private BufferedImage lookKlein;
	private int stabGr��e = 1;
	private Rand rand;
	private int feldgr��ey;
	
	
	
	
	public Spieler(int x, int y, int feldgr��ex, int feldgr��ey, Rand rand){
		try {
			lookNormal = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/Stab_multiplayer.png"));
			lookGro� = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/Stab_multiplayer_gro�.png"));
			lookKlein = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/Stab_multiplayer_klein.png"));
		} catch (IOException e) {e.printStackTrace();}
//		bounding = new Rectangle(x, y, look.getWidth(), look.getHeight());
		this.f_posx = x;
		this.f_posy = y;
		this.rand = rand;
		this.feldgr��ey = feldgr��ey;
	}
	
	public void update(float timeSinceLastFrame, int spielernummer){
		
		if(stabGr��e == 0){
			look=lookKlein;
		}else if(stabGr��e == 1){
			look=lookNormal;
		}else if(stabGr��e == 2){
			look=lookGro�;
		}
		
		
		int geschwindigkeit = 380;
		
		if(spielernummer == 1){
			if(Keyboard.isKeyDown(KeyEvent.VK_W))f_posy-=geschwindigkeit*timeSinceLastFrame;
			if(Keyboard.isKeyDown(KeyEvent.VK_S))f_posy+=geschwindigkeit*timeSinceLastFrame;
		}else if(spielernummer == 2){
			if(Keyboard.isKeyDown(KeyEvent.VK_UP))f_posy-=geschwindigkeit*timeSinceLastFrame;
			if(Keyboard.isKeyDown(KeyEvent.VK_DOWN))f_posy+=geschwindigkeit*timeSinceLastFrame;
		}

		
		if(f_posy < rand.getRand().getHeight())	f_posy = rand.getRand().getHeight();
		if(f_posy + look.getHeight() > feldgr��ey - rand.getRand().getHeight() )	f_posy = feldgr��ey - rand.getRand().getHeight() - look.getHeight();
		
	//	bounding.x = (int)f_posx;
	//	bounding.y = (int)f_posy;
	}
	

	public BufferedImage getLook(){					//grafik einf�gen!
		return look;
	}
	public float getPosx(){
		return f_posx;
	}
	public float getPosy(){
		return f_posy;
	}
	public int getStabgr��e(){
		return stabGr��e;
	}
	public void setStabGr��e(int stabgr��e){
		this.stabGr��e = stabgr��e;
	}
}
