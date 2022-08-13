
/**
 *
 * @author nikol
 */
/*     */ package ecdsa_app.view;

import ecdsa_app.ECDSAApp;
import ecdsa_app.control.IPanel;
import java.awt.Frame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.ResourceMap;

public class ECDSAAboutBox extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton closeButton;

	public ECDSAAboutBox(Frame parent) {
		super(parent);
		initComponents();
		getRootPane().setDefaultButton(this.closeButton);

		ResourceMap resourceMap = ((ECDSAApp) Application.getInstance(ECDSAApp.class)).getContext()
				.getResourceMap(ECDSAAboutBox.class);
		this.iPanel1.setName("iPanel1");
		ImageIcon icon = (ImageIcon) resourceMap.getIcon("iPanel.icon");
		this.iPanel1.setImg(icon.getImage());
		this.iPanel1.setLayout(null);
	}

	private IPanel iPanel1;

	@Action
	public void closeAboutBox() {
		dispose();
	}

	private void initComponents() {
		this.closeButton = new JButton();
		JLabel appTitleLabel = new JLabel();
		JLabel versionLabel = new JLabel();
		JLabel appVersionLabel = new JLabel();
		JLabel vendorLabel = new JLabel();
		JLabel homepageLabel = new JLabel();
		JLabel appDescLabel = new JLabel();
		JLabel appVersionLabel1 = new JLabel();
		JLabel appVersionLabel2 = new JLabel();
		JLabel homepageLabel1 = new JLabel();
		JLabel appVersionLabel3 = new JLabel();
		JLabel homepageLabel2 = new JLabel();
		JLabel appVersionLabel4 = new JLabel();
		JLabel homepageLabel3 = new JLabel();
		JLabel appVersionLabel5 = new JLabel();
		JLabel homepageLabel4 = new JLabel();
		JLabel appVersionLabel6 = new JLabel();
		this.iPanel1 = new IPanel();

		setDefaultCloseOperation(2);
		ResourceMap resourceMap = ((ECDSAApp) Application.getInstance(ECDSAApp.class)).getContext()
				.getResourceMap(ECDSAAboutBox.class);
		setTitle(resourceMap.getString("title", new Object[0]));
		setModal(true);
		setName("aboutBox");
		setResizable(false);

		ApplicationActionMap applicationActionMap = ((ECDSAApp) Application.getInstance(ECDSAApp.class)).getContext()
				.getActionMap(ECDSAAboutBox.class, this);
		this.closeButton.setAction(applicationActionMap.get("closeAboutBox"));
		this.closeButton.setName("closeButton");

		appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | 0x1,
				(appTitleLabel.getFont().getSize() + 4)));
		appTitleLabel.setText(resourceMap.getString("Application.title", new Object[0]));
		appTitleLabel.setName("appTitleLabel");

		versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | 0x1));
		versionLabel.setText(resourceMap.getString("versionLabel.text", new Object[0]));
		versionLabel.setName("versionLabel");

		appVersionLabel.setText(resourceMap.getString("Application.version", new Object[0]));
		appVersionLabel.setName("appVersionLabel");

		vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | 0x1));
		vendorLabel.setText(resourceMap.getString("vendorLabel.text", new Object[0]));
		vendorLabel.setName("vendorLabel");

		homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | 0x1));
		homepageLabel.setText(resourceMap.getString("homepageLabel.text", new Object[0]));
		homepageLabel.setName("homepageLabel");

		appDescLabel.setText(resourceMap.getString("appDescLabel.text", new Object[0]));
		appDescLabel.setName("appDescLabel");

		appVersionLabel1.setText(resourceMap.getString("appVersionLabel1.text", new Object[0]));
		appVersionLabel1.setName("appVersionLabel1");

		appVersionLabel2.setText(resourceMap.getString("appVersionLabel2.text", new Object[0]));
		appVersionLabel2.setName("appVersionLabel2");

		homepageLabel1.setFont(homepageLabel1.getFont().deriveFont(homepageLabel1.getFont().getStyle() | 0x1));
		homepageLabel1.setText(resourceMap.getString("homepageLabel1.text", new Object[0]));
		homepageLabel1.setName("homepageLabel1");

		appVersionLabel3.setText(resourceMap.getString("appVersionLabel3.text", new Object[0]));
		appVersionLabel3.setName("appVersionLabel3");

		homepageLabel2.setFont(homepageLabel2.getFont().deriveFont(homepageLabel2.getFont().getStyle() | 0x1));
		homepageLabel2.setText(resourceMap.getString("homepageLabel2.text", new Object[0]));
		homepageLabel2.setName("homepageLabel2");

		appVersionLabel4.setText(resourceMap.getString("appVersionLabel4.text", new Object[0]));
		appVersionLabel4.setName("appVersionLabel4");

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

		this.iPanel1.setName("iPanel1");

		GroupLayout iPanel1Layout = new GroupLayout(this.iPanel1);
		this.iPanel1.setLayout(iPanel1Layout);
		iPanel1Layout.setHorizontalGroup(
				iPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 159, 32767));

		iPanel1Layout.setVerticalGroup(
				iPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 230, 32767));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(this.iPanel1, -2, -1, -2).addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addComponent(versionLabel)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(appVersionLabel))
												.addComponent(appTitleLabel).addComponent(homepageLabel3))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(appVersionLabel5).addGap(120, 120, 120))
								.addComponent(appDescLabel, -1, 359, 32767)
								.addGroup(layout.createSequentialGroup().addComponent(homepageLabel2)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(appVersionLabel4))
								.addGroup(layout.createSequentialGroup().addComponent(homepageLabel)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(appVersionLabel2))
								.addGroup(layout.createSequentialGroup().addComponent(homepageLabel1)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(appVersionLabel3))
								.addGroup(layout.createSequentialGroup().addComponent(homepageLabel4)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(appVersionLabel6))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addComponent(this.closeButton)
										.addGroup(layout.createSequentialGroup().addComponent(vendorLabel)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(appVersionLabel1))))
						.addContainerGap()));

		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(appTitleLabel)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(appDescLabel, -2, -1, -2)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(versionLabel).addComponent(appVersionLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(vendorLabel).addComponent(appVersionLabel1))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(homepageLabel2).addComponent(appVersionLabel4))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(homepageLabel).addComponent(appVersionLabel2))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(homepageLabel1).addComponent(appVersionLabel3))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(homepageLabel3).addComponent(appVersionLabel5))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(homepageLabel4).addComponent(appVersionLabel6))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
								.addComponent(this.closeButton).addContainerGap())
						.addComponent(this.iPanel1, -1, -1, 32767));

		pack();
	}
}
