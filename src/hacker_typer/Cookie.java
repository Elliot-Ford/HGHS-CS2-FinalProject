package hacker_typer;

public class Cookie {
	double numberOfCookies;
	double lastAmountOfCookies;
	int lastSec;
	double moreCookies;
	double cookiesPerSec;

	public Cookie() {
		this(0);
	}

	public Cookie(int numberOfCookies) {
		super();
		this.numberOfCookies = numberOfCookies;
	}

	public int getNumberOfCookies() {
		return (int) numberOfCookies;
	}

	public void setNumberOfCookies(int numberOfCookies) {
		this.numberOfCookies = numberOfCookies;
	}

	public double getCookiesPerSec() {
		if (getSeconds() - lastSec != 0) {
			double retX = (double) Math
					.round((numberOfCookies - lastAmountOfCookies) * 1000) / 1000;

			lastAmountOfCookies = numberOfCookies;
			lastSec = getSeconds();
			cookiesPerSec = retX;
			return retX;
		}
		return cookiesPerSec;

	}

	public void increaseCookies() {
		numberOfCookies += moreCookies;
	}

	public void setMoreCookies(double numberToIncreaseBy) {
		moreCookies += numberToIncreaseBy;
	}

	public void keyPressed() {
		numberOfCookies += .025;
	}

	private int getSeconds() {
		return (int) ((int) System.currentTimeMillis() * Math.pow(10, -3));
	}

}
