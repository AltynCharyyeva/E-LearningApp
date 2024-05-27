import React, { useState, useEffect } from 'react';
import axios from 'axios';
import cate from './images/cate.jpg';
import { Link } from 'react-router-dom';

import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

export default function Home() {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
        try {
            const result = await axios.get("http://localhost:8080/categories");
            setCategories(result.data);
        } catch (error) {
            console.error("Error loading categories:", error);
        }
    }

    return (
        <div>
            {categories.map(category => (
                <Card key={category.id} sx={{ maxWidth: 345 }}>
                    <CardMedia
                        component="img"
                        alt={category.categoryName}
                        height="140"
                        image={cate}
                    />
                    <CardContent>
                        <Typography gutterBottom variant="h5" component="div">
                            {category.categoryName}
                        </Typography>
                    </CardContent>
                    <CardActions>
                        <Link to="/courses"> {/* Use Link component to navigate */}
                            <Button size="small">Learn More</Button>
                        </Link>
                    </CardActions>
                </Card>
            ))}
        </div>
    );
}
