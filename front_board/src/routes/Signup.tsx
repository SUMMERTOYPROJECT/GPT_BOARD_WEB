import React, { useState } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { AuthSignupApi } from "../api/Auth";
// 가정: 회원가입 API가 다음과 같이 `AuthSignUpApi`라는 이름으로 구현되어 있음
// import { AuthSignUpApi } from "../api/AuthApi";

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
  background-color: #4caf50; // Use the same style as the SignupButton in the login page for consistency
  color: white;
  cursor: pointer;
  border: none;

  &:hover {
    background-color: #357a38;
  }
`;

export default function SignUp() {
  const [formData, setFormData] = useState({
    userId: '',
    userPassword: '',
    email: '',
    nickname: '',
    memo: '',
  });
  const navigate = useNavigate();

  const handleChange = (e:React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
        const response = await AuthSignupApi(formData);
        if(response.status === 200){
          console.log("회원가입이 정상적으로 처리되었습니다.")
          window.alert("회원가입 되었습니다.")
          navigate("/login")
        }
    } catch (error) {
         console.log(error)
         window.alert("회원가입 실패, 잠시 후 다시 시도해주세요.");
    }
   
    
  };

  return (
    <Wrapper>
      <H1>Welcome to My 😊 SignUp Page</H1>
      <Form onSubmit={handleSubmit}>
        <Input
          name="userId"
          value={formData.userId}
          placeholder="Username"
          type="text"
          required
          onChange={handleChange}
        />
        <Input
          name="userPassword"
          value={formData.userPassword}
          placeholder="Password"
          type="password"
          required
          onChange={handleChange}
        />
        <Input
          name="email"
          value={formData.email}
          placeholder="Email"
          type="email"
          required
          onChange={handleChange}
        />
        <Input
          name="nickname"
          value={formData.nickname}
          placeholder="Nickname"
          type="text"
          required
          onChange={handleChange}
        />
        <Input
          name="memo"
          value={formData.memo}
          placeholder="memo"
          type="text"
          required
          onChange={handleChange}
        />
        <SubmitButton value="Sign Up" />
      </Form>
    </Wrapper>
  );
}
