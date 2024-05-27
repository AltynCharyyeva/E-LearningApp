import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import TextField from "@mui/material/TextField";
import { styled } from '@mui/material/styles';
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";

const StyledForm = styled('form')({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  justifyContent: 'center',
  marginTop: '20px',
});

const StyledTextField = styled(TextField)({
  marginBottom: '40px',
  backgroundColor: '#f0f7ff',
  borderRadius: '10px',
});

const Container = styled(Box)({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  borderRadius: '15px',
  boxShadow: '0 4px 8px 0 rgba(0,0,0,0.2)',
  padding: '20px',
  backgroundColor: '#f0f7f8',
});

const SubmitButton = styled(Button)({
  marginTop: '20px',
  width: '50%',
  borderRadius: '25px',
  padding: '15px',
});

const LoginComponent = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate(); 

  const handleInput = (e) => {
    const { name, value } = e.target;
    if (name === 'username') {
      setUsername(value);
    } else if (name === 'password') {
      setPassword(value);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/users/loginUser', { username, password });
      const token = response.data.token;
      localStorage.setItem('userToken', token);
      // Redirect to protected area
      navigate("/courses");
    } catch (error) {
      console.error(error);
      // Handle login error
      setErrorMessage("Invalid username or password");
    }
  };

  return (
    <Container>
    <StyledForm onSubmit={handleSubmit}>
      {/* Username and password fields */}
      <StyledTextField
        variant="outlined"
        margin="normal"
        required
        fullWidth
        id="username"
        label="Username"
        name="username"
        autoComplete="string"
        value={username}
        onChange={handleInput}
        autoFocus
      />
      <StyledTextField
        variant="outlined"
        margin="normal"
        required
        fullWidth
        name="password"
        label="Password"
        type="password"
        id="password"
        value={password}
        onChange={handleInput}
        autoComplete="current-password"
      />
        <SubmitButton type="submit" variant="contained" color="primary" size="large">
          Login
        </SubmitButton>
      {errorMessage && <p>{errorMessage}</p>}
    </StyledForm>
    </Container>
  );
};

export default LoginComponent;
