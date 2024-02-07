import { createContext, useContext, useState, ReactNode } from "react";
import { AuthLoginApi } from "../api/Auth";
import { apiClinet } from "../api/ApiClient";

// 타입 정의 추가
interface AuthContextType {
  isAuthenticated: boolean;
  login: (UserAccount: { username: string; password: string }) => Promise<boolean>;
  logout: () => void;
  username: string | null;
  token: string | null;
}

const defaultValue: AuthContextType = {
    isAuthenticated: false,
    login: async () => false,
    logout: () => {},
    username: null,
    token: null,
};

export const AuthContext = createContext<AuthContextType>(defaultValue);
export const useAuth = () => useContext(AuthContext);

interface AuthProviderProps {
  children: ReactNode;
}

export default function AuthProvider({children}: AuthProviderProps) {
    const [isAuthenticated, setAuthenticated] = useState(false);
    const [username, setUsername] = useState<string | null>(null);
    const [token, setToken] = useState<string | null>(null);
    

    async function login(UserAccount: { username: string; password: string; }): Promise<boolean> {
        try {
            const response = await AuthLoginApi(UserAccount);
            if (response.status === 200) {
                  const token = response.headers['authorization'];
                  setUsername(UserAccount.username)
                  setAuthenticated(true)
                  setToken(token)
                  if (token) {
                    apiClinet.interceptors.request.use((config) => {
                      console.log('intercepting and adding a token')
                      config.headers.Authorization = token
                      return config
                    })
                    
                    window.alert("로그인 되었습니다.");
                    return true
                  }
                  else {
                    window.alert("토큰이 없습니다.");
                    logout();
                    return false;
                  }
                }else {
                logout();
                return false; // 여기서 로그인 실패를 명시적으로 처리합니다.
            }
        } catch (error) {
            console.log("로그인 실패");
            logout();
            return false; // 예외 발생 시 로그인 실패
        }
    }

    function logout() {
        setAuthenticated(false)
        setToken(null)
        setUsername(null)
    };

    // 상태 및 함수를 포함하는 새로운 value 객체
    const value = {
        isAuthenticated,
        login,
        logout,
        username,
        token,
    };

    return(
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
}
