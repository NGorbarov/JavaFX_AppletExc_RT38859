import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.JApplet;

/*
Java 8 update 20, Java 8 update 40b7
Reported bug: RT-38859 
 
When WebView inside JFXPanel in Applet on MacOS X within Safari, the WebView dumps the following exception: 
Exception in thread "JavaFX Application Thread" java.lang.NullPointerException 
   at sun.awt.SunToolkit.getSystemEventQueueImplPP(SunToolkit.java:1082) 
   at sun.awt.SunToolkit.getSystemEventQueueImplPP(SunToolkit.java:1077) 
   at sun.awt.SunToolkit.getSystemEventQueueImpl(SunToolkit.java:1072) 
   at java.awt.Toolkit.getEventQueue(Toolkit.java:1734) 
   at java.awt.EventQueue.invokeLater(EventQueue.java:1252) 
   at javax.swing.SwingUtilities.invokeLater(SwingUtilities.java:1290) 
   at javafx.embed.swing.JFXPanel$HostContainer.setCursor(JFXPanel.java:876) 
   at com.sun.javafx.tk.quantum.EmbeddedScene.setCursor(EmbeddedScene.java:314) 
   at javafx.scene.Scene$MouseHandler.updateCursorFrame(Scene.java:3857) 
   at javafx.scene.Scene$ScenePulseListener.pulse(Scene.java:2407) 
   at com.sun.javafx.tk.Toolkit.lambda$runPulse$28(Toolkit.java:314) 
   at com.sun.javafx.tk.Toolkit$$Lambda$114/1581091068.run(Unknown Source) 
   at java.security.AccessController.doPrivileged(Native Method) 
   at com.sun.javafx.tk.Toolkit.runPulse(Toolkit.java:313) 
   at com.sun.javafx.tk.Toolkit.firePulse(Toolkit.java:340) 
   at com.sun.javafx.tk.quantum.QuantumToolkit.pulse(QuantumToolkit.java:451) 
   at com.sun.javafx.tk.quantum.QuantumToolkit.pulse(QuantumToolkit.java:431) 
   at com.sun.javafx.tk.quantum.QuantumToolkit.lambda$runToolkit$363(QuantumToolkit.java:298) 
   at com.sun.javafx.tk.quantum.QuantumToolkit$$Lambda$59/1564976993.run(Unknown Source) 
   at com.sun.glass.ui.InvokeLaterDispatcher$Future.run(InvokeLaterDispatcher.java:95) 
 */


public class TestApplet extends JApplet { 

    public void init() { 
        System.setSecurityManager(null); 

        final JFXPanel p = new JFXPanel(); 
        add(p); 

        Platform.runLater(new Runnable() { 
            public void run() { 
                final WebView v = new WebView(); 
                final Scene s = new Scene(v); 
                p.setScene(s); 
                v.getEngine().load("http://www.sap.com"); 
            } 
        }); 
    } 
} 