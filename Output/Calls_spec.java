package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Calls_ports.Calls_p_SCalls , type = PortType.spontaneous ),
	@Port(name = Calls_ports.Calls_p_SCalls_reset , type = PortType.spontaneous ),
	@Port(name = Calls_ports.Calls_p_Calls , type = PortType.enforceable ),
	@Port(name = Calls_ports.Calls_p_Calls_reset , type = PortType.enforceable )
})
@ComponentType(initial = Calls_states.Calls_s_init , name ="Calls")
public class Calls_spec{

	protected Boolean start_constrain_Calls;
	protected Boolean reset_constrain_Calls;
	public Calls_spec(){
		start_constrain_Calls = false;
		reset_constrain_Calls = false;
	}


@Guard(name = "Constrain_start_constrain_Calls")
public Boolean check_start_constrain_Calls(){
	 return start_constrain_Calls;
}



@Guard(name = "Constrain_reset_constrain_Calls")
public Boolean check_reset_constrain_Calls(){
	 return reset_constrain_Calls;
}





@Transition(name =Calls_ports.Calls_p_SCalls, source = Calls_states.Calls_s_init, target = Calls_states.Calls_s_SCalls)
	public void trans_init_to_SCalls_SCalls(){
		System.out.println( "component name: Calls from :init---> SCalls  Spontaneous");
	}



@Transition(name =Calls_ports.Calls_p_SCalls_reset, source = Calls_states.Calls_s_Calls, target = Calls_states.Calls_s_SRCalls)
	public void trans_Calls_to_SRCalls_SCalls_reset(){
		System.out.println( "component name: Calls from :Calls---> SRCalls  Spontaneous");
	}



@Transition(name ="", source = Calls_states.Calls_s_SCalls, target = Calls_states.Calls_s_Calls, guard = "!Constrain_start_constrain_Calls")
	public void internal_trans_SCalls_to_Calls_internal_Calls(){
		System.out.println( "component name: Calls from :SCalls---> Calls  Internal");
	}



@Transition(name ="", source = Calls_states.Calls_s_SRCalls, target = Calls_states.Calls_s_init, guard = "!Constrain_reset_constrain_Calls")
	public void internal_trans_SRCalls_to_init_internal_Calls_reset(){
		System.out.println( "component name: Calls from :SRCalls---> init  Internal");
	}



@Transition(name =Calls_ports.Calls_p_Calls, source = Calls_states.Calls_s_SCalls, target = Calls_states.Calls_s_Calls, guard = "Constrain_start_constrain_Calls")
	public void trans_SCalls_to_Calls_Calls(){
		System.out.println( "component name: Calls from :SCalls---> Calls  Enforceable");
	}



@Transition(name =Calls_ports.Calls_p_Calls_reset, source = Calls_states.Calls_s_SRCalls, target = Calls_states.Calls_s_init, guard = "Constrain_reset_constrain_Calls")
	public void trans_SRCalls_to_init_Calls_reset(){
		System.out.println( "component name: Calls from :SRCalls---> init  Enforceable");
	}



}
