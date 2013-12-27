import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;




public class Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private BufferStrategy strat;
	private final Spieler spielerLinks;
	private final Spieler spielerRechts;
	private BufferedImage hintergrund;
	private Rand rand;
	private int spielfeldBreite;
	private int spielfeldHöhe;
	private Ball ball1;
	private Ball ball2;
	private Stein[][] stein;
	private SpecialStein[] xStein;
	private Leben lebenLinks;
	private Leben lebenRechts;
	private Meldung meldung;

	

	public Frame(Spieler spielerLinks, Spieler spielerRechts, Ball ball1, Ball ball2, Leben lebenLinks, Leben lebenRechts, Rand rand, int spielfeldBreite, int spielfeldHöhe, Meldung meldung, Stein[][] stein, SpecialStein[] xStein){
		super("Spiel");
		addKeyListener(new Keyboard());
		this.spielerLinks = spielerLinks;
		this.spielerRechts = spielerRechts;
		try {
			hintergrund = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testHintergrund2.png"));
		} catch (IOException e) {e.printStackTrace();
		}
		this.rand = rand;
		this.spielfeldBreite = spielfeldBreite;
		this.spielfeldHöhe = spielfeldHöhe;
		this.ball1 = ball1;
		this.ball2 = ball2;
		this.lebenLinks = lebenLinks;
		this.lebenRechts = lebenRechts;
		this.meldung = meldung;

		for(int x = 0; x < stein.length; x++){
			this.stein = new Stein[stein.length][stein[x].length];			//setzt das lokale array(stein) auf die gleich größe wie das übergebene Stein-array
		}
		for(int i = 0; i<stein.length; i++){
			for(int j = 0; j< stein[i].length; j++){
				this.stein[i][j] = new Stein();
				this.stein[i][j] = stein[i][j]; 					//kopiert das übergebene array auf das lokale array
			}
		}	
		for(int i = 0; i < xStein.length; i++){
			this.xStein = xStein;
		}
	}


	
	public void makeStrate(){
		createBufferStrategy(2);
		strat = getBufferStrategy();
	}
	
	//paint Meldung
	public void paintMeldung(boolean start, boolean pause){
		Graphics g2 = strat.getDrawGraphics();
		drawStart(g2, start, pause);
		g2.dispose();		//schmeißen graphics objekt weg (speicher freigeben)
		strat.show();		//zeichnet tatsächlich
	}
	private void drawStart(Graphics g2, boolean start, boolean pause){
		if(start){g2.drawImage(meldung.getLookStart(), (int)meldung.getPosStartX(), (int)meldung.getPosStartY(), null);}
		if(pause){g2.drawImage(meldung.getLookPause(), (int)meldung.getPosPauseX(), (int)meldung.getPosPauseY(), null);}
	}
	
	
	
	//repaint Screen
	public void repaintScreen(){
		Graphics g = strat.getDrawGraphics();
		draw(g);
		g.dispose();		//schmeißen graphics objekt weg (speicher freigeben)
		strat.show();		//zeichnet tatsächlich
	}
	
	private void draw(Graphics g){
		g.drawImage(hintergrund, 0, 0, null);
		g.drawImage(rand.getRand(), 0, 0, null);
		g.drawImage(rand.getRand(), 0, spielfeldHöhe-rand.getRand().getHeight(), null);
		g.drawImage(spielerLinks.getLook(), (int)spielerLinks.getPosx(), (int)spielerLinks.getPosy(), null);
		g.drawImage(spielerRechts.getLook(), (int)spielerRechts.getPosx(), (int)spielerRechts.getPosy(), null);
		g.drawImage(ball1.getLook(), (int)ball1.posx, (int)ball1.posy, null);
		g.drawImage(ball2.getLook(), (int)ball2.posx, (int)ball2.posy, null);
		for(int i=0; i<lebenRechts.getLeben(); i++){
			g.drawImage(lebenRechts.getLook(), spielfeldBreite-40-i*lebenRechts.getLook().getWidth(), spielfeldHöhe-15, null);
		}
		for(int i=0; i<lebenLinks.getLeben(); i++){
			g.drawImage(lebenLinks.getLook(), 40+i*lebenLinks.getLook().getWidth(), spielfeldHöhe-15, null);
		}
		for(int i = 0; i<stein.length; i++){
			for(int j=0; j<stein[i].length; j++){
				if(stein[i][j].getExestiert()){
					g.drawImage(stein[i][j].getLook(), stein[i][j].getX(), stein[i][j].getY(), null);
				}
			}
		}	
		for(int i = 0; i<xStein.length; i++){
			if(xStein[i].getExestiert()){
				g.drawImage(xStein[i].getLook(), xStein[i].getX(), xStein[i].getY(), null);
			}else if(xStein[i].getExestiert()==false && xStein[i].getEffekt() != null){
				g.drawImage(xStein[i].getEffekt().getLook(), (int)xStein[i].getEffekt().getPosx(), (int)xStein[i].getEffekt().getPosy(), null);
			}
		}
	}
}
