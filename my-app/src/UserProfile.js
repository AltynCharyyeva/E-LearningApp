import React, { useState, useEffect } from 'react';
import axios from 'axios';

const UserProfile = ({ children }) => {
  const [user, setUser] = useState(null);
  const token = localStorage.getItem('userToken');

  useEffect(() => {
    const fetchUserProfile = async () => {
      if (token) {
        try {
          const response = await axios.get('http://localhost:8080/users/profile', {
            headers: { Authorization: `Bearer ${token}` },
          });
          setUser(response.data);
        } catch (error) {
          console.error(error);
          // Handle errors (e.g., invalid token)
        }
      }
    };

    fetchUserProfile();
  }, [token]);

  // Display user profile information based on the 'user' state
  return (
    <div>
      {user ? (
        <div>
          <h1>Welcome, {user.username}</h1>
          <h1>{user.email}</h1>
        </div>
      ) : (
        <p>You need to be logged in to access this content.</p>
      )}
      {children}
    </div>
  );
};

export default UserProfile;
