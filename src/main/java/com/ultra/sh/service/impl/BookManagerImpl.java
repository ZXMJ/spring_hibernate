package com.ultra.sh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ultra.sh.service.BookManager;
import com.ultra.sh.service.BookService;

@Service("bookManager")
public class BookManagerImpl implements BookManager {

	@Autowired
	private BookService bookService;

	@Override
	@Transactional
	public void buy(int userId, List<Integer> bookIds) {
		for (int bookId : bookIds) {
			bookService.buy(userId, bookId);
		}
	}

}
