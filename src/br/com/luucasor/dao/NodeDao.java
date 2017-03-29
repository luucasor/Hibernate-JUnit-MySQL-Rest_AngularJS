package br.com.luucasor.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.luucasor.model.Node;
import br.com.luucasor.util.Hibernate;

public class NodeDao extends Hibernate {

	public NodeDao() {
		super();
	}

	public Long save(Node object) {
		EntityManager entityManager = Hibernate.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			if (object.getId() == null) {
				entityManager.persist(object);
			} else {
				object = entityManager.merge(object);
			}
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}

		return object.getId();
	}

	public Boolean delete(Long id) {
		EntityManager entityManager = Hibernate.getEntityManager();
		Boolean status = false;
		try {
			Node node = entityManager.find(Node.class, id);
			if(node != null){
				entityManager.getTransaction().begin();
				this.deleteExistingChildNodes(node, entityManager);
				entityManager.remove(node);
				entityManager.getTransaction().commit();
				status = true;
			}
		} finally {
			entityManager.close();
		}
		return status;
	}

	private void deleteExistingChildNodes(Node node, EntityManager entityManager) {
		Query query = entityManager.createQuery("SELECT n FROM Node n WHERE parent = :parentId").setParameter("parentId", node.getId());
		Collection<Node> childNodes = query.getResultList();
		for (Node childNode : childNodes) {
			entityManager.remove(childNode);
		}
	}

	public Node findById(Long id) {
		EntityManager entityManager = Hibernate.getEntityManager();
		Node node = null;
		try {
			node = entityManager.find(Node.class, id);
		} finally {
			entityManager.close();
		}
		return node;
	}

	@SuppressWarnings("unchecked")
	public Collection<Node> findAllNodes() {
		EntityManager entityManager = Hibernate.getEntityManager();
		Query query = entityManager.createQuery("SELECT n FROM Node n");
		return (Collection<Node>) query.getResultList();
	}
}
