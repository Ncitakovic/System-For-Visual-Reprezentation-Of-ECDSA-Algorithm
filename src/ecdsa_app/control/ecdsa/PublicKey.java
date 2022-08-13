package ecdsa_app.control.ecdsa;

import java.math.*;

public class PublicKey {

	public EllipticCurve EC;
	public ECPoint G, Q;
	public BigInteger n;

	private BigInteger d; // private key

	public PublicKey(EllipticCurve EC, ECPoint G, BigInteger n, BigInteger d) {
		this.EC = EC;
		this.G = G;
		this.n = n;
		this.d = d;

		Q = EC.point_multiplication(d, G, EC);

	}

	public ECPoint getPublicKey() {
		return Q;
	}
}
