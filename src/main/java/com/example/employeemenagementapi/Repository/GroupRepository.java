package com.example.employeemenagementapi.Repository;

import com.example.employeemenagementapi.Model.ClassEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<ClassEmployee, Long> {}