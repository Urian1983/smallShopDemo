import api from './api'

/**
 * Procesa el pago de un pedido (simulado)
 * @param {PaymentRequestDTO} paymentData — { orderId, paymentMethod }
 * @returns {Promise<PaymentResponseDTO>}
 */
export const processPayment = async (paymentData) => {
  const { data } = await api.post('/api/payments', paymentData)
  return data
}

/**
 * Obtiene el pago asociado a un pedido
 * @param {number} orderId
 * @returns {Promise<PaymentResponseDTO>}
 */
export const getPaymentByOrderId = async (orderId) => {
  const { data } = await api.get(`/api/payments/order/${orderId}`)
  return data
}
