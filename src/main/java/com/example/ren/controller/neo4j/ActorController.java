package com.example.ren.controller.neo4j;

import com.example.ren.dao.neo4j.ActorRepository;
import com.example.ren.model.neo4j.Actor;
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

/**
 * actor控制器
 *
 * @author qiang.ren
 * @date 2018/4/20
 */
@RestController
@RequestMapping("/actor")
public class ActorController {
    private static Logger logger = LoggerFactory.getLogger(ActorController.class);
    @Autowired
    private ActorRepository actorRepository;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("actor/index");
    }

    @RequestMapping(value="/{id}")
    public ModelAndView show(ModelMap model,@PathVariable Long id) {
        Actor actor = actorRepository.findById(id).get();
        model.addAttribute("actor",actor);
        return new ModelAndView("actor/show");
    }

    @RequestMapping("/new")
    public ModelAndView create(){
        return new ModelAndView("actor/new");
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(@RequestBody Actor actor) throws Exception{
        actorRepository.save(actor);
        logger.info("新增->ID={}", actor.getId());
        return "1";
    }

    @RequestMapping(value="/edit/{id}")
    public ModelAndView update(ModelMap model,@PathVariable Long id){
        Actor actor = actorRepository.findById(id).get();
        model.addAttribute("actor",actor);
        return new ModelAndView("actor/edit");
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    public String update(@RequestBody Actor actor) throws Exception{
        actorRepository.save(actor);
        logger.info("修改->ID="+actor.getId());
        return "1";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id) throws Exception{
        Actor actor = actorRepository.findById(id).get();
        actorRepository.delete(actor);
        logger.info("删除->ID="+id);
        return "1";
    }

    @RequestMapping(value="/list")
    public Page<Actor> list(HttpServletRequest request) throws Exception{
        String name = request.getParameter("name");
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        Pageable pageable = new PageRequest(page==null? 0: Integer.parseInt(page), size==null? 10:Integer.parseInt(size),
                new Sort(Sort.Direction.DESC, "id"));
        return actorRepository.findAll(pageable);
    }

}
