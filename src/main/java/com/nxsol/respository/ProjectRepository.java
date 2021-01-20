package com.nxsol.respository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nxsol.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

//	@Query(value="SELECT proj FROM Project proj WHERE proj =?1")
//	Project findProject(Project proj);
	
	@Query(value="SELECT proj FROM Project proj WHERE proj.id =?1 and proj.isDeleted = 0")
	Project findProjectById(Long proj);
}
