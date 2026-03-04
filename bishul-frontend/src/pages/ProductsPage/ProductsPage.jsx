import { useNavigate } from 'react-router-dom'
import { useProducts } from '../../hooks/useProducts'
import { useCartContext } from '../../context/CartContext'
import ProductGrid from '../../organisms/ProductGrid'
import styles from './ProductsPage.module.css'

/**
 * Page: ProductsPage
 * Carta completa del restaurante con filtros y búsqueda.
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
        <h1 className={styles.title}>Nuestra carta</h1>
        <p className={styles.subtitle}>
          Platos mediterráneos elaborados cada día con producto fresco —
          arroces levantinos, carnes a la brasa, pescados del día y postres de autor
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
