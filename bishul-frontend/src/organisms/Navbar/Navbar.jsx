import { useState } from 'react'
import { Link, NavLink, useNavigate } from 'react-router-dom'
import { useAuth } from '../../context/AuthContext'
import Button from '../../atoms/Button'
import styles from './Navbar.module.css'

/**
 * Organism: Navbar
 * Barra de navegación principal con enlaces, carrito y usuario autenticado.
 * @param {number} cartCount — número de ítems en el carrito
 */
const Navbar = ({ cartCount = 0 }) => {
  const { isAuthenticated, isAdmin, user, logout } = useAuth()
  const navigate = useNavigate()
  const [menuOpen, setMenuOpen] = useState(false)

  const handleLogout = () => {
    logout()
    navigate('/')
    setMenuOpen(false)
  }

  const closeMenu = () => setMenuOpen(false)

  return (
    <header className={styles.header}>
      <nav className={styles.nav} aria-label="Navegación principal">

        {/* Logo */}
        <Link to="/" className={styles.logo} onClick={closeMenu}>
          <span className={styles.logoIcon}>🫕</span>
          <span className={styles.logoText}>Bishul</span>
        </Link>

        {/* Links principales — desktop */}
        <ul className={styles.navLinks}>
          <li>
            <NavLink
              to="/products"
              className={({ isActive }) =>
                [styles.navLink, isActive ? styles.active : ''].filter(Boolean).join(' ')
              }
            >
              Productos
            </NavLink>
          </li>
          {isAdmin && (
            <li>
              <NavLink
                to="/admin"
                className={({ isActive }) =>
                  [styles.navLink, styles.adminLink, isActive ? styles.active : ''].filter(Boolean).join(' ')
                }
              >
                Admin
              </NavLink>
            </li>
          )}
        </ul>

        {/* Acciones — desktop */}
        <div className={styles.actions}>
          {isAuthenticated ? (
            <>
              <Link to="/orders" className={styles.iconBtn} aria-label="Mis pedidos">
                <span className={styles.iconBtnIcon}>📋</span>
              </Link>

              <Link to="/cart" className={styles.cartBtn} aria-label={`Carrito, ${cartCount} productos`}>
                <span className={styles.iconBtnIcon}>🛒</span>
                {cartCount > 0 && (
                  <span className={styles.cartBadge} aria-hidden="true">
                    {cartCount > 99 ? '99+' : cartCount}
                  </span>
                )}
              </Link>

              <div className={styles.userMenu}>
                <button className={styles.userBtn} aria-label="Menú de usuario">
                  <span className={styles.userAvatar}>
                    {user?.name?.[0]?.toUpperCase() ?? '?'}
                  </span>
                  <span className={styles.userName}>{user?.name ?? 'Usuario'}</span>
                </button>
                <div className={styles.dropdown}>
                  <Link to="/profile" className={styles.dropdownItem}>
                    Mi perfil
                  </Link>
                  <Link to="/orders" className={styles.dropdownItem}>
                    Mis pedidos
                  </Link>
                  <hr className={styles.dropdownDivider} />
                  <button className={styles.dropdownItem} onClick={handleLogout}>
                    Cerrar sesión
                  </button>
                </div>
              </div>
            </>
          ) : (
            <>
              <Button variant="ghost" size="sm" onClick={() => navigate('/login')}>
                Entrar
              </Button>
              <Button variant="primary" size="sm" onClick={() => navigate('/register')}>
                Registrarse
              </Button>
            </>
          )}
        </div>

        {/* Hamburguesa — mobile */}
        <button
          className={styles.hamburger}
          onClick={() => setMenuOpen((v) => !v)}
          aria-label={menuOpen ? 'Cerrar menú' : 'Abrir menú'}
          aria-expanded={menuOpen}
        >
          <span className={[styles.bar, menuOpen ? styles.barOpen1 : ''].filter(Boolean).join(' ')} />
          <span className={[styles.bar, menuOpen ? styles.barOpen2 : ''].filter(Boolean).join(' ')} />
          <span className={[styles.bar, menuOpen ? styles.barOpen3 : ''].filter(Boolean).join(' ')} />
        </button>
      </nav>

      {/* Mobile menu */}
      <div className={[styles.mobileMenu, menuOpen ? styles.mobileMenuOpen : ''].filter(Boolean).join(' ')}
        aria-hidden={!menuOpen}
      >
        <ul className={styles.mobileLinks}>
          <li>
            <NavLink to="/products" className={styles.mobileLink} onClick={closeMenu}>
              Productos
            </NavLink>
          </li>
          {isAuthenticated && (
            <>
              <li>
                <NavLink to="/cart" className={styles.mobileLink} onClick={closeMenu}>
                  🛒 Carrito {cartCount > 0 && `(${cartCount})`}
                </NavLink>
              </li>
              <li>
                <NavLink to="/orders" className={styles.mobileLink} onClick={closeMenu}>
                  📋 Mis pedidos
                </NavLink>
              </li>
              <li>
                <NavLink to="/profile" className={styles.mobileLink} onClick={closeMenu}>
                  👤 Mi perfil
                </NavLink>
              </li>
              {isAdmin && (
                <li>
                  <NavLink to="/admin" className={styles.mobileLink} onClick={closeMenu}>
                    ⚙️ Admin
                  </NavLink>
                </li>
              )}
              <li>
                <button className={styles.mobileLink} onClick={handleLogout}>
                  Cerrar sesión
                </button>
              </li>
            </>
          )}
          {!isAuthenticated && (
            <>
              <li>
                <NavLink to="/login" className={styles.mobileLink} onClick={closeMenu}>
                  Entrar
                </NavLink>
              </li>
              <li>
                <NavLink to="/register" className={styles.mobileLink} onClick={closeMenu}>
                  Registrarse
                </NavLink>
              </li>
            </>
          )}
        </ul>
      </div>
    </header>
  )
}

export default Navbar
