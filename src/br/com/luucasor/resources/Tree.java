package br.com.luucasor.resources;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.luucasor.dao.NodeDao;
import br.com.luucasor.model.Node;

@Path("/tree")
public class Tree {

	@GET
	@Produces("text/json")
	public Response getTree() {
		Collection<Node> tree = new NodeDao().findAllNodes();
		String treeView = new Gson().toJson(tree);
		return Response.ok(treeView).build();
	}
	
	@GET @Path("/{id}")
	@Produces("text/json")
	public Response getNode(@PathParam("id") Long id) {
		Node node = new NodeDao().findById(id);
		String nodeView = new Gson().toJson(node);
		return Response.ok(nodeView).build();
	}
	
	@DELETE @Path("/{id}")
	@Produces("text/json")
	public Response deleteNode(@PathParam("id") Long id) {
		Boolean deleted = new NodeDao().delete(id);
		if(deleted)
			return Response.ok("Deletado com sucesso").build();
		
		return Response.ok("Não encontrado").build();
	}
	
	@PUT
	@Produces("text/json")
	public Response insertUpdate(String json){
		Gson gson = new Gson();
		Node node = gson.fromJson(json, Node.class);
		String prefix = node.getId() != null ? "Alterado" : "Criado";
		Long id = new NodeDao().save(node);
		return Response.ok(prefix+" nó de id = "+id).build();
	}
}
