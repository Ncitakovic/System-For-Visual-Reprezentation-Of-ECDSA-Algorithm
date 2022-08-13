package ecdsa_app.view.ecdsa;

import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ecdsa_app.view.ecdsa.ECparamGen.Point;

public class ScatterPlot extends JDialog {
	private static final long serialVersionUID = 6294689542092367723L;
	
	ECparamGen ecParamGen;	

	public ScatterPlot(JFrame f, String title, ECparamGen ecParamGen) {
		super(f, title);
		this.ecParamGen = ecParamGen; 
		
		
		// Create dataset
		XYDataset dataset = createDataset();

		// Create chart
		JFreeChart chart = ChartFactory.createScatterPlot("Elliptic Curve over finite field", "Y-Axis", "X-Axis",
				dataset, PlotOrientation.HORIZONTAL, rootPaneCheckingEnabled, rootPaneCheckingEnabled,
				rootPaneCheckingEnabled);

		// Changes background color
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(255, 228, 196));

		// Create Panel
		ChartPanel panel = new ChartPanel(chart);
		add(panel);
		setSize(800, 600);
		setVisible(true);		
	}
	




	private XYDataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("Points");
		LinkedList<Point> points = ecParamGen.getPoints();
		
		for (int i = 0; i < points.size(); ++i) {
			Point point = (Point) points.get(i);
			long x = point.x;
			long y = point.y;
			series1.add(x, y);
		}
		dataset.addSeries(series1);
		return dataset;
	}

}