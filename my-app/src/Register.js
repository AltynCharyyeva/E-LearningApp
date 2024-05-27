import React, { useState } from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Container from "@mui/material/Container";
import axios from "axios";
import Grid from "@mui/material/Grid";
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [errors, setErrors] = useState({});
    const navigate = useNavigate();

    const handleInput = (event) => {
        const { value, name } = event.target;
        setUsername(name === 'username' ? value : username);
        setPassword(name === 'password' ? value : password);
        setEmail(name === 'email' ? value : email);
    };

    const onSubmitFunction = event => {
        event.preventDefault();
        const credentials = {
            username: username,
            password: password,
            email: email
        };

        axios.post("http://localhost:8080/users/register", credentials)
            .then(res => {
                console.log("Success", res.data);
                // Redirect to login page or any other page after successful registration
                navigate("/login");
            })
            .catch(error => {
                if (error.response && error.response.data) {
                    setErrors(error.response.data);
                } else {
                    console.log("Error", error);
                }
            });
    };

    return (
        <Container maxWidth="sm">
            <div>
                <Grid>
                    <form onSubmit={onSubmitFunction}>
                        <TextField
                            variant="outlined"
                            margin="normal"
                            required
                            fullWidth
                            id="email"
                            label="Email"
                            name="email"
                            autoComplete="string"
                            onChange={handleInput}
                            autoFocus
                            error={errors.email}
                            helperText={errors.email && errors.email}
                        />
                        <TextField
                            variant="outlined"
                            margin="normal"
                            required
                            fullWidth
                            id="username"
                            label="Username"
                            name="username"
                            autoComplete="string"
                            onChange={handleInput}
                            autoFocus
                            error={errors.username}
                            helperText={errors.username && errors.username}
                        />
                        <TextField
                            variant="outlined"
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            id="password"
                            onChange={handleInput}
                            autoComplete="new-password"
                            error={errors.password}
                            helperText={errors.password && errors.password}
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            color="primary"
                        >
                            Register
                        </Button>
                    </form>
                </Grid>
            </div>
        </Container>
    );
};

export default Register;
