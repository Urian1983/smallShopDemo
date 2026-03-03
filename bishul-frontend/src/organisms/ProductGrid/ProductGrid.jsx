import { useState, useMemo } from 'react'
import ProductCard from '../../molecules/ProductCard'
import Spinner from '../../atoms/Spinner'
import styles from './ProductGrid.module.css'

/**
 * Organism: ProductGrid
 * Muestra el catálogo de productos con filtro por categoría y búsqueda.
 *
 * @param {Array}    products    — lista de ProductResponseDTO
 * @param {boolean}  loading
 * @param {string}   error
 * @param {Function} onAddToCart — (product) => void
 * @param {Function} onProductClick — (product) => void
 */
const ProductGrid = ({ products = [], loading, error, onAddToCart, onProductClick }) => {
  const [search, setSearch] = useState('')
  const [activeCategory, setActiveCategory] = useState('Todos')

  // Extraer categorías únicas del catálogo
  const categories = useMemo(() => {
    const cats = [...new Set(products.map((p) => p.category))].sort()
    return ['Todos', ...cats]
  }, [products])

  // Filtrar por búsqueda y categoría
  const filtered = useMemo(() => {
    return products.filter((p) => {
      const matchesCategory = activeCategory === 'Todos' || p.category === activeCategory
      const matchesSearch =
        search.trim() === '' ||
        p.name.toLowerCase().includes(search.toLowerCase()) ||
        p.brand?.toLowerCase().includes(search.toLowerCase())
      return matchesCategory && matchesSearch
    })
  }, [products, search, activeCategory])

  if (loading) {
    return (
      <div className={styles.centered}>
        <Spinner size="lg" label="Cargando productos..." />
      </div>
    )
  }

  if (error) {
    return (
      <div className={styles.centered}>
        <p className={styles.errorMsg}>⚠️ {error}</p>
      </div>
    )
  }

  return (
    <section className={styles.section} aria-label="Catálogo de productos">

      {/* Controles: búsqueda + categorías */}
      <div className={styles.controls}>
        <div className={styles.searchWrapper}>
          <span className={styles.searchIcon} aria-hidden="true">🔍</span>
          <input
            type="search"
            placeholder="Buscar platos o marcas..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className={styles.searchInput}
            aria-label="Buscar productos"
          />
        </div>

        <div className={styles.categories} role="tablist" aria-label="Filtrar por categoría">
          {categories.map((cat) => (
            <button
              key={cat}
              role="tab"
              aria-selected={activeCategory === cat}
              className={[
                styles.categoryBtn,
                activeCategory === cat ? styles.categoryActive : '',
              ]
                .filter(Boolean)
                .join(' ')}
              onClick={() => setActiveCategory(cat)}
            >
              {cat}
            </button>
          ))}
        </div>
      </div>

      {/* Contador de resultados */}
      <p className={styles.resultCount} aria-live="polite">
        {filtered.length === 0
          ? 'No se encontraron productos'
          : `${filtered.length} ${filtered.length === 1 ? 'producto' : 'productos'}`}
      </p>

      {/* Grid de productos */}
      {filtered.length > 0 ? (
        <div className={styles.grid}>
          {filtered.map((product) => (
            <ProductCard
              key={product.id}
              product={product}
              onAddToCart={onAddToCart}
              onClick={() => onProductClick?.(product)}
            />
          ))}
        </div>
      ) : (
        <div className={styles.empty}>
          <span className={styles.emptyIcon}>🍽️</span>
          <p className={styles.emptyText}>No hay platos en esta categoría</p>
          <button
            className={styles.resetBtn}
            onClick={() => { setSearch(''); setActiveCategory('Todos') }}
          >
            Ver todos los productos
          </button>
        </div>
      )}
    </section>
  )
}

export default ProductGrid
