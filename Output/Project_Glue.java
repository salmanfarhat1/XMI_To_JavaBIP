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



				port(Screen_spec.class, Screen_ports.Screen_p_Colour).requires(Camera_spec.class, Camera_ports.Camera_p_Camera_to_Camera);
				port(Screen_spec.class, Screen_ports.Screen_p_Colour).accepts(Camera_spec.class, Camera_ports.Camera_p_Camera_to_Camera);
				port(Camera_spec.class, Camera_ports.Camera_p_Camera_to_Camera).requires(Screen_spec.class, Screen_ports.Screen_p_Colour);
				port(Camera_spec.class, Camera_ports.Camera_p_Camera_to_Camera).accepts(Screen_spec.class, Screen_ports.Screen_p_Colour);



				port(Camera_spec.class, Camera_ports.Camera_p_Camera_reset).requires(Screen_spec.class, Screen_ports.Screen_p_not_Colour_implies_Colour_Camera);
				port(Camera_spec.class, Camera_ports.Camera_p_Camera_reset).accepts(Screen_spec.class, Screen_ports.Screen_p_not_Colour_implies_Colour_Camera);
				port(Screen_spec.class, Screen_ports.Screen_p_not_Colour_implies_Colour_Camera).requires(Camera_spec.class, Camera_ports.Camera_p_Camera_reset);
				port(Screen_spec.class, Screen_ports.Screen_p_not_Colour_implies_Colour_Camera).accepts(Camera_spec.class, Camera_ports.Camera_p_Camera_reset);
			}
		}.build();
	}

}