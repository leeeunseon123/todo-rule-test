package com.office.todoruletest.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TodoService {

	private final Map<Long, Todo> todos = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(1);

	public List<Todo> findAll() {
		return new ArrayList<>(todos.values());
	}

	public Todo create(String title) {
		Long id = sequence.getAndIncrement();
		Todo todo = new Todo(id, title.trim(), false);
		todos.put(id, todo);
		return todo;
	}

	public Todo toggle(Long id) {
		Todo todo = require(id);
		Todo updated = todo.withDone(!todo.isDone());
		todos.put(id, updated);
		return updated;
	}

	public void delete(Long id) {
		require(id);
		todos.remove(id);
	}

	private Todo require(Long id) {
		Todo todo = todos.get(id);
		if (todo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "todo not found");
		}
		return todo;
	}
}
