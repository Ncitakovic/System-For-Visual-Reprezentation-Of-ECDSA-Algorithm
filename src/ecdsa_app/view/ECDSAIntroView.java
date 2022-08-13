
/**
 *
 * @author nikol
 */
/*     */ package ecdsa_app.view;

import ecdsa_app.ECDSAApp;
import ecdsa_app.control.IPanel;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;

public class ECDSAIntroView extends FrameView {
	private ECDSAApp app;
	private ButtonGroup buttonGroup1;
	private IPanel iPanel1;
	private JButton jButton1;
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JDialog aboutBox;

	public ECDSAIntroView(ECDSAApp app) {
		super(app);

		getFrame().setDefaultCloseOperation(3);
		getFrame().setResizable(false);
		getFrame().pack();
		this.app = app;

		initComponents();

		ResourceMap resourceMap = ((ECDSAApp) Application.getInstance(ECDSAApp.class)).getContext()
				.getResourceMap(ECDSAIntroView.class);
		this.iPanel1.setName("iPanel1");
		ImageIcon icon = (ImageIcon) resourceMap.getIcon("iPanel.icon");

		this.iPanel1.setImg(icon.getImage());
		this.iPanel1.setLayout(null);
		this.mainPanel.setPreferredSize(new Dimension(1000, 610));
	}

	@Action
	public void showAboutBox() {
		if (this.aboutBox == null) {
			JFrame mainFrame = ECDSAApp.getApplication().getMainFrame();
			this.aboutBox = new ECDSAAboutBox(mainFrame);
			this.aboutBox.setLocationRelativeTo(mainFrame);
		}
		ECDSAApp.getApplication().show(this.aboutBox);
	}

	private void initComponents() {
		this.mainPanel = new JPanel();
		this.jLabel1 = new JLabel();
		this.jButton1 = new JButton();
		this.iPanel1 = new IPanel();
		this.jScrollPane1 = new JScrollPane();
		this.jTextArea1 = new JTextArea();
		JLabel vendorLabel = new JLabel();
		JLabel appVersionLabel1 = new JLabel();
		JLabel homepageLabel2 = new JLabel();
		JLabel appVersionLabel4 = new JLabel();
		JLabel homepageLabel = new JLabel();
		JLabel appVersionLabel2 = new JLabel();
		JLabel homepageLabel1 = new JLabel();
		JLabel appVersionLabel3 = new JLabel();
		JLabel homepageLabel3 = new JLabel();
		JLabel appVersionLabel5 = new JLabel();
		JLabel homepageLabel4 = new JLabel();
		JLabel appVersionLabel6 = new JLabel();
		JLabel versionLabel = new JLabel();
		JLabel appVersionLabel = new JLabel();
		this.menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu();
		JMenuItem exitMenuItem = new JMenuItem();
		JMenu helpMenu = new JMenu();
		JMenuItem aboutMenuItem = new JMenuItem();
		this.buttonGroup1 = new ButtonGroup();

		this.mainPanel.setName("mainPanel");
		this.mainPanel.setPreferredSize(new Dimension(710, 386));

		ResourceMap resourceMap = ((ECDSAApp) Application.getInstance(ECDSAApp.class)).getContext()
				.getResourceMap(ECDSAIntroView.class);
		this.jLabel1.setFont(resourceMap.getFont("jLabel1.font"));
		this.jLabel1.setHorizontalAlignment(0);
		this.jLabel1.setText(resourceMap.getString("jLabel1.text", new Object[0]));
		this.jLabel1.setName("jLabel1");

		ApplicationActionMap applicationActionMap = ((ECDSAApp) Application.getInstance(ECDSAApp.class)).getContext()
				.getActionMap(ECDSAIntroView.class, this);
		this.jButton1.setAction(applicationActionMap.get("start"));
		this.jButton1.setFont(resourceMap.getFont("jButton1.font"));
		this.jButton1.setText(resourceMap.getString("jButton1.text", new Object[0]));
		this.jButton1.setMinimumSize(new Dimension(0, 0));
		this.jButton1.setName("jButton1");
		this.jButton1.setPreferredSize(new Dimension(25, 25));

		this.iPanel1.setName("iPanel1");

		GroupLayout iPanel1Layout = new GroupLayout(this.iPanel1);
		this.iPanel1.setLayout(iPanel1Layout);
		iPanel1Layout.setHorizontalGroup(
				iPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 345, 32767));

		iPanel1Layout.setVerticalGroup(
				iPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 235, 32767));

		this.jScrollPane1.setName("jScrollPane1");

		this.jTextArea1.setBackground(resourceMap.getColor("jTextArea1.background"));
		this.jTextArea1.setColumns(20);
		this.jTextArea1.setEditable(false);
		this.jTextArea1.setRows(5);
		this.jTextArea1.setText(resourceMap.getString("jTextArea1.text", new Object[0]));
		this.jTextArea1.setName("jTextArea1");
		this.jScrollPane1.setViewportView(this.jTextArea1);

		vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | 0x1));
		vendorLabel.setText(resourceMap.getString("vendorLabel.text", new Object[0]));
		vendorLabel.setName("vendorLabel");

		appVersionLabel1.setText(resourceMap.getString("appVersionLabel1.text", new Object[0]));
		appVersionLabel1.setName("appVersionLabel1");

		homepageLabel2.setFont(homepageLabel2.getFont().deriveFont(homepageLabel2.getFont().getStyle() | 0x1));
		homepageLabel2.setText(resourceMap.getString("homepageLabel2.text", new Object[0]));
		homepageLabel2.setName("homepageLabel2");

		appVersionLabel4.setText(resourceMap.getString("appVersionLabel4.text", new Object[0]));
		appVersionLabel4.setName("appVersionLabel4");

		homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | 0x1));
		homepageLabel.setText(resourceMap.getString("homepageLabel.text", new Object[0]));
		homepageLabel.setName("homepageLabel");

		appVersionLabel2.setText(resourceMap.getString("appVersionLabel2.text", new Object[0]));
		appVersionLabel2.setName("appVersionLabel2");

		homepageLabel1.setFont(homepageLabel1.getFont().deriveFont(homepageLabel1.getFont().getStyle() | 0x1));
		homepageLabel1.setText(resourceMap.getString("homepageLabel1.text", new Object[0]));
		homepageLabel1.setName("homepageLabel1");

		appVersionLabel3.setText(resourceMap.getString("appVersionLabel3.text", new Object[0]));
		appVersionLabel3.setName("appVersionLabel3");

		homepageLabel3.setFont(homepageLabel3.getFont().deriveFont(homepageLabel3.getFont().getStyle() | 0x1));
		homepageLabel3.setText(resourceMap.getString("homepageLabel3.text", new Object[0]));
		homepageLabel3.setName("homepageLabel3");

		appVersionLabel5.setText(resourceMap.getString("appVersionLabel5.text", new Object[0]));
		appVersionLabel5.setName("appVersionLabel5");

		homepageLabel4.setFont(homepageLabel4.getFont().deriveFont(homepageLabel4.getFont().getStyle() | 0x1));
		homepageLabel4.setText(resourceMap.getString("homepageLabel4.text", new Object[0]));
		homepageLabel4.setName("homepageLabel4");

		appVersionLabel6.setText(resourceMap.getString("appVersionLabel6.text", new Object[0]));
		appVersionLabel6.setName("appVersionLabel6");

		versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | 0x1));
		versionLabel.setText(resourceMap.getString("versionLabel.text", new Object[0]));
		versionLabel.setName("versionLabel");

		appVersionLabel.setText(resourceMap.getString("appVersionLabel.text", new Object[0]));
		appVersionLabel.setName("appVersionLabel");

		GroupLayout mainPanelLayout = new GroupLayout(this.mainPanel);
		this.mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addGroup(mainPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(mainPanelLayout.createSequentialGroup().addGroup(mainPanelLayout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(this.jLabel1, -1, 793, 32767)
								.addGroup(mainPanelLayout.createSequentialGroup()
										.addComponent(this.jScrollPane1, -2, 425, -2).addGap(23, 23, 23)
										.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(mainPanelLayout.createSequentialGroup()
														.addComponent(versionLabel)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(appVersionLabel))
												.addGroup(mainPanelLayout.createSequentialGroup()
														.addComponent(homepageLabel3)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(appVersionLabel5).addGap(120, 120, 120))
												.addGroup(mainPanelLayout.createSequentialGroup()
														.addComponent(homepageLabel2)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(appVersionLabel4))
												.addGroup(mainPanelLayout.createSequentialGroup()
														.addComponent(homepageLabel)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(appVersionLabel2))
												.addGroup(mainPanelLayout.createSequentialGroup()
														.addComponent(homepageLabel1)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(appVersionLabel3))
												.addGroup(mainPanelLayout.createSequentialGroup()
														.addComponent(homepageLabel4)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(appVersionLabel6))
												.addComponent(this.iPanel1, -1, -1, 32767)
												.addGroup(mainPanelLayout.createSequentialGroup()
														.addComponent(vendorLabel)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(appVersionLabel1)))))
								.addContainerGap())
						.addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
								.addComponent(this.jButton1, -2, 254, -2).addGap(252, 252, 252)))));

		mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
								GroupLayout.Alignment.TRAILING,
								mainPanelLayout.createSequentialGroup().addComponent(this.iPanel1, -2, -1, -2)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 7, 32767)
										.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(versionLabel).addComponent(appVersionLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(vendorLabel).addComponent(appVersionLabel1))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(homepageLabel2).addComponent(appVersionLabel4))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(homepageLabel).addComponent(appVersionLabel2))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(homepageLabel1).addComponent(appVersionLabel3))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(homepageLabel3).addComponent(appVersionLabel5))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(homepageLabel4).addComponent(appVersionLabel6)))
								.addComponent(this.jScrollPane1, -1, 376, 32767))
						.addGap(18, 18, 18).addComponent(this.jButton1, -2, -1, -2).addGap(8, 8, 8)));

		this.menuBar.setName("menuBar");

		fileMenu.setText(resourceMap.getString("fileMenu.text", new Object[0]));
		fileMenu.setName("fileMenu");

		exitMenuItem.setAction(applicationActionMap.get("quit"));
		exitMenuItem.setName("exitMenuItem");
		fileMenu.add(exitMenuItem);

		this.menuBar.add(fileMenu);

		helpMenu.setText(resourceMap.getString("helpMenu.text", new Object[0]));
		helpMenu.setName("helpMenu");

		aboutMenuItem.setAction(applicationActionMap.get("showAboutBox"));
		aboutMenuItem.setName("aboutMenuItem");
		helpMenu.add(aboutMenuItem);

		this.menuBar.add(helpMenu);

		setComponent(this.mainPanel);
		setMenuBar(this.menuBar);
	}

	@Action
	public void start() {
		this.app.showECDSAMainView(this);
	}
}
