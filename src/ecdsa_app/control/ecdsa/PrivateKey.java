package ecdsa_app.control.ecdsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PrivateKey {
	BigInteger n = new BigInteger("6277101735386680763835789423176059013767194773182842284081");
	SecureRandom rnd = new SecureRandom();
	BigInteger r = new BigInteger("3277101735386680763835789423176059013767194773182842284081");

	public PrivateKey() {
	}

	public void generatePrivateKey() {
		do {
			r = new BigInteger(n.bitLength(), rnd);
		} while (r.compareTo(n) >= 0 || r.equals(new BigInteger("0")));
	}

	public BigInteger getPrivateKey() {
		return r;
	}

	public void setN(BigInteger n) {
		this.n = n;
	}

}
