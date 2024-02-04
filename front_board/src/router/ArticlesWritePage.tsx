import  { useState } from 'react';
import styled from 'styled-components';
import { postArticlesApi } from '../api/Articles';
import mainImage from "../../assets/mainImage.jpg";
import { useNavigate } from 'react-router-dom';

const Wrapper = styled.div`
`;
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
const Component = styled.div`
    display: flex;
    flex-direction: column;
    margin-left: 30%;
    //align-items: center;
    //justify-content: center;
`;
const InputTitle = styled.input`
    width: 60%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-right: 10%;
    margin-bottom: 10px; 
`;
const InputContent = styled.textarea`
    width: 60%;
    height: 300px;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-bottom: 10px; 
`;
const InputCreateBy = styled.input`
    width: 60%;
    padding:  10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-bottom: 10px; 
`;
const InputHashtag = styled.input`
    width: 60%;
    padding:  10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-bottom: 10px; 
`;
const WriteButton = styled.button`
    width: 10%;
    height: 40px;
    margin-left: 47%;
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

export default function ArticlesWritePage() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [createBy, setCreateBy] = useState('');
    const [hashtag, setHashtag] = useState('');
    const navigate = useNavigate();
    const onClick = () =>{
        // alert("버튼이 클릭되었습니다!");
        console.log("백엔드에 POST 요청 보내기")
        const Article = {
            title: "title",
            content: "content",
            hashtag: "hasheasd",
            createBy: "createBy",
        }
        const handleSubmit = async () => {
            try {
                const response = await postArticlesApi(Article);
        
                if (response.status === 201) {
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
            <TopVer>
                <TopButton>카테고리1</TopButton>
                <TopButton>카테고리2</TopButton>
                <TopButton>카테고리3</TopButton>
                <TopButton>카테고리4</TopButton>
            </TopVer>
            <HeaderContainer>
                <HeaderText>Delivering all IT news from around the world</HeaderText>
            </HeaderContainer>
            <Component>
                <tr><InputTitle value={title} onChange={(e) => setTitle(e.target.value)} placeholder="제목" /></tr>
                <tr><InputCreateBy value={createBy} onChange={(e) => setCreateBy(e.target.value)} placeholder="작성자" /></tr>
                <tr><InputContent value={content} onChange={(e) => setContent(e.target.value)} placeholder="내용" /></tr>
                <tr><InputHashtag value={hashtag} onChange={(e) => setHashtag(e.target.value)} placeholder="#" /></tr>
            </Component>
            <WriteButton onClick={onClick}>작성완료</WriteButton>
        </Wrapper>
    );
}

