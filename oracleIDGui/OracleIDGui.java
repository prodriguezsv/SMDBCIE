/**
 * 
 */
package oracleIDGui;

import javax.swing.JPanel;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import edu.stanford.smi.protege.model.*;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ontology.CBR.Case;
import ontology.CBR.Problem;
import ontology.CBR.ProposedSolution;
import ontology.CBR.Solution;
import ontology.common.Descriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SSHeuristicDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SVHeuristicDescriptor;
import ontology.common.SingleValue;
import ontology.taxonomy.Taxon;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.ComponentOrientation;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import system.InterfaceAgent;
import system.OracleIDSystem;

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
	private JComboBox jcbMeasuringUnit = null;
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
	private JScrollPane jspValues = null;
	private JList jlValues = null;
	private JTextField jtfValue = null;
	private JPanel jpProblemNorthernSide = null;
	private JPanel jpProblemSouthernSide = null;
	private JPanel jpStructure = null;
	private JPanel jpAtribute = null;
	private JPanel jpValue = null;
	private JPanel jpButtons = null;
	private JLabel jLabelProposals = null;
	private JPanel jpGeneralInfo = null;
	private JLabel jLabelName = null;
	private JTextField jtfName = null;
	private JLabel jLabelRank = null;
	private JTextField jtfRank = null;
	private JLabel jLabelCertaintyDegree = null;
	private JTextField jtfCertaintyDegree = null;
	private JCheckBox jcbState = null;
	private JPanel jpNorthernSolution = null;
	private JPanel jpSouthernSolution = null;
	private JPanel jpSelectDescription = null;
	private JLabel jLabelSelectDescription = null;
	private JPanel jpDescriptions = null;
	private JRadioButton jrbSolution = null;
	private JRadioButton jrbConfirmed = null;
	private JRadioButton jrbDoubtful = null;
	private JRadioButton jrbUnconfirmed = null;
	private JRadioButton jrbContradictions = null;
	private JScrollPane jspDescriptions = null;
	private JList jlDescriptions = null;
	private JPanel jpSolutionButtons = null;
	private JButton jbSeeSolution = null;
	private JButton jbNext = null;
	private JButton jbAccept = null;
	
	private InterfaceAgent myAgent;
	private KnowledgeBase kb = null;
	private Map<String, String> structuresScope = null;  //  @jve:decl-index=0:
	private Map<String, String> attributesScope = null;
	private Map<String, String> valuesScope = null;  //  @jve:decl-index=0:
	private List<String> measuringUnit = null;  //  @jve:decl-index=0:
	private Problem problem = null;
	private int proposedSolution = 0;
	private jade.util.leap.List proposedSolutions;  //  @jve:decl-index=0:
	/**
	 * @throws HeadlessException
	 */
	public OracleIDGui() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	/**
	 * @throws HeadlessException
	 */
	public OracleIDGui(InterfaceAgent agent) throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		this.myAgent = agent;
		this.kb = OracleIDSystem.getInstance().getCommonKb();
		this.problem = new Problem();
		this.proposedSolutions = new jade.util.leap.ArrayList();
		initialize();
	}

	public void setProposedSolutions(jade.util.leap.List proposedSolutions) {
		this.proposedSolutions = proposedSolutions;
	}

	public jade.util.leap.List getProposedSolutions() {
		return proposedSolutions;
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
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(10);
			borderLayout.setVgap(10);
			jLabelDescription = new JLabel();
			jLabelDescription.setText("Descripción de problema actual");
			jLabelValue = new JLabel();
			jLabelValue.setText("Valor");
			jLabelAttributes = new JLabel();
			jLabelAttributes.setText("Atributo");
			jLabelStructures = new JLabel();
			jLabelStructures.setText("Estructura/Heurística");
			jpProblem = new JPanel();
			jpProblem.setLayout(borderLayout);
			jpProblem.add(getJpProblemNorthernSide(), BorderLayout.NORTH);
			jpProblem.add(getJpProblemSouthernSide(), BorderLayout.CENTER);
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
							attributesScope.clear();
							
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
				            jtfValue.setText("");
						}
					});
			
	    	structuresScope = new HashMap<String, String>();
	    	attributesScope = new HashMap<String, String>();
	        
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
							valuesScope.clear();
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
							    		  jcbMeasuringUnit.setEnabled(false);
							    	  }	 else if (instValue.hasType(kb.getCls("SingleValue") )) {
							    		  valuesScope.put((String)instValue.getOwnSlotValue(kb.getSlot("value")), instValue.getName());
							    		  jcbMeasuringUnit.setEnabled(true);
							    	  } else {
							    		  //valuesScope.put(instValue.getName(), instValue.getName());
							    		  jcbMeasuringUnit.setEnabled(true);
							    		  jcbMeasuringUnit.setSelectedItem(instValue.getOwnSlotValue(kb.getSlot("measuringUnit")));
							    	  }
							      }
							    }
							}
							
				            jlValues.setListData(valuesScope.keySet().toArray());
				            jtfValue.setText("");
						}
					});
			
			valuesScope = new HashMap<String, String>();
		}
		return jlAttributes;
	}

	/**
	 * This method initializes jcbMeasuringUnit	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getJcbMeasuringUnit() {
		if (jcbMeasuringUnit == null) {
			jcbMeasuringUnit = new JComboBox();
			jcbMeasuringUnit.setEditable(false);
			jcbMeasuringUnit.setToolTipText("Proporcione un valor para la estructura y atributo seleccionado");
			jcbMeasuringUnit.setEnabled(true);
			jcbMeasuringUnit.setName("jcbMeasuringUnit");
		}
		
		Cls cls = (Cls) kb.getCls("Value");
	    measuringUnit = new ArrayList<String>(cls.getTemplateSlotAllowedValues(kb.getSlot("measuringUnit")));
	    for (String mu:measuringUnit) {
        	jcbMeasuringUnit.addItem(mu);
        }
         
		return jcbMeasuringUnit;
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
			jbAdd.setText("Agregar descriptor");
			jbAdd.setName("jbAdd");
			jbAdd.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Descriptor descriptor = null;
					if (problem == null)
						problem = new Problem();
					
					Instance instValue;
					
					if (jlStructures.getSelectedValue() != null &&
							jlAttributes.getSelectedValue() != null &&
							(jlValues.getSelectedValue() != null ||
							!jtfValue.getText().equals(""))) {
						if (valuesScope.isEmpty()) {
							instValue = kb.getInstance(structuresScope.get((String)jlStructures.getSelectedValue()));
							
							if (instValue.hasType(kb.getCls("Structure"))) {
								descriptor = new SVCharacterDescriptor((String)jlStructures.getSelectedValue(), 
										(String)jlAttributes.getSelectedValue(),
										new SingleValue(Double.parseDouble(jtfValue.getText()),	(String)jcbMeasuringUnit.getSelectedItem()));
							} else {
								descriptor = new SVHeuristicDescriptor((String)jlStructures.getSelectedValue(), 
										(String)jlAttributes.getSelectedValue(),
										new SingleValue(Double.parseDouble(jtfValue.getText()), (String)jcbMeasuringUnit.getSelectedItem()));
							}
						} else {
							
							instValue = kb.getInstance(structuresScope.get((String)jlStructures.getSelectedValue()));
							
							if (instValue.hasType(kb.getCls("Structure"))) {
								if (jlValues.getSelectedValue() != null) {
									instValue = kb.getInstance(valuesScope.get((String)jlValues.getSelectedValue()));		
									if (instValue.hasType(kb.getCls("State")))
										descriptor = new SSCharacterDescriptor((String)jlStructures.getSelectedValue(), 
												(String)jlAttributes.getSelectedValue(),
												(String)jlValues.getSelectedValue());
									else
										descriptor = new SVCharacterDescriptor((String)jlStructures.getSelectedValue(), 
												(String)jlAttributes.getSelectedValue(),
												new SingleValue(Double.parseDouble((String)jlValues.getSelectedValue()), (String)jcbMeasuringUnit.getSelectedItem()));
								} else {
									instValue = kb.getInstance(valuesScope.values().iterator().next());
									if (instValue.hasType(kb.getCls("State")))
										descriptor = new SSCharacterDescriptor((String)jlStructures.getSelectedValue(), 
												(String)jlAttributes.getSelectedValue(),
												(String)jtfValue.getText());
									else
										descriptor = new SVCharacterDescriptor((String)jlStructures.getSelectedValue(), 
												(String)jlAttributes.getSelectedValue(),
												new SingleValue(Double.parseDouble((String)(String)jtfValue.getText()), (String)jcbMeasuringUnit.getSelectedItem()));
								}								
							} else {
								if (jlValues.getSelectedValue() != null) {
									instValue = kb.getInstance(valuesScope.get((String)jlValues.getSelectedValue()));
									
									if (instValue.hasType(kb.getCls("State")))
										descriptor = new SSHeuristicDescriptor((String)jlStructures.getSelectedValue(), 
												(String)jlAttributes.getSelectedValue(),
												(String)jlValues.getSelectedValue());
									else
										descriptor = new SVHeuristicDescriptor((String)jlStructures.getSelectedValue(), 
												(String)jlAttributes.getSelectedValue(),
												new SingleValue(Double.parseDouble((String)jlValues.getSelectedValue()), (String)jcbMeasuringUnit.getSelectedItem()));
								} else {
									instValue = kb.getInstance(valuesScope.values().iterator().next());
									
									if (instValue.hasType(kb.getCls("State")))
										descriptor = new SSHeuristicDescriptor((String)jlStructures.getSelectedValue(), 
												(String)jlAttributes.getSelectedValue(),
												(String)(String)jtfValue.getText());
									else
										descriptor = new SVHeuristicDescriptor((String)jlStructures.getSelectedValue(), 
												(String)jlAttributes.getSelectedValue(),
												new SingleValue(Double.parseDouble((String)jtfValue.getText()), (String)jcbMeasuringUnit.getSelectedItem()));
								}														
							}
							
						}
						
						problem.getDescription().addToConcreteDescription(descriptor);										
						
						jlDescription.setListData(problem.getDescription().getDescriptors().toArray());
					} else {
						JOptionPane.showMessageDialog(null, "Proporcione un valor para la estructura y atributo seleccionado",
								"OracleID", JOptionPane.INFORMATION_MESSAGE);
					}									
				}
			});
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
			jbRemove.setName("jbRemove");
			jbRemove.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (jlDescription.getSelectedValue() != null) {
						problem.getDescription().getDescriptors().remove(jlDescription.getSelectedIndex());
						jlDescription.setListData(problem.getDescription().getDescriptors().toArray());
					}									
				}
			});
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
			jbNewProblem.setName("jbNewProblem");
			jbNewProblem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					clearProblem();
					clearSolution();
				}
			});
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
			jbIdentify.setName("jbIdentify");
			jbIdentify.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {										
					if ( !problem.getDescription().getDescriptors().isEmpty() ) {
						clearSolution();
						
						myAgent.identifySpecimen(problem);												
					} else {
						JOptionPane.showMessageDialog(null, "Proporcione una descripción del espécimen.",
								"OracleID", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}
		return jbIdentify;
	}
	
	public void presentFirstSolution() {
		jLabelProposals.setText("Propuesta 1 de " + getProposedSolutions().size());
		
		jcbState.setSelected(((ProposedSolution)getProposedSolutions().get(0)).getState());
		jtfCertaintyDegree.setText(((ProposedSolution)getProposedSolutions()
				.get(0)).getCertaintyDegree());
		Object solution = ((ProposedSolution)getProposedSolutions()
				.get(proposedSolution)).getSolution().getSolution();
		
		if (solution instanceof Taxon) {
			jtfName.setText(((Taxon)solution).getName());
			jtfRank.setText(((Taxon)solution).getLevel());
			jbSeeSolution.setText("Ver taxón");								
		} else {
			jtfName.setText(((Case)solution).getSolution().getTaxonName());
			jtfRank.setText(((Case)solution).getSolution().getTaxonLevel());
			jbSeeSolution.setText("Ver caso");
		}
		
		jlDescriptions.setListData(((ProposedSolution)getProposedSolutions()
				.get(proposedSolution)).getSolution().getSolutionDescription().getDescriptors().toArray());
		jtpCase.setSelectedIndex(1);		
	}
	
	private void clearSolution() {
		jLabelProposals.setText("Propuesta 0 de 0");
		jtfName.setText("");
		jtfRank.setText("");
		jtfCertaintyDegree.setText("");
		jcbState.setSelected(false);
		jlDescriptions.setListData((new ArrayList<String>()).toArray());						
		jrbSolution.setSelected(true);
		jrbConfirmed.setSelected(false);
		jrbDoubtful.setSelected(false);
		jrbUnconfirmed.setSelected(false);
		jrbContradictions.setSelected(false);	
		this.getProposedSolutions().clear();
		proposedSolution = 0;
	}
	
	private void clearProblem() {
		jlStructures.clearSelection();
		jlAttributes.clearSelection();
		jlAttributes.setListData((new ArrayList<String>()).toArray());
		jlValues.clearSelection();
		jlValues.setListData((new ArrayList<String>()).toArray());
		jtfValue.setText("");
		jlDescription.setListData((new ArrayList<String>()).toArray());
		this.problem.getDescription().clearAllDescriptors();
	}

	/**
	 * This method initializes jpSolution	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpSolution() {
		if (jpSolution == null) {
			BorderLayout borderLayout2 = new BorderLayout();
			borderLayout2.setHgap(10);
			borderLayout2.setVgap(10);
			jLabelProposals = new JLabel();
			jLabelProposals.setText("Propuesta 0 de 0");
			jLabelProposals.setHorizontalAlignment(SwingConstants.CENTER);
			jpSolution = new JPanel();
			jpSolution.setVisible(true);
			jpSolution.setLayout(borderLayout2);
			jpSolution.add(getJpNorthernSolution(), BorderLayout.NORTH);
			jpSolution.add(getJpSouthernSolution(), BorderLayout.CENTER);
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
			jmiExit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					OracleIDSystem.getInstance().cleanup();
					System.exit(0);
				}
			});
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
			jspStructures.setViewportView(getJlStructures());
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
			jspAttributes.setViewportView(getJlAttributes());
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
			jspDescription.setViewportView(getJlDescription());
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
			jspValues.setViewportView(getJlValues());
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
			jlValues.setToolTipText("Seleccione un valor para la estructura y atributo seleccionado");
			jlValues
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							Instance instValue = null;
							if (jlValues.getSelectedValue() != null)
								instValue = kb.getInstance(valuesScope.get(jlValues.getSelectedValue()));
							
							if (instValue !=  null) 
								if (instValue.hasType(kb.getCls("SingleValue")))
									jcbMeasuringUnit.setSelectedItem(instValue.getOwnSlotValue(kb.getSlot("measuringUnit")));
						}
					});
		}
		return jlValues;
	}

	/**
	 * This method initializes jtfValue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfValue() {
		if (jtfValue == null) {
			jtfValue = new JTextField();
			jtfValue.setName("jtfValue");
			jtfValue.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					jlValues.clearSelection();
				}
			});
			jtfValue.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					if (!jtfValue.getText().equals("")) {
						if (valuesScope.isEmpty()) {
							try {
								Double.parseDouble(jtfValue.getText());
							} catch (Exception exception) {
								JOptionPane.showMessageDialog(null, "Introduzca un número para la estructura y atributo seleccionado.",
										"OracleID", JOptionPane.INFORMATION_MESSAGE);
								jtfValue.setText("");
							}
						} else {
							Instance instValue = kb.getInstance(valuesScope.values().iterator().next());
							
							if (instValue.hasType(kb.getCls("SingleValue"))) {
								try {
									Double.parseDouble(jtfValue.getText());
								} catch (Exception exception) {
									JOptionPane.showMessageDialog(null, "Introduzca un número para la estructura y atributo seleccionado.",
											"OracleID", JOptionPane.INFORMATION_MESSAGE);
									jtfValue.setText("");
								}
							}
						}
					}
				}
			});
		}
		return jtfValue;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(0, 0, 649, 487));
		this.setEnabled(true);
		//this.setPreferredSize(new Dimension(10, 10));
		this.setResizable(true);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("OracleID");
		this.setLocation();
		this.setVisible(true);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				OracleIDSystem.getInstance().cleanup();
				System.exit(0);
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
			jContentPane = new JPanel();
			jContentPane.setLayout(new CardLayout());
			jContentPane.add(getJtpCase(), "East");
		}
		return jContentPane;
	}

	/**
	 * This method initializes jpProblemNorthernSide	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpProblemNorthernSide() {
		if (jpProblemNorthernSide == null) {
			jpProblemNorthernSide = new JPanel();
			jpProblemNorthernSide.setLayout(new BoxLayout(getJpProblemNorthernSide(), BoxLayout.X_AXIS));
			jpProblemNorthernSide.add(getJpStructure(), null);
			jpProblemNorthernSide.add(getJpAtribute(), null);
			jpProblemNorthernSide.add(getJpValue(), null);
		}
		return jpProblemNorthernSide;
	}

	/**
	 * This method initializes jpProblemSouthernSide	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpProblemSouthernSide() {
		if (jpProblemSouthernSide == null) {
			jpProblemSouthernSide = new JPanel();
			jpProblemSouthernSide.setLayout(new BorderLayout());
			jpProblemSouthernSide.add(getJpButtons(), BorderLayout.EAST);
			jpProblemSouthernSide.add(jLabelDescription, BorderLayout.NORTH);
			jpProblemSouthernSide.add(getJspDescription(), BorderLayout.CENTER);
		}
		return jpProblemSouthernSide;
	}

	/**
	 * This method initializes jpStructure	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpStructure() {
		if (jpStructure == null) {
			jpStructure = new JPanel();
			jpStructure.setLayout(new BoxLayout(getJpStructure(), BoxLayout.Y_AXIS));
			jpStructure.add(jLabelStructures, null);
			jpStructure.add(getJspStructures(), null);
		}
		return jpStructure;
	}

	/**
	 * This method initializes jpAtribute	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpAtribute() {
		if (jpAtribute == null) {
			jpAtribute = new JPanel();
			jpAtribute.setLayout(new BoxLayout(getJpAtribute(), BoxLayout.Y_AXIS));
			jpAtribute.add(jLabelAttributes, null);
			jpAtribute.add(getJspAttributes(), null);
		}
		return jpAtribute;
	}

	/**
	 * This method initializes jpValue	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpValue() {
		if (jpValue == null) {
			jpValue = new JPanel();
			jpValue.setLayout(new BorderLayout());
			jpValue.add(jLabelValue, BorderLayout.NORTH);
			jpValue.add(getJtfValue(), BorderLayout.CENTER);
			jpValue.add(getJcbMeasuringUnit(), BorderLayout.EAST);
			jpValue.add(getJspValues(), BorderLayout.SOUTH);
		}
		return jpValue;
	}

	/**
	 * This method initializes jpButtons	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpButtons() {
		if (jpButtons == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(4);
			gridLayout.setVgap(5);
			gridLayout.setColumns(1);
			jpButtons = new JPanel();
			jpButtons.setLayout(gridLayout);
			jpButtons.setComponentOrientation(ComponentOrientation.UNKNOWN);
			jpButtons.add(getJbAdd(), null);
			jpButtons.add(getJbRemove(), null);
			jpButtons.add(getJbNewProblem(), null);
			jpButtons.add(getJbIdentify(), null);
		}
		return jpButtons;
	}

	/**
	 * This method initializes jpGeneralInfo	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpGeneralInfo() {
		if (jpGeneralInfo == null) {
			GridLayout gridLayout4 = new GridLayout();
			gridLayout4.setRows(2);
			gridLayout4.setHgap(10);
			gridLayout4.setVgap(10);
			gridLayout4.setColumns(4);
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(2);
			gridLayout2.setColumns(4);
			jLabelCertaintyDegree = new JLabel();
			jLabelCertaintyDegree.setText("Grado de certeza");
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(2);
			jLabelRank = new JLabel();
			jLabelRank.setText("Rango taxonómico");
			jLabelRank.setToolTipText("");
			jLabelName = new JLabel();
			jLabelName.setText("Nombre científico");
			jpGeneralInfo = new JPanel();
			jpGeneralInfo.setLayout(gridLayout4);
			jpGeneralInfo.add(jLabelName, null);
			jpGeneralInfo.add(getJtfName(), null);
			jpGeneralInfo.add(jLabelRank, null);
			jpGeneralInfo.add(getJtfRank(), null);
			jpGeneralInfo.add(jLabelCertaintyDegree, null);
			jpGeneralInfo.add(getJtfCertaintyDegree(), null);
			jpGeneralInfo.add(getJcbState(), null);
		}
		return jpGeneralInfo;
	}

	/**
	 * This method initializes jtfName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfName() {
		if (jtfName == null) {
			jtfName = new JTextField();
			jtfName.setColumns(0);
			jtfName.setEditable(false);
		}
		return jtfName;
	}

	/**
	 * This method initializes jtfRank	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfRank() {
		if (jtfRank == null) {
			jtfRank = new JTextField();
			jtfRank.setEditable(false);
		}
		return jtfRank;
	}

	/**
	 * This method initializes jtfCertaintyDegree	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfCertaintyDegree() {
		if (jtfCertaintyDegree == null) {
			jtfCertaintyDegree = new JTextField();
			jtfCertaintyDegree.setEditable(false);
		}
		return jtfCertaintyDegree;
	}

	/**
	 * This method initializes jcbState	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJcbState() {
		if (jcbState == null) {
			jcbState = new JCheckBox();
			jcbState.setText("¿Es existoso?");
		}
		return jcbState;
	}

	/**
	 * This method initializes jpNorthernSolution	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpNorthernSolution() {
		if (jpNorthernSolution == null) {
			BorderLayout borderLayout1 = new BorderLayout();
			borderLayout1.setHgap(10);
			borderLayout1.setVgap(10);
			jpNorthernSolution = new JPanel();
			jpNorthernSolution.setLayout(borderLayout1);
			jpNorthernSolution.add(jLabelProposals, BorderLayout.NORTH);
			jpNorthernSolution.add(getJpGeneralInfo(), BorderLayout.WEST);
		}
		return jpNorthernSolution;
	}

	/**
	 * This method initializes jpSouthernSolution	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpSouthernSolution() {
		if (jpSouthernSolution == null) {
			BorderLayout borderLayout3 = new BorderLayout();
			borderLayout3.setHgap(5);
			borderLayout3.setVgap(5);
			jpSouthernSolution = new JPanel();
			jpSouthernSolution.setLayout(borderLayout3);
			jpSouthernSolution.add(getJpSelectDescription(), BorderLayout.NORTH);
			jpSouthernSolution.add(getJspDescriptions(), BorderLayout.CENTER);
			jpSouthernSolution.add(getJpSolutionButtons(), BorderLayout.EAST);
		}
		return jpSouthernSolution;
	}

	/**
	 * This method initializes jpSelectDescription	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpSelectDescription() {
		if (jpSelectDescription == null) {
			jLabelSelectDescription = new JLabel();
			jLabelSelectDescription.setText("Selecciona una descripción:");
			jLabelSelectDescription.setHorizontalAlignment(SwingConstants.LEADING);
			jpSelectDescription = new JPanel();
			jpSelectDescription.setLayout(new BorderLayout());
			jpSelectDescription.add(jLabelSelectDescription, BorderLayout.NORTH);
			jpSelectDescription.add(getJpDescriptions(), BorderLayout.CENTER);
		}
		return jpSelectDescription;
	}

	/**
	 * This method initializes jpDescriptions	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpDescriptions() {
		if (jpDescriptions == null) {
			jpDescriptions = new JPanel();
			jpDescriptions.setLayout(new BoxLayout(getJpDescriptions(), BoxLayout.X_AXIS));
			jpDescriptions.add(getJrbSolution(), null);
			jpDescriptions.add(getJrbConfirmed(), null);
			jpDescriptions.add(getJrbDoubtful(), null);
			jpDescriptions.add(getJrbUnconfirmed(), null);
			jpDescriptions.add(getJrbContradictions(), null);
		}
		return jpDescriptions;
	}

	/**
	 * This method initializes jrbSolution	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbSolution() {
		if (jrbSolution == null) {
			jrbSolution = new JRadioButton();
			jrbSolution.setText("De solución");
			jrbSolution.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if (jrbSolution.isSelected()) {
						jrbConfirmed.setSelected(false);
						jrbDoubtful.setSelected(false);
						jrbUnconfirmed.setSelected(false);
						jrbContradictions.setSelected(false);
						
						if (getProposedSolutions().size()>0) {
							
							jlDescriptions.setListData(((ProposedSolution)getProposedSolutions()
									.get(proposedSolution)).getSolution().getSolutionDescription().getDescriptors().toArray());
						}
					}
				}
			});
		}
		return jrbSolution;
	}

	/**
	 * This method initializes jrbConfirmed	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbConfirmed() {
		if (jrbConfirmed == null) {
			jrbConfirmed = new JRadioButton();
			jrbConfirmed.setText("Confirmada");
			jrbConfirmed.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if (jrbConfirmed.isSelected()) {
						jrbSolution.setSelected(false);
						jrbDoubtful.setSelected(false);
						jrbUnconfirmed.setSelected(false);
						jrbContradictions.setSelected(false);
						
						if (getProposedSolutions().size()>0) {
							
							jlDescriptions.setListData(((ProposedSolution)getProposedSolutions()
									.get(proposedSolution)).getSolution().getConfirmedDescription().getDescriptors().toArray());
						}
					}
				}
			});
		}
		return jrbConfirmed;
	}

	/**
	 * This method initializes jrbDoubtful	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbDoubtful() {
		if (jrbDoubtful == null) {
			jrbDoubtful = new JRadioButton();
			jrbDoubtful.setText("Dudosa");
			jrbDoubtful.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if (jrbDoubtful.isSelected()) {
						jrbSolution.setSelected(false);
						jrbConfirmed.setSelected(false);
						jrbUnconfirmed.setSelected(false);
						jrbContradictions.setSelected(false);
						
						if (getProposedSolutions().size()>0) {
							
							jlDescriptions.setListData(((ProposedSolution)getProposedSolutions()
									.get(proposedSolution)).getSolution().getDoubtfulDescription().getDescriptors().toArray());
						}
					}
				}
			});
		}
		return jrbDoubtful;
	}

	/**
	 * This method initializes jrbUnconfirmed	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbUnconfirmed() {
		if (jrbUnconfirmed == null) {
			jrbUnconfirmed = new JRadioButton();
			jrbUnconfirmed.setText("No confirmada");
			jrbUnconfirmed.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if (jrbUnconfirmed.isSelected()) {
						jrbSolution.setSelected(false);
						jrbConfirmed.setSelected(false);
						jrbDoubtful.setSelected(false);
						jrbContradictions.setSelected(false);
						
						if (getProposedSolutions().size()>0) {
							
							jlDescriptions.setListData(((ProposedSolution)getProposedSolutions()
									.get(proposedSolution)).getSolution().getUnconfirmedDescription().getDescriptors().toArray());
						}
					}
				}
			});
		}
		return jrbUnconfirmed;
	}

	/**
	 * This method initializes jrbContradictions	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbContradictions() {
		if (jrbContradictions == null) {
			jrbContradictions = new JRadioButton();
			jrbContradictions.setText("Contradicciones");
			jrbContradictions.setVisible(false);
			jrbContradictions.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if (jrbContradictions.isSelected()) {
						jrbSolution.setSelected(false);
						jrbConfirmed.setSelected(false);
						jrbDoubtful.setSelected(false);
						jrbUnconfirmed.setSelected(false);
						
						if (getProposedSolutions().size()>0) {
							
							jlDescriptions.setListData(((ProposedSolution)getProposedSolutions()
									.get(proposedSolution)).getSolution().getContradictions().getDescriptors().toArray());
						}
					}
				}
			});
		}
		return jrbContradictions;
	}

	/**
	 * This method initializes jspDescriptions	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspDescriptions() {
		if (jspDescriptions == null) {
			jspDescriptions = new JScrollPane();
			jspDescriptions.setViewportView(getJlDescriptions());
		}
		return jspDescriptions;
	}

	/**
	 * This method initializes jlDescriptions	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJlDescriptions() {
		if (jlDescriptions == null) {
			jlDescriptions = new JList();
		}
		return jlDescriptions;
	}

	/**
	 * This method initializes jpSolutionButtons	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpSolutionButtons() {
		if (jpSolutionButtons == null) {
			GridLayout gridLayout3 = new GridLayout();
			gridLayout3.setRows(3);
			gridLayout3.setVgap(5);
			gridLayout3.setHgap(5);
			gridLayout3.setColumns(1);
			jpSolutionButtons = new JPanel();
			jpSolutionButtons.setLayout(gridLayout3);
			jpSolutionButtons.add(getJbSeeSolution(), null);
			jpSolutionButtons.add(getJbNext(), null);
			jpSolutionButtons.add(getJbAccept(), null);
		}
		return jpSolutionButtons;
	}

	/**
	 * This method initializes jbSeeSolution	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbSeeSolution() {
		if (jbSeeSolution == null) {
			jbSeeSolution = new JButton();
			jbSeeSolution.setText("Ver solución");
		}
		return jbSeeSolution;
	}

	/**
	 * This method initializes jbNext	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbNext() {
		if (jbNext == null) {
			jbNext = new JButton();
			jbNext.setText("Siguiente propuesta");
			jbNext.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (getProposedSolutions().size()>0) {
						proposedSolution = (proposedSolution +1) % getProposedSolutions().size();
						
						jLabelProposals.setText("Propuesta " + (proposedSolution +1) +
								" de " + getProposedSolutions().size());
						
						jcbState.setSelected(((ProposedSolution)getProposedSolutions()
								.get(proposedSolution)).getState());
						jtfCertaintyDegree.setText(((ProposedSolution)getProposedSolutions()
								.get(proposedSolution)).getCertaintyDegree());
						Object solution = ((ProposedSolution)getProposedSolutions()
								.get(proposedSolution)).getSolution().getSolution();
						
						if (solution instanceof Taxon) {
							jtfName.setText(((Taxon)solution).getName());
							jtfRank.setText(((Taxon)solution).getLevel());
							jbSeeSolution.setText("Ver taxón");
						} else {
							jtfName.setText(((Case)solution).getSolution().getTaxonName());
							jtfRank.setText(((Case)solution).getSolution().getTaxonLevel());
							jbSeeSolution.setText("Ver caso");
						}
						
						jlDescriptions.setListData(((ProposedSolution)getProposedSolutions()
								.get(proposedSolution)).getSolution().getSolutionDescription().getDescriptors().toArray());
						jrbSolution.setSelected(true);
						jrbConfirmed.setSelected(false);
						jrbDoubtful.setSelected(false);
						jrbUnconfirmed.setSelected(false);
						jrbContradictions.setSelected(false);
					} else {
						jLabelProposals.setText("Propuesta 0 de " + getProposedSolutions().size());
					}
				}
			});
		}
		return jbNext;
	}

	/**
	 * This method initializes jbAccept	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAccept() {
		if (jbAccept == null) {
			jbAccept = new JButton();
			jbAccept.setText("Aceptar propuesta");
			jbAccept.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (getProposedSolutions().size() > 0) {
						Case aNewCase = new Case();
						
						//Agregar descripción del problema del caso
						aNewCase.setProblem(problem);
						aNewCase.getProblem().setGoalRank(OracleIDSystem.getInstance().getIdentGoal());
						aNewCase.getProblem().setLeastSimilarityDegree(OracleIDSystem.getInstance().getMinSimilarityDegree());
						aNewCase.getProblem().getDescription().addAllToConcreteDescription(((ProposedSolution)getProposedSolutions()
								.get(proposedSolution)).getSolution().getConfirmedDescription());
						//Agregar descripción de la solución del caso
						aNewCase.setSolution(new Solution(jtfRank.getText(), jtfName.getText()));
						//Agregar estado de la solución del caso en la realidad
						aNewCase.setState(jcbState.isSelected());
						
						myAgent.learnCase(aNewCase);												
					} else {
						JOptionPane.showMessageDialog(null, "No hay soluciones propuestas.",
								"OracleID", JOptionPane.INFORMATION_MESSAGE);
					}					
				}
			});
		}
		return jbAccept;
	}
	
	public void setLocation() {
		int  width, height;
		
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		
		if (screenSize.getHeight() < getHeight()) height = (int) screenSize.getHeight();
		else height = getHeight();
		if (screenSize.getWidth() < getWidth()) width = (int)screenSize.getWidth();
		else width = (int)getWidth();
		
		setLocation(centerX - width / 2, centerY - height / 2);
	}	

}  //  @jve:decl-index=0:visual-constraint="23,43"
