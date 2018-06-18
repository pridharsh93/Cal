package com.cal.dao;

import com.cal.model.UserProfile;

/**
 * @author vignesh
 *
 */
public interface IUserProfileDAO {
	
	UserProfile getUserByID(int userID);
    void addUserProfile(UserProfile profile);
    void updateUserprofile(UserProfile profile);
    void deleteUserProfile(Integer userId);
    boolean userExists(String email, Long phoneNumber);

}
