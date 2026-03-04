import styles from './Skeleton.module.css'

/**
 * Atom: Skeleton
 * Placeholder animado para estados de carga.
 *
 * @param {'text'|'rect'|'circle'} variant
 * @param {string} width
 * @param {string} height
 * @param {string} className
 */
const Skeleton = ({ variant = 'rect', width, height, className = '' }) => {
  return (
    <span
      className={[styles.skeleton, styles[variant], className].filter(Boolean).join(' ')}
      style={{ width, height }}
      aria-hidden="true"
    />
  )
}

export default Skeleton
