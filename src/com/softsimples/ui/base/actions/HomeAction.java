package com.softsimples.ui.base.actions;

import com.softsimples.ui.base.OverlayPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class HomeAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private OverlayPanel overlayPanel;
    public HomeAction(OverlayPanel overlayPanel) {
        super("Inicio",new ImageIcon(HomeAction.class.getResource("/icons/home.png")));
        this.putValue(SHORT_DESCRIPTION, "Volta para o menu inicial");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_HOME, ActionEvent.ALT_MASK));
        this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_HOME));
        this.overlayPanel = overlayPanel;
    }

    public void actionPerformed(ActionEvent event) {
        this.overlayPanel.backToHomeWrapper();
    }
}
