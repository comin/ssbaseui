package com.softsimples.ui.base;

import com.softsimples.ui.base.actions.CancelarAction;
import com.softsimples.ui.base.actions.ExcluirAction;
import com.softsimples.ui.base.actions.ImprimirAction;
import com.softsimples.ui.base.actions.LimparAction;
import com.softsimples.ui.base.actions.NovoAction;
import com.softsimples.ui.base.actions.PesquisarAction;
import com.softsimples.ui.base.actions.SalvarAction;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public abstract class BasicFormPanel extends OverlayPanel {
    private static final long serialVersionUID = 1L;
    private boolean isPrincipal = true;
    
    public BasicFormPanel(int width, int height, String tituloTela) {
        super(width, height, tituloTela);
        this.configurePanel();
    }

    public abstract void cancelar();
    public abstract void excluir();    
    public abstract void imprimir();
    public abstract void limpar();
    public abstract void novo();
    public abstract void pesquisar();
    public abstract void salvar();
    
    public void addPanel(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setBorder(null);
        this.formPanel.add(scrollPane, panel.getName());
    }
    
    @Override
    public JPanel getFormPanel() {
        JPanel wrapperFormPanel = new JPanel(new BorderLayout());
        wrapperFormPanel.setOpaque(false);
        CardLayout cardLayout = new CardLayout();
        formPanel = new JPanel(cardLayout);
        formPanel.setOpaque(false);
        formPanel.setBorder(null);
//        fasesPanel = new FasesPanel(this);
//        wrapperFormPanel.add(fasesPanel, BorderLayout.NORTH);
        wrapperFormPanel.add(formPanel, BorderLayout.CENTER);
        return wrapperFormPanel;
    }
    
    public void setPainelVisivel(JPanel panel) {
        CardLayout cardLayout = (CardLayout)this.formPanel.getLayout();
        this.title.setText(tituloTela + " ( "+panel.getName()+" ) ");
        cardLayout.show(this.formPanel, panel.getName());
    }
    
    public JToolBar getToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setOpaque(false);
        toolBar.setBorder(null);
        toolBar.setFloatable(false);
        toolBar.setBorderPainted(false);

        Insets insets = new Insets(6, 0, 0, 0);
        JButton button = null;
        button = toolBar.add(new NovoAction(this));
        button.setMargin(insets);
        button = toolBar.add(new CancelarAction(this));
        button.setMargin(insets);
        button = toolBar.add(new SalvarAction(this));
        button.setMargin(insets);
        button = toolBar.add(new ExcluirAction(this));
        button.setMargin(insets);
        button = toolBar.add(new PesquisarAction(this));
        button.setMargin(insets);
        button = toolBar.add(new LimparAction(this));
        button.setMargin(insets);
        button = toolBar.add(new ImprimirAction(this));
        button.setMargin(insets);
        toolBar.addSeparator(new Dimension(20, 10));
        return toolBar;
    }
}
