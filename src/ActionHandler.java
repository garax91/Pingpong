import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class ActionHandler implements ActionListener{
	
	JButton ende;
	
	public ActionHandler(JButton ende){
		this.ende = ende;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ende){
			System.exit(0);
		}
	}

}
