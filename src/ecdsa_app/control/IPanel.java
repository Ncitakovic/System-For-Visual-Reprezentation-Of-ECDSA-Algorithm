
package ecdsa_app.control;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author nikol
 */
@SuppressWarnings("serial")
public class IPanel extends JPanel{
    private Image img;
    
    public IPanel(){}
    

    public IPanel(String img) { this((new ImageIcon(img)).getImage()); }
    
    public IPanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout((LayoutManager)null);
    }
    public void paintComponent(Graphics g){
        g.drawImage(getImg(), 0, 0, null);
    }
    public Image getImg() { return this.img; }
    
    public void setImg(Image img){
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null),img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout((LayoutManager)null);
    }
}
