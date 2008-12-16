package com.softsimples.ui.base.actions.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class ConfirmaAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private DialogListener dialogListener;
    public ConfirmaAction(DialogListener dialogListener) {
        super("Confirma",new ImageIcon(ConfirmaAction.class.getResource("/icons/disk_blue.png")));
        this.putValue(SHORT_DESCRIPTION, "Confirma inclusão");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.ALT_MASK));
        this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_W));
        this.dialogListener = dialogListener;
   }

    public void actionPerformed(ActionEvent event) {
        this.dialogListener.confirmaDialog();
    }    
}
