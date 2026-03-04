import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useCartContext } from '../../context/CartContext'
import { useOrders } from '../../hooks/useOrders'
import Button from '../../atoms/Button'
import Spinner from '../../atoms/Spinner'
import styles from './CartPage.module.css'

const PAYMENT_METHODS = [
  { value: 'CREDIT_CARD', label: '💳 Tarjeta de crédito' },
  { value: 'PAYPAL', label: '🅿️ PayPal' },
  { value: 'BANK_TRANSFER', label: '🏦 Transferencia bancaria' },
]

const CartPage = () => {
  const navigate = useNavigate()
  const { cart, loading, handleUpdateItem, handleRemoveItem, handleClearCart } = useCartContext()
  const { handleCreateOrder } = useOrders()

  const [checkoutOpen, setCheckoutOpen] = useState(false)
  const [checkoutLoading, setCheckoutLoading] = useState(false)
  const [checkoutError, setCheckoutError] = useState('')
  const [form, setForm] = useState({ address: '', postalCode: '', country: '', paymentMethod: '' })
  const [formErrors, setFormErrors] = useState({})

  const items = cart?.cartItems ?? []
  const total = items.reduce(
    (acc, item) => acc + Number(item.productPrice) * item.quantity,
    0
  )

  const handleQuantityChange = async (item, newQty) => {
    if (newQty < 1) return
    try {
      await handleUpdateItem({
        productId: item.productId,
        productName: item.productName,
        productPrice: item.productPrice,
        quantity: newQty,
      })
    } catch {
      // Error gestionado en CartContext
    }
  }

  const handleRemove = async (cartItemId) => {
    try {
      await handleRemoveItem(cartItemId)
    } catch {
      // Error gestionado en CartContext
    }
  }

  const handleClear = async () => {
    try {
      await handleClearCart()
    } catch {
      // Error gestionado en CartContext
    }
  }

  const validateForm = () => {
    const errors = {}
    if (!form.address.trim()) errors.address = 'La dirección es obligatoria'
    if (!form.postalCode.trim()) errors.postalCode = 'El código postal es obligatorio'
    if (!form.country.trim()) errors.country = 'El país es obligatorio'
    if (!form.paymentMethod) errors.paymentMethod = 'Selecciona un método de pago'
    return errors
  }

  const handleCheckout = async (e) => {
    e.preventDefault()
    setCheckoutError('')
    const errors = validateForm()
    if (Object.keys(errors).length > 0) {
      setFormErrors(errors)
      return
    }
    setCheckoutLoading(true)
    try {
      await handleCreateOrder({
        orderNumber: `ORD-${Date.now()}`,
        address: form.address,
        postalCode: form.postalCode,
        country: form.country,
        paymentMethod: form.paymentMethod,
      })
      navigate('/orders')
    } catch (err) {
      setCheckoutError(err.response?.data?.message || 'Error al crear el pedido')
    } finally {
      setCheckoutLoading(false)
    }
  }

  if (loading) {
    return (
      <div className={styles.centered}>
        <Spinner size="lg" />
      </div>
    )
  }

  return (
    <div className={styles.page}>
      <div className={styles.header}>
        <h1 className={styles.title}>Tu carrito</h1>
        {items.length > 0 && (
          <button className={styles.clearBtn} onClick={handleClear}>
            Vaciar carrito
          </button>
        )}
      </div>

      {items.length === 0 ? (
        <div className={styles.empty}>
          <span className={styles.emptyIcon}>🛒</span>
          <p className={styles.emptyText}>Tu carrito está vacío</p>
          <Button variant="primary" onClick={() => navigate('/products')}>
            Ver el menú
          </Button>
        </div>
      ) : (
        <div className={styles.layout}>

          {/* Lista de ítems */}
          <div className={styles.itemsList}>
            {items.map((item) => (
              <div key={item.id} className={styles.item}>
                <div className={styles.itemInfo}>
                  <p className={styles.itemName}>{item.productName}</p>
                  <p className={styles.itemPrice}>
                    {Number(item.productPrice).toFixed(2)} € / ud.
                  </p>
                </div>

                <div className={styles.itemControls}>
                  <div className={styles.qtyControl}>
                    <button
                      className={styles.qtyBtn}
                      onClick={() => handleQuantityChange(item, item.quantity - 1)}
                      aria-label="Reducir cantidad"
                      disabled={item.quantity <= 1}
                    >
                      −
                    </button>
                    <span className={styles.qty}>{item.quantity}</span>
                    <button
                      className={styles.qtyBtn}
                      onClick={() => handleQuantityChange(item, item.quantity + 1)}
                      aria-label="Aumentar cantidad"
                    >
                      +
                    </button>
                  </div>

                  <span className={styles.itemSubtotal}>
                    {(Number(item.productPrice) * item.quantity).toFixed(2)} €
                  </span>

                  <button
                    className={styles.removeBtn}
                    onClick={() => handleRemove(item.id)}
                    aria-label={`Eliminar ${item.productName}`}
                  >
                    ✕
                  </button>
                </div>
              </div>
            ))}
          </div>

          {/* Resumen + checkout */}
          <div className={styles.summary}>
            <div className={styles.summaryCard}>
              <h2 className={styles.summaryTitle}>Resumen</h2>
              <div className={styles.summaryRow}>
                <span>Subtotal ({items.length} {items.length === 1 ? 'producto' : 'productos'})</span>
                <span>{total.toFixed(2)} €</span>
              </div>
              <div className={styles.summaryRow}>
                <span>Envío</span>
                <span className={styles.freeShipping}>Gratis</span>
              </div>
              <div className={styles.summaryTotal}>
                <span>Total</span>
                <span>{total.toFixed(2)} €</span>
              </div>

              {!checkoutOpen ? (
                <Button
                  variant="primary"
                  size="lg"
                  fullWidth
                  onClick={() => setCheckoutOpen(true)}
                >
                  Finalizar pedido
                </Button>
              ) : (
                <form className={styles.checkoutForm} onSubmit={handleCheckout} noValidate>
                  <h3 className={styles.checkoutTitle}>Datos de envío</h3>

                  {checkoutError && (
                    <p className={styles.checkoutError}>⚠️ {checkoutError}</p>
                  )}

                  <div className={styles.field}>
                    <label className={styles.label}>Dirección *</label>
                    <input
                      className={[styles.input, formErrors.address ? styles.inputError : ''].join(' ')}
                      placeholder="Calle Mayor 1, Apto 2B"
                      value={form.address}
                      onChange={(e) => setForm((p) => ({ ...p, address: e.target.value }))}
                    />
                    {formErrors.address && <span className={styles.fieldError}>{formErrors.address}</span>}
                  </div>

                  <div className={styles.fieldRow}>
                    <div className={styles.field}>
                      <label className={styles.label}>Código postal *</label>
                      <input
                        className={[styles.input, formErrors.postalCode ? styles.inputError : ''].join(' ')}
                        placeholder="28001"
                        value={form.postalCode}
                        onChange={(e) => setForm((p) => ({ ...p, postalCode: e.target.value }))}
                      />
                      {formErrors.postalCode && <span className={styles.fieldError}>{formErrors.postalCode}</span>}
                    </div>

                    <div className={styles.field}>
                      <label className={styles.label}>País *</label>
                      <input
                        className={[styles.input, formErrors.country ? styles.inputError : ''].join(' ')}
                        placeholder="España"
                        value={form.country}
                        onChange={(e) => setForm((p) => ({ ...p, country: e.target.value }))}
                      />
                      {formErrors.country && <span className={styles.fieldError}>{formErrors.country}</span>}
                    </div>
                  </div>

                  {/* Selector de método de pago */}
                  <div className={styles.field}>
                    <label className={styles.label}>Método de pago *</label>
                    <div className={styles.paymentMethods}>
                      {PAYMENT_METHODS.map((method) => (
                        <button
                          key={method.value}
                          type="button"
                          className={[
                            styles.paymentOption,
                            form.paymentMethod === method.value ? styles.paymentOptionSelected : ''
                          ].join(' ')}
                          onClick={() => setForm((p) => ({ ...p, paymentMethod: method.value }))}
                        >
                          {method.label}
                        </button>
                      ))}
                    </div>
                    {formErrors.paymentMethod && (
                      <span className={styles.fieldError}>{formErrors.paymentMethod}</span>
                    )}
                  </div>

                  <div className={styles.checkoutActions}>
                    <Button type="submit" variant="primary" size="lg" fullWidth loading={checkoutLoading}>
                      Confirmar pedido
                    </Button>
                    <Button
                      type="button"
                      variant="ghost"
                      size="md"
                      fullWidth
                      onClick={() => setCheckoutOpen(false)}
                    >
                      Cancelar
                    </Button>
                  </div>
                </form>
              )}
            </div>
          </div>
        </div>
      )}
    </div>
  )
}

export default CartPage
