import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';

export default function Lectures() {
    const [lectures, setLectures] = useState([]);
    const { courseId } = useParams();

    useEffect(() => {
        // Fetch lectures for the given courseId
        const fetchLectures = async () => {
            try {
                console.log("course id: ", courseId)
                const response = await axios.get(`http://localhost:8080/courses/${courseId}/lectures`);
                setLectures(response.data);
            } catch (error) {
                console.error('Error fetching lectures:', error);
            }
        };

        fetchLectures();
    }, [courseId]);

    return (
        <div>
            <h2>Lectures</h2>
            <ul>
                {lectures.map((lecture) => (
                    <li key={lecture.id}>
                        <Link to={`/lectures/${lecture.id}`}>{lecture.name}</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}
