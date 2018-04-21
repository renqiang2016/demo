package com.example.ren.dao.neo4j;

import com.example.ren.model.neo4j.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * movie与neo4j的相关操作
 *
 * @author qiang.ren
 * @date 2018/4/17
 */
@Repository
public interface MovieRepository extends Neo4jRepository<Movie, Long>{

    /**
     * 通过title查找movie
     * @param name 名字
     * @return movie
     */
    Movie findByName(@Param("name") String name);
}
