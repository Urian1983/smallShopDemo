import { createContext, useContext, useState, useCallback } from 'react'
import { login as loginService, register as registerService } from '../services/authService'

const AuthContext = createContext(null)

// Decodifica el payload del JWT sin librería externa
const decodeToken = (token) => {
  try {
    const payload = token.split('.')[1]
    return JSON.parse(atob(payload))
  } catch {
    return null
  }
}

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(() => localStorage.getItem('token') || null)
  const [user, setUser] = useState(() => {
    try {
      const stored = localStorage.getItem('user')
      return stored ? JSON.parse(stored) : null
    } catch {
      return null
    }
  })

  const login = useCallback(async (email, password) => {
    const data = await loginService(email, password)
    localStorage.setItem('token', data.token)
    if (data.user) localStorage.setItem('user', JSON.stringify(data.user))
    setToken(data.token)
    setUser(data.user ?? null)
    return data
  }, [])

  const register = useCallback(async (username, email, password, confirmPassword) => {
    const data = await registerService(username, email, password, confirmPassword)
    localStorage.setItem('token', data.token)
    if (data.user) localStorage.setItem('user', JSON.stringify(data.user))
    setToken(data.token)
    setUser(data.user ?? null)
    return data
  }, [])

  const logout = useCallback(() => {
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    setToken(null)
    setUser(null)
  }, [])

  const isAuthenticated = Boolean(token)

  // Leer roles del JWT directamente — user puede ser null si el backend no lo devuelve
  const tokenPayload = token ? decodeToken(token) : null
  const roles = tokenPayload?.roles ?? user?.roles ?? []
  const isAdmin = roles.includes('ADMIN') || roles.includes('ROLE_ADMIN')

  return (
    <AuthContext.Provider value={{ token, user, isAuthenticated, isAdmin, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => {
  const ctx = useContext(AuthContext)
  if (!ctx) throw new Error('useAuth must be used inside AuthProvider')
  return ctx
}
