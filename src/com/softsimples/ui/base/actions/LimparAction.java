package com.softsimples.ui.base.actions;

import com.softsimples.ui.base.BasicFormPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class LimparAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private BasicFormPanel basicPanel;
    public LimparAction(BasicFormPanel basicPanel) {
        super("Limpar",new ImageIcon(LimparAction.class.getResource("/icons/refresh.png")));
        this.putValue(SHORT_DESCRIPTION, "Limpa tela");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
        this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
        this.basicPanel = basicPanel;
    }

    public void actionPerformed(ActionEvent event) {
        this.basicPanel.limpar();
    }    
}
