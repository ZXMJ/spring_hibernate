package com.ultra.sh.dao;

public interface BookDao {

	public Integer getPriceById(Integer bookId);

	public void updateStockByBookId(Integer bookId);

	public void updateBalanceByUserId(Integer userId, int price);

}
