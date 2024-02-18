import { ReactEventHandler, ReactHTML, ReactHTMLElement, useEffect, useState  } from "react";
import { useNavigate, Link } from 'react-router-dom';
import styled from "styled-components"
import { getArticlesApi, searchArticleApi } from "../api/Articles";
import topImage from "../../assets/recive.png";
import backgroundImage from "../../assets/login_background.png";
import { apiClinet } from "../api/ApiClient";

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
    @media (max-width: 768px) {
        width: 40vw;
        height: 300px;
        margin-left: 10rem;
        margin-top: -5rem;
    }
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

  @media (max-width: 768px) {
        font-size: 2vw;
        width: 30%;
        margin-left: 60%;
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
    @media (max-width: 768px) {
        flex-direction: column;
        align-items: center;
        //align-items: stretch;
        width: 'auto';
    }
`;
const Input = styled.input`
    padding:  10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-right: 10px; // 마진 감소

    @media (max-width: 768px) {
        font-size: 12px;
        width: 47%;
        margin-right: 0;
        margin-bottom: 10px;
    }
`;
const InputButton = styled.button`
    min-width: 50px; 
    width: 5%;
    height: 2.5rem;
    border-radius: 10px;
    background-color: #0e6be5;
    color: white;
    font-size: 15px;
    margin-left: 1%;
    transition: background-color 0.3s;
    margin-left: 1%;
    cursor: pointer;
    border: none;
    &:hover {
        background-color: #e59a0e;
    }
    @media (max-width: 768px) {
        font-size: 15px;
        width: 50%;
        margin-left: 0;
        margin-bottom: 10px;
    }
`;
const BottomButton = styled.button`
    min-width: 90px; 
    margin-left: 30%;
    width: 7%;
    height: 60%;
    border-radius: 10px;
    background-color: #0e6be5;
    color: white;
    font-size: 15px;
    border: none;
    cursor: pointer;
    //margin-left: 5%;

    transition: background-color 0.3s;
    &:hover {
        background-color: #e59a0e;
    }
    @media (max-width: 768px) {
        font-size: 12px;
        width: 50%;
        //margin-right: 20rem;
        margin-left: 0;
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
const StyledSelect = styled.select`
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #ddd;
  background-color: white;
  color: #333;
  font-size: 15px;
  margin-right: 10px;

  @media (max-width: 768px) {
    font-size: 12px;
    padding: 8px;
    //margin-left: 24rem;
    margin-right: 0; // 화면이 작을 때 마진 제거
    margin-bottom: 10px;
    width: 50%;
  }

  &:hover {
    border-color: #0e6be5;
  }

  &:focus {
    outline: none;
    border-color: #e59a0e;
  }
`;


export default function AriticlesHome() {
    const [articles, setArticles] = useState<Article[]>([])
    const navigate = useNavigate(); 


    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await getArticlesApi();
                setArticles(response.data)
            } catch (error) {
                // 에러 처리
            }
        };
    
        fetchData();
    }, []);

    const onClick = () => {
        console.log('글쓰기페이지로 이동')
        navigate('/articles/save');
    };

    const onLogout = () => {
        console.log('로그아웃');
        navigate('/');
    };

    const onSearch = () => {
        const searchArticle = async () => {  
            try{
                const response = await searchArticleApi(selectedValue, keyword)
                if(response.status === 200){
                    console.log(response)
                    if(response.data.length === 0){
                        window.alert('일치하는 게시글이 없습니다.')
                    }
                    else{
                        console.log(response.data)
                        navigate('/articles/search', { state: { data: response.data } })
                    }
                }
            }
            catch(e){
                console.log(e)
            }  
        };
        searchArticle();
    };

    const [selectedValue, setSelectedValue] = useState('');
    const [keyword, setKeyword] = useState('');
    const handleSelectChange = (event: any) => {
        setSelectedValue(event.target.value);
    };
    const handleKeywordChange = (event: any) => {
        setKeyword(event.target.value);
    }
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
                                        <StyledLink to={`/articles/${article.id}`}> {/* ${article.id} */}
                                            {article.title}
                                        </StyledLink>
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
            {/* <label for="pet-select">Choose a pet:</label> */}
                <StyledSelect id="pet-select" value={selectedValue} onChange={handleSelectChange}>
                    <option value="">선택</option>
                    <option value="TITLE">제목</option>
                    <option value="CONTENT">내용</option>
                    <option value="ID">아이디</option>
                    <option value="NICKNAME">닉네임</option>
                    <option value="HASHTAG">해시태그</option>
                </StyledSelect>

                <Input type="text" placeholder="입력하세요."onChange={handleKeywordChange}/>
                <InputButton onClick={onSearch}>검색</InputButton>
                <BottomButton>
                    <BottomButtonText onClick={onClick}>
                        게시글 작성
                    </BottomButtonText>
                </BottomButton>
            </ButtonContainer>
        </Wrapper>
    )
}

/* 
{auth.isAuthenticated ? (
    <>
        <WelcomeMessage>Welcome, {auth.username}!</WelcomeMessage>
        <MoveButton onClick={() => navigate("/articles")}>Go to Board</MoveButton>
        <LoginButton onClick={handleLogout}>Logout</LoginButton>
    </>
    ) : (

    )}
*/