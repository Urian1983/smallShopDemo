import api from './api'

/**
 * Obtiene el carrito del usuario autenticado
 * @returns {Promise<CartResponseDTO>}
 */
export const getCart = async () => {
  const { data } = await api.get('/api/cart')
  return data
}

/**
 * Añade un ítem al carrito
 * @param {CartItemRequestDTO} item — { productId, productName, productPrice, quantity }
 * @returns {Promise<CartResponseDTO>}
 */
export const addItem = async (item) => {
  const { data } = await api.post('/api/cart/items', item)
  return data
}

/**
 * Actualiza la cantidad de un ítem del carrito
 * @param {CartItemRequestDTO} item — { productId, productName, productPrice, quantity }
 * @returns {Promise<CartResponseDTO>}
 */
export const updateItem = async (item) => {
  const { data } = await api.put('/api/cart/items', item)
  return data
}

/**
 * Elimina un ítem del carrito por su cartItemId
 * @param {number} cartItemId
 * @returns {Promise<CartResponseDTO>}
 */
export const removeItem = async (cartItemId) => {
  const { data } = await api.delete(`/api/cart/items/${cartItemId}`)
  return data
}

/**
 * Vacía el carrito completo
 * @returns {Promise<CartResponseDTO>}
 */
export const clearCart = async () => {
  const { data } = await api.delete('/api/cart')
  return data
}
