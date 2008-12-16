package com.softsimples.ui.base.actions;

import com.softsimples.ui.base.BasicFormPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class SalvarAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private BasicFormPanel basicPanel;
    public SalvarAction(BasicFormPanel basicPanel) {
        super("Salvar",new ImageIcon(SalvarAction.class.getResource("/icons/disk_blue.png")));
        this.putValue(SHORT_DESCRIPTION, "Salva modificacao");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
        this.basicPanel = basicPanel;
    }

    public void actionPerformed(ActionEvent event) {
        basicPanel.salvar();
    }    
}
