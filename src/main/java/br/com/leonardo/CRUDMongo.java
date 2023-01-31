package br.com.leonardo;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class CRUDMongo {
	MongoClient mongoClient = new MongoClient();
	MongoDatabase bancoDeDados = mongoClient.getDatabase("Escola");
	MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");

	public String doFind() {
		Document first = alunos.find().first();
		return first.toString();
	}
	
	public String doFindByName(String nome) {
		String retorno;
		try {
			Document first = alunos.find(Filters.eq("nome", nome)).first();
			retorno = first.toString();
		}catch (Exception e) {
			System.out.println("Realmente deletou o caboclo");
			retorno = "";
		}
		return retorno;
	}
	
	
	public String doInsert(String nome) {
		Document novoAluno = new Document("nome", nome)
				  .append("data_nascimento", new Date(2003, 10, 10))
				  .append("curso", new Document("nome", "Historia"))
				  .append("notas", Arrays.asList(10, 9, 8))
				  .append("habilidades", Arrays.asList(
				          new Document().append("nome", "Ingles").append("nível", "Básico"),
				          new Document().append("nome", "Espanhol").append("nível", "Básico")));
		
		alunos.insertOne(novoAluno);
		Document first = alunos.find(Filters.eq("nome", nome)).first();
		return first.toString().stripIndent();
	}
	
	public String doUpdate(String nome, String novoNome) {
		alunos.updateOne(Filters.eq("nome", nome), new Document("$set", new Document("nome", novoNome)));
		return alunos.find(Filters.eq("nome", novoNome)).first().toJson();
	}
	
	public void doDelete(String nome) {
		alunos.deleteOne(Filters.eq("nome", nome));
	}
}
