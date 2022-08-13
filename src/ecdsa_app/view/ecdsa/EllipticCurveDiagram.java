package ecdsa_app.view.ecdsa;

import ac.essex.graphing.plotting.Graph;
import ac.essex.graphing.plotting.PlotSettings;
import ac.essex.graphing.plotting.ContinuousFunctionPlotter;
import java.awt.*;

class LowerCurvePlot extends ContinuousFunctionPlotter {
	double a;
	double b;

	LowerCurvePlot(double a, double b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public double getY(double x) {
		return (-1) * Math.sqrt((x * x * x) + (a * x) + b);
	}

	@Override
	public String getName() {
		return "Lower Curve";
	}

}

class UpperCurvePlot extends ContinuousFunctionPlotter {
	double a;
	double b;

	UpperCurvePlot(double a, double b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public double getY(double x) {
		return Math.sqrt((x * x * x) + (a * x) + b);
	}

	@Override
	public String getName() {
		return "Upper Curve";
	}
}

public class EllipticCurveDiagram {
	double a, b;

	public EllipticCurveDiagram(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public Graph getExampleGraph() {

		PlotSettings p = new PlotSettings(-10, 10, -10, 10);
		p.setPlotColor(Color.RED);
		p.setGridSpacingX(2);
		p.setGridSpacingY(2);
		p.setTitle("Elliptic curve over real numbers");
		Graph graph = new Graph(p);
		graph.functions.add(new UpperCurvePlot(a, b));
		graph.functions.add(new LowerCurvePlot(a, b));

		return graph;
	}
}