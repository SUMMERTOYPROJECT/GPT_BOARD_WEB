import  { useState } from 'react';
import styled from 'styled-components';
import { postArticlesApi } from '../api/Articles';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../security/AuthContext';
import topImage from "../../assets/give2.png";

const Wrapper = styled.div`
`;
const Component = styled.div`
    display: flex;
    flex-direction: column;
    margin-left: 30%;
    //align-items: center;
    //justify-content: center;
`;
const HeaderContainer = styled.div`
    background-image: url(${topImage});
    background-size: contain;
    background-position: center;
    background-repeat: no-repeat;
    height: 23vh;
    width: 43vw;
    margin-bottom: 0rem;
    margin-left: 27rem;
    @media (max-width: 768px) {
        font-size: 15px;
        width: 40%;
        margin-left: 15rem;
        margin-bottom: -2rem;
    }
`;
const InputTitle = styled.input`
    width: 60%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-right: 10%;
    margin-bottom: 10px; 

    &:focus {
    outline: none;
    border-color: #e59a0e;
    }  
`;
const InputContent = styled.textarea`
    width: 60%;
    height: 300px;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-bottom: 10px; 

    &:focus {
    outline: none;
    border-color: #e59a0e;
    }  
`;
const InputHashtag = styled.input`
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
const WriteButton = styled.button`
    width: 10%;
    height: 40px;
    margin-left: 47%;
    border-radius: 5px;
    background-color: #007bff;
    color: white;
    font-size: 20px;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
    &:hover {
        background-color: #e59a0e;
    }
    @media (max-width: 768px) {
        width: 20%;
        margin-left: 19rem;
    }
`;

export default function ArticlesWritePage() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [hashtag, setHashtag] = useState('');
    const auth = useAuth();
    const navigate = useNavigate();
    const onClick = () =>{
        // alert("버튼이 클릭되었습니다!");
        console.log("백엔드에 POST 요청 보내기")
        const Article = {
            title: title,
            content: content,
            hashtag: hashtag,
            userId: auth.username,
        }
        const handleSubmit = async () => {
            try {
                const response = await postArticlesApi(Article);
        
                if (response.status === 200) {
                    window.alert('등록 완료!!');
                    navigate('/articles');
                    console.log('등록 완료');
                } else {
                    console.log('등록 실패');
                }
            } catch (error) {
                console.log('에러 발생:', error);
            }
        }
        handleSubmit()
    }

    return (
        <Wrapper>
            <HeaderContainer/>
            <Component>
                <InputTitle value={title} onChange={(e) => setTitle(e.target.value)} placeholder="제목" />
                <InputContent value={content} onChange={(e) => setContent(e.target.value)} placeholder="내용" />
                <InputHashtag value={hashtag} onChange={(e) => setHashtag(e.target.value)} placeholder="#" />
            </Component>
            <WriteButton onClick={onClick}>작성완료</WriteButton>
        </Wrapper>
    );
}