package Output;
import org.javabip.api.BIPGlue;
import org.javabip.glue.GlueBuilder;



public class Project_Glue{
	private BIPGlue bipGlue;

	public Project_Glue(BIPGlue bipGlue){
		this.bipGlue = bipGlue;
	}

	public Project_Glue(){
		this.bipGlue = init();
	}

	public BIPGlue getBipGlue(){
		return this.bipGlue;
	}

	public void setBipGlue(BIPGlue bipGlue){
		this.bipGlue = bipGlue;
	}
	private BIPGlue init () {
		return bipGlue = new GlueBuilder() {
			@Override
			public void configure() {



				port(Camera_spec.class, Camera_ports.Camera_p_Camera).requires(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_to_High_Resolution);
				port(Camera_spec.class, Camera_ports.Camera_p_Camera).accepts(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_to_High_Resolution);
				port(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_to_High_Resolution).requires(Camera_spec.class, Camera_ports.Camera_p_Camera);
				port(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_to_High_Resolution).accepts(Camera_spec.class, Camera_ports.Camera_p_Camera);



				port(GPS_spec.class, GPS_ports.GPS_p_GPS).requires(Screen_spec.class, Screen_ports.Screen_p_not_Basic);
				port(GPS_spec.class, GPS_ports.GPS_p_GPS).accepts(Screen_spec.class, Screen_ports.Screen_p_not_Basic);
				port(Screen_spec.class, Screen_ports.Screen_p_not_Basic).requires(GPS_spec.class, GPS_ports.GPS_p_GPS);
				port(Screen_spec.class, Screen_ports.Screen_p_not_Basic).accepts(GPS_spec.class, GPS_ports.GPS_p_GPS);



				port(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_reset).requires(Camera_spec.class, Camera_ports.Camera_p_not_Camera_implies_Camera_High_Resolution);
				port(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_reset).accepts(Camera_spec.class, Camera_ports.Camera_p_not_Camera_implies_Camera_High_Resolution);
				port(Camera_spec.class, Camera_ports.Camera_p_not_Camera_implies_Camera_High_Resolution).requires(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_reset);
				port(Camera_spec.class, Camera_ports.Camera_p_not_Camera_implies_Camera_High_Resolution).accepts(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_reset);



				port(GPS_spec.class, GPS_ports.GPS_p_not_GPS).requires(Screen_spec.class, Screen_ports.Screen_p_Basic);
				port(GPS_spec.class, GPS_ports.GPS_p_not_GPS).accepts(Screen_spec.class, Screen_ports.Screen_p_Basic);
				port(Screen_spec.class, Screen_ports.Screen_p_Basic).requires(GPS_spec.class, GPS_ports.GPS_p_not_GPS);
				port(Screen_spec.class, Screen_ports.Screen_p_Basic).accepts(GPS_spec.class, GPS_ports.GPS_p_not_GPS);
			}
		}.build();
	}

}