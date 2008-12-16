package com.softsimples.ui.base;

import com.softsimples.ui.base.actions.dialog.CancelaAction;
import com.softsimples.ui.base.actions.dialog.ConfirmaAction;
import com.softsimples.ui.base.actions.dialog.DialogListener;
import java.awt.CardLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public abstract class BasicDialogPanel extends OverlayPanel {
    private DialogListener dialogListener;
    private JToolBar toolBar;
    public BasicDialogPanel(int width, int height, String tituloTela, DialogListener dialogListener) {
        super(width, height, tituloTela);
        this.dialogListener = dialogListener;
        Insets insets = new Insets(6, 1, 0, 1);
        JButton button = null;
        button = this.toolBar.add(new ConfirmaAction(this.dialogListener));
        button.setMargin(insets);
        button = this.toolBar.add(new CancelaAction(this.dialogListener));
        button.setMargin(insets);
        this.configurePanel();
    }
    
    public void addPanel(JPanel panel) {
        this.formPanel.add(panel, panel.getName());
        this.title.setText(panel.getName());
        this.setPainelVisivel(panel);
    }
    
    public void setPainelVisivel(JPanel panel) {
        CardLayout cardLayout = (CardLayout)this.formPanel.getLayout();
        cardLayout.show(this.formPanel, panel.getName());
    }
    
    public JToolBar getToolBar() {
        this.toolBar = new JToolBar();
        this.toolBar.setOpaque(false);
        this.toolBar.setFloatable(false);
        this.toolBar.setBorderPainted(false);
        this.toolBar.setBorder(null);
        return toolBar;
    }
}
