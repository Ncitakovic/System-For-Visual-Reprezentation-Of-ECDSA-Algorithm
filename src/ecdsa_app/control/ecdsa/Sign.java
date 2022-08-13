package ecdsa_app.control.ecdsa;

import java.math.*;
import java.security.SecureRandom;

public class Sign {

	private EllipticCurve EC;
	private ECPoint G;
	private BigInteger n;
	private PrivateKey PK;
	private String msg;

	private BigInteger h;
	private BigInteger k;
	private ECPoint kG;

	public Sign(EllipticCurve EC, ECPoint G, BigInteger n, PrivateKey PK, String msg) {
		this.EC = EC;
		this.G = G;
		this.n = n;
		this.PK = PK;
		this.msg = msg;
	}

	public Signature signing() {
		BigInteger kinv, r, s = null;

		SecureRandom rnd = new SecureRandom();
		do {
			do {
				k = new BigInteger(n.bitLength(), rnd);
			} while (k.compareTo(n) >= 0 || k.equals(new BigInteger("0")));

			kG = EC.point_multiplication(k, G, EC);
			r = (kG.getX()).mod(EC.getP());
		} while (r.equals(new BigInteger("0")));

		kinv = k.modInverse(n);

		String s2 = SHA1.hash(msg);
		h = new BigInteger(s2, 16);
		System.out.println("\nmessage digest: " + s2);
		
		BigInteger privateKey = PK.getPrivateKey();
		s = (kinv.multiply(h.add(privateKey.multiply(r)))).mod(n);

		Signature signature = new Signature(r, s);

		System.out.println("r: " + r);
		System.out.println("s: " + s);

		return signature;
	}

	public EllipticCurve getEC() {
		return EC;
	}

	public ECPoint getG() {
		return G;
	}

	public BigInteger getN() {
		return n;
	}

	public PrivateKey getPK() {
		return PK;
	}

	public String getMsg() {
		return msg;
	}

	public BigInteger getH() {
		return h;
	}

	public BigInteger getK() {
		return k;
	}

	public ECPoint getkG() {
		return kG;
	}

}
