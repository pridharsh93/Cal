package com.cal.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cal.dao.IUserProfileDAO;
import com.cal.model.UserProfile;

public class UserService implements IUserService {

	@Autowired
	IUserProfileDAO userDAO;

	@Override
	public UserProfile getUserByID(int articleId) {

		return userDAO.getUserByID(articleId);
	}

	@Override
	public boolean addUserProfile(UserProfile profile) {

		if (userDAO.userExists(profile.getEmail(), profile.getPhoneNumber()))
			return false;
		else
			userDAO.addUserProfile(profile);
		return true;

	}

	@Override
	public void updateUserprofile(UserProfile profile) {

		UserProfile userProfile = getUserByID(profile.getUserID());

		boolean isModified = false;

		if (!userProfile.getPassword().equals(profile.getPassword())) {
			userProfile.setPassword(profile.getPassword());
			isModified = true;
		} else if (!userProfile.getSessionId().equalsIgnoreCase(profile.getSessionId())) {
			userProfile.setSessionId(profile.getSessionId());
			isModified = true;
		} else if (userProfile.getPicture() != profile.getPicture()) {
			userProfile.setPicture(profile.getPicture());
			isModified = true;
		}

		if (isModified)
			userDAO.updateUserprofile(userProfile);

	}

	@Override
	public void deleteUserProfile(Integer userId) {
		userDAO.deleteUserProfile(userId);

	}

}
