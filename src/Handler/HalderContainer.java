package Handler;

import java.util.ArrayList;
import java.util.List;

public class HalderContainer implements HandlerInterface{
	private List<HandlerInterface> handlerList=new ArrayList<>();

	@Override
	public boolean markerCheckClick(float mouseX,float mouseY) {
		
		for(HandlerInterface handler:handlerList) {
			if(handler.markerCheckClick( mouseX, mouseY)) {
				return true;
			}
		}
		return false;
		
	//	handlerList.stream().forEach(a->a.markerCheckClick( mouseX, mouseY));
		
		
	}
	 public void addHandler(HandlerInterface handler) {
	        handlerList.add(handler);
	}

}
