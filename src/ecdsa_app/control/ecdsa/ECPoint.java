package ecdsa_app.control.ecdsa;

import java.math.*;

public class ECPoint {
	private BigInteger x, y;
	boolean point_at_infinity;

	public ECPoint(BigInteger value_x, BigInteger value_y) {
		x = value_x;
		y = value_y;
		point_at_infinity = false;
	}

	public BigInteger getX() {
		return x;
	}

	public BigInteger getY() {
		return y;
	}

	public boolean isPoint_at_infinity() {
		return point_at_infinity;
	}

	boolean ECPoint_equals(ECPoint P) {
		if (this.x.equals(P.getX()) && this.y.equals(P.getY())) {
			return true;
		} else
			return false;
	}

	@Override
	public String toString() {
		return "(" + getX().toString() + ", " + getY().toString() + ")";
	}

}
