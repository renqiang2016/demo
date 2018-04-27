package com.example.ren.neo4j;

import com.example.ren.dao.neo4j.ActorRepository;
import com.example.ren.dao.neo4j.MovieRepository;
import com.example.ren.model.neo4j.Actor;
import com.example.ren.model.neo4j.Movie;
import com.example.ren.model.neo4j.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Neo4j测试类(需要注意，进行mysql测试时，需要解禁Neo4j配置类->Neo4jConfiguration.java)
 * Created by qiang.ren on 2018/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Neo4jTests {

    private static Logger logger = LoggerFactory.getLogger(Neo4jTests.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Before
    public void initData() throws ParseException {
        movieRepository.deleteAll();
        actorRepository.deleteAll();

        Movie movie1 = new Movie();
        movie1.setName("三国演义");
        movie1.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse("1999-03-31"));

        Movie movie2 = new Movie();
        movie2.setName("西游记");
        movie2.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse("2003-05-07"));

        Movie movie3 = new Movie();
        movie3.setName("红楼梦");
        movie3.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse("2003-10-27"));

        Actor actor1 = new Actor();
        actor1.setName("鲍国安");

        Actor actor2 = new Actor();
        actor2.setName("六小龄童");

        Actor actor3 = new Actor();
        actor3.setName("邓婕");

        movie1.addRole(actor1,  "曹操");
        movie1.addRole(actor2, "孙悟空");
        movie1.addRole(actor3,  "王熙凤");
        movieRepository.save(movie1);
        Assert.notNull(movie1.getId());

        movie2.addRole(actor1, "曹操");
        movie2.addRole(actor2, "孙悟空");
        movie2.addRole(actor3,  "王熙凤");
        movieRepository.save(movie2);
        Assert.notNull(movie2.getId());

        movie3.addRole(actor1, "曹操");
        movie3.addRole(actor2, "孙悟空");
        movie3.addRole(actor3, "王熙凤");
        movieRepository.save(movie3);
        Assert.notNull(movie3.getId());
    }

    @Test
    public void get(){
        Movie movie = movieRepository.findByName("三国演义");
        Assert.notNull(movie);
        logger.info("===movie=== movie:{}, {}",movie.getName(), movie.getCreateDate());
        for(Role role : movie.getRoles()){
            logger.info("====== actor:{}, role:{}", role.getActor().getName(), role.getName());
        }
    }
}
