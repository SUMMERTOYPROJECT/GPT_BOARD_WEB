import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { deleteArticleApi, getArticleApi } from '../api/Articles'; // 게시글 상세 정보를 가져오는 API 함수
import { useAuth } from '../security/AuthContext';
import CommentComponent from '../components/Comment';

interface Article {
    title: string;
    hashtag: string;
    nickname: string;
    createAt: string;
    content: string;
}

const Wrapper = styled.div`
    width: 60%;
    height: 100%;
`;
const CenteredContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: 130vw;
    border-collapse: collapse;
`;
const ArticleTable = styled.table`
    width: 60%;
    box-shadow: 1px 2px 2px 3px rgba(0,0,0,0.1);
`;
const TitleTable = styled.td`
    padding: 10px;
    display: block; 
    background-color: #f2bb2f;
    color: white;
`;
const ContentTable = styled.td`
    //border-bottom: 1px solid #ddd;
    display: block;
    width: 100%;
    height: 20rem;
    padding: 10px;
`;
const InfoTable = styled.td`
    padding: 10px;
    border-bottom: 1px solid #ddd;
    display: flex;
    justify-content: space-between;
`;
const HashtagTable = styled.td`
    border-top: 1px solid #ddd;
    padding: 10px;
    display: block; 
`;
const ButtonContainer = styled.div`
    display: flex;
    justify-content: space-between;
    margin-top: 0.5rem;
    width: 60%;
    //background-color: black;
`;
const EditDeleteContainer = styled.div`
    display: flex;
    gap: 10px;
`;
const Button = styled.button`
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: #fff;
    font-size: 16px;
    cursor: pointer;
    margin: 0.1rem;
    transition: background-color 0.3s;
    &:hover {
        background-color: #0056b3;
    }
`;

function ArticleDetail() {
    const { id } = useParams(); // URL에서 게시글 ID 추출
    const [article, setArticle] = useState<Article>();
    const navigate = useNavigate(); 
    const auth = useAuth();
    
    useEffect(() => {
        const fetchArticle = async () => {
            try {
                const response = await getArticleApi(id); // API 호출
                // console.log(response);
                setArticle(response.data);
            } catch (error) {
                // 에러 처리
            }
        };

        fetchArticle();
    }, [id]);

    if (!article) {
        return <div>Loading...</div>;
    }

    const onBackButton = () => {
        console.log('뒤로 이동');
        navigate('/articles');
    };

    const onDeleteButton = () => {
        console.log('삭제 요청 완료');
        const handleSubmit = async () => {
            try {
                const response = await deleteArticleApi(id);
        
                if (response.status === 200) {
                    window.alert('삭제 완료!!');
                    navigate('/articles');
                    console.log('삭제 완료');
                } else {
                    console.log('삭제 실패');
                }
            } catch (error) {
                console.log('에러 발생:', error);
            }
        }
        handleSubmit()
    };

    const onCorrectionButton = () => {
        console.log('수정 시작');
        // navigate('/articles/change', { state: { id: id } });
        navigate(`/articles/${id}/change`)
    };
    return (
        <>
            <CenteredContainer>
                <Wrapper>
                    <ArticleTable>
                        <TitleTable>{article.title}</TitleTable>      
                        <InfoTable>
                            <span>{article.nickname}</span>
                            <span>{article.createAt}</span>
                        </InfoTable> 
                        <ContentTable>{article.content}</ContentTable>                  
                        <HashtagTable>{article.hashtag}</HashtagTable>    
                    </ArticleTable>                   
                    {auth.username === article.nickname ? (
                        <ButtonContainer>
                            <Button onClick={onBackButton}>뒤로</Button>
                            <EditDeleteContainer>
                                <Button onClick={onCorrectionButton}>수정</Button>
                                <Button onClick={onDeleteButton}>삭제</Button>
                            </EditDeleteContainer>
                        </ButtonContainer>
                            ):(
                            <ButtonContainer>
                                    <Button onClick={onBackButton}>뒤로</Button>
                            </ButtonContainer>
                    )}
                    <CommentComponent/>
                </Wrapper>
            </CenteredContainer>
        </>
    );
}

export default ArticleDetail;
