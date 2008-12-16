package com.softsimples.ui.base.menu;

import com.sun.scenario.animation.Clip;
import com.sun.scenario.scenegraph.SGComposite;
import com.sun.scenario.scenegraph.SGGroup;
import com.sun.scenario.scenegraph.SGImage;
import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.SGShape;
import com.sun.scenario.scenegraph.SGText;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuTooltip extends SGGroup {
    private Clip shapeFadeIn, shapeFadeOut;
    private Clip textFadeIn, textFadeOut;
    private Clip imageFadeIn, imageFadeOut;
    private static final int FADE_DUR = 500;
    private SGText textNode;
    private SGComposite textComposite;
    private static BufferedImage BUFFERED_IMAGE;
    static {
        try {
            BUFFERED_IMAGE = ImageIO.read(MenuTooltip.class.getResourceAsStream("/tooltip/lightbulb_on.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public MenuTooltip() {
        initialize();
    }
    
    public void initialize() {
        this.add(this.setupShape());
        this.add(this.setupText());
        this.add(this.setupImage());
    }
    
    private SGNode setupShape() {
        SGShape shapeNode = new SGShape();
        shapeNode.setShape(new RoundRectangle2D.Double(0, 0, 500, 100, 40, 40));
        shapeNode.setAntialiasingHint(RenderingHints.VALUE_ANTIALIAS_ON);
        shapeNode.setMode(SGShape.Mode.STROKE_FILL);
        shapeNode.setDrawPaint(Color.DARK_GRAY);
        shapeNode.setDrawStroke(new BasicStroke(4f));
        shapeNode.setFillPaint(Color.GRAY);
        SGComposite faderNode = new SGComposite();
        faderNode.setOpacity(0f);
        faderNode.setChild(shapeNode);
        
        shapeFadeIn = Clip.create(0, shapeNode, "visible", true);
        Clip fader = Clip.create(FADE_DUR, faderNode, "opacity", 0f, 0.7f);
        shapeFadeIn.addEndAnimation(fader);

        shapeFadeOut = Clip.create(FADE_DUR, faderNode, "opacity", 0.7f, 0f);
        Clip disappearer = Clip.create(0, shapeNode, "visible", false);
        shapeFadeOut.addEndAnimation(disappearer);
        
        return faderNode;
    }
    
    private SGNode setupText() {
        textNode = new SGText();
        textNode.setText("Cadastro");
        textNode.setAntialiasingHint(RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        textNode.setFont(new Font("Times", Font.BOLD, 18));
        textNode.setMode(SGText.Mode.FILL);
        textNode.setFillPaint(Color.WHITE);
        textNode.setDrawPaint(new Color(80,80,80,30));
        textNode.setLocation(new Point2D.Double(80, 35));
        textComposite = new SGComposite();
        textComposite.setOpacity(0f);
        textComposite.setChild(textNode);

        textFadeIn = Clip.create(0, textNode, "visible", true);
        Clip fader = Clip.create(FADE_DUR, textComposite, "opacity", 0f, 1f);
        textFadeIn.addEndAnimation(fader);
        
        textFadeOut = Clip.create(FADE_DUR, textComposite, "opacity", 1f, 0f);
        Clip disappearer = Clip.create(0, textNode, "visible", false);
        textFadeOut.addEndAnimation(disappearer);
        
        return textComposite;
    }
    
    private SGNode setupImage() {
        SGImage imageNode = new SGImage();
        imageNode.setImage(BUFFERED_IMAGE);
        imageNode.setLocation(new Point2D.Double(20, 26));
        SGComposite faderNode = new SGComposite();
        faderNode.setOpacity(0f);
        faderNode.setChild(imageNode);
        
        imageFadeIn = Clip.create(0, imageNode, "visible", true);
        Clip fader = Clip.create(FADE_DUR, faderNode, "opacity", 0f, 1f);
        imageFadeIn.addEndAnimation(fader);
        
        imageFadeOut = Clip.create(FADE_DUR, faderNode, "opacity", 1f, 0f);
        Clip disappearer = Clip.create(0, imageNode, "visible", false);
        imageFadeOut.addEndAnimation(disappearer);
        
        return faderNode;
    }
    
    public void setTitle(String title) {
        textNode.setText(title);
    }
    
    public void fadeIn() {
        shapeFadeIn.start();
        textFadeIn.start();
        imageFadeIn.start();
    }
    
    public void fadeOut() {
        shapeFadeOut.start();
        textFadeOut.start();
        imageFadeOut.start();
    }
}
