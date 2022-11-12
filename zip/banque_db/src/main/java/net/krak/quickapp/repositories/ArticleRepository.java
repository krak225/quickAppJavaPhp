package net.krak.quickapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.krak.quickapp.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
