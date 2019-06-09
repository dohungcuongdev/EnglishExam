/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import daos.ListeningDAO;
import daos.ReadingDAO;
import daos.SpeakingDAO;
import daos.UserDAO;
import daos.WritingDAO;
import helper.Authentication;
import helper.Logger;
import models.AppVars;
import models.ListeningResult;
import models.Listeninganswer;
import models.Reading;
import models.Readinganswer;
import models.Speaking;
import models.User;
import models.Writing;
import services.UserService;

@Controller
@RequestMapping(value = "/")
public class AppController {

	@Autowired
	private UserService userService;

	@Autowired
	private WritingDAO writingDAO;

	@Autowired
	private ListeningDAO listeningDAO;

	@Autowired
	private SpeakingDAO speakingDAO;

	@Autowired
	private ReadingDAO readingDAO;

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_test");
		return "test";
	}

	@RequestMapping(value = "test2", method = RequestMethod.GET)
	public String test2(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_test2");
		model.put("studentResult", 0);
		System.out.println(readingDAO.getReadingAnswer(1));
		return "test2";
	}

	@RequestMapping(value = "computeScore", method = RequestMethod.POST)
	public String computeScore(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_computeScore");

		List<Readinganswer> correctAnswser = readingDAO.getReadingAnswer(1);
		int score = 0;
		for (int i = 0; i < correctAnswser.size(); i++) {
			if (correctAnswser.get(i).getAnswer().equals(request.getParameter("Q" + (i + 1))))
				score++;
		}
		model.put("studentResult", score);
		return "test2";
	}

	// admin
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String admin(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_admin");
		if (!isAuthenticated(request)) {
			return "login";
		}
		return "admin";
	}

	// resigter
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String user(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_register");
		return "register";
	}

	// addNewUser
	@RequestMapping(value = "newRegister", method = RequestMethod.POST)
	public String newRegister(@ModelAttribute(value = "newUser") User newUser, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_newRegister");
		newUser.setPassword(Authentication.getEncryptPassword(newUser.getPassword()));
		Logger.infor(newUser.getUserName());
		Logger.infor(newUser.getPassword());
		userDAO.addNewUser(newUser);
		return "register";
	}

	// edit speaking information
	@RequestMapping(value = "edit-speaking/{id}", method = RequestMethod.GET)
	public String editSpeakingInfor(@PathVariable(value = "id") String id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_edit-speaking");
		Speaking speaking = speakingDAO.getSpeakingByID(id);
		Logger.infor(speaking);
		model.put("speaking", speaking);
		return "edit-speaking";
	}

	// save speaking information
	@RequestMapping(value = "updateSpeaking", method = RequestMethod.POST)
	public String updateSpeaking(@ModelAttribute(value = "speaking") Speaking speaking, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_updateSpeaking");
		speakingDAO.editSpeakingInfor(speaking);
		model.put("id", speaking.getId());
		return "edit-speaking";
	}

	// addNewReading
	@RequestMapping(value = "addNewReading", method = RequestMethod.POST)
	public String addNewReading(@ModelAttribute(value = "newReading") Reading newReading, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_addNewReading");
		Logger.infor(newReading.getId());
		Logger.infor(newReading.getTopic());
		readingDAO.addNewReading(newReading);
		return "admin";
	}

	// addNewSpeaking
	@RequestMapping(value = "addNewSpeaking", method = RequestMethod.POST)
	public String addNewSpeaking(@ModelAttribute(value = "newSpeaking") Speaking newSpeaking,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_addNewReading");
		Logger.infor(newSpeaking.getId());
		Logger.infor(newSpeaking.getTopic());
		speakingDAO.addNewSpeaking(newSpeaking);
		return "admin";
	}

	// index
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_index");

		return "index";
	}

	// reading
	@RequestMapping(value = "reading/{id}", method = RequestMethod.GET)
	public String reading(@PathVariable(value = "id") String id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_reading/" + id);

		// do logic
		Reading reading = readingDAO.getReadingById(id);
		Logger.infor(reading.getTopic());
		model.put("readingTopic", reading.getTopic());

		return "reading";
	}

	@RequestMapping(value = "reading", method = RequestMethod.GET)
	public String reading(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_index");

		return "reading";
	}

	@RequestMapping(value = "readingscore", method = RequestMethod.POST)
	public String readingscore(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_readingscore");
		List<String> studentAnswers = new ArrayList<String>(); // list contains 40 students answers
		List<String> correctAnswsers = new ArrayList<String>(); // list contains 40 correct answers
		List<String> results = new ArrayList<String>(); // list contains 40 correct answers
		List<Readinganswer> listcorrectAnswser = readingDAO.getReadingAnswer(1); // get 40 answers from db where reading
																					// no = 1
		int score = 0;
		for (int i = 0; i < listcorrectAnswser.size(); i++) {
			String studentAnswer = request.getParameter("Q" + (i + 1)); // get 40 answers from form
			String correctAnswser = listcorrectAnswser.get(i).getAnswer();
			studentAnswers.add(studentAnswer);
			correctAnswsers.add(correctAnswser);
			if (correctAnswser.equals(studentAnswer)) { // compare answers
				results.add("bingo");
				score++;
			} else {
				results.add("vinh oc cho");
			}
		}
		request.setAttribute("studentAnswers", studentAnswers);
		request.setAttribute("correctAnswsers", correctAnswsers);
		request.setAttribute("results", results);
		model.put("studentResult", score);
		return "readingscore";
	}

	@RequestMapping(value = "listeningscore", method = RequestMethod.POST)
	public String listeningscore(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_listeningscore");
		List<ListeningResult> listeningResults = new ArrayList<ListeningResult>(); // list contains 40 listening result
		List<Listeninganswer> listcorrectAnswser = listeningDAO.getListeningAnswer(1); // get 40 answers from db where
																						// listening no = 1
		int score = 0;
		for (int i = 0; i < listcorrectAnswser.size(); i++) {
			String studentAnswer = request.getParameter("Q" + (i + 1)); // get 40 answers from form
			String correctAnswser = listcorrectAnswser.get(i).getAnswer();
			String result = "";
			if (correctAnswser.equals(studentAnswer)) { // compare answers
				result = "bingo";
				score++;
			} else {
				result = "wrong";
			}
			listeningResults.add(new ListeningResult(studentAnswer, correctAnswser, result));
		}
		model.put("listeningResults", listeningResults);
		model.put("studentResult", score);
		return "listeningscore";
	}

	@RequestMapping(value = "writing", method = RequestMethod.GET)
	public String writing(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_index");

		return "writing";
	}

	// writing
	@RequestMapping(value = "writing/{id}", method = RequestMethod.GET)
	public String writing(@PathVariable(value = "id") String id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_writing");

		// do logic
		Writing writing = writingDAO.getWrtingByID(id);
		Logger.infor(writing.getTopic());
		model.put("writingTopic", writing.getTopic());

		return "writing";
	}

	@RequestMapping(value = "listening", method = RequestMethod.GET)
	public String listening(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_index");

		return "listening";
	}

	@RequestMapping(value = "speaking", method = RequestMethod.GET)
	public String speaking(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_index");

		return "speaking";
	}

	@RequestMapping(value = "speaking/{id}", method = RequestMethod.GET)
	public String speaking(@PathVariable(value = "id") String id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_writing");

		// do logic
		Speaking speaking = speakingDAO.getSpeakingByID(id);
		Logger.infor(speaking.getTopic());
		model.put("speakingTopic", speaking.getTopic());

		return "speaking";
	}

	// login
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_login");
		if (isStillRemember(request))
			return admin(request, response, model);
		if (isAuthenticated(request))
			return admin(request, response, model);
		/*
		 * if(isStillRemember(request)) { String[] rememberData = rememberData(request);
		 * model.put("loginbean", new User(rememberData[0], rememberData[1])); }
		 */
		return "login";
	}

	// logout
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_logout");
		request.getSession().setAttribute("username", null);
		Cookie cookieUN = new Cookie("username", null);
		cookieUN.setMaxAge(0);
		Cookie cookiePW = new Cookie("passwordEncrypted", null);
		cookiePW.setMaxAge(0);
		response.addCookie(cookieUN);
		response.addCookie(cookiePW);
		return "index";
	}

	private boolean isAuthenticated(HttpServletRequest request) {
		Object username = request.getSession().getAttribute("username");
		if (username == null)
			return false;
		User user = userService.getUserByUserName(username.toString());
		return user != null && user.getUserName() != null;
	}

	// check login
	@RequestMapping(value = "check-login", method = RequestMethod.POST)
	public String checklogin(@ModelAttribute(value = "loginbean") User loginbean, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		Logger.infor("@RequestMapping_check-login");
		String username = loginbean.getUserName();
		String password = loginbean.getPassword();
		AppVars.user = userService.getUserByUserName(username);
		String passwordDecrypted = Authentication.getDecryptPassword(AppVars.user.getPassword());
		if (AppVars.user != null && username.equals(AppVars.user.getUserName()) && password.equals(passwordDecrypted)) {
			request.getSession().setAttribute("username", username);
			request.getSession().setMaxInactiveInterval(30 * 60);
			// For remember me feature
			checkRememberMe(request, response, username, AppVars.user.getPassword());
			return admin(request, response, model);
		}
		model.put("checkLogin", "Invalid username or password!");
		return "login";
	}

	private void checkRememberMe(HttpServletRequest request, HttpServletResponse response, String username,
			String passwordEncrypted) {
		Object rememberMe = request.getParameter("rememberMe");
		if (rememberMe != null && rememberMe.equals("on")) {
			// fix An invalid character [32] was present in the Cookie value bug
			/*
			 * Cookie cookieUN = new Cookie("username", username); Cookie cookiePW = new
			 * Cookie("passwordEncrypted", passwordEncrypted); cookieUN.setMaxAge(3600 * 24
			 * * 3); cookiePW.setMaxAge(3600 * 24 * 3); response.addCookie(cookieUN);
			 * response.addCookie(cookiePW);
			 */
			try {
				Cookie cookieUN = new Cookie("username", URLEncoder.encode(username, "UTF-8"));
				Cookie cookiePW = new Cookie("passwordEncrypted", URLEncoder.encode(passwordEncrypted, "UTF-8"));
				cookieUN.setMaxAge(3600 * 24 * 3);
				cookiePW.setMaxAge(3600 * 24 * 3);
				response.addCookie(cookieUN);
				response.addCookie(cookiePW);
			} catch (UnsupportedEncodingException e) {
				Logger.infor(e);
			}
		}
	}

	private boolean isStillRemember(HttpServletRequest request) {
		return rememberData(request) != null;
	}

	private String[] rememberData(HttpServletRequest request) {
		try {
			String cookieUN = getCookieValue(request, "username");
			String cookiePW = getCookieValue(request, "passwordEncrypted");
			if(cookieUN == null || cookiePW == null) {
				return null;
			}
			String username = URLDecoder.decode(cookieUN, "UTF-8");
			String passwordEncrypted = URLDecoder.decode(cookiePW, "UTF-8");
			AppVars.user = userService.getUserByUserName(username);
			if (AppVars.user != null && username.equals(AppVars.user.getUserName())
					&& passwordEncrypted.equals(AppVars.user.getPassword())) {
				request.getSession().setAttribute("username", username);
				request.getSession().setMaxInactiveInterval(30 * 60);
				return new String[] { username, Authentication.getDecryptPassword(AppVars.user.getPassword()) };
			}
		} catch (UnsupportedEncodingException e) {
			Logger.infor(e);
		}
		return null;
	}

	private String getCookieValue(HttpServletRequest req, String cookieName) {
		return Arrays.stream(req.getCookies()).filter(c -> c.getName().equals(cookieName)).findFirst()
				.map(Cookie::getValue).orElse(null);
	}
}