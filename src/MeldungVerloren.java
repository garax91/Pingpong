import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;




public class MeldungVerloren extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MeldungVerloren(String seite){
		super();
		setTitle("Verloren");
		setResizable(true);
		setLocationRelativeTo(null);
		setModal(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
/*		JTextArea nachricht = new JTextArea("Sie haben leider Verloren!");
		//nachricht.setText("Sie haben leider Verloren!");
	//	nachricht.setLocation(0, 0);
	//	nachricht.setVisible(true);
	//	nachricht.setEditable(false);
		nachricht.setLineWrap(true);
		nachricht.setWrapStyleWord(true);
		nachricht.setOpaque(false);		//hintergrund ausschalten
*/
		
		JButton ende;
		ende = new JButton(seite +" hat leider verloren!");

		
		
		ende.addActionListener(new ActionHandler(ende));
		
//		add(nachricht);
		add(ende);
		
		pack();
	}
	
	
}
