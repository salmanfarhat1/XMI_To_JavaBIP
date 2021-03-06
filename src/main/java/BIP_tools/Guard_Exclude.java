package BIP_tools;

public class Guard_Exclude extends Guard{
	public State srcState;
	public State dstState;
	public Component srcComponent; 
	public Component dstComponent;
	
	
	public Guard_Exclude(String name, String guardMethod, Component parent,  State srcState, State dstState, Component srcComponent,
			Component dstComponent) {
		super(name, guardMethod, parent);
		this.srcState = srcState;
		this.dstState = dstState;
		this.srcComponent = srcComponent;
		this.dstComponent = dstComponent;
	}


	public State getSrcState() {
		return srcState;
	}


	public void setSrcState(State srcState) {
		this.srcState = srcState;
	}


	public State getDstState() {
		return dstState;
	}


	@Override
	public String toString() {
		String S  = "";

		S += "\n\tGuard name: " + super.getName();
		S += "\n\t\tGuard cmp name: " + this.getRelatedComponent().getName();
		S += "\n\t\tSource state: " + srcState.getState_name();
		S += "\n\t\tDest state: " + dstState.getState_name();
		S += "\n\t\tSource state: " + srcComponent.getName();
		S += "\n\t\tDest Component: " + dstComponent.getName();
		S += "\n\t\tTransition (src comp): " + getSrcCmpTransition().getTransition_name();
		S += "\n\t\tTransition (dst comp): " + getDstCmpTransition().getTransition_name();
		S +="\n";
				
		return S;
				
//				"Guard_Exclude [srcState=" + srcState + ", dstState=" + dstState + ", srcComponent=" + srcComponent.getName()
//				+ ", dstComponent=" + dstComponent.getName() +" Parent name "+ this.getRelatedComponent().getName() + "]";
		
	}


	public void setDstState(State dstState) {
		this.dstState = dstState;
	}


	public Component getSrcComponent() {
		return srcComponent;
	}


	public void setSrcComponent(Component srcComponent) {
		this.srcComponent = srcComponent;
	}


	public Component getDstComponent() {
		return dstComponent;
	}


	public void setDstComponent(Component dstComponent) {
		this.dstComponent = dstComponent;
	}
	


}
