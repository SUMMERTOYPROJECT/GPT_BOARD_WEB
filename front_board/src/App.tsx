import { RouterProvider, createBrowserRouter } from "react-router-dom"
import HomePage from "./router/HomePage"
import Articles from "./router/ArticlesPage"
import ArticlesWritePage from "./router/ArticlesWritePage"

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
  }
])

function App() {

  return (
    <>
      <RouterProvider router = {router}/>
    </>
  )
}

export default App
