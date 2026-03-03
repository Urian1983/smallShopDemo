import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../../context/AuthContext'
import AuthForm from '../../organisms/AuthForm'

/**
 * Page: LoginPage
 * Redirige al home si el usuario ya está autenticado.
 */
const LoginPage = () => {
  const { isAuthenticated } = useAuth()
  const navigate = useNavigate()

  useEffect(() => {
    if (isAuthenticated) navigate('/', { replace: true })
  }, [isAuthenticated, navigate])

  return <AuthForm mode="login" />
}

export default LoginPage
