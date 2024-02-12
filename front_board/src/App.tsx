import { RouterProvider, createBrowserRouter } from "react-router-dom"
import ArticlesWritePage from "./routes/ArticlesWritePage"
import Login from "./routes/Login"
import SignUp from "./routes/Signup"
import AuthProvider from "./security/AuthContext"
import ArticleDetail from "./routes/ArticleDetail"
import AriticlesHome from "./routes/AriticlesHome"
import ArticleCorrection from "./routes/AriticleCorrection"

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
    path:"/articles/change",
    element:<ArticleCorrection/>
  }
])

function App() {

  return (
    <AuthProvider>
      <RouterProvider router = {router}/>
    </AuthProvider>
  )
}

export default App
