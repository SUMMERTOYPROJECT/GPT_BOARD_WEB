import { apiClinet } from "./ApiClient";

interface Article {
    title: string;
    content: string;
    hashtag: string;
}

interface updateArticle{
    title: string;
    content: string;
    hashtag: string;
    id: string|undefined;
}

export const getArticlesApi =
() => apiClinet.get('/api/articles')

export const getArticleApi =
(id: string | undefined) => apiClinet.get(`/api/articles/${id}`)

export const postArticlesApi =
(article: Article) => apiClinet.post('/api/articles', article)

export const deleteArticleApi =
(id: string | undefined) => apiClinet.delete(`/api/articles/${id}`)

// /api/articles/{article-id}
export const putArticleApi =
(id: string | undefined, artcle : Article) => apiClinet.put(`/api/articles/${id}`)

export const searchArticleApi = 
(type: string | undefined, keyword: string | undefined) => apiClinet.get(`/api/articles/search`, {
    params: {
    type: type,
    keyword: keyword,
}
});

