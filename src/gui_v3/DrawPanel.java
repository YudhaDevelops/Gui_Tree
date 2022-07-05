package gui_v3;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.RenderingHints;
import java.awt.Color;

public class DrawPanel extends JPanel {
   protected JLabel status;
   public DrawPanel() {
      super();
   }
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
   }
   public void drawLine(Graphics g, Point start,
      Point end, float thickness, Color color) {
      Graphics2D g2 = (Graphics2D)g;
      Color oldColor = g2.getColor();
      BasicStroke oldStroke = (BasicStroke)g2.getStroke();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setStroke(new BasicStroke(thickness));
      g2.setColor(color);
      g2.draw(new Line2D.Float(start.x,start.y,end.x,
         end.y));
      g2.setStroke(oldStroke);
      g2.setColor(oldColor);
   }
   public void drawLine(Graphics g, Point start, Point end) {
      drawLine(g,start,end,2,Color.black);
   }
   public void drawLine(Graphics g, Point start, Point end,
      float thickness) {
      drawLine(g,start,end,thickness,Color.black);
   }
   public void drawRectangle(Graphics g, Point start,
      float width, float height, float thickness,
      Color color) {
      Graphics2D g2 = (Graphics2D)g;
      Color oldColor = g2.getColor();
      BasicStroke oldStroke = (BasicStroke)g2.getStroke();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setStroke(new BasicStroke(thickness));
      g2.setColor(color);
      g2.draw(new Rectangle2D.Float(start.x,start.y,width,
         height));
      g2.setStroke(oldStroke);
      g2.setColor(oldColor);
   }
   public void drawRectangle(Graphics g, Point start,
      float width, float height) {
      drawRectangle(g,start,width,height,2,Color.black);
   }
   public void drawLinkNode(Graphics g, Point head) {
      Point p = new Point(head.x,head.y-10);
      drawRectangle(g,p,40,20);
      drawLine(g,new Point(p.x+30,p.y),
         new Point(p.x+30,p.y+20));
   }
   public void appendLinkNode(Graphics g, Point tail) {
      drawLinkNode(g,tail);
   }
}
