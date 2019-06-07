package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.Answer;

@RestController
@RequestMapping("/api")
public class RESTController {
	
	private static Map<String, Answer> answerRepo = new HashMap<>();
	static {
		Answer answer1 = new Answer();
		answer1.setId("1");
		answer1.setName("vinh oc cho");
		answerRepo.put(answer1.getId(), answer1);
		Answer answer2 = new Answer();
		answer2.setId("2");
		answer2.setName("B");
		answerRepo.put(answer2.getId(), answer2);
		Answer answer3 = new Answer();
		answer3.setId("3");
		answer3.setName("C");
		answerRepo.put(answer3.getId(), answer3);
	}
	
	private static List<String> correctAnswser = new ArrayList();
	static {
		correctAnswser.add("vinh oc cho");
		correctAnswser.add("B");
		correctAnswser.add("C");
	}
	
	@CrossOrigin
	@RequestMapping(value = "/result/{answers}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> getResult(@PathVariable List<String> answers) {
		System.out.println("getResult is Calling");
		System.out.println(answers);
		int score = 0;
		for(int i = 0; i < correctAnswser.size(); i++) {
			if(correctAnswser.get(i).equals(answers.get(i)))
				score++;
		}
		System.out.println(score);
		return new ResponseEntity<>(score, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/answers", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> getAnswer() {
		System.out.println("getAnswer is Calling");
		return new ResponseEntity<>(answerRepo.values(), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/answers", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> createAnswer(@RequestBody Answer answer) {
		System.out.println("createAnswer is Calling");
		answerRepo.put(answer.getId(), answer);
		return new ResponseEntity<>("Answer is created successfully", HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/answers/{id}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> updateAnswer(@PathVariable("id") String id, @RequestBody Answer answer) {
		System.out.println("updateAnswer is Calling");
		if (!answerRepo.containsKey(id))
			//throw new AnswerNotfoundException(); //Exception Handling
		answerRepo.remove(id);
		answer.setId(id);
		answerRepo.put(id, answer);
		return new ResponseEntity<>("Answer is updated successfully", HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/answers/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		System.out.println("delete is Calling");
		answerRepo.remove(id);
		return new ResponseEntity<>("Answer is deleted successsfully", HttpStatus.OK);
	}
}