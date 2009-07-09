/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*****************************************************************/

package speciesID;

//import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import speciesidontology.*;

/**
  @author Armando Rodríguez
 */
@SuppressWarnings("serial")
class IdentifierAgentGui extends JFrame {
	private IdentifierAgent myAgent;
	
	private JTextField structureField, propertyField, stateField, inferedTaxonField;
    private JCheckBox evalResultado;
    private JTextArea explication, descriptionList;
	
	IdentifierAgentGui(IdentifierAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2));
		p.add(new JLabel("Estructura:"));
		structureField = new JTextField(15);
		p.add(structureField);
		p.add(new JLabel("Propiedad:"));
		propertyField = new JTextField(15);
		p.add(propertyField);
        p.add(new JLabel("Valor/Estado:"));
		stateField = new JTextField(15);
		p.add(stateField);

        JPanel q = new JPanel();
        q.setLayout(new BorderLayout());
        q.add(p, BorderLayout.WEST);

        JButton newButton = new JButton("Nuevo Caso");
		newButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(IdentifierAgentGui.this, "Valores invÃ¡lidos. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} );

        JButton addButton = new JButton("Agregar a Descripción");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
                String structure = structureField.getText().trim();
				String property = propertyField.getText().trim();
                String state = stateField.getText().trim();
                ElementoDescriptivo ed;

				try {
                    Float value = Float.parseFloat(state);
                    ElementoDescriptivoCuantitativo edc = new ElementoDescriptivoCuantitativo(structure, property, value);
                    myAgent.updateDescription(edc);
                    ed = edc;
				}
				catch (Exception e) {
                    ElementoDescriptivoCualitativo edc = new ElementoDescriptivoCualitativo(structure, property, state);
                    myAgent.updateDescription(edc);
                    ed = edc;
                }

                if (descriptionList.getText().equals(""))
                   descriptionList.append(ed.toString());
                else
                   descriptionList.append("; " + ed.toString());

				structureField.setText("");
				propertyField.setText("");
                stateField.setText("");
			}
		} );

        p = new JPanel();
		p.setLayout(new GridLayout(2, 1, 5, 5));
        p.add(addButton);
        p.add(newButton);
		q.add(p, BorderLayout.CENTER);

        p = new JPanel();
		p.setLayout(new GridLayout(1, 3));

        JButton identifyButton = new JButton("Identificar Taxón");
		identifyButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				myAgent.identifySpecimen();
			}
		} );

		p.add(identifyButton);
		inferedTaxonField = new JTextField(15);
		p.add(inferedTaxonField);
        evalResultado = new JCheckBox("Caso exitoso", true);
        p.add(evalResultado);
        q.add(p, BorderLayout.SOUTH);
        
        getContentPane().add(q, BorderLayout.CENTER);

        p = new JPanel();
		p.setLayout(new GridLayout(1, 2, 5, 5));
		p.add(new JLabel("Descripciónn Actual"));
		p.add(new JLabel("Explicación de Inferencia"));

        q = new JPanel();
        q.setLayout(new BorderLayout());
        q.add(p, BorderLayout.CENTER);

        p = new JPanel();
        p.setLayout(new GridLayout(1, 2, 5, 5));
        descriptionList = new JTextArea();
        descriptionList.setRows(10);
        descriptionList.setLineWrap(true);
        descriptionList.setEnabled(false);
        p.add(descriptionList);
        explication = new JTextArea();
        explication.setRows(10);
        explication.setLineWrap(true);
        explication.setEnabled(false);
        p.add(explication);
        q.add(p, BorderLayout.SOUTH);

        getContentPane().add(q, BorderLayout.SOUTH);
		
		// Hace que el agente termine cuando el usuario cierra
		// el GUI usando el boton de la esquina superior derecha
		addWindowListener(new	WindowAdapter() {
            @Override
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
    @SuppressWarnings("deprecation")
	@Override
	public void show() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.show();
	}	
}
