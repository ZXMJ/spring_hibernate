package com.ultra.sh.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ultra.sh.dao.BookDao;
import com.ultra.sh.exception.BookException;
import com.ultra.sh.exception.UserException;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Integer getPriceById(Integer bookId) {
		String sql = "SELECT price FROM Book WHERE id = ?";
		Query<Integer> query = getSession().createQuery(sql);
		query.setParameter(0, bookId);
		return query.getSingleResult();
	}

	@Override
	public void updateStockByBookId(Integer bookId) {
		String sql = "UPDATE Book SET Stock= Stock - 1 WHERE id = ? AND Stock > 0";
		Query<Integer> query = getSession().createQuery(sql);
		query.setParameter(0, bookId);
		int result = query.executeUpdate();
		if (result > 0)
			return;
		else {
			throw new BookException("库存不足...");
		}
	}

	@Override
	public void updateBalanceByUserId(Integer userId, int price) {
		String sql = "UPDATE User SET balance = balance - ? WHERE id = ? AND balance >= ?";
		Query<Integer> query = getSession().createQuery(sql);
		query.setParameter(0, price).setParameter(1, userId).setParameter(2, price);
		int result = query.executeUpdate();
		if (result > 0)
			return;
		else {
			throw new UserException("余额不足...");
		}
	}

}
