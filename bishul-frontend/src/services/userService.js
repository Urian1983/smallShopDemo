import api from './api'

/**
 * Obtiene el perfil del usuario autenticado
 * @returns {Promise<UserResponseDTO>}
 */
export const getMyProfile = async () => {
  const { data } = await api.get('/api/users/me')
  return data
}

/**
 * Obtiene un usuario por ID (ADMIN)
 * @param {number} id
 * @returns {Promise<UserResponseDTO>}
 */
export const getUserById = async (id) => {
  const { data } = await api.get(`/api/users/${id}`)
  return data
}

/**
 * Actualiza un usuario por ID (ADMIN)
 * @param {number} id
 * @param {UserRequestDTO} userData — { name, email, password }
 * @returns {Promise<UserResponseDTO>}
 */
export const updateUser = async (id, userData) => {
  const { data } = await api.put(`/api/users/${id}`, userData)
  return data
}

/**
 * Elimina un usuario por ID (ADMIN)
 * @param {number} id
 * @returns {Promise<void>}
 */
export const deleteUser = async (id) => {
  await api.delete(`/api/users/${id}`)
}
