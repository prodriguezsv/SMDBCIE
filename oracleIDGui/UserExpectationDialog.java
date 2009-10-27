/**
 * 
 */
package oracleIDGui;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

import system.OracleIDSystem;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.KnowledgeBase;

/**
 * @author Armando
 *
 */
public class UserExpectationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jpButtons = null;
	private JButton jbAccept = null;
	private JButton jbCancel = null;
	private JPanel jpExpectations = null;
	private JLabel jLabelGoalRank = null;
	private JComboBox jcbGoalRank = null;
	private JLabel jLabelMaxProposedSolutions = null;
	private JComboBox jcbMaxProposedSolutions = null;
	private JLabel jLabelMinSimilarityDegree = null;
	private JComboBox jcbMinSimilarityDegree = null;
	private JCheckBox jcbPresentFailureSolution = null;
	
	private List<String> ranks = null;
	private List<String> similarityDegrees = null;

	/**
	 * @param owner
	 */
	public UserExpectationDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(520, 205);
		this.setModal(true);		
		this.setContentPane(getJContentPane());
		setLocation();
		this.setVisible(true);
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
			jContentPane.add(getJpExpectations(), BorderLayout.CENTER);
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
			gridLayout.setRows(1);
			gridLayout.setHgap(5);
			gridLayout.setVgap(5);
			gridLayout.setColumns(2);
			jpButtons = new JPanel();
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
			jbAccept.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					OracleIDSystem.getInstance().setIdentGoal((String)jcbGoalRank.getSelectedItem());
					OracleIDSystem.getInstance().setMinSimilarityDegree((String)jcbMinSimilarityDegree.getSelectedItem());
					OracleIDSystem.getInstance().setMaxNumberSolutions(Integer.parseInt((String)jcbMaxProposedSolutions.getSelectedItem()));
					OracleIDSystem.getInstance().setPresentFailedSolutions(jcbPresentFailureSolution.isSelected());
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
			jbCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
				}
			});
		}
		return jbCancel;
	}

	/**
	 * This method initializes jpExpectations	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpExpectations() {
		if (jpExpectations == null) {
			jLabelMinSimilarityDegree = new JLabel();
			jLabelMinSimilarityDegree.setText("Mínimo grado de similaridad:");
			jLabelMaxProposedSolutions = new JLabel();
			jLabelMaxProposedSolutions.setText("Número máximo de soluciones propuestas:");
			jLabelMaxProposedSolutions.setName("");
			jLabelGoalRank = new JLabel();
			jLabelGoalRank.setText("Rango de identificación:");
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(4);
			gridLayout1.setColumns(2);
			jpExpectations = new JPanel();
			jpExpectations.setLayout(gridLayout1);
			jpExpectations.add(jLabelGoalRank, null);
			jpExpectations.add(getJcbGoalRank(), null);
			jpExpectations.add(jLabelMinSimilarityDegree, null);
			jpExpectations.add(getJcbMinSimilarityDegree(), null);
			jpExpectations.add(jLabelMaxProposedSolutions, null);
			jpExpectations.add(getJcbMaxProposedSolutions(), null);
			jpExpectations.add(getJcbPresentFailureSolution(), null);
		}
		return jpExpectations;
	}

	/**
	 * This method initializes jcbGoalRank	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getJcbGoalRank() {
		if (jcbGoalRank == null) {
			jcbGoalRank = new JComboBox();
		}
		
		KnowledgeBase kb = OracleIDSystem.getInstance().getCbrKb();
		Cls cls = (Cls) kb.getCls("Problem");
	    ranks = new ArrayList<String>(cls.getTemplateSlotAllowedValues(kb.getSlot("goalRank")));
	    for (String r:ranks) {
        	jcbGoalRank.addItem(r);
        }
	    
	    jcbGoalRank.setSelectedItem(OracleIDSystem.getInstance().getIdentGoal());
	    
		return jcbGoalRank;
	}

	/**
	 * This method initializes jcbMaxProposedSolutions	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJcbMaxProposedSolutions() {
		if (jcbMaxProposedSolutions == null) {
			jcbMaxProposedSolutions = new JComboBox();
		}
		
		for (int i = 1; i <= 10; i++) {
			jcbMaxProposedSolutions.addItem(Integer.toString(i));
        }
		jcbMaxProposedSolutions.setSelectedItem(Integer.toString(OracleIDSystem.getInstance().getMaxNumberSolutions()));
		return jcbMaxProposedSolutions;
	}

	/**
	 * This method initializes jcbMinSimilarityDegree	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getJcbMinSimilarityDegree() {
		if (jcbMinSimilarityDegree == null) {
			jcbMinSimilarityDegree = new JComboBox();
		}
		
		KnowledgeBase kb = OracleIDSystem.getInstance().getCbrKb();
		Cls cls = (Cls) kb.getCls("Problem");
	    similarityDegrees = new ArrayList<String>(cls.getTemplateSlotAllowedValues(kb.getSlot("leastSimilarityDegree")));
	    for (String mu:similarityDegrees) {
	    	jcbMinSimilarityDegree.addItem(mu);
        }
	    
	    jcbMinSimilarityDegree.setSelectedItem(OracleIDSystem.getInstance().getMinSimilarityDegree());
	    
		return jcbMinSimilarityDegree;
	}

	/**
	 * This method initializes jcbPresentFailureSolution	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJcbPresentFailureSolution() {
		if (jcbPresentFailureSolution == null) {
			jcbPresentFailureSolution = new JCheckBox();
			jcbPresentFailureSolution.setText("¿Presentar soluciones fallidas?");
		}
		
		jcbPresentFailureSolution.setSelected(OracleIDSystem.getInstance().isPresentFailedSolutions());
		return jcbPresentFailureSolution;
	}
	
	public void setLocation() {
		//pack(); //Reajustar al tamaño preferido 
		int centerX = (int) this.getOwner().getLocation().getX() + this.getOwner().getWidth()/ 2;
		int centerY = (int) this.getOwner().getLocation().getY() + this.getOwner().getHeight()/ 2;
				
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
	}	

}  //  @jve:decl-index=0:visual-constraint="10,10"
