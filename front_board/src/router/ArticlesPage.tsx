import { useEffect, useState  } from "react";
import { useNavigate } from 'react-router-dom';
import styled from "styled-components"
import { getArticlesApi } from "../api/Articles";
import mainImage from "../../assets/mainImage.jpg";

interface Article {
    title: string;
    hashtag: string;
    createBy: string;
    createAt: string;
}

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
const ButtonContainer = styled.div`
    display: flex;
    align-items: center;
    justify-content: right;
    height: 60px;
`;
const Input = styled.input`
    //flex-grow: 1;
    padding:  10px;
    border: 1px solid #ddd;
    border-radius: 5px;
`;
const InputButton = styled.button`
    //flex-shrink: 0;
    width: 5%;
    height: 50%;
    border-radius: 5px;
    border: none;
    background-color: #333;
    color: white;
    font-size: 13px;
    margin-right: 80%;
    margin-left: 10px;
    transition: background-color 0.3s;
    &:hover {
        background-color: gray;
    }
`;
const BottomButton = styled.button`
    //flex-shrink: 0;
    width: 7%;
    height: 60%;
    border-radius: 10px;
    background-color: #333;
    color: white;
    font-size: 15px;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
    &:hover {
        background-color: gray;
    }
`;
const BottomButtonText = styled.h2`
    font-size: 15px;
    color: white;
    line-height: 0.8;
`;
const Table = styled.table`
    width: 100%;
    text-align: left;
    border-collapse: collapse;
`;
const Th = styled.th`
    border-bottom: 2px solid #4e4e4e;
    background-color: #e5e5e5;
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

export default function Articles() {
    const [articles, setArticles] = useState<Article[]>([])
    const navigate = useNavigate(); 

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await getArticlesApi();
                setArticles(response.data._embedded.articles)
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
            
            <Table>
                <thead>
                <tr>
                    <Th>제목</Th>
                    {/* <Th>|</Th> */}
                    <Th>해시테그</Th>
                    <Th>작성자</Th>
                    <Th>작성일</Th>
                </tr>
                </thead>
                <tbody>
                    {articles.map((article, index) => (
                        <tr key={index}>
                        <Td1>{article.title}</Td1>
                        <Td2>{article.hashtag}</Td2>
                        <Td2>{article.createBy}</Td2>
                        <Td2>{article.createAt}</Td2>
                        </tr>
                    ))}
                </tbody>
            </Table>
            <ButtonContainer>
                <Input type="text" placeholder="입력하세요."/>
                <InputButton>검색</InputButton>
                <BottomButton>
                    <BottomButtonText onClick={onClick}>
                        글쓰기
                    </BottomButtonText>
                </BottomButton>
            </ButtonContainer>
        </Wrapper>
    )
}