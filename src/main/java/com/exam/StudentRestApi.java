package com.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRestApi extends JpaRepository<Student, Integer>{

}
