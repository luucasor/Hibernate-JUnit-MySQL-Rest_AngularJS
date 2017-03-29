package dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.luucasor.dao.NodeDao;
import br.com.luucasor.model.Node;

public class NodeDaoTest {

	Node node = null;
	
	@Before
	public void setUp(){
		this.node = new Node();
		this.node.setCode(001);
		this.node.setDescription("Casa");
		this.node.setNote("Nó criado para casa");
		this.node.setParent(null);
	}
	
	@Test
	public void saveTest() {
		this.node.setId(new NodeDao().save(this.node));
		Assert.assertTrue(this.node.getId() != null);
	}
	
	@After
	public void deleteTest(){
		Assert.assertTrue(new NodeDao().delete(this.node.getId()));
	}

}
