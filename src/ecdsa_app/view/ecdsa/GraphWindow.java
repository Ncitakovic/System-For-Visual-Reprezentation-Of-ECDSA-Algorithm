package ecdsa_app.view.ecdsa;


import ac.essex.graphing.plotting.Graph;
import ac.essex.graphing.plotting.PlotSettings;
import ac.essex.graphing.swing.GraphPanel;
import ac.essex.graphing.swing.InteractiveGraphPanel;
import ac.essex.graphing.swing.SettingsUpdateListener;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

@SuppressWarnings("serial")
public class GraphWindow extends JDialog implements ActionListener, SettingsUpdateListener {
  protected JButton save = new JButton("Save");
  
  protected JButton exit;
  
  protected JButton update;
  
  protected JTextField minX;
  
  protected JTextField minY;
  
  protected JTextField maxX;
  
  protected JTextField maxY;
  
  protected GraphPanel graphPanel;
  
  public GraphWindow(Graph paramGraph) {
    this.save.addActionListener(this);
    this.exit = new JButton("Exit");
    this.exit.addActionListener(this);
    JPanel jPanel = new JPanel(new FlowLayout(2));
    this.minX = new JTextField(String.valueOf(paramGraph.plotSettings.getMinX()));
    this.minY = new JTextField(String.valueOf(paramGraph.plotSettings.getMinY()));
    this.maxX = new JTextField(String.valueOf(paramGraph.plotSettings.getMaxX()));
    this.maxY = new JTextField(String.valueOf(paramGraph.plotSettings.getMaxY()));
    jPanel.add(new JLabel("X: "));
    jPanel.add(this.minX);
    jPanel.add(new JLabel("-"));
    jPanel.add(this.maxX);
    jPanel.add(new JLabel(", Y:"));
    jPanel.add(this.minY);
    jPanel.add(new JLabel("-"));
    jPanel.add(this.maxY);
    this.update = new JButton("Update");
    this.update.addActionListener(this);
    jPanel.add(this.update);
    jPanel.add(this.save);
    jPanel.add(this.exit);
    this.graphPanel = new InteractiveGraphPanel(this);
    addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent param1WindowEvent) {
//            System.exit(0);
          }
        });
    Container container = getContentPane();
    container.add(jPanel, "South");
    container.add(this.graphPanel, "Center");
    setSize(640, 480);
    setTitle("Java Plot 1.1");
    setVisible(true);
    this.graphPanel.setGraph(paramGraph);
  }
  
  public void graphUpdated(PlotSettings paramPlotSettings) {
    this.minX.setText(String.valueOf(paramPlotSettings.getMinX()));
    this.minY.setText(String.valueOf(paramPlotSettings.getMinY()));
    this.maxX.setText(String.valueOf(paramPlotSettings.getMaxX()));
    this.maxY.setText(String.valueOf(paramPlotSettings.getMaxY()));
  }
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    if (paramActionEvent.getSource() == this.update) {
      Graph graph = this.graphPanel.getGraph();
      graph.plotSettings.setMinX(Double.parseDouble(this.minX.getText()));
      graph.plotSettings.setMaxX(Double.parseDouble(this.maxX.getText()));
      graph.plotSettings.setMinY(Double.parseDouble(this.minY.getText()));
      graph.plotSettings.setMaxY(Double.parseDouble(this.maxY.getText()));
      this.graphPanel.repaint();
    } 
    if (paramActionEvent.getSource() == this.save) {
      JFileChooser jFileChooser = new JFileChooser(System.getProperty("user.home"));
      jFileChooser.setDialogTitle("Save Graph Image");
      jFileChooser.setSelectedFile(new File(jFileChooser.getCurrentDirectory(), "graph.png"));
      jFileChooser.setFileFilter(new FileFilter() {
            public boolean accept(File param1File) {
              String str = param1File.getName().substring(param1File.getName().lastIndexOf('.') + 1).toLowerCase();
              return param1File.isDirectory() ? true : (str.equals("bmp") ? true : (str.equals("jpg") ? true : (str.equals("png"))));
            }
            
            public String getDescription() {
              return "Image Files: jpg, png, bmp";
            }
          });
      int i = jFileChooser.showSaveDialog(this);
      if (i == 0) {
        File file = jFileChooser.getSelectedFile();
        try {
          String str = file.getName().substring(file.getName().lastIndexOf(".") + 1);
          ImageIO.write(this.graphPanel.getImage(), str, file);
        } catch (IOException iOException) {
          JOptionPane.showMessageDialog(this, "Could not save image: " + iOException.getMessage());
        } 
      } 
    } 
    if (paramActionEvent.getSource() == this.exit)
      dispose(); 
  }
}
