package com.softsimples.ui.base;

import com.softsimples.ui.base.actions.FecharAction;
import com.softsimples.ui.base.actions.HomeAction;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.jdesktop.jxlayer.JXLayer;

public abstract class OverlayPanel extends JXLayer<JComponent> {
    private CenterPanel centerPanel;
    private JPanel mainPanel;
    private CloseWindowListener closeWindowListener;
    protected JLabel title;
    protected JPanel formPanel;
    protected int basicWidth;
    protected int basicHeight;
    protected String tituloTela;
    private BufferedImage blurImage;
    
    public OverlayPanel(int width, int height, String tituloTela) {
        this.basicWidth = width;
        this.basicHeight = height;
        this.tituloTela = tituloTela;
        this.setName(tituloTela);
        this.setOpaque(false);
        this.setBorder(null);
        this.setDoubleBuffered(true);
        initialize();
    }
    
    public abstract URL getMenuIcon();
    
    public void backToHomeWrapper() {
        closeWindowListener.backToHome();
    }

    public void closeWindowWrapper() {
        closeWindowListener.closeWindow();
    }

    public String getTituloTela() {
        return this.tituloTela;
    }
    
    protected abstract void configurePanel();
    public abstract JToolBar getToolBar();
    
    public JPanel getBasicPanel() {
        return new BasicPanel(this.basicWidth, this.basicHeight);
    }
    
    public void setCloseWindowListener(CloseWindowListener closeWindowListener) {
        this.closeWindowListener = closeWindowListener;
    }
    
    public JPanel getCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(null);
        panel.setOpaque(false);
        panel.add(this.getToolBar(), BorderLayout.NORTH);
        panel.add(this.getFormPanel(), BorderLayout.CENTER);
        return panel;
    }
    
    public JPanel getFormPanel() {
        CardLayout cardLayout = new CardLayout();
        formPanel = new JPanel(cardLayout);
        formPanel.setOpaque(false);
        formPanel.setBorder(null);
        return formPanel;
    }
    
    public JPanel getSpacerPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(null);
        Rectangle rectangle = new Rectangle(0,0,width,height);
        panel.setBounds(rectangle);
        panel.setSize(rectangle.getSize());
        panel.setPreferredSize(rectangle.getSize());
        return panel;
    }
    
    public JPanel getTitlePanel(int width, int height) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);
        panel.setBorder(null);
        Rectangle rectangle = new Rectangle(0,0,width,height);
        panel.setBounds(rectangle);
        panel.setSize(rectangle.getSize());
        panel.setPreferredSize(rectangle.getSize());
        panel.add(this.getSpacerPanel(15, height));
        panel.add(this.getTitleLabel(width));
        JToolBar mainToolBar = new JToolBar();
        mainToolBar.setFloatable(false);
        mainToolBar.setOpaque(false);
        mainToolBar.setBorder(null);
        JButton button = null;
        button = mainToolBar.add(new FecharAction(this));
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button = mainToolBar.add(new HomeAction(this));
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        panel.add(mainToolBar);
        return panel;
    }
    
    public JLabel getTitleLabel(int width) {
        this.title = new JLabel();
        this.title.setText(tituloTela);
        this.title.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.title.setOpaque(false);
        this.title.setHorizontalAlignment(JLabel.LEFT);
        this.title.setForeground(Color.WHITE);
        Rectangle rectangle = new Rectangle(0,0,width - 110, 35);
        this.title.setBounds(rectangle);
        this.title.setSize(rectangle.getSize());
        this.title.setPreferredSize(rectangle.getSize());
        this.title.setMinimumSize(rectangle.getSize());
        return this.title;
    }
    
    public void initialize() {
        this.centerPanel = new CenterPanel();
        this.mainPanel = this.getBasicPanel();
        this.centerPanel.addPanel(mainPanel);
        this.setView(this.centerPanel);
    }
    
    private class CenterPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private JPanel basicPanel;
        public CenterPanel() {
            this.setLayout(null);
            this.setOpaque(false);
        }
        
        public void addPanel(JPanel basicPanel) {
            this.basicPanel = basicPanel;
            this.centralizarPainel();
            this.add(this.basicPanel);
        }
        
        public void centralizarPainel() {
            Rectangle rectangle = this.getBounds();
            Rectangle rectanglePanel = this.basicPanel.getBounds();
            rectanglePanel.x = (rectangle.width / 2) - (rectanglePanel.width / 2);
            rectanglePanel.y = (rectangle.height / 2) - (rectanglePanel.height / 2);
            this.basicPanel.setBounds(rectanglePanel);
        }
        
        @Override
        public void paint(Graphics graphics) {
            Rectangle rectangle = this.getBounds();
            Graphics2D graphics2D = (Graphics2D)graphics;
            Composite composite = graphics2D.getComposite();
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.fillRect(0, 0, rectangle.width, rectangle.height);
            graphics2D.setComposite(composite);
            super.paint(graphics);
        }
        
        @Override
        protected void paintComponent(Graphics graphics) {
            this.centralizarPainel();
            Rectangle rectangle = this.getBounds();
            super.paintComponent(graphics);
        }
    }
    
    protected class BasicPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        public BasicPanel(int width, int height) {
            this.setBorder(null);
            this.setOpaque(false);
            this.setBounds(0, 0, width, height);
            this.setSize(width, height);
            this.setLayout(new BorderLayout());        
            this.add(getTitlePanel(width, 30), BorderLayout.NORTH);
            this.add(getSpacerPanel(20, 20), BorderLayout.EAST);
            this.add(getCenterPanel(), BorderLayout.CENTER);
            this.add(getSpacerPanel(20, 20), BorderLayout.WEST);
            this.add(getSpacerPanel(width, 17), BorderLayout.SOUTH);
        }
        
        @Override
        public void paint(Graphics graphics) {
            Rectangle rectangleLayer = this.getBounds();
            Graphics2D graphics2D = (Graphics2D)graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Composite composite = graphics2D.getComposite();
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
            graphics2D.setColor(Color.WHITE);
            Stroke stroke = graphics2D.getStroke();
            graphics2D.setStroke(new BasicStroke(10));
            graphics2D.drawRoundRect(8, 8, rectangleLayer.width - 17, rectangleLayer.height - 17, 40, 40);
            graphics2D.setComposite(composite);
            graphics2D.setStroke(stroke);
            graphics2D.setColor(Color.GRAY);
            graphics2D.fillRoundRect(10, 10, rectangleLayer.width - 20, rectangleLayer.height - 20, 40, 40);
            super.paint(graphics);
        }
    }
}
