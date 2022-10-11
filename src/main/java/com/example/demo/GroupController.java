package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GroupController {

	private final GroupService groupService;

	//shianServiceのDI
	public GroupController(GroupService groupService) {
		this.groupService = groupService;
	}

	@ModelAttribute
	public GroupForm setUpForm() {
		return new GroupForm();
	}

	@RequestMapping("/input_2")
	public String index() {
		return "group_index.html";
	}

	@RequestMapping("/output_2")
	public String result(@Validated GroupForm groupForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "group_index.html";
		}

		String number = groupForm.getNumber();
		//String name = "コントローラー太郎"
		String name = groupService.findByNo(number);

		model.addAttribute("number", number);
		model.addAttribute("name", name);
		return "group_output.html";
	}

}