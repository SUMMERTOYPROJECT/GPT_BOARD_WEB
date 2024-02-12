import { useEffect, useState  } from "react";
import { useNavigate, Link } from 'react-router-dom';
import styled from "styled-components"
import { getArticlesApi } from "../api/Articles";

interface Article {
    title: string;
    hashtag: string;
    nickname: string;
    createAt: string;
    id: string;
}

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
const StyledLink = styled(Link)`
    color: black;
    text-decoration: none;

    &:hover {
        color: gray;
        text-decoration: underline;
    }
`;

export default function Articles() {
    const [articles, setArticles] = useState<Article[]>([])
    const navigate = useNavigate(); 

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await getArticlesApi();
                console.log(response.data)
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

    return(
        <Wrapper>        
            <Table>
                <thead>
                <tr>
                    <Th>제목</Th>
                    <Th>해시테그</Th>
                    <Th>작성자</Th>
                    <Th>작성일</Th>
                </tr>
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