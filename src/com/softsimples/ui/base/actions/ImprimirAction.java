package com.softsimples.ui.base.actions;

import com.softsimples.ui.base.BasicFormPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class ImprimirAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private BasicFormPanel basicPanel;
    public ImprimirAction(BasicFormPanel basicPanel) {
        super("Imprimir",new ImageIcon(ImprimirAction.class.getResource("/icons/printer3.png")));
        this.putValue(SHORT_DESCRIPTION, "Imprimi cadastro");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_I));
        this.basicPanel = basicPanel;
    }

    public void actionPerformed(ActionEvent event) {
        this.basicPanel.imprimir();
    }    
}
