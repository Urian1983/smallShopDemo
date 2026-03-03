import { useState, useEffect, useCallback } from 'react'
import { useAuth } from '../context/AuthContext'
import {
  getCart,
  addItem,
  updateItem,
  removeItem,
  clearCart,
} from '../services/cartService'

/**
 * Hook: useCart
 * Gestiona el estado del carrito del usuario autenticado.
 * Solo realiza llamadas a la API si el usuario está autenticado.
 *
 * @returns {{
 *   cart: CartResponseDTO|null,
 *   cartCount: number,
 *   loading: boolean,
 *   error: string|null,
 *   handleAddItem: (product) => Promise<void>,
 *   handleUpdateItem: (item) => Promise<void>,
 *   handleRemoveItem: (cartItemId) => Promise<void>,
 *   handleClearCart: () => Promise<void>,
 *   refetch: () => void
 * }}
 */
export const useCart = () => {
  const { isAuthenticated } = useAuth()

  const [cart, setCart] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const fetchCart = useCallback(async () => {
    if (!isAuthenticated) return
    setLoading(true)
    setError(null)
    try {
      const data = await getCart()
      setCart(data)
    } catch (err) {
      setError(err.response?.data?.message || 'Error al cargar el carrito')
    } finally {
      setLoading(false)
    }
  }, [isAuthenticated])

  useEffect(() => {
    fetchCart()
  }, [fetchCart])

  // Número total de unidades en el carrito (para el badge del Navbar)
  const cartCount = cart?.cartItems?.reduce((acc, item) => acc + item.quantity, 0) ?? 0

  const handleAddItem = useCallback(async (product) => {
    setError(null)
    try {
      const updated = await addItem({
        productId: product.id,
        productName: product.name,
        productPrice: product.price,
        quantity: 1,
      })
      setCart(updated)
    } catch (err) {
      setError(err.response?.data?.message || 'Error al añadir el producto')
      throw err
    }
  }, [])

  const handleUpdateItem = useCallback(async (item) => {
    setError(null)
    try {
      const updated = await updateItem(item)
      setCart(updated)
    } catch (err) {
      setError(err.response?.data?.message || 'Error al actualizar el carrito')
      throw err
    }
  }, [])

  const handleRemoveItem = useCallback(async (cartItemId) => {
    setError(null)
    try {
      const updated = await removeItem(cartItemId)
      setCart(updated)
    } catch (err) {
      setError(err.response?.data?.message || 'Error al eliminar el producto')
      throw err
    }
  }, [])

  const handleClearCart = useCallback(async () => {
    setError(null)
    try {
      const updated = await clearCart()
      setCart(updated)
    } catch (err) {
      setError(err.response?.data?.message || 'Error al vaciar el carrito')
      throw err
    }
  }, [])

  return {
    cart,
    cartCount,
    loading,
    error,
    handleAddItem,
    handleUpdateItem,
    handleRemoveItem,
    handleClearCart,
    refetch: fetchCart,
  }
}
