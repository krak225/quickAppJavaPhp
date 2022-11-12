package net.krak.quickapp.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Article;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

private ArticleRepository articleRepository;

public ArticleServiceImpl(ArticleRepository articleRepository) {
	this.articleRepository = articleRepository;
}

@Override
public List<Article> getArticles(){
	return articleRepository.findAll();
}

@Override
public Article getArticle(Long id){
	return articleRepository.findById(id);
}

@Override
public Article AddArticle(Article article){
	return articleRepository.save(article);
}

@Override
public Article EditArticle(Long id, Article article){
	return articleRepository.save(article);
}

@Override
public Article deleteArticle(Long id){
	return articleRepository.delete(id);
}

}
