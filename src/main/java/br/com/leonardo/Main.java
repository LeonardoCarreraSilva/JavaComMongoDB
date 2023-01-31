package br.com.leonardo;

public class Main {

	public static void main(String[] args) {
		CRUDMongo crud = new CRUDMongo();
		System.out.println("Find-----------------------------");
		System.out.println(crud.doFind());
		System.out.println();
		
		System.out.println("Insert---------------------------");
		System.out.println(crud.doInsert("Jose Antonio"));
		System.out.println();
		
		System.out.println("Update---------------------------");
		System.out.println(crud.doUpdate("Jose Antonio", "Jose Antonio Silva"));
		System.out.println();
		
		System.out.println("Delete---------------------------");
		crud.doDelete("Jose Antonio Silva");
		System.out.println(crud.doFindByName("Jose Antonio Silva"));
	}

}
