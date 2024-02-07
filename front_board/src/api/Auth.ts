import { apiClinet } from "./ApiClient";

export const AuthLoginApi = 
(userAccount: { username: string; password: string; }) =>
    apiClinet.post("/login", userAccount)


export const AuthSignupApi = 
(userAccount: { userId: string; userPassword: string; email: string; nickname: string; memo: string; }) =>
    apiClinet.post("/api/sign-up", userAccount)
