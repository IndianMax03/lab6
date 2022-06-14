package base;

import java.io.Serializable;

public class Coordinates implements Serializable {

	private Double x; //Поле не может быть null
	private Double y; //Значение поля должно быть больше -628, Поле не может быть null

	public Coordinates(Double x, Double y){
		setX(x);
		setY(y);
	}

	@Override
	public String toString() {
		return this.x + ";" + this.y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}


}
