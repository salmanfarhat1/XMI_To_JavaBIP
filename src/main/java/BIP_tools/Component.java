package BIP_tools;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator.OfDouble;
//import org.javabip.annotations.Guard;


public class Component {
	
	private String name; 
	private Boolean optional; 
	private Boolean usableComponent; 
	private State initial_state; 
	private ArrayList<State> states;
	private ArrayList<Transition> Transitions;
	private ArrayList<Guard> guards; 

	
	public Component(String name, Boolean optional, Boolean usableComponent,  State initial_state, ArrayList<State> states, ArrayList<Transition> transitions) {
		this.name = name;
		this.optional = optional;
		this.usableComponent = usableComponent;
		this.initial_state = initial_state;
		this.states = states;
		this.Transitions = transitions;
		this.guards = new ArrayList<Guard>();
	}

//	import org.javabip.annotations.ComponentType;
//	import org.javabip.annotations.Data;
//	import org.javabip.annotations.Guard;
//	import org.javabip.annotations.Port;
//	import org.javabip.annotations.Ports;
//	import org.javabip.annotations.Transition;
//	import org.javabip.api.PortType;
	


	public ArrayList<Guard> getGuards() {
		return guards;
	}

	public void setGuards(ArrayList<Guard> guards) {
		this.guards = guards;
	}

	public Boolean getUsableComponent() {
		return usableComponent;
	}



	public void setUsableComponent(Boolean usableComponent) {
		this.usableComponent = usableComponent;
	}



	public Boolean getOptional() {
		return optional;
	}



	public void setOptional(Boolean optional) {
		this.optional = optional;
	}



	public static void main(String[] args) {

		System.out.println(" Run Generate_JavaBIP_Code" );
	}



	public void GenerateCode() {
		CreateCode_for_states();
		CreateCode_for_ports();
		CreateCodeForComponentSpec();
	}
	
//		
//
	public void CreateCode_for_ports() {
		StringBuilder Component_ports = new StringBuilder();
		Component_ports.append("package Output;");
		Component_ports.append(System.lineSeparator());

		Component_ports.append("public class " + this.name+"_ports" + "{");
		Component_ports.append(System.lineSeparator());
		
		ArrayList<String> ports_names_added = new ArrayList<String>();
		for(int i = 0; i < Transitions.size() ; i++) {
			if(!(Transitions.get(i) instanceof Internal)) { // if name contains internal I'm not creating a port for it
				if(!ports_names_added.contains(Transitions.get(i).getTransition_name())) {
					Component_ports.append("\t");
					Component_ports.append("public static final String "+ name+ "_"+ "p"+"_"+ Transitions.get(i).getTransition_name() +" = \"" + name+ "_"+ "p"+"_"+ Transitions.get(i).getTransition_name() +"\";");
					Component_ports.append(System.lineSeparator());
				}	
			}
			ports_names_added.add(Transitions.get(i).getTransition_name());
		}
		Component_ports.append("}");
//		package Exclude_FM_To_JavaBIP;
		
		try (FileOutputStream oS = new FileOutputStream(new File("./Output/"+this.name+"_ports.java"))) {
			oS.write(Component_ports.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Boolean isInternal(String S) {
		return S.contains("internal");
	}
	
	public void CreateCode_for_states() {
		StringBuilder Component_states = new StringBuilder();
		Component_states.append("package Output;");
		Component_states.append(System.lineSeparator());
		Component_states.append("public class " + this.name+"_states" + "{");
		Component_states.append(System.lineSeparator());

		Component_states.append("\t");
		Component_states.append("public static final String "+ name+ "_"+ "s"+"_"+initial_state.getState_name() +" = \"" + name+ "_"+ "s"+"_"+initial_state.getState_name() +"\";");		
		Component_states.append(System.lineSeparator());

		for(int i = 0; i < states.size() ; i++) {
			
				Component_states.append("\t");
				Component_states.append("public static final String "+ name+ "_s_"+ states.get(i).getState_name() +" = \"" + name+ "_"+ "s"+"_"+ states.get(i).getState_name() +"\";" );
				Component_states.append(System.lineSeparator());
		
		
		}
		Component_states.append("}");
//		package Exclude_FM_To_JavaBIP;
		
		try (FileOutputStream oS = new FileOutputStream(new File("./Output/"+this.name+"_states.java"))) {
			oS.write(Component_states.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void CreateCodeForComponentSpec() {

		StringBuilder component_specification = new StringBuilder();

		component_specification.append("package Output;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.annotations.ComponentType;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.annotations.Data;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.annotations.Guard;");
		component_specification.append(System.lineSeparator());

		component_specification.append("import org.javabip.annotations.Port;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.annotations.Ports;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.annotations.Transition;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.api.PortType;");
		component_specification.append(System.lineSeparator());
		
		
//		@Ports({
//			@Port(name = Controller_exclude_ports.controller_exclude_p_basic , type = PortType.enforceable),
//			@Port(name = Controller_exclude_ports.controller_exclude_p_not_basic , type = PortType.enforceable),
//			@Port(name = Controller_exclude_ports.controller_exclude_p_valid_gps , type = PortType.enforceable),
//			@Port(name = Controller_exclude_ports.controller_exclude_p_reset , type = PortType.enforceable)
//
//		})
		
		component_specification.append("@Ports({");
		component_specification.append(System.lineSeparator());
		
		ArrayList<Transition> transition_spon_enfo = new ArrayList<Transition>();
		for(int i = 0; i < Transitions.size();i++) {
			if(!(Transitions.get(i) instanceof Internal)) {
				transition_spon_enfo.add(Transitions.get(i));
			}
		}
		ArrayList<String> ports_names_added = new ArrayList<String>();
		
		for(int i = 0 ; i < transition_spon_enfo.size(); i++) {
			if(i != 0 && !ports_names_added.contains(transition_spon_enfo.get(i).getTransition_name())) {
				component_specification.append(",");
				component_specification.append(System.lineSeparator());
			}
			if(!ports_names_added.contains(transition_spon_enfo.get(i).getTransition_name())) { // to avoid duplicating declarations not_basic only need to be declared once		
				if(transition_spon_enfo.get(i) instanceof Enforceable) {
					component_specification.append("\t@Port(name = " + name+ "_ports."+ name+ "_p_"+ transition_spon_enfo.get(i).getTransition_name() + " , type = PortType.enforceable )");
				}
				else {
					component_specification.append("\t@Port(name = " + name+ "_ports."+ name+ "_p_"+ transition_spon_enfo.get(i).getTransition_name() + " , type = PortType.spontaneous )");

				}
			}
			
			ports_names_added.add(transition_spon_enfo.get(i).getTransition_name());

		}
		component_specification.append(System.lineSeparator());
		component_specification.append("})");
		component_specification.append(System.lineSeparator());

//		@ComponentType(initial = Controller_exclude_states.controller_exclude_s_init , name ="controller_exclude")
//		public class Controller_exclude_spec {
//			
//			public Controller_exclude_spec() {
//				
//			}
		String full_initial_state_name = name +"_s_" +initial_state.getState_name();
		
		component_specification.append("@ComponentType(initial = "+name+"_states." + full_initial_state_name +" , name =\""+name +"\")");
		component_specification.append(System.lineSeparator());
		component_specification.append("public class "+name+"_spec{");
		component_specification.append(System.lineSeparator());	
		
		String start_constrain = "start_constrain_";
		String reset_constrain = "reset_constrain_";
		HashMap<State, Boolean> starting_constaints = new HashMap<State, Boolean>();
		HashMap<State, Boolean> reseting_constaints = new HashMap<State, Boolean>();
		
	
		
		ArrayList<State> filteredStates =  removeIntermediateStates(); 
	
		for(int i =0; i < filteredStates.size(); i++) {
			starting_constaints.put(filteredStates.get(i), false);
			reseting_constaints.put(filteredStates.get(i), false);
		}
		
		
// creating a Hashmap that indicates the boolean constraints that should be setted up			

		for(int  i = 0; i < this.guards.size(); i++) {
			if(guards.get(i) instanceof Guard_Implies){
				if(name.equals( ((Guard_Implies) guards.get(i)).getSrcComponent().getName())){ // if the component is the source one 
					if(guards.get(i).getSrcCmpTransition().getTransition_name().contains("reset")) {
						reseting_constaints.put(findStateByName( ((Guard_Implies) guards.get(i)).getSrcState().getState_name()), true);
					}
					else {
						starting_constaints.put(findStateByName( ((Guard_Implies) guards.get(i)).getSrcState().getState_name()), true);
					}
				}
				else { // if the component is the source one 
					if(guards.get(i).getDstCmpTransition().getTransition_name().contains("reset")) {
						reseting_constaints.put(findStateByName( ((Guard_Implies) guards.get(i)).getDstState().getState_name()), true);

					}
					else {
						starting_constaints.put(findStateByName( ((Guard_Implies) guards.get(i)).getDstState().getState_name()), true);

					}
				}  
			} 
			else if(guards.get(i) instanceof Guard_Exclude) {
				if(name.equals( ((Guard_Exclude) guards.get(i)).getSrcComponent().getName())){ // if the component is the source one 
						starting_constaints.put(findStateByName( ((Guard_Exclude) guards.get(i)).getSrcState().getState_name()), true);	
				}
				else {
						starting_constaints.put(findStateByName( ((Guard_Exclude) guards.get(i)).getDstState().getState_name()), true);	
				} 	
			}			
		}	
		System.out.println("Component name: " + name + " guards for start features: "+starting_constaints);
		System.out.println("Component name: " + name + " guards for reset features: "+reseting_constaints);

// now I have the hashmap ready for use for the creaction of the boolean constraints for the guards
		component_specification.append(System.lineSeparator());
		for(Map.Entry con_element : starting_constaints.entrySet()){ 
			component_specification.append("\tprotected Boolean " + start_constrain + ((State) con_element.getKey()).getState_name() + ";");
			component_specification.append(System.lineSeparator());
		} 
		for(Map.Entry con_element : reseting_constaints.entrySet()){ 
			component_specification.append("\tprotected Boolean " + reset_constrain + ((State) con_element.getKey()).getState_name() + ";");
			component_specification.append(System.lineSeparator());		    
		} 
	
		
		
		component_specification.append("\tpublic "+name+"_spec(){");
		component_specification.append(System.lineSeparator());

		for(Map.Entry con_element : starting_constaints.entrySet()){ 
			component_specification.append("\t\t" + start_constrain + ((State) con_element.getKey()).getState_name() +" = " + con_element.getValue() + ";");
			component_specification.append(System.lineSeparator());
		    
		} 
		for(Map.Entry con_element : reseting_constaints.entrySet()){ 
			component_specification.append("\t\t" + reset_constrain + ((State) con_element.getKey()).getState_name() +" = " + con_element.getValue() + ";");
			component_specification.append(System.lineSeparator());
		} 
//		

		component_specification.append("\t}"+System.lineSeparator()); // end of the constructor 
		

		
// Create guards at the begining to all features 
//@Guard(name = "Constrains_reset_colour")
//public Boolean checkConstraints_reset_colour(){
//	 return resetColourConstrain;
//}
		
		for(Map.Entry con_element : starting_constaints.entrySet()){ 	
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
			component_specification.append("@Guard(name = \"Constrain_"+ start_constrain + ((State) con_element.getKey()).getState_name() +"\")"); 
			component_specification.append(System.lineSeparator());
			component_specification.append("public Boolean check_" + start_constrain + ((State) con_element.getKey()).getState_name() +"(){");
			component_specification.append(System.lineSeparator());
			
			component_specification.append("\t return "+start_constrain + ((State) con_element.getKey()).getState_name()  + ";");
			component_specification.append(System.lineSeparator());
			component_specification.append("}");
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
		} 
		for(Map.Entry con_element : reseting_constaints.entrySet()){ 
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
			component_specification.append("@Guard(name = \"Constrain_"+ reset_constrain + ((State) con_element.getKey()).getState_name() +"\")"); 
			component_specification.append(System.lineSeparator());
			component_specification.append("public Boolean check_" + reset_constrain + ((State) con_element.getKey()).getState_name() +"(){");
			component_specification.append(System.lineSeparator());
			
			component_specification.append("\t return "+reset_constrain + ((State) con_element.getKey()).getState_name()  + ";");
			component_specification.append(System.lineSeparator());
			component_specification.append("}");
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
		} 
		component_specification.append(System.lineSeparator());
		component_specification.append(System.lineSeparator());	
		component_specification.append(System.lineSeparator());	
		component_specification.append(System.lineSeparator());	

//		@Transition(name = GPS_ports.gps_component_p_gps, source = GPS_states.gps_component_s_init, target = GPS_states.gps_component_s_gps)
//		public void callAPI_1() {
//		System.out.println("************");
//			System.out.println("gps_component  init ---> gps");
//			System.out.println("************ In state gps");
//		}
		String pre_port_name = name+"_p_"; 
		String pre_state_name = name+"_s_"; 
		for(int i = 0 ; i < Transitions.size();i++) {
			if(Transitions.get(i) instanceof Internal) { // the internal ones
				component_specification.append("@Transition(name =\"\", source = "+name +"_states."+pre_state_name+Transitions.get(i).getSrc().getState_name() + ", target = "+name +"_states."+pre_state_name+Transitions.get(i).getDst().getState_name());

			}
			else { // spontaneous and the enforceable ones 
				component_specification.append("@Transition(name =" + name +"_ports."+ pre_port_name + Transitions.get(i).getTransition_name() +", source = "+name +"_states."+pre_state_name+Transitions.get(i).getSrc().getState_name() + ", target = "+name +"_states."+pre_state_name+Transitions.get(i).getDst().getState_name());

			}
			// for Implies Guard additions to add the guard condition to the enforceable and internal transition 
			if(!Transitions.get(i).getTransition_name().contains("not") && !Transitions.get(i).getDst().equals(Transitions.get(i).getSrc())) {
				// checking if it is not a looping transition
				if(Transitions.get(i) instanceof Internal || Transitions.get(i) instanceof Enforceable) 
				{ // if we have an internal or an enforceable we will add guard
					if(isTransitionForReset(Transitions.get(i), filteredStates) == true ) { // this is for reset
						// here if we are in reset so we use the source SRBasic for example to init 
						if(Transitions.get(i) instanceof Internal) { // for internal we should have not guard
							component_specification.append( ", guard = \"!Constrain_"+ reset_constrain + Transitions.get(i).getSrc().getState_name().substring(2)+"\"");
						}
						else { // here for enforceable
							component_specification.append( ", guard = \"Constrain_"+ reset_constrain + Transitions.get(i).getSrc().getState_name().substring(2)+"\"");
						}

					}
					else { // here it is for turning on so the destination is the Basic for example (SBasic to Basic)
						if(Transitions.get(i) instanceof Internal) {
							component_specification.append( ", guard = \"!Constrain_"+ start_constrain + Transitions.get(i).getDst().getState_name()+"\"");

						}
						else {
							component_specification.append( ", guard = \"Constrain_"+ start_constrain + Transitions.get(i).getDst().getState_name()+"\"");

						}

					}
				}
			}
			
			
			component_specification.append( ")");
			component_specification.append(System.lineSeparator());	
			
			
			if(Transitions.get(i) instanceof Internal) {
				component_specification.append("\tpublic void internal_trans_"+Transitions.get(i).getSrc().getState_name()+"_to_"+Transitions.get(i).getDst().getState_name()+ "_"+Transitions.get(i).getTransition_name()+"(){");

			}
			else {
				component_specification.append("\tpublic void trans_"+Transitions.get(i).getSrc().getState_name()+"_to_"+Transitions.get(i).getDst().getState_name()+ "_"+Transitions.get(i).getTransition_name()+"(){");
			}	
			
			component_specification.append(System.lineSeparator());	
		
			component_specification.append("\t\tSystem.out.println( \"component name: "+name +" from :" +   Transitions.get(i).getSrc().getState_name()+ "---> " + Transitions.get(i).getDst().getState_name()+ " " +transType(Transitions.get(i))+ "\");");
			component_specification.append(System.lineSeparator());

			component_specification.append("\t}");	
			
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
			
//			if(Transitions.get(i).getTransition_name().contains("not") && Transitions.get(i).getDst().equals(Transitions.get(i).getSrc())) {
//				for(int k =0; k < filteredStates.size(); k++) {
//					
//				}
//			}
		}
		
		component_specification.append("}");
		component_specification.append(System.lineSeparator());	
		try (FileOutputStream oS = new FileOutputStream(new File("./Output/"+this.name+"_spec.java"))) {
			oS.write(component_specification.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String transType(Transition transition) {
		if(transition instanceof Internal) {
			return " Internal";
		}
		else if(transition instanceof Enforceable) {
			return " Enforceable";
		}
		else {
			return " Spontaneous";
		}
//		return "not defined type";
	}

	private boolean isTransitionForReset(Transition transition, ArrayList<State> filteredStates) {
		for(int i = 0; i < filteredStates.size(); i++) {
			if(transition.getDst().equals(filteredStates.get(i))) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<State> removeIntermediateStates() {
		ArrayList<State> statesFilterd = new ArrayList<State>(); 
		int counter = 0; 
		for(int i = 0; i < states.size(); i++) {
			counter = 0;
			for(int j =0;j <states.size(); j++) {
				if(i != j) {
					if(states.get(j).getState_name().contains(states.get(i).getState_name())) {
						counter++; 
					}
				}
			}
			if(counter == 2) {
				statesFilterd.add(states.get(i));
			}
		}
		return statesFilterd;
	}
	@Override
	public String toString() {	
		String S = "Component *** " + this.name + " ***\n";
		S += "\tOptional Component " + this.optional + "\n";
		S += "\tUsable Component " + this.usableComponent + "\n";
		S += "\tInitial State: "+ initial_state.getState_name()+ "\n";
		S += "\tStates: ";
		for(int i=0; i < states.size(); i++ ) {
			
			if(states.size() == 1) {
				S += "[" + states.get(i).getState_name() + "]";
			} 
			else {
				if(i ==0) 
					S += "[" + states.get(i).getState_name() + ", ";
				else if(i != states.size()-1 ) 
					S += states.get(i).getState_name() + ", ";
				else
					S += states.get(i).getState_name() + "]";
			}
		}	
		S += "\n\tTransitions: \n";	
		for(int i=0; i < Transitions.size(); i++ ) {
			S += Transitions.get(i).toString();	
		}
	
		
//		S += "\n\tGuards: \n";	
		for(int i = 0 ; i < guards.size() ; i++) {
			S += guards.get(i).toString();
		}
		
//		S += this.guards.toString();
		S += "\n\\n\n";
		return S;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public State getInitial_state() {
		return initial_state;
	}



	public void setInitial_state(State initial_state) {
		this.initial_state = initial_state;
	}



	public ArrayList<State> getStates() {
		return states;
	}



	public void setStates(ArrayList<State> states) {
		this.states = states;
	}



	public ArrayList<Transition> getTransitions() {
		return Transitions;
	}



	public void setTransitions(ArrayList<Transition> transitions) {
		Transitions = transitions;
	}

	public Transition getTransitionsFromXtoY(String src, String dst) {
		Transition t = null; 
		for(int i= 0 ; i < this.Transitions.size() ; i++) {
			if(Transitions.get(i).getDst().getState_name().equals(dst) && Transitions.get(i).getSrc().getState_name().equals(src) ) {
				t= Transitions.get(i);
			}
		}
		return t;
	}

	public State findStateByName(String state_name) {
		for(int i = 0; i < this.states.size(); i++ ) {
			if(this.states.get(i).getState_name().equals(state_name)) {
				return this.states.get(i);
			}
		}
		return null;
	}

	public void addGuardToListOfGaurds(Guard guard) {
		this.guards.add(guard);
	}

	public Transition findTransitionByName(String transition_name) {
		
		Transition t = null; 
		for(int i =0 ; i < this.Transitions.size(); i++) {
			if(this.Transitions.get(i).getTransition_name().equals(transition_name)) {
//				System.out.println(transition_name);
				return Transitions.get(i);
			}
		}
		
		return t;
	}

}
