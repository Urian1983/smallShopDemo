import { useState, useEffect, useCallback } from 'react'
import { getAllProducts, getProductById } from '../services/productService'

/**
 * Hook: useProducts
 * Carga y gestiona el catálogo de productos.
 *
 * @returns {{
 *   products: ProductResponseDTO[],
 *   loading: boolean,
 *   error: string|null,
 *   refetch: () => void
 * }}
 */
export const useProducts = () => {
  const [products, setProducts] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  const fetchProducts = useCallback(async () => {
    setLoading(true)
    setError(null)
    try {
      const data = await getAllProducts()
      setProducts(data)
    } catch (err) {
      setError(err.response?.data?.message || 'Error al cargar los productos')
    } finally {
      setLoading(false)
    }
  }, [])

  useEffect(() => {
    fetchProducts()
  }, [fetchProducts])

  return { products, loading, error, refetch: fetchProducts }
}

/**
 * Hook: useProduct
 * Carga un producto individual por ID.
 *
 * @param {number|string} id
 * @returns {{
 *   product: ProductResponseDTO|null,
 *   loading: boolean,
 *   error: string|null,
 *   refetch: () => void
 * }}
 */
export const useProduct = (id) => {
  const [product, setProduct] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  const fetchProduct = useCallback(async () => {
    if (!id) return
    setLoading(true)
    setError(null)
    try {
      const data = await getProductById(id)
      setProduct(data)
    } catch (err) {
      setError(err.response?.data?.message || 'Producto no encontrado')
    } finally {
      setLoading(false)
    }
  }, [id])

  useEffect(() => {
    fetchProduct()
  }, [fetchProduct])

  return { product, loading, error, refetch: fetchProduct }
}
