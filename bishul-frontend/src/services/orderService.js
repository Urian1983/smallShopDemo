import api from './api'

/**
 * Crea un nuevo pedido a partir del carrito del usuario
 * @param {OrderRequestDTO} orderData — { orderNumber, address, postalCode, country }
 * @returns {Promise<OrderResponseDTO>}
 */
export const createOrder = async (orderData) => {
  const { data } = await api.post('/api/order', orderData)
  return data
}

/**
 * Obtiene un pedido por su ID
 * @param {number} id
 * @returns {Promise<OrderResponseDTO>}
 */
export const getOrderById = async (id) => {
  const { data } = await api.get(`/api/order/${id}`)
  return data
}

/**
 * Obtiene los pedidos del usuario autenticado
 * @returns {Promise<OrderResponseDTO[]>}
 */
export const getMyOrders = async () => {
  const { data } = await api.get('/api/order/my-orders')
  return data
}

/**
 * Obtiene todos los pedidos (ADMIN)
 * @returns {Promise<OrderResponseDTO[]>}
 */
export const getAllOrders = async () => {
  const { data } = await api.get('/api/order')
  return data
}

/**
 * Actualiza el estado de un pedido (ADMIN)
 * @param {number} id
 * @param {'PENDING'|'PAID'|'SHIPPED'|'DELIVERED'|'CANCELLED'} status
 * @returns {Promise<OrderResponseDTO>}
 */
export const updateOrderStatus = async (id, status) => {
  const { data } = await api.put(`/api/order/${id}/status`, null, {
    params: { status },
  })
  return data
}

/**
 * Cancela un pedido
 * @param {number} id
 * @returns {Promise<OrderResponseDTO>}
 */
export const cancelOrder = async (id) => {
  const { data } = await api.put(`/api/order/${id}/cancel`)
  return data
}
