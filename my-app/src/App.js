import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Users from './Users';
import Home from './Home';
import Courses from './Courses';
import Navbar from './Navbar'; // Import the Navbar component
import Login from './Login';
import Register from './Register';
import Lecture from './Lecture'; // Import the Lecture component
import TakeCourse from './TakeCourse';
import Lectures from './Lectures';
import BookmarkLecture from './BookmarkLecture';
import LoginComponent from './LoginComponent';
import UserProfile from './UserProfile';
import Logout from './Logout';

function App() {
  return (
    <Router>
      <div className="App">
        <header className="App-header">
          {/* Your existing header content */}
          {/* Include the Navbar component */}
          <Navbar />
        </header>
        <main>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/learners" element={<Users />} />
            <Route path="/courses" element={<Courses />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/lectures/:lectureId" element={<Lecture />} /> {/* Route for Lecture with lectureId parameter */}
            <Route path="/take-course/:courseId" element={<TakeCourse />} /> 
            <Route path="/:courseId/:lectures" element={<Lectures />} />
            <Route path="/bookmark-lecture/:lectureId" element={<BookmarkLecture />} />
            <Route path="/loginUser" element={<LoginComponent />} />
            <Route path="/profile" element={<UserProfile />} />
            <Route path="/logout" element={<Logout />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
