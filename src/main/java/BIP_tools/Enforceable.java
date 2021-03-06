package BIP_tools;

public class Enforceable extends Transition{
	protected Boolean synchron; 

	public Enforceable(String transition_name, State src, State dst, Boolean synchron, Component componentOfTheTransition) {
		super(transition_name, src, dst, componentOfTheTransition);
		this.synchron = synchron; 
	}

	public Boolean getSynchron() {
		return synchron;
	}

	public void setSynchron(Boolean synchron) {
		this.synchron = synchron;
	}

	@Override
	public String toString() {
//		return "\nEnforceable Transition \n\tTransition_name : "+ super.getTransition_name() +"\n\tCorrespond to the component : "+ super.getComponentOfTheTransition().getName()  +"\n\tIs Synchron : "+ super.getSynchron()+ "\n\tSrc : " + super.getSrc() + " \n\tDst : " + super.getDst();
		return "\t\tEnforceable Transition {Transition_name : "+ super.getTransition_name() +", For component : "+ super.getComponentOfTheTransition().getName()  +", Synchron : "+ this.getSynchron()+ ", Src : " + super.getSrc() + ", Dst : " + super.getDst()+ "}\n";

	}




}
