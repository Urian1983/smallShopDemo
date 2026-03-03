import styles from './Spinner.module.css'

/**
 * Atom: Spinner
 * @param {'sm'|'md'|'lg'} size
 * @param {string} label — texto accesible para screen readers
 */
const Spinner = ({ size = 'md', label = 'Cargando...', className = '' }) => {
  const classes = [styles.spinner, styles[size], className].filter(Boolean).join(' ')

  return (
    <div className={styles.wrapper} role="status" aria-label={label}>
      <span className={classes} aria-hidden="true" />
      <span className={styles.srOnly}>{label}</span>
    </div>
  )
}

export default Spinner
