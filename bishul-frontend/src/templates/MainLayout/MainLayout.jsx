import { Outlet } from 'react-router-dom'
import Navbar from '../../organisms/Navbar'
import { useCartContext } from '../../context/CartContext'
import styles from './MainLayout.module.css'

/**
 * Template: MainLayout
 * Envuelve todas las páginas con el Navbar y un contenedor principal.
 * Obtiene cartCount del CartContext para pasárselo al Navbar.
 */
const MainLayout = () => {
  const { cartCount } = useCartContext()

  return (
    <div className={styles.layout}>
      <Navbar cartCount={cartCount} />
      <main className={styles.main}>
        <Outlet />
      </main>
      <footer className={styles.footer}>
        <div className={styles.footerInner}>
          <span className={styles.footerLogo}>🫕 Bishul Cooking Shop</span>
          <p className={styles.footerText}>
            © {new Date().getFullYear()} Bishul · Cocina mediterránea auténtica
          </p>
        </div>
      </footer>
    </div>
  )
}

export default MainLayout
