package BIP_tools;

import java.io.File;

public class Guard {
	private String name;
	private String guardMethod;


	private Component relatedComponent; 
	private Enforceable srcCmpTransition; 
	private Enforceable dstCmpTransition; 
	
	public Guard(String name, String guardMethod, Component relatedComponent) {

		this.name = name;
		this.guardMethod = guardMethod;
		this.relatedComponent = relatedComponent; 
	}

	public Component getRelatedComponent() {
		return relatedComponent;
	}

	public void setRelatedComponent(Component relatedComponent) {
		this.relatedComponent = relatedComponent;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGuardMethod() {
		return guardMethod;
	}
	public void setGuardMethod(String guardMethod) {
		this.guardMethod = guardMethod;
	} 
	
	
	public Enforceable getSrcCmpTransition() {
		return srcCmpTransition;
	}

	public void setSrcCmpTransition(Transition srcCmpTransition) {
		this.srcCmpTransition = (Enforceable) srcCmpTransition;
	}

	public Enforceable getDstCmpTransition() {
		return dstCmpTransition;
	}

	public void setDstCmpTransition(Transition dstCmpTransition) {
		this.dstCmpTransition = (Enforceable) dstCmpTransition;
	}
	@Override
	public String toString() {
		String S =""; 
		S += "\n\tGuard name: " + name;
		S += "\n\t\tGuard cmp name: " + this.getRelatedComponent().getName();
		S += "\n\t\tTransition (src comp): " + srcCmpTransition.getTransition_name();
		S +="\n";
		return S; 
		
	//	return "Guard [name=" + name + ", guardMethod=" + guardMethod +", srcCmpTransition=" + srcCmpTransition +  "]";
	}


	
}
