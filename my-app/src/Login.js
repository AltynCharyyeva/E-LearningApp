import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate instead of useHistory
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Container from "@mui/material/Container";
import axios from "axios";
import Grid from "@mui/material/Grid";

const Login = () => {
    const [credentials, setCredentials] = useState({ username: "", password: "" });
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate(); // Use useNavigate instead of useHistory

    const handleInput = event => {
        const { value, name } = event.target;
        setCredentials(prevCredentials => ({
            ...prevCredentials,
            [name]: value
        }));
    };

    const onSubmitFunction = event => {
        event.preventDefault();
        axios.post("http://localhost:8080/users/login", credentials)
            .then(res => {
                const loginSuccess = res.data;
                if (loginSuccess.role === "USER") {
                    localStorage.setItem("role", loginSuccess.role);
                    navigate("/courses"); // Use navigate to navigate to /courses
                } else {
                    setErrorMessage("Invalid username or password");
                }
            })
            .catch(error => {
                console.log(error);
                setErrorMessage("Error logging in. Please try again later.");
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
                            id="username"
                            label="Username"
                            name="username"
                            autoComplete="string"
                            onChange={handleInput}
                            autoFocus
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
                            autoComplete="current-password"
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            color="primary"
                        >
                            Sign In
                        </Button>
                        {errorMessage && <p>{errorMessage}</p>}
                    </form>
                </Grid>
            </div>
        </Container>
    );
};

export default Login;
