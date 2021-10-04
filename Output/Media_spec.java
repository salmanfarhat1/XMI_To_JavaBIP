package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Media_ports.Media_p_SCamera , type = PortType.spontaneous ),
	@Port(name = Media_ports.Media_p_SMP3 , type = PortType.spontaneous ),
	@Port(name = Media_ports.Media_p_SCamera_reset , type = PortType.spontaneous ),
	@Port(name = Media_ports.Media_p_SMP3_reset , type = PortType.spontaneous ),
	@Port(name = Media_ports.Media_p_Camera , type = PortType.enforceable ),
	@Port(name = Media_ports.Media_p_MP3 , type = PortType.enforceable ),
	@Port(name = Media_ports.Media_p_Camera_reset , type = PortType.enforceable ),
	@Port(name = Media_ports.Media_p_MP3_reset , type = PortType.enforceable )
})
@ComponentType(initial = Media_states.Media_s_init , name ="Media")
public class Media_spec{

	protected Boolean start_constrain_MP3;
	protected Boolean start_constrain_Camera;
	protected Boolean reset_constrain_MP3;
	protected Boolean reset_constrain_Camera;
	public Media_spec(){
		start_constrain_MP3 = false;
		start_constrain_Camera = false;
		reset_constrain_MP3 = false;
		reset_constrain_Camera = false;
	}


@Guard(name = "Constrain_start_constrain_MP3")
public Boolean check_start_constrain_MP3(){
	 return start_constrain_MP3;
}



@Guard(name = "Constrain_start_constrain_Camera")
public Boolean check_start_constrain_Camera(){
	 return start_constrain_Camera;
}



@Guard(name = "Constrain_reset_constrain_MP3")
public Boolean check_reset_constrain_MP3(){
	 return reset_constrain_MP3;
}



@Guard(name = "Constrain_reset_constrain_Camera")
public Boolean check_reset_constrain_Camera(){
	 return reset_constrain_Camera;
}





@Transition(name =Media_ports.Media_p_SCamera, source = Media_states.Media_s_init, target = Media_states.Media_s_SCamera)
	public void trans_init_to_SCamera_SCamera(){
		System.out.println( "component name: Media from :init---> SCamera  Spontaneous");
	}



@Transition(name =Media_ports.Media_p_SMP3, source = Media_states.Media_s_init, target = Media_states.Media_s_SMP3)
	public void trans_init_to_SMP3_SMP3(){
		System.out.println( "component name: Media from :init---> SMP3  Spontaneous");
	}



@Transition(name =Media_ports.Media_p_SCamera_reset, source = Media_states.Media_s_Camera, target = Media_states.Media_s_SRCamera)
	public void trans_Camera_to_SRCamera_SCamera_reset(){
		System.out.println( "component name: Media from :Camera---> SRCamera  Spontaneous");
	}



@Transition(name =Media_ports.Media_p_SMP3_reset, source = Media_states.Media_s_MP3, target = Media_states.Media_s_SRMP3)
	public void trans_MP3_to_SRMP3_SMP3_reset(){
		System.out.println( "component name: Media from :MP3---> SRMP3  Spontaneous");
	}



@Transition(name ="", source = Media_states.Media_s_SCamera, target = Media_states.Media_s_Camera, guard = "!Constrain_start_constrain_Camera")
	public void internal_trans_SCamera_to_Camera_internal_Camera(){
		System.out.println( "component name: Media from :SCamera---> Camera  Internal");
	}



@Transition(name ="", source = Media_states.Media_s_SMP3, target = Media_states.Media_s_MP3, guard = "!Constrain_start_constrain_MP3")
	public void internal_trans_SMP3_to_MP3_internal_MP3(){
		System.out.println( "component name: Media from :SMP3---> MP3  Internal");
	}



@Transition(name ="", source = Media_states.Media_s_SRCamera, target = Media_states.Media_s_init, guard = "!Constrain_reset_constrain_Camera")
	public void internal_trans_SRCamera_to_init_internal_Camera_reset(){
		System.out.println( "component name: Media from :SRCamera---> init  Internal");
	}



@Transition(name ="", source = Media_states.Media_s_SRMP3, target = Media_states.Media_s_init, guard = "!Constrain_reset_constrain_MP3")
	public void internal_trans_SRMP3_to_init_internal_MP3_reset(){
		System.out.println( "component name: Media from :SRMP3---> init  Internal");
	}



@Transition(name =Media_ports.Media_p_Camera, source = Media_states.Media_s_SCamera, target = Media_states.Media_s_Camera, guard = "Constrain_start_constrain_Camera")
	public void trans_SCamera_to_Camera_Camera(){
		System.out.println( "component name: Media from :SCamera---> Camera  Enforceable");
	}



@Transition(name =Media_ports.Media_p_MP3, source = Media_states.Media_s_SMP3, target = Media_states.Media_s_MP3, guard = "Constrain_start_constrain_MP3")
	public void trans_SMP3_to_MP3_MP3(){
		System.out.println( "component name: Media from :SMP3---> MP3  Enforceable");
	}



@Transition(name =Media_ports.Media_p_Camera_reset, source = Media_states.Media_s_SRCamera, target = Media_states.Media_s_init, guard = "Constrain_reset_constrain_Camera")
	public void trans_SRCamera_to_init_Camera_reset(){
		System.out.println( "component name: Media from :SRCamera---> init  Enforceable");
	}



@Transition(name =Media_ports.Media_p_MP3_reset, source = Media_states.Media_s_SRMP3, target = Media_states.Media_s_init, guard = "Constrain_reset_constrain_MP3")
	public void trans_SRMP3_to_init_MP3_reset(){
		System.out.println( "component name: Media from :SRMP3---> init  Enforceable");
	}



}
