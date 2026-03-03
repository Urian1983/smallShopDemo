import api from './api'

/**
 * Obtiene todos los productos del catálogo
 * @returns {Promise<ProductResponseDTO[]>}
 */
export const getAllProducts = async () => {
  const { data } = await api.get('/api/products')
  return data
}

/**
 * Obtiene un producto por su ID
 * @param {number} id
 * @returns {Promise<ProductResponseDTO>}
 */
export const getProductById = async (id) => {
  const { data } = await api.get(`/api/products/${id}`)
  return data
}

/**
 * Crea un nuevo producto (ADMIN)
 * @param {ProductRequestDTO} productData
 * @returns {Promise<ProductResponseDTO>}
 */
export const createProduct = async (productData) => {
  const { data } = await api.post('/api/products', productData)
  return data
}

/**
 * Actualiza un producto existente (ADMIN)
 * @param {number} id
 * @param {ProductRequestDTO} productData
 * @returns {Promise<ProductResponseDTO>}
 */
export const updateProduct = async (id, productData) => {
  const { data } = await api.put(`/api/products/${id}`, productData)
  return data
}

/**
 * Elimina un producto por ID (ADMIN)
 * @param {number} id
 * @returns {Promise<void>}
 */
export const deleteProduct = async (id) => {
  await api.delete(`/api/products/${id}`)
}
