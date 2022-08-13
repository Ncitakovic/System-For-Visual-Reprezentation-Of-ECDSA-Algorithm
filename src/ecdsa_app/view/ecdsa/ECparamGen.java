package ecdsa_app.view.ecdsa;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

import ecdsa_app.control.ecdsa.ECPoint;
import ecdsa_app.control.ecdsa.EllipticCurve;

public class ECparamGen {

	private long a = -3;
	private long b = 7;
	private long p = 11;

	private LinkedList<Long> listL = new LinkedList<>();
	private LinkedList<Long> listR = new LinkedList<>();
	private HashMap<String,BasePointAndN> hashOfPossibleGandN = new HashMap<String,BasePointAndN>();
	
	class Point {
		long x;
		long y;

		Point(long i, long j) {
			x = i;
			y = j;
		}
	}

	private LinkedList<Point> points = new LinkedList<>();
	int i, j;
	
	
	public ECparamGen(long a, long b, long p){
		this.a = a;
		this.b = b;
		this.p = p;
		fillArrayL();
		fillArrayR();
		checkIfLiesOnCurve();
		detectN();
	}

	void fillArrayL() {
		for (i = 0; i < p; ++i)
			listL.add((i * i) % p);

	}

	void fillArrayR() {
		for (i = 0; i < p; ++i)
			listR.add((i * i * i + a * i + b) % p);
	}

	private void checkIfLiesOnCurve() {
		for (i = 0; i < p; ++i)
			for (j = 0; j < p; ++j)
				if (Objects.equals(listL.get(i), listR.get(j)))
					points.add(new Point(i, j));
	}
	
	void detectN() {
		EllipticCurve EC = new EllipticCurve(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(p));
		int x = 1;
		for(int i=0; i < points.size(); i++) {
			Point p = (Point) points.get(i);
			int n=1;
			ECPoint ecP = new ECPoint( BigInteger.valueOf(p.y), BigInteger.valueOf(p.x));
			ECPoint res = new ECPoint(BigInteger.valueOf(p.y), BigInteger.valueOf(p.x));
			try {
			do {
				res = EC.point_add(res, ecP);
				n++;
			}while(!res.isPoint_at_infinity());
			
			}catch(Exception e) {
				System.out.print("elem not valid");
				n++;
			}
			System.out.println(n+ " "+ x++);
			if(isPrime(n) && (n!=2)) {
				System.out.println("***** " + p.y +" , " + p.x +", n="+n+"   *****");
				BasePointAndN b = new BasePointAndN(p.y,p.x,n);
				hashOfPossibleGandN.put("("+p.y+", "+p.x+")", b);				
			}
		}
		
	}
	
	public HashMap<String,BasePointAndN> getHashOfPossibleGandN() {
		return hashOfPossibleGandN;
	}
	
	public boolean isPrime(int number) {
	    BigInteger bigInt = BigInteger.valueOf(number);
	    return bigInt.isProbablePrime(100);
	}
	
	public LinkedList<Point> getPoints() {
		return points;
	}
	
	
	
}
