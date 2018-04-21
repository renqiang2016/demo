package com.example.ren.dao.neo4j;

import com.example.ren.model.neo4j.Actor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * actor与neo4j的相关操作
 * Created by qiang.ren on 2018/4/19.
 */
@Repository
public interface ActorRepository extends Neo4jRepository<Actor, Long> {

    Actor findByName(@Param("name") String name);
}
