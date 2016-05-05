package at.fh.swenga.samt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class UserManager {

	private List<UserModel> users = new ArrayList<UserModel>();

	public UserModel createUserModel() {
		return new UserModel();
	}

	public UserModel createUserModel(int id, String firstName, String lastName, String degreeCourse, String email,
			String password, String profilePicture) {
		return new UserModel(id, firstName, lastName, degreeCourse, email, password, profilePicture);
	}

	/**
	 * Add user to List
	 * 
	 * @param user
	 */
	public void addUser(UserModel user) {
		users.add(user);
	}

	/**
	 * Verify if list contains user with same Id
	 * 
	 * @param user
	 * @return
	 */
	public boolean contains(UserModel user) {
		return users.contains(user);
	}

	/**
	 * convenient method: true if list is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return users.isEmpty();
	}

	/**
	 * try to find UserModel with given Id return model, otherwise null
	 * 
	 * @param id
	 * @return
	 */
	public UserModel getUserById(int id) {
		Optional<UserModel> user = users.stream().filter(userModel -> userModel.getId() == id).findFirst();

		return user.orElse(null);
	}

	/**
	 * return list with all users
	 * 
	 * @return
	 */
	public List<UserModel> getAllUsers() {
		return users;
	}

	/**
	 * return a new list with all users where firstname or lastname contains
	 * search string
	 * 
	 * @param searchString
	 * @return
	 */
	public List<UserModel> getFilteredUsers(String searchString) {

		if (searchString == null || searchString.equals("")) {
			return users;
		}
		// check every user
		return users.stream().filter(userModel -> {
			return checkUser(userModel, searchString);
		}).collect(Collectors.toList());
	}

	private boolean checkUser(UserModel userModel, String searchString) {
		return ((userModel.getFirstName() != null && userModel.getFirstName().contains(searchString))
				|| (userModel.getLastName() != null && userModel.getLastName().contains(searchString)));
	}

	/**
	 * remove users with same id
	 * 
	 * @param id
	 * @return
	 */
	public boolean remove(int id) {
		return users.remove(new UserModel(id, null, null, null, null, null, null));
	}
}
