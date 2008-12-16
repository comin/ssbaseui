package com.softsimples.ui.base.actions.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class CancelaAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private DialogListener dialogListener;
    public CancelaAction(DialogListener dialogListener) {
        super("Cancela",new ImageIcon(CancelaAction.class.getResource("/icons/disk_blue_error.png")));
        this.putValue(SHORT_DESCRIPTION, "Cancela inclusão");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_Q));
        this.dialogListener = dialogListener;
   }

    public void actionPerformed(ActionEvent event) {
        this.dialogListener.cancelaDialog();
    }    
}
