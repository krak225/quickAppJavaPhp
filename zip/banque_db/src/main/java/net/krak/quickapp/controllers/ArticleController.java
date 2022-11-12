package net.krak.quickapp.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.krak.quickapp.entities.Article;
import net.krak.quickapp.services.ArticleService;

@RestController
@RequestMapping(path="/api/")
public class ArticleController {

public ArticleService articleService;

public ArticleController(ArticleService articleService) {
	this.articleService = articleService;
}

@GetMapping(path="/articles")
public List<Article> getArticles(){
	return articleService.findAll();
}

@GetMapping(path="/article/{id}")
public Article getArticle(@PathVariable Long id){
	return articleService.findById(id);
}

@PostMapping(path="/article")
public Article AddArticle(Article article){
	return articleService.save(article);
}

@PutMapping(path="/article/{id}")
public Article EditArticle(@PathVariable Long id, Article article){
	return articleService.save(article);
}

@DeleteMapping(path="/article/{id}")
public Article DeleteArticle(@PathVariable Long id){
	return articleService.delete(id);
}

}
