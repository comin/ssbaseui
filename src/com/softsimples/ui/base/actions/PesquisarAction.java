package com.softsimples.ui.base.actions;

import com.softsimples.ui.base.BasicFormPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class PesquisarAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private BasicFormPanel basicPanel;
    public PesquisarAction(BasicFormPanel basicPanel) {
        super("Pesquisar",new ImageIcon(PesquisarAction.class.getResource("/icons/find.png")));
        this.putValue(SHORT_DESCRIPTION, "Pesquisa cadastro");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_P));
        this.basicPanel = basicPanel;
    }

    public void actionPerformed(ActionEvent event) {
        this.basicPanel.pesquisar();
    }    
}
