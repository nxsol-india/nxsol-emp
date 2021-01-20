package com.nxsol.respository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nxsol.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	
	@Query(value="SELECT * FROM Employee emp WHERE emp.isDeleted=false")
	public List<Employee> findAllEmployeesByIsDeletedFalse();
	
	@Query(value="SELECT emp FROM Employee emp WHERE emp.id=?1 and emp.isDeleted=false ")
	public Employee findEmployeeById(Long id);
	
	/*@Query(value="SELECT emp FROM Employee emp WHERE emp.id= ?1 AND emp.project.id= ?2")
	public List<Employee> findEmployeeByProjectId(Long empId, Long projectid);*/
}
