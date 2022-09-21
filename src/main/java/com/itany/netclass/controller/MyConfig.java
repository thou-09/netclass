package com.itany.netclass.controller;

import com.itany.mvc.annotation.Configuration;
import com.itany.mvc.config.ResourceHandlerRegistry;
import com.itany.mvc.config.WebConfigurer;

/**
 * MyConfig
 *
 * @author Thou
 * @date 2022/8/30
*/
@Configuration
public class MyConfig extends WebConfigurer {

	@Override
	public void addViewControllers(ResourceHandlerRegistry registry) {
		registry.addViewController("back_login", "/show_back_login")
				.addViewController("back_home", "/show_back_home")
				.addViewController("back_userSet", "/show_back_userSet")
				.addViewController("back_courseSet", "/show_back_courseSet")
				.addViewController("back_courseResourceSet", "/show_back_courseResourceSet")
				.addViewController("back_courseTypeSet", "/show_back_courseTypeSet")
				.addViewController("back_resourceSet", "/show_back_resourceSet")
				.addViewController("back_commentSet", "/show_back_commentSet")
				.addViewController("back_commentManageSet", "/show_back_commentManageSet")
				.addViewController("front_index", "/show_front_index")
				.addViewController("front_select", "/show_front_select")
				.addViewController("front_mycourse", "/show_front_mycourse")
				.addViewController("front_record", "/show_front_record");
	}
}
