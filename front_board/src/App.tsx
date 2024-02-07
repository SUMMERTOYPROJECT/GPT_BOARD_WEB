import { RouterProvider, createBrowserRouter } from "react-router-dom"
import HomePage from "./routes/HomePage"
import Articles from "./routes/ArticlesPage"
import ArticlesWritePage from "./routes/ArticlesWritePage"
import Login from "./routes/Login"
import SignUp from "./routes/Signup"
import AuthProvider from "./security/AuthContext"

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomePage/>,
  },
  {
    path: "/articles",
    element: <Articles/>,
  },
  {
    path: "/articles/save",
    element: <ArticlesWritePage/>
  },
  {
    path:"/login",
    element:<Login/>
  },
  {
    path:"/signup",
    element:<SignUp/>
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
