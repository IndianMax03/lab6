package base;

public enum Climate {

	HUMIDCONTINENTAL("Влажный континентальный"),
	SUBARCTIC("Субарктический"),
	TUNDRA("Тундра");

	private final String title;

	Climate(String title){
		this.title = title;
	}

	@Override
	public String toString() { return title;}

}

