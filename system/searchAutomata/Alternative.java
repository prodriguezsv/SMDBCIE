/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata;

import ontology.common.Descriptor;
import redundantDiscriminationNet.Node;

/**
 *
 * @author pabloq
 */
public class Alternative {
    private Descriptor<Object> descriptor;
    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setDescriptor(Descriptor<Object> descriptor) {
        this.descriptor = descriptor;
    }

    public Descriptor<Object> getDescriptor() {
        return descriptor;
    }

    


}
