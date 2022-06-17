package server;

import base.City;
import base.Government;
import fileworker.WorkWithFile;
import listening.Response;

import java.time.ZonedDateTime;
import java.util.TreeSet;

/**
 * Responsible for the implementation of commands
 */
public class Receiver {

	private long ID = 1L;
	private TreeSet<City> collection = new TreeSet<>();
	private Response response = new Response();
	private final ZonedDateTime creationDate;
	public Receiver(){
		creationDate = ZonedDateTime.now();
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
		city.setId(this.ID++);
		city.setCreationDate(ZonedDateTime.now());
		if (!collection.add(city)) {
			response.setMessage("Город добавить не удалось. Коллекция TreeSet не предполагает хранение одинаковых объектов.");
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
				collection.stream()
				.map(City::cityToShow)
				.toArray(String[]::new)
		);
		return response;
	}

	public Response filterStartsWithName(String filter){
		if (collection.isEmpty()){
			response.setMessage("Коллекция пуста. Команда не может выполниться.");
			return response;
		}
		String[] reaction = collection.stream()
				.filter(city -> city.getName().startsWith(filter))
				.map(City::toString)
				.toArray(String[]::new);
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
		response.setAnswer(( collection.stream()
					.sorted((city1, city2) -> -city1.compareTo(city2))
					.map(City::toString)
					.toArray(String[]::new)
		));
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
		Government government = null;
		for (Government gov : Government.values()){
			if (gov.toString().equals(argument))
				government = gov;
		}
		if (government == null){
			response.setMessage("Обнаружена ошибка при попытке удаления. Сервер отказывается обрабатывать.");
			return response;
		}
		long before = collection.size();
		Government finalGovernment = government;
		collection.removeIf(city -> city.getGovernment().equals(finalGovernment));
		long after = collection.size();
		if (before == after){
			response.setMessage("В коллекции не найдены подходящие элементы.");
		} else{
			response.setMessage("Удаление элементов прошло успешно.");
		}
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
		City maybeDel = null;
		long before = collection.size();
		for (City sity : collection){
			if (sity.getId().equals(id)){
				collection.remove(sity);
				maybeDel = sity;
				break;
			}
		}
		long after = collection.size();
		if (before == after) {
			response.setMessage("Элемента с заданным id не существует. Город не будет обновлён.");
			return response;
		}
		city.setId(id);
		city.setCreationDate(ZonedDateTime.now());
		if (!collection.add(city)) {
			response.setMessage("Город не удалось изменить. Коллекция TreeSet не предполагает хранение одинаковых объектов.");
			collection.add(maybeDel);
		} else {
			response.setMessage("Город с id = " + id + " успешно изменён.");
		}
		return response;
	}

	public Response save(){
		WorkWithFile worker = new WorkWithFile();
		response.setMessage("Сообщение от сервера: " + worker.writeInFile(collection));
		return response;
	}

	public void clearResponse(){
		this.response.setMessage(null);
		this.response.setAnswer(null);
	}

	public TreeSet<City> getCollection(){
		return this.collection;
	}

}
