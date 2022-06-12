package server;

import base.City;
import base.Government;
import commands.Command;
import fileworker.WorkWithFile;
import input.Adder;
import listening.Response;

import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Responsible for the implementation of commands
 */
public class Receiver {

	private TreeSet<City> collection;
	private Response response;
	private final ZonedDateTime creationDate;


	public Receiver(){
		creationDate = ZonedDateTime.now();
	}

	public String exit(){
		System.out.println("Спасибо за работу, до свидания!");
		return null;
	}

	public Response clear(){
		if (!collection.isEmpty()){
			collection.clear();
			response.setMessage("Коллекция успешно очищена.");
		} else {
			response.setMessage("Колллекция уже пуста.");
		}
		return response;
	}

	public Response add(City city){
		if (!collection.add(city)) {
			response.setMessage("Город добавить не удалось");
		} else {
			response.setMessage("Город успешно добавлен в коллецию.");
		}
		return response;
	}

	public Response addIfMin(City city){
		if (collection.isEmpty()){
			response.setMessage("Коллекция пуста. Создайте хотя бы один элемент, чтобы использовать эту команду.");
		}
		if (city.getPopulation() < collection.first().getPopulation()){
			collection.add(city);
			response.setMessage("Город успешно добавлен в коллекцию.");
		} else {
			response.setMessage("Город не удалось добавить. Его значение поля population превосходит наименьшее.");
		}
		return response;
	}

	public Response show(){
		if (collection.isEmpty()) {
			response.setMessage("Коллекция пуста.");
			return response;
		}
		response.setMessage("Элементы коллекции в строковом представлении:");
		response.setAnswer(
				(String[]) collection.stream()
				.map(City::cityToShow)
				.toArray()
		);
		return response;
	}

	public Response filterStartsWithName(String filter){
		if (collection.isEmpty()){
			response.setMessage("Коллекция пуста. Команда не может выполниться.");
			return response;
		}
		String[] reaction = (String[]) collection.stream()
				.filter(city -> city.getName().startsWith(filter))
				.toArray();
		response.setMessage("Элементы коллекции, начинающиеся с подстроки: " + filter);
		response.setAnswer(reaction);
		return response;
	}

	public Response printDescending(){
		if (collection.isEmpty()){
			response.setMessage("Коллекция пуста.");
			return response;
		}
		response.setMessage("Элементы коллекции в обратном порядке: ");
		response.setAnswer((String[]) collection.stream()
					.sorted((city1, city2) -> -city1.compareTo(city2))
					.toArray()
		);
		return response;
	}

	//  todo Разобраться с терминалом
//	public String executeScript(Invoker invoker, TreeSet<City> collection, String filename) throws FileNotFoundException {
//
//		Terminal terminal = new Terminal(invoker, collection);
//
//		return terminal.startFile(filename);
//
//	}

	public Response removeAllByGovernment(String argument){

		for (Government government : Government.values()){

			if (government.toString().equals(argument)){

				boolean flag = false;

				for (City city : collection){
					if (city.getGovernment().equals(government)){
						collection.remove(city);
						flag = true;
					}
				}
				if (flag) {
					response.setMessage("Элементы коллекции с заданным условием удалены.");
					return response;
				} else {
					response.setMessage("Элементов коллекции с заданным полем government не найдено.");
					return response;
				}
			}

		}
		response.setMessage("Такого поля Government не существует.");
		return response;
	}

	public Response removeById(String argument){
		long id = Long.parseLong(argument);
		boolean flag = false;
		for (City city : collection){
			if (city.getId().equals(id)){
				flag = true;
				collection.remove(city);
				break;
			}
		}
		if (!flag){
			response.setMessage("Города с заданным id не существует.");
			return response;
		}
		response.setMessage("Город с заданным id успешно удалён.");
		return response;
	}

	public Response removeGreater(City city){
		if (collection.isEmpty()){
			response.setMessage("Коллекция пуста.");
			return response;
		}
		collection.removeIf(sity -> sity.getPopulation() > city.getPopulation());
		response.setMessage("Элементы коллекции, превышающие заданный, удалены.");
		return response;
	}

	public Response removeLower(City city){
		if (collection.isEmpty()){
			response.setMessage("Коллекция пуста.");
			return response;
		}
		collection.removeIf(sity -> sity.getPopulation() < city.getPopulation());
		response.setMessage("Элементы коллекции, меньшие чем заданный, удалены.");
		return response;
	}


	public Response info(){
		response.setMessage("Тип коллекции: " + collection.getClass() +
				"\nДата инициализации коллекции: " + creationDate +
				"\nКоличество элементов коллекции: " + collection.size());
		return response;
	}

	public Response updateId(String idArgument, City city){
		long id = Long.parseLong(idArgument);
		long before = collection.size();
		collection.removeIf(sity -> city.getId().equals(id));
		long after = collection.size();
		if (before == after){
			response.setMessage("Элемента с заданным id не существует. Город не будет добавлен.");
			return response;
		}
		city.setId(id);
		return add(city);
	}

	public String save(TreeSet<City> collection){

		WorkWithFile worker = new WorkWithFile();

		return worker.writeInFile(collection);

	}

	public void clearResponse(){
		this.response.setMessage(null);
		this.response.setAnswer(null);
	}

}
