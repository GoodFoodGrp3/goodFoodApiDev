package com.goodfood.api.repositories;

import com.goodfood.api.entities.Comments;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer> {

    Comments findById( int id );

}