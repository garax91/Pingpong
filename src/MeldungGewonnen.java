import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;


public class MeldungGewonnen extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MeldungGewonnen(float zeit, String sieger){
		super();
		setTitle("Gewonnen");
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
	/*	JTextPane nachricht = new JTextPane();
		nachricht.setText("Sie haben mit der Zeit "+ zeit +" Sekunden gewonnen!!");
		nachricht.setLocation(0, 0);
		nachricht.setVisible(true);
		nachricht.setEditable(false);
*/
		
		JButton ende;
		ende = new JButton("Spieler >>"+ sieger + "<< hat nach " + ((int)(zeit/60.0)) +" Minuten und " +((float)(((int)(zeit*100))/100.0)%60) + " Sekunden gewonnen!");			//alle nachkommastellen (bis auf 2) abschneiden
		
		ende.addActionListener(new ActionHandler(ende));
		
	//	add(nachricht);
		add(ende);
		
		pack();
	}
	
	
}
