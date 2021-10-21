package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Screen_ports.Screen_p_SBasic , type = PortType.spontaneous ),
	@Port(name = Screen_ports.Screen_p_SColour , type = PortType.spontaneous ),
	@Port(name = Screen_ports.Screen_p_SHigh_Resolution , type = PortType.spontaneous ),
	@Port(name = Screen_ports.Screen_p_SBasic_reset , type = PortType.spontaneous ),
	@Port(name = Screen_ports.Screen_p_SColour_reset , type = PortType.spontaneous ),
	@Port(name = Screen_ports.Screen_p_SHigh_Resolution_reset , type = PortType.spontaneous ),
	@Port(name = Screen_ports.Screen_p_Basic , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_Colour , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_High_Resolution , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_Basic_reset , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_Colour_reset , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_High_Resolution_reset , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_not_Colour_implies_Colour_Camera , type = PortType.enforceable )
})
@ComponentType(initial = Screen_states.Screen_s_init , name ="Screen")
public class Screen_spec{

	protected Boolean start_constrain_Basic;
	protected Boolean start_constrain_High_Resolution;
	protected Boolean start_constrain_Colour;
	protected Boolean reset_constrain_Basic;
	protected Boolean reset_constrain_High_Resolution;
	protected Boolean reset_constrain_Colour;
	public Screen_spec(){
		start_constrain_Basic = false;
		start_constrain_High_Resolution = false;
		start_constrain_Colour = true;
		reset_constrain_Basic = false;
		reset_constrain_High_Resolution = false;
		reset_constrain_Colour = false;
	}


@Guard(name = "Constrain_start_constrain_Basic")
public Boolean check_start_constrain_Basic(){
	 return start_constrain_Basic;
}



@Guard(name = "Constrain_start_constrain_High_Resolution")
public Boolean check_start_constrain_High_Resolution(){
	 return start_constrain_High_Resolution;
}



@Guard(name = "Constrain_start_constrain_Colour")
public Boolean check_start_constrain_Colour(){
	 return start_constrain_Colour;
}



@Guard(name = "Constrain_reset_constrain_Basic")
public Boolean check_reset_constrain_Basic(){
	 return reset_constrain_Basic;
}



@Guard(name = "Constrain_reset_constrain_High_Resolution")
public Boolean check_reset_constrain_High_Resolution(){
	 return reset_constrain_High_Resolution;
}



@Guard(name = "Constrain_reset_constrain_Colour")
public Boolean check_reset_constrain_Colour(){
	 return reset_constrain_Colour;
}





@Transition(name =Screen_ports.Screen_p_SBasic, source = Screen_states.Screen_s_init, target = Screen_states.Screen_s_SBasic)
	public void trans_init_to_SBasic_SBasic(){
		System.out.println( "component name: Screen from :init---> SBasic  Spontaneous");
	}



@Transition(name =Screen_ports.Screen_p_SColour, source = Screen_states.Screen_s_init, target = Screen_states.Screen_s_SColour)
	public void trans_init_to_SColour_SColour(){
		System.out.println( "component name: Screen from :init---> SColour  Spontaneous");
	}



@Transition(name =Screen_ports.Screen_p_SHigh_Resolution, source = Screen_states.Screen_s_init, target = Screen_states.Screen_s_SHigh_Resolution)
	public void trans_init_to_SHigh_Resolution_SHigh_Resolution(){
		System.out.println( "component name: Screen from :init---> SHigh_Resolution  Spontaneous");
	}



@Transition(name =Screen_ports.Screen_p_SBasic_reset, source = Screen_states.Screen_s_Basic, target = Screen_states.Screen_s_SRBasic)
	public void trans_Basic_to_SRBasic_SBasic_reset(){
		System.out.println( "component name: Screen from :Basic---> SRBasic  Spontaneous");
	}



@Transition(name =Screen_ports.Screen_p_SColour_reset, source = Screen_states.Screen_s_Colour, target = Screen_states.Screen_s_SRColour)
	public void trans_Colour_to_SRColour_SColour_reset(){
		System.out.println( "component name: Screen from :Colour---> SRColour  Spontaneous");
	}



@Transition(name =Screen_ports.Screen_p_SHigh_Resolution_reset, source = Screen_states.Screen_s_High_Resolution, target = Screen_states.Screen_s_SRHigh_Resolution)
	public void trans_High_Resolution_to_SRHigh_Resolution_SHigh_Resolution_reset(){
		System.out.println( "component name: Screen from :High_Resolution---> SRHigh_Resolution  Spontaneous");
	}



@Transition(name ="", source = Screen_states.Screen_s_SBasic, target = Screen_states.Screen_s_Basic, guard = "!Constrain_start_constrain_Basic")
	public void internal_trans_SBasic_to_Basic_internal_Basic(){
		System.out.println( "component name: Screen from :SBasic---> Basic  Internal");
	}



@Transition(name ="", source = Screen_states.Screen_s_SColour, target = Screen_states.Screen_s_Colour, guard = "!Constrain_start_constrain_Colour")
	public void internal_trans_SColour_to_Colour_internal_Colour(){
		System.out.println( "component name: Screen from :SColour---> Colour  Internal");
	}



@Transition(name ="", source = Screen_states.Screen_s_SHigh_Resolution, target = Screen_states.Screen_s_High_Resolution, guard = "!Constrain_start_constrain_High_Resolution")
	public void internal_trans_SHigh_Resolution_to_High_Resolution_internal_High_Resolution(){
		System.out.println( "component name: Screen from :SHigh_Resolution---> High_Resolution  Internal");
	}



@Transition(name ="", source = Screen_states.Screen_s_SRBasic, target = Screen_states.Screen_s_init, guard = "!Constrain_reset_constrain_Basic")
	public void internal_trans_SRBasic_to_init_internal_Basic_reset(){
		System.out.println( "component name: Screen from :SRBasic---> init  Internal");
	}



@Transition(name ="", source = Screen_states.Screen_s_SRColour, target = Screen_states.Screen_s_init, guard = "!Constrain_reset_constrain_Colour")
	public void internal_trans_SRColour_to_init_internal_Colour_reset(){
		System.out.println( "component name: Screen from :SRColour---> init  Internal");
	}



@Transition(name ="", source = Screen_states.Screen_s_SRHigh_Resolution, target = Screen_states.Screen_s_init, guard = "!Constrain_reset_constrain_High_Resolution")
	public void internal_trans_SRHigh_Resolution_to_init_internal_High_Resolution_reset(){
		System.out.println( "component name: Screen from :SRHigh_Resolution---> init  Internal");
	}



@Transition(name =Screen_ports.Screen_p_Basic, source = Screen_states.Screen_s_SBasic, target = Screen_states.Screen_s_Basic, guard = "Constrain_start_constrain_Basic")
	public void trans_SBasic_to_Basic_Basic(){
		System.out.println( "component name: Screen from :SBasic---> Basic  Enforceable");
	}



@Transition(name =Screen_ports.Screen_p_Colour, source = Screen_states.Screen_s_SColour, target = Screen_states.Screen_s_Colour, guard = "Constrain_start_constrain_Colour")
	public void trans_SColour_to_Colour_Colour(){
		System.out.println( "component name: Screen from :SColour---> Colour  Enforceable");
	}



@Transition(name =Screen_ports.Screen_p_High_Resolution, source = Screen_states.Screen_s_SHigh_Resolution, target = Screen_states.Screen_s_High_Resolution, guard = "Constrain_start_constrain_High_Resolution")
	public void trans_SHigh_Resolution_to_High_Resolution_High_Resolution(){
		System.out.println( "component name: Screen from :SHigh_Resolution---> High_Resolution  Enforceable");
	}



@Transition(name =Screen_ports.Screen_p_Basic_reset, source = Screen_states.Screen_s_SRBasic, target = Screen_states.Screen_s_init, guard = "Constrain_reset_constrain_Basic")
	public void trans_SRBasic_to_init_Basic_reset(){
		System.out.println( "component name: Screen from :SRBasic---> init  Enforceable");
	}



@Transition(name =Screen_ports.Screen_p_Colour_reset, source = Screen_states.Screen_s_SRColour, target = Screen_states.Screen_s_init, guard = "Constrain_reset_constrain_Colour")
	public void trans_SRColour_to_init_Colour_reset(){
		System.out.println( "component name: Screen from :SRColour---> init  Enforceable");
	}



@Transition(name =Screen_ports.Screen_p_High_Resolution_reset, source = Screen_states.Screen_s_SRHigh_Resolution, target = Screen_states.Screen_s_init, guard = "Constrain_reset_constrain_High_Resolution")
	public void trans_SRHigh_Resolution_to_init_High_Resolution_reset(){
		System.out.println( "component name: Screen from :SRHigh_Resolution---> init  Enforceable");
	}



@Transition(name =Screen_ports.Screen_p_not_Colour_implies_Colour_Camera, source = Screen_states.Screen_s_init, target = Screen_states.Screen_s_init)
	public void trans_init_to_init_not_Colour_implies_Colour_Camera(){
		System.out.println( "component name: Screen from :init---> init  Enforceable");
	}



@Transition(name =Screen_ports.Screen_p_not_Colour_implies_Colour_Camera, source = Screen_states.Screen_s_High_Resolution, target = Screen_states.Screen_s_High_Resolution)
	public void trans_High_Resolution_to_High_Resolution_not_Colour_implies_Colour_Camera(){
		System.out.println( "component name: Screen from :High_Resolution---> High_Resolution  Enforceable");
	}



@Transition(name =Screen_ports.Screen_p_not_Colour_implies_Colour_Camera, source = Screen_states.Screen_s_Basic, target = Screen_states.Screen_s_Basic)
	public void trans_Basic_to_Basic_not_Colour_implies_Colour_Camera(){
		System.out.println( "component name: Screen from :Basic---> Basic  Enforceable");
	}



}
