package dao;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.luucasor.dao.NodeDao;
import br.com.luucasor.model.Node;

public class NodeDaoAllTest {
	
	Node node1 = null;
	Node node2 = null;
	Node node3 = null;
	ArrayList<Node> nodes = new ArrayList<Node>();
	
	@Before
	public void setUp(){
		node1 = new Node();
		node1.setCode(001);
		node1.setDescription("Casa");
		node1.setNote("Nó criado para casa");
		node1.setParent(null);
		node1.setId(new NodeDao().save(node1));
		
		node2 = new Node();
		node2.setCode(002);
		node2.setDescription("Carro");
		node2.setNote("Nó criado para carro");
		node2.setParent(null);
		node2.setId(new NodeDao().save(node2));
		
		node3 = new Node();
		node3.setCode(003);
		node3.setDescription("Chevrolet");
		node3.setNote("Nó criado como filho de carro");
		node3.setParent(node2.getId());
		node3.setId(new NodeDao().save(node3));
		
		this.nodes.add(node1);
		this.nodes.add(node2);
		this.nodes.add(node3);
	}
	
	@Test
	public void saveTest() {
		Collection<Node> tree = new NodeDao().findAllNodes();
		Gson gson = new Gson();
		System.out.println("Tree: "+gson.toJson(tree));
		Assert.assertTrue(!tree.isEmpty());
	}
	
	//@After
	public void removeAllTest(){
		Boolean del1 = new NodeDao().delete(node1.getId());
		Boolean del2 = new NodeDao().delete(node2.getId());
		Boolean del3 = new NodeDao().delete(node3.getId());
		Assert.assertTrue(del1 && del2 && del3);
	}
}
