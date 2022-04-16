package com.matters.moviedb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 Repository to persist MovieEntity
 */
@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Integer> {

}
