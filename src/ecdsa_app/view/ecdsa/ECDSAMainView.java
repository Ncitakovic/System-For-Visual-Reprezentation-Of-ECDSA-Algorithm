package ecdsa_app.view.ecdsa;

import ecdsa_app.ECDSAApp;
import ecdsa_app.view.ECDSAAboutBox;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.math.BigInteger;
import java.util.HashMap;
import javax.swing.JButton;		  
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import ac.essex.graphing.swing.GraphApplication;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

import ecdsa_app.control.ecdsa.ECPoint;
import ecdsa_app.control.ecdsa.EllipticCurve;
import ecdsa_app.control.ecdsa.PrivateKey;
import ecdsa_app.control.ecdsa.PublicKey;
import ecdsa_app.control.ecdsa.Sign;
import ecdsa_app.control.ecdsa.Signature;
import ecdsa_app.control.ecdsa.Verify;

			
public class ECDSAMainView extends FrameView {
			private ECDSAApp app;
			private FrameView previous;

			private JPanel contentPane;
			private JTextField textField_Pk;
			private JTextField textField_Pu;
			private JTextField textField_Sign;
			private JTextField textField_Verify;
			private JTextField textField_IsValid;
			
			private JTextField textField_a;
			private JTextField textField_b;
			private JTextField textField_p;
			private JTextField textField_n;
			

			class DomainParameters{
				BigInteger a;
				BigInteger b;
				BigInteger p;
				BigInteger n;
				BigInteger Gx;
				BigInteger Gy;
				DomainParameters(BigInteger a,BigInteger b,BigInteger p,BigInteger n, BigInteger Gx, BigInteger Gy) {
					this.a = a; this.b = b; this.p = p; this.n = n; this.Gx = Gx; this.Gy = Gy;
				}
			}
			DomainParameters currentDomainParameters;
			BasePointAndN currentBasePointAndN;
			HashMap<String, DomainParameters> specialCurves = new HashMap<String, DomainParameters>();
			HashMap<String, BasePointAndN> hashOfBasePointAndN;
			ScatterPlot scatterPlot;
			
			void initHashOfBasePointAndN(){
				hashOfBasePointAndN = ecParamGen.getHashOfPossibleGandN();
				String firstKey= (String) ecParamGen.getHashOfPossibleGandN().keySet().toArray()[0];
				currentBasePointAndN = hashOfBasePointAndN.get(firstKey);
				textField_n.setText(""+currentBasePointAndN.n);				
			}
			
			private String[] getListOfGs() {						
				String[] strs = new String[hashOfBasePointAndN.size()];
				int index = 0;
				for (String i : hashOfBasePointAndN.keySet()) {
					  strs[index++] = i;
				}
				return strs;
			}
			
			public ECDSAMainView(ECDSAApp app, FrameView fv) {
			  super(app);
			  getFrame().setDefaultCloseOperation(3);
			  getFrame().setResizable(false);
			  Dimension minimumSize = new Dimension(800, 700);
			  getFrame().setMinimumSize(minimumSize);
			  getFrame().pack();
			  this.app = app;
			  this.previous = fv;
			  initSpecialCurves();
			  initComponents();
			  currentDomainParameters = specialCurves.get("P-192");
			}

			void initSpecialCurves() {
				specialCurves.put("P-192", new DomainParameters(
						new BigInteger("-3"),
						new BigInteger("64210519e59c80e70fa7e9ab72243049feb8deecc146b9b1",16), 
						new BigInteger("6277101735386680763835789423207666416083908700390324961279"),
						new BigInteger("6277101735386680763835789423176059013767194773182842284081"),
						new BigInteger("188da80eb03090f67cbf20eb43a18800f4ff0afd82ff1012",16),
						new BigInteger("07192b95ffc8da78631011ed6b24cdd573f977a11e794811",16))
				);
				specialCurves.put("P-224", new DomainParameters(
						new BigInteger("-3"),
						new BigInteger("b4050a850c04b3abf54132565044b0b7d7bfd8ba270b39432355ffb4",16), 
						new BigInteger("26959946667150639794667015087019630673557916260026308143510066298881"),
						new BigInteger("26959946667150639794667015087019625940457807714424391721682722368061"),
						new BigInteger("b70e0cbd6bb4bf7f321390b94a03c1d356c21122343280d6115c1d21",16),
						new BigInteger("bd376388b5f723fb4c22dfe6cd4375a05a07476444d5819985007e34",16))								
				);
				specialCurves.put("P-256", new DomainParameters(
						new BigInteger("-3"),
						new BigInteger("5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b",16), 
						new BigInteger("115792089210356248762697446949407573530086143415290314195533631308867097853951"),
						new BigInteger("115792089210356248762697446949407573529996955224135760342422259061068512044369"),
						new BigInteger("6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296",16),
						new BigInteger("4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5",16))
				);
				specialCurves.put("P-384", new DomainParameters(
						new BigInteger("-3"),
						new BigInteger("b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef",16), 
						new BigInteger("39402006196394479212279040100143613805079739270465446667948293404245721771496870329047266088258938001861606973112319"),
						new BigInteger("39402006196394479212279040100143613805079739270465446667946905279627659399113263569398956308152294913554433653942643"),
						new BigInteger("aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7",16),
						new BigInteger("3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f",16))
				);
				specialCurves.put("P-521", new DomainParameters(
						new BigInteger("-3"),
						new BigInteger("051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00",16), 
						new BigInteger("6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151"),
						new BigInteger("6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449"),
						new BigInteger("c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66",16),
						new BigInteger("11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650",16))
				);				
				specialCurves.put("secp160k1", new DomainParameters(
						new BigInteger("0000000000000000000000000000000000000000",16),
						new BigInteger("0000000000000000000000000000000000000007",16),
						new BigInteger("fffffffffffffffffffffffffffffffeffffac73",16),
						new BigInteger("0100000000000000000001b8fa16dfab9aca16b6b3",16),
						new BigInteger("3b4c382ce37aa192a4019e763036f4f5dd4d7ebb",16),
						new BigInteger("938cf935318fdced6bc28286531733c3f03c4fee",16))
				);
				specialCurves.put("secp192k1", new DomainParameters(
						new BigInteger("000000000000000000000000000000000000000000000000",16),
						new BigInteger("000000000000000000000000000000000000000000000003",16),
						new BigInteger("fffffffffffffffffffffffffffffffffffffffeffffee37",16),
						new BigInteger("fffffffffffffffffffffffe26f2fc170f69466a74defd8d",16),
						new BigInteger("db4ff10ec057e9ae26b07d0280b7f4341da5d1b1eae06c7d",16),
						new BigInteger("9b2f2f6d9c5628a7844163d015be86344082aa88d95e2f9d",16))
				);
				specialCurves.put("secp224k1", new DomainParameters(
						new BigInteger("00000000000000000000000000000000000000000000000000000000",16),
						new BigInteger("00000000000000000000000000000000000000000000000000000005",16),
						new BigInteger("fffffffffffffffffffffffffffffffffffffffffffffffeffffe56d",16),
						new BigInteger("10000000000000000000000000001dce8d2ec6184caf0a971769fb1f7",16),
						new BigInteger("a1455b334df099df30fc28a169a467e9e47075a90f7e650eb6b7a45c",16),
						new BigInteger("7e089fed7fba344282cafbd6f7e319f7c0b0bd59e2ca4bdb556d61a5",16))
				);
				specialCurves.put("secp256k1", new DomainParameters(
						new BigInteger("0000000000000000000000000000000000000000000000000000000000000000",16),
						new BigInteger("0000000000000000000000000000000000000000000000000000000000000007",16),
						new BigInteger("fffffffffffffffffffffffffffffffffffffffffffffffffffffffefffffc2f",16),
						new BigInteger("fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141",16),
						new BigInteger("79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798",16),
						new BigInteger("483ada7726a3c4655da4fbfc0e1108a8fd17b448a68554199c47d08ffb10d4b8",16))
				);
				
			}
						
			private JDialog aboutBox;
			private PrivateKey PK;
			private PublicKey  PU;
			private EllipticCurve EC;
			private Sign sign;
			private Signature signature;
			private JTable jTableSignData;
			private JTable jTableVerifyData;
			private String msg;
			protected ECparamGen ecParamGen;
			
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
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				this.getFrame().setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JMenuBar menuBar = new JMenuBar();
				menuBar.setBounds(0, 0, 800, 22);
				contentPane.add(menuBar);
				
				JMenu mnFile = new JMenu("File");
				menuBar.add(mnFile);
				
				ApplicationActionMap applicationActionMap = ((ECDSAApp)Application.getInstance(ECDSAApp.class)).getContext().getActionMap(ECDSAMainView.class, this);
				
				JMenuItem mntmExit = new JMenuItem("Exit");
				mntmExit.setAction(applicationActionMap.get("quit"));
				mnFile.add(mntmExit);
				
				JMenu mnHelp = new JMenu("Help");
				menuBar.add(mnHelp);
				
				JMenuItem mntmAbout = new JMenuItem();
				mntmAbout.setAction(applicationActionMap.get("showAboutBox"));
				mntmAbout.setText("About");
				mnHelp.add(mntmAbout);
				
				JLabel lblTitle = new JLabel("Elliptic Curve Digital Signature Algorithm (ECDSA)");
				lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
				lblTitle.setBounds(100, 33, 606, 57);
				contentPane.add(lblTitle);
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setBounds(10, 96, 750, 550);
				contentPane.add(tabbedPane);
				
				JPanel panelDomainParameters = new JPanel();
				tabbedPane.addTab("Domain Parameters", null, panelDomainParameters, null);
				panelDomainParameters.setLayout(new BorderLayout());
				
				JPanel panelKeyGeneration = new JPanel();
				tabbedPane.addTab("Key Generation", null, panelKeyGeneration, null);
				panelKeyGeneration.setLayout(null);
				
				PK = new PrivateKey();
				
				
				JButton btnGenerateKeys = new JButton("Generate keys");				
				btnGenerateKeys.setBounds(300, 29, 145, 36);
				panelKeyGeneration.add(btnGenerateKeys);
				
				JLabel lbl_privKey = new JLabel("privKey:");
				lbl_privKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbl_privKey.setBounds(22, 93, 73, 27);
				panelKeyGeneration.add(lbl_privKey);
				
				textField_Pk = new JTextField();
				textField_Pk.setEditable(false);
				textField_Pk.setBounds(105, 98, 506, 20);
				panelKeyGeneration.add(textField_Pk);
				textField_Pk.setColumns(10);
				
				JLabel lbl_pubKey = new JLabel("pubKey:");
				lbl_pubKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbl_pubKey.setBounds(22, 138, 73, 19);
				panelKeyGeneration.add(lbl_pubKey);
				
				textField_Pu = new JTextField();
				textField_Pu.setEditable(false);
				textField_Pu.setBounds(105, 139, 506, 20);
				panelKeyGeneration.add(textField_Pu);
				textField_Pu.setColumns(10);
				
				btnGenerateKeys.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PK.generatePrivateKey();
						textField_Pk.setText(PK.getPrivateKey().toString());
						PU = new PublicKey(EC, new ECPoint(currentDomainParameters.Gx,currentDomainParameters.Gy), currentDomainParameters.n, PK.getPrivateKey());
						textField_Pu.setText(PU.getPublicKey().toString());
					}
				});
				
				JLabel lbl_privateKey_info = new JLabel("The private key is generated as a random integer in the range [1...n-1]. ");
				lbl_privateKey_info.setForeground(new Color(0, 102, 255));
				lbl_privateKey_info.setBounds(22, 385, 487, 27);
				panelKeyGeneration.add(lbl_privateKey_info);
				
				JLabel lbl_publicKey_info = new JLabel("The public key pubKey is a point on the elliptic curve, calculated by the EC point multiplication:");
				lbl_publicKey_info.setForeground(new Color(0, 102, 255));
				lbl_publicKey_info.setBounds(22, 411, 609, 22);
				panelKeyGeneration.add(lbl_publicKey_info);
				
				JLabel lbl_publicKey_info1 = new JLabel("pubKey = privKey * G (the private key, multiplied by the generator point G).");
				lbl_publicKey_info1.setForeground(new Color(0, 102, 255));
				lbl_publicKey_info1.setBounds(22, 436, 487, 14);
				panelKeyGeneration.add(lbl_publicKey_info1);
				
				JPanel panelSignatureGeneration = new JPanel();
				tabbedPane.addTab("Signature Generation", null, panelSignatureGeneration, null);
				panelSignatureGeneration.setLayout(null);
				
				JLabel lblMsgStringToSign = new JLabel("Message string to be signed:");
				lblMsgStringToSign.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMsgStringToSign.setBounds(34, 11, 263, 26);
				panelSignatureGeneration.add(lblMsgStringToSign);
				
				textField_Sign = new JTextField();
				textField_Sign.setBounds(34, 37, 595, 20);
				panelSignatureGeneration.add(textField_Sign);
				textField_Sign.setColumns(10);
											
				JButton btnSignMsg = new JButton("sign message");
				btnSignMsg.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						msg = textField_Sign.getText();
						sign = new Sign(EC,new ECPoint(currentDomainParameters.Gx,currentDomainParameters.Gy),currentDomainParameters.n,PK, msg);
						do {
						signature = sign.signing();
						}while(signature.getS().equals(new BigInteger("0")));
						
						String[] columnNames = { "                Variable", "                Value" };
						String[][] data = {
					            { "hash" , sign.getH().toString() },
					            { "n" , sign.getN().toString() },
					            { "k" , sign.getK().toString() },
					            { "G", sign.getG().toString() },
					            { "R", sign.getkG().toString() },
					            { "r", signature.getR().toString() },
					            { "s", signature.getS().toString() },
					            { "signature", "{"+ signature.getR().toString() +","+signature.getS().toString() +"}"}
					    };
						jTableSignData = new JTable(data,columnNames);
//						jTableSignData.setBounds(50,80,100,100);
						JScrollPane sp = new JScrollPane(jTableSignData);
						sp.setBounds(70,120,600,100);
						panelSignatureGeneration.add(sp);
//						sp.setViewportView(jTableSignData);						
					}
				});
				btnSignMsg.setBounds(34, 63, 109, 23);
				panelSignatureGeneration.add(btnSignMsg);
				
				JLabel lblNewLabel_Steps = new JLabel("Steps:");
				lblNewLabel_Steps.setForeground(new Color(0, 102, 255));
				lblNewLabel_Steps.setBounds(22, 270, 487, 15);
				panelSignatureGeneration.add(lblNewLabel_Steps);
								
				JLabel lblNewLabel_Hash = new JLabel("1.Calculating the message hash, using a cryptographic hash function SHA-1: h = hash(msg)");
				lblNewLabel_Hash.setForeground(new Color(0, 102, 255));
				lblNewLabel_Hash.setBounds(22, 300, 487, 15);
				panelSignatureGeneration.add(lblNewLabel_Hash);
				
				JLabel lblNewLabel_k_gen = new JLabel("2.Generating securely a random number k in the range [1..n-1]");
				lblNewLabel_k_gen.setForeground(new Color(0, 102, 255));
				lblNewLabel_k_gen.setBounds(22, 330, 487, 15);
				panelSignatureGeneration.add(lblNewLabel_k_gen);							
				
				JLabel lblNewLabel_R_gen = new JLabel("3.Calculating the random point R = k * G");
				lblNewLabel_R_gen.setForeground(new Color(0, 102, 255));
				lblNewLabel_R_gen.setBounds(22, 360, 487, 15);
				panelSignatureGeneration.add(lblNewLabel_R_gen);
				
				JLabel lblNewLabel_r_gen = new JLabel("4.Taking x-coordinate of random point R: r = R.x");
				lblNewLabel_r_gen.setForeground(new Color(0, 102, 255));
				lblNewLabel_r_gen.setBounds(22, 390, 487, 15);
				panelSignatureGeneration.add(lblNewLabel_r_gen);
				
				JLabel lblNewLabel_s_gen = new JLabel("5.Calculating the signature proof: s = k^(-1) * (h + r * privKey) (mod n)");
				lblNewLabel_s_gen.setForeground(new Color(0, 102, 255));
				lblNewLabel_s_gen.setBounds(22, 420, 487, 15);
				panelSignatureGeneration.add(lblNewLabel_s_gen);
				
				JLabel lblNewLabel_sig_gen = new JLabel("6. Returning signature {r,s}");
				lblNewLabel_sig_gen.setForeground(new Color(0, 102, 255));
				lblNewLabel_sig_gen.setBounds(22, 450, 487, 15);
				panelSignatureGeneration.add(lblNewLabel_sig_gen);
				
				JPanel panelVerification = new JPanel();
				tabbedPane.addTab("Verification", null, panelVerification, null);
				panelVerification.setLayout(null);
				
				JLabel msgLabel = new JLabel("message: ");
				msgLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				msgLabel.setBounds(70, 90, 88, 27);
				panelVerification.add(msgLabel);
				
				textField_Verify = new JTextField();
				textField_Verify.setBounds(70,120,600,20);
				panelVerification.add(textField_Verify);
				
				textField_IsValid = new JTextField("is Valid?");
				textField_IsValid.setBounds(350, 480, 50, 15);
				panelVerification.add(textField_IsValid);

				
				JButton btnVerify = new JButton("verify signature");
				btnVerify.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {	
						Verify verify = new Verify(PU,PU.n,signature,msg);
						System.out.println(verify.verification());
						
						//verification						
						String[] columnNames = { "                Variable", "                Value" };
						String[][] data = {
					            { "hash" , verify.getH().toString() },
					            { "n" , verify.getN().toString() },
					            { "w" , verify.getW().toString() },
					            { "G", verify.getPU().G.toString() },
					            { "u1", verify.getU1().toString() },
					            { "u2", verify.getU2().toString() },
					            { "R",  verify.getR().toString() },
					            { "r", signature.getR().toString() },
					            { "v", verify.getV().toString() },
					            { "signature", "{"+ signature.getR().toString() +","+signature.getS().toString() +"}"}
					    };
						jTableVerifyData = new JTable(data,columnNames);
//						jTableVerifyData.setBounds(50,80,100,100);
						JScrollPane sp = new JScrollPane(jTableVerifyData);
						sp.setBounds(70,160,600,120);
						panelVerification.add(sp);					
						
						textField_Verify.setText(verify.getMsg());
						if(signature.getR().equals(verify.getV())) {
							textField_IsValid.setText("VALID");
							textField_IsValid.setForeground(new Color(0,128,0));

						}
						else { 
							textField_IsValid.setText("INVALID");
							textField_IsValid.setForeground(new Color(255,0,0));
						}
						
					}
				});
				btnVerify.setBounds(300, 29, 136, 29);
				panelVerification.add(btnVerify);
				
				JLabel lblNewLabel1_Steps = new JLabel("Steps:");
				lblNewLabel1_Steps.setForeground(new Color(0, 102, 255));
				lblNewLabel1_Steps.setBounds(22, 300, 487, 15);
				panelVerification.add(lblNewLabel1_Steps);
				
				JLabel lblNewLabel1_Hash = new JLabel("1.Calculating the message hash, with the same cryptographic hash function used during the signing: h = hash(msg)");
				lblNewLabel1_Hash.setForeground(new Color(0, 102, 255));
				lblNewLabel1_Hash.setBounds(22, 330, 587, 15);
				panelVerification.add(lblNewLabel1_Hash);
				
				JLabel lblNewLabel1_k_gen = new JLabel("2.Calculating the modular inverse of the signature proof: w = s^(-1) (mod n)");
				lblNewLabel1_k_gen.setForeground(new Color(0, 102, 255));
				lblNewLabel1_k_gen.setBounds(22, 360, 487, 15);
				panelVerification.add(lblNewLabel1_k_gen);							
				
				JLabel lblNewLabel1_R_gen = new JLabel("3.Recovering the random point used during the signing: R' = (h * w) * G + (r * w) * pubKey");
				lblNewLabel1_R_gen.setForeground(new Color(0, 102, 255));
				lblNewLabel1_R_gen.setBounds(22, 390, 487, 15);
				panelVerification.add(lblNewLabel1_R_gen);
				
				JLabel lblNewLabel1_r_gen = new JLabel("4.Taking from R' its x-coordinate: v = R'.x");
				lblNewLabel1_r_gen.setForeground(new Color(0, 102, 255));
				lblNewLabel1_r_gen.setBounds(22, 420, 487, 15);
				panelVerification.add(lblNewLabel1_r_gen);
				
				JLabel lblNewLabel1_s_gen = new JLabel("5.Calculating the signature validation result by comparing whether v == r");
				lblNewLabel1_s_gen.setForeground(new Color(0, 102, 255));
				lblNewLabel1_s_gen.setBounds(22, 450, 487, 15);
				panelVerification.add(lblNewLabel1_s_gen);								
				
				//1st tab
				final JPanel cards = new JPanel(new CardLayout());

				JRadioButton rb1 = new JRadioButton("NIST and SECG Elliptic Curves", true);
				JRadioButton rb2 = new JRadioButton("Elliptic Curves", false);
				ButtonGroup buttons = new ButtonGroup();		
				buttons.add(rb1);
				buttons.add(rb2);
				
				JPanel radioButton = new JPanel();
				
				radioButton.add(rb1);
				radioButton.add(rb2);
				
				JPanel card1 = new JPanel();
				
				JPanel card2 = new JPanel();
				
				cards.add(card1, "MyPanel1");
				card1.setLayout(null);
				
				JLabel lblSelectCurve = new JLabel("Select curve:");
				lblSelectCurve.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblSelectCurve.setBounds(116, 48, 88, 27);
				card1.add(lblSelectCurve);
				
				
				JLabel lblDomParamDef1 = new JLabel(" elliptic curve domain parameters over Fp are a sextuple:");
				lblDomParamDef1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDomParamDef1.setBounds(200, 200, 588, 27);
				lblDomParamDef1.setForeground(new Color(0,102,255));
				card1.add(lblDomParamDef1);
				
				JLabel lblDomParamDef2 = new JLabel("T = (p,a,b,G,n,h)");
				lblDomParamDef2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDomParamDef2.setBounds(320, 230, 588, 27);
				lblDomParamDef2.setForeground(new Color(0,102,255));
				card1.add(lblDomParamDef2);
				
				JLabel lblDomParamDef3 = new JLabel("consisting of an integer p specifying the finite field Fp, two elements a, b included in Fp specifying an elliptic curve ");
				lblDomParamDef3.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDomParamDef3.setBounds(10, 260, 708, 27);
				lblDomParamDef3.setForeground(new Color(0,102,255));
				card1.add(lblDomParamDef3);
				
				JLabel lblDomParamDef4 = new JLabel("E(Fp) defined by the equation:");
				lblDomParamDef4.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDomParamDef4.setBounds(10, 290, 708, 27);
				lblDomParamDef4.setForeground(new Color(0,102,255));
				card1.add(lblDomParamDef4);
				
				JLabel lblDomParamDef5 = new JLabel("E: y^(2) = x^3 + ax + b (mod p),");
				lblDomParamDef5.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDomParamDef5.setBounds(280, 320, 708, 27);
				lblDomParamDef5.setForeground(new Color(0,102,255));
				card1.add(lblDomParamDef5);
				
				JLabel lblDomParamDef6 = new JLabel("a base point G=(Xg,Yg) on E(Fp), a prime n which is the order of G,and an integer h which is the");
				lblDomParamDef6.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDomParamDef6.setBounds(10, 350, 708, 27);
				lblDomParamDef6.setForeground(new Color(0,102,255));
				card1.add(lblDomParamDef6);
				
				JLabel lblDomParamDef7 = new JLabel("cofactor h = #E(Fp)/n.");
				lblDomParamDef7.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDomParamDef7.setBounds(10, 380, 708, 27);
				lblDomParamDef7.setForeground(new Color(0,102,255));
				card1.add(lblDomParamDef7);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"P-192", "P-224", "P-256", "P-384", "P-521", "secp160k1", "secp192k1", "secp224k1", "secp256k1"}));
				comboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if(comboBox.getSelectedItem().toString().equals("P-192")) {
							System.out.println("192");
							currentDomainParameters = specialCurves.get("P-192");
						}else if(comboBox.getSelectedItem().toString().equals("P-224")) {
							System.out.println("224");
							currentDomainParameters = specialCurves.get("P-224");
						}else if(comboBox.getSelectedItem().toString().equals("P-256")) {
							System.out.println("256");
							currentDomainParameters = specialCurves.get("P-256");
						}else if(comboBox.getSelectedItem().toString().equals("P-384")) {
							System.out.println("384");
							currentDomainParameters = specialCurves.get("P-384");
						}else if(comboBox.getSelectedItem().toString().equals("P-521")){
							System.out.println("521");
							currentDomainParameters = specialCurves.get("P-521");
						}else if(comboBox.getSelectedItem().toString().equals("secp160k1")){
							System.out.println("secp160k1");
							currentDomainParameters = specialCurves.get("secp160k1");
						}else if(comboBox.getSelectedItem().toString().equals("secp192k1")){
							System.out.println("secp192k1");
							currentDomainParameters = specialCurves.get("secp192k1");
						}else if(comboBox.getSelectedItem().toString().equals("secp224k1")){
							System.out.println("secp224k1");
							currentDomainParameters = specialCurves.get("secp224k1");
						}else {							
							System.out.println("secp256k1");
							currentDomainParameters = specialCurves.get("secp256k1");
						} 	 	 	 	 	
			            PK.setN(currentDomainParameters.n);
						EC = new EllipticCurve(currentDomainParameters.a, currentDomainParameters.b, currentDomainParameters.p);
					}
				});
				
				comboBox.setBounds(235, 52, 88, 22);
				card1.add(comboBox);
				
				JButton btnShowCurve = new JButton("show curve");
				btnShowCurve.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {						
							String s = e.getActionCommand();
					        if (s.equals("show curve")) {
					            // create a dialog Box
					        	//GraphApplication g = new ScatterPlot(getFrame(), "dialog Box");
					        	double a = currentDomainParameters.a.doubleValue();
					        	double b = currentDomainParameters.b.doubleValue();
					        	EllipticCurveDiagram ecd = new EllipticCurveDiagram(a,b);
					        	GraphWindow g = new GraphWindow(ecd.getExampleGraph());
					        						        	
					            // setsize of dialog
					            g.setSize(800, 600);
					            
					            // set visibility of dialog
					            g.setVisible(true);
					        }
					}
				});
				btnShowCurve.setBounds(428, 50, 115, 27);
				card1.add(btnShowCurve);
				cards.add(card2, "MyPanel2");
				card2.setLayout(null);
				
				JLabel lblEnterDomParams = new JLabel("Enter domain parameters");
				lblEnterDomParams.setBounds(68, 25, 176, 22);
				card2.add(lblEnterDomParams);
				
				textField_a = new JTextField();
				textField_a.setBounds(68, 58, 239, 20);
				card2.add(textField_a);
				textField_a.setColumns(10);
				
				textField_b = new JTextField();
				textField_b.setColumns(10);
				textField_b.setBounds(68, 89, 239, 20);
				card2.add(textField_b);
				
				textField_p = new JTextField();
				textField_p.setColumns(10);
				textField_p.setBounds(68, 120, 239, 20);
				card2.add(textField_p);
				
				textField_n = new JTextField();
				textField_n.setColumns(10);
				textField_n.setEditable(false);
				textField_n.setBounds(68, 212, 239, 20);
				card2.add(textField_n);
							
				JComboBox comboBox_G = new JComboBox();
//				comboBox_G.setModel(new DefaultComboBoxModel(new String[] {"P-192", "P-224", "P-256", "P-384", "P-521", "secp160k1", "secp192k1", "secp224k1", "secp256k1"}));
				comboBox_G.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
						String[] strings = getListOfGs();
						for(String s : strings) {
							if(s.equals(comboBox_G.getSelectedItem().toString())) {
								currentBasePointAndN = hashOfBasePointAndN.get(s);
//								currentDomainParameters = new DomainParameters(null, null, null, BigInteger.valueOf(b.Gx), BigInteger.valueOf(b.Gy), BigInteger.valueOf(b.n));
								textField_n.setText(""+currentBasePointAndN.n);
							}
						}

					}
				});				
				comboBox_G.setBounds(68, 181, 239, 20);
				card2.add(comboBox_G);
				
				JLabel lbl_param_a = new JLabel("a:");
				lbl_param_a.setBounds(41, 61, 17, 14);
				card2.add(lbl_param_a);
				
				JLabel lbl_param_b = new JLabel("b:");
				lbl_param_b.setBounds(41, 92, 17, 14);
				card2.add(lbl_param_b);
				
				JLabel lbl_param_p = new JLabel("p:");
				lbl_param_p.setBounds(41, 123, 17, 14);
				card2.add(lbl_param_p);
				
				JLabel lbl_param_n = new JLabel("n:");
				lbl_param_n.setBounds(41, 215, 17, 14);
				card2.add(lbl_param_n);
				
				JLabel lbl_param_G = new JLabel("G:");
				lbl_param_G.setBounds(41, 184, 17, 14);
				card2.add(lbl_param_G);				
				
				JButton btnUpdate = new JButton("update");
				btnUpdate.setBounds(68, 145, 120, 30);
				btnUpdate.addActionListener(new ActionListener() {
					public boolean isPrime(int number) {
					    BigInteger bigInt = BigInteger.valueOf(number);
					    return bigInt.isProbablePrime(100);
					}
					public void actionPerformed(ActionEvent e) {

						String s = e.getActionCommand();
				        if (s.equals("update")) {
				            long aBig = Long.parseLong(textField_a.getText());
				            long bBig = Long.parseLong(textField_b.getText());				            
				            long pBig = Long.parseLong(textField_p.getText());
				            		
				            if(isPrime((int) pBig)) {
	//				            scatterPlot = new ScatterPlot(getFrame(), "Diagram", aBig, bBig, pBig);
					            ecParamGen = new ECparamGen(aBig, bBig, pBig);
	
					            initHashOfBasePointAndN();
					            
					            String[] strs = getListOfGs();		
					            comboBox_G.setModel(new DefaultComboBoxModel(strs));
	//				            currentDomainParameters = new DomainParameters(BigInteger.valueOf(aBig), BigInteger.valueOf(bBig),BigInteger.valueOf(pBig), BigInteger.valueOf(nBig), BigInteger.valueOf(GxBig),BigInteger.valueOf(GyBig));			
	//				            EC = new EllipticCurve(currentDomainParameters.a,currentDomainParameters.b,currentDomainParameters.p);
				            }else {
				            	 JDialog dialogMustPrime = new JDialog(getFrame(),"Primarity");
				            	 // setsize of dialog
				            	 dialogMustPrime.setBounds(600,300,300,300); 
				            	 
				            	 JLabel lab = new JLabel("p must be prime number!",JLabel.CENTER);
						         lab.setForeground(new Color(255,0,0));						         
				            	 dialogMustPrime.add(lab);
						 
						         // set visibility of dialog
				            	 dialogMustPrime.setVisible(true);				            	
				            }
				            
				        }
					}
				});
				
				JLabel exampleDomParams = new JLabel("Example of domain parameters");
				exampleDomParams.setForeground(new Color(0, 102, 255));
				exampleDomParams.setBounds(41,330,300,14);
				card2.add(exampleDomParams);
				
				JLabel exampleDomParam1 = new JLabel("a=2, b=3, p=97, n=5, G=(3,91)/G=(3,6)");
				exampleDomParam1.setForeground(new Color(0, 102, 255));
				exampleDomParam1.setBounds(41,361,300,14);
				card2.add(exampleDomParam1);
				
				JLabel exampleDomParam2 = new JLabel("a=-3, b=7, p=11, n=7, G=(1,4)/G=(9,4)");
				exampleDomParam2.setForeground(new Color(0, 102, 255));
				exampleDomParam2.setBounds(41,392,300,14);
				card2.add(exampleDomParam2);
				
				JButton btnShowDiagram = new JButton("show diagram");
				btnShowDiagram.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String s = e.getActionCommand();
				        if (s.equals("show diagram")) {

				        	long a = currentDomainParameters.a.longValue();
				        	long b = currentDomainParameters.b.longValue();
				        	long p = currentDomainParameters.p.longValue();

				            JDialog d = new ScatterPlot(getFrame(), "Diagram", ecParamGen);
				        	
				            // create a label
				            JLabel l = new JLabel("this is a dialog box");				            
				            d.add(l);
				 
				            // setsize of dialog
				            d.setSize(800, 600);
				 
				            // set visibility of dialog
				            d.setVisible(true);
				        }
					}
				});
				btnShowDiagram.setBounds(442, 97, 105, 29);
				
				JButton btnSubmit = new JButton("Submit");
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String s = e.getActionCommand();
				        if (s.equals("Submit")) {
				            long aBig = Long.parseLong(textField_a.getText());
				            long bBig = Long.parseLong(textField_b.getText());				            
				            long pBig = Long.parseLong(textField_p.getText());

				            currentDomainParameters = new DomainParameters(BigInteger.valueOf(aBig), BigInteger.valueOf(bBig),BigInteger.valueOf(pBig), BigInteger.valueOf(currentBasePointAndN.n), BigInteger.valueOf(currentBasePointAndN.Gx), BigInteger.valueOf(currentBasePointAndN.Gy));
				            PK.setN(currentDomainParameters.n);
				            
				            EC = new EllipticCurve(currentDomainParameters.a,currentDomainParameters.b,currentDomainParameters.p);
				        }
					}
				});
				btnSubmit.setBounds(68, 280, 120, 30);
				
				JButton btnShowCurve1 = new JButton("show curve");
				btnShowCurve1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {						
							String s = e.getActionCommand();
					        if (s.equals("show curve")) {
					            // create a dialog Box
					        	//GraphApplication g = new ScatterPlot(getFrame(), "dialog Box");
					        	double a = currentDomainParameters.a.doubleValue();
					        	double b = currentDomainParameters.b.doubleValue();
					        	EllipticCurveDiagram ecd = new EllipticCurveDiagram(a,b);
					        	GraphWindow g = new GraphWindow(ecd.getExampleGraph());
					        	
					            // setsize of dialog
					            g.setSize(800, 600);
					            
					            // set visibility of dialog
					            g.setVisible(true);
					        }
					}
				});
				btnShowCurve1.setBounds(442, 167, 105, 29);				
				
				card2.add(btnShowDiagram);				
				card2.add(btnSubmit);
				card2.add(btnShowCurve1);
				
				card2.add(btnUpdate);
				
				rb1.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	
			        	CardLayout cl = (CardLayout)(cards.getLayout());
			    		if(e.getSource() == rb1)
			    		    cl.show(cards, "MyPanel1");
			        }
			    });
				
				rb2.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	CardLayout cl = (CardLayout)(cards.getLayout());
			    		if(e.getSource() == rb2)
			    		    cl.show(cards, "MyPanel2");
			        }
			    });
				
				panelDomainParameters.add(radioButton,BorderLayout.PAGE_START);
				panelDomainParameters.add(cards, BorderLayout.CENTER);											
			}

			@Action
			public void back() {
			  getFrame().setVisible(false);
              this.previous.getFrame().setVisible(true);
		   }
   
   			@Action
   			public void next() {}
 
}
