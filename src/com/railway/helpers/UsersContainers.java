package com.railway.helpers;

import java.util.HashMap;
import java.util.Map;

import com.railway.models.User;

public class UsersContainers {
		
		private static Map<Integer, User> users = new HashMap<>();
		
		public static void add(int userId, User user) {
			users.put(userId, user);
		}
		
		public static User getUserForUserId(int userId) {
			return users.get(userId);
		}
		
	
}
