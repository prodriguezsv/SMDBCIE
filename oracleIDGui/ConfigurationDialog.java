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
import javax.swing.JCheckBox;

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
	
	private JCheckBox jcbRMA = null;
	private JCheckBox jcbIsMainContainer = null;
	
	private String name = null;
	private String host = null;
	private boolean isMainContainer = true;
	private boolean openRMI = false;
	
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

	public boolean isMainContainer() {
		return isMainContainer;
	}

	public void setMainContainer(boolean isMainContainer) {
		this.isMainContainer = isMainContainer;
	}

	public boolean isOpenRMI() {
		return openRMI;
	}

	public void setOpenRMI(boolean openRMI) {
		this.openRMI = openRMI;
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
			jLabelMainContainerHost.setText("Host del contenedor principal:");
			jLabelIdentSystem = new JLabel();
			jLabelIdentSystem.setText("Nombre del sistema de identificación:");
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(3);
			gridLayout1.setColumns(2);
			jpParameters = new JPanel();
			jpParameters.setName("jpParameters");
			jpParameters.setLayout(gridLayout1);
			jpParameters.add(jLabelIdentSystem, null);
			jpParameters.add(getJcbIdentSystem(), null);
			jpParameters.add(jLabelMainContainerHost, null);
			jpParameters.add(getJcbMainContainerHost(), null);
			jpParameters.add(getJcbIsMainContainer(), null);
			jpParameters.add(getJcbRMA(), null);
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
			jcbIdentSystem.setEditable(true);
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
			jcbMainContainerHost.setSelectedItem("LOCALHOST");
			jcbMainContainerHost.setEditable(true);
		}
		
		jcbMainContainerHost.addItem("LOCALHOST");
		
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
					isMainContainer = jcbIsMainContainer.isSelected();
					openRMI = jcbRMA.isSelected();
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

	/**
	 * This method initializes jcbRMI	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJcbRMA() {
		if (jcbRMA == null) {
			jcbRMA = new JCheckBox();
			jcbRMA.setText("¿Abrir RMA?");
			jcbRMA.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					setOpenRMI(jcbRMA.isSelected());
				}
			});
		}
		return jcbRMA;
	}

	/**
	 * This method initializes jcbIsMainContainer	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJcbIsMainContainer() {
		if (jcbIsMainContainer == null) {
			jcbIsMainContainer = new JCheckBox();
			jcbIsMainContainer.setText("¿Es contenedor principal?");
			jcbIsMainContainer.setSelected(true);
		}
		return jcbIsMainContainer;
	}	

}  //  @jve:decl-index=0:visual-constraint="10,10"
