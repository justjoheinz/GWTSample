package de.oose.gwtsample.client;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.oose.gwtsample.shared.Pair;

@RemoteServiceRelativePath("prime")
public interface PrimeService extends RemoteService{
	
	public Pair<Integer, Boolean> isPrime(Integer i);

}
