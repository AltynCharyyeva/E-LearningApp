import React, { useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

export default function BookmarkLecture() {
    const [username, setUsername] = useState('');
    const { lectureId } = useParams();
    const navigate = useNavigate();

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handleSubmit = async (event) => {
    event.preventDefault();
    try {
        // Send a POST request to the backend to enroll the user in the course
        const response = await axios.put('http://localhost:8080/learners/bookmark', {
            username: username, // Assuming username is defined somewhere in your component's state
            lectureId: lectureId // Assuming courseId is defined somewhere in your component's state
        });
        console.log('Username: ', username)
        console.log('Lecture id: ', lectureId)
        console.log('User enrolled successfully:', response.data);
        navigate(`/learners`);
        // Add any additional actions you want to perform after successful enrollment
    } catch (error) {
        console.error('Error enrolling user:', error);
    }
};

    return (
        <div>
            <h2>Bookmerk the lecture</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Username:
                    <input type="text" value={username} onChange={handleUsernameChange} />
                </label>
                <button type="submit">Bookmark this Lecture</button>
            </form>
        </div>
    );
}
