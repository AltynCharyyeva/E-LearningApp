import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function Courses() {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        loadCourses();
    }, []);

    const loadCourses = async () => {
        try {
            const result = await axios.get("http://localhost:8080/courses");
            setCourses(result.data);
        } catch (error) {
            console.error("Error loading courses:", error);
        }
    }

    return (
        <div>
            <h1>Course List</h1>
            {courses.map((course, index) => (
                <div key={index} style={boxStyle}>
                    <h2>{course.name}</h2>
                    <p>{course.description}</p>
                    <h3>Lectures:</h3>
                    <ul>
                        {course.lectures.map((lecture, lectureIndex) => (
                            <li key={lectureIndex}>
                                <Link to={`/lectures/${lecture.id}`}>{lecture.name}</Link>
                            </li>
                        ))}
                    </ul>
                    <Link to={`/take-course/${course.id}`}>
                        <button>Take this course</button>
                    </Link>
                </div>
            ))}
        </div>
    );
}

const boxStyle = {
    backgroundColor: '#f0f8ff', // Light blue color
    padding: '20px',
    marginBottom: '20px',
    borderRadius: '5px',
};
