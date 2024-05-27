import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, Link } from 'react-router-dom';
import './Lecture.css'; // Import your CSS file for styling

export default function Lecture() {
    const [lecture, setLecture] = useState(null);
    const { lectureId } = useParams();

    useEffect(() => {
        axios.get(`http://localhost:8080/lectures/${lectureId}`)
            .then(res => {
                console.log(res);
                setLecture(res.data);
            })
            .catch(err => {
                console.error("Error loading lecture:", err);
            });
    }, [lectureId]);

    return (
        <div className="lecture-container">
            {lecture ? (
                <div className="lecture-content">
                    <h2 className="lecture-name">{lecture.name}</h2>
                    <div className="lecture-text-box">
                        <p className="lecture-text">{lecture.text}</p>
                    </div>
                    {/* Use Link to navigate to the "learners" page */}
                    <Link to={`/bookmark-lecture/${lecture.id}`} className="bookmark-button">Bookmark this lecture</Link>
                </div>
            ) : (
                <p>No lecture found</p>
            )}
        </div>
    );
}
