import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { useAuth } from '../../context/AuthContext'
import FormField from '../../molecules/FormField'
import Button from '../../atoms/Button'
import styles from './AuthForm.module.css'

/**
 * Organism: AuthForm
 * Formulario reutilizable para login y registro.
 * @param {'login'|'register'} mode
 */
const AuthForm = ({ mode = 'login' }) => {
  const { login, register } = useAuth()
  const navigate = useNavigate()

  const isLogin = mode === 'login'

  const [fields, setFields] = useState({
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
  })
  const [errors, setErrors] = useState({})
  const [serverError, setServerError] = useState('')
  const [loading, setLoading] = useState(false)

  const handleChange = (e) => {
    const { name, value } = e.target
    setFields((prev) => ({ ...prev, [name]: value }))
    // Limpiar error del campo al escribir
    if (errors[name]) setErrors((prev) => ({ ...prev, [name]: '' }))
  }

  const validate = () => {
    const next = {}
    if (!isLogin && !fields.username.trim()) next.username = 'El nombre de usuario es obligatorio'
    if (!fields.email.trim()) next.email = 'El email es obligatorio'
    else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(fields.email)) next.email = 'Email no válido'
    if (!fields.password) next.password = 'La contraseña es obligatoria'
    else if (fields.password.length < 6) next.password = 'Mínimo 6 caracteres'
    if (!isLogin && fields.password !== fields.confirmPassword) {
      next.confirmPassword = 'Las contraseñas no coinciden'
    }
    return next
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    setServerError('')

    const validationErrors = validate()
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors)
      return
    }

    setLoading(true)
    try {
      if (isLogin) {
        await login(fields.email, fields.password)
      } else {
        await register(fields.username, fields.email, fields.password, fields.confirmPassword)
      }
      navigate('/')
    } catch (err) {
      const msg = err.response?.data?.message || err.response?.data?.error
      setServerError(msg || 'Ha ocurrido un error. Inténtalo de nuevo.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className={styles.wrapper}>
      <div className={styles.card}>

        {/* Header */}
        <div className={styles.header}>
          <span className={styles.icon}>🫕</span>
          <h1 className={styles.title}>
            {isLogin ? 'Bienvenido de nuevo' : 'Crea tu cuenta'}
          </h1>
          <p className={styles.subtitle}>
            {isLogin
              ? 'Accede a tu cuenta de Bishul Cooking Shop'
              : 'Únete a nuestra comunidad gastronómica'}
          </p>
        </div>

        {/* Server error */}
        {serverError && (
          <div className={styles.serverError} role="alert">
            ⚠️ {serverError}
          </div>
        )}

        {/* Form */}
        <form className={styles.form} onSubmit={handleSubmit} noValidate>
          {!isLogin && (
            <FormField
              id="username"
              name="username"
              label="Nombre de usuario"
              placeholder="johndoe88"
              value={fields.username}
              onChange={handleChange}
              error={errors.username}
              required
            />
          )}

          <FormField
            id="email"
            name="email"
            type="email"
            label="Email"
            placeholder="tu@email.com"
            value={fields.email}
            onChange={handleChange}
            error={errors.email}
            required
          />

          <FormField
            id="password"
            name="password"
            type="password"
            label="Contraseña"
            placeholder="••••••••"
            value={fields.password}
            onChange={handleChange}
            error={errors.password}
            required
          />

          {!isLogin && (
            <FormField
              id="confirmPassword"
              name="confirmPassword"
              type="password"
              label="Confirmar contraseña"
              placeholder="••••••••"
              value={fields.confirmPassword}
              onChange={handleChange}
              error={errors.confirmPassword}
              required
            />
          )}

          <Button
            type="submit"
            variant="primary"
            size="lg"
            fullWidth
            loading={loading}
          >
            {isLogin ? 'Entrar' : 'Crear cuenta'}
          </Button>
        </form>

        {/* Footer link */}
        <p className={styles.footerText}>
          {isLogin ? '¿Aún no tienes cuenta? ' : '¿Ya tienes cuenta? '}
          <Link to={isLogin ? '/register' : '/login'} className={styles.footerLink}>
            {isLogin ? 'Regístrate' : 'Inicia sesión'}
          </Link>
        </p>
      </div>
    </div>
  )
}

export default AuthForm
