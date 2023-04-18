package com.pi.tobeeb.Repositorys;

import com.pi.tobeeb.Entities.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    /*
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.idPost = :id")
    Optional<Post> findByIdWithComments(@Param("id") Long id);*/
    @Query(" SELECT new com.pi.tobeeb.Entities.Post(p.idPost,p.namePost, p.contentPost) FROM Post p LEFT JOIN p.user u WHERE u.idUser = :iduser")
    Set<Post> findPostsByUser(@Param("iduser") Long iduser);

    }
