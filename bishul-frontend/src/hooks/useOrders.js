import { useState, useEffect, useCallback } from 'react'
import { useAuth } from '../context/AuthContext'
import {
  getMyOrders,
  getAllOrders,
  getOrderById,
  createOrder,
  cancelOrder,
  updateOrderStatus,
} from '../services/orderService'

/**
 * Hook: useOrders
 * Gestiona los pedidos del usuario autenticado.
 * Si el usuario es ADMIN carga todos los pedidos, si no solo los suyos.
 *
 * @returns {{
 *   orders: OrderResponseDTO[],
 *   loading: boolean,
 *   error: string|null,
 *   handleCreateOrder: (orderData) => Promise<OrderResponseDTO>,
 *   handleCancelOrder: (id) => Promise<void>,
 *   handleUpdateStatus: (id, status) => Promise<void>,
 *   refetch: () => void
 * }}
 */
export const useOrders = () => {
  const { isAuthenticated, isAdmin } = useAuth()

  const [orders, setOrders] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const fetchOrders = useCallback(async () => {
    if (!isAuthenticated) return
    setLoading(true)
    setError(null)
    try {
      const data = isAdmin ? await getAllOrders() : await getMyOrders()
      setOrders(data)
    } catch (err) {
      setError(err.response?.data?.message || 'Error al cargar los pedidos')
    } finally {
      setLoading(false)
    }
  }, [isAuthenticated, isAdmin])

  useEffect(() => {
    fetchOrders()
  }, [fetchOrders])

  const handleCreateOrder = useCallback(async (orderData) => {
    setError(null)
    try {
      const newOrder = await createOrder(orderData)
      setOrders((prev) => [newOrder, ...prev])
      return newOrder
    } catch (err) {
      setError(err.response?.data?.message || 'Error al crear el pedido')
      throw err
    }
  }, [])

  const handleCancelOrder = useCallback(async (id) => {
    setError(null)
    try {
      const updated = await cancelOrder(id)
      setOrders((prev) => prev.map((o) => (o.id === id ? updated : o)))
    } catch (err) {
      setError(err.response?.data?.message || 'Error al cancelar el pedido')
      throw err
    }
  }, [])

  const handleUpdateStatus = useCallback(async (id, status) => {
    setError(null)
    try {
      const updated = await updateOrderStatus(id, status)
      setOrders((prev) => prev.map((o) => (o.id === id ? updated : o)))
    } catch (err) {
      setError(err.response?.data?.message || 'Error al actualizar el estado')
      throw err
    }
  }, [])

  return {
    orders,
    loading,
    error,
    handleCreateOrder,
    handleCancelOrder,
    handleUpdateStatus,
    refetch: fetchOrders,
  }
}

/**
 * Hook: useOrder
 * Carga un pedido individual por ID.
 *
 * @param {number|string} id
 */
export const useOrder = (id) => {
  const [order, setOrder] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  const fetchOrder = useCallback(async () => {
    if (!id) return
    setLoading(true)
    setError(null)
    try {
      const data = await getOrderById(id)
      setOrder(data)
    } catch (err) {
      setError(err.response?.data?.message || 'Pedido no encontrado')
    } finally {
      setLoading(false)
    }
  }, [id])

  useEffect(() => {
    fetchOrder()
  }, [fetchOrder])

  return { order, loading, error, refetch: fetchOrder }
}
