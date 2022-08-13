package ecdsa_app.control.ecdsa;

import java.math.*;

public class Verify {
	PublicKey PU;
	BigInteger n;
	String msg;
	Signature signature;

	BigInteger w, u1, u2, v = null;
	ECPoint R, R1, R2;
	BigInteger h;

	public Verify(PublicKey PU, BigInteger n, Signature signature, String msg) {
		this.PU = PU;
		this.n = n;
		this.signature = signature;
		this.msg = msg;
	}

	public String verification() {
		BigInteger r = signature.getR();
		BigInteger s = signature.getS();

		w = s.modInverse(n);


		String s2 = SHA1.hash(msg);
		h = new BigInteger(s2, 16);
		System.out.println("\nmessage digest: " + s2);

		// calculation of u1 and u2
		u1 = (h.multiply(w)).mod(n);
		u2 = (r.multiply(w)).mod(n);

		// calculation of u1*G+u2*PU
		R1 = PU.EC.point_multiplication(u1, PU.G, PU.EC);
		R2 = PU.EC.point_multiplication(u2, PU.getPublicKey(), PU.EC);
		R = PU.EC.point_add(R1, R2);

//		v = (R.getX()).mod(n);
		v = (R.getX());

		// checking
		System.out.println("v: " + v);
		System.out.println("r: " + r);

		if (v.compareTo(r) == 0) {
			return "\nThe Signature is Valid.";
		} else {
			return "\nThe Signature is Invalid.";
		}
	}

	public BigInteger getW() {
		return w;
	}

	public BigInteger getU1() {
		return u1;
	}

	public BigInteger getU2() {
		return u2;
	}

	public BigInteger getV() {
		return v;
	}

	public BigInteger getH() {
		return h;
	}

	public BigInteger getN() {
		return n;
	}

	public PublicKey getPU() {
		return PU;
	}

	public ECPoint getR() {
		return R;
	}

	public ECPoint getR1() {
		return R1;
	}

	public ECPoint getR2() {
		return R2;
	}

	public String getMsg() {
		return msg;
	}
}
