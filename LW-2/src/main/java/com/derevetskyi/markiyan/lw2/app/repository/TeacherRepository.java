package com.derevetskyi.markiyan.lw2.app.repository;

import com.derevetskyi.markiyan.lw2.app.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select t from Teacher t left join fetch t.subjects where t.id = :id")
    Optional<Teacher> findById(@Param(value = "id") Long id);
    
    @Query(value = "select * from teacher where id not in(select curator_id from group_ where curator_id is not null)", nativeQuery = true)
    List<Teacher> findAllNotCurators();
}
