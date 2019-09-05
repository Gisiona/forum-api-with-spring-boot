package br.com.estudos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
@ResponseBody
public class ForumController {

	@GetMapping("/")
	public String get() {
		return "TEste do primeiro endpoint";
				}
}
