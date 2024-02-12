import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { deleteArticleApi, getArticleApi, putArticleApi } from '../api/Articles'; // 게시글 상세 정보를 가져오는 API 함수
import { useAuth } from '../security/AuthContext';

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
const CommentContainer = styled.div`
    width: 60%;
    height: 15%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #50505011;
    border-radius: 10px;
    margin-top: 0.3rem;
    flex-direction: column; 
`;
const CommentText = styled.text`
    font-size: 18px;
    color: black;
    margin-right: 36rem;
    margin-top: 1.5rem;
`;
const CommentInputGroup = styled.div`
    display: flex;
    align-items: center;
    width: 100%;
    padding: 0.5rem;
`;
const CommentInput = styled.input`
    width: 80%;
    height: 20%;
    //margin-top: 0rem;
    margin-left: 1rem;
    padding: 0.8rem;
    border: 1px solid #ccc;
    border-radius: 4px;

    &:focus {
    outline: none;
    border-color: #e59a0e;
    }  
`;
const CommentButton = styled.button`
    //padding: 10px 20px;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: #fff;
    font-size: 16px;
    cursor: pointer;
    margin: 0.5rem;
    margin-top: 0.5rem;
    transition: background-color 0.3s;
    &:hover {
        background-color: #0056b3;
    }
`;

function ArticleDetail() {
    const { id } = useParams(); // URL에서 게시글 ID 추출
    const [article, setArticle] = useState<Article>();
    const [comment, setComment] = useState([]);
    const navigate = useNavigate(); 
    const auth = useAuth();

    // const fetchReviews = async () => {
    //     try {
    //         // 댓글 데이터를 가져오는 API 호출
    //         const response = await getCommentListApi(id); 
    //         if (response.status === 200) {
    //             setComment(response.data);
    //         } else {
    //             // API 호출 실패 시 적절한 에러 처리
    //             console.error('Failed to fetch reviews: ', response);
    //         }
    //     } catch (error) {
    //         // 네트워크 오류 또는 API 호출 중 오류 발생 시 처리
    //         console.error('Error fetching reviews: ', error);
    //     }
    // };

    // // 컴포넌트 마운트 시 댓글 불러오기
    // useEffect(() => {
    //     fetchReviews();
    // }, []);
    

    useEffect(() => {
        const fetchArticle = async () => {
            try {
                const response = await getArticleApi(id); // API 호출
                console.log(response);
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
        console.log('수정 요청 완료');
        const handleSubmit = async () => {
            try {
                const response = await putArticleApi(id);
                if (response.status === 200) {
                    window.alert('수정 완료!!');
                    navigate('/articles/change');
                    console.log('수정 완료');
                } else {
                    console.log('수정 실패');
                }
            } catch (error) {
                console.log('에러 발생:', error);
            }
        }
        handleSubmit()
    };

    const onCommentInputButton = () => {
        console.log('댓글 작성중');
        const handleSubmit = async () => {
            try {
                const response = await putArticleApi(id);
                if (response.status === 200) {
                    window.alert('댓글 작성 완료!!');
                    console.log('댓글 작성 완료');
                    //fetchReviews();
                } else {
                    console.log('댓글 작성 실패');
                }
            } catch (error) {
                console.log('에러 발생:', error);
            }
        }
        handleSubmit()
    }
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
                    <CommentContainer>
                        <CommentText>댓글 : </CommentText>  
                        <CommentInputGroup>
                            <CommentInput placeholder="댓글을 입력해주세요."/>
                            <CommentButton onClick={onCommentInputButton}>작성</CommentButton>
                        </CommentInputGroup> 
                    </CommentContainer>
                </Wrapper>
            </CenteredContainer>
        </>
    );
}

export default ArticleDetail;
