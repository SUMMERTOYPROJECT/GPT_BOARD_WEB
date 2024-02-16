import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import { deleteCommentApi, getCommentListApi, postCommentApi, putCommentApi } from '../api/ArticlesComment';
import { useAuth } from '../security/AuthContext';

interface ArticleComment {
    createAt: any;
    articleId: string;
    userId: string | null;
    content: string;
    id: string;
}

const CommentContainer = styled.div`
    width: 60%;
    height: 25%;
    min-height: 15%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #50505011;
    border-radius: 10px;
    margin-top: 0.3rem;
    flex-direction: column; 
    overflow: auto;
`;
const CommentSideButton = styled.button`
    padding: 5px 10px;  
    border: none;
    background-color: transparent;
    color: #007bff;
    font-size: 12px; 
    cursor: pointer;
    margin: 0.1rem;
    transition: color 0.3s;
    white-space: nowrap; 

    &:hover {
        color: #e59a0e;
    }
`;
const ButtonsContainer = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
`;
const CommentText = styled.text`
    font-size: 18px;
    color: black;
    margin-right: 36rem;
    margin-top: 3rem;
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
    align-items: center; 
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
        background-color: #e59a0e;
    }
`;
const CommentList = styled.table`
    width: 100%;
    text-align: left;
    border: 1px solid #ddd;
    border-collapse: collapse;
    min-height: 100px;
    width: 100%;
`;
const CommentNickname = styled.td`
    color: #1e1e1e;
    padding: 8px;
`;
const CommentDate = styled.td`
    color: #1e1e1e;
    padding: 8px;
`;
const CommentContent = styled.td`
    color: #1e1e1e;
    padding: 8px;
`;
const EditInput = styled.input`
    width: 60%;
    padding:  10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-bottom: 10px; 

    &:focus {
    outline: none;
    border-color: #e59a0e;
    }  
`;

export default function CommentComponent(){
    const { id } = useParams();
    const [comments, setComments] = useState<ArticleComment[]>([])
    const [content, setContent] = useState('')
    const [addcommnet, setAddcommnet] = useState(false)
    const [editcommnet, setEditcommnet] = useState(false)
    const navigate = useNavigate()
    const auth = useAuth()

    useEffect(() => {
        setAddcommnet(false)
        const fetchArticle = async () => {
            try {
                const response = await getCommentListApi(id); // API 호출
                console.log('여기');
                console.log(response.data);
                setComments(response.data);
            } catch (error) {
                // 에러 처리
            }
        };

        fetchArticle();
    }, [addcommnet]);

    const onCommentButton = () => {
        const postArticleComment = async () => {
            const comment = {
                content: content,
                userId: auth.username,
                articleId: id
            }
            console.log(comment)         
            const confirm = window.confirm("댓글을 등록하시겠습니까?")
            if(confirm){
                try{
                    const response =await postCommentApi(id, comment);
                    if(response.status === 200){
                        window.alert('댓글이 등록되었습니다.')
                        setAddcommnet(true)   
                        setContent("")
                    }
                }
                catch(e){
                    console.log(e)
                }  
            };
        };
        postArticleComment();
    };

    const onEditComment = () => {
        setEditcommnet(true)
    };

    const onDeleteComment = (commentId: string | undefined) => {
        const deleteArticleComment = async () => {  
            setAddcommnet(false)
            console.log(commentId)
            const confirm = window.confirm("댓글을 삭제하시겠습니까?")
            if(confirm){
                try{
                    const response =await deleteCommentApi(id, commentId);
                    //console.log(response)
                    if(response.status === 200){
                        window.alert('댓글이 삭제되었습니다.')   
                        navigate(`/articles/${id}`)
                        setAddcommnet(true)
                    }
                }
                catch(e){
                    console.log(e)
                }  
            };
        };
        deleteArticleComment();
    };

    const onEditCheck = () => {
        const putArticleComment = async () => { 
            const comment = {
                content: content,
                userId: auth.username,
                articleId: id
            } 
            const confirm = window.confirm("댓글을 수정하겠습니까?")
            if(confirm){
                try{
                    const response =await putCommentApi(id, comment);
                    if(response.status === 200){
                        window.alert('댓글이 수정되었습니다.')   
                        
                    }
                }
                catch(e){
                    console.log(e)
                }  
            };
        };
        putArticleComment();
    };

    const onEditBack = () => {
        
    };

    
    return(
        <>
            <CommentContainer>
                    <CommentText>댓글 :</CommentText>
                <CommentInputGroup>
                    <CommentInput value = {content} onChange={(e) => setContent(e.target.value)} placeholder="댓글을 입력해주세요."/>
                    <CommentButton onClick={onCommentButton}>등록</CommentButton>
                </CommentInputGroup> 
                    <CommentList>
                        <tbody>
                            {comments.map((comment, index) => {
                                const formattedDate = comment.createAt.split('T')[0];
                                return (
                                    <ButtonsContainer key={index}>
                                        {
                                            <>
                                                <CommentNickname>{comment.userId}</CommentNickname>
                                                <CommentContent>{comment.content}</CommentContent>
                                                <CommentDate>{formattedDate}</CommentDate>
                                                {auth.username === comment.userId && (
                                                    <td>
                                                        <CommentSideButton onClick={() => onDeleteComment(comment.id)}>삭제</CommentSideButton>
                                                    </td>
                                                )}                
                                            </>         
                                        }
                                    </ButtonsContainer>
                                );
                            })}
                        </tbody>  
                    </CommentList>                
            </CommentContainer>  
        </>
    );
}

