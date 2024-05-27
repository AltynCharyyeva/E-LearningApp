import React from 'react';

const Logout = () => {
  const handleLogout = () => {
    localStorage.removeItem('userToken');
    // Optionally make an API call to the logout endpoint (if implemented)
    window.location.href = '/loginUser'; // Redirect to login page
  };

  return (
    <button onClick={handleLogout}>Logout</button>
  );
};

export default Logout;
