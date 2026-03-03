import { useNavigate } from 'react-router-dom'
import { useProducts } from '../../hooks/useProducts'
import { useCartContext } from '../../context/CartContext'
import ProductGrid from '../../organisms/ProductGrid'
import styles from './ProductsPage.module.css'

/**
 * Page: ProductsPage
 * Catálogo completo de productos con filtros y búsqueda.
 */
const ProductsPage = () => {
  const navigate = useNavigate()
  const { products, loading, error } = useProducts()
  const { handleAddItem } = useCartContext()

  const handleAddToCart = async (product) => {
    try {
      await handleAddItem(product)
    } catch {
      // Error gestionado en CartContext
    }
  }

  const handleProductClick = (product) => {
    navigate(`/products/${product.id}`)
  }

  return (
    <div className={styles.page}>
      <div className={styles.header}>
        <h1 className={styles.title}>Nuestro menú</h1>
        <p className={styles.subtitle}>
          Descubre todos nuestros platos mediterráneos, desde arroces levantinos
          hasta dulces de Oriente Medio
        </p>
      </div>

      <ProductGrid
        products={products}
        loading={loading}
        error={error}
        onAddToCart={handleAddToCart}
        onProductClick={handleProductClick}
      />
    </div>
  )
}

export default ProductsPage
