package de.oose.gwtsample.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.oose.gwtsample.shared.Pair;

public interface PrimeServiceAsync {

	void isPrime(Integer i, AsyncCallback<Pair<Integer, Boolean>> callback);
	

}
