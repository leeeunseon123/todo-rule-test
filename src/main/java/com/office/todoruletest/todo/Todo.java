package com.office.todoruletest.todo;

public class Todo {

	private final Long id;
	private final String title;
	private final boolean done;

	public Todo(Long id, String title, boolean done) {
		this.id = id;
		this.title = title;
		this.done = done;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public boolean isDone() {
		return done;
	}

	public Todo withDone(boolean done) {
		return new Todo(id, title, done);
	}
}
