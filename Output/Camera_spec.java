package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Camera_ports.Camera_p_SCamera , type = PortType.spontaneous ),
	@Port(name = Camera_ports.Camera_p_SCamera_reset , type = PortType.spontaneous ),
	@Port(name = Camera_ports.Camera_p_Camera , type = PortType.enforceable ),
	@Port(name = Camera_ports.Camera_p_Camera_reset , type = PortType.enforceable ),
	@Port(name = Camera_ports.Camera_p_Camera_to_Camera , type = PortType.enforceable )
})
@ComponentType(initial = Camera_states.Camera_s_init , name ="Camera")
public class Camera_spec{

	protected Boolean start_constrain_Camera;
	protected Boolean reset_constrain_Camera;
	public Camera_spec(){
		start_constrain_Camera = false;
		reset_constrain_Camera = true;
	}


@Guard(name = "Constrain_start_constrain_Camera")
public Boolean check_start_constrain_Camera(){
	 return start_constrain_Camera;
}



@Guard(name = "Constrain_reset_constrain_Camera")
public Boolean check_reset_constrain_Camera(){
	 return reset_constrain_Camera;
}





@Transition(name =Camera_ports.Camera_p_SCamera, source = Camera_states.Camera_s_init, target = Camera_states.Camera_s_SCamera)
	public void trans_init_to_SCamera_SCamera(){
		System.out.println( "component name: Camera from :init---> SCamera  Spontaneous");
	}



@Transition(name =Camera_ports.Camera_p_SCamera_reset, source = Camera_states.Camera_s_Camera, target = Camera_states.Camera_s_SRCamera)
	public void trans_Camera_to_SRCamera_SCamera_reset(){
		System.out.println( "component name: Camera from :Camera---> SRCamera  Spontaneous");
	}



@Transition(name ="", source = Camera_states.Camera_s_SCamera, target = Camera_states.Camera_s_Camera, guard = "!Constrain_start_constrain_Camera")
	public void internal_trans_SCamera_to_Camera_internal_Camera(){
		System.out.println( "component name: Camera from :SCamera---> Camera  Internal");
	}



@Transition(name ="", source = Camera_states.Camera_s_SRCamera, target = Camera_states.Camera_s_init, guard = "!Constrain_reset_constrain_Camera")
	public void internal_trans_SRCamera_to_init_internal_Camera_reset(){
		System.out.println( "component name: Camera from :SRCamera---> init  Internal");
	}



@Transition(name =Camera_ports.Camera_p_Camera, source = Camera_states.Camera_s_SCamera, target = Camera_states.Camera_s_Camera, guard = "Constrain_start_constrain_Camera")
	public void trans_SCamera_to_Camera_Camera(){
		System.out.println( "component name: Camera from :SCamera---> Camera  Enforceable");
	}



@Transition(name =Camera_ports.Camera_p_Camera_reset, source = Camera_states.Camera_s_SRCamera, target = Camera_states.Camera_s_init, guard = "Constrain_reset_constrain_Camera")
	public void trans_SRCamera_to_init_Camera_reset(){
		System.out.println( "component name: Camera from :SRCamera---> init  Enforceable");
	}



@Transition(name =Camera_ports.Camera_p_Camera_to_Camera, source = Camera_states.Camera_s_Camera, target = Camera_states.Camera_s_Camera)
	public void trans_Camera_to_Camera_Camera_to_Camera(){
		System.out.println( "component name: Camera from :Camera---> Camera  Enforceable");
	}



}
