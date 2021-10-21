package Output;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import org.javabip.annotations.Port;
import org.javabip.api.BIPActor;
import org.javabip.api.BIPEngine;
import org.javabip.api.BIPGlue;
import org.javabip.api.PortType;
import org.javabip.engine.factory.EngineFactory;
import akka.actor.ActorSystem;

public class test {

	private static BIPActor Camera_1_actor;
	private static BIPActor GPS_1_actor;
	private static BIPActor Calls_1_actor;
	private static BIPActor MP3_1_actor;
	private static BIPActor Screen_1_actor;

	private static ActorSystem system;
	private static EngineFactory engineFactory;
	static BIPGlue glue;
	static BIPEngine engine;

	private static Camera_spec Camera_1;
	private static GPS_spec GPS_1;
	private static Calls_spec Calls_1;
	private static MP3_spec MP3_1;
	private static Screen_spec Screen_1;

	private static TextField tf;
	public static void frame1() { 
		Frame f=new Frame(); 

		Button button_select_Camera = new Button("Camera");
		button_select_Camera.setBackground(Color.green);
		Button button_unselect_Camera = new Button("Camera");
		button_unselect_Camera.setBackground(Color.red);

		Button button_select_GPS = new Button("GPS");
		button_select_GPS.setBackground(Color.green);
		Button button_unselect_GPS = new Button("GPS");
		button_unselect_GPS.setBackground(Color.red);

		Button button_select_Calls = new Button("Calls");
		button_select_Calls.setBackground(Color.green);
		Button button_unselect_Calls = new Button("Calls");
		button_unselect_Calls.setBackground(Color.red);

		Button button_select_MP3 = new Button("MP3");
		button_select_MP3.setBackground(Color.green);
		Button button_unselect_MP3 = new Button("MP3");
		button_unselect_MP3.setBackground(Color.red);

		Button button_select_Colour = new Button("Colour");
		button_select_Colour.setBackground(Color.green);
		Button button_unselect_Colour = new Button("Colour");
		button_unselect_Colour.setBackground(Color.red);
		Button button_select_High_Resolution = new Button("High_Resolution");
		button_select_High_Resolution.setBackground(Color.green);
		Button button_unselect_High_Resolution = new Button("High_Resolution");
		button_unselect_High_Resolution.setBackground(Color.red);
		Button button_select_Basic = new Button("Basic");
		button_select_Basic.setBackground(Color.green);
		Button button_unselect_Basic = new Button("Basic");
		button_unselect_Basic.setBackground(Color.red);

		button_select_Camera.setBounds(20, 50, 130, 30);
		button_unselect_Camera.setBounds(20, 100, 130, 30);

		button_select_GPS.setBounds(180, 50, 130, 30);
		button_unselect_GPS.setBounds(180, 100, 130, 30);

		button_select_Calls.setBounds(340, 50, 130, 30);
		button_unselect_Calls.setBounds(340, 100, 130, 30);

		button_select_MP3.setBounds(500, 50, 130, 30);
		button_unselect_MP3.setBounds(500, 100, 130, 30);

		button_select_Colour.setBounds(660, 50, 130, 30);
		button_unselect_Colour.setBounds(660, 100, 130, 30);
		button_select_High_Resolution.setBounds(820, 50, 130, 30);
		button_unselect_High_Resolution.setBounds(820, 100, 130, 30);
		button_select_Basic.setBounds(980, 50, 130, 30);
		button_unselect_Basic.setBounds(980, 100, 130, 30);

		tf = new TextField(); 
		tf.setBounds(400,150,270,40); 

		button_select_Camera.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** Selecting Camera ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Selecting Camera ... result in terminal");
				Camera_1_actor.inform(Camera_ports.Camera_p_SCamera);
			}
		});


		button_unselect_Camera.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** UnSelecting Camera ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Unselecting Camera ... result in terminal");
				Camera_1_actor.inform(Camera_ports.Camera_p_SCamera_reset);
			}
		});

		button_select_GPS.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** Selecting GPS ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Selecting GPS ... result in terminal");
				GPS_1_actor.inform(GPS_ports.GPS_p_SGPS);
			}
		});


		button_unselect_GPS.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** UnSelecting GPS ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Unselecting GPS ... result in terminal");
				GPS_1_actor.inform(GPS_ports.GPS_p_SGPS_reset);
			}
		});

		button_select_Calls.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** Selecting Calls ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Selecting Calls ... result in terminal");
				Calls_1_actor.inform(Calls_ports.Calls_p_SCalls);
			}
		});


		button_unselect_Calls.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** UnSelecting Calls ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Unselecting Calls ... result in terminal");
				Calls_1_actor.inform(Calls_ports.Calls_p_SCalls_reset);
			}
		});

		button_select_MP3.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** Selecting MP3 ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Selecting MP3 ... result in terminal");
				MP3_1_actor.inform(MP3_ports.MP3_p_SMP3);
			}
		});


		button_unselect_MP3.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** UnSelecting MP3 ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Unselecting MP3 ... result in terminal");
				MP3_1_actor.inform(MP3_ports.MP3_p_SMP3_reset);
			}
		});

		button_select_Colour.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** Selecting Colour ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Selecting Colour ... result in terminal");
				Screen_1_actor.inform(Screen_ports.Screen_p_SColour);
			}
		});


		button_unselect_Colour.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** UnSelecting Colour ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Unselecting Colour ... result in terminal");
				Screen_1_actor.inform(Screen_ports.Screen_p_SColour_reset);
			}
		});
		button_select_High_Resolution.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** Selecting High_Resolution ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Selecting High_Resolution ... result in terminal");
				Screen_1_actor.inform(Screen_ports.Screen_p_SHigh_Resolution);
			}
		});


		button_unselect_High_Resolution.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** UnSelecting High_Resolution ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Unselecting High_Resolution ... result in terminal");
				Screen_1_actor.inform(Screen_ports.Screen_p_SHigh_Resolution_reset);
			}
		});
		button_select_Basic.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** Selecting Basic ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Selecting Basic ... result in terminal");
				Screen_1_actor.inform(Screen_ports.Screen_p_SBasic);
			}
		});


		button_unselect_Basic.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("********************************** ********************************** ********************************** ");
				System.out.println("********************************** UnSelecting Basic ********************************** ");
				System.out.println("********************************** ********************************** ********************************** ");
				tf.setText("Unselecting Basic ... result in terminal");
				Screen_1_actor.inform(Screen_ports.Screen_p_SBasic_reset);
			}
		});

		f.add(button_select_Camera);
		f.add(button_unselect_Camera);

		f.add(button_select_GPS);
		f.add(button_unselect_GPS);

		f.add(button_select_Calls);
		f.add(button_unselect_Calls);

		f.add(button_select_MP3);
		f.add(button_unselect_MP3);

		f.add(button_select_Colour);
		f.add(button_unselect_Colour);
		f.add(button_select_High_Resolution);
		f.add(button_unselect_High_Resolution);
		f.add(button_select_Basic);
		f.add(button_unselect_Basic);

		f.add(tf);
		f.setSize(1150,600); 
		f.setLayout(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		System.out.println("*************** Example Safe Dynamic reconfiguration  ***************");
		setUpBIP();
	}

	public static void setUpBIP() {
		system = ActorSystem.create("MySystem");
		engineFactory = new EngineFactory(system);
		glue = new Project_Glue().getBipGlue();
		engine = engineFactory.create("Engine", glue); // attached the glue to the engine

		Camera_1 = new Camera_spec();
		GPS_1 = new GPS_spec();
		Calls_1 = new Calls_spec();
		MP3_1 = new MP3_spec();
		Screen_1 = new Screen_spec();

		Camera_1_actor = engine.register(Camera_1, "Camera_component", true);
		GPS_1_actor = engine.register(GPS_1, "GPS_component", true);
		Calls_1_actor = engine.register(Calls_1, "Calls_component", true);
		MP3_1_actor = engine.register(MP3_1, "MP3_component", true);
		Screen_1_actor = engine.register(Screen_1, "Screen_component", true);

		int n = 1, option = 0; 
		engine.start();
		engine.execute();
		frame1();
		Scanner sc = new Scanner(System.in);
		while(n == 1) {
			System.out.println("Use the user interface");	
			option = sc.nextInt();
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		engine.stop();
		engineFactory.destroy(engine);
		System.out.println("The engine is stopped, satisfied?");
	}
}
