package com.example.ren;

import com.example.ren.model.BookBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private BookBean bookBean;

	@Test
	public void contextLoads() {
		System.out.println(bookBean.getAuthor());
	}

}
