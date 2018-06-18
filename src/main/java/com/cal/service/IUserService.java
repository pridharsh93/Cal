package com.cal.service;

import com.cal.model.UserProfile;

public interface IUserService 
{
	UserProfile getUserByID(int userId);
	boolean addUserProfile(UserProfile profile);
    void updateUserprofile(UserProfile profile);
    void deleteUserProfile(Integer userId);
}
