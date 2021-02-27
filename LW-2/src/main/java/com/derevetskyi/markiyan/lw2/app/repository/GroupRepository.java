package com.derevetskyi.markiyan.lw2.app.repository;

import com.derevetskyi.markiyan.lw2.app.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from Group g left join fetch g.students left join fetch g.curator where g.id = :id")
    Optional<Group> findById(@Param(value = "id") Long id);

    Group findByName(String groupName);

    @Query("select g from Group g where g.id = :id")
    Group findByIdSecond(@Param(value = "id") Long id);
    
}
