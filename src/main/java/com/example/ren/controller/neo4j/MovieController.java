package com.example.ren.controller.neo4j;

import com.example.ren.dao.neo4j.ActorRepository;
import com.example.ren.dao.neo4j.MovieRepository;
import com.example.ren.model.neo4j.Actor;
import com.example.ren.model.neo4j.Movie;
import com.example.ren.service.neo4j.PagesService;
import com.google.common.base.Strings;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * movie控制器
 *
 * @author qiang.ren
 * @date 2018/4/20
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    private final static Logger logger = LoggerFactory.getLogger(MovieController.class);

    private final MovieRepository movieRepository;

    private final ActorRepository actorRepository;

    private final PagesService<Movie> pagesService;

    @Autowired
    public MovieController(MovieRepository movieRepository, ActorRepository actorRepository, PagesService<Movie> pagesService) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.pagesService = pagesService;
    }

    @RequestMapping("/new")
    public ModelAndView create(ModelMap modelMap){
        String[] files = {"/images/movie/西游记.jpg", "/images/movie/test.jpg"};
        modelMap.addAttribute("files", files);
        return new ModelAndView("movie/new");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody Movie movie){
        movieRepository.save(movie);
        logger.info("新增->ID={}" + movie.getId());
        return "1";
    }

    @RequestMapping(value = "/{id}")
    public ModelAndView show(ModelMap modelMap, @PathVariable Long id){
        Optional<Movie> optional = movieRepository.findById(id);
        modelMap.addAttribute("movie", optional.get());
        return new ModelAndView("movie/show");
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView update(ModelMap modelMap, @PathVariable Long id){
        Optional<Movie> optional = movieRepository.findById(id);
        String[] files = {"/images/movie/西游记.jpg", "/images/movie/test.jpg"};
        String[] roles = {"唐僧", "孙悟空", "猪八戒", "沙僧"};
        Iterable<Actor> actors = actorRepository.findAll();
        modelMap.addAttribute("files", files);
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("movie", optional.get());
        modelMap.addAttribute("actors", actors);
        return new ModelAndView("movie/edit");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody Movie movie, HttpServletRequest request){
        String roleName = request.getParameter("roleName");
        String actorId = request.getParameter("actorId");
        Movie old = movieRepository.findById(movie.getId()).get();
        old.setName(movie.getName());
        old.setPhoto(movie.getPhoto());
        if (!Strings.isNullOrEmpty(roleName) && !Strings.isNullOrEmpty(actorId)){
            Actor actor = actorRepository.findById(new Long(actorId)).get();
            old.addRole(actor, roleName);
        }
        movieRepository.save(old);
        logger.info("修改->ID={}", old.getId());
        return "1";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id){
        Movie movie = movieRepository.findById(id).get();
        movieRepository.delete(movie);
        logger.info("删除->ID={}" + id);
        return "1";
    }

    @RequestMapping(value = "/list")
    public Page<Movie> list(HttpServletRequest request){
        String name = request.getParameter("name");
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        Pageable pageable = new PageRequest(page==null?0:Integer.parseInt(page),
                size==null?10:Integer.parseInt(size), new Sort(Sort.Direction.DESC, "id"));
        Filters filters = new Filters();
        /*if (!Strings.isNullOrEmpty(name)){
            Filter filter = new Filter("name", name);
            filters.add(filter);
        }*/
        return pagesService.findAll(Movie.class, pageable, filters);
    }
}
