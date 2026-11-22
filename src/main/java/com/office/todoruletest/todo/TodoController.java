package com.office.todoruletest.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {

	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("todos", todoService.findAll());
		model.addAttribute("form", new TodoCreateForm());
		return "todos";
	}

	@PostMapping("/todos")
	public String create(
		@Valid @ModelAttribute("form") TodoCreateForm form,
		BindingResult bindingResult,
		Model model
	) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("todos", todoService.findAll());
			return "todos";
		}

		todoService.create(form.getTitle());
		return "redirect:/";
	}

	@PostMapping("/todos/{id}/toggle")
	public String toggle(@PathVariable Long id) {
		todoService.toggle(id);
		return "redirect:/";
	}

	@PostMapping("/todos/{id}/delete")
	public String delete(@PathVariable Long id) {
		todoService.delete(id);
		return "redirect:/";
	}
}
