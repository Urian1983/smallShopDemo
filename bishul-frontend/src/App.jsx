import { Routes, Route, Navigate } from 'react-router-dom'
import { useAuth } from './context/AuthContext'

// Layout
import MainLayout from './templates/MainLayout'

// Pages
import HomePage from './pages/HomePage'
import LoginPage from './pages/LoginPage'
import RegisterPage from './pages/RegisterPage'
import ProductsPage from './pages/ProductsPage'
import ProductDetailPage from './pages/ProductDetailPage'
import CartPage from './pages/CartPage'

// Placeholders para páginas del siguiente paso
const Placeholder = ({ name }) => (
  <div style={{ padding: '4rem 2rem', fontFamily: 'serif', textAlign: 'center' }}>
    <h2 style={{ fontSize: '1.5rem', color: '#C1440E' }}>🚧 {name}</h2>
    <p style={{ color: '#9E8B74', marginTop: '0.5rem' }}>Próximamente en el siguiente paso</p>
  </div>
)

// Guard de ruta privada
const PrivateRoute = ({ children }) => {
  const { isAuthenticated } = useAuth()
  return isAuthenticated ? children : <Navigate to="/login" replace />
}

// Guard de ruta admin
const AdminRoute = ({ children }) => {
  const { isAuthenticated, isAdmin } = useAuth()
  if (!isAuthenticated) return <Navigate to="/login" replace />
  if (!isAdmin) return <Navigate to="/" replace />
  return children
}

const App = () => {
  return (
    <Routes>
      <Route element={<MainLayout />}>
        <Route path="/"             element={<HomePage />} />
        <Route path="/products"     element={<ProductsPage />} />
        <Route path="/products/:id" element={<ProductDetailPage />} />
        <Route path="/cart"         element={<PrivateRoute><CartPage /></PrivateRoute>} />
        <Route path="/orders"       element={<PrivateRoute><Placeholder name="Orders Page" /></PrivateRoute>} />
        <Route path="/profile"      element={<PrivateRoute><Placeholder name="Profile Page" /></PrivateRoute>} />
        <Route path="/admin"        element={<AdminRoute><Placeholder name="Admin Panel" /></AdminRoute>} />
      </Route>

      {/* Auth pages fuera del layout principal */}
      <Route path="/login"    element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />

      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  )
}

export default App
