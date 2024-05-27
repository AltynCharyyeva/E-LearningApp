import React, { useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

export default function TakeCourse() {
    const [username, setUsername] = useState('');
    const { courseId } = useParams();
    const navigate = useNavigate();

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handleSubmit = async (event) => {
    event.preventDefault();
    try {
        // Send a POST request to the backend to enroll the user in the course
        const response = await axios.put('http://localhost:8080/learners/take_course', {
            username: username, // Assuming username is defined somewhere in your component's state
            courseId: courseId // Assuming courseId is defined somewhere in your component's state
        });
        console.log('Username: ', username)
        console.log('Course id: ', courseId)
        console.log('User enrolled successfully:', response.data);
        navigate(`/${courseId}/lectures`);
        // Add any additional actions you want to perform after successful enrollment
    } catch (error) {
        console.error('Error enrolling user:', error);
    }
};

    return (
        <div>
            <h2>Enroll in Course</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Username:
                    <input type="text" value={username} onChange={handleUsernameChange} />
                </label>
                <button type="submit">Take This Course</button>
            </form>
        </div>
    );
}
