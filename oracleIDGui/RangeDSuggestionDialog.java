package oracleIDGui;

import javax.swing.JPanel;

import java.awt.Frame;
import java.awt.BorderLayout;

import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class RangeDSuggestionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jpButtons = null;
	private JButton jbAccept = null;
	private JButton jbReject = null;
	private JButton jbDoubt = null;
	private JButton jbCancel = null;
	private JTextArea jLabelIndication = null;
	private JTextField jtfValue = null;
	
	public enum Response {ACEPT, REJECT, DOUBT, CANCEL};
	private Response response;
	private String value;
	
	/**
	 * @param owner
	 */
	public RangeDSuggestionDialog(Frame owner) {
		super(owner);
		initialize();
	}
	
	/**
	 * @param owner
	 */
	public RangeDSuggestionDialog(OracleIDGui owner, String indication, String value) {
		super(owner);
		initialize();
		setLocation();
		jLabelIndication.setText(indication);
		jtfValue.setText(value);
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
		this.setSize(300, 200);
		this.setModal(true);
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
			jContentPane.add(getJpButtons(), java.awt.BorderLayout.SOUTH);
			jContentPane.add(getJLabelIndication(), BorderLayout.NORTH);
			jContentPane.add(getJtfValue(), BorderLayout.CENTER);
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
			gridLayout.setVgap(5);
			gridLayout.setHgap(5);
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
					if (!jtfValue.getText().equals("")) {
						try {
							Double.parseDouble(jtfValue.getText());
							response = Response.ACEPT;
							value = (String)jtfValue.getText();
							setVisible(false);
						} catch (Exception exception) {
							JOptionPane.showMessageDialog(null, "Introduzca un número para la estructura y atributo seleccionado.",
									"OracleID", JOptionPane.INFORMATION_MESSAGE);
							jtfValue.setText("");
						}						
					} else {
						JOptionPane.showMessageDialog(null, "Digite un valor numérico para el atributo de la estructura indicada",
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
	 * This method initializes jLabelIndication	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJLabelIndication() {
		if (jLabelIndication == null) {
			jLabelIndication = new JTextArea();
			jLabelIndication.setBackground(new Color(238, 238, 238));
			jLabelIndication.setEditable(false);
			jLabelIndication.setLineWrap(true);
			jLabelIndication.setWrapStyleWord(true);
			jLabelIndication.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		return jLabelIndication;
	}

	/**
	 * This method initializes jtfValue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfValue() {
		if (jtfValue == null) {
			jtfValue = new JTextField();
		}
		return jtfValue;
	}

	public void setLocation() {
		//pack(); //Reajustar al tamaño preferido 
		int centerX = (int) this.getOwner().getLocation().getX() + this.getOwner().getWidth()/ 2;
		int centerY = (int) this.getOwner().getLocation().getY() + this.getOwner().getHeight()/ 2;
				
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
	}	
}
