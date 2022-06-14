package base;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Human implements Serializable {

	private String name; //Поле не может быть null, Строка не может быть пустой
	private Integer height; //Значение поля должно быть больше 0
	private ZonedDateTime birthday;

	public Human(String name, Integer height, ZonedDateTime birthday){
		setName(name);
		setHeight(height);
		setBirthday(birthday);
	}

	/**
	 * Human factory
	 */
	public static Human randomHuman(String name){
		int height = ((int) (Math.random() * 200)) + 1;
		ZonedDateTime birthday = ZonedDateTime.now();
		return new Human(name, height, birthday);
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * Getters and Setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public ZonedDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(ZonedDateTime birthday) {
		this.birthday = birthday;
	}


}
