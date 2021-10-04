package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_SCalls , type = PortType.spontaneous ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_SMedia , type = PortType.spontaneous ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_SScreen , type = PortType.spontaneous ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_SGPS , type = PortType.spontaneous ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_SCalls_reset , type = PortType.spontaneous ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_SMedia_reset , type = PortType.spontaneous ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_SScreen_reset , type = PortType.spontaneous ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_SGPS_reset , type = PortType.spontaneous ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_Calls , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_Media , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_Screen , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_GPS , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_Calls_reset , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_Media_reset , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_Screen_reset , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_GPS_reset , type = PortType.enforceable )
})
@ComponentType(initial = Mobile_Phone_states.Mobile_Phone_s_init , name ="Mobile_Phone")
public class Mobile_Phone_spec{

	protected Boolean start_constrain_Media;
	protected Boolean start_constrain_Screen;
	protected Boolean start_constrain_GPS;
	protected Boolean start_constrain_Calls;
	protected Boolean reset_constrain_Media;
	protected Boolean reset_constrain_Screen;
	protected Boolean reset_constrain_GPS;
	protected Boolean reset_constrain_Calls;
	public Mobile_Phone_spec(){
		start_constrain_Media = false;
		start_constrain_Screen = false;
		start_constrain_GPS = false;
		start_constrain_Calls = false;
		reset_constrain_Media = false;
		reset_constrain_Screen = false;
		reset_constrain_GPS = false;
		reset_constrain_Calls = false;
	}


@Guard(name = "Constrain_start_constrain_Media")
public Boolean check_start_constrain_Media(){
	 return start_constrain_Media;
}



@Guard(name = "Constrain_start_constrain_Screen")
public Boolean check_start_constrain_Screen(){
	 return start_constrain_Screen;
}



@Guard(name = "Constrain_start_constrain_GPS")
public Boolean check_start_constrain_GPS(){
	 return start_constrain_GPS;
}



@Guard(name = "Constrain_start_constrain_Calls")
public Boolean check_start_constrain_Calls(){
	 return start_constrain_Calls;
}



@Guard(name = "Constrain_reset_constrain_Media")
public Boolean check_reset_constrain_Media(){
	 return reset_constrain_Media;
}



@Guard(name = "Constrain_reset_constrain_Screen")
public Boolean check_reset_constrain_Screen(){
	 return reset_constrain_Screen;
}



@Guard(name = "Constrain_reset_constrain_GPS")
public Boolean check_reset_constrain_GPS(){
	 return reset_constrain_GPS;
}



@Guard(name = "Constrain_reset_constrain_Calls")
public Boolean check_reset_constrain_Calls(){
	 return reset_constrain_Calls;
}





@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_SCalls, source = Mobile_Phone_states.Mobile_Phone_s_init, target = Mobile_Phone_states.Mobile_Phone_s_SCalls)
	public void trans_init_to_SCalls_SCalls(){
		System.out.println( "component name: Mobile_Phone from :init---> SCalls  Spontaneous");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_SMedia, source = Mobile_Phone_states.Mobile_Phone_s_init, target = Mobile_Phone_states.Mobile_Phone_s_SMedia)
	public void trans_init_to_SMedia_SMedia(){
		System.out.println( "component name: Mobile_Phone from :init---> SMedia  Spontaneous");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_SScreen, source = Mobile_Phone_states.Mobile_Phone_s_init, target = Mobile_Phone_states.Mobile_Phone_s_SScreen)
	public void trans_init_to_SScreen_SScreen(){
		System.out.println( "component name: Mobile_Phone from :init---> SScreen  Spontaneous");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_SGPS, source = Mobile_Phone_states.Mobile_Phone_s_init, target = Mobile_Phone_states.Mobile_Phone_s_SGPS)
	public void trans_init_to_SGPS_SGPS(){
		System.out.println( "component name: Mobile_Phone from :init---> SGPS  Spontaneous");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_SCalls_reset, source = Mobile_Phone_states.Mobile_Phone_s_Calls, target = Mobile_Phone_states.Mobile_Phone_s_SRCalls)
	public void trans_Calls_to_SRCalls_SCalls_reset(){
		System.out.println( "component name: Mobile_Phone from :Calls---> SRCalls  Spontaneous");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_SMedia_reset, source = Mobile_Phone_states.Mobile_Phone_s_Media, target = Mobile_Phone_states.Mobile_Phone_s_SRMedia)
	public void trans_Media_to_SRMedia_SMedia_reset(){
		System.out.println( "component name: Mobile_Phone from :Media---> SRMedia  Spontaneous");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_SScreen_reset, source = Mobile_Phone_states.Mobile_Phone_s_Screen, target = Mobile_Phone_states.Mobile_Phone_s_Calls)
	public void trans_Screen_to_Calls_SScreen_reset(){
		System.out.println( "component name: Mobile_Phone from :Screen---> Calls  Spontaneous");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_SGPS_reset, source = Mobile_Phone_states.Mobile_Phone_s_GPS, target = Mobile_Phone_states.Mobile_Phone_s_Media)
	public void trans_GPS_to_Media_SGPS_reset(){
		System.out.println( "component name: Mobile_Phone from :GPS---> Media  Spontaneous");
	}



@Transition(name ="", source = Mobile_Phone_states.Mobile_Phone_s_SCalls, target = Mobile_Phone_states.Mobile_Phone_s_Calls, guard = "!Constrain_start_constrain_Calls")
	public void internal_trans_SCalls_to_Calls_internal_Calls(){
		System.out.println( "component name: Mobile_Phone from :SCalls---> Calls  Internal");
	}



@Transition(name ="", source = Mobile_Phone_states.Mobile_Phone_s_SMedia, target = Mobile_Phone_states.Mobile_Phone_s_Media, guard = "!Constrain_start_constrain_Media")
	public void internal_trans_SMedia_to_Media_internal_Media(){
		System.out.println( "component name: Mobile_Phone from :SMedia---> Media  Internal");
	}



@Transition(name ="", source = Mobile_Phone_states.Mobile_Phone_s_SScreen, target = Mobile_Phone_states.Mobile_Phone_s_Screen, guard = "!Constrain_start_constrain_Screen")
	public void internal_trans_SScreen_to_Screen_internal_Screen(){
		System.out.println( "component name: Mobile_Phone from :SScreen---> Screen  Internal");
	}



@Transition(name ="", source = Mobile_Phone_states.Mobile_Phone_s_SGPS, target = Mobile_Phone_states.Mobile_Phone_s_GPS, guard = "!Constrain_start_constrain_GPS")
	public void internal_trans_SGPS_to_GPS_internal_GPS(){
		System.out.println( "component name: Mobile_Phone from :SGPS---> GPS  Internal");
	}



@Transition(name ="", source = Mobile_Phone_states.Mobile_Phone_s_SRCalls, target = Mobile_Phone_states.Mobile_Phone_s_init, guard = "!Constrain_reset_constrain_Calls")
	public void internal_trans_SRCalls_to_init_internal_Calls_reset(){
		System.out.println( "component name: Mobile_Phone from :SRCalls---> init  Internal");
	}



@Transition(name ="", source = Mobile_Phone_states.Mobile_Phone_s_SRMedia, target = Mobile_Phone_states.Mobile_Phone_s_init, guard = "!Constrain_reset_constrain_Media")
	public void internal_trans_SRMedia_to_init_internal_Media_reset(){
		System.out.println( "component name: Mobile_Phone from :SRMedia---> init  Internal");
	}



@Transition(name ="", source = Mobile_Phone_states.Mobile_Phone_s_Calls, target = Mobile_Phone_states.Mobile_Phone_s_init, guard = "!Constrain_reset_constrain_lls")
	public void internal_trans_Calls_to_init_internal_Screen_reset(){
		System.out.println( "component name: Mobile_Phone from :Calls---> init  Internal");
	}



@Transition(name ="", source = Mobile_Phone_states.Mobile_Phone_s_Media, target = Mobile_Phone_states.Mobile_Phone_s_init, guard = "!Constrain_reset_constrain_dia")
	public void internal_trans_Media_to_init_internal_GPS_reset(){
		System.out.println( "component name: Mobile_Phone from :Media---> init  Internal");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_Calls, source = Mobile_Phone_states.Mobile_Phone_s_SCalls, target = Mobile_Phone_states.Mobile_Phone_s_Calls, guard = "Constrain_start_constrain_Calls")
	public void trans_SCalls_to_Calls_Calls(){
		System.out.println( "component name: Mobile_Phone from :SCalls---> Calls  Enforceable");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_Media, source = Mobile_Phone_states.Mobile_Phone_s_SMedia, target = Mobile_Phone_states.Mobile_Phone_s_Media, guard = "Constrain_start_constrain_Media")
	public void trans_SMedia_to_Media_Media(){
		System.out.println( "component name: Mobile_Phone from :SMedia---> Media  Enforceable");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_Screen, source = Mobile_Phone_states.Mobile_Phone_s_SScreen, target = Mobile_Phone_states.Mobile_Phone_s_Screen, guard = "Constrain_start_constrain_Screen")
	public void trans_SScreen_to_Screen_Screen(){
		System.out.println( "component name: Mobile_Phone from :SScreen---> Screen  Enforceable");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_GPS, source = Mobile_Phone_states.Mobile_Phone_s_SGPS, target = Mobile_Phone_states.Mobile_Phone_s_GPS, guard = "Constrain_start_constrain_GPS")
	public void trans_SGPS_to_GPS_GPS(){
		System.out.println( "component name: Mobile_Phone from :SGPS---> GPS  Enforceable");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_Calls_reset, source = Mobile_Phone_states.Mobile_Phone_s_SRCalls, target = Mobile_Phone_states.Mobile_Phone_s_init, guard = "Constrain_reset_constrain_Calls")
	public void trans_SRCalls_to_init_Calls_reset(){
		System.out.println( "component name: Mobile_Phone from :SRCalls---> init  Enforceable");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_Media_reset, source = Mobile_Phone_states.Mobile_Phone_s_SRMedia, target = Mobile_Phone_states.Mobile_Phone_s_init, guard = "Constrain_reset_constrain_Media")
	public void trans_SRMedia_to_init_Media_reset(){
		System.out.println( "component name: Mobile_Phone from :SRMedia---> init  Enforceable");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_Screen_reset, source = Mobile_Phone_states.Mobile_Phone_s_Calls, target = Mobile_Phone_states.Mobile_Phone_s_init, guard = "Constrain_reset_constrain_lls")
	public void trans_Calls_to_init_Screen_reset(){
		System.out.println( "component name: Mobile_Phone from :Calls---> init  Enforceable");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_GPS_reset, source = Mobile_Phone_states.Mobile_Phone_s_Media, target = Mobile_Phone_states.Mobile_Phone_s_init, guard = "Constrain_reset_constrain_dia")
	public void trans_Media_to_init_GPS_reset(){
		System.out.println( "component name: Mobile_Phone from :Media---> init  Enforceable");
	}



}
