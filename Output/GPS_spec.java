package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = GPS_ports.GPS_p_SGPS , type = PortType.spontaneous ),
	@Port(name = GPS_ports.GPS_p_SGPS_reset , type = PortType.spontaneous ),
	@Port(name = GPS_ports.GPS_p_GPS , type = PortType.enforceable ),
	@Port(name = GPS_ports.GPS_p_GPS_reset , type = PortType.enforceable ),
	@Port(name = GPS_ports.GPS_p_not_GPS , type = PortType.enforceable )
})
@ComponentType(initial = GPS_states.GPS_s_init , name ="GPS")
public class GPS_spec{

	protected Boolean start_constrain_GPS;
	protected Boolean reset_constrain_GPS;
	public GPS_spec(){
		start_constrain_GPS = true;
		reset_constrain_GPS = false;
	}


@Guard(name = "Constrain_start_constrain_GPS")
public Boolean check_start_constrain_GPS(){
	 return start_constrain_GPS;
}



@Guard(name = "Constrain_reset_constrain_GPS")
public Boolean check_reset_constrain_GPS(){
	 return reset_constrain_GPS;
}





@Transition(name =GPS_ports.GPS_p_SGPS, source = GPS_states.GPS_s_init, target = GPS_states.GPS_s_SGPS)
	public void trans_init_to_SGPS_SGPS(){
		System.out.println( "component name: GPS from :init---> SGPS  Spontaneous");
	}



@Transition(name =GPS_ports.GPS_p_SGPS_reset, source = GPS_states.GPS_s_GPS, target = GPS_states.GPS_s_SRGPS)
	public void trans_GPS_to_SRGPS_SGPS_reset(){
		System.out.println( "component name: GPS from :GPS---> SRGPS  Spontaneous");
	}



@Transition(name ="", source = GPS_states.GPS_s_SGPS, target = GPS_states.GPS_s_GPS, guard = "!Constrain_start_constrain_GPS")
	public void internal_trans_SGPS_to_GPS_internal_GPS(){
		System.out.println( "component name: GPS from :SGPS---> GPS  Internal");
	}



@Transition(name ="", source = GPS_states.GPS_s_SRGPS, target = GPS_states.GPS_s_init, guard = "!Constrain_reset_constrain_GPS")
	public void internal_trans_SRGPS_to_init_internal_GPS_reset(){
		System.out.println( "component name: GPS from :SRGPS---> init  Internal");
	}



@Transition(name =GPS_ports.GPS_p_GPS, source = GPS_states.GPS_s_SGPS, target = GPS_states.GPS_s_GPS, guard = "Constrain_start_constrain_GPS")
	public void trans_SGPS_to_GPS_GPS(){
		System.out.println( "component name: GPS from :SGPS---> GPS  Enforceable");
	}



@Transition(name =GPS_ports.GPS_p_GPS_reset, source = GPS_states.GPS_s_SRGPS, target = GPS_states.GPS_s_init, guard = "Constrain_reset_constrain_GPS")
	public void trans_SRGPS_to_init_GPS_reset(){
		System.out.println( "component name: GPS from :SRGPS---> init  Enforceable");
	}



@Transition(name =GPS_ports.GPS_p_not_GPS, source = GPS_states.GPS_s_init, target = GPS_states.GPS_s_init)
	public void trans_init_to_init_not_GPS(){
		System.out.println( "component name: GPS from :init---> init  Enforceable");
	}



}
