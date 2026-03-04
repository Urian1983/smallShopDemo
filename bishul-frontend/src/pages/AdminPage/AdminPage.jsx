import { useState, useEffect } from 'react'
import { useAuth } from '../../context/AuthContext'
import { useNavigate } from 'react-router-dom'
import { getProducts, createProduct, deleteProduct } from '../../services/productService'
import { getAllOrders, updateOrderStatus } from '../../services/orderService'
import Button from '../../atoms/Button'
import Spinner from '../../atoms/Spinner'
import Badge from '../../atoms/Badge'
import styles from './AdminPage.module.css'

const TABS = ['Productos', 'Pedidos']

const STATUS_OPTIONS = ['PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED']

const emptyProduct = {
  name: '', description: '', price: '', stock: '', brand: '', category: '', imageUrl: ''
}

const AdminPage = () => {
  const { isAdmin } = useAuth()
  const navigate = useNavigate()
  const [activeTab, setActiveTab] = useState('Productos')

  // Products
  const [products, setProducts] = useState([])
  const [prodLoading, setProdLoading] = useState(false)
  const [prodError, setProdError] = useState('')
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState(emptyProduct)
  const [formLoading, setFormLoading] = useState(false)
  const [formError, setFormError] = useState('')

  // Orders
  const [orders, setOrders] = useState([])
  const [ordLoading, setOrdLoading] = useState(false)
  const [ordError, setOrdError] = useState('')

  useEffect(() => {
    if (!isAdmin) {
      navigate('/')
      return
    }
    if (activeTab === 'Productos') fetchProducts()
    else fetchOrders()
  }, [activeTab, isAdmin])

  const fetchProducts = async () => {
    setProdLoading(true)
    setProdError('')
    try {
      const data = await getProducts()
      setProducts(data)
    } catch {
      setProdError('Error al cargar productos')
    } finally {
      setProdLoading(false)
    }
  }

  const fetchOrders = async () => {
    setOrdLoading(true)
    setOrdError('')
    try {
      const data = await getAllOrders()
      setOrders(data)
    } catch {
      setOrdError('Error al cargar pedidos')
    } finally {
      setOrdLoading(false)
    }
  }

  const handleCreateProduct = async (e) => {
    e.preventDefault()
    setFormLoading(true)
    setFormError('')
    try {
      await createProduct({
        ...form,
        price: parseFloat(form.price),
        stock: parseInt(form.stock),
      })
      setForm(emptyProduct)
      setShowForm(false)
      fetchProducts()
    } catch (err) {
      setFormError(err.response?.data?.message || 'Error al crear el producto')
    } finally {
      setFormLoading(false)
    }
  }

  const handleDelete = async (id) => {
    if (!window.confirm('¿Eliminar este producto?')) return
    try {
      await deleteProduct(id)
      setProducts((prev) => prev.filter((p) => p.id !== id))
    } catch {
      alert('Error al eliminar el producto')
    }
  }

  const handleStatusChange = async (orderId, newStatus) => {
    try {
      await updateOrderStatus(orderId, newStatus)
      setOrders((prev) =>
        prev.map((o) => (o.id === orderId ? { ...o, status: newStatus } : o))
      )
    } catch {
      alert('Error al actualizar el estado')
    }
  }

  if (!isAdmin) return null

  return (
    <div className={styles.page}>
      <div className={styles.header}>
        <h1 className={styles.title}>Panel de administración</h1>
      </div>

      {/* Tabs */}
      <div className={styles.tabs}>
        {TABS.map((tab) => (
          <button
            key={tab}
            className={[styles.tab, activeTab === tab ? styles.tabActive : ''].join(' ')}
            onClick={() => setActiveTab(tab)}
          >
            {tab}
          </button>
        ))}
      </div>

      {/* PRODUCTOS */}
      {activeTab === 'Productos' && (
        <div className={styles.section}>
          <div className={styles.sectionHeader}>
            <span className={styles.count}>{products.length} productos</span>
            <Button variant="primary" size="sm" onClick={() => setShowForm(!showForm)}>
              {showForm ? 'Cancelar' : '+ Nuevo producto'}
            </Button>
          </div>

          {/* Formulario nuevo producto */}
          {showForm && (
            <form className={styles.form} onSubmit={handleCreateProduct}>
              <h3 className={styles.formTitle}>Nuevo producto</h3>
              {formError && <p className={styles.formError}>⚠️ {formError}</p>}
              <div className={styles.formGrid}>
                {[
                  { key: 'name', label: 'Nombre', placeholder: 'Tahini artesano' },
                  { key: 'brand', label: 'Marca', placeholder: 'Al Amir' },
                  { key: 'category', label: 'Categoría', placeholder: 'Salsas' },
                  { key: 'price', label: 'Precio (€)', placeholder: '9.99', type: 'number' },
                  { key: 'stock', label: 'Stock', placeholder: '50', type: 'number' },
                  { key: 'imageUrl', label: 'URL imagen', placeholder: 'https://...' },
                ].map(({ key, label, placeholder, type = 'text' }) => (
                  <div key={key} className={styles.field}>
                    <label className={styles.label}>{label}</label>
                    <input
                      className={styles.input}
                      type={type}
                      placeholder={placeholder}
                      value={form[key]}
                      onChange={(e) => setForm((p) => ({ ...p, [key]: e.target.value }))}
                    />
                  </div>
                ))}
              </div>
              <div className={styles.field}>
                <label className={styles.label}>Descripción</label>
                <textarea
                  className={styles.textarea}
                  placeholder="Descripción del producto..."
                  rows={3}
                  value={form.description}
                  onChange={(e) => setForm((p) => ({ ...p, description: e.target.value }))}
                />
              </div>
              <div className={styles.formActions}>
                <Button type="submit" variant="primary" loading={formLoading}>
                  Crear producto
                </Button>
              </div>
            </form>
          )}

          {/* Tabla de productos */}
          {prodLoading ? (
            <div className={styles.centered}><Spinner size="lg" /></div>
          ) : prodError ? (
            <p className={styles.errorMsg}>{prodError}</p>
          ) : (
            <div className={styles.table}>
              <div className={[styles.tableRow, styles.tableHead].join(' ')}>
                <span>Nombre</span>
                <span>Marca</span>
                <span>Categoría</span>
                <span>Precio</span>
                <span>Stock</span>
                <span>Acciones</span>
              </div>
              {products.map((p) => (
                <div key={p.id} className={styles.tableRow}>
                  <span className={styles.productName}>{p.name}</span>
                  <span className={styles.cell}>{p.brand}</span>
                  <span className={styles.cell}>{p.category}</span>
                  <span className={styles.cell}>{Number(p.price).toFixed(2)} €</span>
                  <span className={styles.cell}>{p.stock}</span>
                  <span className={styles.actions}>
                    <button className={styles.deleteBtn} onClick={() => handleDelete(p.id)}>
                      Eliminar
                    </button>
                  </span>
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {/* PEDIDOS */}
      {activeTab === 'Pedidos' && (
        <div className={styles.section}>
          <div className={styles.sectionHeader}>
            <span className={styles.count}>{orders.length} pedidos</span>
          </div>

          {ordLoading ? (
            <div className={styles.centered}><Spinner size="lg" /></div>
          ) : ordError ? (
            <p className={styles.errorMsg}>{ordError}</p>
          ) : (
            <div className={styles.ordersList}>
              {orders.map((order) => (
                <div key={order.id} className={styles.orderCard}>
                  <div className={styles.orderMeta}>
                    <span className={styles.orderId}>#{order.orderNumber?.slice(-8).toUpperCase()}</span>
                    <span className={styles.orderUser}>{order.userEmail ?? `Usuario #${order.userId}`}</span>
                    <span className={styles.orderDate}>
                      {new Date(order.createdAt).toLocaleDateString('es-ES')}
                    </span>
                    <span className={styles.orderTotal}>{Number(order.totalPrice).toFixed(2)} €</span>
                  </div>
                  <div className={styles.orderStatus}>
                    <Badge variant={order.status?.toLowerCase()}>{order.status}</Badge>
                    <select
                      className={styles.statusSelect}
                      value={order.status}
                      onChange={(e) => handleStatusChange(order.id, e.target.value)}
                    >
                      {STATUS_OPTIONS.map((s) => (
                        <option key={s} value={s}>{s}</option>
                      ))}
                    </select>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      )}
    </div>
  )
}

export default AdminPage
