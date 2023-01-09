package com.KoreaIT.example.JAM.service;

import java.sql.Connection;

import com.KoreaIT.example.JAM.dao.ArticleDao;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService(Connection conn) {
		articleDao = new ArticleDao(conn);

	}

	public int doWrite(String title, String body) {
		// TODO Auto-generated method stub
		return articleDao.doWrite(title, body);
	}

	public boolean isArticleExists(int id) {
		return articleDao.isArticleExists(id);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);
	}

	public int doModfiy(int id, String title, String body) {
		//
		return articleDao.doModify(id, title, body);
	}

}
