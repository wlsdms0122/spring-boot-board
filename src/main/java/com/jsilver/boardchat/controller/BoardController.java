package com.jsilver.boardchat.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsilver.boardchat.dto.Comment;
import com.jsilver.boardchat.dto.Post;
import com.jsilver.boardchat.dto.ViewParams;
import com.jsilver.boardchat.service.CommentService;
import com.jsilver.boardchat.service.PostService;

@Controller
public class BoardController {

	@Autowired
	PostService postService;
	@Autowired
	CommentService commentService;
	
	// 게시글 목록 조회 페이지
	@RequestMapping("/board/list")
	public String list(ViewParams viewParams, Model model) {
		model.addAttribute("board", "active");

		model.addAttribute("posts", postService.getPosts(viewParams));
		model.addAttribute("pageCount", (int)Math.ceil(postService.getPostCount() / (double)20));
		model.addAttribute("viewParams", viewParams);
		
		return "board/list";
	}
	
	// 게시글 조회 페이지
	@RequestMapping(method = RequestMethod.GET, value = "/board/view")
	public String view(ViewParams viewParams, @RequestParam int id, Model model) {
		model.addAttribute("board", "active");
		
		Post post = postService.getPost(id);
		if (post.getIsDeleted()) {
			return "redirect:/board/list";
		}
		
		model.addAttribute("post", post);
		model.addAttribute("pageCount", (int)Math.ceil(commentService.getCommentCount(id) / (double)20));
		model.addAttribute("viewParams", viewParams);
		
		return "board/view";
	}
	
	// 게시글 작성 페이지
	@RequestMapping("/board/write")
	public String write(Model model) {
		model.addAttribute("board", "active");

		return "board/write";
	}
	
	// 게시글 수정 페이지
	@RequestMapping(method = RequestMethod.POST, value = "/board/modify")
	public String modify(@RequestParam(required = false) String password, @RequestParam int id, Model model) {
		model.addAttribute("board", "active");

		if (password == null) {
			return "board/validate";
		}
		
		Post post = postService.getPost(id);
		if (!post.getPassword().equals(password)) {
			model.addAttribute("error", 1);
			return "board/validate";
		}
		
		model.addAttribute("post", post);
		
		return "board/modify";
	}
	
	// 게시글 삭제 페이지
	@RequestMapping(method = RequestMethod.POST, value = "/board/delete")
	public String delete(@RequestParam(required = false) String password, @RequestParam int id, Model model) {
		model.addAttribute("board", "active");
		
		if (password == null) {
			return "board/validate";
		}
		
		Post post = postService.getPost(id);
		if (!post.getPassword().equals(password)) {
			model.addAttribute("error", 1);
			return "board/validate";
		}
		
		return "redirect:/board/post/delete.process" + "?id=" + id;
	}
	
	// 게시글 작성
	@RequestMapping(method = RequestMethod.POST, value = "/board/post/create.process")
	public String postCreate(Post post) {
		try {
			postService.create(post);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/board/list";
	}
	
	// 게시글 삭제
	@RequestMapping("/board/post/delete.process")
	public String postDelete(@RequestParam int id) {
		try {
			postService.remove(id);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/board/list";
	}
	
	// 게시글 수정
	@RequestMapping(method = RequestMethod.POST, value = "/board/post/update.process")
	public String postUpdate(@RequestParam int id, Post post) {
		try {
			postService.modify(post);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/board/view" + "?id=" + post.getId();
	}
	
	// 댓글 조회
	@RequestMapping("/board/comment/view.process")
	@ResponseBody
	public List<Comment> commentView(@RequestParam int page, @RequestParam int post_id) {
		return commentService.getComments(page, post_id);
	}
	
	// 댓글 작성
	@RequestMapping(method = RequestMethod.POST, value = "/board/comment/create.process")
	@ResponseBody
	public int commentCreate(Comment comment) {
		try {
			commentService.create(comment);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}
	
	// 댓글 삭제
	@RequestMapping(method = RequestMethod.POST, value = "/board/comment/delete.process")
	@ResponseBody
	public int commentDelete(@RequestParam int id, @RequestParam String password) {
		Comment comment = commentService.getComment(id);
		if (!comment.getPassword().equals(password)) {
			return 0;
		}
		
		try {
			commentService.remove(id);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}
}
