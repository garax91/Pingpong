import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpecialStein{
	
	private int posx;
	private int posy;
	private int modeNummer;	//was für eine art stein solle s sein(+1leben, kurzer stab, kleiner stab, etc)
	private BufferedImage look;
	private BufferedImage look2;
	private BufferedImage look3;
	private boolean existiert = true;
	private int trefferAnzahl=0; // für modeNummer 5
	private Spieler spielerLinks;
	private Spieler spielerRechts;
	private SpecialEffekt effekt = null;
	private Leben lebenRechts;
	private Leben lebenLinks;
	private static boolean modeBalance = true;

	public SpecialStein(int posx, int posy, int modeNummer, Spieler spielerLinks, Spieler spielerRechts, Leben lebenRechts, Leben lebenLinks) {
		if(modeBalance == true && modeNummer == 4){		// jeder 2. feste stein (modeNummer 4) wird zu einem zerstörbaren stein
			modeBalance = false;
		}else if(modeBalance == false && modeNummer == 4){
			modeNummer++;
			modeBalance = true;
		}
		
		try {
			if(modeNummer==4){
				look2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testXStein_gedreht.png"));
			}else if(modeNummer == 5){
				look2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testX3Stein_gedreht.png"));
				look3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testX3Stein_kaputt_gedreht.png"));
			}
			
			look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testStein_gedreht.png"));
		} catch (IOException e) {e.printStackTrace();}
		this.posx = posx;
		this.posy = posy;
		this.modeNummer = modeNummer;
		this.spielerLinks = spielerLinks;
		this.spielerRechts = spielerRechts;
		this.lebenLinks = lebenLinks;
		this.lebenRechts = lebenRechts;
	}

	public void update(int ballNummer, Ball ball){
		
		boolean getroffen = false;
		
		//getroffene steine "verschwinden"
		//ball prallt ab
/*a*/		if(ball.getSpeedx()>0 && ball.getPosx()+ball.getLook().getWidth()>posx && (ball.getPosx()+ball.getLook().getWidth())-(ball.getSpeedx()*ball.getTimeSinceLastFrame())< posx && existiert
				&& (ball.getPosy()+(ball.getLook().getHeight()/2)>posy && ball.posy+(ball.getLook().getHeight()/2)<posy+look.getHeight() 
						|| ball.getPosy()>posy && ball.posy<posy+look.getHeight() 
						|| ball.getPosy()+(ball.getLook().getHeight())>posy && ball.posy+(ball.getLook().getHeight())<posy+look.getHeight())){

			ball.setPosx(posx-ball.getLook().getWidth());
			ball.setSpeedx(ball.speedx*-1);
			getroffen = true;
		}
/*b*/		if(ball.getSpeedy()<0 && ball.getPosy()<posy+look.getHeight() && ball.getPosy()-(ball.getSpeedy()*ball.getTimeSinceLastFrame())> posy+look.getHeight() && existiert
				&& (ball.getPosx()+(ball.getLook().getWidth()/2)>posx && ball.getPosx()+(ball.getLook().getWidth()/2)<posx+look.getWidth() 
						|| ball.getPosx()>posx && ball.getPosx()<posx+look.getWidth()
						|| ball.getPosx()+(ball.getLook().getWidth())>posx && ball.getPosx()+(ball.getLook().getWidth())<posx+look.getWidth())){
			
			ball.setPosy(posy+look.getHeight());
			ball.setSpeedy(ball.speedy*-1);
			getroffen = true;
		}
/*c*/		if(ball.getSpeedx()<0 && ball.getPosx()<posx+look.getWidth() && ball.getPosx()-(ball.getSpeedx()*ball.getTimeSinceLastFrame())> posx+look.getWidth() && existiert
				&& (ball.getPosy()+(ball.getLook().getHeight()/2)>posy && ball.getPosy()+(ball.getLook().getHeight()/2)<posy+look.getHeight()
						|| ball.getPosy()>posy && ball.posy<posy+look.getHeight()
						|| ball.getPosy()+(ball.getLook().getHeight())>posy && ball.posy+(ball.getLook().getHeight())<posy+look.getHeight())){
			
			ball.setPosx(posx+look.getWidth());
			ball.setSpeedx(ball.speedx*-1);
			getroffen = true;
		}
/*d*/		if(ball.getSpeedy()>0 && ball.getPosy()+ball.getLook().getHeight()>posy && (ball.getPosy()+ball.getLook().getHeight())-(ball.getSpeedy()*ball.getTimeSinceLastFrame())< posy && existiert
				&& (ball.getPosx()+(ball.getLook().getWidth()/2)>posx && ball.getPosx()+(ball.getLook().getWidth()/2)<posx+look.getWidth()
						|| ball.getPosx()>posx && ball.getPosx()<posx+look.getWidth()
						|| ball.getPosx()+(ball.getLook().getWidth())>posx && ball.getPosx()+(ball.getLook().getWidth())<posx+look.getWidth())){
		
			ball.setPosy(posy-ball.getLook().getHeight());
			ball.setSpeedy(ball.speedy*-1);
			getroffen = true;
		}

		if(getroffen){
			if(modeNummer == 4){
				look = look2;
			}else if(modeNummer == 5){
				if(trefferAnzahl == 0){
					look = look2;
					trefferAnzahl=1;
				}else if(trefferAnzahl == 1){
					look = look3;
					trefferAnzahl=2;
				}else if(trefferAnzahl == 2){
					existiert = false;
					Stein.anzahlGetroffen++;
				}
			}else{	
				Stein.anzahlGetroffen++;
				existiert = false;
				
				SpecialEffekt effekt = new SpecialEffekt(ballNummer, posx, posy, spielerLinks, spielerRechts, lebenRechts, lebenLinks, modeNummer);
				this.effekt = effekt;
			}
		}
		
		//Objekt entfernen
		if(existiert == false && effekt != null && (effekt.getPosx()>spielerRechts.getPosx() || effekt.getPosx()<spielerLinks.getPosx())){
			effekt = null;
		}
	}
	
	public BufferedImage getLook(){
		return look;
	}
	public int getX(){
		return posx;
	}
	public int getY(){
		return posy;
	}
	public boolean getExestiert(){
		return existiert;
	}
	public SpecialEffekt getEffekt(){
		return effekt;
	}
	public void setExestiert(boolean exestiert){
		this.existiert = exestiert;
	}
}
