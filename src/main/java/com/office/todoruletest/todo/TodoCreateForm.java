package com.office.todoruletest.todo;

import jakarta.validation.constraints.NotBlank;

public class TodoCreateForm {

	@NotBlank(message = "내용을 입력해 주세요.")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
