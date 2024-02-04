import { apiClinet } from "./ApiClient";

interface Article {
    title: string;
    content: string;
    hashtag: string;
  }

export const getArticlesApi =
() => apiClinet.get('/api/articles')

export const postArticlesApi =
(article: Article) => apiClinet.post('/api/articles', article)
