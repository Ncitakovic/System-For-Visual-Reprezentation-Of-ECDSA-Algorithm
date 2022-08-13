package ecdsa_app.control.ecdsa;

import java.math.BigInteger;

public class Signature {
	BigInteger r;
	BigInteger s;
	
	public Signature(BigInteger r, BigInteger s) {
		this.r = r;
		this.s = s;
	}

	public BigInteger getR() {
		return r;
	}

	public BigInteger getS() {
		return s;
	}
	
	
	
}
