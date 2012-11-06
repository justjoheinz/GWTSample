package de.oose.gwtsample.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.oose.gwtsample.client.PrimeService;
import de.oose.gwtsample.shared.Pair;

public class PrimeServiceImpl extends RemoteServiceServlet implements PrimeService {

	@Override
	public Pair<Integer, Boolean> isPrime(Integer i) {
		List<Integer> result = Prime.generatePrimes(i);
		if (result.size() == 0) return new Pair<Integer, Boolean>(i, false);
		int max = result.get(result.size() -1 );
		return new Pair<Integer, Boolean>(i,max == i);
	}

}
