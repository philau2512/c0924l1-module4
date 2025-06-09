package com.codegym.demo_spring_jpa.repository;
import com.codegym.demo_spring_jpa.model.ClassCG;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassRepository extends JpaRepository<ClassCG, Integer> {
}
