/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;

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

import daos.ReadingDAO;
import daos.SpeakingDAO;
import daos.UserDAO;
import daos.WritingDAO;
import helper.Authentication;
import helper.Logger;
import models.AppVars;
import models.Reading;
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

	// admin
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String admin(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Logger.infor("@RequestMapping_admin");
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
		Logger.infor("@RequestMapping_reading");

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
		if (isAuthenticated(request))
			return index(request, response, model);
		Logger.infor("@RequestMapping_login");
		return "login";
	}

	// check login
	@RequestMapping(value = "check-login", method = RequestMethod.POST)
	public String checklogin(@ModelAttribute(value = "loginbean") User loginbean, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		Logger.infor("@RequestMapping_check-login");
		String username = loginbean.getUserName();
		String password = loginbean.getPassword();
		AppVars.user = userService.getUserByUserName(username);
		if (AppVars.user != null && username.equals(AppVars.user.getUserName())
				&& password.equals(Authentication.getDecryptPassword(AppVars.user.getPassword()))) {
			request.getSession().setAttribute("username", username);
			request.getSession().setMaxInactiveInterval(24 * 60 * 60);
			Cookie cookieUN = new Cookie("username", username);
			Cookie cookiePW = new Cookie("password", Authentication.getEncryptPassword(password));
			cookieUN.setMaxAge(3600 * 24 * 3);
			cookiePW.setMaxAge(3600 * 24 * 3);
			response.addCookie(cookieUN);
			response.addCookie(cookiePW);
			return index(request, response, model);
		}
		model.put("checkLogin", "Invalid username or password!");
		return "login";
	}

	private boolean isAuthenticated(HttpServletRequest request) {
		return false;
	}

}