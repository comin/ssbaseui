package com.softsimples.ui.base.actions;

import com.softsimples.ui.base.BasicFormPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class CancelarAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private BasicFormPanel basicPanel;
    public CancelarAction(BasicFormPanel basicPanel) {
        super("Cancelar",new ImageIcon(CancelarAction.class.getResource("/icons/document_error.png")));
        this.putValue(SHORT_DESCRIPTION, "Cancela modificacao");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_C));
        this.basicPanel = basicPanel;
    }

    public void actionPerformed(ActionEvent event) {
        basicPanel.cancelar();
    }    
}
