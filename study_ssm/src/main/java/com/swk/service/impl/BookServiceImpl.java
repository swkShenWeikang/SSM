package com.swk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swk.dao.AppointmentDao;
import com.swk.dao.BookDao;
import com.swk.dto.AppointExecution;
import com.swk.enums.AppointStateEnum;
import com.swk.exception.AppointException;
import com.swk.exception.NoNumberException;
import com.swk.exception.RepeatAppointException;
import com.swk.model.Appointment;
import com.swk.model.Book;
import com.swk.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	public BookServiceImpl() {
		super();
		System.out.println("service...");
	}

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private AppointmentDao appointmentDao;
	

	@Override
	public Book getById(long bookId) {
		return bookDao.queryById(bookId);
	}

	@Override
	public List<Book> getList() {
		return bookDao.queryAll(0, 100);
	}

	/**
	 * 使用注解控制事务方法的优点： 
	 * 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	@Transactional
	@Override
	public AppointExecution appoint(long bookId, long studentId) {
		
		try {
			//减库存
			int update = bookDao.reduceNumber(bookId);
			if(update <= 0){//库存不足
				throw new NoNumberException(AppointStateEnum.NO_NUMBER.getStateInfo());
			}else{//执行预约操作
				int insert = appointmentDao.insertAppointment(bookId, studentId);
				if (insert <= 0) {//重复预约
					throw new RepeatAppointException(AppointStateEnum.REPEAT_APPOINT.getStateInfo());
				}else{//预约成功
					Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
					return new AppointExecution(bookId, AppointStateEnum.SUCCESS, appointment);
				}
			}
			
		} catch (NoNumberException e1) {
			throw e1;
		} catch (RepeatAppointException e2) {
			throw e2;
		} catch (Exception e) {
			throw new AppointException("appoint inner error:" + e.getMessage());
		}
		
	}

}
