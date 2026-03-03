import { useNavigate } from 'react-router-dom'
import { useCartContext } from '../../context/CartContext'
import { useProducts } from '../../hooks/useProducts'
import ProductGrid from '../../organisms/ProductGrid'
import Button from '../../atoms/Button'
import styles from './HomePage.module.css'

/**
 * Page: HomePage
 * Hero + catálogo de productos destacados.
 */
const HomePage = () => {
  const navigate = useNavigate()
  const { products, loading, error } = useProducts()
  const { handleAddItem } = useCartContext()

  const handleAddToCart = async (product) => {
    try {
      await handleAddItem(product)
    } catch {
      // El error se gestiona en CartContext
    }
  }

  const handleProductClick = (product) => {
    navigate(`/products/${product.id}`)
  }

  return (
    <div className={styles.page}>

      {/* Hero */}
      <section className={styles.hero} aria-label="Bienvenida">
        <div className={styles.heroContent}>
          <p className={styles.heroEyebrow}>Cocina mediterránea auténtica</p>
          <h1 className={styles.heroTitle}>
            El sabor del<br />
            <em>Mediterráneo</em><br />
            en tu mesa
          </h1>
          <p className={styles.heroSubtitle}>
            Platos tradicionales elaborados con ingredientes frescos de temporada.
            De la cocina valenciana a los sabores de Oriente Medio.
          </p>
          <div className={styles.heroActions}>
            <Button variant="primary" size="lg" onClick={() => navigate('/products')}>
              Ver el menú
            </Button>
            <Button variant="secondary" size="lg" onClick={() => navigate('/register')}>
              Crear cuenta
            </Button>
          </div>
        </div>
        <div className={styles.heroDecor} aria-hidden="true">
          <span className={styles.heroEmoji}>🫕</span>
          <span className={styles.heroEmojiSm1}>🥗</span>
          <span className={styles.heroEmojiSm2}>🍋</span>
          <span className={styles.heroEmojiSm3}>🫒</span>
        </div>
      </section>

      {/* Features */}
      <section className={styles.features} aria-label="Nuestros valores">
        {FEATURES.map(({ icon, title, text }) => (
          <div key={title} className={styles.featureCard}>
            <span className={styles.featureIcon}>{icon}</span>
            <h3 className={styles.featureTitle}>{title}</h3>
            <p className={styles.featureText}>{text}</p>
          </div>
        ))}
      </section>

      {/* Catálogo */}
      <section className={styles.catalog}>
        <div className={styles.catalogHeader}>
          <h2 className={styles.catalogTitle}>Nuestro catálogo</h2>
          <p className={styles.catalogSubtitle}>
            Más de 50 platos de la cocina mediterránea, del Levante español a Oriente Medio
          </p>
        </div>
        <ProductGrid
          products={products}
          loading={loading}
          error={error}
          onAddToCart={handleAddToCart}
          onProductClick={handleProductClick}
        />
      </section>
    </div>
  )
}

const FEATURES = [
  {
    icon: '🌿',
    title: 'Ingredientes frescos',
    text: 'Seleccionamos los mejores productos de temporada de productores locales.',
  },
  {
    icon: '👨‍🍳',
    title: 'Recetas auténticas',
    text: 'Elaboradas siguiendo las tradiciones culinarias de cada región mediterránea.',
  },
  {
    icon: '🚚',
    title: 'Entrega rápida',
    text: 'Recibe tu pedido en casa con la máxima frescura garantizada.',
  },
]

export default HomePage
