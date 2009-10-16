/**
 * 
 */
package oracleIDGui;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;

/**
 * @author Armando
 *
 */
public class SingleDSuggestionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jpButtons = null;
	private JButton jbAccept = null;
	private JButton jbReject = null;
	private JButton jbDoubt = null;
	private JButton jbCancel = null;

	private JList jlValues = null;
	private JScrollPane jspValues = null;
	
	private JTextArea jLabelIndication = null;
	
	public enum Response {ACEPT, REJECT, DOUBT, CANCEL};
	private Response response;
	private String value;

	/**
	 * @param owner
	 */
	public SingleDSuggestionDialog(Frame owner) {
		super(owner);
		initialize();
	}
	
	/**
	 * @param owner
	 */
	public SingleDSuggestionDialog(OracleIDGui owner, String indication, Object values[]) {
		super(owner);
		initialize();
		setLocation();
		jLabelIndication.setText(indication);
		jlValues.setListData(values);
		this.setVisible(true);
	}
	
	public Response getResponse() {
		return response;
	}
	
	public String getValue() {
		return value;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(324, 266);
		this.setModal(true);
		this.setTitle("OracleID");
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				response = Response.CANCEL;
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(5);
			borderLayout.setVgap(5);
			jContentPane = new JPanel();
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getJpButtons(), BorderLayout.SOUTH);
			jContentPane.add(getJspValues(), BorderLayout.CENTER);
			jContentPane.add(getJLabelIndication(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jpButtons	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpButtons() {
		if (jpButtons == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setHgap(5);
			gridLayout.setVgap(5);
			gridLayout.setColumns(2);
			jpButtons = new JPanel();
			jpButtons.setLayout(gridLayout);
			jpButtons.add(getJbAccept(), null);
			jpButtons.add(getJbReject(), null);
			jpButtons.add(getJbDoubt(), null);
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
			jbAccept.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (jlValues.getSelectedValue() != null) {
						response = Response.ACEPT;
						value = (String)jlValues.getSelectedValue();
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un valor de las opciones posibles",
								"OracleID", JOptionPane.INFORMATION_MESSAGE);					
					}
				}
			});
		}
		return jbAccept;
	}

	/**
	 * This method initializes jbReject	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbReject() {
		if (jbReject == null) {
			jbReject = new JButton();
			jbReject.setText("Rechazar");
			jbReject.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					response = Response.REJECT;
					setVisible(false);
				}
			});
		}
		return jbReject;
	}

	/**
	 * This method initializes jbDoubt	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbDoubt() {
		if (jbDoubt == null) {
			jbDoubt = new JButton();
			jbDoubt.setText("Dudar");
			jbDoubt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					response = Response.DOUBT;
					setVisible(false);
				}
			});
		}
		return jbDoubt;
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
			jbCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					response = Response.CANCEL;
					setVisible(false);
				}
			});
		}
		return jbCancel;
	}

	/**
	 * This method initializes jlValues	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJlValues() {
		if (jlValues == null) {
			jlValues = new JList();
		}
		return jlValues;
	}

	/**
	 * This method initializes jspValues	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspValues() {
		if (jspValues == null) {
			jspValues = new JScrollPane();
			jspValues.setViewportView(getJlValues());
		}
		return jspValues;
	}

	/**
	 * This method initializes jLabelIndication	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJLabelIndication() {
		if (jLabelIndication == null) {
			jLabelIndication = new JTextArea();
			jLabelIndication.setLineWrap(true);
			jLabelIndication.setFont(new Font("Dialog", Font.BOLD, 12));
			jLabelIndication.setWrapStyleWord(true);
			jLabelIndication.setEditable(false);
			jLabelIndication.setBackground(new Color(238, 238, 238));
		}
		return jLabelIndication;
	}
	
	public void setLocation() {
		pack(); //Reajustar al tamaño preferido 
		int centerX = (int) this.getOwner().getLocation().getX() + this.getOwner().getWidth()/ 2;
		int centerY = (int) this.getOwner().getLocation().getY() + this.getOwner().getHeight()/ 2;
				
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
	}	

}  //  @jve:decl-index=0:visual-constraint="10,10"
