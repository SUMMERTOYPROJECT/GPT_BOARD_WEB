import { apiClinet } from "./ApiClient";

interface ArticleComment {
    userId: string | null;
    articleId: string | undefined;
    content: string;
  }

export const postCommentApi =
(id: string | undefined, comment:ArticleComment) => apiClinet.post(`/api/articles/${id}/articleComments`, comment)

export const getCommentListApi =
(id: string | undefined) => apiClinet.get(`/api/articles/${id}/articleComments`)

export const deleteCommentApi =
(id: string | undefined, commentId: string | undefined) => apiClinet.delete(`/api/articles/${id}/articleComments/${commentId}`)

export const putCommentApi =
(id: string | undefined, comment:ArticleComment) => apiClinet.put(`/api/articles/{article-id}/articleComments/${id}`, comment)


// /api/articles/{article-id}/articleComments
// /api/articles/{article-id}/articleComments
// /api/articles/{article-id}/articleComments/{article-comment-id}
// /api/articles/{article-id}/articleComments/{article-comment-id}