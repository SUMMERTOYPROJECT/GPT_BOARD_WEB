import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../security/AuthContext"; // 가정: AuthContext가 로그인 상태를 관리

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background: linear-gradient(135deg, #6e8efb, #88d3ce); /* 그라데이션 배경 추가 */
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0,0,0,0.2); /* 그림자 효과 추가 */
  border-radius: 10px; /* 경계 둥글기 추가 */
  color: #fff; /* 글자색 변경 */
`;

const WelcomeMessage = styled.h1`
  font-size: 2.5rem; /* 폰트 크기 증가 */
  margin-bottom: 2rem;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.2); /* 텍스트에 그림자 효과 추가 */
`;

const NavigationButton = styled.button`
  padding: 12px 24px; /* 패딩 조정 */
  margin: 10px;
  font-size: 1.2rem; /* 폰트 크기 조정 */
  border-radius: 20px; /* 버튼 둥글기 조정 */
  cursor: pointer;
  border: none;
  background-color: #fff;
  color: #1d9bf0; /* 버튼 내 글자색 변경 */
  transition: background-color 0.3s, transform 0.3s; /* 애니메이션 효과 추가 */

  &:hover {
    background-color: #1d9bf0;
    color: #fff;
    transform: scale(1.05); /* 마우스 오버 시 확대 효과 */
  }
`;
export default function Home() {
    const navigate = useNavigate();
    const auth = useAuth(); // 가정: useAuth()는 현재 로그인한 사용자의 정보를 반환
  
    const handleLogout = () => {
      auth.logout(); // 로그아웃 처리
      navigate("/login");
    };
  
    return (
      <Container>
        {/* 로그인 상태에 따라 다른 메시지 또는 버튼 표시 */}
        {auth.isAuthenticated ? (
          <>
            <WelcomeMessage>Welcome, {auth.username}!</WelcomeMessage>
            <NavigationButton onClick={() => navigate("/board")}>Go to Board</NavigationButton>
            <NavigationButton onClick={handleLogout}>Logout</NavigationButton>
          </>
        ) : (
          <>
            <WelcomeMessage>Welcome, Guest!</WelcomeMessage>
            <NavigationButton onClick={() => navigate("/login")}>Login</NavigationButton>
          </>
        )}
      </Container>
    );
  }