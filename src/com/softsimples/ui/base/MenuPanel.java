package com.softsimples.ui.base;

import com.softsimples.ui.base.menu.MenuTooltip;
import com.sun.scenario.animation.Clip;
import com.sun.scenario.animation.Interpolators;
import com.sun.scenario.effect.Reflection;
import com.sun.scenario.scenegraph.JSGPanel;
import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.SGTransform;
import com.sun.scenario.scenegraph.event.SGMouseAdapter;
import com.sun.scenario.scenegraph.fx.FXGroup;
import com.sun.scenario.scenegraph.fx.FXImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public abstract class MenuPanel extends JSGPanel {
    private static final long serialVersionUID = 1L;
    protected FXGroup mainGroup;
    protected FXGroup menuGroup;
    protected Reflection reflection;
    protected int iconSize = 0;
    protected int spacerSize = 20;
    protected BufferedImage gradient;
    protected BufferedImage backgroundImage;
    protected GraphicsConfiguration graphicsConfiguration;
    protected OpenWindowListener openWindowListener;
    
    public MenuPanel(OpenWindowListener openWindowListener, String background) {
        this.graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        this.setOpaque(false);
        this.setDoubleBuffered(true);
        this.openWindowListener = openWindowListener;
        try {
            backgroundImage = ImageIO.read(MenuPanel.class.getResourceAsStream(background));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public abstract void executar();
    public abstract void configurePanels();
    public abstract void loadImages();
    
    public BufferedImage paintGradient(Graphics graphics) {
        if (gradient == null || gradient.getWidth() != this.getWidth() || gradient.getHeight() != this.getHeight()) {
            gradient = this.graphicsConfiguration.createCompatibleImage(this.getWidth(), this.getHeight());
            Graphics2D graphics2D = (Graphics2D) gradient.getGraphics();
            graphics2D.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
            graphics2D.dispose();
        }
        return gradient;
    }
    
    @Override
    public void paint(Graphics graphics) {        
        graphics.drawImage(this.paintGradient(graphics), 0, 0, null);
        super.paint(graphics);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Rectangle rectangle = this.getBounds();
        if (menuGroup != null) {
            menuGroup.setTranslateX((rectangle.width / 2) - (iconSize / 2));
            menuGroup.setTranslateY((rectangle.height / 2) - 48);
        }
        super.paintComponent(graphics);
    }
    
    public FXImage loadImage(InputStream inputStream) throws IOException {
        BufferedImage image = ImageIO.read(inputStream);
        return getFXImage(image);
    }
    
    public FXImage loadImage(URL url) throws IOException {
        BufferedImage image = ImageIO.read(url);
        return getFXImage(image);
    }
    
    public FXImage getFXImage(BufferedImage image) {
        FXImage fxImage = new FXImage();
        fxImage.setImage(image);
        fxImage.setEffect(reflection);
        return fxImage;
    }
    
    public int calcularNovaPosicao(FXImage image) {
        iconSize += image.getBounds().getWidth() + spacerSize;
        return iconSize;
    }

    protected class MenuMouseAdapter extends SGMouseAdapter {
        @Override
        public void mouseEntered(MouseEvent event, SGNode node) {
            Clip yMover = Clip.create(100, 1, node, "scaleY", 1.4);
            yMover.setInterpolator(Interpolators.getLinearInstance());
            yMover.start();

            Clip xMover = Clip.create(100, 1, node, "scaleX", 1.4);
            xMover.setInterpolator(Interpolators.getLinearInstance());
            xMover.start();
        }

        @Override
        public void mouseExited(MouseEvent event, SGNode node) {
            Clip yMover = Clip.create(100, 1, node, "scaleY", 1d);
            yMover.setInterpolator(Interpolators.getLinearInstance());
            yMover.start();

            Clip xMover = Clip.create(100, 1, node, "scaleX", 1d);
            xMover.setInterpolator(Interpolators.getLinearInstance());
            xMover.start();
        }
        
        public void fadeIn(SGNode node, SGTransform.Translate translate, MenuTooltip menuTooltip) {
            double meioDaTelaX = ((double)node.getPanel().getBounds().width / 2);
            double meioDaTelaY = ((double)node.getPanel().getBounds().height / 2);
            double offset = (menuTooltip.getBounds().getWidth() / 2);
            offset -= 20;
            translate.setTranslateX(meioDaTelaX - offset);
            translate.setTranslateY(meioDaTelaY - 160);
            menuTooltip.fadeIn();
        }
        
        public void fadeOut(MenuTooltip menuTooltip) {
            menuTooltip.fadeOut();
        }
    }
}