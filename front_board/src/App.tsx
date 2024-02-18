import { RouterProvider, createBrowserRouter} from "react-router-dom"
import ArticlesWritePage from "./routes/ArticlesWritePage"
import Login from "./routes/Login"
import SignUp from "./routes/Signup"
import AuthProvider from "./security/AuthContext"
import ArticleDetail from "./routes/ArticleDetail"
import AriticlesHome from "./routes/AriticlesHome"
import ArticleCorrection from "./routes/AriticleCorrection"
import ArticleSearchPage from "./routes/ArticleSearchPage"
import { createGlobalStyle, ThemeProvider } from 'styled-components';

// const GlobalStyle = createGlobalStyle`
//   body {
//     margin: 0;
//     padding: 0;
//     font-family: 'Roboto', sans-serif;
//     background-color: white;
//   }

//   // 반응형 미디어 쿼리
//   @media (max-width: 768px) {
//     body {
//       background-color: white;
//     }
//   }
// `;

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login/>,
  },
  {
    path: "/articles",
    element: <AriticlesHome/>,
  },
  {
    path: "/articles/save",
    element: <ArticlesWritePage/>
  },
  {
    path:"/signup",
    element:<SignUp/>
  },
  {
    path:"/articles/:id",
    element:<ArticleDetail/>
  },
  {
    path:"/articles/:id/change",
    element:<ArticleCorrection/>
  },
  {
    path:"/articles/search",
    element:<ArticleSearchPage/>
  }
])

function App() {

  return (
    <>
    {/* <GlobalStyle /> */}
      <AuthProvider>
        <RouterProvider router = {router}/>
      </AuthProvider>
    </>
  )
}

export default App
