/**
 * 
 */
package oracleIDGui;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.BorderLayout;

/**
 * @author Armando
 *
 */
public class ConfigurationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jpParameters = null;
	private JLabel jLabelIdentSystem = null;
	private JComboBox jcbIdentSystem = null;
	private JLabel jLabelMainContainerHost = null;
	private JComboBox jcbMainContainerHost = null;
	private JPanel jpButtons = null;
	private JButton jbAccept = null;
	private JButton jbCancel = null;
	
	private String name = null;
	private String host = null;

	/**
	 * @param owner
	 */
	public ConfigurationDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(454, 162);
		this.setModal(true);
		this.setTitle("OracleID");		
		this.setContentPane(getJContentPane());
		setLocation();
		this.setVisible(true);
	}

	public String getName() {
		return name;
	}

	public String getHost() {
		return host;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJpParameters(), BorderLayout.NORTH);
			jContentPane.add(getJpButtons(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jpParameters	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpParameters() {
		if (jpParameters == null) {
			jLabelMainContainerHost = new JLabel();
			jLabelMainContainerHost.setText("Host de contenedor principal:");
			jLabelIdentSystem = new JLabel();
			jLabelIdentSystem.setText("Nombre de sistema de identificación:");
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(2);
			gridLayout1.setColumns(2);
			jpParameters = new JPanel();
			jpParameters.setName("jpParameters");
			jpParameters.setLayout(gridLayout1);
			jpParameters.add(jLabelIdentSystem, null);
			jpParameters.add(getJcbIdentSystem(), null);
			jpParameters.add(jLabelMainContainerHost, null);
			jpParameters.add(getJcbMainContainerHost(), null);
		}
		return jpParameters;
	}

	/**
	 * This method initializes jcbIdentSystem	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJcbIdentSystem() {
		String institutions[] = {"INBio", "FishBase", "EnciclopedyOfLife", "Species2000", "TDWG"};
		
		if (jcbIdentSystem == null) {
			jcbIdentSystem = new JComboBox();
			jcbIdentSystem.setSelectedItem("INBio");
		}
		
		for (String r:institutions) {
			jcbIdentSystem.addItem(r);
        }
		
		return jcbIdentSystem;
	}

	/**
	 * This method initializes jcbMainContainerHost	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJcbMainContainerHost() {
		if (jcbMainContainerHost == null) {
			jcbMainContainerHost = new JComboBox();
			jcbMainContainerHost.setSelectedItem("HARDPC");
			jcbMainContainerHost.setEditable(true);
		}
		
		jcbMainContainerHost.addItem("HARDPC");
		
		return jcbMainContainerHost;
	}

	/**
	 * This method initializes jpButtons	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpButtons() {
		if (jpButtons == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			gridLayout.setHgap(5);
			gridLayout.setVgap(5);
			gridLayout.setColumns(2);
			jpButtons = new JPanel();
			jpButtons.setName("jpButtons");
			jpButtons.setLayout(gridLayout);
			jpButtons.add(getJbAccept(), null);
			jpButtons.add(getJbCancel(), null);
		}
		return jpButtons;
	}

	/**
	 * This method initializes jbAccept	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAccept() {
		if (jbAccept == null) {
			jbAccept = new JButton();
			jbAccept.setText("Aceptar");
			jbAccept.setName("jbAccept");
			jbAccept.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					name = (String) jcbIdentSystem.getSelectedItem();
					host = (String) jcbMainContainerHost.getSelectedItem();
					setVisible(false);
				}
			});
		}
		return jbAccept;
	}

	/**
	 * This method initializes jbCancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbCancel() {
		if (jbCancel == null) {
			jbCancel = new JButton();
			jbCancel.setText("Cancelar");
			jbCancel.setName("jbCancel");
			jbCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					name = null;
					host = null;
					setVisible(false);
				}
			});
		}
		return jbCancel;
	}
	
	public void setLocation() {
		//pack(); //Reajustar al tamaño preferido 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
				
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
	}	

}  //  @jve:decl-index=0:visual-constraint="10,10"
