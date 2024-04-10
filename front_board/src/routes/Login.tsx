import styled, {createGlobalStyle} from "styled-components";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../security/AuthContext"; // 가정: AuthContext가 로그인 상태를 관리
import backgroundImage from "../../assets/1.webp";

const GlobalStyle = createGlobalStyle`
  html, body {
    margin: 0;
    padding: 0;
    overflow-x: hidden;
  }
  #root {
    width: 100vw;
    height: 100vh;
    overflow: hidden;
  }
`;
const Wrapper = styled.div`
  background-image: url(${backgroundImage});
  background-size: 100%;
  background-position: center;
  background-repeat: no-repeat;
  height: 100vh;
  width: 100vw;
`;
const CenterBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 35%;
  max-width: '100%';
  height: auto;
  background-color: rgba(255, 255, 255, 0.8);
  box-shadow: 1px 1px 3px rgba(0,0,0,0.1);
  border-radius: 10px;
  padding: 20px;
`;
const WelcomeMessage = styled.h1`
  font-size: 3.5vw;
  color: #0e6be5;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.2);
  white-space: nowrap;
  margin-bottom: 20px;
`;
const Form = styled.form`
  align-items: center;
  display: flex;
  flex-direction: column;
  width: 100%;
`;
const Input = styled.input`
  width: 80%;
  margin-bottom: 1rem;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;

  &:focus {
    outline: none;
    border-color: #e59a0e;
  }
`;
const SubmitButton = styled(Input).attrs({ type: 'submit' })`
  font-size: 1.5vw;
  border-radius: 5px;
  width: 85%;
  //height: 5vh;
  background-color: #0e6be5;
  color: white;
  cursor: pointer;
  border: 1px solid #0e6be5;
  margin-bottom: 1rem;

  &:hover {
    background-color: #e59a0e;
    border: 1px solid #e59a0e;
    color: white;
  }
`;
const SignupButton = styled.button`
  width: 85%;
  //height: 5vh;
  font-size: 1.5vw;
  border-radius: 5px;
  padding: 0.5rem;
  background-color: white;
  color: #0e6be5;
  cursor: pointer;
  border: 1px solid #0e6be5;
  margin-top: -0.5rem; 

  &:hover {
    background-color: white;
    border: 1px solid #e59a0e;
    color: #e59a0e;
  }
`;
const FindUserInfo = styled.button`
  font-size: 1vw;
  padding: 0;
  width: 85%;
  height: 5vh;
  color: #0e6be5;
  background-color: transparent;
  cursor: pointer;
  border: none;
  margin-bottom: 1rem;

  &:hover {
    background-color: transparent;
    color: #e59a0e;
  }
`;

export default function Home() {
    const navigate = useNavigate();
    const auth = useAuth(); // 가정: useAuth()는 현재 로그인한 사용자의 정보를 반환
    const [userId, setUserId] = useState("");
    const [password, setPassword] = useState("");

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
          navigate("articles")
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

    const onFind = () => {
      console.log("아이디/비밀번호 찾기 페이지로 이동");
    }

  return (
    <>
    <GlobalStyle/>
      <Wrapper>
        <CenterBox>
          <>
            <Form onSubmit={onSubmit}>
              <WelcomeMessage>Welcome!</WelcomeMessage>
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
              <FindUserInfo onClick={onFind} type="button">아이디 / 비밀번호 찾기</FindUserInfo>
            </Form>    
          </>
        </CenterBox>
      </Wrapper>
    </>
  );
}