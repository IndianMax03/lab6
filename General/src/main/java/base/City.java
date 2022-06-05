package base;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class City implements Comparable<City>, Serializable {

	private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
	private String name; //Поле не может быть null, Строка не может быть пустой
	private Coordinates coordinates; //Поле не может быть null
	private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
	private float area; //Значение поля должно быть больше 0
	private int population; //Значение поля должно быть больше 0
	private float metersAboveSeaLevel;
	private Climate climate; //Поле не может быть null
	private Government government; //Поле не может быть null
	private StandardOfLiving standardOfLiving; //Поле может быть null
	private Human governor; //Поле не может быть null

	public City(Long id, String name, Coordinates coordinates, ZonedDateTime creationDate, float area, int population,
	            float metersAboveSeaLevel, Climate climate, Government government, StandardOfLiving standardOfLiving,
	            Human governor){
		setId(id);
		setName(name);
		setCoordinates(coordinates);
		setCreationDate(creationDate);
		setArea(area);
		setPopulation(population);
		setMetersAboveSeaLevel(metersAboveSeaLevel);
		setClimate(climate);
		setGovernment(government);
		setStandardOfLiving(standardOfLiving);
		setGovernor(governor);

	}

	@Override
	public int compareTo(City city){
		return population - city.getPopulation();
	}

	@Override
	public String toString() {
		return name;
	}

	public String cityToString(){
		return this.id+","+this.name+","+this.coordinates+","+this.creationDate+","+this.area+","+this.population+","+
				this.metersAboveSeaLevel+","+this.climate+","+this.government+","+this.standardOfLiving+","+this.governor;
	}

	public String cityToShow(){
		return "Город: \"" + name + "\", id: " + id + ";";
	}

	/**
	 *
	 * Getters and Setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public ZonedDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(ZonedDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public float getMetersAboveSeaLevel() {
		return metersAboveSeaLevel;
	}

	public void setMetersAboveSeaLevel(float metersAboveSeaLevel) {
		this.metersAboveSeaLevel = metersAboveSeaLevel;
	}

	public Climate getClimate() {
		return climate;
	}

	public void setClimate(Climate climate) {
		this.climate = climate;
	}

	public Government getGovernment() {
		return government;
	}

	public void setGovernment(Government government) {
		this.government = government;
	}

	public StandardOfLiving getStandardOfLiving() {
		return standardOfLiving;
	}

	public void setStandardOfLiving(StandardOfLiving standardOfLiving) {
		this.standardOfLiving = standardOfLiving;
	}

	public Human getGovernor() {
		return governor;
	}

	public void setGovernor(Human governor) {
		this.governor = governor;
	}

}
