import { RouterProvider, createBrowserRouter } from "react-router-dom"
import HomePage from "./router/HomePage"

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomePage/>,
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
