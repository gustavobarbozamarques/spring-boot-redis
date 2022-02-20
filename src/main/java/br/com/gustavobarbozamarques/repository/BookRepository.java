package br.com.gustavobarbozamarques.repository;

import br.com.gustavobarbozamarques.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
