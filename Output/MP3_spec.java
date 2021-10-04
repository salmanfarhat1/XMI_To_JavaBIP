package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = MP3_ports.MP3_p_SMP3 , type = PortType.spontaneous ),
	@Port(name = MP3_ports.MP3_p_SMP3_reset , type = PortType.spontaneous ),
	@Port(name = MP3_ports.MP3_p_MP3 , type = PortType.enforceable ),
	@Port(name = MP3_ports.MP3_p_MP3_reset , type = PortType.enforceable )
})
@ComponentType(initial = MP3_states.MP3_s_init , name ="MP3")
public class MP3_spec{

	protected Boolean start_constrain_MP3;
	protected Boolean reset_constrain_MP3;
	public MP3_spec(){
		start_constrain_MP3 = false;
		reset_constrain_MP3 = false;
	}


@Guard(name = "Constrain_start_constrain_MP3")
public Boolean check_start_constrain_MP3(){
	 return start_constrain_MP3;
}



@Guard(name = "Constrain_reset_constrain_MP3")
public Boolean check_reset_constrain_MP3(){
	 return reset_constrain_MP3;
}





@Transition(name =MP3_ports.MP3_p_SMP3, source = MP3_states.MP3_s_init, target = MP3_states.MP3_s_SMP3)
	public void trans_init_to_SMP3_SMP3(){
		System.out.println( "component name: MP3 from :init---> SMP3  Spontaneous");
	}



@Transition(name =MP3_ports.MP3_p_SMP3_reset, source = MP3_states.MP3_s_MP3, target = MP3_states.MP3_s_SRMP3)
	public void trans_MP3_to_SRMP3_SMP3_reset(){
		System.out.println( "component name: MP3 from :MP3---> SRMP3  Spontaneous");
	}



@Transition(name ="", source = MP3_states.MP3_s_SMP3, target = MP3_states.MP3_s_MP3, guard = "!Constrain_start_constrain_MP3")
	public void internal_trans_SMP3_to_MP3_internal_MP3(){
		System.out.println( "component name: MP3 from :SMP3---> MP3  Internal");
	}



@Transition(name ="", source = MP3_states.MP3_s_SRMP3, target = MP3_states.MP3_s_init, guard = "!Constrain_reset_constrain_MP3")
	public void internal_trans_SRMP3_to_init_internal_MP3_reset(){
		System.out.println( "component name: MP3 from :SRMP3---> init  Internal");
	}



@Transition(name =MP3_ports.MP3_p_MP3, source = MP3_states.MP3_s_SMP3, target = MP3_states.MP3_s_MP3, guard = "Constrain_start_constrain_MP3")
	public void trans_SMP3_to_MP3_MP3(){
		System.out.println( "component name: MP3 from :SMP3---> MP3  Enforceable");
	}



@Transition(name =MP3_ports.MP3_p_MP3_reset, source = MP3_states.MP3_s_SRMP3, target = MP3_states.MP3_s_init, guard = "Constrain_reset_constrain_MP3")
	public void trans_SRMP3_to_init_MP3_reset(){
		System.out.println( "component name: MP3 from :SRMP3---> init  Enforceable");
	}



}
