import { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import topImage from "../../assets/recive.png";
import backgroundImage from "../../assets/login_background.png";

interface Article {
    title: string;
    hashtag: string;
    nickname: string;
    createAt: string;
    id: string;
}

const HeaderContainer = styled.div`
    background-image: url(${topImage});
    background-size: contain;
    background-position: center;
    background-repeat: no-repeat;
    height: 20vh;
    width: 20vw;
    margin-bottom: -10.4rem;
    margin-left: 20rem;
`;
const Wrapper = styled.div`
    //background-image: url(${backgroundImage});
    background-size: 80%;
    background-position: center;
    background-repeat: no-repeat;
    display: flex;
    flex-direction: column;
`;
const LogoutButton = styled.button`
  font-size: 1vw;
  //padding: 0;
  width: 5%;
  height: 5vh;
  color: #0e6be5;
  background-color: transparent;
  cursor: pointer;
  border: none;
  margin-left: 67rem;

  &:hover {
    background-color: transparent;
    color: #e59a0e;
  }
`;
const TableContainer = styled.div`
    display: flex;
    justify-content: center;
    margin-top: 10%;
    width: 100%; 
    height: 100%;
`;
const Table = styled.table`
    width: 60%;
    text-align: left;
    border-collapse: collapse;
`;
const ButtonContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;

    margin-top: 1%;
    width: 100%; 
    height: 100%;
`;
const Input = styled.input`
    padding:  10px;
    border: 1px solid #ddd;
    border-radius: 5px;
`;
const InputButton = styled.button`
    width: 5%;
    height: 2rem;
    border-radius: 5px;
    border: none;
    background-color: #0e6be5;
    color: white;
    font-size: 15px;
    margin-left: 1%;
    transition: background-color 0.3s;
    &:hover {
        background-color: #e59a0e;
    }
`;
const BottomButton = styled.button`
    margin-left: 35%;
    width: 7%;
    height: 60%;
    border-radius: 10px;
    background-color: #0e6be5;
    color: white;
    font-size: 15px;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
    &:hover {
        background-color: #e59a0e;
    }
`;
const BottomButtonText = styled.h2`
    font-size: 15px;
    color: white;
    line-height: 0.8;
`;
const Th = styled.th`
    background-color: #0e6be5;
    color: white;
    padding: 8px;
    font-weight: bold;
    font-size: 15px;
    text-align: center;
`;
const Td1 = styled.td`
    border-bottom: 1px solid #ddd;
    padding: 8px;
`;
const Td2 = styled.td`
    border-bottom: 1px solid #ddd;
    padding: 8px;
    text-align: center;
`;
const StyledLink = styled(Link)`
    color: #333;
    text-decoration: none;

    &:hover {
        color: #e59a0e;
        text-decoration: underline;
    }
`;

export default function ArticleSearchPage() {
    const location = useLocation();
    const data = location.state;
    const [articles, setArticles] = useState<Article[]>([])
    const navigate = useNavigate()

    useEffect(() => {
        setArticles(data.data);
    }, []);
 
    const onLogout = () => {
        console.log('로그아웃');
        navigate('/');
    };


    return(
        <Wrapper>
            <LogoutButton onClick={onLogout}>Logout</LogoutButton>
            <HeaderContainer/>
            <TableContainer>
                <Table>
                    <thead>
                        <Th>제목</Th>
                        <Th>해시테그</Th>
                        <Th>작성자</Th>
                        <Th>작성일</Th>
                    </thead>
                    <tbody>
                        {articles.map((article, index) => {
                            const formattedDate = article.createAt.split('T')[0]; // 'T'를 기준으로 나누기
                            return (
                                <tr key={index}>
                                    <Td1>
                                        {/* <StyledLink to={`/articles/${article.id}`}> ${article.id} */}
                                            {article.title}
                                        {/* </StyledLink> */}
                                    </Td1>
                                    <Td2>{article.hashtag}</Td2>
                                    <Td2>{article.nickname}</Td2>
                                    <Td2>{formattedDate}</Td2> {/* 변환된 날짜 사용 */}
                                </tr>
                            );
                        })}
                    </tbody>
                </Table>
            </TableContainer>
            <ButtonContainer>
    
            </ButtonContainer>
        </Wrapper>
    )
}
