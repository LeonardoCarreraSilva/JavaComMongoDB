package br.com.leonardo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
		} catch (Exception e) {
			System.out.println("Realmente deletou o caboclo");
			retorno = "";
		}
		return retorno;
	}

	public String doInsert(String nome) {
		Date dataNascimento = new Date(2003, 10, 10);
		Document curso = new Document("nome", "Historia");
		List<Integer> notas = Arrays.asList(10, 9, 8);
		List<Document> habilidades = Arrays.asList(new Document("nome", "Inglês")
														.append("nível", "Básico"),
				new Document("nome", "Espanhol")
				.append("nível", "Básico"));

		Document novoAluno = new Document("nome", nome)
				.append("data_nascimento", dataNascimento)
				.append("curso", curso)
				.append("notas", notas)
				.append("habilidades", habilidades);

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
