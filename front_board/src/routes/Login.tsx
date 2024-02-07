import React, { useState } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../security/AuthContext";

const Wrapper = styled.div`
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  font-size: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
`;

const H1 = styled.h1`
  margin-bottom: 2rem;
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  gap: 1rem;
`;

const Input = styled.input`
  padding: 0.5rem;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;

  &:focus {
    outline: none;
    border-color: #1d9bf0;
  }
`;

const SubmitButton = styled(Input).attrs({ type: 'submit' })`
  background-color: #1d9bf0;
  color: white;
  cursor: pointer;
  border: none;

  &:hover {
    background-color: #147ab6;
  }
`;

const SignupButton = styled.button`
  margin-top: 1rem;
  padding: 0.5rem;
  font-size: 1rem;
  background-color: #4caf50; // A different color to distinguish from the login button
  color: white;
  cursor: pointer;
  border: none;
  border-radius: 4px;

  &:hover {
    background-color: #357a38;
  }
`;

export default function Login() {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate()
  const auth = useAuth()


  const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault(); // 기본 동작 막기
    console.log("로그인 요청");
  
    const userAccount = {
      "username": userId,
      "password": password
    }
    try {
      const success = await auth.login(userAccount)
      if(success){
        console.log(auth.username, auth.token)
        navigate("/")
      }
      else{
        window.alert("아이디 또는 비밀번호가 일치하지 않습니다!!")
      }
    } catch (error) {
      window.alert("아이디 또는 비밀번호가 일치하지 않습니다.")
    }
  }

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    if (name === "userId") {
      setUserId(value);
    } else if (name === "password") {
      setPassword(value);
    }
  };

  const onSignup = () => {
    console.log("회원가입 페이지로 이동");
    navigate('/signup')
    
  };


  return (
    <Wrapper>
      <H1>Welcome to My 😆 Login Page</H1>
      <Form onSubmit={onSubmit}>
        <Input
          name="userId"
          value={userId}
          placeholder="UserId"
          type="text"
          required
          onChange={onChange}
        />
        <Input
          name="password"
          value={password}
          placeholder="Password"
          type="password"
          required
          onChange={onChange}
        />
        <SubmitButton value="Login" />
        <SignupButton onClick={onSignup}>Sign Up</SignupButton>
      </Form>
      
    </Wrapper>
  );
}
