package ecdsa_app.view.ecdsa;

public class BasePointAndN {
	long Gx;
	long Gy;
	long n;
	
	
	
	public BasePointAndN(long gx, long gy, long n) {
		super();
		Gx = gx;
		Gy = gy;
		this.n = n;
	}
	public long getGx() {
		return Gx;
	}
	public long getGy() {
		return Gy;
	}
	public long getN() {
		return n;
	}
	
	
}
