package com._520it.wms1.service;
import java.util.List;
import com._520it.wms1.domain.Student;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.StudentQueryObject;

public interface IStudentService {
	void delete(Long id);
	
	void save(Student entity);
	
    Student get(Long id);
    
    List<Student> list();
    
	void update(Student entity);
	
	PageResult pageQuery(StudentQueryObject qo);
}
