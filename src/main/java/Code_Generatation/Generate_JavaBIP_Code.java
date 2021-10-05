package Code_Generatation;

import BIP_tools.Component;
import BIP_tools.Enforceable;
import BIP_tools.Guard;
import BIP_tools.Guard_Exclude;
import BIP_tools.Guard_Implies;
import BIP_tools.Internal;
import BIP_tools.Spontaneous;
import BIP_tools.Connector_Motif;
import BIP_tools.State;
import BIP_tools.Transition;

import java.awt.TextField;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Generate_JavaBIP_Code {
	
	public static final String state = "states_link_to_State_Base";  
	public static final String ends = "ends";  

	public static final String initial_state = "initial_s";  
	public static final String state_name = "state_name";  

	public static final String transition = "connectors";
	public static final String transition_name = "transition_name";
	public static final String transition_src = "src";
	public static final String transition_dst = "dst";
	public static final String transition_type = "xsi:type";
	public static final String component = "component";  
	public static final String guard = "guards"; 
	
	// kind of initialization to deal with my xml file
	public static Element Get_JavaBIP_root_element(Document doc) {
//		System.out.println("Root element -> " + doc.getDocumentElement().getNodeName());

		NodeList First_tag_xmi_List = doc.getElementsByTagName("xmi:XMI"); // retrieving <xmi:XMI>...<xmi:XMI/> 
//		System.out.println("----------------------------" + First_tag_xmi_List.getLength());  
		Node First_Node_inside_xmi_XMI_tag = First_tag_xmi_List.item(0); // returns the Java_BIP_project     
		Element eElement_xmi_XMI = (Element) First_Node_inside_xmi_XMI_tag;   
		NodeList nList_2 = eElement_xmi_XMI.getElementsByTagName("Java_BIP_project"); // retrieving <Java_BIP_project>...<Java_BIP_project/>      
		return (Element) nList_2.item(0);
	}
	
	
	// return the initial state of a component
	public static State Get_Initial_State(Node initial_state_node) {
		Element initial_state_element = (Element) initial_state_node;
		String init_state_name = initial_state_element.getAttribute(state_name);
		
		return new State(init_state_name);
	}
	
	// return states for a component c
	public static ArrayList<State> Get_States(NodeList states_list) {
		int j; 
		ArrayList<State> states_arraylist = new ArrayList<State>();
		State s1;
		//StringBuilder str = new StringBuilder();
		
		for(j = 0; j < states_list.getLength(); j++) {
			
			Node state_node = states_list.item(j);
			Element state_element = (Element) state_node;
			s1 = new State(state_element.getAttribute(state_name));
			states_arraylist.add(s1);
			//System.out.println("state "+ (j+1) + " :" + state_element.getAttribute(state_name) );
		}
		
		//PrintStates(states_arraylist);
		return states_arraylist;
	}
	
	// Printing states  
	public static void PrintStates(ArrayList<State> states_arraylist) {
		for(int i = 0; i < states_arraylist.size(); i++) {
			System.out.println("state "+(i+1)+ " ----> "+ states_arraylist.get(i).getState_name());
		}
	}
	
	// return the Transition (enforceable transitiions (tans_name: name, src: state , dst: state))
	public static Transition Get_Transition(ArrayList<State> states_arraylist,Element transition_element, State init_state, char last_character_of_the_source_state,char last_character_of_the_destination) {
		Transition t = null;
		
		
		if(!Character.isDigit(last_character_of_the_source_state)) { // initial state is the init
			if(Character.isDigit(last_character_of_the_destination)) {
				int number_of_dst_state = Character.getNumericValue(last_character_of_the_destination);
				
				if(transition_element.getAttribute(transition_type).equals("Internal") ) {	
					 t = new Internal(transition_element.getAttribute(transition_name), init_state, states_arraylist.get(number_of_dst_state),null);
				}
				else if(transition_element.getAttribute(transition_type).equals("Spontaneous")) {
					t = new Spontaneous(transition_element.getAttribute(transition_name), init_state, states_arraylist.get(number_of_dst_state),null);
				}
				else if(transition_element.getAttribute(transition_type).equals("Enforceable")) {
					t = new Enforceable(transition_element.getAttribute(transition_name), init_state, states_arraylist.get(number_of_dst_state),null, null);
				}
				
				 
			}
			else {
				if(transition_element.getAttribute(transition_type).equals("Internal") ) {	
					 t = new Internal(transition_element.getAttribute(transition_name), init_state, init_state,null);
				}
				else if(transition_element.getAttribute(transition_type).equals("Spontaneous")) {
					t = new Spontaneous(transition_element.getAttribute(transition_name), init_state, init_state,null);
				}
				else if(transition_element.getAttribute(transition_type).equals("Enforceable")) {
					t = new Enforceable(transition_element.getAttribute(transition_name), init_state, init_state,null, null);
				}
//				t = new Enforceable(transition_element.getAttribute(transition_name), init_state, init_state,null, null);

			}
		}
		else {
			int number_of_src_state = Character.getNumericValue(last_character_of_the_source_state);

			if(Character.isDigit(last_character_of_the_destination)) {
				int number_of_dst_state = Character.getNumericValue(last_character_of_the_destination);
		
				if(transition_element.getAttribute(transition_type).equals("Internal") ) {	
					 t = new Internal(transition_element.getAttribute(transition_name), states_arraylist.get(number_of_src_state), states_arraylist.get(number_of_dst_state),null);
				}
				else if(transition_element.getAttribute(transition_type).equals("Spontaneous")) {
					t = new Spontaneous(transition_element.getAttribute(transition_name), states_arraylist.get(number_of_src_state), states_arraylist.get(number_of_dst_state),null);
				}
				else if(transition_element.getAttribute(transition_type).equals("Enforceable")) {
					t = new Enforceable(transition_element.getAttribute(transition_name), states_arraylist.get(number_of_src_state), states_arraylist.get(number_of_dst_state),null, null);
				}
//				t = new Enforceable(transition_element.getAttribute(transition_name), states_arraylist.get(number_of_src_state), states_arraylist.get(number_of_dst_state),null, null);
			}
			else {
				if(transition_element.getAttribute(transition_type).equals("Internal") ) {	
					 t = new Internal(transition_element.getAttribute(transition_name), states_arraylist.get(number_of_src_state), init_state,null);
				}
				else if(transition_element.getAttribute(transition_type).equals("Spontaneous")) {
					t = new Spontaneous(transition_element.getAttribute(transition_name), states_arraylist.get(number_of_src_state), init_state,null);
				}
				else if(transition_element.getAttribute(transition_type).equals("Enforceable")) {
					t = new Enforceable(transition_element.getAttribute(transition_name), states_arraylist.get(number_of_src_state), init_state,null, null);
				}
//				t = new Enforceable(transition_element.getAttribute(transition_name), states_arraylist.get(number_of_src_state), init_state,null, null);

			}
		}
		return t;
	}
	

	// Create and return the transitions (enforceable transitiions (tans_name: name, src: state , dst: state))
	public static ArrayList<Transition> Get_transitions(NodeList transitions_list, ArrayList<State> states_arraylist , State init_state, Component com) {
		int j; 
		ArrayList<Transition> transitions_arraylist = new ArrayList<Transition>();
		
		for(j = 0; j < transitions_list.getLength(); j++) {
			Node transition_node = transitions_list.item(j);
			Element transition_element = (Element) transition_node;
			//system.out.println("Transition "+ (j) + " :" + transition_element.getAttribute(transition_name) );
			//system.out.println("src: " + transition_element.getAttribute(transition_src).charAt(transition_element.getAttribute(transition_src).length() - 1) );
			//system.out.println("dst: " + transition_element.getAttribute(transition_dst).charAt(transition_element.getAttribute(transition_dst).length() - 1) );
			
			
			// I take the last character to get the index of the destination state (in my xml format I have it like that )
			char last_character_of_the_destination= transition_element.getAttribute(transition_dst).charAt(transition_element.getAttribute(transition_dst).length() - 1);
			char last_character_of_the_source_state= transition_element.getAttribute(transition_src).charAt(transition_element.getAttribute(transition_src).length() - 1);

			// to get the tranistion with the right source and destination
			transitions_arraylist.add(Get_Transition(states_arraylist, transition_element,  init_state,  last_character_of_the_source_state, last_character_of_the_destination));
			if(transition_element.getAttribute(transition_name).contains("not")){
				ArrayList<State> filteredStates = com.removeIntermediateStates();
				for(int k = 0; k < filteredStates.size(); k++) {
					if(!transition_element.getAttribute(transition_name).split("_")[1].equals(filteredStates.get(k).getState_name())) {
						transitions_arraylist.add(new Enforceable(transition_element.getAttribute(transition_name), filteredStates.get(k), filteredStates.get(k),null, null));
					}
				}
//				System.out.println("here you go " + transition_element.getAttribute(transition_name) + " " + filteredStates );
			}
		}
		return transitions_arraylist;
	}
	
	private static Component findComponentByName(String com_name, ArrayList<Component> components_list) {
		for(int i =0; i < components_list.size(); i++) {
			if(com_name.equals(components_list.get(i).getName())) {
				return components_list.get(i);
			}
		}
		return null;
	}
	


	
	
	private static ArrayList<Guard> Get_guards(NodeList guard_list, ArrayList<Component> components_list) {
		ArrayList<Guard> guards_arr = new ArrayList<Guard>();
		Guard guard = null;
		Component srcCom;
		Component dstCom; 
		State srcState; 
		State dstState; 
		Transition srcCmpTransition;
		Transition dstCmpTransition;

		for(int i = 0;  i < guard_list.getLength(); i++ ) {
			Node guard_node = guard_list.item(i);
			Element guard_element = (Element) guard_node;
//			System.out.println("guard name: "+guard_element.getAttribute("name"));
//			System.out.println("guard method :"+guard_element.getAttribute("guardMethod"));
//			Example of the name: Implies___srcComponent:Camera___dstComponnt:Screen
			if(guard_element.getAttribute("name").split("___")[0].equals("Implies")) { // check if it is an Implies guard
				// taking Camera
				guard = new Guard_Implies(null, null, null, null, null, null,null);
				guard.setName(guard_element.getAttribute("name"));
				guard.setGuardMethod(guard_element.getAttribute("guardMethod"));
//				System.out.println("hey ya khara shuf : " +guard_element.getAttribute("name") );
				srcCom = findComponentByName(guard_element.getAttribute("name").split("___")[1].split(":")[1], components_list); 		
				dstCom = findComponentByName(guard_element.getAttribute("name").split("___")[2].split(":")[1], components_list); // taking Camera		
				((Guard_Implies) guard).setSrcComponent(srcCom);
				((Guard_Implies) guard).setDstComponent(dstCom);
//				System.out.println("implies  stuff : " +srcCom.getName());
//				System.out.println("implies stuff : " +dstCom.getName());
//				System.out.println("component printing : "+dstCom);
//				Example of guard method 
//				srcFeature:Camera___dstFeature:High_Resolution___srcTransition:init_to_Camera___dstTransitionHigh_Resolution_to_High_Resolution

				srcState = srcCom.findStateByName(guard_element.getAttribute("guardMethod").split("___")[0].split(":")[1]); 
				dstState = dstCom.findStateByName(guard_element.getAttribute("guardMethod").split("___")[1].split(":")[1]); 
				((Guard_Implies) guard).setSrcState(srcState);
				((Guard_Implies) guard).setDstState(dstState);
//				System.out.println("implies  stuff : " +srcState.getState_name());
//				System.out.println("implies stuff : " +dstState.getState_name());
				
				srcCmpTransition = srcCom.findTransitionByName(guard_element.getAttribute("guardMethod").split("___")[2].split(":")[1]); 
				dstCmpTransition= dstCom.findTransitionByName(guard_element.getAttribute("guardMethod").split("___")[3].split(":")[1]); 
				guard.setSrcCmpTransition(srcCmpTransition);
				guard.setDstCmpTransition(dstCmpTransition);
//				System.out.println("t : "+srcCmpTransition);
//				System.out.println("t : "+dstCmpTransition);
				
				guards_arr.add(guard);	
			}
			else if(guard_element.getAttribute("name").split("___")[0].equals("Exclude")) {
				guard = new Guard_Exclude(null, null, null, null, null, null,null);
				guard.setName(guard_element.getAttribute("name"));
				guard.setGuardMethod(guard_element.getAttribute("guardMethod"));
				
//				System.out.println("Exclude stuffsss : " +guard_element.getAttribute("name") );
				
				srcCom = findComponentByName(guard_element.getAttribute("name").split("___")[1].split(":")[1], components_list); 		
				dstCom = findComponentByName(guard_element.getAttribute("name").split("___")[2].split(":")[1], components_list); 
//				System.out.println("Exclude stuff : " +srcCom.getName());
//				System.out.println("Exclude stuff : " +dstCom.getName());
				((Guard_Exclude) guard).setSrcComponent(srcCom);
				((Guard_Exclude) guard).setDstComponent(dstCom);
				
				srcState = srcCom.findStateByName(guard_element.getAttribute("guardMethod").split("___")[0].split(":")[1]); 
				dstState = dstCom.findStateByName(guard_element.getAttribute("guardMethod").split("___")[1].split(":")[1]); 
				((Guard_Exclude) guard).setSrcState(srcState);
				((Guard_Exclude) guard).setDstState(dstState);
//				System.out.println("implies  stuff : " +srcState.getState_name());
//				System.out.println("implies stuff : " +dstState.getState_name());
				srcCmpTransition = srcCom.findTransitionByName(guard_element.getAttribute("guardMethod").split("___")[2].split(":")[1]); 
				dstCmpTransition= dstCom.findTransitionByName(guard_element.getAttribute("guardMethod").split("___")[3].split(":")[1]); 
	
				guard.setSrcCmpTransition(srcCmpTransition);
				guard.setDstCmpTransition(dstCmpTransition);
//				System.out.println("t : "+srcCmpTransition);
//				System.out.println("t : "+dstCmpTransition);
				
				guards_arr.add(guard);	
				
			}
			else { // in general (this should be the only case change the code)
				// taking Camera
				guard = new Guard(null, null, null);
				guard.setName(guard_element.getAttribute("name"));
				guard.setGuardMethod(guard_element.getAttribute("guardMethod"));
//				System.out.println("hey ya khara shuf : " +guard_element.getAttribute("name") );
				srcCom = findComponentByName(guard_element.getAttribute("name").split("___")[1].split(":")[1], components_list); 		
//				dstCom = findComponentByName(guard_element.getAttribute("name").split("___")[2].split(":")[1], components_list); // taking Camera		
//				((Guard_Implies) guard).setSrcComponent(srcCom);
//				((Guard_Implies) guard).setDstComponent(dstCom);
//				System.out.println("implies  stuff : " +srcCom.getName());
//				System.out.println("implies stuff : " +dstCom.getName());
//				System.out.println("component printing : "+dstCom);
//				Example of guard method 
//				srcFeature:Camera___dstFeature:High_Resolution___srcTransition:init_to_Camera___dstTransitionHigh_Resolution_to_High_Resolution

				srcState = srcCom.findStateByName(guard_element.getAttribute("guardMethod").split("___")[0].split(":")[1]); 
//				dstState = dstCom.findStateByName(guard_element.getAttribute("guardMethod").split("___")[1].split(":")[1]); 
//				((Guard_Implies) guard).setSrcState(srcState);
//				((Guard_Implies) guard).setDstState(dstState);
////				System.out.println("implies  stuff : " +srcState.getState_name());
////				System.out.println("implies stuff : " +dstState.getState_name());
				
				srcCmpTransition = srcCom.findTransitionByName(guard_element.getAttribute("guardMethod").split("___")[2].split(":")[1]); 
//				dstCmpTransition= dstCom.findTransitionByName(guard_element.getAttribute("guardMethod").split("___")[3].split(":")[1]); 
				guard.setSrcCmpTransition(srcCmpTransition);
//				guard.setDstCmpTransition(dstCmpTransition);
//				System.out.println("t : "+srcCmpTransition);
//				System.out.println("t : "+dstCmpTransition);
				
				guards_arr.add(guard);	
			}
			
		}
		
		return guards_arr;
	}


	public static Connector_Motif GetConnectorMotifs(NodeList endsList ,ArrayList<Component> components_arraylist, Element connectorMotif_element){
		
		Connector_Motif connector_instance  = new Connector_Motif(connectorMotif_element.getAttribute("connector_id").toString());
		ArrayList<Transition> transitionList = new ArrayList<Transition>();
		int indexOfTheComponent, indexOfTheTransition; 
		for(int i = 0 ; i < endsList.getLength(); i++) {
			
			Node endNode = endsList.item(i);
			Element endElement = (Element) endNode;
//			System.out.println("end "+ (i) + " :" + endElement.getAttribute("xsi:type") );
//			System.out.println("end "+ (i) + " :" + endElement.getAttribute("description") );
//			System.out.println("end "+ (i) + " :" + endElement.getAttribute("one_enforceable") );
//			System.out.println("end "+ (i) + " :" + endElement.getAttribute("one_enforceable").lastIndexOf("@components.") );
			
			String[] sentences = endElement.getAttribute("one_enforceable").split("@components.");
			indexOfTheComponent = Integer.parseInt(sentences[1].split("/")[0]);
			String[] sentences_2 = sentences[1].split("connectors.");
			indexOfTheTransition = Integer.parseInt(sentences_2[1].split("\"")[0]);
			 
//			System.out.println( "transition number : " + sentences_2[1].split("\"")[0]);
//			
			
			if(endElement.getAttribute("xsi:type").equals("Trigger")) {
//				System.out.println(components_arraylist.get(indexOfTheComponent).getTransitions().get(indexOfTheTransition));
				((Enforceable) components_arraylist.get(indexOfTheComponent).getTransitions().get(indexOfTheTransition)).setSynchron(true);
			}
			else {
//				System.out.println(components_arraylist.get(indexOfTheComponent).getTransitions().get(indexOfTheTransition));
				((Enforceable) components_arraylist.get(indexOfTheComponent).getTransitions().get(indexOfTheTransition)).setSynchron(true);
			}
			transitionList.add(components_arraylist.get(indexOfTheComponent).getTransitions().get(indexOfTheTransition));
		}
		connector_instance.setRelationsList(transitionList);
		return connector_instance;
	}
	

	public static void GenerateGlue(ArrayList<Connector_Motif> connectorsList) {
//		import org.javabip.api.BIPGlue;
//		import org.javabip.glue.GlueBuilder;
//
//		import Exclude_FM_To_JavaBIP.*;
//		import Screen_Component.Screen_ports;
//		import Screen_Component.Screen_spec;
		
		
		StringBuilder glueCode = new StringBuilder();
		
		glueCode.append("package Output;");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("import org.javabip.api.BIPGlue;");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("import org.javabip.glue.GlueBuilder;");
		glueCode.append(System.lineSeparator());
		
//		glueCode.append("import Output.*");
//		glueCode.append(System.lineSeparator());



//public class Exclude_Glue {
//	private BIPGlue bipGlue;
//	
//	public Exclude_Glue(BIPGlue bipGlue) {
//		this.bipGlue = bipGlue;
//	}
//	
//	public Exclude_Glue() {
//		this.bipGlue = init();
//	}
//	
//	public BIPGlue getBipGlue() {
//		return bipGlue;
//	}
//
//	public void setBipGlue(BIPGlue bipGlue) {
//		this.bipGlue = bipGlue;
//	}
		
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());

		
		glueCode.append("public class Project_Glue{");
		glueCode.append(System.lineSeparator());
		glueCode.append("\tprivate BIPGlue bipGlue;");
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());

		glueCode.append("\tpublic Project_Glue(BIPGlue bipGlue){");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tthis.bipGlue = bipGlue;");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		

		glueCode.append(System.lineSeparator());

		
		glueCode.append("\tpublic Project_Glue(){");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tthis.bipGlue = init();");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		
		
		glueCode.append(System.lineSeparator());

		
		glueCode.append("\tpublic BIPGlue getBipGlue(){");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\treturn this.bipGlue;");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		
		
		glueCode.append(System.lineSeparator());

		
		glueCode.append("\tpublic void setBipGlue(BIPGlue bipGlue){");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tthis.bipGlue = bipGlue;");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		
		
	
		
//private BIPGlue init () {
//	return bipGlue = new GlueBuilder() {
//		@Override
//		public void configure() {
		
		
		glueCode.append("\tprivate BIPGlue init () {");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("\t\treturn bipGlue = new GlueBuilder() {");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("\t\t\t@Override");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("\t\t\tpublic void configure() {");
		glueCode.append(System.lineSeparator());
		
		
		
		

		
//		// *********************************************************************************************************************************
//		port(Screen_spec.class, Screen_ports.screen_p_high_resolution).requires(Hold_exclude.class , "hold_exclude_p_state_3_to_state_4_high" );
//		port(Hold_exclude.class , "hold_exclude_p_state_3_to_state_4_high" ).requires(Screen_spec.class, Screen_ports.screen_p_high_resolution);
//
//		port(Screen_spec.class, Screen_ports.screen_p_high_resolution).accepts(Hold_exclude.class , "hold_exclude_p_state_3_to_state_4_high" );
//		port(Hold_exclude.class , "hold_exclude_p_state_3_to_state_4_high" ).accepts(Screen_spec.class, Screen_ports.screen_p_high_resolution);

		// *********************************************************************************************************************************
		
		
		Transition transition_base;
		Transition temp_transition;
//		StringBuilder acceptsbuilder  = new StringBuilder();
		
		for(int i = 0 ; i < connectorsList.size() ; i++ ) { // on all connector motifs that exist
			glueCode.append(System.lineSeparator());
			glueCode.append(System.lineSeparator());
			glueCode.append(System.lineSeparator());
			for(int j = 0 ; j < connectorsList.get(i).getRelationsList().size();j++) {// to for each end should be connected to all other ends 
				transition_base =  connectorsList.get(i).getRelationsList().get(j); // for motif i we take end j 
				
				if( ((Enforceable) transition_base).getSynchron() == true) { // this is a synchron transition
					
// 					For Require				
					glueCode.append("\t\t\t\tport(" + transition_base.getComponentOfTheTransition().getName()+"_spec.class, "+transition_base.getComponentOfTheTransition().getName()+ "_ports."+transition_base.getComponentOfTheTransition().getName()+"_p_"+transition_base.getTransition_name()+").requires(");
					for(int k =0 ; k < connectorsList.get(i).getRelationsList().size() ; k++ ) { // list of ends transitions that are engaged in the transaction 					
						if(j != k) {
							temp_transition = connectorsList.get(i).getRelationsList().get(k);
							
								
							glueCode.append(temp_transition.getComponentOfTheTransition().getName()+"_spec.class, "+temp_transition.getComponentOfTheTransition().getName()+ "_ports."+temp_transition.getComponentOfTheTransition().getName()+"_p_"+temp_transition.getTransition_name() + ", ");
							//glueCode.append(System.lineSeparator());
						}	
					}
					glueCode.replace(glueCode.length()-2, glueCode.length(), "");
					glueCode.append(");");
					glueCode.append(System.lineSeparator());
					
					
					
// 					For accepts
					glueCode.append("\t\t\t\tport(" + transition_base.getComponentOfTheTransition().getName()+"_spec.class, "+transition_base.getComponentOfTheTransition().getName()+ "_ports."+transition_base.getComponentOfTheTransition().getName()+"_p_"+transition_base.getTransition_name()+").accepts(");
					for(int k =0 ; k < connectorsList.get(i).getRelationsList().size() ; k++ ) { // list of ends transitions that are engaged in the transaction 					
						if(j != k) {
							temp_transition = connectorsList.get(i).getRelationsList().get(k);
							
								
							glueCode.append(temp_transition.getComponentOfTheTransition().getName()+"_spec.class, "+temp_transition.getComponentOfTheTransition().getName()+ "_ports."+temp_transition.getComponentOfTheTransition().getName()+"_p_"+temp_transition.getTransition_name() + ", ");
							//glueCode.append(System.lineSeparator());
						}	
					}
					glueCode.replace(glueCode.length()-2, glueCode.length(), "");
					glueCode.append(");");
					glueCode.append(System.lineSeparator());
				
				}
				else { // for trigger
//					For require
					for(int k =0 ; k < connectorsList.get(i).getRelationsList().size() ; k++ ) { // list of ends transitions that are engaged in the transaction 
						if(j != k) {
							temp_transition = connectorsList.get(i).getRelationsList().get(k);
							glueCode.append("\t\t\t\tport("+ transition_base.getComponentOfTheTransition().getName()+"_spec.class, "+transition_base.getComponentOfTheTransition().getName()+ "_ports."+transition_base.getComponentOfTheTransition().getName()+"_p_"+transition_base.getTransition_name()+")");
							glueCode.append(".requires();");
							glueCode.append(System.lineSeparator());
		
						}
					} 
					
//					For accepts
					glueCode.append("\t\t\t\tport(" + transition_base.getComponentOfTheTransition().getName()+"_spec.class, "+transition_base.getComponentOfTheTransition().getName()+ "_ports."+transition_base.getComponentOfTheTransition().getName()+"_p_"+transition_base.getTransition_name()+").accepts(");
					for(int k =0 ; k < connectorsList.get(i).getRelationsList().size() ; k++ ) { // list of ends transitions that are engaged in the transaction 					
						if(j != k) {
							temp_transition = connectorsList.get(i).getRelationsList().get(k);
							
								
							glueCode.append(temp_transition.getComponentOfTheTransition().getName()+"_spec.class, "+temp_transition.getComponentOfTheTransition().getName()+ "_ports."+temp_transition.getComponentOfTheTransition().getName()+"_p_"+temp_transition.getTransition_name() + ", ");
							//glueCode.append(System.lineSeparator());
						}	
					}
					glueCode.replace(glueCode.length()-2, glueCode.length(), "");
					glueCode.append(");");
					glueCode.append(System.lineSeparator());
				} 
			}
			
		}


		glueCode.append("\t\t\t}");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("\t\t}.build();");
		glueCode.append(System.lineSeparator());

		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		
		glueCode.append(System.lineSeparator());
		glueCode.append("}");
		
		
		try (FileOutputStream oS = new FileOutputStream(new File("./Output/"+"Project_Glue.java"))) {
			oS.write(glueCode.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void GenerateTestFile(ArrayList<Component> components_arraylist) {
//		import java.awt.Button;
//		import java.awt.Frame;
//		import java.awt.TextField;
//		import java.awt.event.ActionEvent;
//		import java.awt.event.ActionListener;
//		import java.util.Scanner;
//		import org.javabip.annotations.Port;
//		import org.javabip.api.BIPActor;
//		import org.javabip.api.BIPEngine;
//		import org.javabip.api.BIPGlue;
//		import org.javabip.api.PortType;
//		import org.javabip.engine.factory.EngineFactory;
//		import akka.actor.ActorSystem;

		StringBuilder glueCode = new StringBuilder();
		
		glueCode.append("package Output;");
		glueCode.append(System.lineSeparator());
		glueCode.append(ImportsGeneration());
		glueCode.append(System.lineSeparator());

// 		public class test {
		
		glueCode.append("public class test {");
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());


// 		private static BIPActor gps_1_actor;
//		private static BIPActor calls_1_actor;
//		private static BIPActor mp3_1_actor;
//		private static BIPActor camera_1_actor;
//		private static BIPActor screen_1_actor;
		
		for(int i = 0; i < components_arraylist.size(); i++) {
			if(components_arraylist.get(i).getUsableComponent() == true) {
				glueCode.append("\tprivate static BIPActor " + components_arraylist.get(i).getName()+"_1_actor;");
				glueCode.append(System.lineSeparator());
			}
		}
		
		glueCode.append(System.lineSeparator());

//		private static ActorSystem system;
//		private static EngineFactory engineFactory;
//		static BIPGlue glue;
//		static BIPEngine engine;
		glueCode.append("\tprivate static ActorSystem system;");
		glueCode.append(System.lineSeparator());
		glueCode.append("\tprivate static EngineFactory engineFactory;");
		glueCode.append(System.lineSeparator());
		glueCode.append("\tstatic BIPGlue glue;");
		glueCode.append(System.lineSeparator());
		glueCode.append("\tstatic BIPEngine engine;");
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());

//		private static GPS_spec gps_1;
//		private static Calls_spec calls_1; 
//		private static MP3_spec mp3_1;
//		private static Camera_spec camera_1;
//		private static Screen_spec screen_1; 
		
		for(int i = 0; i < components_arraylist.size(); i++) {
			if(components_arraylist.get(i).getUsableComponent() == true) {
				glueCode.append("\tprivate static " + components_arraylist.get(i).getName()+"_spec "+components_arraylist.get(i).getName() +"_1;");
				glueCode.append(System.lineSeparator());
			}
		}
		glueCode.append(System.lineSeparator());

//		private static TextField tf;  
//		public static void frame1() { 
//			Frame f=new Frame();  
		
		glueCode.append("\tprivate static TextField tf;");
		glueCode.append(System.lineSeparator());
		glueCode.append("\tpublic static void frame1() { ");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tFrame f=new Frame(); ");
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());
		
		
		

//		Button button_select_calls=new Button("Select Calls");  
// 		Button button_unselect_calls=new Button("Unselect Calls ");  
		
//		Button button_select_GPS=new Button("Select GPS");  
//		Button button_unselect_GPS=new Button("Unselect GPS ");  
//		
//		Button button_select_camera=new Button("Select camera");  
//		Button button_unselect_camera=new Button("Unselect camera "); 
//		
//		Button button_select_mp3=new Button("Select mp3");  
//		Button button_unselect_mp3=new Button("Unselect mp3 "); 
//		
//		Button button_select_h_r=new Button("Select H_R");  
//		Button button_unselect_h_r=new Button("Unselect H_R "); 
//		
//		Button button_select_basic=new Button("Select basic");  
//		Button button_unselect_basic=new Button("Unselect basic "); 
//		
//		Button button_select_colour=new Button("Select colour");  
//		Button button_unselect_colour=new Button("Unselect colour ");
		State state_filtered; 
		for(int i=0; i <components_arraylist.size(); i++) { // move on all components
			if(components_arraylist.get(i).getUsableComponent() == false)
				continue;
			for(int j =0 ; j < components_arraylist.get(i).removeIntermediateStates().size(); j++) { // move on all the filtered states of the component i
				state_filtered = components_arraylist.get(i).removeIntermediateStates().get(j);
				glueCode.append("\t\tButton button_select_" + state_filtered.getState_name() + " = new Button(\"Select " +state_filtered.getState_name() +"\");" );
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\tButton button_unselect_" + state_filtered.getState_name()  + " = new Button(\"Unselect " +state_filtered.getState_name() +"\");" );
				glueCode.append(System.lineSeparator());
			}
			glueCode.append(System.lineSeparator());
		} 
//		x, y, width, height
//		button_select_calls.setBounds(20,50,130,30); 
//		button_select_GPS.setBounds(180, 50, 130, 30);
//		button_select_mp3.setBounds(340, 50, 130, 30);
//		button_select_camera.setBounds(500, 50, 130, 30);
//		button_select_h_r.setBounds(660, 50, 130, 30); // 500 660 820 980 1140 1300
//		button_select_basic.setBounds(820, 50, 130, 30); // 500 660 820 980 1140 1300
//		button_select_colour.setBounds(980, 50, 130, 30); // 500 660 820 980 1140 1300
//		
//		button_unselect_calls.setBounds(20,100,130,30); 
//		button_unselect_GPS.setBounds(180,100,130,30); 
//		button_unselect_mp3.setBounds(340, 100, 130, 30);
//		button_unselect_camera.setBounds(500,100,130,30);
//		button_unselect_h_r.setBounds(660,100,130,30);
//		button_unselect_basic.setBounds(820,100,130,30);
//		button_unselect_colour.setBounds(980,100,130,30);
		
		int width = 130;
		int height =30; 
		int x_select =20; 
		int y_select = 50;
		int x_reset =20; 
		int y_reset = 100;
		
		for(int i=0; i <components_arraylist.size(); i++) { // move on all components
			if(components_arraylist.get(i).getUsableComponent() == false)
				continue;
			for(int j =0 ; j < components_arraylist.get(i).removeIntermediateStates().size(); j++) { // move on all the filtered states of the component i
				
				state_filtered = components_arraylist.get(i).removeIntermediateStates().get(j);
				glueCode.append("\t\tbutton_select_" + state_filtered.getState_name() + ".setBounds(" + x_select +", "+y_select +", "+width+", "+height+");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\tbutton_unselect_" + state_filtered.getState_name() + ".setBounds(" + x_reset +", "+y_reset +", "+width+", "+height+");");
				glueCode.append(System.lineSeparator());
				x_select += 160;
				x_reset += 160;
			}
			glueCode.append(System.lineSeparator());
		} 
		
		
//		tf = new TextField();  
//		tf.setBounds(400,150,270,40);  
		glueCode.append("\t\ttf = new TextField(); ");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\ttf.setBounds(400,150,270,40); ");
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());

		
		
//		button_select_calls.addActionListener( new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			System.out.println("********************************** ********************************** ********************************** ");
//			System.out.println("********************************** Selecting Calls ********************************** ");
//			System.out.println("********************************** ********************************** ********************************** ");
//			tf.setText("Selecting Calls.... result in terminal");
//			calls_1_actor.inform(Calls_ports.Calls_p_SCalls);
//		}
//	});		
//	button_unselect_calls.addActionListener( new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			System.out.println("********************************** ********************************** ********************************** ");
//			System.out.println("********************************** UnSelecting Calls ********************************** ");
//			System.out.println("********************************** ********************************** ********************************** ");
//			tf.setText("Unselecting Calls ... result in terminal");
//			calls_1_actor.inform(Calls_ports.Calls_p_SCalls_reset);
//		}
//	});
// etc... 
		
		for(int i=0; i <components_arraylist.size(); i++) { // move on all components
			if(components_arraylist.get(i).getUsableComponent() == false)
				continue;
			for(int j =0 ; j < components_arraylist.get(i).removeIntermediateStates().size(); j++) { // move on all the filtered states of the component i
				
				state_filtered = components_arraylist.get(i).removeIntermediateStates().get(j);
				glueCode.append("\t\tbutton_select_" + state_filtered.getState_name() + ".addActionListener( new ActionListener() {");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\tpublic void actionPerformed(ActionEvent e) {");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t\tSystem.out.println(\"********************************** ********************************** ********************************** \");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t\tSystem.out.println(\"********************************** Selecting "+state_filtered.getState_name() + " ********************************** \");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t\tSystem.out.println(\"********************************** ********************************** ********************************** \");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t\ttf.setText(\"Selecting "+ state_filtered.getState_name() +" ... result in terminal\");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t\t"+components_arraylist.get(i).getName()+"_1_actor.inform("+ components_arraylist.get(i).getName() +"_ports."+components_arraylist.get(i).getName()+"_p_S"+state_filtered.getState_name()+");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t}");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t});");
				
				
				glueCode.append(System.lineSeparator());
				glueCode.append(System.lineSeparator());
				glueCode.append(System.lineSeparator());

				
				
				glueCode.append("\t\tbutton_unselect_" +  state_filtered.getState_name()  + ".addActionListener( new ActionListener() {");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\tpublic void actionPerformed(ActionEvent e) {");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t\tSystem.out.println(\"********************************** ********************************** ********************************** \");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t\tSystem.out.println(\"********************************** UnSelecting "+state_filtered.getState_name() + " ********************************** \");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t\tSystem.out.println(\"********************************** ********************************** ********************************** \");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t\ttf.setText(\"Unselecting "+ state_filtered.getState_name() +" ... result in terminal\");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t\t"+components_arraylist.get(i).getName()+"_1_actor.inform("+ components_arraylist.get(i).getName() +"_ports."+components_arraylist.get(i).getName()+"_p_S"+state_filtered.getState_name()+"_reset);");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t\t}");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\t});");
				glueCode.append(System.lineSeparator());
		
			}
			glueCode.append(System.lineSeparator());
		} 
		
		
//		f.add(button_select_calls);
//		f.add(button_unselect_calls);
//	
//		f.add(button_select_GPS);  
//		f.add(button_unselect_GPS); 
		
		for(int i=0; i <components_arraylist.size(); i++) { // move on all components
			if(components_arraylist.get(i).getUsableComponent() == false)
				continue;
			for(int j =0 ; j < components_arraylist.get(i).removeIntermediateStates().size(); j++) { // move on all the filtered states of the component i
				
				state_filtered = components_arraylist.get(i).removeIntermediateStates().get(j);
				glueCode.append("\t\tf.add(button_select_" + state_filtered.getState_name()+  ");");
				glueCode.append(System.lineSeparator());
				glueCode.append("\t\tf.add(button_unselect_" + state_filtered.getState_name()+  ");");
				glueCode.append(System.lineSeparator());
				
			}
			glueCode.append(System.lineSeparator());
		} 

//		f.add(tf);	
//		f.setSize(1150,600);  
//		f.setLayout(null);  
//		f.setVisible(true); 
		glueCode.append("\t\tf.add(tf);");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tf.setSize(1150,600); ");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tf.setLayout(null);");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tf.setVisible(true);");
		glueCode.append(System.lineSeparator());
		
//	}
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());

		
		
//		public static void main(String[] args) {
//			// TODO Auto-generated method stub
//			System.out.println("*************** Example Safe Dynamic reconfiguration  ***************");
//			setUpBIP();
//			
//		}
		glueCode.append("\tpublic static void main(String[] args) {");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tSystem.out.println(\"*************** Example Safe Dynamic reconfiguration  ***************\");");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tsetUpBIP();");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());

		
//		public static void setUpBIP() {
//		system = ActorSystem.create("MySystem");
//        engineFactory = new EngineFactory(system);
//        
//        glue = new Project_Glue().getBipGlue();
//        
//        engine = engineFactory.create("Engine", glue); // attached the glue to the engine
		
		glueCode.append("\tpublic static void setUpBIP() {");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tsystem = ActorSystem.create(\"MySystem\");");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tengineFactory = new EngineFactory(system);");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tglue = new Project_Glue().getBipGlue();");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tengine = engineFactory.create(\"Engine\", glue); // attached the glue to the engine");
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());


//      gps_1 = new GPS_spec();
//      calls_1 = new Calls_spec();
//      mp3_1 = new MP3_spec();
//      camera_1 = new Camera_spec();
//      screen_1 = new Screen_spec();
//      
		for(int i=0; i <components_arraylist.size(); i++) { // move on all components
			if(components_arraylist.get(i).getUsableComponent() == false)
				continue;
			glueCode.append("\t\t" + components_arraylist.get(i).getName() + "_1 = new "+  components_arraylist.get(i).getName()+"_spec();" );
			glueCode.append(System.lineSeparator());
		} 
		
//      
//      gps_1_actor = engine.register(gps_1, "GPS_component", true); 
//      calls_1_actor = engine.register(calls_1, "Calls_component", true); 
//      mp3_1_actor = engine.register(mp3_1, "mp3_component", true); 
//      camera_1_actor = engine.register(camera_1, "camera_component", true);  
//      screen_1_actor = engine.register(screen_1, "Screen_component", true); 
//      
		glueCode.append(System.lineSeparator());

		
		for(int i=0; i <components_arraylist.size(); i++) { // move on all components
			if(components_arraylist.get(i).getUsableComponent() == false)
				continue;
			glueCode.append("\t\t" + components_arraylist.get(i).getName() + "_1_actor = engine.register("+  components_arraylist.get(i).getName()+"_1, \"" +  components_arraylist.get(i).getName()+"_component\", true);" );
			glueCode.append(System.lineSeparator());
		} 
		glueCode.append(System.lineSeparator());

		
//      int n = 1, option = 0; 
//		engine.start();
//		engine.execute();
//		frame1();
//		Scanner sc = new Scanner(System.in);

//		while(n == 1) {
//			System.out.println("Use the user interface");	
//			option = sc.nextInt();
//		}
//		
//		try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		
//		engine.stop();
//		engineFactory.destroy(engine);
//		
//		System.out.println("The engine is stopped, satisfied?");
//		
//	}
//
//}
		glueCode.append("\t\tint n = 1, option = 0; ");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tengine.start();");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tengine.execute();");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tframe1();");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tScanner sc = new Scanner(System.in);");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\twhile(n == 1) {");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\t\tSystem.out.println(\"Use the user interface\");	");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\t\toption = sc.nextInt();");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\t}");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\ttry {");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\t\tThread.sleep(10000);");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\t} catch (InterruptedException e) {");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\t\te.printStackTrace();");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\t}");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tengine.stop();");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tengineFactory.destroy(engine);");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tSystem.out.println(\"The engine is stopped, satisfied?\");");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		glueCode.append("}");
		glueCode.append(System.lineSeparator());
		

		
		try (FileOutputStream oS = new FileOutputStream(new File("./Output/"+"test.java"))) {
			oS.write(glueCode.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	private static String ImportsGeneration() {	
		String S ="";
		S += "import java.awt.Button;\n";
		S += "import java.awt.Frame;\n";
		S += "import java.awt.TextField;\n";
		S += "import java.awt.event.ActionEvent;\n";
		S += "import java.awt.event.ActionListener;\n";
		S += "import java.util.Scanner;\n";
		S += "import org.javabip.annotations.Port;\n";
		S += "import org.javabip.api.BIPActor;\n";
		S += "import org.javabip.api.BIPEngine;\n";
		S += "import org.javabip.api.BIPGlue;\n";
		S += "import org.javabip.api.PortType;\n";
		S += "import org.javabip.engine.factory.EngineFactory;\n";
		S += "import akka.actor.ActorSystem;\n";
		
		return S;
	}


	public static void main(String[] args){
		int i,j;
		ArrayList<Component> components_arraylist = new ArrayList<Component>();
		try {
			 // creating a document builder object
			File inputFile = new File("/Users/salmanfarhat/eclipse-workspace/XMI_to_JavaBIP_Transformation_SimonV2/Input/ATL_NEW_TRANS_ssnew.xmi");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			 
			//	         Read the XML file to Document object.
			Document doc = dBuilder.parse(inputFile);
			 
			//	         (bring or return to a normal or standard condition or state) return a tree with the root element
			doc.getDocumentElement().normalize();
			 
			 
			// 			getting the Java_BIP_project Element
			Element eElement_Java_BIP_project = Get_JavaBIP_root_element(doc);
			 

			NodeList components_list = eElement_Java_BIP_project.getElementsByTagName("components");
			//system.out.println("number of components : " + eElement_Java_BIP_project.getElementsByTagName("components").getLength());
			Component c1;
			for( i = 0; i < components_list.getLength() ; i++) {
				c1 = new Component(null,null, null, null, null, null);
				Node component_node = components_list.item(i);
				if (component_node.getNodeType() == Node.ELEMENT_NODE)
				{
					    //Print each employee's detail
					Element component_element = (Element) component_node; 
					
					//system.out.println("Component name : "    + component_element.getAttribute("name"));
					c1.setName(component_element.getAttribute("name"));
					
					
					if(component_element.getAttribute("optional").equals("true")) {
						c1.setOptional(true);
					}else {
						c1.setOptional(false);
					}
					//system.out.println("optional : "    + c1.getOptional());
					
					if(component_element.getAttribute("usableComponent").equals("true")) {
						c1.setUsableComponent(true);
					}else {
						c1.setUsableComponent(false);
					}
					//system.out.println("Usable Component : "    + c1.getUsableComponent());

			
// ************************************************************** states **************************************************************
					NodeList states_list = component_element.getElementsByTagName(state);
					//system.out.println("number of states state: "+ (states_list.getLength()+1) );
					c1.setStates(Get_States(states_list)); 
// ************************************************************************************************************************************
					
// ************************************************************** initial state **************************************************************
					Node initial_state_node = component_element.getElementsByTagName(initial_state).item(0);
//					System.out.println("state 0 : "+ Get_Initial_State(initial_state_node));
					c1.setInitial_state(Get_Initial_State(initial_state_node));
// ************************************************************************************************************************************
					
// ************************************************************** transitions **************************************************************
					NodeList transitions_list = component_element.getElementsByTagName(transition);
					ArrayList<Transition> transitions_arraylist = Get_transitions(transitions_list ,  c1.getStates() ,c1.getInitial_state(),c1 );
					c1.setTransitions(transitions_arraylist);
					
					for(int h = 0 ; h < transitions_arraylist.size(); h++) {
						transitions_arraylist.get(h).setComponentOfTheTransition(c1);
					}

// ************************ Adding Component to the List ***************************************************************************
					components_arraylist.add(c1);
				 }
			 } 
			
//// ************************************************************** guards **************************************************************

			Component comp;
			ArrayList<Guard> guards_arraylist = new ArrayList<>();
			for( i = 0; i < components_list.getLength() ; i++) { // Loop on the list of components 
				
				Node component_node = components_list.item(i);
				if (component_node.getNodeType() == Node.ELEMENT_NODE)
				{
// ************************************************************** Generating guards **************************************************************

					Element component_element = (Element) component_node;
					comp = findComponentByName(component_element.getAttribute("name"), components_arraylist);
					// create guards after i have all components created
					NodeList guard_list = component_element.getElementsByTagName("guards"); // take all the guards for the component comp
					guards_arraylist = Get_guards(guard_list, components_arraylist);
//					System.out.println(guards_arraylist);
					for(j =0 ; j < guards_arraylist.size(); j++) {
						guards_arraylist.get(j).setRelatedComponent(comp);
						
					}
					comp.setGuards(guards_arraylist);
					

				}
			}
			
//// *****************************************************************generate code for components *******************************************************
//			
			for(int l = 0; l < components_arraylist.size(); l++) {
				if(components_arraylist.get(l).getUsableComponent() == false)
					continue; 
				components_arraylist.get(l).GenerateCode();
				//System.out.println(components_arraylist.get(l).toString());
			}		
// ***************************************************************** Create Motifs *******************************************************
	
//			Connector_Motif connector;
//			
			NodeList connectorMotifsList = eElement_Java_BIP_project.getElementsByTagName("connector_motifs");
			System.out.println("number of connector motifs : " + eElement_Java_BIP_project.getElementsByTagName("connector_motifs").getLength());
			ArrayList<Connector_Motif> connectorsList = new ArrayList<>(); // Motifs from the one received from the ATL xml file 		

			for(i = 0 ; i < connectorMotifsList.getLength();i++) {
				Node connectorMotif_node = connectorMotifsList.item(i); // get the instances of connector_motifs each one by it self
				if (connectorMotif_node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element connectorMotif_element = (Element) connectorMotif_node;
					NodeList endsList = connectorMotif_element.getElementsByTagName(ends); // take the ends 
					connectorsList.add(GetConnectorMotifs(endsList , components_arraylist , connectorMotif_element));
				}
			}

			
			
// ************************************** Generate Glue specifications *******************************************************
			GenerateGlue(connectorsList);
			
			
// ************************************** Create the test java file *******************************************************
			GenerateTestFile(components_arraylist);
			
			
// ***************************************************************** System Description *******************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
			printSystemDescription( components_arraylist, connectorsList);
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
			
			
			
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	}






	public static void printSystemDescription(ArrayList<Component> components_arraylist, ArrayList<Connector_Motif> connectorsList) {
		// TODO Auto-generated method stub
		System.out.println("******************************************************************************************************************************************************************************");
		System.out.println("************************************************************** System Description ********************************************************************************");
		System.out.println("******************************************************************************************************************************************************************************");
		
		
		for( int i = 0; i < components_arraylist.size();i++) {
			System.out.println(components_arraylist.get(i).toString());
		}
		
		for(int  i = 0; i < connectorsList.size();i++) {
			System.out.println(connectorsList.get(i).toString()	);
		}
		
		
		System.out.println("******************************************************************************************************************************************************************************");
		System.out.println("*********************************************************************************************************************************************************");
		System.out.println("******************************************************************************************************************************************************************************");

		
	}
}
