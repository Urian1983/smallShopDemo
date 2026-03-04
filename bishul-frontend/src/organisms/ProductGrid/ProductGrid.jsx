import { useState, useMemo } from 'react'
import ProductCard from '../../molecules/ProductCard'
import ProductCardSkeleton from '../../molecules/ProductCardSkeleton'
import styles from './ProductGrid.module.css'

const ProductGrid = ({ products = [], loading, error, onAddToCart, onProductClick }) => {
  const [search, setSearch] = useState('')
  const [activeCategory, setActiveCategory] = useState('Todos')
  const [sortBy, setSortBy] = useState('default')

  const categories = useMemo(() => {
    const cats = [...new Set(products.map((p) => p.category).filter(Boolean))].sort()
    return ['Todos', ...cats]
  }, [products])

  const filtered = useMemo(() => {
    let result = products.filter((p) => {
      const matchesCategory = activeCategory === 'Todos' || p.category === activeCategory
      const matchesSearch =
        search.trim() === '' ||
        p.name?.toLowerCase().includes(search.toLowerCase()) ||
        p.brand?.toLowerCase().includes(search.toLowerCase())
      return matchesCategory && matchesSearch
    })
    if (sortBy === 'price-asc') result = [...result].sort((a, b) => a.price - b.price)
    else if (sortBy === 'price-desc') result = [...result].sort((a, b) => b.price - a.price)
    else if (sortBy === 'name') result = [...result].sort((a, b) => a.name.localeCompare(b.name))
    return result
  }, [products, search, activeCategory, sortBy])

  if (error) {
    return (
      <div className={styles.centered}>
        <p className={styles.errorMsg}>⚠️ {error}</p>
      </div>
    )
  }

  return (
    <section className={styles.section} aria-label="Catálogo de productos">
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
          {search && (
            <button className={styles.clearSearch} onClick={() => setSearch('')} aria-label="Limpiar búsqueda">✕</button>
          )}
        </div>
        <select
          className={styles.sortSelect}
          value={sortBy}
          onChange={(e) => setSortBy(e.target.value)}
          aria-label="Ordenar productos"
        >
          <option value="default">Ordenar</option>
          <option value="price-asc">Precio: menor a mayor</option>
          <option value="price-desc">Precio: mayor a menor</option>
          <option value="name">Nombre A-Z</option>
        </select>
      </div>

      <div className={styles.categories} role="tablist" aria-label="Filtrar por categoría">
        {categories.map((cat) => (
          <button
            key={cat}
            role="tab"
            aria-selected={activeCategory === cat}
            className={[styles.categoryBtn, activeCategory === cat ? styles.categoryActive : ''].filter(Boolean).join(' ')}
            onClick={() => setActiveCategory(cat)}
          >
            {cat}
          </button>
        ))}
      </div>

      {!loading && (
        <p className={styles.resultCount} aria-live="polite">
          {filtered.length === 0 ? 'No se encontraron productos' : `${filtered.length} ${filtered.length === 1 ? 'producto' : 'productos'}`}
        </p>
      )}

      {loading ? (
        <div className={styles.grid}>
          {Array.from({ length: 8 }).map((_, i) => (<ProductCardSkeleton key={i} />))}
        </div>
      ) : filtered.length > 0 ? (
        <div className={styles.grid}>
          {filtered.map((product) => (
            <ProductCard key={product.id} product={product} onAddToCart={onAddToCart} onClick={() => onProductClick?.(product)} />
          ))}
        </div>
      ) : (
        <div className={styles.empty}>
          <span className={styles.emptyIcon}>🍽️</span>
          <p className={styles.emptyText}>No hay platos en esta categoría</p>
          <button className={styles.resetBtn} onClick={() => { setSearch(''); setActiveCategory('Todos'); setSortBy('default') }}>
            Ver todos los productos
          </button>
        </div>
      )}
    </section>
  )
}

export default ProductGrid
