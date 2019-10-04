package ru.vtarasov.sb.student;

import org.springframework.data.repository.CrudRepository;

/**
 * @author vtarasov
 * @since 04.10.2019
 */
public interface StudentRepository extends CrudRepository<Student, String> {}
