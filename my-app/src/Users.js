import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function Users() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        try {
            const result = await axios.get("http://localhost:8080/learners");
            setUsers(result.data); // Set users in the state
        } catch (error) {
            console.error("Error loading users:", error);
        }
    }

    return (
        <div>
            <h1>User List</h1>
            {users.map((user, index) => (
                <div key={index} style={userBoxStyle}>
                    <p><strong>Username:</strong> {user.username}</p>
                    <p><strong>Email:</strong> {user.email}</p>
                    <p><strong>Courses:</strong></p>
                    <ul>
                        {user.enrolledCourses.map((course, courseIndex) => (
                            <li key={courseIndex}>{course.name}</li>
                        ))}
                    </ul>
                    <p><strong>Bookmarks: </strong></p>
                    <ul>
                        {user.bookmarks.map((lecture, lectureIndex) => (
                            <li key={lectureIndex}>{lecture.name}</li>
                        ))}
                    </ul>
                </div>
            ))}
        </div>
    );
}

const userBoxStyle = {
    backgroundColor: '#f0f8ff', // Light blue color
    padding: '20px',
    marginBottom: '20px',
    borderRadius: '5px',
};
