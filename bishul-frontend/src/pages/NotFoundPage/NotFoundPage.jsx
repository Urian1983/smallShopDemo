import { useNavigate } from 'react-router-dom'
import Button from '../../atoms/Button'
import styles from './NotFoundPage.module.css'

/**
 * Page: NotFoundPage
 * Página de error 404 con diseño mediterráneo.
 */
const NotFoundPage = () => {
  const navigate = useNavigate()

  return (
    <div className={styles.page}>
      <div className={styles.content}>
        <span className={styles.emoji}>🫕</span>
        <h1 className={styles.code}>404</h1>
        <h2 className={styles.title}>Esta página se ha perdido en el zoco</h2>
        <p className={styles.subtitle}>
          Parece que el plato que buscas no está en nuestro menú. <br />
          Vuelve al inicio y descubre todo lo que tenemos para ti.
        </p>
        <div className={styles.actions}>
          <Button variant="primary" size="lg" onClick={() => navigate('/')}>
            Volver al inicio
          </Button>
          <Button variant="secondary" size="lg" onClick={() => navigate('/products')}>
            Ver el menú
          </Button>
        </div>
      </div>

      {/* Decoración de fondo */}
      <div className={styles.decoration} aria-hidden="true">
        <span>🌿</span>
        <span>🫙</span>
        <span>🌶️</span>
        <span>🧄</span>
        <span>🫒</span>
      </div>
    </div>
  )
}

export default NotFoundPage
