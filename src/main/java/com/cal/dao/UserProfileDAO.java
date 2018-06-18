package com.cal.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cal.model.UserProfile;
@Transactional
@Repository
public class UserProfileDAO implements IUserProfileDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserProfileDAO.class);

	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public UserProfile getUserByID(int articleId) {
		return entityManager.find(UserProfile.class, articleId);
	}

	@Override
	public void addUserProfile(UserProfile profile) {
		entityManager.persist(profile);

	}

	@Override
	public void updateUserprofile(UserProfile profile) {
		
		
		entityManager.persist(profile);;
	}

	@Override
	public void deleteUserProfile(Integer userId) {
		entityManager.remove(getUserByID(userId));

	}

	@Override
	public boolean userExists(String email, Long phoneNumber) {

		String sql = "SELECT * FROM UserProfile WHERE email = ? OR phonenumber = ?;";
		Query query = null;
		try {
			query = entityManager.createNativeQuery(sql, UserProfile.class).setParameter(1, email)
					.setParameter(2, phoneNumber);
			
			if(query.getResultList().size() > 0)
				return true;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}			return false;
	}

}
