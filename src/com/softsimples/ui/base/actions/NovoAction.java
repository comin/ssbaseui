package com.softsimples.ui.base.actions;

import com.softsimples.ui.base.BasicFormPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class NovoAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private BasicFormPanel basicPanel;
    public NovoAction(BasicFormPanel basicPanel) {
        super("Novo",new ImageIcon(NovoAction.class.getResource("/icons/document_plain_new.png")));
        this.putValue(SHORT_DESCRIPTION, "Cria novo cadastro");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_N));
        this.basicPanel = basicPanel;
    }

    public void actionPerformed(ActionEvent event) {
        this.basicPanel.novo();
    }    
}
