import { styled } from "styled-components";
import { useNavigate } from 'react-router-dom';
import mainImage from "../../assets/mainImage.jpg";


const TopVer = styled.nav`
    background-color: #333;
    //overflow: hidden;
    display: flex;
    justify-content: space-around;
    padding: 20px;

`;
const TopButton = styled.button`
    background: none;
    color: #ccc;
    text-align: center;
    border: none;
    cursor: pointer;
    font-size: 12px;
    transition: color 0.3s;
    &:hover {
        color: gray;
    }
`;
const HeaderContainer = styled.div`
  background-image: url(${mainImage});
  background-size: cover;
  background-position: center;
  padding: 50px;
  margin-bottom: 10px;
`;
const HeaderText = styled.h2`
  color: white;
  margin-left: -30px;
  transform: translateY(200%);
`;
const Wrapper = styled.div`
    //display: flex;
`;
const ArticleButton = styled.button`
    width: 100%;
    height: 100%;
    // margin-left: 47%;
    border-radius: 5px;
    background-color: #333;
    color: white;
    font-size: 20px;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
    &:hover {
        background-color: gray;
    }
`;

export default function HomePage(){
    const navigate = useNavigate(); 
    
    const onClick = () => {
        console.log('게시판페이지로 이동')
        navigate('/articles');
    };

    return(
        <Wrapper>
            <TopVer>
                <TopButton>카테고리1</TopButton>
                <TopButton>카테고리2</TopButton>
                <TopButton>카테고리3</TopButton>
                <TopButton>카테고리4</TopButton>
            </TopVer>
            <HeaderContainer>
                <HeaderText>Delivering all IT news from around the world</HeaderText>
            </HeaderContainer>
            <tr>
                <ArticleButton onClick={onClick}>게시판 이동</ArticleButton>
            </tr>
        </Wrapper>
    )
}