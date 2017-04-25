package com.swk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swk.dto.AppointExecution;
import com.swk.dto.Result;
import com.swk.enums.AppointStateEnum;
import com.swk.exception.NoNumberException;
import com.swk.exception.RepeatAppointException;
import com.swk.model.Book;
import com.swk.service.BookService;

@Controller
@RequestMapping("/book")	//url:/模块/资源/{id}/细分 /seckill/list
public class BookController {
	
	public BookController() {
		super();
		System.out.println("controller...");
	}

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	private String list(Model model){
		List<Book> list = bookService.getList();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value="/detail/{bookId}", method=RequestMethod.GET)
	private String detail(@PathVariable("bookId") Long bookId, Model model){
		if (bookId == null) {
			return "redirect:/book/list";
		}
		Book book = bookService.getById(bookId);
		if (book == null) {
			return "forward:/book/list";
		}
		model.addAttribute("book", book);
		return "detail";
	}
	
	@ResponseBody
	@RequestMapping(value="/appoint/{bookId}", method=RequestMethod.POST)
	private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId, 
			@RequestParam("studentId") Long studentId){
		
		if (studentId == null || "".equals(studentId)) {
			return new Result<>(false, "学号不能为空");
		}
		
		AppointExecution execution = null;
		try {
			execution = bookService.appoint(bookId, studentId);
		} catch (NoNumberException e1) {
			execution = new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
		} catch (RepeatAppointException e2) {
			execution = new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);
		} catch (Exception e) {
			execution = new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);
		}
		return new Result<AppointExecution>(true, execution);
	}
	
	
	
	
}
