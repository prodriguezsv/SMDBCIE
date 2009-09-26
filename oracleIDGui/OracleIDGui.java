/**
 * 
 */
package oracleIDGui;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import edu.stanford.smi.protege.model.*;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;

/**
 * @author Armando
 *
 */
public class OracleIDGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jmiExpectations = null;
	private JTabbedPane jtpCase = null;
	private JPanel jpProblem = null;
	private JList jlStructures = null;
	private JList jlAttributes = null;
	private JComboBox jcbValues = null;
	private JLabel jLabelStructures = null;
	private JLabel jLabelAttributes = null;
	private JLabel jLabelValue = null;
	private JList jlDescription = null;
	private JLabel jLabelDescription = null;
	private JButton jbAdd = null;
	private JButton jbRemove = null;
	private JButton jbNewProblem = null;
	private JButton jbIdentify = null;
	private JPanel jpSolution = null;
	private JMenuItem jmiExit = null;
	private JScrollPane jspStructures = null;
	private JScrollPane jspAttributes = null;
	private JScrollPane jspDescription = null;
	private KnowledgeBase kb = null;
	private Map<String, String> structuresScope = null;  //  @jve:decl-index=0:
	private Map<String, String> attributesScope = null;
	private Map<String, String> valuesScope = null;
	final private String PROJECT_FILE_NAME = "c:\\eclipse\\Projects\\Tests\\OracleID\\rsc\\ProtegeOntologies\\" +
		"commonOntology.pprj";  //  @jve:decl-index=0:
	private JScrollPane jspValues = null;
	private JList jlValues = null;
	/**
	 * @throws HeadlessException
	 */
	public OracleIDGui() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	/**
	 * @param arg0
	 */
	public OracleIDGui(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public OracleIDGui(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public OracleIDGui(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}


	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setName("jMenu");
			jMenu.setMnemonic(KeyEvent.VK_S);
			jMenu.setText("Sistema");
			jMenu.add(getJmiExpectations());
			jMenu.add(getJmiExit());
			jMenu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jMenu;
	}

	/**
	 * This method initializes jmiExpectations	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJmiExpectations() {
		if (jmiExpectations == null) {
			jmiExpectations = new JMenuItem();
			jmiExpectations.setText("Expectativas...");
		}
		return jmiExpectations;
	}

	/**
	 * This method initializes jtpCase	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJtpCase() {
		if (jtpCase == null) {
			jtpCase = new JTabbedPane();
			jtpCase.setName("jtpCase");
			jtpCase.addTab("Problema", null, getJpProblem(), null);
			jtpCase.addTab("Solución", null, getJpSolution(), null);
		}
		return jtpCase;
	}

	/**
	 * This method initializes jpProblem	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpProblem() {
		if (jpProblem == null) {
			jLabelDescription = new JLabel();
			jLabelDescription.setText("Description de problema actual");
			jLabelDescription.setSize(new Dimension(495, 24));
			jLabelDescription.setLocation(new Point(19, 224));
			jLabelValue = new JLabel();
			jLabelValue.setText("Valor");
			jLabelValue.setSize(new Dimension(167, 23));
			jLabelValue.setLocation(new Point(538, 14));
			jLabelAttributes = new JLabel();
			jLabelAttributes.setBounds(new Rectangle(278, 15, 235, 23));
			jLabelAttributes.setText("Atributo");
			jLabelStructures = new JLabel();
			jLabelStructures.setBounds(new Rectangle(18, 15, 234, 24));
			jLabelStructures.setText("Estructura");
			jpProblem = new JPanel();
			jpProblem.setLayout(null);
			jpProblem.add(getJcbValues(), null);
			jpProblem.add(getJspStructures(), null);
			jpProblem.add(jLabelStructures, null);
			jpProblem.add(jLabelAttributes, null);
			jpProblem.add(jLabelValue, null);
			jpProblem.add(jLabelDescription, null);
			jpProblem.add(getJbAdd(), null);
			jpProblem.add(getJbRemove(), null);
			jpProblem.add(getJbNewProblem(), null);
			jpProblem.add(getJbIdentify(), null);
			jpProblem.add(getJspAttributes(), null);
			jpProblem.add(getJspDescription(), null);
			jpProblem.add(getJspValues(), null);
		}
		return jpProblem;
	}

	/**
	 * This method initializes jlStructures	
	 * 	
	 * @return javax.swing.JList	
	 */
	@SuppressWarnings("unchecked")
	private JList getJlStructures() {
		
		if (jlStructures == null) {
			jlStructures = new JList();
			jlStructures.setName("jlStructures");
			jlStructures.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jlStructures.setToolTipText("Seleccione el nombre de una estructura");
			jlStructures
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							attributesScope = new HashMap<String, String>();
							
							Instance structureInstance = null;
							if (jlStructures.getSelectedValue() != null)
								structureInstance = kb.getInstance(structuresScope.get(jlStructures.getSelectedValue()));
							
							if (structureInstance != null) {
								List<Instance> tempInstanceValues = 
									new ArrayList<Instance>(structureInstance.getOwnSlotValues(kb.getSlot( "owns" )));
	
							    if (tempInstanceValues != null) {
							      for (Instance instValue : tempInstanceValues) {
							    	  attributesScope.put((String)instValue.getOwnSlotValue(kb.getSlot("term")), instValue.getName());
							      }
							    }
							}
							
				            jlAttributes.setListData(attributesScope.keySet().toArray());
				            jlValues.setListData(new ArrayList().toArray());
						}
					});
			
	    	structuresScope = new HashMap<String, String>();
	        
            Cls cls = (Cls) kb.getCls("Structure");
            
            Iterator j = cls.getDirectInstances().iterator();
            while (j.hasNext()) {
            	Instance instance = (Instance) j.next();
            	structuresScope.put((String)instance.getOwnSlotValue(kb.getSlot("term")), instance.getName());		                    
            }
	        
            cls = (Cls) kb.getCls("EnvironmentalCategory");
            
            j = cls.getDirectInstances().iterator();
            while (j.hasNext()) {
            	Instance instance = (Instance) j.next();
            	structuresScope.put((String)instance.getOwnSlotValue(kb.getSlot("term")), instance.getName());		                    
            }
            
            jlStructures.setListData(structuresScope.keySet().toArray());
		}
		return jlStructures;
	}

	/**
	 * This method initializes jlAttributes	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJlAttributes() {
		if (jlAttributes == null) {
			jlAttributes = new JList();
			jlAttributes.setName("jlAttributes");
			jlAttributes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jlAttributes.setToolTipText("Seleccione el nombre de un atributo");
			jlAttributes.setSize(new Dimension(233, 180));
			jlAttributes.setFont(new Font("Dialog", Font.BOLD, 12));
			jlAttributes
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						@SuppressWarnings("unchecked")
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							valuesScope = new HashMap<String, String>();
							Instance attributeInstance = null;
							
							if (jlAttributes.getSelectedValue() != null)
								attributeInstance = kb.getInstance(attributesScope.get(jlAttributes.getSelectedValue()));			
						
							if (attributeInstance != null) {
								List<Instance> tempInstanceValues = 
									new ArrayList<Instance>(attributeInstance.getOwnSlotValues(kb.getSlot("describedBy")));
	
							    if (tempInstanceValues != null) {
							      for (Instance instValue : tempInstanceValues) {
							    	  if (instValue.hasType(kb.getCls("State") )) {
							    		  valuesScope.put((String)instValue.getOwnSlotValue(kb.getSlot("term")), instValue.getName());  
							    	  }						    	  
							      }
							    }
							}
							
				            jlValues.setListData(valuesScope.keySet().toArray());
						}
					});
		}
		return jlAttributes;
	}

	/**
	 * This method initializes jcbValues	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJcbValues() {
		if (jcbValues == null) {
			jcbValues = new JComboBox();
			jcbValues.setEditable(true);
			jcbValues.setToolTipText("Proporcione un valor para la estructura y atributo seleccionado");
			jcbValues.setLocation(new Point(538, 39));
			jcbValues.setSize(new Dimension(233, 31));
			jcbValues.setName("jcbValue");
		}
		return jcbValues;
	}

	/**
	 * This method initializes jlDescription	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJlDescription() {
		if (jlDescription == null) {
			jlDescription = new JList();
		}
		return jlDescription;
	}

	/**
	 * This method initializes jbAdd	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAdd() {
		if (jbAdd == null) {
			jbAdd = new JButton();
			jbAdd.setBounds(new Rectangle(546, 260, 164, 26));
			jbAdd.setText("Agregar descriptor");
		}
		return jbAdd;
	}

	/**
	 * This method initializes jbRemove	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbRemove() {
		if (jbRemove == null) {
			jbRemove = new JButton();
			jbRemove.setText("Remover descriptor");
			jbRemove.setSize(new Dimension(164, 26));
			jbRemove.setLocation(new Point(546, 317));
		}
		return jbRemove;
	}

	/**
	 * This method initializes jbNewProblem	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbNewProblem() {
		if (jbNewProblem == null) {
			jbNewProblem = new JButton();
			jbNewProblem.setText("Nuevo problema");
			jbNewProblem.setSize(new Dimension(164, 26));
			jbNewProblem.setLocation(new Point(546, 367));
		}
		return jbNewProblem;
	}

	/**
	 * This method initializes jbIdentify	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbIdentify() {
		if (jbIdentify == null) {
			jbIdentify = new JButton();
			jbIdentify.setText("Identificar espécimen");
			jbIdentify.setSize(new Dimension(164, 26));
			jbIdentify.setLocation(new Point(546, 418));
		}
		return jbIdentify;
	}

	/**
	 * This method initializes jpSolution	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpSolution() {
		if (jpSolution == null) {
			jpSolution = new JPanel();
			jpSolution.setLayout(new GridBagLayout());
		}
		return jpSolution;
	}

	/**
	 * This method initializes jmiExit	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJmiExit() {
		if (jmiExit == null) {
			jmiExit = new JMenuItem();
			jmiExit.setText("Salir");
		}
		
		return jmiExit;
	}

	/**
	 * This method initializes jspStructures	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspStructures() {
		if (jspStructures == null) {
			jspStructures = new JScrollPane();
			jspStructures.setLocation(new Point(18, 41));
			jspStructures.setViewportView(getJlStructures());
			jspStructures.setSize(new Dimension(236, 183));
		}
		return jspStructures;
	}

	/**
	 * This method initializes jspAttributes	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspAttributes() {
		if (jspAttributes == null) {
			jspAttributes = new JScrollPane();
			jspAttributes.setLocation(new Point(278, 40));
			jspAttributes.setViewportView(getJlAttributes());
			jspAttributes.setSize(new Dimension(236, 183));
		}
		return jspAttributes;
	}

	/**
	 * This method initializes jspDescription	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspDescription() {
		if (jspDescription == null) {
			jspDescription = new JScrollPane();
			jspDescription.setLocation(new Point(18, 249));
			jspDescription.setViewportView(getJlDescription());
			jspDescription.setSize(new Dimension(497, 200));
		}
		return jspDescription;
	}

	/**
	 * This method initializes jspValues	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspValues() {
		if (jspValues == null) {
			jspValues = new JScrollPane();
			jspValues.setLocation(new Point(540, 77));
			jspValues.setViewportView(getJlValues());
			jspValues.setSize(new Dimension(233, 145));
		}
		return jspValues;
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				OracleIDGui thisClass = new OracleIDGui();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		Collection<String> errors = new ArrayList<String>();
    	
		Project project  = new Project(PROJECT_FILE_NAME, errors);
        
    	kb = project.getKnowledgeBase();
        
        if (errors.size() != 0) {
        	Iterator<String> i = errors.iterator();
            while (i.hasNext()) {
            	System.out.println("Error: " + i.next());
            }
        }
        
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("OracleID");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new CardLayout());
			jContentPane.add(getJtpCase(), getJtpCase().getName());
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="13,35"
