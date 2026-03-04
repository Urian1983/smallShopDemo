import { useParams, useNavigate } from 'react-router-dom'
import { useProduct } from '../../hooks/useProducts'
import { useCartContext } from '../../context/CartContext'
import { useAuth } from '../../context/AuthContext'
import Button from '../../atoms/Button'
import Badge from '../../atoms/Badge'
import Spinner from '../../atoms/Spinner'
import styles from './ProductDetailPage.module.css'

/**
 * Page: ProductDetailPage
 * Detalle completo de un producto con imagen, descripción y botón de carrito.
 */
const ProductDetailPage = () => {
  const { id } = useParams()
  const navigate = useNavigate()
  const { product, loading, error } = useProduct(id)
  const { handleAddItem } = useCartContext()
  const { isAuthenticated } = useAuth()

  const handleAddToCart = async () => {
    if (!isAuthenticated) {
      navigate('/login')
      return
    }
    try {
      await handleAddItem(product)
    } catch {
      // Error gestionado en CartContext
    }
  }

  if (loading) {
    return (
      <div className={styles.centered}>
        <Spinner size="lg" />
      </div>
    )
  }

  if (error || !product) {
    return (
      <div className={styles.centered}>
        <p className={styles.errorMsg}>⚠️ {error || 'Producto no encontrado'}</p>
        <Button variant="secondary" onClick={() => navigate('/products')}>
          Volver al catálogo
        </Button>
      </div>
    )
  }

  const outOfStock = product.stock === 0

  return (
    <div className={styles.page}>

      {/* Breadcrumb */}
      <nav className={styles.breadcrumb} aria-label="Breadcrumb">
        <button className={styles.breadcrumbLink} onClick={() => navigate('/')}>Inicio</button>
        <span className={styles.breadcrumbSep}>›</span>
        <button className={styles.breadcrumbLink} onClick={() => navigate('/products')}>Menú</button>
        <span className={styles.breadcrumbSep}>›</span>
        <span className={styles.breadcrumbCurrent}>{product.name}</span>
      </nav>

      <div className={styles.content}>

        {/* Imagen */}
        <div className={styles.imageWrapper}>
          <img
            src={product.imageUrl}
            alt={product.name}
            className={styles.image}
            onError={(e) => {
              e.target.onerror = null
              e.target.src = `https://placehold.co/800x600/F5EDD6/C1440E?text=${encodeURIComponent(product.name)}`
            }}
          />
          {outOfStock && (
            <div className={styles.outOfStockOverlay}>
              <span>Agotado</span>
            </div>
          )}
        </div>

        {/* Info */}
        <div className={styles.info}>
          <div className={styles.meta}>
            <Badge variant="category">{product.category}</Badge>
            {product.brand && (
              <span className={styles.brand}>{product.brand}</span>
            )}
          </div>

          <h1 className={styles.name}>{product.name}</h1>

          {product.shortDescription && (
            <p className={styles.shortDesc}>{product.shortDescription}</p>
          )}

          <p className={styles.price}>{Number(product.price).toFixed(2)} €</p>

          {product.description && (
            <div className={styles.descSection}>
              <h2 className={styles.descTitle}>Descripción</h2>
              <p className={styles.description}>{product.description}</p>
            </div>
          )}

          <div className={styles.stockInfo}>
            {outOfStock ? (
              <span className={styles.stockOut}>Sin stock disponible</span>
            ) : (
              <span className={styles.stockIn}>✓ {product.stock} unidades disponibles</span>
            )}
          </div>

          <div className={styles.actions}>
            <Button
              variant="primary"
              size="lg"
              disabled={outOfStock}
              onClick={handleAddToCart}
              fullWidth
            >
              {outOfStock ? 'Sin stock' : '🛒 Añadir al carrito'}
            </Button>
            <Button
              variant="ghost"
              size="lg"
              onClick={() => navigate('/products')}
              fullWidth
            >
              ← Volver al menú
            </Button>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ProductDetailPage
