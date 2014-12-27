package edu.rit.notification.poller;

import java.util.List;

public interface Pollable<T> {	
	public void poll();
	
	public void receive(List<T> c);
	
}
