
package ecdsa_app;

/**
 *
 * @author nikol
 */



/*     */ import ecdsa_app.view.ECDSAIntroView;
		//import cavs.view.CAVSMainView;
		//import cavs.view.rsa.CAVSRSAMainView;

		  import java.awt.Window;
		  import org.jdesktop.application.Application;
		  import org.jdesktop.application.FrameView;
		  import org.jdesktop.application.SingleFrameApplication;
		  import ecdsa_app.view.ecdsa.*;
 
		  public class ECDSAApp extends SingleFrameApplication
		{
			protected void startup() {
				show(new ECDSAIntroView(this));
     
			}
 
			public void showECDSAMainView(FrameView fv) {
			  fv.getFrame().setVisible(false);
			  show(new ECDSAMainView(this,fv)); 
			}			

			protected void configureWindow(Window root) {}
 
   
			public static ECDSAApp getApplication() { return (ECDSAApp)Application.getInstance(ECDSAApp.class); }
 
			public static void main(String[] args) { launch(ECDSAApp.class, args); }

		}

