package com.swk.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.swk.dto.AppointExecution;
import com.swk.enums.AppointStateEnum;
import com.swk.exception.AppointException;
import com.swk.exception.NoNumberException;
import com.swk.exception.RepeatAppointException;
import com.swk.service.BookService;

public class BookServiceImplTest extends BaseTest {
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void testAppoint() throws Exception {
		long bookId = 1001;
		long studentId = 12345678910L;
		AppointExecution execution = null;
		try {
			execution = bookService.appoint(bookId, studentId);
		} catch (NoNumberException e1) {
			execution = new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
		} catch (RepeatAppointException e2) {
			execution = new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);
		} catch (AppointException e3) {
			execution = new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(execution);
	}
	
	
}
