package com.goodfood.api.repositories;

import com.goodfood.api.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer> {

  // List<Comments> findAllByCommentId(int comment_id);
}