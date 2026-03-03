import styles from './Badge.module.css'

/**
 * Atom: Badge
 * @param {'category'|'pending'|'paid'|'shipped'|'delivered'|'cancelled'|'approved'|'rejected'} variant
 * @param {'sm'|'md'} size
 */
const Badge = ({ children, variant = 'category', size = 'md', className = '' }) => {
  const classes = [styles.badge, styles[variant], styles[size], className]
    .filter(Boolean)
    .join(' ')

  return <span className={classes}>{children}</span>
}

export default Badge
