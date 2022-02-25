package com.goodfood.api.repositories;

import com.goodfood.api.entities.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer> {
}
