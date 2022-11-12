package net.krak.quickapp.services;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Article;

public interface ArticleService {

	List<Article> getArticles();
	Optional Article getArticle(Long id);
	Article AddArticle(Article article);
	Article EditArticle(Long id, Article article);
	void Article deleteArticle(Long id);

}
