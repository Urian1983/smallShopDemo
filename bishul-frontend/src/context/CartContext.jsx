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
  // Usamos token directamente — no isAuthenticated — para evitar race condition
  const { token } = useAuth()

  const [cart, setCart] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const fetchCart = useCallback(async (currentToken) => {
    if (!currentToken) {
      setCart(null)
      return
    }
    setLoading(true)
    setError(null)
    try {
      const data = await getCart()
      setCart(data)
    } catch (err) {
      if (err.response?.status !== 403) {
        setError(err.response?.data?.message || 'Error al cargar el carrito')
      }
    } finally {
      setLoading(false)
    }
  }, [])

  // Se dispara cuando cambia el token — garantiza que ya está en localStorage
  useEffect(() => {
    fetchCart(token)
  }, [token, fetchCart])

  const cartCount = cart?.cartItems?.reduce((acc, item) => acc + item.quantity, 0) ?? 0

  const handleAddItem = useCallback(async (product) => {
    const updated = await addItem({
      productId: product.id,
      productName: product.name,
      productPrice: product.price,
      quantity: 1,
    })
    setCart(updated)
  }, [])

  const handleUpdateItem = useCallback(async (item) => {
    const updated = await updateItem(item)
    setCart(updated)
  }, [])

  const handleRemoveItem = useCallback(async (cartItemId) => {
    const updated = await removeItem(cartItemId)
    setCart(updated)
  }, [])

  const handleClearCart = useCallback(async () => {
    const updated = await clearCart()
    setCart(updated)
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
        refetch: () => fetchCart(token),
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
