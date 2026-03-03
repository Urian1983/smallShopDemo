import { createContext, useContext, useState, useEffect, useCallback } from 'react'
import { useAuth } from './AuthContext'
import {
  getCart,
  addItem,
  updateItem,
  removeItem,
  clearCart,
} from '../services/cartService'

const CartContext = createContext(null)

export const CartProvider = ({ children }) => {
  const { isAuthenticated } = useAuth()

  const [cart, setCart] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const fetchCart = useCallback(async () => {
    if (!isAuthenticated) {
      setCart(null)
      return
    }
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

  // Recargar el carrito cuando cambia el estado de autenticación
  useEffect(() => {
    fetchCart()
  }, [fetchCart])

  const cartCount = cart?.cartItems?.reduce((acc, item) => acc + item.quantity, 0) ?? 0

  const handleAddItem = useCallback(async (product) => {
    try {
      const updated = await addItem({
        productId: product.id,
        productName: product.name,
        productPrice: product.price,
        quantity: 1,
      })
      setCart(updated)
    } catch (err) {
      throw err
    }
  }, [])

  const handleUpdateItem = useCallback(async (item) => {
    try {
      const updated = await updateItem(item)
      setCart(updated)
    } catch (err) {
      throw err
    }
  }, [])

  const handleRemoveItem = useCallback(async (cartItemId) => {
    try {
      const updated = await removeItem(cartItemId)
      setCart(updated)
    } catch (err) {
      throw err
    }
  }, [])

  const handleClearCart = useCallback(async () => {
    try {
      const updated = await clearCart()
      setCart(updated)
    } catch (err) {
      throw err
    }
  }, [])

  return (
    <CartContext.Provider
      value={{
        cart,
        cartCount,
        loading,
        error,
        handleAddItem,
        handleUpdateItem,
        handleRemoveItem,
        handleClearCart,
        refetch: fetchCart,
      }}
    >
      {children}
    </CartContext.Provider>
  )
}

export const useCartContext = () => {
  const ctx = useContext(CartContext)
  if (!ctx) throw new Error('useCartContext must be used inside CartProvider')
  return ctx
}
