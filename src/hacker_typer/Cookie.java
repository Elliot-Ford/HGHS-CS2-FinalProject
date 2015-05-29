package hacker_typer;

public class Cookie {
	int numberOfCookies;

	public Cookie() {
		this(0);
	}

	public Cookie(int numberOfCookies) {
		super();
		this.numberOfCookies = numberOfCookies;
	}

	public int getNumberOfCookies() {
		return numberOfCookies;
	}

	public void setNumberOfCookies(int numberOfCookies) {
		this.numberOfCookies = numberOfCookies;
	}

	public double getCookiesPerSec() {
		return CookiesPerSec;
	}

	double CookiesPerSec;

}
