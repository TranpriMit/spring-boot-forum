package com.lixiaoqiu.service;

import com.lixiaoqiu.persistence.model.Comment;
import com.lixiaoqiu.web.dto.CommentDto;

public interface CommentService {

	void save(Comment comment);

	int countNumCommentsByPostId(Long postId);

	Comment createNewCommentOnPost(Long postId, CommentDto newCommentForm);

}
