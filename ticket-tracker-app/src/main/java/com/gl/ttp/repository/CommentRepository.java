package com.gl.ttp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ttp.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Add any additional methods specific to comments if needed

}
