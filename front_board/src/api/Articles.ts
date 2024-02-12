import { apiClinet } from "./ApiClient";

interface Article {
    title: string;
    content: string;
    hashtag: string;
}

interface ArticleComment {
  id: string | undefined;
  nickname: string
  content: string
}

export const getArticlesApi =
() => apiClinet.get('/api/articles')

export const getArticleApi =
(id: string | undefined) => apiClinet.get(`/api/articles/${id}`)

export const postArticlesApi =
(article: Article) => apiClinet.post('/api/articles', article)

export const postCommentApi =
(article: ArticleComment) => apiClinet.post(`/api/articles/${article.id}/articleComment`, article)

export const getCommentListApi =
(article: ArticleComment) => apiClinet.get('/api/articleComment', article)

export const deleteArticleApi =
(id: string | undefined) => apiClinet.delete(`/api/articles/${id}`)

export const putArticleApi = 
(id: string | undefined) => apiClinet.put(`/api/articles/${id}`)