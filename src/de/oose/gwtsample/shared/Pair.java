package de.oose.gwtsample.shared;

import java.io.Serializable;

public class Pair<L extends Serializable ,R extends Serializable> implements Serializable {

	  public  L left;
	  public  R right;
	  
	  public Pair() {
		  left =null;
		  right = null;
	  }

	  public Pair(L left, R right) {
	    this.left = left;
	    this.right = right;
	  }

	  @Override
	  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

	  @Override
	  public boolean equals(Object o) {
	    if (o == null) return false;
	    if (!(o instanceof Pair)) return false;
	    Pair pairo = (Pair) o;
	    return this.left.equals(pairo.left) &&
	           this.right.equals(pairo.right);
	  }

	}
