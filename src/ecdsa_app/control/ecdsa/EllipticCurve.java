package ecdsa_app.control.ecdsa;

import java.math.*;

public class EllipticCurve {
	private BigInteger a, b, prime_p;

	public EllipticCurve(BigInteger value_a, BigInteger value_b, BigInteger value_p) {
		a = value_a;
		b = value_b;
		prime_p = value_p;
	}

	BigInteger getA() {
		return this.a;
	}

	BigInteger getB() {
		return this.b;
	}

	BigInteger getP() {
		return this.prime_p;
	}

	// Elliptic Curve Point Addition method

	public ECPoint point_add(ECPoint P, ECPoint Q) {
		ECPoint R = new ECPoint(BigInteger.ZERO, BigInteger.ZERO);
		BigInteger lambda, x, y, temp;

		if (((P.point_at_infinity) && (Q.point_at_infinity))
				|| (P.getX().equals(Q.getX()) && P.getY().equals(Q.getY().negate()))) {
			R.point_at_infinity = true;

		} else {
			// if two points are equal
			if ((P.getX().equals(Q.getX())) && (P.getY().equals(Q.getY()))) {

				temp = new BigInteger("3");
				lambda = ((temp.multiply(P.getX().pow(2))).add(this.a))
						.multiply((P.getY().add(P.getY())).modInverse(this.prime_p));
				x = (lambda.multiply(lambda)).subtract(P.getX().add(Q.getX()));
				y = (lambda.multiply(P.getX().subtract(x))).subtract(P.getY());

				x = x.mod(this.prime_p);
				y = y.mod(this.prime_p);

				R = new ECPoint(x, y);

			} else {
				// if two points are distinct
				if (P.point_at_infinity) {
					x = Q.getX();
					y = Q.getY();
					R = new ECPoint(x, y);
				} else if (Q.point_at_infinity) {
					x = P.getX();
					y = P.getY();
					R = new ECPoint(x, y);
				} else {
					temp = (Q.getX().subtract(P.getX())).modInverse(this.prime_p);
					lambda = (Q.getY().subtract(P.getY())).multiply(temp);

					x = (lambda.multiply(lambda)).subtract(P.getX().add(Q.getX()));
					y = (lambda.multiply(P.getX().subtract(x))).subtract(P.getY());

					x = x.mod(this.prime_p);
					y = y.mod(this.prime_p);

					R = new ECPoint(x, y);

				}
			}
		}
		return R;
	}

	// Elliptic Curve Point Multiplication method
	ECPoint point_multiplication(BigInteger d, ECPoint P, EllipticCurve E1) {

		String d_binary = d.toString(2);
		ECPoint Q = new ECPoint(BigInteger.ZERO, BigInteger.ZERO);
		Q.point_at_infinity = true;

		/*
		 * if(d_binary.substring(0,1).equals("1")){ Q = P; }
		 */

		for (int i = 0; i < d_binary.length(); i++) {
			Q = E1.point_add(Q, Q);

			if (d_binary.substring(i, i + 1).equals("1")) {
				Q = E1.point_add(Q, P);
			}
		}
		return Q;
	}

	// Point checking
	void point_check(ECPoint P) {
		BigInteger k1 = (P.getY().pow(2)).mod(this.prime_p);
		BigInteger k2 = ((P.getX().pow(3)).add((this.a).multiply(P.getX())).add(this.b)).mod(this.prime_p);
		if (k1.compareTo(k2) == 0) {
			System.out.println("The point is within the curve");
		} else
			System.out.println("The point is not within the curve");
	}
}
