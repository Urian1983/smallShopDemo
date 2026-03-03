import api from './api'

/**
 * Autentica al usuario y devuelve { token, user }
 * @param {string} email
 * @param {string} password
 */
export const login = async (email, password) => {
  const { data } = await api.post('/api/auth/login', { email, password })
  return data
}

/**
 * Registra un nuevo usuario y devuelve { token, user }
 * @param {string} username
 * @param {string} email
 * @param {string} password
 * @param {string} confirmPassword
 */
export const register = async (username, email, password, confirmPassword) => {
  const { data } = await api.post('/api/auth/register', {
    username,
    email,
    password,
    confirmPassword,
  })
  return data
}
