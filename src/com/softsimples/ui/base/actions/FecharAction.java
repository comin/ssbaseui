package com.softsimples.ui.base.actions;

import com.softsimples.ui.base.OverlayPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class FecharAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private OverlayPanel overlayPanel;
    public FecharAction(OverlayPanel overlayPanel) {
        super("Fechar",new ImageIcon(FecharAction.class.getResource("/icons/delete.png")));
        this.putValue(SHORT_DESCRIPTION, "Fecha esta tela");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, ActionEvent.ALT_MASK));
        this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_BACK_SPACE));
        this.overlayPanel = overlayPanel;
    }

    public void actionPerformed(ActionEvent event) {
        this.overlayPanel.closeWindowWrapper();
    }    
}
